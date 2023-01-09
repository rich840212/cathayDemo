package com.cathey.tw.api.model;

import java.util.Map;

import lombok.Data;

@Data
public class CoinDeskData {
	
	private Map<String,String> time;
	private String disclaimer;
	private String chartName;
	private Map<String,CoinDeskBpi> bpi;
}
