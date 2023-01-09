package com.cathey.tw.api.service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cathey.tw.api.exception.ResourceNotFoundException;
import com.cathey.tw.api.model.CoinDeskBpi;
import com.cathey.tw.api.model.CoinDeskData;
import com.cathey.tw.api.model.Currency;
import com.cathey.tw.api.model.NewCoinDeskData;
import com.cathey.tw.api.model.NewCurrency;
import com.cathey.tw.api.repostory.CurrencyRepostory;



@Service
public class CurrencyServiceImpl implements CurrecyService{
	
    @Autowired
    private CurrencyRepostory currencyRepostory;
    
	
	@Override
	public Currency createCurrency(Currency curreccy){
		Optional<Currency> currencyDb = currencyRepostory.findByCode(curreccy.getCode());
		if (!currencyDb.isPresent()) {
			return this.currencyRepostory.save(curreccy);
		} else {
			throw new ResourceNotFoundException("幣別代號重複 Code = " + curreccy.getCode());
		}
	}

	@Override
	public Currency updateCurrency(Currency curreccy) {
		Optional<Currency> currencyDb = currencyRepostory.findByCode(curreccy.getCode());
		if (currencyDb.isPresent()) {
			Currency currencyUpdate = currencyDb.get();
			currencyUpdate.setCode(curreccy.getCode());
			currencyUpdate.setCodeName(curreccy.getCodeName());
			currencyUpdate.setDescription(curreccy.getDescription());
			this.currencyRepostory.save(currencyUpdate);
			return currencyUpdate;
			
		} else {
			throw new ResourceNotFoundException("資料不存在 id = " + curreccy.getId());
		}
		
	}

	@Override
	public void deleteCurrency(String code) {
		Optional<Currency> currencyDb = currencyRepostory.findByCode(code);
		if (currencyDb.isPresent()) {
			this.currencyRepostory.delete(currencyDb.get());
		} else {
			throw new ResourceNotFoundException("資料不存在 code = " + code);
		}
	}

	@Override
	public Currency getCurrencyById(String code) {
		Optional<Currency> currencyDb = currencyRepostory.findByCode(code);
		if (currencyDb.isPresent()) {
			return currencyDb.get();
		} else {
			throw new ResourceNotFoundException("資料不存在  code= " + code);
		}
	}

	@Override
	public List<Currency> getAllCurrency() {
		List<Currency> list = this.currencyRepostory.findAll();
		if (list.size()>0) {
			return list;
		} else {
			throw new ResourceNotFoundException("資料不存在");
		}
	}

	@Override
	public NewCoinDeskData newCoinDesk(CoinDeskData data) {
		// 宣告 新api物件
		NewCoinDeskData newData = new NewCoinDeskData();
		List <NewCurrency> list = new ArrayList<NewCurrency>();
		for (String key: data.getBpi().keySet()) {
			CoinDeskBpi bpi = data.getBpi().get(key);
			Optional<Currency> currency = currencyRepostory.findByCode(key);
			String codeName ="";
			if (currency.isPresent()) {
				codeName = currency.get().getCodeName();
			}
			NewCurrency newCurrency = NewCurrency.builder().code(bpi.getCode()).codeName(codeName)
					.rate(bpi.getRate()).rate_Float(bpi.getRate_float()).build();
			list.add(newCurrency);
		}
		newData.setCurrency(list);
		newData.setUpDateTime(stringToDateTime(data.getTime().get("updatedISO")));
		return newData;
	}
	public ZonedDateTime stringToDateTime(String datetime) {
		return ZonedDateTime.parse(datetime);
	}
}
