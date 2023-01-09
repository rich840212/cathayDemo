package com.cathey.tw.api.service;

import java.util.List;

import com.cathey.tw.api.model.CoinDeskData;
import com.cathey.tw.api.model.Currency;
import com.cathey.tw.api.model.NewCoinDeskData;


public interface CurrecyService {
	
	// 新增幣別對應表資料 API。
	Currency createCurrency(Currency curreccy);
	// 叫更新幣別對應表資料 API，並顯示其內容
	Currency updateCurrency(Currency curreccy);
	// 刪除幣別對應表資料 API
	void deleteCurrency(String code);
	// 取得
	Currency getCurrencyById(String code);
	// 取得全部
	List<Currency> getAllCurrency();
	// 新 API 
	NewCoinDeskData newCoinDesk(CoinDeskData data);
	
	
}
