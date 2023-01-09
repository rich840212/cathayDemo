package com.cathey.tw.api;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.cathey.tw.api.model.Currency;
import com.cathey.tw.api.repostory.CurrencyRepostory;
import com.cathey.tw.api.service.CurrecyService;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
class CathayDemoApplicationTests {
	@Autowired
	CurrencyRepostory res;
	@Autowired
	CurrecyService currencyService;
	@Autowired
	MockMvc mvc ;
	
	@Test
	// 1. 測試呼叫查詢幣別對應表資料 API，並顯示其內容。
	public void TestDemo1() throws Exception {
		// giving
		List <Currency> list = new ArrayList<>();
		list.add(Currency.builder().code("USD").codeName("美金").description("美國").build());
		list.add(Currency.builder().code("GBP").codeName("英鎊").description("英國").build());
		list.add(Currency.builder().code("TWD").codeName("台幣").description("台灣").build());
		res.saveAll(list);
		// action
		ResultActions responseActions = mvc.perform(MockMvcRequestBuilders.get("/currency").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(""));
		//then
		responseActions.andExpect(status().isOk());
		responseActions.andReturn().getResponse().getContentAsString();
		System.out.println(responseActions.andReturn().getResponse().getContentAsString());
	}
	@Test
	// 2. 測試呼叫新增幣別對應表資料 API。
	public void TestDemo2() throws Exception {
		// giving
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code", "EUR");
		jsonObject.put("codeName", "歐元");
		jsonObject.put("description", "歐洲");
		// action
		ResultActions responseActions = mvc.perform(MockMvcRequestBuilders.post("/currency").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(String.valueOf(jsonObject)));
		//then
		responseActions.andExpect(status().isOk());
		responseActions.andReturn().getResponse().getContentAsString();
	}
	@Test
	// 3. 測試呼叫更新幣別對應表資料 API，並顯示其內容。
	public void TestDemo3() throws Exception {
		// giving
		String code = "EUR";
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("codeName", "歐元(更正用)");
		jsonObject.put("description", "歐洲(更正用)");
		// action 
	    ResultActions responseActions = mvc.perform(MockMvcRequestBuilders.put("/currency/"+code).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(String.valueOf(jsonObject)));
	    // then
	    responseActions.andExpect(status().isOk());
		responseActions.andReturn().getResponse().getContentAsString();
		System.out.println(responseActions.andReturn().getResponse().getContentAsString());
	}
	@Test
	// 4. 測試呼叫刪除幣別對應表資料 API。
	public void TestDemo4() throws Exception {
		//giving
		String code = "TWD";
		// action
		ResultActions responseActions = mvc.perform(MockMvcRequestBuilders.post("/currency/"+code).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON));
		//then
		responseActions.andExpect(status().isNoContent());
		responseActions.andReturn().getResponse().getContentAsString();
	}
	@Test
	// 5. 測試呼叫 coinDesk API，並顯示其內容
	public void TestDemo5() throws Exception {
		// giving
		// action
		ResultActions responseActions = mvc.perform(MockMvcRequestBuilders.get("/coindesk")
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON));
		//then
		responseActions.andExpect(status().isOk());
		System.out.println(responseActions.andReturn().getResponse().getContentAsString());

	}
	@Test
	// 6. 測試呼叫資料轉換的 API，並顯示其內容
	public void TestDemo6() throws Exception {
		// giving
		// action
		ResultActions responseActions = mvc.perform(MockMvcRequestBuilders.get("/newCoindesk")
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON));
		// then
		responseActions.andExpect(status().isOk());
		System.out.println(responseActions.andReturn().getResponse().getContentAsString());
	}

}
