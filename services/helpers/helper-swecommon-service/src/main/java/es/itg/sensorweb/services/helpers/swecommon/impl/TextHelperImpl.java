package es.itg.sensorweb.services.helpers.swecommon.impl;

import net.opengis.swe.x20.TextDocument;
import net.opengis.swe.x20.TextType;

import org.apache.xmlbeans.XmlObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import es.itg.sensorweb.model.swecommon.AllowedTokens;

import es.itg.sensorweb.model.swecommon.Text;

@Service
public class TextHelperImpl {
	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractDataComponentHelperImpl.class);

	public TextHelperImpl(){}
	/////////////////////////////
	//XML Deserialize
	/////////////////////////////
	public Text deserialize(XmlObject xmlObject){
		LOGGER.debug("deserializing Text Type");
		if (xmlObject instanceof TextType){
			LOGGER.debug(" Text Type Detected");
			return deserialize((TextType)xmlObject);
		}
		LOGGER.error("No se ha implementado un deserializador para el tipo: "+xmlObject.getClass().getName());
		return null;
	}

	protected Text deserialize(TextType text_xb){
		Text text = new Text();
		if(text_xb.isSetConstraint() && text_xb.getConstraint().isSetAllowedTokens()){
			AllowedTokens constraint = new AllowedTokens();
			constraint.setValue(text_xb.getConstraint().getAllowedTokens().getValueArray());
			constraint.setPattern(text_xb.getConstraint().getAllowedTokens().getPattern());
			text.setConstraint(constraint);
		}
		text.setValue(text_xb.getValue());
		return text;	  
	}

	/////////////////////////////
	//XML Serialize
	/////////////////////////////

	protected TextDocument serializeSwecommon_v20(Text text) {
		TextDocument textXmlDoc = TextDocument.Factory.newInstance();
		TextType textXml = textXmlDoc.addNewText();
		if (text.getDefinition()!=null) textXml.setDefinition(text.getDefinition());
		if (text.getValue()!=null) textXml.setValue(text.getValue());
		if (text.getConstraint()!=null){
			textXml.addNewConstraint().addNewAllowedTokens();
			if (text.getConstraint().getValue()!=null) textXml.getConstraint().getAllowedTokens().setValueArray(text.getConstraint().getValue());
			if (text.getConstraint().getPattern()!=null) textXml.getConstraint().getAllowedTokens().setPattern(text.getConstraint().getPattern());
		}
		return textXmlDoc; 
	}

	protected TextType serializeSwecommon_v20Type(Text text){	
		return serializeSwecommon_v20(text).getText();
	}
}
