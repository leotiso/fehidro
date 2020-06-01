package fehidro.model;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import fehidro.control.ItemRelatorio;


public class Relatorio  {
	
	protected HashMap<Long, ItemRelatorio> itensRelatorio;
	
	public Relatorio()
	{
		
	}
	
	public Relatorio(List<Avaliacao> avaliacoes)
	{
		setItensRelatorio(avaliacoes);
	}
	
	public void setItensRelatorio(List<Avaliacao> avaliacoes)
	{
		Avaliacao avaliacaoAtual;
		Long idPropostaAtual;
		for(int i =0;i<avaliacoes.size();i++)
		{
			avaliacaoAtual = avaliacoes.get(i);
			idPropostaAtual = avaliacaoAtual.getProposta().getId();
			if(this.itensRelatorio.get(idPropostaAtual) == null)
			{
				this.itensRelatorio.put(new Long(i), new ItemRelatorio() );
			}
			
			this.itensRelatorio.get(idPropostaAtual).addAvaliacao(avaliacaoAtual);
		}
	}
	
	public LinkedList<ItemRelatorio> getItensRelatorio()
	{
		System.out.println("getting itens relatorio");
		return new LinkedList<ItemRelatorio>(this.itensRelatorio.values());
	}
	
	
}
