package com.expeditors.interview.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.expeditors.interview.domain.Person;


@Service
public class PersonServiceImpl implements PersonService {
	
	@Autowired
	ApplicationContext ctx;

	@Override
	public Map<Person.SameHouseHold, List<Person>> getHouseHoldOccupants(List<Person> personList) {
		
		Map<Person.SameHouseHold, List<Person>> personInSameHouseHold = personList.stream().collect(Collectors.groupingBy(person -> 
		                            new Person.SameHouseHold(person.getAddress().toLowerCase(), person.getCity().toLowerCase(), person.getState().toLowerCase())
		                            ));
		
		
		return personInSameHouseHold;
	}

	@Override
	public List<Person> getOlderThanEighteenOccupants(List<Person> personList) {
		
		List<Person> personOlderThanEighteen = personList.stream()
											.filter(p -> p.getAge() > 18 )
											.sorted((p1,p2) -> p1.compareTo(p2))
											.collect(Collectors.toList());
				                               
		
		return personOlderThanEighteen;
	}
	
	@Override
	public List<Person> loadDataFromFile(String fileName) throws Exception {
		
		//get the input data from a text file.
		List<String> strInput = getInput("/data.txt");

		//convert each line from text file into a person object
		List<Person> personList = strInput.stream().map(Person::new).collect(Collectors.toList());
		
		return personList;
	}
	
	private List<String> getInput(String fileName) throws Exception {
		
		BufferedReader bufferedReader = null;
		try {
			
			InputStream inputStream = getClass().getResourceAsStream(fileName);
		    bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		    return bufferedReader.lines().collect(Collectors.toList());
			
		}catch(Exception e) {
			throw e;
		}finally {
			bufferedReader.close();
		}
		
	}

	
	

}
