package com.cathey.tw.api.model;

import lombok.Data;

@Data
public class CoinDeskBpi {
	private String code;
	private String symbol;
	private String rate;
	private String description;
	private float rate_float;
}
