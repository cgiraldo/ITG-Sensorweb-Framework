
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

import com.fasterxml.jackson.annotation.JsonIgnore;


public class ObservableProperty extends SWEIdentifiable {
	
  private String definition;
  
  public static final String TYPE = ".ObservableProperty";

  public ObservableProperty () {
	  super();
		identifier="observableProperty"; //Default identifier
  }

  public ObservableProperty(String identifier, String description, String label){
	  super(identifier,description,label);
  }
  
  /**
   * Set the value of definition
   * @param newVar the new value of definition
   */
  public void setDefinition ( String newVar ) {
    definition = newVar;
  }

  /**
   * Get the value of definition
   * @return the value of definition
   */
  public String getDefinition ( ) {
    return definition;
  }

@Override
@JsonIgnore
public String getType() {
	return TYPE;
}


}

