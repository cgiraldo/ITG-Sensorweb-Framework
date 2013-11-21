package es.itg.sensorweb.services.helpers.swecommon.impl;

import javax.xml.namespace.QName;

import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.itg.sensorweb.model.swecommon.DataArray;
import es.itg.sensorweb.services.helpers.swecommon.AbstractDataComponentHelperService;

import net.opengis.swe.x20.DataArrayPropertyType;
import net.opengis.swe.x20.DataArrayType;


import net.opengis.swe.x20.TextEncodingType;

import net.opengis.swe.x20.DataArrayDocument;
@Service
public class DataArrayHelperImpl {
	
	 private static final Logger LOGGER = LoggerFactory.getLogger(DataArrayHelperImpl.class);
     @Autowired
	 AbstractDataComponentHelperService abstractDataComponentHelper;
	 
	 public DataArrayHelperImpl(){
		 
	 }
	
	 public AbstractDataComponentHelperService getAbstractDataComponentHelper() {
		return abstractDataComponentHelper;
	}

	public void setAbstractDataComponentHelper(
			AbstractDataComponentHelperService abstractDataComponentHelper) {
		this.abstractDataComponentHelper = abstractDataComponentHelper;
	}

    /////////////////////////////
    //XML Deserialize
    /////////////////////////////
	public DataArray deserialize(XmlObject xmlObject) throws XmlException{
		 LOGGER.debug("deserializing DataArray");
		 if (xmlObject instanceof DataArrayType){
			 LOGGER.debug(" DataArray Type Detected");
			 return deserialize((DataArrayType) xmlObject);
		 }
		 else if (xmlObject instanceof DataArrayPropertyType){
			 LOGGER.debug(" DataArray Type Detected");
			 return deserialize((DataArrayPropertyType) xmlObject);
		 }
		 else {
			 LOGGER.error("No se ha implementado un deserializador para el tipo: "+xmlObject.getClass().getName());
			 throw new XmlException("Deserializing of type " + xmlObject.getClass().getName()+" not implemented");
		 }
	 }
	 protected DataArray deserialize(DataArrayPropertyType dataArrayProp_xb) throws XmlException{
			return deserialize(dataArrayProp_xb.getDataArray1());
	 }
	 
	 protected DataArray deserialize(DataArrayType dataArray_xb) throws XmlException{
		DataArray dataArray = new DataArray();
		dataArray.setIdentifier(dataArray_xb.getIdentifier());
		dataArray.setDescription(dataArray_xb.getDescription());
		if (dataArray_xb.isSetOptional()) dataArray.setOptional(dataArray_xb.getOptional());
		dataArray.setElementCount(dataArray_xb.getElementCount().getCount().getValue());
		dataArray.setElementType(
				abstractDataComponentHelper.deserialize(dataArray_xb.getElementType().getAbstractDataComponent()));
		if (dataArray_xb.getEncoding().getAbstractEncoding() instanceof TextEncodingType){
			LOGGER.debug("Text encoding detected");
			TextEncodingType textEncoding_xb = (TextEncodingType)dataArray_xb.getEncoding().getAbstractEncoding();
			dataArray.setBlockSeparator(textEncoding_xb.getBlockSeparator());
			dataArray.setTokenSeparator(textEncoding_xb.getTokenSeparator());
			XmlCursor cursor = dataArray_xb.getValues().newCursor();
			cursor.toFirstChild();
			dataArray.setTextEncodedResult(cursor.getTextValue());
			return dataArray;
		}
		else{
			throw new XmlException("Only Text Encoding data Array deserialize is implemented");
		}
	 }

	 /////////////////////////////
	 //XML Serialize
	 /////////////////////////////
	 protected DataArrayDocument serializeSwecommon_v20(DataArray dataArray) throws XmlException {

		 DataArrayDocument dataArrayXmlDoc = DataArrayDocument.Factory.newInstance();
		 DataArrayType array_xb = dataArrayXmlDoc.addNewDataArray1();
		 array_xb.addNewElementCount().addNewCount().setValue(dataArray.getElementCount());
		 array_xb.addNewElementType().setName(dataArray.getElementType().getIdentifier());
		 array_xb.getElementType().set(abstractDataComponentHelper.serializeSwecommon_v20(dataArray.getElementType()));
		
		
		TextEncodingType textEncoding_xb = (TextEncodingType)array_xb.addNewEncoding().addNewAbstractEncoding()
				.substitute(new QName("http://www.opengis.net/swe/2.0","TextEncoding"),TextEncodingType.type);
		
		
		textEncoding_xb.setBlockSeparator(dataArray.getBlockSeparator());
		textEncoding_xb.setTokenSeparator(dataArray.getTokenSeparator());
		XmlCursor cursor = array_xb.addNewValues().newCursor();
		cursor.toFirstChild();
		cursor.setTextValue(dataArray.getTextEncodedResult());
		
		return dataArrayXmlDoc;
	}
	protected DataArrayType serializeSwecommon_v20Type(DataArray dataArray) throws XmlException {
		return serializeSwecommon_v20(dataArray).getDataArray1();
	}
}

