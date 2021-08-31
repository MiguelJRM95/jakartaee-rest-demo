package com.restservice.jakarta.service;

import com.restservice.jakarta.model.Person;

import java.util.List;

public interface PersonService {
	Person save(Person p);
	Person update(Person p);
	List<Person> listAll();
	Person findById(Integer id);
	void delete(Integer id);
}
