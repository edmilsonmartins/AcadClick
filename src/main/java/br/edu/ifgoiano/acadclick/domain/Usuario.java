package br.edu.ifgoiano.acadclick.domain;

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
@Table(name = "tb_usuario")
@PrimaryKeyJoinColumn(name = "id_pessoa")
public class Usuario extends GenericDomain {
	@Temporal(TemporalType.DATE)
	@Column(name = "usu_dt_cadastro", updatable = false)
	private Date dataCadastro;

	@Temporal(TemporalType.DATE)
	@Column(name = "usu_dataNasc", nullable = false)
	private Date dataNascimento;

	@Column(name = "usu_nome", nullable = false)
	private String nome;

	@Enumerated(EnumType.STRING)
	@Column(name = "usu_sexo", nullable = false, length = 1)
	private Sexo sexo;

	@Transient
	private String idade;

	@Column(name = "usu_cpf", length = 14, unique = true)
	private String cpf;

	@Column(name = "usu_rg", length = 12)
	private String rg;

	@Column(name = "usu_certidao_nascimento")
	private Integer certidao_nascimento;

	@Column(name = "usu_pai")
	private String pai;

	@Column(name = "usu_mae")
	private String mae;

	@Column(name = "usu_telefone", length = 14)
	private String telefone;

	@Column(name = "usu_celular", length = 14)
	private String celular;

	@Column(name = "usu_email", length = 100)
	private String email;

	@Column(name = "usu_senha", length = 32, nullable = false)
	private String senha;

	@Transient
	private String senhaSemCriptografia;

	@Column(name = "usu_ativo", nullable = false)
	private Boolean ativo;

	@ManyToOne
	@JoinColumn(name = "usu_tipo")
	private TipoUsuario tipoUsuario;

	@ManyToOne
	@JoinColumn(name = "codigo_grupo")
	private Grupo grupo;

	@Transient
	private String caminho;

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
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

	public String getIdade() {
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getSenhaSemCriptografia() {
		return senhaSemCriptografia;
	}

	public void setSenhaSemCriptografia(String senhaSemCriptografia) {
		this.senhaSemCriptografia = senhaSemCriptografia;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}

}
