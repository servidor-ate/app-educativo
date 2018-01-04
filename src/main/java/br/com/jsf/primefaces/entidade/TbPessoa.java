package br.com.jsf.primefaces.entidade;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries({
		@NamedQuery(name = TbPessoa.RECUPERAR_TODOS_REGISTROS, query = "SELECT p FROM TbPessoa p JOIN p.usuario ORDER BY p.nome ASC"),
		@NamedQuery(name = TbPessoa.RECUPERAR_REGISTROS_AGRUPADOS_POR_ORIGEM_CADASTRO, query = "SELECT p.flagOrigemCadastro, count(p) as total FROM TbPessoa p GROUP BY p.flagOrigemCadastro ORDER BY p.flagOrigemCadastro") })
public class TbPessoa implements Serializable {

	public static final String RECUPERAR_TODOS_REGISTROS = "TbPessoa.recuperarTodos";
	public static final String RECUPERAR_REGISTROS_AGRUPADOS_POR_ORIGEM_CADASTRO = "TbPessoa.recuperarRegistrosPorOrigemCadastro";

	/**
	 * 
	 */
	private static final long serialVersionUID = 7802183235628503340L;

	@Id
	@GeneratedValue
	private long id;

	@Column(name = "NOME")
	private String nome;

	@Column(name = "GERNERO")
	private String flagSexo;

	@Column(name = "DT_CADASTRO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtCadastro;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "ENDERECO")
	private String endereco;

	@Column(name = "ORIGEM_CADASTRO")
	private String flagOrigemCadastro;

	@OneToOne
	private TbUsuario usuario;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFlagSexo() {
		return flagSexo;
	}

	public void setFlagSexo(String flagSexo) {
		this.flagSexo = flagSexo;
	}

	public Date getDtCadastro() {
		return dtCadastro;
	}

	public void setDtCadastro(Date dtCadastro) {
		this.dtCadastro = dtCadastro;
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

	public String getFlagOrigemCadastro() {
		return flagOrigemCadastro;
	}

	public void setFlagOrigemCadastro(String flagOrigemCadastro) {
		this.flagOrigemCadastro = flagOrigemCadastro;
	}

	public TbUsuario getUsuario() {
		return usuario;
	}

	public void setUsuario(TbUsuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TbPessoa other = (TbPessoa) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TbPessoa [id=");
		builder.append(id);
		builder.append(", nome=");
		builder.append(nome);
		builder.append(", flagSexo=");
		builder.append(flagSexo);
		builder.append(", dtCadastro=");
		builder.append(dtCadastro);
		builder.append(", email=");
		builder.append(email);
		builder.append(", endereco=");
		builder.append(endereco);
		builder.append(", flagOrigemCadastro=");
		builder.append(flagOrigemCadastro);
		builder.append("]");
		return builder.toString();
	}

	public TbPessoa(String nome, String flagSexo, Date dtCadastro, String email, String endereco,
			String flagOrigemCadastro) {
		this.nome = nome;
		this.flagSexo = flagSexo;
		this.dtCadastro = dtCadastro;
		this.email = email;
		this.endereco = endereco;
		this.flagOrigemCadastro = flagOrigemCadastro;
	}

	public TbPessoa() {
		super();
	}
	
	
}
