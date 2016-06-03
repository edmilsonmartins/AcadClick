package br.edu.ifgoiano.acadclick.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.edu.ifgoiano.acadclick.dao.UsuarioDAO;
import br.edu.ifgoiano.acadclick.domain.Usuario;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class AutenticacaoBean implements Serializable{
	private Usuario usuario;
	static Usuario usuarioLogado;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	@SuppressWarnings("static-access")
	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	@PostConstruct
	public void iniciar() {
		usuario = new Usuario();
	}

	public void autenticar() {
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioLogado = usuarioDAO.autenticar(usuario.getCpf(), usuario.getSenha());
			

			if (usuarioLogado == null) {
				Messages.addGlobalError("CPF e/ou senha incorretos");
				return;
			}
			
			usuarioLogado.setCaminho("D:/Uploads/am-user-avatar/" + usuarioLogado.getCodigo() + ".png");

			Faces.redirect("./pages/Home.xhtml");
		} catch (IOException erro) {
			erro.printStackTrace();
			Messages.addGlobalError(erro.getMessage());
		}
	}

	public boolean temPermissoes(List<String> permissoes) {

		for (String permissao : permissoes) {
			if (usuarioLogado.getGrupo().getNome().equals(permissao)) {
				return true;
			}else if (usuarioLogado.getTipoUsuario().getNome().equals(permissao)) {
				return true;
			}
		}
		return false;
	}
	
	public String logout() {		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);	
		
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("http://localhost:8080/AcadClick/pages/autenticacao.xhtml");
			session.invalidate();
		} catch (IOException e) {
			 System.out.println("Erro ao tentar redirecionar para página solicitada ao efetuar Logout: " + e.toString());
			e.printStackTrace();
		}
		
		return "logout";
    }
}