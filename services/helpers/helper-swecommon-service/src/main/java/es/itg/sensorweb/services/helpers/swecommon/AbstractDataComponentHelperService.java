package es.itg.sensorweb.services.helpers.swecommon;

import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;

import es.itg.sensorweb.model.swecommon.AbstractDataComponent;


public interface AbstractDataComponentHelperService {
   
//////////////////////////////
// XML Deserialize
//////////////////////////////
	
   public AbstractDataComponent deserialize(XmlObject xmlObject) throws XmlException;

//////////////////////////////
//XML Serialize
//////////////////////////////
  
  public XmlObject serializeSwecommon_v20(AbstractDataComponent absData) throws XmlException;
  
  public XmlObject serializeSwecommon_v20Type(AbstractDataComponent absData) throws XmlException;
  
  public XmlObject serializeGml_v321Type(AbstractDataComponent absData) throws XmlException;
  
}
