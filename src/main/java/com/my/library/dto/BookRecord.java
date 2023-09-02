package com.my.library.dto;

import java.time.LocalDate;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Component
@Entity
@Data
@Scope("prototype")
public class BookRecord {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	LocalDate issueDate;
	LocalDate returnDate;
	double fine;

	@ManyToOne
	Book book;

	@ManyToOne
	Student student;
}