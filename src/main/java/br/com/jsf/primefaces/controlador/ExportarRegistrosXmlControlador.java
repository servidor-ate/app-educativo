package br.com.jsf.primefaces.controlador;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.jsf.primefaces.modelo.PessoaModelo;
import br.com.jsf.primefaces.repositorio.PessoaRepositorio;
import br.com.jsf.primefaces.utilidades.Utilidades;

@Named(value = "exportarRegistrosXmlControlador")
@RequestScoped
public class ExportarRegistrosXmlControlador implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8783375040535766929L;

	@Inject
	transient PessoaRepositorio pessoaRepositorio;

	private StreamedContent arquivoDownload;

	/***
	 * REALIZA O DOWNLOAD DO ARQUIVO XML
	 * 
	 * @return
	 */
	public StreamedContent getArquivoDownload() {
		this.downlaodArquivoXmlPessoa();
		return arquivoDownload;
	}

	/***
	 * GERANDO ARQUIVO XML PARA EXPORTAÇÃO.
	 * 
	 * @return
	 */
	private File gerarXmlPessoas() throws Exception {
		// MASCARA PARA FORMATAR A DATA QUE VAI SER ADICIONADA NO ARQUIVO XML
		final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		List<PessoaModelo> pessoasLista = pessoaRepositorio.recuperarPessoas();

		// ELEMENTO RAIZ DO NOSSO ARQUIVO XML
		Element elementPessoas = new Element("Pessoas");

		Document documentoPessoas = new Document(elementPessoas);

		pessoasLista.forEach(pessoa -> {

			// MONTANDO AS TAGS DO XML COM OS SEUS VALORES
			Element elementPessoa = new Element("Pessoa");
			elementPessoa.addContent(new Element("codigo").setText(String.valueOf(pessoa.getCodigo())));
			elementPessoa.addContent(new Element("nome").setText(pessoa.getNome()));
			elementPessoa.addContent(new Element("sexo").setText(pessoa.getSexo()));

			// FORMATANDO A DATA
			String dataCadastroFormatada = sdf.format(pessoa.getDataCadastro());

			elementPessoa.addContent(new Element("dataCadastro").setText(dataCadastroFormatada));
			elementPessoa.addContent(new Element("email").setText(pessoa.getEmail()));
			elementPessoa.addContent(new Element("endereco").setText(pessoa.getEndereco()));
			elementPessoa.addContent(new Element("origemCadastro").setText(pessoa.getOrigemCadastro()));
			elementPessoa.addContent(new Element("usuarioCadastro").setText(pessoa.getUsuarioModelo().getUsuario()));

			elementPessoas.addContent(elementPessoa);

		});

		XMLOutputter xmlGerado = new XMLOutputter();

		try {

			final SimpleDateFormat sdf_ = new SimpleDateFormat("yyyyMMddHHmmss");
			
			/* GERANDO O NOME DO ARQUIVO */
			String nomeArquivo = "pessoas_".concat(sdf_.format(new Date())).concat(".xml");

			String diretorioProjeto = System.getProperty("user.dir");
			System.out.printf("Exportando arquivo em [ %s ]\n", diretorioProjeto);

			// CAMINHO ONDE VAI SER GERADO O ARQUIVO XML
			File arquivo = new File(diretorioProjeto.concat("/").concat(nomeArquivo));

			FileWriter fileWriter = new FileWriter(arquivo);

			xmlGerado.output(documentoPessoas, fileWriter);

			return arquivo;

		} catch (Exception ex) {
			throw ex;
		}
	}

	/***
	 * PREPARA O ARQUIVO PARA DOWNLOAD
	 */
	public void downlaodArquivoXmlPessoa() {

		try {

			File arquivoXml = this.gerarXmlPessoas();

			InputStream inputStream = new FileInputStream(arquivoXml.getPath());

			arquivoDownload = new DefaultStreamedContent(inputStream, "application/xml", arquivoXml.getName());

		} catch (Exception ex) {
			Utilidades.exibirMensagemAtencao("Não foi possível exportar o arquivo: " + ex.getMessage());
		}

	}

}
