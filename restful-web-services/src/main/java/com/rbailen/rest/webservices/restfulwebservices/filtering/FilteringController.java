package com.rbailen.rest.webservices.restfulwebservices.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {
	
	/* ------------------- FILTROS ESTATICOS ------------------- */
	@GetMapping("/filtering")
	public SomeBean retrieveSomeBean(){
		return new SomeBean("value1", "value2", "value3");
	}
	
	@GetMapping("/filtering-list")
	public List<SomeBean> retrieveListOfSomeBeans(){
		return Arrays.asList(new SomeBean("value1", "value2", "value3"),
				new SomeBean("value1", "value2", "value3"));
	}
	
	/* ------------------- FILTROS DINAMICOS ------------------- */
	@GetMapping("/filtering-dynamic")
	public MappingJacksonValue retrieveSomeBeanDynamically(){
		SomeBean someBean = new SomeBean("value1", "value2", "value3");

		// La clase MappingJacksonValue contiene los filtros
		MappingJacksonValue mapping = new MappingJacksonValue(someBean);

		// Muestra field1 y field2
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
				.filterOutAllExcept("field1", "field2");

		FilterProvider filters = new SimpleFilterProvider().addFilter(
				"SomeBeanFilter", filter);

		mapping.setFilters(filters);

		return mapping;
	}
	
	@GetMapping("/filtering-list-dynamic")
	public MappingJacksonValue retrieveListOfSomeBeansDynamically(){
		List<SomeBean> list = Arrays.asList(new SomeBean("value1", "value2", "value3"),
				new SomeBean("value1", "value2", "value3"));

		// La clase MappingJacksonValue contiene los filtros
		MappingJacksonValue mapping = new MappingJacksonValue(list);

		// Muestra field1 y field2
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
				.filterOutAllExcept("field1", "field2");

		FilterProvider filters = new SimpleFilterProvider().addFilter(
				"SomeBeanFilter", filter);

		mapping.setFilters(filters);

		return mapping;
	}
	
}
