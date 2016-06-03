package br.edu.ifgoiano.acadclick.bean;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.edu.ifgoiano.acadclick.dao.GrupoDAO;
import br.edu.ifgoiano.acadclick.dao.TipoUsuarioDAO;
import br.edu.ifgoiano.acadclick.dao.UsuarioDAO;
import br.edu.ifgoiano.acadclick.domain.Grupo;
import br.edu.ifgoiano.acadclick.domain.TipoUsuario;
import br.edu.ifgoiano.acadclick.domain.Usuario;
import br.edu.ifgoiano.acadclick.util.HibernateUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class UsuarioBean implements Serializable {

	private Usuario usuario;
	private List<Usuario> usuarios;
	private List<Grupo> grupos;

	private List<TipoUsuario> tipos;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}

	public List<TipoUsuario> getTipos() {
		return tipos;
	}

	public void setTipos(List<TipoUsuario> tipos) {
		this.tipos = tipos;
	}

	@PostConstruct
	public void listar() {
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarios = usuarioDAO.listar();
			
			GrupoDAO grupoDAO = new GrupoDAO();
			grupos = grupoDAO.listar("nome");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os usuários");
			erro.printStackTrace();
		}
	}

	public void novo() {
		try {
			usuario = new Usuario();

			TipoUsuarioDAO tipoUsuarioDAO = new TipoUsuarioDAO();
			tipos = tipoUsuarioDAO.listar("nome");
			
			GrupoDAO grupoDAO = new GrupoDAO();
			grupos = grupoDAO.listar("nome");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar gerar um novo usuário");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");

			usuario.setCaminho("D:/Uploads/Usuarios/" + usuario.getCodigo() + ".png");

			TipoUsuarioDAO tipoUsuarioDAO = new TipoUsuarioDAO();
			tipos = tipoUsuarioDAO.listar("nome");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar selecionar um usuário");
		}
	}

	public void salvar() {
		try {

			if (AutenticacaoBean.usuarioLogado.getTipoUsuario().getNome().equals("COORDENADOR")) {
				if (usuario.getTipoUsuario().getNome().equals("PASTOR")) {
					Messages.addGlobalError("Usuário não possui perfil para manter um usuário 'Pastor'");
					return;
				}
			}

			UsuarioDAO usuarioDAO = new UsuarioDAO();
			Usuario usuarioRetorno = usuarioDAO.merge(usuario);

			Path origem = Paths.get(usuario.getCaminho());
			Path destino = Paths.get("D:/Uploads/Usuarios/" + usuarioRetorno.getCodigo() + ".png");
			Files.copy(origem, destino, StandardCopyOption.REPLACE_EXISTING);

			usuarios = usuarioDAO.listar("nome");

			usuario = new Usuario();

			TipoUsuarioDAO tipoUsuarioDAO = new TipoUsuarioDAO();
			tipos = tipoUsuarioDAO.listar("nome");
			
			GrupoDAO grupoDAO = new GrupoDAO();
			grupos = grupoDAO.listar("nome");

			Messages.addGlobalInfo("Usuário salvo com sucesso");
		} catch (RuntimeException | IOException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o usuário");
			erro.printStackTrace();
		}
	}

	public void upload(FileUploadEvent evento) {
		try {
			UploadedFile arquivoUpload = evento.getFile();
			Path arquivoTemp = Files.createTempFile(null, null);
			Files.copy(arquivoUpload.getInputstream(), arquivoTemp, StandardCopyOption.REPLACE_EXISTING);
			usuario.setCaminho(arquivoTemp.toString());

		} catch (IOException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar realizar o upload de arquivo");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");

			if (AutenticacaoBean.usuarioLogado.getTipoUsuario().getNome().equals("COORDENADOR")) {
				if (usuario.getTipoUsuario().getNome().equals("PASTOR")) {
					Messages.addGlobalError("Usuário não possui perfil para manter um usuário 'Pastor'");
					return;
				}
			}

			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioDAO.excluir(usuario);

			Path arquivo = Paths.get("D:/Uploads/Alunos/" + usuario.getCodigo() + ".png");
			Files.deleteIfExists(arquivo);

			listar();

			Messages.addGlobalInfo("Usuário removido com sucesso");
		} catch (RuntimeException | IOException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar remover o usuário");
			erro.printStackTrace();
		}
	}

	public void imprimir() {
		try {
			DataTable tabela = (DataTable) Faces.getViewRoot().findComponent("formListagem:tabela");
			Map<String, Object> filtros = tabela.getFilters();

			String usuNome = (String) filtros.get("nome");

			String caminho = Faces.getRealPath("/reports/usuarios.jasper");

			Map<String, Object> parametros = new HashMap<>();
			if (usuNome == null) {
				parametros.put("USUARIO_NOME", "%%");
			} else {
				parametros.put("USUARIO_NOME", "%" + usuNome + "%");
			}

			Connection conexao = HibernateUtil.getConexao();

			JasperPrint relatorio = JasperFillManager.fillReport(caminho, parametros, conexao);

			JasperPrintManager.printReport(relatorio, true);
		} catch (JRException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar gerar o relatório");
			erro.printStackTrace();
		}
	}

}