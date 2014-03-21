package es.itg.sensorweb.services.helpers.swecommon.impl;


import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.itg.sensorweb.model.swecommon.AbstractDataComponent;
import es.itg.sensorweb.model.swecommon.DataRecord;
import es.itg.sensorweb.services.helpers.swecommon.AbstractDataComponentHelperService;
import net.opengis.swe.x20.DataRecordPropertyType;
import net.opengis.swe.x20.DataRecordType;
import net.opengis.swe.x20.DataRecordType.Field;


import net.opengis.swe.x20.DataRecordDocument;

public class DataRecordHelperImpl {
	private static final Logger LOGGER = LoggerFactory.getLogger(DataRecordHelperImpl.class);
	AbstractDataComponentHelperService abstractDataComponentHelper;
	 
	 public DataRecordHelperImpl(AbstractDataComponentHelperService abstractDataComponentHelper){
		 this.abstractDataComponentHelper = abstractDataComponentHelper;
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
	public DataRecord deserialize(XmlObject xmlObject) throws XmlException{
		LOGGER.debug("deserializing DataRecord");
		if (xmlObject instanceof DataRecordType){
			LOGGER.debug(" DataRecord Type Detected");
			return deserialize((DataRecordType) xmlObject);
		}
		else if (xmlObject instanceof DataRecordPropertyType){
			LOGGER.debug(" DataRecordPropertyType Detected");
			return deserialize((DataRecordPropertyType) xmlObject);
		}
		else {
			LOGGER.error("No se ha implementado un deserializador para el tipo: "+xmlObject.getClass().getName());
			throw new XmlException("Deserializing of type " + xmlObject.getClass().getName()+" not implemented");
		}
	}
	
	 protected DataRecord deserialize(DataRecordPropertyType dataRecordProp_xb) throws XmlException{
			return deserialize(dataRecordProp_xb.getDataRecord());
	 }
	 
	 protected DataRecord deserialize(DataRecordType dataRecord_xb) throws XmlException{
		DataRecord dataRecord = new DataRecord();
		dataRecord.setIdentifier(dataRecord_xb.getIdentifier());
		dataRecord.setDescription(dataRecord_xb.getDescription());
		if (dataRecord_xb.isSetOptional()) dataRecord.setOptional(dataRecord_xb.getOptional());
		AbstractDataComponent[] fields = new AbstractDataComponent[dataRecord_xb.getFieldArray().length];
		for (int i=0; i<fields.length;i++){
			fields[i] = abstractDataComponentHelper.deserialize(dataRecord_xb.getFieldArray(i).getAbstractDataComponent());
			fields[i].setIdentifier(dataRecord_xb.getFieldArray(i).getName());
		}
		dataRecord.setFields(fields);
		return dataRecord;
	 }

	
/////////////////////////////
//XML Serialize
/////////////////////////////
	protected DataRecordDocument serializeSwecommon_v20(DataRecord dataRecord) throws XmlException {

		DataRecordDocument dataRecordXmlDoc = DataRecordDocument.Factory.newInstance();
		DataRecordType record_xb = dataRecordXmlDoc.addNewDataRecord();
		if (dataRecord.getIdentifier()!=null) record_xb.setIdentifier(dataRecord.getIdentifier());
		for (int i=0; i<dataRecord.getFields().length; i++){
			AbstractDataComponent field = dataRecord.getFields()[i];
			Field field_xb = record_xb.addNewField();
			field_xb.set(abstractDataComponentHelper.serializeSwecommon_v20(field));
			field_xb.setName(field.getIdentifier());
		}
		return dataRecordXmlDoc;
	}
	protected DataRecordType serializeSwecommon_v20Type(DataRecord dataRecord) throws XmlException {
		return serializeSwecommon_v20(dataRecord).getDataRecord();
	}
}

