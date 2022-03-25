package com.gabrielrossilopes.appmalote.dataseed;

import com.gabrielrossilopes.appmalote.model.dominio.*;
import com.gabrielrossilopes.appmalote.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private DepositoRepository depositoRepository;

    @Autowired
    private TransferenciaRepository transferenciaRepository;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private MaloteRepository maloteRepository;


    @Override
    public void run(String... args) throws Exception {

        List<Empresa> empresas = addEmpresas();
        List<Usuario> usuarios = addUsuarios(empresas.get(0));
        List<Malote> malotes = addMalotes(empresas.get(0), usuarios.get(1));
        addDepositos(malotes.get(0));
        addPagamentos(malotes.get(0));
        addTransferencia(malotes.get(0));
    }

    private List<Usuario> addUsuarios(Empresa empresa) {
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(new Usuario("admin@admin.com", "Elberth", "123", true, empresa, false));
        usuarios.add(new Usuario("fulaninho@gmail.com", "Fulano", "123", false, empresa, false));
        usuarios.add(new Usuario("rodrigo@gmail.com", "Rodrigo", "123", false, empresa, false));
        usuarios.add(new Usuario("danielle@gmail.com", "Danielle", "123", false, empresa, false));
        usuarios.add(new Usuario("beltrano@gmail.com", "Beltrano", "123", false, empresa, false));
        usuarios.add(new Usuario("lenon@gmail.com", "Lenon", "123", false, empresa, false));
        return usuarioRepository.saveAll(usuarios);
    }

    private List<Empresa> addEmpresas() {
        List<Empresa> empresas = new ArrayList<>();
        empresas.add(new Empresa("36178046000142", "Ecorp"));
        empresas.add(new Empresa("00556585000120", "X-Devs"));
        empresas.add(new Empresa("54325282000126", "Cjv consultoria"));
        empresas.add(new Empresa("67942949000181", "Bairulinhm"));
        return empresaRepository.saveAll(empresas);
    }

    private List<Deposito> addDepositos(Malote malote) {
        List<Deposito> depositos = new ArrayList<>();
        depositos.add(new Deposito("25917576076", "Fulano da Silva", malote, new BigDecimal(12321354)));
        depositos.add(new Deposito("17794293049", "Orlando Silva", malote, new BigDecimal(889897)));
        depositos.add(new Deposito("01777779073", "Rui da Silva", malote, new BigDecimal(7898090)));
        depositos.add(new Deposito("76194308060", "Rodrigo Rodrigues", malote, new BigDecimal(89765434)));
        return depositoRepository.saveAll(depositos);
    }

    private List<Pagamento> addPagamentos(Malote malote) {
        List<Pagamento> pagamentos = new ArrayList<>();
        pagamentos.add(new Pagamento("56529545000144", malote, new BigDecimal(2300)));
        pagamentos.add(new Pagamento("93471399000170", malote, new BigDecimal(177787)));
        pagamentos.add(new Pagamento("33026320000170", malote, new BigDecimal(8809787)));
        pagamentos.add(new Pagamento("84203575000100", malote, new BigDecimal(12127)));
        return pagamentoRepository.saveAll(pagamentos);
    }

    private List<Transferencia> addTransferencia(Malote malote) {
        List<Transferencia> transferencias = new ArrayList<>();
        transferencias.add(new Transferencia("78787878", "1231312123", malote, new BigDecimal(123123)));
        transferencias.add(new Transferencia("1111", "00123", malote, new BigDecimal(7867889)));
        transferencias.add(new Transferencia("1231231", "324324", malote, new BigDecimal(98989)));
        transferencias.add(new Transferencia("78786", "89789", malote, new BigDecimal(3436789)));
        return transferenciaRepository.saveAll(transferencias);
    }

    private List<Malote> addMalotes(Empresa empresa, Usuario usuario) {
        List<Malote> malotes = new ArrayList<>();
        malotes.add(new Malote(empresa, usuario));
        return maloteRepository.saveAll(malotes);
    }
}
