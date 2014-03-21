/**
 * Copyright (C) 2013
 * by Instituto Tecnológico de Galicia
 *
 * Contact: Carlos Giraldo
 * 52 Instituto Tecnológico de Galicia
 * PO.CO.MA.CO. Sector i, Portal 5 15190, A Coruña
 * A Coruña, Spain
 * cgiraldo@5itg.es
 *
 * This program is free software; you can redistribute and/or modify it under
 * the terms of the GNU General Public License version 2 as published by the
 * Free Software Foundation.
 *
 * This program is distributed WITHOUT ANY WARRANTY; even without the implied
 * WARRANTY OF MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program (see gnu-gpl v2.txt). If not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA or
 * visit the Free Software Foundation web page, http://www.fsf.org.
 */

package es.itg.sensorweb.model.swecommon;

import static org.junit.Assert.assertEquals;

import org.junit.Test;





public class TextEncodingTest {
	
	
  @Test
  public void testTextEncoding() {
	       
	    	Quantity quantity = new Quantity("quantity_id",null,null);
	    	quantity.setValue(123.21);
	    	Bool bool = new Bool("bool_id",null,null);
	    	bool.setValue(true);
	    	Text text = new Text("text_id",null,null);
	    	text.setValue("testText");
	    	
	    	DataRecord dataRecord = new DataRecord();
	    	AbstractDataComponent[] fields = new AbstractDataComponent[3];
	    	fields[0] = quantity;
	    	fields[1] = bool;
	    	fields[2] = text;
	    	dataRecord.setFields(fields);
	    	String recordTextEncoded = dataRecord.getTextEncodedValue(";",",");
	    	System.out.println("RecordTextEncoded: "+recordTextEncoded);
	    	assertEquals(recordTextEncoded,"123.21,true,testText");
	    	
	    	DataChoice dataChoice = new DataChoice();
	    	dataChoice.setItems(fields);
	    	dataChoice.setChoiceValue("bool_id");
	    	String choiceTextEncoded = dataChoice.getTextEncodedValue("@","#");
	    	System.out.println("ChoiceTextEncoded: "+choiceTextEncoded);
	    	assertEquals(choiceTextEncoded,"bool_id#true");
	    	dataChoice.setChoiceValue("text_id");
	    	System.out.println("ChoiceTextEncoded: "+ dataChoice.getTextEncodedValue("@","!!#"));
	    	assertEquals(dataChoice.getTextEncodedValue("@","!!#"),"text_id!!#testText");  	
	    	
	    	DataArray dataArray = new DataArray();
	    	dataArray.setBlockSeparator(";");
	    	dataArray.setTokenSeparator(",");
	    	dataArray.setTextEncodedResult("11,aa;22,bb;33,cc;44,dd;55,ee;66,ff");
	    	System.out.println(dataArray.getTextEncodedValue("@", "!"));
	    	
  }  
}
