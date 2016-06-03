package br.edu.ifgoiano.acadclick.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.edu.ifgoiano.acadclick.dao.AlunoDAO;
import br.edu.ifgoiano.acadclick.dao.GrupoDAO;
import br.edu.ifgoiano.acadclick.dao.ResponsavelDAO;
import br.edu.ifgoiano.acadclick.domain.Aluno;
import br.edu.ifgoiano.acadclick.domain.Grupo;
import br.edu.ifgoiano.acadclick.domain.Responsavel;
import br.edu.ifgoiano.acadclick.domain.Sexo;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class GGABean implements Serializable {
	private List<Aluno> alunosGGA;
	private List<Responsavel> responsaveis;
	private List<Grupo> grupos;
	private List<Aluno> meninas;
	private List<Aluno> meninos;
	private Responsavel responsavel;
	private int quantidadeGGA;
	private int quantidadeMeninos;
	private int quantidadeMeninas;

	public List<Aluno> getAlunosGGA() {
		return alunosGGA;
	}

	public void setAlunosGGA(List<Aluno> alunosGGA) {
		this.alunosGGA = alunosGGA;
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

	public List<Aluno> getMeninas() {
		return meninas;
	}

	public void setMeninas(List<Aluno> meninas) {
		this.meninas = meninas;
	}

	public List<Aluno> getMeninos() {
		return meninos;
	}

	public void setMeninos(List<Aluno> meninos) {
		this.meninos = meninos;
	}

	public Responsavel getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Responsavel responsavel) {
		this.responsavel = responsavel;
	}

	public int getQuantidadeGGA() {
		return quantidadeGGA;
	}

	public void setQuantidadeGGA(int quantidadeGGA) {
		this.quantidadeGGA = quantidadeGGA;
	}

	public int getQuantidadeMeninos() {
		return quantidadeMeninos;
	}

	public void setQuantidadeMeninos(int quantidadeMeninos) {
		this.quantidadeMeninos = quantidadeMeninos;
	}

	public int getQuantidadeMeninas() {
		return quantidadeMeninas;
	}

	public void setQuantidadeMeninas(int quantidadeMeninas) {
		this.quantidadeMeninas = quantidadeMeninas;
	}

	@PostConstruct
	public void listarGGA() {
		try {
			AlunoDAO alunoDAO = new AlunoDAO();
			alunosGGA = alunoDAO.buscarPorGrupo(4L);
			meninas = alunoDAO.buscarPorSexo(Sexo.F, 4L);
			meninos = alunoDAO.buscarPorSexo(Sexo.M, 4L);

			for (Aluno aluno : alunosGGA) {

				aluno.setCaminho("D:/Uploads/Alunos/" + aluno.getCodigo() + ".png");

			}

			ResponsavelDAO responsavelDAO = new ResponsavelDAO();
			responsaveis = responsavelDAO.listar("nome");

			GrupoDAO grupoDAO = new GrupoDAO();
			grupos = grupoDAO.listar("nome");

			quantidadeGGA = alunosGGA.size();
			quantidadeMeninas = meninas.size();
			quantidadeMeninos = meninos.size();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os alunos");
			erro.printStackTrace();
		}
	}

}
