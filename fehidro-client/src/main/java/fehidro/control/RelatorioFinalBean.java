package fehidro.control;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import fehidro.model.Avaliacao;
import fehidro.model.Relatorio;
import fehidro.rest.client.AvaliacaoRESTClient;

@ManagedBean(name="relatorioFinal")
@SessionScoped
public class RelatorioFinalBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Relatorio relatorio;
	private AvaliacaoRESTClient rest;
	
	
	public Relatorio getRelatorio() {
		return relatorio;
	}

	public void setRelatorio(Relatorio relatorio) {
		this.relatorio = relatorio;
	}

	public RelatorioFinalBean() {
		relatorio = new Relatorio();
		rest  = new AvaliacaoRESTClient();
		System.out.println("===========Metodo gerar - R Final===============");
		List<Avaliacao> avaliacoes = rest.findAll();//FIXME: Substituir por metodo mais apropriado + considerar data
		
//		if(avaliacoes == null)
//		{
//			System.out.println("TESTE");
//		}
//		System.out.println("> "+avaliacoes.toString());
		this.relatorio.setItensRelatorio(avaliacoes);
	}

	public String getUrl() {
		return "/relatorio/relatorioFinal";
	}
	
}
