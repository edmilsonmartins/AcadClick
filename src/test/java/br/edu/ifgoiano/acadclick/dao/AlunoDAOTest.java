package br.edu.ifgoiano.acadclick.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.edu.ifgoiano.acadclick.domain.Aluno;
import br.edu.ifgoiano.acadclick.domain.Responsavel;
import br.edu.ifgoiano.acadclick.domain.Sexo;

public class AlunoDAOTest {
	@Test
	@Ignore
	public void salvar() throws ParseException {

		ResponsavelDAO responsavelDAO = new ResponsavelDAO();
		Responsavel responsavel = responsavelDAO.buscar(8L);

		Aluno aluno = new Aluno();
		aluno.setDataCadastro(new SimpleDateFormat("dd/MM/yyyy").parse("21/04/2016"));
		aluno.setNome("Heitor Iago Henrique Moura");
		aluno.setDataNascimento(new SimpleDateFormat("dd/MM/yyyy").parse("08/06/1994"));
		aluno.setSexo(Sexo.M);
		aluno.setCertidao_nascimento(0000);
		aluno.setPai("Moura");
		aluno.setMae("Moura");
		aluno.setTelefone("(71)2756-4239");
		aluno.setCelular("(71)8517-9456");
		aluno.setResponsavel(responsavel);

		AlunoDAO dao = new AlunoDAO();
		dao.salvar(aluno);

	}

	@Test
	@Ignore
	public void listar() {
		AlunoDAO dao = new AlunoDAO();
		List<Aluno> resultado = dao.listar();

		System.out.println("Total de Registros Encontrados: " + resultado.size());

		for (Aluno aluno : resultado) {
			System.out.println("Data do Cadastro: " + aluno.getDataCadastro() + " Nome: " + aluno.getNome()
					+ " Data de Nascimento: " + aluno.getDataNascimento());
		}
	}

	@Test
	@Ignore
	public void buscar() throws ParseException {
		Long codigo = 15L;

		AlunoDAO dao = new AlunoDAO();
		Aluno aluno = dao.buscar(codigo);

		if (aluno == null) {
			System.out.println("Nenhum registro encontrado");
		} else {
			System.out.println("Registro encontrado: ");
			System.out.println("Data do Cadastro: " + aluno.getDataCadastro() + " Nome: " + aluno.getNome()
					+ " Data de Nascimento: " + aluno.getDataNascimento());
		}

	}

	@Test
	@Ignore
	public void editar() {
		Long codigo = 2L;

		AlunoDAO dao = new AlunoDAO();
		Aluno aluno = dao.buscar(codigo);

		if (aluno == null) {
			System.out.println("Nenhum registro encontrado");
		} else {
			System.out.println("Registro editado - antes:");
			System.out.println("Data do Cadastro: " + aluno.getDataCadastro() + " Nome: " + aluno.getNome()
					+ " Data de Nascimento: " + aluno.getDataNascimento());
			System.out.println();

			aluno.setNome("Delebe");

			dao.editar(aluno);

			System.out.println("Registro editado:");
			System.out.println("Data do Cadastro: " + aluno.getDataCadastro() + " Nome: " + aluno.getNome()
					+ " Data de Nascimento: " + aluno.getDataNascimento());
		}

	}
}
