package com.myscrum.model;

public class Etapa {
	
	private String etapa;
	private String etapaAtualiza;
	private int idEtapa;
	private String cc;
	
	public Etapa(String etapa, String cc) {
		this.etapa = etapa;
		this.cc = cc;
	}
	
	public Etapa(String etapa, String etapaAtualiza, String cc) {
		this.etapa = etapa;
		this.etapaAtualiza = etapaAtualiza;
		this.cc = cc;

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

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}
	
}
