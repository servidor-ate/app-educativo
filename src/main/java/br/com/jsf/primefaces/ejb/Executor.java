package br.com.jsf.primefaces.ejb;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import br.com.jsf.primefaces.thread.Task;

/**
 * Invoca uma operação para ser processada de maneira assíncrona
 * 
 * @author Felipe
 *
 */
@Stateless
public class Executor {

	@Resource
	private ManagedExecutorService managedExecutorService;

	@Inject
	private Instance<Task> instanceTask;

	public void executeAsync() {
		long inicio = System.currentTimeMillis();
		System.out.println("Executor.executeAsync() -> INICIO");
		try {

			Task task = this.instanceTask.get();
			this.managedExecutorService.submit(task);

		} catch (Exception ex) {
			System.out.println("Executor.executeAsync() -> " + ex.getMessage());
			ex.printStackTrace();
		}
		System.out.println("Executor.executeAsync() - FIM [ " + (System.currentTimeMillis() - inicio) + " ] (ms)");
	}

}
