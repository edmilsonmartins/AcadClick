package br.edu.ifgoiano.acadclick.domain;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity
@Table(name = "tb_aluno")
@PrimaryKeyJoinColumn(name = "id_pessoa")
public class Aluno extends GenericDomain {
	@Temporal(TemporalType.DATE)
	@Column(name = "alu_dt_cadastro", updatable = false)
	private Date dataCadastro;

	@Temporal(TemporalType.DATE)
	@Column(name = "alu_dataNasc", nullable = false)
	private Date dataNascimento;

	@Column(name = "alu_nome", nullable = false)
	private String nome;

	@Enumerated(EnumType.STRING)
	@Column(name = "alu_sexo", nullable = false, length = 1)
	private Sexo sexo;

	@Transient
	private String idade;

	@Column(name = "alu_certidao_nascimento")
	private Integer certidao_nascimento;

	@Column(name = "alu_pai")
	private String pai;

	@Column(name = "alu_mae")
	private String mae;

	@Column(name = "alu_telefone", length = 14)
	private String telefone;

	@Column(name = "alu_celular", length = 14)
	private String celular;

	@Column(name = "alu_email", length = 100)
	private String email;

	@ManyToOne
	@JoinColumn(name = "codigo_grupo")
	private Grupo grupo;

	@ManyToOne
	@JoinColumn(name = "codigo_responsavel")
	private Responsavel responsavel;

	@Transient
	private String caminho;

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getIdade() {

		String d = getDataNascimento().toString();

		LocalDate dataNascimento = LocalDate.parse(d);
		LocalDate dataAtual = LocalDate.now();

		Period periodo = Period.between(dataNascimento, dataAtual);

		if (periodo.getYears() != 0) {
			if (periodo.getYears() < 10 && periodo.getYears() != 1) {
				idade = "0" + periodo.getYears() + " anos";
			}

			else if (periodo.getYears() == 1) {
				idade = "0" + periodo.getYears() + " ano";
			}

			else {
				idade = "" + periodo.getYears() + " anos";
			}
		}

		else if (periodo.getMonths() != 0) {
			if (periodo.getMonths() < 10 && periodo.getMonths() != 1) {
				idade = "0" + periodo.getMonths() + " meses";
			}

			else if (periodo.getMonths() == 1) {
				idade = "0" + periodo.getMonths() + " mês";
			}

			else {
				idade = "" + periodo.getMonths() + " meses";
			}
		}

		else if (periodo.getDays() >= 0) {
			if (periodo.getDays() < 10 && periodo.getDays() != 1) {
				idade = "0" + periodo.getDays() + " dias";
			}

			else if (periodo.getDays() == 1 || periodo.getDays() == 0) {
				idade = "0" + periodo.getDays() + " dia";
			}

			else {
				idade = "" + periodo.getDays() + " dias";
			}
		}

		return idade;
	}

	public void setIdade(String idade) {
		this.idade = idade;
	}

	public Integer getCertidao_nascimento() {
		return certidao_nascimento;
	}

	public void setCertidao_nascimento(Integer certidao_nascimento) {
		this.certidao_nascimento = certidao_nascimento;
	}

	public String getPai() {
		return pai;
	}

	public void setPai(String pai) {
		this.pai = pai;
	}

	public String getMae() {
		return mae;
	}

	public void setMae(String mae) {
		this.mae = mae;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public Responsavel getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Responsavel responsavel) {
		this.responsavel = responsavel;
	}

	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}

}
