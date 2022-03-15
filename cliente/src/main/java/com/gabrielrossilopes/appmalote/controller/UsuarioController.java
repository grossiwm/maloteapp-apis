package com.gabrielrossilopes.appmalote.controller;

import com.gabrielrossilopes.appmalote.dto.UsuarioDTO;
import com.gabrielrossilopes.appmalote.model.dominio.*;
import com.gabrielrossilopes.appmalote.service.*;
import com.gabrielrossilopes.appmalote.session.UsuarioLogadoSession;
import com.gabrielrossilopes.appmalote.utils.DataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioLogadoSession usuarioLogado;

	@Autowired
	private EmpresaService empresaService;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private DepositoService depositoService;

	@Autowired
	private PagamentoService pagamentoService;

	@Autowired
	private MaloteService maloteService;

	@Autowired
	private TransferenciaService transferenciaService;


	@GetMapping("/perfil")
	public String perfil(Model model) {
		Usuario usuario = usuarioService.getUsuarioById(usuarioLogado.getId());
		model.addAttribute("usuario", usuario);
		return "perfil";
	}

	@GetMapping("/403")
	@ResponseBody
	public String forbidden() {
		return "<html><body><h1> 403 - Forbidden </h2></body></html>";
	}

	@GetMapping("/solicitar-acesso")
	public String solcicitarAcesso(Model model	) {
		model.addAttribute("empresas", empresaService.buscaTodasOrdenado());
		return "solicitarAcesso";
	}

	@PostMapping("/solicitar-acesso")
	public String processaSolicitacao(UsuarioDTO usuarioDTO) {
		Empresa empresa = empresaService.buscaPorId(usuarioDTO.getEmpresa());

		Usuario usuario = new Usuario();
		usuario.setNome(usuarioDTO.getNome());
		usuario.setEmpresa(empresa);
		usuario.setEmail(usuarioDTO.getEmail());
		usuario.setSenha(usuarioDTO.getSenha());
		usuario.setAdmin(false);
		usuario.setAceito(false);

		usuarioService.cadastrarUsuario(usuario);

		return "redirect:/usuario/perfil";
	}

	@GetMapping("/aguardando-aceite")
	public String aguardando(Model model) {
		String username = usuarioLogado.getEmail().split("@")[0];
		model.addAttribute("username",username);
		if (usuarioLogado.isAceito())
			return "redirect:/usuario/perfil";
		return "aguardandoAceite";
	}

	@GetMapping("/listar-usuarios")
	public String listarUsuarios(Model model) {
		List<Usuario> usuarios = usuarioService.busucaTodos();
		model.addAttribute("usuarios", usuarios);
		return "admin/listarUsuarios";
	}

	@GetMapping("/listar-depositos")
	public String listarDepositos(Model model) {
		List<Deposito> depositos = depositoService.getAllDepositos();
		model.addAttribute("depositos", depositos);
		return "listarDepositos";
	}

	@GetMapping("/alterar-deposito/{id}")
	public String alterarDeposito(Model model, @PathVariable Long id) {
		Deposito deposito = depositoService.buscaPorId(id);
		model.addAttribute("deposito", deposito);
		return "alterarDeposito";
	}

	@PostMapping("/alterar-deposito")
	public String alterarDeposito(Deposito deposito) {
		Deposito deposito1 = depositoService.alteraDeposito(deposito);
		return "redirect:/usuario/alterar-malote/" + deposito1.getMalote().getId();
	}

	@GetMapping("/novo-deposito/{maloteId}")
	public String novoDeposito(Model model, @PathVariable Long maloteId) {
		Deposito deposito = new Deposito();
		Malote malote = maloteService.getById(maloteId);
		deposito.setMalote(malote);
		model.addAttribute("deposito", deposito);
		return "alterarDeposito";
	}

	@PostMapping("/novo-deposito/{maloteId}")
	public String novoDeposito(Deposito deposito, @PathVariable Long maloteId) {
		Malote malote = maloteService.getById(maloteId);
		deposito.setMalote(malote);
		depositoService.salvaDeposito(deposito);
		return "redirect:/usuario/alterar-malote/" + deposito.getMalote().getId();
	}

	@GetMapping("/remove-deposito/{id}")
	public String removeDeposito(@PathVariable Long id) {
		Deposito deposito = depositoService.buscaPorId(id);
		depositoService.removeDeposito(deposito);
		return "redirect:/usuario/alterar-malote/" + deposito.getMalote().getId();
	}

	@GetMapping("/listar-pagamentos")
	public String listarPagamentos(Model model) {
		List<Pagamento> pagamentos = pagamentoService.getAllPagamentos().stream().sorted(Comparator.comparing(Pagamento::getCnpjRecebedor)).toList();
		model.addAttribute("pagamentos", pagamentos);
		return "listarPagamentos";
	}

	@GetMapping("/alterar-pagamento/{id}")
	public String alterarPagamento(@PathVariable Long id, Model model) {
		Pagamento pagamento = pagamentoService.buscarPorId(id);
		model.addAttribute("pagamento", pagamento);
		return "alterarPagamento";
	}

	@PostMapping("/alterar-pagamento")
	public String alterarPagamento(Pagamento pagamento) {
		Pagamento pagamento1 = pagamentoService.alterarPagamento(pagamento);
		return "redirect:/usuario/alterar-malote/" + pagamento1.getMalote().getId();
	}

	@GetMapping("/novo-pagamento/{maloteId}")
	public String novoPagamento(Model model, @PathVariable Long maloteId) {
		Malote malote = maloteService.getById(maloteId);
		Pagamento pagamento = new Pagamento();
		pagamento.setMalote(malote);
		model.addAttribute("pagamento", pagamento);
		return "alterarPagamento";
	}

	@PostMapping("/novo-pagamento/{maloteId}")
	public String novoPagamento(Pagamento pagamento, @PathVariable Long maloteId) {
		Malote malote = maloteService.getById(maloteId);
		pagamento.setMalote(malote);
		pagamentoService.salvarPagamento(pagamento);
		return "redirect:/usuario/alterar-malote/" + pagamento.getMalote().getId();
	}

	@GetMapping("/remove-pagamento/{id}")
	public String removePagamento(@PathVariable Long id) {
		Pagamento pagamento = pagamentoService.buscarPorId(id);
		pagamentoService.removerPagamento(pagamento);
		return "redirect:/usuario/alterar-malote/" + pagamento.getMalote().getId();
	}

	@GetMapping("/listar-transferencias")
	public String listarTransferencias(Model model) {
		List<Transferencia> transferencias = transferenciaService.getAllTransferencia().stream().sorted(Comparator.comparing(Transferencia::getContaOrigem)).toList();
		model.addAttribute("transferencias", transferencias);
		return "listarTransferencias";
	}

	@GetMapping("/alterar-transferencia/{id}")
	public String alterarTransferencia(@PathVariable Long id, Model model) {
		Transferencia transferencia = transferenciaService.buscaPorId(id);
		model.addAttribute("transferencia", transferencia);
		return "alterarTransferencia";
	}

	@PostMapping("/alterar-transferencia")
	public String alterarTransferencia(Transferencia transferencia) {
		Transferencia transferencia1 = transferenciaService.alteraTransferencia(transferencia);
		return "redirect:/usuario/alterar-malote/" + transferencia1.getMalote().getId();
	}

	@GetMapping("/nova-transferencia/{maloteId}")
	public String novaTransferencia(Model model, @PathVariable Long maloteId) {
		Malote malote = maloteService.getById(maloteId);
		Transferencia transferencia = new Transferencia();
		transferencia.setMalote(malote);
		model.addAttribute("transferencia", transferencia);
		return "alterarTransferencia";
	}

	@PostMapping("/nova-transferencia/{maloteId}")
	public String novaTransferencia(Transferencia transferencia, @PathVariable Long maloteId) {
		Malote malote = maloteService.getById(maloteId);
		transferencia.setMalote(malote);
		transferenciaService.salvaTransferencia(transferencia);
		return "redirect:/usuario/alterar-malote/" + transferencia.getMalote().getId();
	}

	@GetMapping("/remove-transferencia/{id}")
	public String removeTransferencia(@PathVariable Long id) {
		Transferencia transferencia = transferenciaService.buscaPorId(id);
		transferenciaService.removeTransferencia(transferencia);
		return "redirect:/usuario/alterar-malote/" + transferencia.getMalote().getId();
	}

	@GetMapping("/novo-malote")
	public String novoMalote(Model model) {
		Usuario usuario = usuarioService.getUsuarioById(usuarioLogado.getId());
		Malote malote = new Malote();
		malote.setEmpresa(usuario.getEmpresa());
		malote.setUsuario(usuario);
		maloteService.salvaMalote(malote);

		return "redirect:/usuario/alterar-malote/" + malote.getId();
	}

	@GetMapping("/alterar-malote/{id}")
	public String alterarMalote(Model model, @PathVariable Long id) {
		Malote malote = maloteService.getById(id);
		model.addAttribute("malote", malote);
		model.addAttribute("dataCriacao", DataUtils.dataFormatada(malote.getData()));
		List<Deposito> depositos = depositoService.getAllDepositos();
		model.addAttribute("depositos", depositos);
		return "alterarMalote";
	}

	@PostMapping("/alterar-malote/{id}")
	public String salvaMalote(HttpServletRequest req, @PathVariable Long id) {
		List<String> depositoIds = List.of(req.getParameterMap().get("deposito-id"));
		Malote malote = maloteService.getById(id);
		depositoIds.stream().map(Long::valueOf).forEach(i -> {
			Deposito deposito = depositoService.buscaPorId(i);
			deposito.setMalote(malote);
			depositoService.salvaDeposito(deposito);
		});

		return "redirect:/usuario/alterar-malote/" + id;
	}

	@GetMapping("/listar-malotes")
	public String listaMalotes(Model model, @RequestParam(required = false) String aviso) {
		List<Malote> malotes = maloteService.buscaTodos();
		model.addAttribute("malotes", malotes);
		model.addAttribute("aviso", aviso);

		return "listarMalotes";
	}

	@GetMapping("/remove-malote/{id}")
	public String removeMalotes(Model model, @PathVariable Long id, RedirectAttributes redirectAttributes) {
		Malote malote = maloteService.getById(id);
		try {
			maloteService.removeMalote(malote);
		} catch (DataIntegrityViolationException e) {
			redirectAttributes.addAttribute("aviso", "Não é possível remover malote pois existem transações associadas");
		}
		return "redirect:/usuario/listar-malotes";
	}

}
