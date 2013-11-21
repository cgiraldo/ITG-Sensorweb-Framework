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
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Class that represent the "SweCommon 2.0 Basic Types::AbstractSWEIdentifiable"
 * @author Carlos Giraldo
 */

@JsonTypeInfo(use=JsonTypeInfo.Id.MINIMAL_CLASS, include=JsonTypeInfo.As.PROPERTY, property="type")
public abstract class SWEIdentifiable {
  
  /** param id only used as Entity Identification if JPA is used. */
  private long id;
  
  /** identifier unique identifier of the component */
  protected String identifier;
  
  /** Human readable information describing the component */
  protected String description;
  
  /** Optional classification string */
  protected String label;  
  
  /** definition of the type. Overridden by the subclasses */
  public static final String TYPE = ".SWEIdentifiable";

  /** 
   * Constructors 
   */
  public SWEIdentifiable(){
  }
  
  public SWEIdentifiable(String identifier,String description, String label) {
	  this.identifier = identifier;
	  this.description = description;
	  this.label = label;
  } 
  
  /**
   * Get the value of id
   * @return the value of id
   */
  @JsonIgnore
  public long getId() {
	  return id;
  }
  
  /**
   * Set the value of identifier
   * @param id the new value of identifier
   */
  public void setId(long id) {
	this.id = id;
}


  
  /**
   * Set the value of identifier
   * @param newVar the new value of identifier
   */
  public void setIdentifier ( String newVar ) {
    identifier = newVar;
  }

  /**
   * Get the value of identifier
   * @return the value of identifier
   */

  public String getIdentifier ( ) {
    return identifier;
  }

  /**
   * Set the value of description
   * @param newVar the new value of description
   */
  public void setDescription ( String newVar ) {
    description = newVar;
  }

  /**
   * Get the value of description
   * @return the value of description
   */
  public String getDescription ( ) {
    return description;
  }

  /**
   * Set the value of label
   * @param newVar the new value of label
   */
  public void setLabel ( String newVar ) {
    label = newVar;
  }

  /**
   * Get the value of label
   * @return the value of label
   */
  public String getLabel ( ) {
    return label;
  }
  
  /**
   * Get the value of TYPE. ".SweIdentifiable" for the current class
   * @return the value of TYPE
   */
  @JsonIgnore
  public abstract String getType();
   
}
