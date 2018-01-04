package br.com.jsf.primefaces.modelo;

import java.io.Serializable;
import java.util.Date;

public class PessoaModelo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 23379134586580787L;

	private long codigo;
	private String nome;
	private String sexo;
	private Date dataCadastro;
	private String email;
	private String endereco;
	private String origemCadastro;
	private UsuarioModelo usuarioModelo;

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getOrigemCadastro() {
		return origemCadastro;
	}

	public void setOrigemCadastro(String origemCadastro) {
		this.origemCadastro = origemCadastro;
	}

	public UsuarioModelo getUsuarioModelo() {
		return usuarioModelo;
	}

	public void setUsuarioModelo(UsuarioModelo usuarioModelo) {
		this.usuarioModelo = usuarioModelo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PessoaModelo [codigo=");
		builder.append(codigo);
		builder.append(", nome=");
		builder.append(nome);
		builder.append(", sexo=");
		builder.append(sexo);
		builder.append(", dataCadastro=");
		builder.append(dataCadastro);
		builder.append(", email=");
		builder.append(email);
		builder.append(", endereco=");
		builder.append(endereco);
		builder.append(", origemCadastro=");
		builder.append(origemCadastro);
		builder.append("]");
		return builder.toString();
	}

	public PessoaModelo() {
		super();
	}

	public PessoaModelo(long codigo, String nome, String sexo,
			Date dataCadastro, String email, String endereco,
			String origemCadastro, UsuarioModelo usuarioModelo) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.sexo = sexo;
		this.dataCadastro = dataCadastro;
		this.email = email;
		this.endereco = endereco;
		this.origemCadastro = origemCadastro;
		this.usuarioModelo = usuarioModelo;
	}

}
