package br.com.jsf.primefaces.controlador;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.UploadedFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import br.com.jsf.primefaces.modelo.PessoaModelo;
import br.com.jsf.primefaces.repositorio.PessoaRepositorio;
import br.com.jsf.primefaces.utilidades.Utilidades;

@Named(value = "pessoaControlador")
@RequestScoped
public class PessoaControlador implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private PessoaModelo pessoaModelo;

	@Inject
	private UsuarioControlador usuarioControlador;

	@Inject
	private PessoaRepositorio pessoaRepositorio;

	private UploadedFile file;
	
	private int indice = 0;
	
	public int getIndice() {
		return indice;
	}

	public void setIndice(int indice) {
		this.indice = indice;
	}

	public PessoaModelo getPessoaModelo() {
		return pessoaModelo;
	}

	public void setPessoaModelo(PessoaModelo pessoaModelo) {
		this.pessoaModelo = pessoaModelo;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	/**
	 * SALVA UM NOVO REGISTRO VIA INPUT
	 */
	public void salvarNovaPessoa() {

		this.indice = 0;
		
		pessoaModelo.setUsuarioModelo(this.usuarioControlador.getUsuarioSession());

		// INFORMANDO QUE O CADASTRO FOI VIA INPUT
		pessoaModelo.setOrigemCadastro("I");

		try {

			pessoaRepositorio.salvarNovoRegistro(this.pessoaModelo);

			this.pessoaModelo = null;

			Utilidades.exibirMensagemInfo("Registro cadastrado com sucesso");

		} catch (Exception ex) {
			ex.printStackTrace();
			Utilidades.exibirMensagemAtencao("Não foi possível concluir o cadastro: " + ex.getMessage());
		}
	}

	/**
	 * REALIZANDO UPLOAD DE ARQUIVO
	 */
	public void carregarRegistros() {
		
		this.indice = 1;
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		try {

			if ("".equals(this.file.getFileName())) {
				Utilidades.exibirMensagemAtencao("Nenhum arquivo selecionado!");
				return;
			}

			DocumentBuilder builder = factory.newDocumentBuilder();

			Document doc = builder.parse(this.file.getInputstream());

			Element element = doc.getDocumentElement();

			NodeList nodes = element.getChildNodes();

			for (int i = 0; i < nodes.getLength(); i++) {

				Node node = nodes.item(i);

				if (node.getNodeType() == Node.ELEMENT_NODE) {

					Element elementPessoa = (Element) node;

					// PEGANDO OS VALORES DO ARQUIVO XML
					String nome = elementPessoa.getElementsByTagName("nome").item(0).getChildNodes().item(0)
							.getNodeValue();
					String sexo = elementPessoa.getElementsByTagName("sexo").item(0).getChildNodes().item(0)
							.getNodeValue();
					String email = elementPessoa.getElementsByTagName("email").item(0).getChildNodes().item(0)
							.getNodeValue();
					String endereco = elementPessoa.getElementsByTagName("endereco").item(0).getChildNodes().item(0)
							.getNodeValue();

					PessoaModelo novaPessoa = new PessoaModelo();

					novaPessoa.setUsuarioModelo(this.usuarioControlador.getUsuarioSession());
					novaPessoa.setEmail(email);
					novaPessoa.setEndereco(endereco);
					novaPessoa.setNome(nome);
					novaPessoa.setOrigemCadastro("X");
					novaPessoa.setSexo(sexo);

					// SALVANDO UM REGISTRO QUE VEIO DO ARQUIVO XML
					this.pessoaRepositorio.salvarNovoRegistro(novaPessoa);
				}
			}

			Utilidades.exibirMensagemInfo("Registros cadastrados com sucesso!");

		} catch (Exception ex) {
			ex.printStackTrace();
			Utilidades.exibirMensagemFatal("Não foi possível ler o arquivo. O arquivo é válido?");
		}

	}

	public void recuperarTodasPessoas() {
		this.pessoaRepositorio.recuperarPessoas();
	}
	
    public void onTabChange(TabChangeEvent event) {
        System.out.println("\n\n ------ Tab atual: " + event.getTab().getId());
    }

}