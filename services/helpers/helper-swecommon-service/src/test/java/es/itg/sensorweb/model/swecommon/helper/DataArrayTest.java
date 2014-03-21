package es.itg.sensorweb.model.swecommon.helper;


import static org.junit.Assert.fail;

import java.io.IOException;
import java.math.BigInteger;

import net.opengis.swe.x20.DataArrayDocument;

import org.apache.xmlbeans.XmlException;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.itg.sensorweb.model.swecommon.AbstractDataComponent;
import es.itg.sensorweb.model.swecommon.DataArray;
import es.itg.sensorweb.model.swecommon.DataRecord;
import es.itg.sensorweb.model.swecommon.Quantity;
import es.itg.sensorweb.services.helpers.swecommon.impl.AbstractDataComponentHelperImpl;
import es.itg.sensorweb.services.helpers.swecommon.impl.BoolHelperImpl;
import es.itg.sensorweb.services.helpers.swecommon.impl.DataArrayHelperImpl;
import es.itg.sensorweb.services.helpers.swecommon.impl.DataChoiceHelperImpl;
import es.itg.sensorweb.services.helpers.swecommon.impl.DataRecordHelperImpl;
import es.itg.sensorweb.services.helpers.swecommon.impl.QuantityHelperImpl;
import es.itg.sensorweb.services.helpers.swecommon.impl.TextHelperImpl;


public class DataArrayTest {
	
	private DataArray dataArray;
	private String xmlString;
	private AbstractDataComponentHelperImpl helper;
	private ObjectMapper mapper;

	
	@Before
    public void initObjects() {
		mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);	
		//When using the library, this part will be done by the Dependency Injector
		helper = new AbstractDataComponentHelperImpl();
	
		
		AbstractDataComponent fields[] = new AbstractDataComponent[2];
		Quantity deep = new Quantity();
		deep.setIdentifier("deep");
		deep.setUom("m");
		fields[0] = deep;
		Quantity quantity = new Quantity();
		quantity.setIdentifier("temperature");
		quantity.setUom("cel");
		fields[1] = quantity;
		DataRecord record = new DataRecord();
		record.setIdentifier("deep-temperature");
		record.setFields(fields);
		dataArray = new DataArray();
		dataArray.setElementType(record);
		dataArray.setElementCount(BigInteger.valueOf(25));
		dataArray.setTokenSeparator("##");
		dataArray.setBlockSeparator("@@");
		dataArray.setTextEncodedResult("Should be the encoded result... as 34##21@@11##55, etc.");
		xmlString = "<ns:DataArray xmlns:ns=\"http://www.opengis.net/swe/2.0\">\n"+
				"<ns:elementCount>\n"+
				"    <ns:Count>\n"+
				"        <ns:value>25</ns:value>\n"+
				"    </ns:Count>\n"+
				"</ns:elementCount>\n"+
				"<ns:elementType>\n"+
				"    <ns:DataRecord>\n"+
				"        <ns:identifier>deep-temperature</ns:identifier>\n"+
				"        <ns:field name=\"deep\">\n"+
				"            <ns:Quantity definition=\"\">\n"+
				"                <ns:uom code=\"m\"/>\n"+
				"            </ns:Quantity>\n"+
				"        </ns:field>\n"+
				"        <ns:field name=\"temperature\">\n"+
				"            <ns:Quantity definition=\"\">\n"+
				"                <ns:uom code=\"cel\"/>\n"+
				"            </ns:Quantity>\n"+
				"        </ns:field>\n"+
				"    </ns:DataRecord>\n"+
				"</ns:elementType>\n"+
				"<ns:encoding>\n"+
				"    <ns:TextEncoding blockSeparator=\"@@\" tokenSeparator=\"##\"/>\n"+
				"</ns:encoding>\n"+
				"<ns:values>Should be the encoded result... as 34##21@@11##55, etc.</ns:values>\n"+
				"</ns:DataArray>\n";

    }
	
	    @Test
	    public void testValidRequest() {
	     
	        	xmlDeserialize();
	        	xmlSerialize_X20();
	        	//xmlSerializeGmlv321();
	          
	       
	    }
	    
	    private void xmlDeserialize(){
	    	try {
				System.out.println("DataArray Deserializing: "+xmlString);
				AbstractDataComponent data = helper.deserialize(DataArrayDocument.Factory.parse(xmlString).getAbstractDataComponent());
				System.out.println("result: "+mapper.writeValueAsString(data));
			} catch (XmlException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonGenerationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    
	    private void xmlSerialize_X20() {
			try {
				System.out.println("DataArray Serializing: "+mapper.writeValueAsString(dataArray));
				System.out.println("     result: "+helper.serializeSwecommon_v20(dataArray).xmlText());
			} catch (XmlException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonGenerationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    
	    private void xmlSerializeGmlv321() {
	    }
	    
}
