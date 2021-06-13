package com.kazak.carrent.model.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "passport_data")
public class PassportData {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @Column(name = "first_name", nullable = false)
  private String firstName;

  @Column(name = "last_name", nullable = false)
  private String lastName;

  @Column(name = "middle_name", nullable = false)
  private String middleName;

  @Column(name = "sex", nullable = false)
  private String sex;

  @Column(name = "date_of_birth", nullable = false)
  private LocalDate dateOfBirth;

  @Column(name = "passport_number", nullable = false, unique = true)
  private String passportNumber;

  @Column(name = "identification_number", nullable = false, unique = true)
  private String identificationNumber;

  @Column(name = "date_of_issue", nullable = false)
  private LocalDate dateOfIssue;

  @Column(name = "date_of_expiry", nullable = false)
  private LocalDate dateOfExpiry;

  @Column(name = "place_of_birth", nullable = false)
  private String placeOfBirth;

  @Column(name = "authority", nullable = false)
  private String authority;

  @Column(name = "nationality", nullable = false)
  private String nationality;

  @Column(name = "registration", nullable = false)
  private String registration;

}
