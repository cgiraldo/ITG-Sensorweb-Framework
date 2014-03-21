package es.itg.sensorweb.services.helpers.swecommon.impl;


import net.opengis.gml.x32.MeasureType;
import net.opengis.swe.x20.DataArrayPropertyType;
import net.opengis.swe.x20.DataArrayType;
import net.opengis.swe.x20.DataChoicePropertyType;
import net.opengis.swe.x20.DataChoiceType;
import net.opengis.swe.x20.DataRecordPropertyType;
import net.opengis.swe.x20.DataRecordType;
import net.opengis.swe.x20.QuantityType;
import net.opengis.swe.x20.TextType;

import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import es.itg.sensorweb.model.swecommon.AbstractDataComponent;
import es.itg.sensorweb.model.swecommon.Bool;
import es.itg.sensorweb.model.swecommon.DataArray;
import es.itg.sensorweb.model.swecommon.DataChoice;
import es.itg.sensorweb.model.swecommon.DataRecord;
import es.itg.sensorweb.model.swecommon.Quantity;
import es.itg.sensorweb.model.swecommon.Text;

import es.itg.sensorweb.services.helpers.swecommon.AbstractDataComponentHelperService;

@Service
public class AbstractDataComponentHelperImpl implements AbstractDataComponentHelperService{

	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractDataComponentHelperImpl.class);

	BoolHelperImpl boolHelper;

	DataArrayHelperImpl dataArrayHelper;

	DataChoiceHelperImpl dataChoiceHelper;

	DataRecordHelperImpl dataRecordHelper;

	QuantityHelperImpl quantityHelper;

	TextHelperImpl textHelper;

	public AbstractDataComponentHelperImpl(){
		boolHelper = new BoolHelperImpl();
		dataArrayHelper = new DataArrayHelperImpl(this);
		dataChoiceHelper = new DataChoiceHelperImpl(this);
		dataRecordHelper = new DataRecordHelperImpl(this);
		quantityHelper = new QuantityHelperImpl();
		textHelper = new TextHelperImpl();
	};


	//////////////////////////////
	// XML Deserialize
	//////////////////////////////

	public AbstractDataComponent deserialize(XmlObject xmlObject) throws XmlException{
		LOGGER.debug("deserializing AbstractDataComponent: "+xmlObject.toString());
		if (xmlObject instanceof AbstractDataComponent){
			LOGGER.debug(" AbstractDataComponent Type Detected");
		}
		if ((xmlObject instanceof QuantityType)||(xmlObject instanceof MeasureType)||
				(xmlObject instanceof net.opengis.swe.x101.QuantityDocument.Quantity)){
			LOGGER.debug(" Quantity Type Detected");
			return quantityHelper.deserialize(xmlObject);
		}
		else if ((xmlObject instanceof DataArrayPropertyType)||(xmlObject instanceof DataArrayType)){
			LOGGER.debug("DataArray Type Detected") ;
			return dataArrayHelper.deserialize(xmlObject);
		}
		else if ((xmlObject instanceof TextType)){
			LOGGER.debug("Text Type Detected") ;
			return textHelper.deserialize(xmlObject);
		}
		else if ((xmlObject instanceof DataRecordPropertyType)||(xmlObject instanceof DataRecordType)){
			LOGGER.debug("DataRecord Type Detected") ;
			return dataRecordHelper.deserialize(xmlObject);
		}
		else if ((xmlObject instanceof DataChoicePropertyType)||(xmlObject instanceof DataChoiceType)){
			LOGGER.debug("DataChoice Type Detected") ;
			return dataChoiceHelper.deserialize(xmlObject);
		}
		else {
			LOGGER.debug("Deserializing of classname " 
		         + xmlObject.getClass().getName()+" not implemented");
			throw new XmlException("Deserializing of type " + xmlObject.getClass().getName()+" not implemented");
		}
	}

	//////////////////////////////
	//XML Serialize
	//////////////////////////////

	public XmlObject serializeSwecommon_v20(AbstractDataComponent absData) throws XmlException {

		if (absData.getType().equals(Quantity.TYPE)) {
			return quantityHelper.serializeSwecommon_v20((Quantity)absData);
		}
		else if (absData.getType().equals(Text.TYPE)) {
			return textHelper.serializeSwecommon_v20((Text)absData);
		}
		else if (absData.getType().equals(DataArray.TYPE)) {
			return dataArrayHelper.serializeSwecommon_v20((DataArray)absData);
		}
		else if (absData.getType().equals(DataRecord.TYPE)) {
			return dataRecordHelper.serializeSwecommon_v20((DataRecord)absData);
		}
		else if (absData.getType().equals(Bool.TYPE)) {
			return boolHelper.serializeSwecommon_v20((Bool)absData);
		}
		else if (absData.getType().equals(DataChoice.TYPE)){
			return dataChoiceHelper.serializeSwecommon_v20((DataChoice)absData);
		}
		throw new XmlException("swecommon v20 serialization of type "+absData.getType()+ "not yet implemented");

	}

	public XmlObject serializeSwecommon_v20Type(AbstractDataComponent absData) throws XmlException {

		if (absData.getType().equals(Quantity.TYPE)) {
			return quantityHelper.serializeSwecommon_v20Type((Quantity)absData);
		}
		else if (absData.getType().equals(Text.TYPE)) {
			return textHelper.serializeSwecommon_v20Type((Text)absData);
		}
		else if (absData.getType().equals(DataArray.TYPE)) {
			return dataArrayHelper.serializeSwecommon_v20Type((DataArray)absData);
		}
		else if (absData.getType().equals(DataRecord.TYPE)) {
			return dataRecordHelper.serializeSwecommon_v20Type((DataRecord)absData);
		}

		else if (absData.getType().equals(Bool.TYPE)) {
			return boolHelper.serializeSwecommon_v20Type((Bool)absData);
		}
		else if (absData.getType().equals(DataChoice.TYPE)){
			return dataChoiceHelper.serializeSwecommon_v20Type((DataChoice)absData);
		}
		throw new XmlException("swecommon v20 serialization of"+ absData.getIdentifier() +" of type "+absData.getType()+ "not yet implemented");
	}

	public XmlObject serializeGml_v321Type(AbstractDataComponent absData) throws XmlException {

		if (absData.getType().equals(Quantity.TYPE)) {
			return quantityHelper.serializeGml_v321Type((Quantity)absData);
		}
		throw new XmlException("gml v321 serialization of type "+absData.getType()+ "not yet implemented");
	}
}
