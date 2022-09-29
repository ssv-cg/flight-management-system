package com.flights.controller;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flights.bean.Flight;
import com.flights.service.FlightService;

@RestController
@RequestMapping("api")
public class FlightController {
	@Autowired
	FlightService flightservice;
	
	
	@PostMapping("addflight")
	public Flight addFlight(@RequestBody Flight f)
	{
		Flight f1=flightservice.addFlight(f);
		
		return f1;
		
	}
	@PutMapping(path="/updateflight")
	public Flight modifyFlight(@RequestBody Flight f) 
	{
		Flight f1=flightservice.modifyFlight(f);
		
	
		return f1;
	}
	@GetMapping("viewflightbyflightnumber/{flightNumber}")
	public Flight viewFlight(@PathVariable BigInteger flightNumber)
	{
		Flight f2=flightservice.viewFlight(flightNumber);
		return f2;
	}
	
	@GetMapping(path="viewflight")
	public List<Flight> viewFlight()
	{
		List<Flight> lf=flightservice.viewFlight();
		return lf;	
	}
	
	@DeleteMapping(path="/deleteflight/{flightNumber}") // variable name 
	public void deleteFlight(@PathVariable BigInteger flightNumber)
	{
		flightservice.deleteFlight(flightNumber);
		
	}
	
	
	
	
}
