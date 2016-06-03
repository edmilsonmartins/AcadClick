package br.edu.ifgoiano.acadclick.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.edu.ifgoiano.acadclick.domain.Cidade;
import br.edu.ifgoiano.acadclick.domain.Responsavel;
import br.edu.ifgoiano.acadclick.domain.Sexo;

public class ResponsavelDAOTest {
	@Test
	@Ignore
	public void salvar() throws ParseException {

		CidadeDAO cidadeDAO = new CidadeDAO();

		Cidade cidade = cidadeDAO.buscar(962L);

		Responsavel responsavel = new Responsavel();
		responsavel.setDataCadastro(new SimpleDateFormat("dd/MM/yyyy").parse("03/05/2016"));
		responsavel.setNome("Valentina Olivia Fernanda Moura");
		responsavel.setSexo(Sexo.F);
		responsavel.setDataNascimento(new SimpleDateFormat("dd/MM/yyyy").parse("27/02/1982"));
		responsavel.setCpf("005.303.481-36");
		responsavel.setRg("50.998.736-9");
		responsavel.setTelefone("(27)2917-0192");
		responsavel.setCelular("(27)9744-4845");
		responsavel.setEmail("valentina-olivia80@davimil.com.br");
		responsavel.setEndereco("Rua da Fiat I");
		responsavel.setNumero("849");
		responsavel.setComplemento("ap. 159");
		responsavel.setBairro("Alvorada");
		responsavel.setCep("29940-636");
		responsavel.setCidade(cidade);
		responsavel.setConheceajesus('S');
		responsavel.setPertenceoutraigreja('N');

		ResponsavelDAO dao = new ResponsavelDAO();
		dao.salvar(responsavel);

	}

	@Test
	@Ignore
	public void listar() {
		ResponsavelDAO dao = new ResponsavelDAO();
		List<Responsavel> resultado = dao.listar();

		System.out.println("Total de Registros Encontrados: " + resultado.size());

		for (Responsavel responsavel : resultado) {
			System.out.println("Data do Cadastro: " + responsavel.getDataCadastro() + " Nome: " + responsavel.getNome()
					+ " Data de Nascimento: " + responsavel.getDataNascimento());
		}
	}

	@Test
	@Ignore
	public void buscar() {
		Long codigo = 2L;

		ResponsavelDAO dao = new ResponsavelDAO();
		Responsavel responsavel = dao.buscar(codigo);

		if (responsavel == null) {
			System.out.println("Nenhum registro encontrado");
		} else {
			System.out.println("Registro encontrado: ");
			System.out.println("Data do Cadastro: " + responsavel.getDataCadastro() + " Nome: " + responsavel.getNome()
					+ " Data de Nascimento: " + responsavel.getDataNascimento());
		}

	}

	@Test
	@Ignore
	public void editar() {
		Long codigo = 1L;

		ResponsavelDAO dao = new ResponsavelDAO();
		Responsavel responsavel = dao.buscar(codigo);

		if (responsavel == null) {
			System.out.println("Nenhum registro encontrado");
		} else {
			System.out.println("Registro editado - antes:");
			System.out.println("Data do Cadastro: " + responsavel.getDataCadastro() + " Nome: " + responsavel.getNome()
					+ " Data de Nascimento: " + responsavel.getDataNascimento());
			System.out.println();

			responsavel.setNome("Helena Pinto");

			dao.editar(responsavel);

			System.out.println("Registro editado:");
			System.out.println("Data do Cadastro: " + responsavel.getDataCadastro() + " Nome: " + responsavel.getNome()
					+ " Data de Nascimento: " + responsavel.getDataNascimento());
		}

	}
	
	@Test
	@Ignore
	public void excluir() {
		Long codigo = 1L;
		ResponsavelDAO dao = new ResponsavelDAO();

		Responsavel responsavel = dao.buscar(codigo);

		if (responsavel == null) {
			System.out.println("Nenhum registro encontrado");
		} else {
			dao.excluir(responsavel);
			System.out.println("Registro removido:");
			System.out.println("Data do Cadastro: " + responsavel.getDataCadastro() + " Nome: " + responsavel.getNome()
					+ " Data de Nascimento: " + responsavel.getDataNascimento());
		}
	}

}
