package com.expeditors.interview;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.expeditors.interview.domain.Person;
import com.expeditors.interview.service.PersonService;

@SpringBootApplication
public class PersonApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(PersonApplication.class, args);
	}
	
	@Autowired
	PersonService personService;
	
	@Override
	public void run(String... args) throws Exception {
		
		//Load data from input file
		List<Person> personList = personService.loadDataFromFile("data.txt");

		//Getting each household and number of occupants based on address, city, state.
		System.out.println("Address and number of occupants");
		
		Map<Person.SameHouseHold, List<Person>> personInSameHouseHold = personService.getHouseHoldOccupants(personList);
		for(Map.Entry<Person.SameHouseHold, List<Person>> entry: personInSameHouseHold.entrySet()) {
			System.out.println(entry.getKey().address()+", "+ entry.getKey().city()+", "+entry.getKey().state() +" - "+ entry.getValue().size());
		}
		
		//getting persons who are older than 18 years.
		System.out.println("Name and address of person who are 18 years and older");
		List<Person> personOlderThanEighteen = personService.getOlderThanEighteenOccupants(personList);
		for(Person p : personOlderThanEighteen) {
			System.out.println(p.toString());
		}
		

		
	}
	
	

}
