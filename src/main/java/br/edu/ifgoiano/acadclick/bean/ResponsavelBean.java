package br.edu.ifgoiano.acadclick.bean;

import java.io.Serializable;
import java.sql.Connection;
import java.util.ArrayList;
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

import br.edu.ifgoiano.acadclick.dao.CidadeDAO;
import br.edu.ifgoiano.acadclick.dao.EstadoDAO;
import br.edu.ifgoiano.acadclick.dao.ResponsavelDAO;
import br.edu.ifgoiano.acadclick.domain.Aluno;
import br.edu.ifgoiano.acadclick.domain.Cidade;
import br.edu.ifgoiano.acadclick.domain.Estado;
import br.edu.ifgoiano.acadclick.domain.Responsavel;
import br.edu.ifgoiano.acadclick.util.HibernateUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ResponsavelBean implements Serializable {
	private Responsavel responsavel;
	private List<Responsavel> responsaveis;

	private Estado estado;
	private List<Estado> estados;

	private List<Cidade> cidades;

	private List<Aluno> familia;

	public Responsavel getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Responsavel responsavel) {
		this.responsavel = responsavel;
	}

	public List<Responsavel> getResponsaveis() {
		return responsaveis;
	}

	public void setResponsaveis(List<Responsavel> responsaveis) {
		this.responsaveis = responsaveis;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public List<Aluno> getFamilia() {
		return familia;
	}

	public void setFamilia(List<Aluno> familia) {
		this.familia = familia;
	}

	@PostConstruct
	public void listar() {
		try {
			ResponsavelDAO responsavelDAO = new ResponsavelDAO();
			responsaveis = responsavelDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar as pessoas");
			erro.printStackTrace();
		}
	}

	public void novo() {
		try {
			responsavel = new Responsavel();

			estado = new Estado();

			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.listar("nome");

			cidades = new ArrayList<Cidade>();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar gerar uma nova pessoa");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			responsavel = (Responsavel) evento.getComponent().getAttributes().get("responsavelSelecionado");

			estado = responsavel.getCidade().getEstado();

			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.listar("nome");

			CidadeDAO cidadeDAO = new CidadeDAO();
			cidades = cidadeDAO.buscarPorEstado(estado.getCodigo());
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar selecionar uma pessoa");
		}
	}

	public void salvar() {
		try {
			ResponsavelDAO responsavelDAO = new ResponsavelDAO();
			responsavelDAO.merge(responsavel);

			responsaveis = responsavelDAO.listar("nome");

			responsavel = new Responsavel();

			estado = new Estado();

			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.listar("nome");

			cidades = new ArrayList<>();

			listar();

			Messages.addGlobalInfo("Pessoa salva com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar a pessoa");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			responsavel = (Responsavel) evento.getComponent().getAttributes().get("responsavelSelecionado");

			ResponsavelDAO responsavelDAO = new ResponsavelDAO();
			responsavelDAO.excluir(responsavel);

			listar();

			Messages.addGlobalInfo("Responsável removido com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar remover o responsável");
			erro.printStackTrace();
		}
	}

	public void popular() {
		try {
			if (estado != null) {
				CidadeDAO cidadeDAO = new CidadeDAO();
				cidades = cidadeDAO.buscarPorEstado(estado.getCodigo());
			} else {
				cidades = new ArrayList<>();
			}
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar filtrar as cidades");
			erro.printStackTrace();
		}
	}

	public void listarFamilia(ActionEvent evento) {

		try {
			responsavel = (Responsavel) evento.getComponent().getAttributes().get("responsavelSelecionado");

			ResponsavelDAO responsavelDAO = new ResponsavelDAO();
			familia = responsavelDAO.buscarPorResponsavel(responsavel);

			estado = responsavel.getCidade().getEstado();

			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.listar("nome");

			CidadeDAO cidadeDAO = new CidadeDAO();
			cidades = cidadeDAO.buscarPorEstado(estado.getCodigo());
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar filtrar as cidades");
			erro.printStackTrace();
		}

	}

	public void imprimir() {
		try {
			DataTable tabela = (DataTable) Faces.getViewRoot().findComponent("formListagem:tabela");
			Map<String, Object> filtros = tabela.getFilters();

			String resNome = (String) filtros.get("nome");

			String caminho = Faces.getRealPath("/reports/responsaveis.jasper");

			Map<String, Object> parametros = new HashMap<>();
			if (resNome == null) {
				parametros.put("RESPONSAVEL_NOME", "%%");
			} else {
				parametros.put("RESPONSAVEL_NOME", "%" + resNome + "%");
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