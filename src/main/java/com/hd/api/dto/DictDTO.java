package com.hd.api.dto;

import java.io.Serializable;

public class DictDTO implements Serializable {

	private String DictId;
	
	private String Value;

	private String Code;

	
	public String getDictId() {
		return DictId;
	}

	public void setDictId(String dictId) {
		DictId = dictId;
	}
	
	public String getValue() {
		return Value;
	}

	public void setValue(String value) {
		Value = value;
	}

	public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		Code = code;
	}

}
