package de.hdm.itprojekt.shared;

import com.google.gwt.user.client.rpc.RemoteService;

public class Verwaltungsklasse {
	public interface Verwaltungsklasse extends RemoteService {
		
		public void init() throws IllegalArgumentException;
		

}
