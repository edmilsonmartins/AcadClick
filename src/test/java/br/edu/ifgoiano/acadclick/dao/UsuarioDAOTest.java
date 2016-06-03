package br.edu.ifgoiano.acadclick.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Ignore;
import org.junit.Test;

import br.edu.ifgoiano.acadclick.domain.Sexo;
import br.edu.ifgoiano.acadclick.domain.TipoUsuario;
import br.edu.ifgoiano.acadclick.domain.Usuario;

public class UsuarioDAOTest {
	@Test
	@Ignore
	public void salvar() throws ParseException {

		TipoUsuario tipoUsuario = new TipoUsuario();

		TipoUsuarioDAO tipoUsuarioDAO = new TipoUsuarioDAO();
		tipoUsuario = tipoUsuarioDAO.buscar(2L);

		Usuario usuario = new Usuario();
		usuario.setDataCadastro(new SimpleDateFormat("dd/MM/yyyy").parse("19/04/2016"));
		usuario.setNome("Iago Diogo Leonardo Martins");
		usuario.setSexo(Sexo.M);
		usuario.setDataNascimento(new SimpleDateFormat("dd/MM/yyyy").parse("13/11/1994"));
		usuario.setCpf("222.222.222-11");
		usuario.setRg("33.957.371-5");
		usuario.setCertidao_nascimento(0000);
		usuario.setPai("Almeida");
		usuario.setMae("Almeida");
		usuario.setTelefone("(68)3763-9044");
		usuario.setCelular("(68)9749-6164");
		usuario.setEmail("iago-diogo90@piscinasegura.com.br");
		usuario.setTipoUsuario(tipoUsuario);
		usuario.setAtivo(true);
		usuario.setSenha("123456");

		UsuarioDAO dao = new UsuarioDAO();
		dao.salvar(usuario);

	}

	@Test
	@Ignore
	public void autenticar() {
		String cpf = "894.946.618-00";
		String senha = "123456";

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.autenticar(cpf, senha);

		System.out.println("Usuário autenticado: " + usuario);
	}

}