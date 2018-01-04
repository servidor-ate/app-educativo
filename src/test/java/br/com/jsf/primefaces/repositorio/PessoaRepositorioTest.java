package br.com.jsf.primefaces.repositorio;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.jsf.primefaces.ejb.EntityManagerProvider;
import br.com.jsf.primefaces.entidade.TbPessoa;
import br.com.jsf.primefaces.entidade.TbUsuario;
import br.com.jsf.primefaces.modelo.PessoaModelo;
import br.com.jsf.primefaces.modelo.UsuarioModelo;

@RunWith(MockitoJUnitRunner.class)
public class PessoaRepositorioTest {

	@Mock
	private EntityManagerProvider entityManagerProvider;

	@InjectMocks
	private PessoaRepositorio pessoaRepositorio;

	private static TbPessoa tbPessoa;

	private static PessoaModelo pessoaModelo;

	private static UsuarioModelo usuarioModelo;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		tbPessoa = new TbPessoa();
		tbPessoa.setDtCadastro(new Date());
		tbPessoa.setEmail("email.mock@domain.com");
		tbPessoa.setEndereco("campo endereco com numero");
		tbPessoa.setNome("nome");
		tbPessoa.setFlagOrigemCadastro("INPUT");
		tbPessoa.setFlagSexo("M");

		usuarioModelo = new UsuarioModelo("junit", "junit");
		usuarioModelo.setCodigo("1");

		pessoaModelo = new PessoaModelo(0l, "nome", "M", new Date(), "email@domain.com", "rua endereco numero", "INPUT",
				usuarioModelo);
	}

	@Test
	public void testSalvarNovoRegistro() {
		when(entityManagerProvider.getEntityManager()).thenReturn(Mockito.mock(EntityManager.class));
		when(entityManagerProvider.getEntityManager().find(Mockito.any(), Mockito.any())).thenReturn(new TbUsuario());
		when(entityManagerProvider.registrarPessoa(Mockito.any(TbPessoa.class))).thenReturn(true);

		pessoaRepositorio = new PessoaRepositorio(entityManagerProvider);
		pessoaRepositorio.salvarNovoRegistro(pessoaModelo);
	}

	@Test
	public void testRecuperarPessoaPorId() {
		long id = 10l;
		tbPessoa.setId(10l);

		when(entityManagerProvider.getEntityManager()).thenReturn(Mockito.mock(EntityManager.class));
		when(entityManagerProvider.getEntityManager().find(Mockito.any(), Mockito.any())).thenReturn(tbPessoa);

		TbPessoa retorno = pessoaRepositorio.recuperarPessoaPorId(id);

		Assert.assertNotNull(retorno);
		Assert.assertTrue(retorno.equals(tbPessoa));
	}

	@Test
	public void testAtualizarPessoa() {
		when(entityManagerProvider.getEntityManager()).thenReturn(Mockito.mock(EntityManager.class));
		when(entityManagerProvider.getEntityManager().find(Mockito.any(), Mockito.any())).thenReturn(tbPessoa);
		when(entityManagerProvider.atualizarPessoa(Mockito.any())).thenReturn(true);

		pessoaRepositorio.atualizarPessoa(pessoaModelo);
	}

	@Test
	public void testRemoverPessoa() {
		when(entityManagerProvider.getEntityManager()).thenReturn(Mockito.mock(EntityManager.class));
		when(entityManagerProvider.getEntityManager().find(Mockito.any(), Mockito.any())).thenReturn(tbPessoa);
		when(entityManagerProvider.removerPessoa(Mockito.any())).thenReturn(true);

		pessoaRepositorio.removerPessoa(1l);
	}

	@Test
	public void testRecuperarPessoas() {
		tbPessoa.setUsuario(new TbUsuario());
		tbPessoa.getUsuario().setId(1l);

		List<TbPessoa> lista = new ArrayList<TbPessoa>();
		lista.add(tbPessoa);

		when(entityManagerProvider.getEntityManager()).thenReturn(Mockito.mock(EntityManager.class));
		when(entityManagerProvider.getEntityManager().createNamedQuery(Mockito.any()))
				.thenReturn(Mockito.mock(Query.class));
		when(entityManagerProvider.getEntityManager().createNamedQuery(Mockito.any()).getResultList())
				.thenReturn(lista);

		List<PessoaModelo> retornoLista = pessoaRepositorio.recuperarPessoas();

		Assert.assertNotNull(retornoLista);
		Assert.assertTrue(retornoLista.size() > 0 && retornoLista.size() == 1);
	}

	@Test
	public void testRecuperarRegistrosAgrupadoPorOrigemCadastro() {
		when(entityManagerProvider.getEntityManager()).thenReturn(Mockito.mock(EntityManager.class));
		when(entityManagerProvider.getEntityManager().createNamedQuery(Mockito.any()))
				.thenReturn(Mockito.mock(Query.class));
		when(entityManagerProvider.getEntityManager().createNamedQuery(Mockito.any()).getResultList())
				.thenReturn(new ArrayList<Object[]>());
		
		Map<String, Integer> retornoMapa = pessoaRepositorio.recuperarRegistrosAgrupadoPorOrigemCadastro(); 
		
		Assert.assertNotNull(retornoMapa);
		Assert.assertTrue(retornoMapa.size() <= 0);
	}

}
