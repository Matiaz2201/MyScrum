package com.myscrum.model;

public class Etapa {
	
	private String etapa;
	private String etapaAtualiza;
	private int idEtapa;
	
	public Etapa(String etapa) {
		this.etapa = etapa;
	}
	
	public Etapa(String etapa, String etapaAtualiza) {
		this.etapa = etapa;
		this.etapaAtualiza = etapaAtualiza;

	}
	
	public String getEtapa() {
		return etapa;
	}
	public void setEtapa(String etapa) {
		this.etapa = etapa;
	}
	public String getEtapaAtualiza() {
		return etapaAtualiza;
	}
	public void setEtapaAtualiza(String etapaAtualiza) {
		this.etapaAtualiza = etapaAtualiza;
	}
	public int getIdEtapa() {
		return idEtapa;
	}
	public void setIdEtapa(int idEtapa) {
		this.idEtapa = idEtapa;
	}
	
}
