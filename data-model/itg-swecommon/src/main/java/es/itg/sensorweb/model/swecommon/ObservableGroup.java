
/**
 * Copyright (C) 2013
 * by Instituto Tecnológico de Galicia
 *
 * Contact: Carlos Giraldo
 * 52 Instituto Tecnológico de Galicia
 * PO.CO.MA.CO. Sector i, Portal 5 15190, A Coruña
 * A Coruña, Spain
 * cgiraldo@5itg.es
 *
 * This program is free software; you can redistribute and/or modify it under
 * the terms of the GNU General Public License version 2 as published by the
 * Free Software Foundation.
 *
 * This program is distributed WITHOUT ANY WARRANTY; even without the implied
 * WARRANTY OF MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program (see gnu-gpl v2.txt). If not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA or
 * visit the Free Software Foundation web page, http://www.fsf.org.
 */
package es.itg.sensorweb.model.swecommon;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;



/**
 * Class that represent a Group of {@link ObservableProperty}
 * @author Carlos Giraldo
 */
public class ObservableGroup extends SWEIdentifiable{
	
	public static final String TYPE = ".ObservableGroup";

	public List<ObservableProperty> observableProperties;

	public ObservableGroup () { };
	
	public ObservableGroup (String identifier, String description, String label){
		  super(identifier,description,label);
	}


	public List<ObservableProperty> getObservableProperties() {
		return observableProperties;
	}
	public void setObservableProperties(
			List<ObservableProperty> observableProperties) {
		this.observableProperties = observableProperties;
	}
	
	@Override
	@JsonIgnore
	public String getType() {
		return TYPE;
	}

}
