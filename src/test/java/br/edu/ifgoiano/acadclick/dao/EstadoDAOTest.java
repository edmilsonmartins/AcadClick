package br.edu.ifgoiano.acadclick.dao;

import java.text.ParseException;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.edu.ifgoiano.acadclick.domain.Estado;

public class EstadoDAOTest {
	@Test
	@Ignore
	public void salvar() throws ParseException {
		Estado estado = new Estado();

		estado.setUf("TT");
		estado.setNome("Teste");

		EstadoDAO estadoDAO = new EstadoDAO();
		estadoDAO.salvar(estado);
	}

	@Test
	
	public void listar() {

		EstadoDAO estadoDAO = new EstadoDAO();
		List<Estado> resultado = estadoDAO.listar();

		System.out.println("Total de Registros Encontrados: " + resultado.size());

		for (Estado estado : resultado) {
			System.out.println("Código: " + estado.getCodigo() + " estado: " + estado.getNome());
		}

	}

	@Test
	@Ignore
	public void buscar() throws ParseException {

	}

	@Test
	@Ignore
	public void editar() {

	}
}
