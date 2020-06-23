package com.study.ms.currencyconversion;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CurrencyConversionController {
	private Logger logger =LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CurrencyExchangeServiceProxy proxy;
	@GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean getMultiple(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {

		Map<String, String> uriVariable = new HashMap<>();
		uriVariable.put("from", from);
		uriVariable.put("to", to);
		ResponseEntity<CurrencyConversionBean> r = new RestTemplate().getForEntity(
				"http://localhost:8101/currency-exchange/from/{from}/to/{to}", CurrencyConversionBean.class, uriVariable);
		CurrencyConversionBean currencyConversionBean = r.getBody();

		return new CurrencyConversionBean(currencyConversionBean.getId(), 
				currencyConversionBean.getFrom(),
				currencyConversionBean.getTo(), 
				currencyConversionBean.getConversionMultiple(),
				quantity, 
				quantity.multiply(currencyConversionBean.getConversionMultiple()),
						currencyConversionBean.getPort());

	}
	@GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean getMultipleFeign(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {

		CurrencyConversionBean currencyConversionBean=proxy.retrieveExchange(from, to);
		logger.info("{}", currencyConversionBean);
		return new CurrencyConversionBean(currencyConversionBean.getId(), 
				currencyConversionBean.getFrom(),
				currencyConversionBean.getTo(), 
				currencyConversionBean.getConversionMultiple(),
				quantity, 
				quantity.multiply(currencyConversionBean.getConversionMultiple()),
						currencyConversionBean.getPort());

	}
}
