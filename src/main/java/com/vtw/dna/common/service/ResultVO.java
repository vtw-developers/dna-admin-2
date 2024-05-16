package com.vtw.dna.common.service;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Schema(description = "응답 객체 VO")
@Getter
@Setter
public class ResultVO {

	@Schema(description = "응답 코드")
	private int resultCode = 0;

	@Schema(description = "응답 메시지")
	private String resultMessage = "OK";
	private Map<String, Object> result = new HashMap<String, Object>();

	public void putResult(String key, Object value) {
		result.put(key, value);
	}

	public Object getResult(String key) {
		return this.result.get(key);
	}


}
