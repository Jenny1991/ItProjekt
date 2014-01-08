package de.hdm.itprojekt.client.gui;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionModel;
import com.google.gwt.view.client.SingleSelectionModel;

import de.hdm.itprojekt.shared.*;
import de.hdm.itprojekt.shared.bo.Studiengang;

public interface StudiengangInhalt extends SelectionModel<Studiengang>{
		
	public String getBezeichnung();
	
	public String setBezeichnung();	
	
	public void displayStudiengang(Object model);
	
	
	
	
/**	private ProvidesKey<BusinessObjekt> boKeyProvider = new ProvidesKey<BusinessObjekt>() {
		public Integer getKey(BusinessObject bo) {
			if (bo == null) {
				return null;
			}
			if (bo instanceof Studiengang) {
				return new Integer(bo.getId());
			} else {
				return new Integer(-bo.getId());
			}
		}
	};

	private SingleSelectionModel<BusinessObjekt> selectionModel = new SingleSelectionModel<BusinessObjekt>(
			boKeyProvider);
	
	public ModelClass(Studiengang sg) {
		this.sg = sg;
		d.setCatvm(this);
		this.af = af;
		af.setCatvm(this);
		selectionModel
				.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
					public void onSelectionChange(SelectionChangeEvent event) {
						BusinessObjekt selection = selectionModel.getSelectedObject();
						if (selection instanceof Studiengang) {
							setSelectedStudiengang((Studiengang) selection);
						}
					}
				});
	}

	Dozent getSelectedStudiengang() {
		return selectedStudiengang;
	}

	void setSelectedStudiengang(Studiengang sg) {
		selectedStudiengang = d;
		d.setSelected(d);
		af.setSelected(null);
	} }*/
	
}
