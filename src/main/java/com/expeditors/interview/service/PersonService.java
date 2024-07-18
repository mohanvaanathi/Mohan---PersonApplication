package com.expeditors.interview.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.expeditors.interview.domain.Person;


public interface PersonService {

	//Load data from input file
	List<Person> loadDataFromFile(String fileName) throws Exception;

	//Getting each household and number of occupants based on address, city, state.
	Map<Person.SameHouseHold, List<Person>> getHouseHoldOccupants(List<Person> personList);
	
	//getting persons who are older than 18 years.
	List<Person> getOlderThanEighteenOccupants(List<Person> personList);
	
}
