package com.gabrielrossilopes.appmalote.controller;

import com.gabrielrossilopes.appmalote.dto.EmpresaDTO;
import com.gabrielrossilopes.appmalote.dto.UsuarioDTO;
import com.gabrielrossilopes.appmalote.model.dominio.Empresa;
import com.gabrielrossilopes.appmalote.model.dominio.Usuario;
import com.gabrielrossilopes.appmalote.service.EmpresaService;
import com.gabrielrossilopes.appmalote.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private EmpresaService empresaService;
	
	@GetMapping("/cadastrar-usuario")
	public String cadastrarUsuario(Model model) {
		model.addAttribute("empresas", empresaService.buscaTodasOrdenado());
		return "admin/cadastrarUsuario";
	}

	@PostMapping("/cadastrar-usuario")
	public String recebeUsuario(UsuarioDTO usuarioDTO, Model model) {
		try {
			Empresa empresa = empresaService.buscaPorId(usuarioDTO.getEmpresa());
			Usuario usuario = new Usuario();
			usuario.setEmpresa(empresa);
			usuario.setEmail(usuarioDTO.getEmail());
			usuario.setSenha(usuarioDTO.getSenha());
			usuario.setNome(usuarioDTO.getNome());
			usuarioService.cadastrarUsuario(usuario);
			return "redirect:/admin/listar-usuarios";
		} catch (Exception e) {
			model.addAttribute("empresas", empresaService.buscaTodasOrdenado());
			model.addAttribute("usuario", usuarioDTO);
			model.addAttribute("error", true);
			return "admin/cadastrarUsuario";
		}

	}
	
	@GetMapping("/listar-usuarios")
	public String listarUsuarios(Model model, @RequestParam(required = false) String aviso) {
		List<Usuario> usuarios = usuarioService.busucaTodos();
		model.addAttribute("usuarios", usuarios);
		model.addAttribute("aviso", aviso);
		return "admin/listarUsuarios";
	}

	@GetMapping("/editar-usuario/{id}")
	public String alteraUsuario(Model model, @PathVariable Long id) {
		Usuario usuario = usuarioService.getUsuarioById(id);
		UsuarioDTO usuarioDTO = UsuarioDTO.getUsuarioDTOdeUsuario(usuario);
		model.addAttribute("usuario", usuarioDTO);
		model.addAttribute("empresas", empresaService.buscaTodasOrdenado());
		return "admin/editarUsuario";
	}

	@PostMapping("/editar-usuario")
	public String alteraUsuario(UsuarioDTO usuarioDTO, Model model) {
		try {
			Empresa empresa = empresaService.buscaPorId(usuarioDTO.getEmpresa());
			Usuario usuario = usuarioService.getUsuarioById(usuarioDTO.getId());
			usuario.setEmpresa(empresa);
			usuario.setEmail(usuarioDTO.getEmail());
			usuario.setSenha(usuarioDTO.getSenha());
			usuario.setNome(usuarioDTO.getNome());
			usuarioService.cadastrarUsuario(usuario);
			return "redirect:/admin/listar-usuarios";
		} catch (Exception e) {
			model.addAttribute("empresas", empresaService.buscaTodasOrdenado());
			model.addAttribute("usuario", usuarioDTO);
			model.addAttribute("error", true);
			return "admin/editarUsuario";
		}

	}

	@GetMapping("/remove-usuario/{id}")
	public String excluirUsuario(@PathVariable Long id, RedirectAttributes redirectAttributes) {
		Usuario usuario = usuarioService.getUsuarioById(id);
		try {
			usuarioService.removeUsuario(usuario);
		} catch (DataIntegrityViolationException e) {
			redirectAttributes.addAttribute("aviso", "Usuário possui malotes com transações associadas");
		}
		return "redirect:/admin/listar-usuarios";
	}

	@GetMapping("/listar-solicitacoes")
	public String listarSolicitacoes(Model model) {
		List<Usuario> usuarios = usuarioService.busucaTodos();
		usuarios = usuarios.stream().filter(u -> !u.isAdmin() && !u.isAceito()).toList();
		model.addAttribute("usuarios", usuarios);
		return "admin/listarSolicitacoes";
	}

	@GetMapping("/aceitar-usuario/{id}")
	public String aceitarUsuario(@PathVariable Long id) {
		usuarioService.aceitarUsuario(id);
		return "redirect:/admin/listar-solicitacoes";
	}
	
	@GetMapping("/listar-empresas")
	public String listarEmpresas(Model model, @RequestParam(required = false) String aviso) {
		List<Empresa> empresas = empresaService.buscaTodasOrdenado();
		model.addAttribute("empresas", empresas);
		model.addAttribute("aviso", aviso);
		return "admin/listarEmpresas";
	}

	@GetMapping("/cadastrar-empresa")
	public String cadastrarEmpresa() {
		return "admin/cadastrarEmpresa";
	}

	@PostMapping("/cadastrar-empresa")
	public String processaCadastroEmpresa(EmpresaDTO empresaDTO, Model model) {
		try {
			empresaService.criaEmpresa(empresaDTO);
			return "redirect:/admin/listar-empresas";
		} catch (Exception e) {
			model.addAttribute("empresa", empresaDTO);
			model.addAttribute("error", true);
			return "admin/cadastrarEmpresa";
		}

	}

	@GetMapping("/editar-empresa/{id}")
	public String editarEmpresa(Model model, @PathVariable Long id) {
		Empresa empresa = empresaService.buscaPorId(id);
		EmpresaDTO empresaDTO = EmpresaDTO.getEmpresaDTOdeEmpresa(empresa);
		model.addAttribute("empresa", empresaDTO);
		return "admin/editarEmpresa";
	}

	@PostMapping("/editar-empresa")
	public String processaEditarEmpresa(EmpresaDTO empresaDTO, Model model) {
		try {
			empresaService.alterarEmpresa(empresaDTO);
			return "redirect:/admin/listar-empresas";
		} catch (Exception e) {
			model.addAttribute("empresa", empresaDTO);
			model.addAttribute("error", true);
			return "admin/editarEmpresa";
		}
	}

	@GetMapping("/remove-empresa/{id}")
	public String excluirEmpresa(@PathVariable Long id, RedirectAttributes redirectAttributes) {
		Empresa empresa = empresaService.buscaPorId(id);
		try {
			empresaService.removeEmpresa(empresa);
		} catch (DataIntegrityViolationException e) {
			redirectAttributes.addAttribute("aviso", "Não é possível remover empresa, pois esta possui malotes com transações associadas");
		}
		return "redirect:/admin/listar-empresas";
	}
	
}
