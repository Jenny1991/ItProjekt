package de.hdm.itprojekt.client.gui;

import java.io.Serializable;

@SuppressWarnings("serial")
public class StudiengangDetails implements Serializable {
	private String bezeichnung;
	private String id;
	
	public StudiengangDetails() 
	{ new StudiengangDetails("0", "");}
	
	public StudiengangDetails(String id, String bezeichnung){
		this.id=id;
		this.bezeichnung=bezeichnung;
	}
	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id=id;
	}
	public String getBezeichnung() {
		return bezeichnung;
	}	
	public void setBezeichnung(String bezeichnung){
		this.bezeichnung=bezeichnung;
	}
}
