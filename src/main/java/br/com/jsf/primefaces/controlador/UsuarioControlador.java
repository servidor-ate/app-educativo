package br.com.jsf.primefaces.controlador;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import br.com.jsf.primefaces.ejb.Executor;
import br.com.jsf.primefaces.entidade.TbUsuario;
import br.com.jsf.primefaces.modelo.UsuarioModelo;
import br.com.jsf.primefaces.repositorio.UsuarioRepositorio;
import br.com.jsf.primefaces.utilidades.Utilidades;

@Named(value = "usuarioControlador")
@SessionScoped
public class UsuarioControlador implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioModelo usuarioModel;

	@Inject
	private UsuarioRepositorio usuarioRepository;

	@Inject
	private TbUsuario usuarioEntity;
	
	@Inject
	private Executor executor;

	public UsuarioModelo getUsuarioModel() {
		return usuarioModel;
	}

	public void setUsuarioModel(UsuarioModelo usuarioModel) {
		this.usuarioModel = usuarioModel;
	}

	public UsuarioModelo getUsuarioSession() {

		FacesContext facesContext = FacesContext.getCurrentInstance();

		return (UsuarioModelo) facesContext.getExternalContext()
				.getSessionMap().get("usuarioAutenticado");
	}

	public String logout() {

		FacesContext.getCurrentInstance().getExternalContext()
				.invalidateSession();

		return "/index.xhtml?faces-redirect=true";
	}

	public String efetuarLogin() {

		// Realiza uma chamada para execução de método assíncrono
		this.executor.executeAsync();
		
		if (StringUtils.isEmpty(usuarioModel.getUsuario())
				|| StringUtils.isBlank(usuarioModel.getUsuario())) {

			Utilidades.exibirMensagem("Favor informar o login!");
			return null;
		} else if (StringUtils.isEmpty(usuarioModel.getSenha())
				|| StringUtils.isBlank(usuarioModel.getSenha())) {

			Utilidades.exibirMensagem("Favor informara senha!");
			return null;
		} else {

			usuarioEntity = usuarioRepository.validarUsuario(usuarioModel);

			if (usuarioEntity != null) {

				usuarioModel.setSenha(null);
				usuarioModel.setCodigo(String.valueOf(usuarioEntity.getId()));

				FacesContext facesContext = FacesContext.getCurrentInstance();

				facesContext.getExternalContext().getSessionMap()
						.put("usuarioAutenticado", usuarioModel);

				return "sistema/home?faces-redirect=true";
			} else {

				Utilidades
						.exibirMensagem("Não foi possível efetuar o login com esse usuário e senha!");
				return null;
			}
		}

	}

}