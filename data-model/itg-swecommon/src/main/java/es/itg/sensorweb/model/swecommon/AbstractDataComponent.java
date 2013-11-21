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
 * Class that represent the "SweCommon 2.0 AbstractDataComponent Type"
 * @author Carlos Giraldo
 */
public abstract class AbstractDataComponent extends SWEIdentifiable {
  /** optional flag indicating if the component value can be omitted in the data stream*/
  protected boolean optional;
  
  /** identifies the property (often an observed property in our context) that the data component represents. 
   * It should map to a controlled term defined in a dictionary, registry or ontology.
   */
  protected String definition;
  
  /** optional flag indicating if the component value is fixed or can be updatable*/
  protected boolean updatable;
  
  protected AbstractDataComponent quality; 
  
  /** definition of the type. Overridden by the subclasses */
  public static final String TYPE = ".AbstractDataComponent";
 
  
  public AbstractDataComponent () { 
	  super();
  };
  
  public AbstractDataComponent (String identifier, String description, String label){
	  super(identifier,description,label);
  }

/**
 * @return the optional
 */
public boolean isOptional() {
	return optional;
}

/**
 * @param optional the optional to set
 */
public void setOptional(boolean optional) {
	this.optional = optional;
}

/**
 * @param updatable the updatable to set
 */
public void setUpdatable(boolean updatable) {
	this.updatable = updatable;
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

  /**
   * @return the quality
   */
  public AbstractDataComponent getQuality() {
	  return quality;
  }

  /**
   * @param quality the quality to set
   */
  public void setQuality(AbstractDataComponent quality) {
	  this.quality = quality;
  }

  @JsonIgnore
  /**
   *  It returns the DataComponent value encoded in a String
   * @param blockSeparator The string used to separate text blocks
   * @param tokenSeparator The string used to separate text tokens
   * @return the text encoded Value
   */
  public abstract String getTextEncodedValue(String blockSeparator, String tokenSeparator);

  @JsonIgnore
  /**
   * @return the Type of the DataComponent
   */
  public abstract String getType();
}
