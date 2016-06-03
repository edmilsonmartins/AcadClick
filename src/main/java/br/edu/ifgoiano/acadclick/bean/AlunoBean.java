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

import br.edu.ifgoiano.acadclick.dao.AlunoDAO;
import br.edu.ifgoiano.acadclick.dao.GrupoDAO;
import br.edu.ifgoiano.acadclick.dao.ResponsavelDAO;
import br.edu.ifgoiano.acadclick.domain.Aluno;
import br.edu.ifgoiano.acadclick.domain.Grupo;
import br.edu.ifgoiano.acadclick.domain.Responsavel;
import br.edu.ifgoiano.acadclick.util.HibernateUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class AlunoBean implements Serializable {
	private Aluno aluno;

	private Aluno alunoEdicao = new Aluno();
	private List<Aluno> alunos;
	private List<Responsavel> responsaveis;
	private List<Grupo> grupos;
	private Responsavel responsavel;
	private int quantidadeAlunos;

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Aluno getAlunoEdicao() {
		return alunoEdicao;
	}

	public void setAlunoEdicao(Aluno alunoEdicao) {
		this.alunoEdicao = alunoEdicao;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public List<Responsavel> getResponsaveis() {
		return responsaveis;
	}

	public void setResponsaveis(List<Responsavel> responsaveis) {
		this.responsaveis = responsaveis;
	}

	public List<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}

	public Responsavel getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Responsavel responsavel) {
		this.responsavel = responsavel;
	}

	public int getQuantidadeAlunos() {
		return quantidadeAlunos;
	}

	public void setQuantidadeAlunos(int quantidadeAlunos) {
		this.quantidadeAlunos = quantidadeAlunos;
	}

	@PostConstruct
	public void listar() {
		try {
			AlunoDAO alunoDAO = new AlunoDAO();
			
			if (AutenticacaoBean.usuarioLogado.getGrupo().getNome().equals("GGI")) {
				
				alunos = alunoDAO.buscarPorGrupo(2L);

				ResponsavelDAO responsavelDAO = new ResponsavelDAO();
				responsaveis = responsavelDAO.listar("nome");

				GrupoDAO grupoDAO = new GrupoDAO();
				grupos = grupoDAO.listar("nome");

				quantidadeAlunos = alunos.size();
				
			}else if (AutenticacaoBean.usuarioLogado.getGrupo().getNome().equals("GGJ")) {
				
				alunos = alunoDAO.buscarPorGrupo(3L);

				ResponsavelDAO responsavelDAO = new ResponsavelDAO();
				responsaveis = responsavelDAO.listar("nome");

				GrupoDAO grupoDAO = new GrupoDAO();
				grupos = grupoDAO.listar("nome");

				quantidadeAlunos = alunos.size();	
							
			}else if (AutenticacaoBean.usuarioLogado.getGrupo().getNome().equals("GGA")) {
				
				alunos = alunoDAO.buscarPorGrupo(4L);

				ResponsavelDAO responsavelDAO = new ResponsavelDAO();
				responsaveis = responsavelDAO.listar("nome");

				GrupoDAO grupoDAO = new GrupoDAO();
				grupos = grupoDAO.listar("nome");

				quantidadeAlunos = alunos.size();	
							
			}else if (AutenticacaoBean.usuarioLogado.getGrupo().getNome().equals("GGF")) {
				
				alunos = alunoDAO.buscarPorGrupo(5L);

				ResponsavelDAO responsavelDAO = new ResponsavelDAO();
				responsaveis = responsavelDAO.listar("nome");

				GrupoDAO grupoDAO = new GrupoDAO();
				grupos = grupoDAO.listar("nome");

				quantidadeAlunos = alunos.size();	
							
			}else {
				
				alunos = alunoDAO.listar();

				ResponsavelDAO responsavelDAO = new ResponsavelDAO();
				responsaveis = responsavelDAO.listar("nome");

				GrupoDAO grupoDAO = new GrupoDAO();
				grupos = grupoDAO.listar("nome");

				quantidadeAlunos = alunos.size();
			}
			
			List<Aluno> alunosQuantidade = alunoDAO.listar();
			quantidadeAlunos = alunosQuantidade.size();
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os alunos");
			erro.printStackTrace();
		}
	}

	public void novo() {
		try {
			aluno = new Aluno();

			ResponsavelDAO responsavelDAO = new ResponsavelDAO();
			responsaveis = responsavelDAO.listar("nome");

			GrupoDAO grupoDAO = new GrupoDAO();
			grupos = grupoDAO.listar("nome");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar gerar um novo aluno");
		}
	}

	public void salvar() {

		try {
			if (aluno.getCaminho() == null) {
				Messages.addGlobalError("O campo 'foto' é obrigatório");
				return;
			}

			AlunoDAO alunoDAO = new AlunoDAO();
			Aluno alunoRetorno = alunoDAO.merge(aluno);

			Path origem = Paths.get(aluno.getCaminho());
			Path destino = Paths.get("D:/Uploads/Alunos/" + alunoRetorno.getCodigo() + ".png");
			Files.copy(origem, destino, StandardCopyOption.REPLACE_EXISTING);

			aluno = new Aluno();

			ResponsavelDAO responsavelDAO = new ResponsavelDAO();
			responsaveis = responsavelDAO.listar("nome");

			GrupoDAO grupoDAO = new GrupoDAO();
			grupos = grupoDAO.listar("nome");

			listar();

			Messages.addGlobalInfo("Aluno salvo com sucesso");
		} catch (RuntimeException | IOException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o aluno");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			aluno = (Aluno) evento.getComponent().getAttributes().get("alunoSelecionado");

			AlunoDAO alunoDAO = new AlunoDAO();
			alunoDAO.excluir(aluno);

			Path arquivo = Paths.get("D:/Uploads/Alunos/" + aluno.getCodigo() + ".png");
			Files.deleteIfExists(arquivo);

			listar();

			Messages.addGlobalInfo("Aluno removido com sucesso");
		} catch (RuntimeException | IOException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar remover o aluno");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			aluno = (Aluno) evento.getComponent().getAttributes().get("alunoSelecionado");
			aluno.setCaminho("D:/Uploads/Alunos/" + aluno.getCodigo() + ".png");

			ResponsavelDAO responsavelDAO = new ResponsavelDAO();
			responsaveis = responsavelDAO.listar("nome");

			GrupoDAO grupoDAO = new GrupoDAO();
			grupos = grupoDAO.listar("nome");

			listar();

			Messages.addGlobalInfo("Aluno salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar carregar os dados");
			erro.printStackTrace();
		}

	}

	public void upload(FileUploadEvent evento) {
		try {
			UploadedFile arquivoUpload = evento.getFile();
			Path arquivoTemp = Files.createTempFile(null, null);
			Files.copy(arquivoUpload.getInputstream(), arquivoTemp, StandardCopyOption.REPLACE_EXISTING);
			aluno.setCaminho(arquivoTemp.toString());

		} catch (IOException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar realizar o upload de arquivo");
			erro.printStackTrace();
		}
	}

	public void imprimir() {
		try {
			DataTable tabela = (DataTable) Faces.getViewRoot().findComponent("formListagem:tabela");
			Map<String, Object> filtros = tabela.getFilters();


			String gruNome;
			String aluNome = (String) filtros.get("nome");
			
			if (AutenticacaoBean.usuarioLogado.getGrupo().getNome().equals("GGG")) {
				gruNome = (String) filtros.get("grupo.nome");
			} else {
				gruNome = AutenticacaoBean.usuarioLogado.getGrupo().getNome();
			}

			String caminho = Faces.getRealPath("/reports/alunos.jasper");

			Map<String, Object> parametros = new HashMap<>();
			if (aluNome == null) {
				parametros.put("ALUNO_NOME", "%%");
			} else {
				parametros.put("ALUNO_NOME", "%" + aluNome + "%");
			}
			
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
