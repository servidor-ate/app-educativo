package br.com.jsf.primefaces.thread;

import javax.inject.Inject;

import br.com.jsf.primefaces.modelo.PessoaModelo;
import br.com.jsf.primefaces.repositorio.PessoaRepositorio;

/**
 * Executa operação de maneria assíncrona
 * 
 * @author Felipe
 *
 */
public class Task implements Runnable {

	@Inject
	transient private PessoaRepositorio pessoaRepositorio;

	@Override
	public void run() {
		long inicio = System.currentTimeMillis();
		System.out.println("Task.run() -> Inicio");
		try {

			for (PessoaModelo pessoaModelo : pessoaRepositorio.recuperarPessoas()) {
				System.out.println(pessoaModelo);
			}

		} catch (Exception ex) {
			System.out.println("Task.run() -> Erro: " + ex.getMessage());
			ex.printStackTrace();
		}
		System.out.println("Task.run() -> Fim [ " + (System.currentTimeMillis() - inicio) + " ] (ms)");
	}

}
