package es.itg.sensorweb.model.swecommon;

import static org.junit.Assert.fail;

import java.io.IOException;


import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;



public class QuantityTest {
	
	private Quantity quantity;
	private String quantityJson;
	private ObjectMapper mapper;
	
	@Before
    public void initObjects() {
		mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);		
		quantity = new Quantity();
		quantity.setValue(-1345.251);
		quantity.setUom("volts");
		quantityJson="{\"type\":\".Quantity\",\"value\":-1345.251,\"uom\":\"volts\"}";
    }
	
	    @Test
	    public void testValidRequest() {
	        try {
	        	jsonSerialize();
	        	jsonDeserialize();
	          
	        } catch (Exception e) {
	            System.err.println("excepcion: "+ e.getMessage());
	            fail("A valid request resulted in an exception.");
	        }
	    }
	    private void jsonSerialize() throws JsonGenerationException, JsonMappingException, IOException{
	    	System.out.println("Quantity Serializing:");
    		System.out.println("     expected result: "+quantityJson);
			System.out.println("     result: "+mapper.writeValueAsString(quantity));
	    }
	    private void jsonDeserialize() throws JsonParseException, JsonMappingException, IOException{
	    	SWEIdentifiable sweId = mapper.readValue(quantityJson, SWEIdentifiable.class);
	    	if (sweId.getType().equals(Quantity.TYPE)){
	    		if (sweId instanceof Quantity) System.out.println("instanceof also works");
				Quantity quantity = (Quantity) sweId;
		    	System.out.println("Quantity Deserializing:");
	    		System.out.println("     expected result: "+ quantityJson);
				System.out.println("     result: "+mapper.writeValueAsString(quantity));
	    	}
	
	    }
	    
	    
}
