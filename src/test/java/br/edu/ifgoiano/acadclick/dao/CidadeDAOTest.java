package br.edu.ifgoiano.acadclick.dao;

import java.text.ParseException;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.edu.ifgoiano.acadclick.domain.Cidade;
import br.edu.ifgoiano.acadclick.domain.Estado;

public class CidadeDAOTest {
	@Test
	@Ignore
	public void salvar() throws ParseException {
		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscar(1L);

		Cidade cidade = new Cidade();
		cidade.setEstado(estado);
		cidade.setNome("Teste");

		CidadeDAO cidadeDAO = new CidadeDAO();
		cidadeDAO.salvar(cidade);

	}

	@Test
	@Ignore
	public void listar() {
		CidadeDAO cidadeDAO = new CidadeDAO();
		List<Cidade> resultado = cidadeDAO.listar();

		System.out.println("Total de Registros Encontrados: " + resultado.size());

		for (Cidade cidade : resultado) {
			System.out.println("Código: " + cidade.getCodigo() + " Nome: " + cidade.getNome());
		}
	}

	@Test
	@Ignore
	public void buscar() throws ParseException {
		Long codigo = 0L;

		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscar(codigo);

		if (cidade == null) {
			System.out.println("Nenhum registro encontrado");
		} else {
			System.out.println("Registro encontrado: ");
			System.out.println("Código: " + cidade.getCodigo() + " Nome: " + cidade.getNome());
		}

	}

	@Test
	@Ignore
	public void editar() {
		Long codigo = 2L;

		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscar(codigo);

		if (cidade == null) {
			System.out.println("Nenhum registro encontrado");
		} else {
			System.out.println("Registro editado - antes:");
			System.out.println("Código: " + cidade.getCodigo() + " Nome: " + cidade.getNome());
			System.out.println();

			cidade.setNome("Testes");

			cidadeDAO.editar(cidade);

			System.out.println("Registro editado:");
			System.out.println("Código: " + cidade.getCodigo() + " Nome: " + cidade.getNome());
		}

	}
}
