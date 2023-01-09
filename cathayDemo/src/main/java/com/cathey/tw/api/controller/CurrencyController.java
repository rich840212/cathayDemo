package com.cathey.tw.api.controller;

import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cathey.tw.api.clinet.CoinDeskApiClient;
import com.cathey.tw.api.model.CoinDeskData;
import com.cathey.tw.api.model.Currency;
import com.cathey.tw.api.model.NewCoinDeskData;
import com.cathey.tw.api.service.CurrecyService;

import lombok.RequiredArgsConstructor;



@RestController
@RequiredArgsConstructor
public class CurrencyController {
	
	@Autowired
	CurrecyService currecyService;
	
	private final CoinDeskApiClient coinDeskApiClient;
	
	final ZonedDateTime currentTime = ZonedDateTime.now();
	
	// 新增
	@PostMapping("/currency")
	public ResponseEntity<Currency> createCurrency(@RequestBody Currency currency) {
		return ResponseEntity.ok().body(currecyService.createCurrency(currency));
	}
	
	//更新
	@PutMapping("/currency/{code}")
	public ResponseEntity<Currency> updateCurrency(@PathVariable String code , @RequestBody Currency currency) {
		currency.setCode(code);
		currency.setCreateTime(currentTime);
		return ResponseEntity.ok().body(currecyService.updateCurrency(currency));
	}
	//刪除
	@PostMapping("/currency/{code}")
	public ResponseEntity<String> deleteCurrency(@PathVariable String code ) {
		this.currecyService.deleteCurrency(code);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
	}

	// 取得特定幣別
	@GetMapping("/currency/{code}")
	public ResponseEntity<Currency> getCurrencyById(@PathVariable String code) {
		return ResponseEntity.ok().body(currecyService.getCurrencyById(code));
	}
	
	// 取得全部幣別
	@GetMapping("/currency")
	public ResponseEntity<List<Currency>> getAllCurrency(){
		return ResponseEntity.ok().body(currecyService.getAllCurrency());
	}

	// get api data
	@GetMapping("/coindesk")
	public ResponseEntity<CoinDeskData> readAirlineData() {
		return ResponseEntity.ok().body(coinDeskApiClient.readCoinDeskData());
	}

	// 新api
	@GetMapping("/newCoindesk")
	public ResponseEntity<NewCoinDeskData> newCoinDesk(){
		CoinDeskData data = coinDeskApiClient.readCoinDeskData();
		return ResponseEntity.ok().body(currecyService.newCoinDesk(data));
	}
	 

}
