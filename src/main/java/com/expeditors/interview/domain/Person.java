package com.expeditors.interview.domain;

import java.util.Comparator;

public class Person implements Comparable<Person>{
	
	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private String state;
	private int age;
	public record SameHouseHold(String address, String city, String state) {};
	
	
	public Person(String firstName, String lastName, String address, String city, String state, int age) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.state = state;
		this.age = age;
	}
	
    public Person(String line) {

    	String[] fields = line.split("\",\"");
        this.firstName = readFields(fields[0]);
        this.lastName = readFields(fields[1]);
        this.address = readFields(fields[2]);
        this.city = readFields(fields[3]);
        this.state = readFields(fields[4]);
        this.age = Integer.parseInt(readFields(fields[5]));
    }
    
    private String readFields(String field) {
    	
    	return field
    			  .replaceAll("\"", "")
                  .replaceAll(",", "")
                  .replaceAll("\\.", "")
                  .trim();
    }
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public int compareTo(Person person) {
		return Comparator.comparing(Person::getLastName).thenComparing(Person::getFirstName).compare(this, person);
	}
	
	@Override
	public String toString() {
		return firstName +","+lastName+","+address+","+city+","+state+","+age;
	}
	
	

}
