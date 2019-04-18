package com.myscrum.model;

public class SubEtapa {
	
	private String subetapa;
	private String subetapaAtualiza;
	private int idSubEtapa;
	
	public SubEtapa(String subetapa) {
		this.subetapa = subetapa;
	}
	
	public SubEtapa(String subetapa, String subetapaAtualiza) {
		this.subetapa = subetapa;
		this.subetapaAtualiza = subetapaAtualiza;
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
