package br.edu.ifgoiano.acadclick.domain;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity
@Table(name = "tb_responsavel")
@PrimaryKeyJoinColumn(name = "id_pessoa")
public class Responsavel extends GenericDomain {
	@Temporal(TemporalType.DATE)
	@Column(name = "res_dt_cadastro", updatable = false)
	private Date dataCadastro;

	@Temporal(TemporalType.DATE)
	@Column(name = "res_dataNasc", nullable = false)
	private Date dataNascimento;

	@Column(name = "res_nome", nullable = false)
	private String nome;

	@Enumerated(EnumType.STRING)
	@Column(name = "res_sexo", nullable = false, length = 1)
	private Sexo sexo;

	@Transient
	private String idade;

	@Column(name = "res_cpf", length = 14)
	private String cpf;

	@Column(name = "res_rg", length = 12)
	private String rg;

	@Column(name = "res_telefone", length = 14)
	private String telefone;

	@Column(name = "res_celular", length = 14)
	private String celular;

	@Column(name = "res_email", length = 100)
	private String email;

	@Column(name = "res_conheceajesus", nullable = false)
	private Character conheceajesus;

	@Column(name = "res_pertenceoutraigreja", nullable = false)
	private Character pertenceoutraigreja;

	@Column(name = "res_endereco", length = 100)
	private String endereco;

	@Column(name = "res_numero")
	private String numero;

	@Column(name = "res_complemento", length = 10)
	private String complemento;

	@Column(name = "res_bairro", length = 30)
	private String bairro;

	@Column(name = "res_cep", length = 10)
	private String cep;

	@ManyToOne
	@JoinColumn(name = "res_cidade", nullable = false)
	private Cidade cidade;

	@OneToMany(mappedBy = "responsavel")
	private List<Aluno> alunos;

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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
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

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public Character getConheceajesus() {
		return conheceajesus;
	}

	public void setConheceajesus(Character conheceajesus) {
		this.conheceajesus = conheceajesus;
	}

	public Character getPertenceoutraigreja() {
		return pertenceoutraigreja;
	}

	public void setPertenceoutraigreja(Character pertenceoutraigreja) {
		this.pertenceoutraigreja = pertenceoutraigreja;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

}
