package com.study.ms.currencyexhange;

import org.springframework.data.repository.CrudRepository;

public interface ExchangeValueRepository  extends CrudRepository<ExchangeValue, Long>{

	ExchangeValue findByFromAndTo(String from, String to);

}
