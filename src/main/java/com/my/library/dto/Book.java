package com.my.library.dto;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String name;
	String author;
	int quantity;
	double Price;
	boolean status;
	
	@Lob
	@Column(columnDefinition = "LONGBLOB")
	byte[] picture;
	
	@OneToMany(fetch = FetchType.EAGER)
	List<BookRecord> records;
}
