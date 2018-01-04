package br.com.jsf.primefaces.repositorio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.Query;

import br.com.jsf.primefaces.ejb.EntityManagerProvider;
import br.com.jsf.primefaces.entidade.TbPessoa;
import br.com.jsf.primefaces.entidade.TbUsuario;
import br.com.jsf.primefaces.modelo.PessoaModelo;
import br.com.jsf.primefaces.modelo.UsuarioModelo;

public class PessoaRepositorio implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8670201605815048661L;


	private EntityManagerProvider provider;
	
	@Inject
	public PessoaRepositorio(EntityManagerProvider provider) {
		this.provider = provider;
	}

	/***
	 * MÉTODO RESPONSÁVEL POR SALVAR UMA NOVA PESSOA
	 * 
	 * @param pessoaModelo
	 */
	public void salvarNovoRegistro(PessoaModelo pessoaModelo) {

		TbPessoa tbPessoa = new TbPessoa();
		tbPessoa.setDtCadastro(new Date());
		tbPessoa.setEmail(pessoaModelo.getEmail());
		tbPessoa.setEndereco(pessoaModelo.getEndereco());
		tbPessoa.setNome(pessoaModelo.getNome());
		tbPessoa.setFlagOrigemCadastro(pessoaModelo.getOrigemCadastro());
		tbPessoa.setFlagSexo(pessoaModelo.getSexo());

		try {

			TbUsuario usuario = this.provider.getEntityManager().find(TbUsuario.class,
					Long.parseLong(pessoaModelo.getUsuarioModelo().getCodigo()));

			tbPessoa.setUsuario(usuario);

			boolean pessoaCadastrada = this.provider.registrarPessoa(tbPessoa);
			System.out.printf("%s cadastrada com sucesso? :: %b\n", tbPessoa, pessoaCadastrada);

			//this.recuperarPessoas();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public TbPessoa recuperarPessoaPorId(long id) {
		try {

			TbPessoa tbPessoa = this.provider.getEntityManager().find(TbPessoa.class, id);

			return tbPessoa;

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public void atualizarPessoa(PessoaModelo pessoaModelo) {
		try {

			TbPessoa tbPessoa = this.recuperarPessoaPorId(pessoaModelo.getCodigo());
			tbPessoa.setEmail(pessoaModelo.getEmail());
			tbPessoa.setEndereco(pessoaModelo.getEndereco());
			tbPessoa.setNome(pessoaModelo.getNome());
			tbPessoa.setFlagSexo(pessoaModelo.getSexo());

			boolean pessoaAtualizada = this.provider.atualizarPessoa(tbPessoa);
			System.out.printf("Pessoa %s atualizada com sucesso? :: %b\n", tbPessoa, pessoaAtualizada);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void removerPessoa(long id) {
		try {

			TbPessoa tbPessoa = this.recuperarPessoaPorId(id);

			boolean pessoaRemovida = this.provider.removerPessoa(tbPessoa);
			System.out.printf("Pessoa %s removida com sucesso? :: %b\n", tbPessoa, pessoaRemovida);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public List<PessoaModelo> recuperarPessoas() {

		List<PessoaModelo> pessoaLista = new ArrayList<PessoaModelo>();

		try {

			Query consulta = this.provider.getEntityManager().createNamedQuery(TbPessoa.RECUPERAR_TODOS_REGISTROS);

			List<TbPessoa> lista = (ArrayList<TbPessoa>) consulta.getResultList();
			System.out.println("Total de registros recuperados = " + lista.size());

			for (TbPessoa pessoa : lista) {
				System.out.printf("Pessoa = %s\nUsuario associado = %s\n", pessoa, pessoa.getUsuario());

				PessoaModelo pessoaModelo = new PessoaModelo();
				pessoaModelo.setCodigo(pessoa.getId());
				pessoaModelo.setDataCadastro(pessoa.getDtCadastro());
				pessoaModelo.setEmail(pessoa.getEmail());
				pessoaModelo.setEndereco(pessoa.getEndereco());
				pessoaModelo.setNome(pessoa.getNome());

				if (pessoa.getFlagOrigemCadastro().equals("X")) {
					pessoaModelo.setOrigemCadastro("XML");
				} else {
					pessoaModelo.setOrigemCadastro("INPUT");
				}

				if (pessoa.getFlagSexo().equals("M")) {
					pessoaModelo.setSexo("Masculino");
				} else {
					pessoaModelo.setSexo("Feminino");
				}

				TbUsuario usuario = pessoa.getUsuario();

				UsuarioModelo usuarioModelo = new UsuarioModelo();
				usuarioModelo.setUsuario(usuario.getUsuario());

				pessoaModelo.setUsuarioModelo(usuarioModelo);

				pessoaLista.add(pessoaModelo);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return pessoaLista;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Integer> recuperarRegistrosAgrupadoPorOrigemCadastro() {
		try {

			Query consulta = this.provider.getEntityManager()
					.createNamedQuery(TbPessoa.RECUPERAR_REGISTROS_AGRUPADOS_POR_ORIGEM_CADASTRO);

			List<Object[]> lista = (ArrayList<Object[]>) consulta.getResultList();

			Map<String, Integer> agrupamentoMapa = new HashMap<String, Integer>();

			for (Object[] retorno : lista) {

				String tipoCadastro = (String) retorno[0];

				tipoCadastro = ("X".equalsIgnoreCase(tipoCadastro)) ? "XML" : "INPUT";

				agrupamentoMapa.put(tipoCadastro, ((Long) retorno[1]).intValue());

			}

			return agrupamentoMapa;

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}

}
