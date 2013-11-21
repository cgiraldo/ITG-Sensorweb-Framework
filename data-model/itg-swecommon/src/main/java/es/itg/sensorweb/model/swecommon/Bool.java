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


/**
 * Class that represent the "SweCommon 2.0 Boolean Type"
 * @author Carlos Giraldo
 */
public class Bool extends AbstractDataComponent {

  /** real value expressed in the unit specified */
  private Boolean value;
  
  
  public static final String TYPE = ".Bool";
  
  public Bool () { 
	  super();
  };
  
  public Bool (String identifier, String description, String label){
	  super(identifier,description,label);
  }
  /**
   * Set the value of value
   * @param newVar the new value of value
   */
  public void setValue ( Boolean newVar ) {
    value = newVar;
  }

  /**
   * Get the value of value
   * @return the value of value
   */
  public Boolean getValue ( ) {
    return value;
  }
  
  @Override
  @JsonIgnore
  public String getTextEncodedValue(String blockSeparator, String tokenSeparator){
  	return value.toString();
  }
  /**
   * @return       es.itg.sensorweb.model.swecommon.SWEType
   */
 @JsonIgnore
  public String getType(){
	  return TYPE;
  }
}
