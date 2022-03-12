package com.gabrielrossilopes.appmalote.model.tests;

import com.gabrielrossilopes.appmalote.exception.LetraNaoCorrespondeComClasseException;
import com.gabrielrossilopes.appmalote.model.dominio.*;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ArquivoTeste {

    private static Map<String, Class> identificadorDeClassesMap;

    private static Map<Class, List<Object>> objetosMap;

    public static Map<Class, List<Object>> getObjetosMap() {
        return objetosMap;
    }

    private static final Pattern pattern = Pattern.compile("(.)\\.(.+)=(.+)");

    private static final int classeGroup = 1;
    private static final int campoGroup = 2;
    private static final int valorGroup = 3;

    private static final Matcher getMatcher(String s) {
        return pattern.matcher(s);
    }

    static {
        identificadorDeClassesMap = new HashMap<>();
        identificadorDeClassesMap.put("m", Malote.class);
        identificadorDeClassesMap.put("e", Empresa.class);
        identificadorDeClassesMap.put("p", Pagamento.class);
        identificadorDeClassesMap.put("t", Transferencia.class);
        identificadorDeClassesMap.put("d", Deposito.class);

        objetosMap = new HashMap<>();


    }

    public static void main(String[] args) {
        File file = new File("files/arquivo");

        final List<Objects> objs = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8))) {

            String linha;

            while ((linha = reader.readLine()) != null) {

                Optional<Set<Class>> classesOp = Optional.ofNullable(buscaClassesDaLinha(linha));
                if (classesOp.isEmpty())
                    continue;

                Set<Class> classes = classesOp.get();

                for (Class clazz : classes) {
                    Object instance = clazz.getDeclaredConstructor().newInstance();

                    Arrays.stream(linha.split(";"))
                            .map(String::trim).filter(s-> {
                                try {
                                    return buscaClasseDeString(s).equals(clazz);
                                } catch (LetraNaoCorrespondeComClasseException e) {
                                    e.printStackTrace();
                                    return false;
                                }
                            }).forEach(s -> {
                                Matcher matcher = getMatcher(s);
                                if (matcher.find()) {
                                    atribuiCampo(clazz, instance, matcher.group(valorGroup), matcher.group(campoGroup));
                                }
                            });

                    addClassInstance(clazz, instance);
                }

            }

        } catch (IOException | LetraNaoCorrespondeComClasseException | NoSuchMethodException | InstantiationException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }

        Malote malote = criaMalote();
        escreveArquivoMalote(malote);
        System.out.println("Escrito em arquivo_out");
    }

    private static Malote criaMalote() {
        Malote malote = (Malote) objetosMap.get(Malote.class).get(0);
        Empresa empresa = (Empresa) objetosMap.get(Empresa.class).get(0);

        malote.setEmpresa(empresa);
        empresa.addMalote(malote);
        final List<Transacao> transacoes = new ArrayList<>();

        objetosMap.keySet().stream().filter(Transacao.class::isAssignableFrom).forEach(tk-> {
            transacoes.addAll(getObjetosMap().get(tk).stream().map(t -> (Transacao) t).collect(Collectors.toList()));
        });

        malote.addTransacoes(transacoes);

        return malote;

    }

    private static Class obtemClassePorLetra(String letra) throws LetraNaoCorrespondeComClasseException {
        Class clazz = identificadorDeClassesMap.get(letra);

        if (Objects.isNull(clazz))
            throw new LetraNaoCorrespondeComClasseException("A letra " + letra + "Não corresponde a nenhuma clases");

        return clazz;
    }

    private static void addClassInstance(Class clazz, Object obj) {
        List<Object> lista = objetosMap.get(clazz);
        if (Objects.isNull(lista)) {
            lista = new ArrayList<>();
        }
        lista.add(obj);
        objetosMap.put(clazz, lista);
    }

    private static void atribuiCampo(Class clazz, Object obj, String valueAsString, String nomeCampo) {

        try {
            Field campo = clazz.getDeclaredField(nomeCampo);
            campo.setAccessible(true);

            if (campo.getType().equals(LocalDateTime.class)) {
                DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                LocalDateTime data = LocalDateTime.parse(valueAsString, formato);
                campo.set(obj.getClass().cast(obj), data);
            } else if (campo.getType().equals(String.class)) {
                campo.set(obj.getClass().cast(obj), valueAsString);
            }
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    private static Set<Class> buscaClassesDaLinha(String s) throws LetraNaoCorrespondeComClasseException {
        Set<Class> classes = new HashSet<>();
        List<String> ss = Arrays.asList(s.split(";"));

        ss.forEach(i-> {
            Matcher matcher = getMatcher(i.trim());
            if (matcher.find()) {
                try {
                    classes.add(obtemClassePorLetra(matcher.group(classeGroup)));
                } catch (LetraNaoCorrespondeComClasseException e) {
                    e.printStackTrace();
                }
            }
        });
        return classes;
    }

    private static Class buscaClasseDeString(String s) throws LetraNaoCorrespondeComClasseException {
        Matcher matcher = getMatcher(s);
        if (matcher.find()) {
            return obtemClassePorLetra(matcher.group(classeGroup));
        }
        return null;
    }

    private static void escreveArquivoMalote(Malote malote) {

        File file = new File("arquivo_out");
        try (PrintWriter writer = new PrintWriter(new FileWriter(file))){
            writer.println("Malote criado na data " + malote.getData() + " pela empresa " + malote.getEmpresa().getNome() + "" +
                    " de cnpj " + malote.getEmpresa().getCnpj());
            writer.print("Transações:\n");
            malote.getTransacoes().forEach(t->{
                writer.print(t.getTipoTransacao() + " -> ");
                Arrays.stream(t.getClass().getDeclaredFields()).forEach(f-> {
                    f.setAccessible(true);
                    if (!f.getName().equals("id")) {
                        try {
                            writer.print(f.getName() + ":" + f.get(t) + " ");
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                });
                writer.print("\n");
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
