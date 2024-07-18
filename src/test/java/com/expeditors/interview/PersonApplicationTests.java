package com.expeditors.interview;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.expeditors.interview.domain.Person;
import com.expeditors.interview.service.PersonService;

@SpringBootTest
public class PersonApplicationTests {
	
	@Autowired 
	PersonService personService;	
	
	@Test
	public void PersonService_getOlderThanEighteenOccupants() {

		try {
			List<Person> personList = personService.loadDataFromFile("data.txt");
			assertEquals(6,personService.getOlderThanEighteenOccupants(personList).size());
		}catch(Exception e) {
			System.out.println("Error in loading data from file: "+e.getMessage());
		}
	}
	
	@Test
	public void PersonService_getHouseHoldOccumpants() {

		try {
			List<Person> personList = personService.loadDataFromFile("data.txt");
			Map<Person.SameHouseHold, List<Person>> resultMap = personService.getHouseHoldOccupants(personList);
			assertTrue(resultMap.containsKey(new Person.SameHouseHold("234 2nd ave","tacoma","fl")) && resultMap.get(new Person.SameHouseHold("234 2nd ave","tacoma","fl")).size()==1);
			assertTrue(resultMap.containsKey(new Person.SameHouseHold("234 2nd ave","seattle","wa")) && resultMap.get(new Person.SameHouseHold("234 2nd ave","seattle","wa")).size()==1);
			assertTrue(resultMap.containsKey(new Person.SameHouseHold("234 2nd ave","tacoma","wa")) && resultMap.get(new Person.SameHouseHold("234 2nd ave","tacoma","wa")).size()==2);
			assertTrue(resultMap.containsKey(new Person.SameHouseHold("123 main st","seattle","wa")) && resultMap.get(new Person.SameHouseHold("123 main st","seattle","wa")).size()==4);
			assertTrue(resultMap.containsKey(new Person.SameHouseHold("345 3rd blvd apt 200","seattle","wa")) && resultMap.get(new Person.SameHouseHold("345 3rd blvd apt 200","seattle","wa")).size()==2);
		}catch(Exception e) {
			System.out.println("Error in loading data from file: "+e.getMessage());
		}
	}


}
