package com.study.ms.currencyexhange;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CurrencyExchangeServiceController {
	private Logger logger =LoggerFactory.getLogger(this.getClass());
	@Autowired
	private Environment environment;
	@Autowired
	private ExchangeValueRepository exchangeValueRepository;

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue retrieveExchange(@PathVariable String from, @PathVariable String to) {
		ExchangeValue exhangeValue= exchangeValueRepository.findByFromAndTo(from, to);
		exhangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		logger.info("{}", exhangeValue);
		return exhangeValue;
	}
}
