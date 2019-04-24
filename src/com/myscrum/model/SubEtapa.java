package com.myscrum.model;

public class SubEtapa {
	
	private String subetapa;
	private String subetapaAtualiza;
	private int idSubEtapa;
	private String cc;
	private String etapa;
	
	public SubEtapa(String subetapa, String etapa, String cc) {
		this.subetapa = subetapa;
		this.etapa = etapa;
		this.cc = cc;
	}
	
	public SubEtapa(String subetapa, String subetapaAtualiza) {
		this.subetapa = subetapa;
		this.subetapaAtualiza = subetapaAtualiza;
	}
	
	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public String getEtapa() {
		return etapa;
	}

	public void setEtapa(String etapa) {
		this.etapa = etapa;
	}
	
	public String getSubetapa() {
		return subetapa;
	}
	public void setSubetapa(String subetapa) {
		this.subetapa = subetapa;
	}
	public String getSubetapaAtualiza() {
		return subetapaAtualiza;
	}
	public void setSubetapaAtualiza(String subetapaAtualiza) {
		this.subetapaAtualiza = subetapaAtualiza;
	}
	public int getIdSubEtapa() {
		return idSubEtapa;
	}
	public void setIdSubEtapa(int idSubEtapa) {
		this.idSubEtapa = idSubEtapa;
	}
}
