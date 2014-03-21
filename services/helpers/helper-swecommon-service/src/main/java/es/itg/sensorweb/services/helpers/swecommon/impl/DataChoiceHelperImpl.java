package es.itg.sensorweb.services.helpers.swecommon.impl;


import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.itg.sensorweb.model.swecommon.AbstractDataComponent;

import es.itg.sensorweb.model.swecommon.DataChoice;
import es.itg.sensorweb.services.helpers.swecommon.AbstractDataComponentHelperService;
import net.opengis.swe.x20.DataChoicePropertyType;
import net.opengis.swe.x20.DataChoiceType;
import net.opengis.swe.x20.DataChoiceType.Item;


import net.opengis.swe.x20.DataChoiceDocument;

public class DataChoiceHelperImpl {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DataChoiceHelperImpl.class);
	AbstractDataComponentHelperService abstractDataComponentHelper;

	public DataChoiceHelperImpl(AbstractDataComponentHelperService abstractDataComponentHelper){
		this.abstractDataComponentHelper = abstractDataComponentHelper;

	}

	/////////////////////////////
	//XML Deserialize
	/////////////////////////////
	public DataChoice deserialize(XmlObject xmlObject) throws XmlException{
		LOGGER.debug("deserializing DataChoice");
		if (xmlObject instanceof DataChoiceType){
			LOGGER.debug(" DataChoice Type Detected");
			return deserialize((DataChoiceType) xmlObject);
		}
		else if (xmlObject instanceof DataChoicePropertyType){
			LOGGER.debug(" DataChoicePropertyType Detected");
			return deserialize((DataChoicePropertyType) xmlObject);
		}
		else {
			LOGGER.error("No se ha implementado un deserializador para el tipo: "+xmlObject.getClass().getName());
			throw new XmlException("Deserializing of type " + xmlObject.getClass().getName()+" not implemented");
		}
	}
	
	 protected DataChoice deserialize(DataChoicePropertyType dataChoiceProp_xb) throws XmlException{
			return deserialize(dataChoiceProp_xb.getDataChoice());
	 }
	 
	 protected DataChoice deserialize(DataChoiceType dataChoice_xb) throws XmlException{
		DataChoice dataChoice = new DataChoice();
		dataChoice.setIdentifier(dataChoice_xb.getIdentifier());
		dataChoice.setDescription(dataChoice_xb.getDescription());
		if (dataChoice_xb.isSetOptional()) dataChoice.setOptional(dataChoice_xb.getOptional());
		AbstractDataComponent[] items = new AbstractDataComponent[dataChoice_xb.getItemArray().length];
		for (int i=0; i<items.length;i++){
			items[i] = abstractDataComponentHelper.deserialize(dataChoice_xb.getItemArray(i).getAbstractDataComponent());
			items[i].setIdentifier(dataChoice_xb.getItemArray(i).getName());
		}
		dataChoice.setItems(items);
		//XXX correct way of data choice?
		if (dataChoice_xb.isSetChoiceValue()){
			dataChoice.setChoiceValue(dataChoice_xb.getChoiceValue().getCategory().getValue());
		}
		return dataChoice;
	 }

	
/////////////////////////////
//XML Serialize
/////////////////////////////
	protected DataChoiceDocument serializeSwecommon_v20(DataChoice dataChoice) throws XmlException {

		DataChoiceDocument dataChoiceXmlDoc = DataChoiceDocument.Factory.newInstance();
		DataChoiceType choice_xb = dataChoiceXmlDoc.addNewDataChoice();
		if (dataChoice.getIdentifier()!=null) choice_xb.setIdentifier(dataChoice.getIdentifier());
		for (int i=0; i<dataChoice.getItems().length; i++){
			AbstractDataComponent item = dataChoice.getItems()[i];
			Item item_xb = choice_xb.addNewItem();
			item_xb.set(abstractDataComponentHelper.serializeSwecommon_v20(item));
			item_xb.setName(item.getIdentifier());
			
			//TODO what happens with datachoice??
		}
		return dataChoiceXmlDoc;
	}
	protected DataChoiceType serializeSwecommon_v20Type(DataChoice dataChoice) throws XmlException {
		return serializeSwecommon_v20(dataChoice).getDataChoice();
	}
}

