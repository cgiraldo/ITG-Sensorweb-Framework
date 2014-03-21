package es.itg.sensorweb.services.helpers.swecommon.impl;


import org.apache.xmlbeans.XmlObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import es.itg.sensorweb.model.swecommon.Bool;
import net.opengis.swe.x20.BooleanDocument;
import net.opengis.swe.x20.BooleanType;


public class BoolHelperImpl {

	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractDataComponentHelperImpl.class);

	public BoolHelperImpl(){};
	/////////////////////////////
	//XML Deserialize
	/////////////////////////////
	public Bool deserialize(XmlObject xmlObject){
		LOGGER.debug("deserializing Boolean");
		if (xmlObject instanceof BooleanType){
			LOGGER.debug(" Boolean Type Detected");
			return deserialize((BooleanType)xmlObject);
		}
		LOGGER.error("No se ha implementado un deserializador para el tipo: "+xmlObject.getClass().getName());
		return null;
	}

	protected Bool deserialize(BooleanType bool_xb){
		Bool bool = new Bool();
		bool.setValue(bool_xb.getValue());
		return bool;	  
	}


	/////////////////////////////
	//XML Serialize
	/////////////////////////////

	protected BooleanDocument serializeSwecommon_v20(Bool bool) {
		BooleanDocument booleanXmlDoc = BooleanDocument.Factory.newInstance();
		BooleanType booleanXml = booleanXmlDoc.addNewBoolean();
		if (bool.getDefinition()!=null) booleanXml.setDefinition(bool.getDefinition());
		if (bool.getValue()!=null) booleanXml.setValue(bool.getValue());
		return booleanXmlDoc; 
	}

	protected BooleanType serializeSwecommon_v20Type(Bool bool){	
		return serializeSwecommon_v20(bool).getBoolean();
	}
}

