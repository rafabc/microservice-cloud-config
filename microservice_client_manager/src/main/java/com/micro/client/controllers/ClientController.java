package com.micro.client.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.micro.client.dto.Car;
import com.micro.client.services.ClientService;

@RefreshScope
@Component
@RestController
@EnableDiscoveryClient
public class ClientController {

	@Autowired
	private ClientService service;

	@Value("${service.name}")
	String serviceName;

	@Value("${service.version}")
	String serviceVersion;


	@Value("${server.port}")
	String port;

	
	@RequestMapping("/")
	public String goHome() {

		return "index";
	}
	
	@RequestMapping("/config")
	public List<String> getConfig() {

		List<String> config = Arrays.asList("Service name: " + serviceName, 
						"Service version: " + serviceVersion,
						"Server port: " + port);
		return config;
	}

	@RequestMapping(value = "/cliente-car/{id}", method = RequestMethod.GET, produces = "application/json")
	public Car getCar(@PathVariable String id) {

		return service.getCar(id);
	}

}
