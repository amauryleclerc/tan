package org.maurange.formation.licpro.rest;

public class TempsArret {
	private int sens;
	private String terminus;
	private String infotrafic;
	private String temps;
	private NumLigne ligne;
	private PointArret arret;
	
	public int getSens() {
		return sens;
	}
	
	public void setSens(int sens) {
		this.sens = sens;
	}
	public String getTerminus() {
		return terminus;
	}
	public void setTerminus(String terminus) {
		this.terminus = terminus;
	}

	public String getInfotrafic() {
		return infotrafic;
	}
	public void setInfotrafic(String infotrafic) {
		this.infotrafic = infotrafic;
	}
	public String getTemps() {
		return temps;
	}
	public void setTemps(String temps) {
		this.temps = temps;
	}
	public NumLigne getLigne() {
		return ligne;
	}
	public void setLigne(NumLigne ligne) {
		this.ligne = ligne;
	}
	public PointArret getArret() {
		return arret;
	}
	public void setArret(PointArret arret) {
		this.arret = arret;
	}
	
	
	
}
