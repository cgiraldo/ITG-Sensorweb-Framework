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

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Class that represent the "SweCommon 2.0 DataArray Type"
 * @author Carlos Giraldo
 */
public class DataArray extends AbstractDataComponent {

	public static final String TYPE = ".DataArray";

	private BigInteger elementCount;
	
	private AbstractDataComponent elementType;
	
	private String blockSeparator;
	
	private String tokenSeparator;
	
	private String textEncodedResult;
	
	public DataArray () { 
		super();
	}

	public DataArray (String identifier, String description, String label){
		super(identifier,description,label);
	}
	/**
	 * Set the value of elementCount
	 * @param newVar the new value of elementCount
	 */
	public void setElementCount ( BigInteger newVar ) {
		elementCount = newVar;
	}

	/**
	 * Get the value of elementCount
	 * @return the value of elementCount
	 */
	public BigInteger getElementCount ( ) {
		return elementCount;
	}

	public AbstractDataComponent getElementType() {
		return elementType;
	}

	public void setElementType(AbstractDataComponent elementType) {
		this.elementType = elementType;
	}

	public String getBlockSeparator() {
		return blockSeparator;
	}

	public void setBlockSeparator(String blockSeparator) {
		this.blockSeparator = blockSeparator;
	}

	public String getTokenSeparator() {
		return tokenSeparator;
	}

	public void setTokenSeparator(String tokenSeparator) {
		this.tokenSeparator = tokenSeparator;
	}

	public String getTextEncodedResult() {
		return textEncodedResult;
	}

	public void setTextEncodedResult(String textEncodedResult) {
		this.textEncodedResult = textEncodedResult;
	}

	/**
	 * @return       es.itg.sensorweb.model.swecommon.SWEType
	 */
	@Override
	@JsonIgnore
	public String getType(){
		return TYPE;
	}

	@Override
	@JsonIgnore
	public String getTextEncodedValue(String blockSeparator,
			String tokenSeparator) {
		
		Map<String,String> separators = new HashMap<String,String>();
		separators.put(this.blockSeparator,blockSeparator);
		separators.put(this.tokenSeparator,tokenSeparator);
		
		Pattern pattern = Pattern.compile("("+this.blockSeparator+"|"+this.tokenSeparator+")");
		Matcher matcher = pattern.matcher(this.textEncodedResult);
		
		StringBuffer sb = new StringBuffer();
		while(matcher.find()) {
			matcher.appendReplacement(sb, separators.get(matcher.group(1)));
		}
		return sb.toString();
	}


}
