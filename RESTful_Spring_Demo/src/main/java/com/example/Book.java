package com.example;

public class Book {
	private long id;
	private String ISBN;
	private String Name;
	public Book(long id, String iSBN, String name) {
		super();
		this.id = id;
		ISBN = iSBN;
		Name = name;
	}
	public String getName() {
		return Name;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public void setName(String name) {
		Name = name;
	}
	public long getId() {
		return id;
	}
	public String getISBN() {
		return ISBN;
	}
}
