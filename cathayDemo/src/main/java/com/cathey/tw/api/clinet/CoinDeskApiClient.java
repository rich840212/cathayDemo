package com.cathey.tw.api.clinet;





import java.util.Collections;
import java.util.List;

import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.GetMapping;

import com.cathey.tw.api.model.CoinDeskData;

import feign.codec.Decoder;




@FeignClient(url = "${coindesk.api.url}",value = "${coindesk.api.name}",configuration = CoinDeskApiClient.FeignTestConfiguration.class)
public interface CoinDeskApiClient {
	@GetMapping(value = "/" )
    public CoinDeskData readCoinDeskData();
	
	// 處理api 回傳 Content-Type application/javascript 
	// 處理方式待確認
	class FeignTestConfiguration {
        @Bean
        public Decoder textPlainDecoder() {
            return new SpringDecoder(() -> new HttpMessageConverters(new CustomMappingJackson2HttpMessageConverter()));
        }
    }

    class CustomMappingJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {
        @Override
        public void setSupportedMediaTypes(List<MediaType> supportedMediaTypes) {
        	super.setSupportedMediaTypes(Collections.singletonList(new MediaType("application", "javascript")));
        }
    }
}
