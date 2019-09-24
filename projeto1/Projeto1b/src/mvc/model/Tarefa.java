package mvc.model;

import java.util.Calendar;

public class Tarefa {
	private int id;
	private String descricao;
	private Calendar dataFinalizcao;
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	
	public String getDescricao() {
		return this.descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Calendar getDataFinalizacao() {
		return dataFinalizcao;
	}
	
	public void setDataFinalizacao(Calendar dataFinalizacao){
		this.dataFinalizcao = dataFinalizacao;
	}
}
