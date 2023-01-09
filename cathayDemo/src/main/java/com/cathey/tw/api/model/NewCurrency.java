package com.cathey.tw.api.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewCurrency {
	private String code;
	private String codeName;
	private String rate;
	private Float rate_Float;
}
