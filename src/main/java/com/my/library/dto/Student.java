package com.my.library.dto;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;

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
@Component
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String email;
	private long mobile;
	private String password;
	private String gender;
	private LocalDate dob;
	private String token;
	private boolean status;

	@Lob
	@Column(columnDefinition = "LONGBLOB")
	private byte[] picture;

	@OneToMany(fetch = FetchType.EAGER)
	List<BookRecord> records;
}
