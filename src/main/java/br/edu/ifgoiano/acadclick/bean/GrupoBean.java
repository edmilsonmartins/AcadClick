package br.edu.ifgoiano.acadclick.bean;

import java.io.Serializable;
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

import br.edu.ifgoiano.acadclick.dao.GrupoDAO;
import br.edu.ifgoiano.acadclick.domain.Grupo;
import br.edu.ifgoiano.acadclick.util.HibernateUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class GrupoBean implements Serializable {
	private Grupo grupo;
	private List<Grupo> grupos;

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public List<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}

	@PostConstruct
	public void listar() {
		try {
			GrupoDAO grupoDAO = new GrupoDAO();
			grupos = grupoDAO.listar();
		} catch (Exception erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os grupos");
			erro.printStackTrace();
		}
	}

	public void novo() {
		grupo = new Grupo();
	}

	public void salvar() {
		try {
			GrupoDAO grupoDAO = new GrupoDAO();
			grupoDAO.merge(grupo);

			novo();

			listar();

			Messages.addGlobalInfo("Grupo salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o grupo");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			grupo = (Grupo) evento.getComponent().getAttributes().get("grupoSelecionado");

			GrupoDAO grupoDAO = new GrupoDAO();
			grupoDAO.excluir(grupo);

			listar();

			Messages.addGlobalInfo("Grupo removido com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar remover o grupo");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			grupo = (Grupo) evento.getComponent().getAttributes().get("grupoSelecionado");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar carregar os dados");
			erro.printStackTrace();
		}

	}
	
	public void imprimir() {
		try {
			DataTable tabela = (DataTable) Faces.getViewRoot().findComponent("formListagem:tabela");
			Map<String, Object> filtros = tabela.getFilters();

			
			String gruNome = (String) filtros.get("nome");
			
			String caminho = Faces.getRealPath("/reports/grupos.jasper");

			Map<String, Object> parametros = new HashMap<>();
			if (gruNome == null) {
				parametros.put("GRUPO_NOME", "%%");
			} else {
				parametros.put("GRUPO_NOME", "%" + gruNome + "%");
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
