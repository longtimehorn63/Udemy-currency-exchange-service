package com.udemy.microservices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.udemy.microservices.bean.ExchangeValue;
import com.udemy.microservices.bean.ExchangeValueRepository;

@RestController
public class CurrencyExchangeController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private ExchangeValueRepository repository;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue retreiveExchangeValue(@PathVariable String from, @PathVariable String to){
//		ExchangeValue exchangeValue = new ExchangeValue(1000L, from, to, BigDecimal.valueOf(65));
		System.out.println("Looking for from " + from + " to " + to);
		ExchangeValue exchangeValue = repository.findByFromAndTo(from, to);

		if (exchangeValue == null){
			System.out.println("exchangeValue from repo is null");
		}
		if (environment == null){
			System.out.println("environment is null");
		}
		String sPort = environment.getProperty("local.server.port");
		if (sPort == null){
			System.out.println("sPort is null");
		}
		
		int port = Integer.parseInt(sPort);
		if (exchangeValue == null){
			System.out.println("exchangeValue is null");
		}
		exchangeValue.setPort(port);
		
		logger.info("exchange {}",exchangeValue);
		
		return exchangeValue;
		
	}

}
