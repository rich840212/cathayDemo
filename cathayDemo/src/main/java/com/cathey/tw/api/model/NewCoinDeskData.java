package com.cathey.tw.api.model;

import java.time.ZonedDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class NewCoinDeskData {
	private List<NewCurrency> currency;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private ZonedDateTime upDateTime;
}
