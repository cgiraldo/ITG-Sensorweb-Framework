package es.itg.sensorweb.services.helpers.swecommon.impl;


import org.apache.xmlbeans.XmlObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import es.itg.sensorweb.model.swecommon.Quantity;
import net.opengis.gml.x32.MeasureDocument;
import net.opengis.gml.x32.MeasureType;
import net.opengis.swe.x20.QuantityType;
import net.opengis.swe.x20.QuantityDocument;

public class QuantityHelperImpl {

	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractDataComponentHelperImpl.class);
	
	public QuantityHelperImpl(){};

	/////////////////////////////
	//XML Deserialize
	/////////////////////////////
	public Quantity deserialize(XmlObject xmlObject){
		LOGGER.debug("deserializing Quantity");
		if (xmlObject instanceof QuantityType){
			LOGGER.debug(" Quantity Type Detected");
			return deserialize((QuantityType)xmlObject);
		}
		else if (xmlObject instanceof MeasureType){
			LOGGER.debug(" Measure Type Detected");
			return deserialize((MeasureType)xmlObject);
		}

		else if (xmlObject instanceof net.opengis.swe.x101.QuantityDocument.Quantity){
			LOGGER.debug("Quantity x101 Detected");
			return deserialize((net.opengis.swe.x101.QuantityDocument.Quantity)xmlObject);
		}
		LOGGER.error("No se ha implementado un deserializador para el tipo: "+xmlObject.getClass().getName());
		return null;
	}

	protected Quantity deserialize(MeasureType measure_xb){
		Quantity quantity = new Quantity();
		quantity.setUom(measure_xb.getUom());
		quantity.setValue(measure_xb.getDoubleValue());
		return quantity;	  


	}
	protected Quantity deserialize(net.opengis.swe.x101.QuantityDocument.Quantity quantity_xb){
		Quantity quantity = new Quantity();
		if (quantity_xb.isSetDefinition()){
			quantity.setDefinition(quantity_xb.getDefinition());
		}
		quantity.setUom(quantity_xb.getUom().getCode());
		if (quantity_xb.isSetValue()) {
			quantity.setValue(quantity_xb.getValue());
		}
		if (quantity_xb.isSetDescription()){
			quantity.setDescription(quantity_xb.getDescription().getStringValue());
		}
		return quantity;	  
	}

	public Quantity deserialize(net.opengis.swe.x101.QuantityDocument xmlDoc){
		return deserialize(xmlDoc.getQuantity());
	}
	//Protected to force to use AbstractDataComponent.deserialize in the case of SweCommon 2.0
	protected Quantity deserialize( QuantityType quantity_xb) {
		Quantity quantity = new Quantity();
		if (quantity_xb.isSetOptional()) quantity.setOptional(quantity_xb.getOptional());
		quantity.setUom(quantity_xb.getUom().getCode());
		if (quantity_xb.isSetValue()) {
			quantity.setValue(quantity_xb.getValue());
		}
		quantity.setIdentifier(quantity_xb.getIdentifier());
		quantity.setDescription(quantity_xb.getDescription());
		quantity.setLabel(quantity_xb.getLabel());
		return quantity;	  
	}

	//Protected to force to use AbstractDataComponent.deserialize in the case of SweCommon 2.0
	protected Quantity deserialize(QuantityDocument xmlDoc){
		return deserialize(xmlDoc.getQuantity());
	}

	/////////////////////////////
	//XML Deserialize
	/////////////////////////////

	protected QuantityDocument serializeSwecommon_v20(Quantity quantity) {
		QuantityDocument quantityXmlDoc = QuantityDocument.Factory.newInstance();
		QuantityType quantityXml = quantityXmlDoc.addNewQuantity();
		quantityXml.setDefinition(quantity.getDefinition());
		quantityXml.addNewUom().setCode(quantity.getUom());
		if (quantity.getValue()!=null) quantityXml.setValue(quantity.getValue());
		return quantityXmlDoc; 
	}

	protected QuantityType serializeSwecommon_v20Type(Quantity quantity){	
		return serializeSwecommon_v20(quantity).getQuantity();
	}

	protected MeasureDocument serializeGml_v321(Quantity quantity) {
		MeasureDocument measureDoc = MeasureDocument.Factory.newInstance();
		measureDoc.addNewMeasure();
		measureDoc.getMeasure().setUom(quantity.getUom());
		if (quantity.getValue()!=null) measureDoc.getMeasure().setDoubleValue(quantity.getValue());
		return measureDoc;
	}

	protected MeasureType serializeGml_v321Type(Quantity quantity) {
		return serializeGml_v321(quantity).getMeasure();
	}
}

