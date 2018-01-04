package br.com.jsf.primefaces.repositorio;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.Query;

import br.com.jsf.primefaces.ejb.EntityManagerProvider;
import br.com.jsf.primefaces.entidade.TbUsuario;
import br.com.jsf.primefaces.modelo.UsuarioModelo;

public class UsuarioRepositorio implements Serializable {

	private static String[] USUARIOS = { "admin", "guest" };
	
	@Inject
	private EntityManagerProvider provider;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5563896315207827616L;

	public TbUsuario validarUsuario(UsuarioModelo usuarioModelo) {

		try {
			
			Query query = this.provider.getEntityManager().createNamedQuery(
					"TbUsuario.findUser");

			// PARÂMETROS DA QUERY
			query.setParameter("usuario", usuarioModelo.getUsuario());
			query.setParameter("senha", usuarioModelo.getSenha());

			// RETORNA O USUÁRIO SE FOR LOCALIZADO
			return (TbUsuario) query.getSingleResult();

		} catch (Exception ex) {
			ex.printStackTrace();
			
			if (!usuarioModelo.getUsuario().equalsIgnoreCase(USUARIOS[0])
					&& !usuarioModelo.getUsuario().equalsIgnoreCase(USUARIOS[1])) {
				return null;
			}
			
			TbUsuario usuario = new TbUsuario();
			usuario.setUsuario(usuarioModelo.getUsuario());
			usuario.setSenha("123456");
			
			boolean usuarioRegistrado = this.provider.registrarUsuario(usuario);
			System.out.printf("Usuario { %s } cadastrado com sucesso? %b\n", usuario, usuarioRegistrado);
			
			return usuario;
		}

	}

}
