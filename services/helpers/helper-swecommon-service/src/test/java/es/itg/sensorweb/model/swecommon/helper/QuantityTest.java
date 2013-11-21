package es.itg.sensorweb.model.swecommon.helper;


import static org.junit.Assert.fail;

import net.opengis.gml.QuantityDocument;

import org.apache.xmlbeans.XmlException;
import org.junit.Before;
import org.junit.Test;

import es.itg.sensorweb.model.swecommon.Quantity;


public class QuantityTest {
	
	private Quantity quantity;
	private String xmlString;
	
	@Before
    public void initObjects() {
		quantity = new Quantity();
		quantity.setValue(-1345.251);
		quantity.setUom("volts");
		
    	xmlString = "<ns:Quantity definition=\"\" xmlns:ns=\"http://www.opengis.net/swe/2.0\"><ns:uom code=\"volts\"/><ns:value>-1345.251</ns:value></ns:Quantity>";
	}
	
	    @Test
	    public void testValidRequest() {
	
	        	xmlDeserializeX20();
	        	xmlSerialize();
	        	xmlSerializeGmlv321();
	          
	       
	    }
	    
	    private void xmlDeserializeX20(){
	    
	    }
	    
	    private void xmlSerialize() {
	    }
	    
	    private void xmlSerializeGmlv321() {
	    	
	    }
	    
}
