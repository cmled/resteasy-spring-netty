package org.greg.resteasy.vo;

public class DemoName {

	String	name;
	Integer	id;

	public DemoName() {

	}

	public DemoName(int id, String name) {
		this.name = name;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
