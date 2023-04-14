package com.gdu.app01.xml05;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Person {
	private List<String> hobbies;
	private Set<String> address;
	private Map<String, String> friends;
	public List<String> getHobbies(){
		return hobbies;
	}
	// default constructor
	
	// method (getter, setter)
	public Set<String> getAddress() {
		return address;
	}
	public void setAddress(Set<String> address) {
		this.address = address;
	}
	public Map<String, String> getFriends() {
		return friends;
	}
	public void setFriends(Map<String, String> friends) {
		this.friends = friends;
	}
	public void setHobbies(List<String> hobbies) {
		this.hobbies = hobbies;
	}
		
}
