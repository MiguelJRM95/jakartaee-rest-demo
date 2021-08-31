package com.restservice.jakarta.service;

import com.restservice.jakarta.model.Person;
import jakarta.inject.Named;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Named
public class PersonServiceImp implements PersonService {
	private static List<Person> list = new ArrayList<>();

	@Override
	public Person save(Person p) {
		list.add(p);
		return p;
	}

	@Override
	public Person update(Person p) {
		list = list.stream().map(e -> e.getId().equals(p.getId()) ? p : e)
				.collect(Collectors.toList());
		return p;
	}

	@Override
	public List<Person> listAll() {
		return list;
	}

	@Override
	public Person findById(Integer id) {
		return list.stream().filter(e -> e.getId().equals(id)).findAny().orElse(null);
	}

	@Override
	public void delete(Integer id) {
		list.removeIf(e -> e.getId().equals(id));
	}
}
