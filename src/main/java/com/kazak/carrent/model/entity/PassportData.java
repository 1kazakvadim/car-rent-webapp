package com.kazak.carrent.model.entity;

import com.kazak.carrent.annotation.UniqueIdentificationNumber;
import com.kazak.carrent.annotation.UniquePassportNumber;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@ToString(exclude={"user"})
@Table(name = "passport_data")
public class PassportData {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @OneToOne(mappedBy = "passportData", fetch = FetchType.LAZY)
  private User user;

  @NotEmpty
  @Column(name = "first_name", nullable = false)
  private String firstName;

  @NotEmpty
  @Column(name = "last_name", nullable = false)
  private String lastName;

  @NotEmpty
  @Column(name = "middle_name", nullable = false)
  private String middleName;

  @NotEmpty
  @Column(name = "sex", nullable = false)
  private String sex;

  @Past
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @Column(name = "date_of_birth", nullable = false)
  private LocalDate dateOfBirth;

  @NotEmpty
  @UniquePassportNumber
  @Column(name = "passport_number", nullable = false, unique = true)
  private String passportNumber;

  @NotEmpty
  @UniqueIdentificationNumber
  @Column(name = "identification_number", nullable = false, unique = true)
  private String identificationNumber;

  @Past
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @Column(name = "date_of_issue", nullable = false)
  private LocalDate dateOfIssue;

  @Future
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @Column(name = "date_of_expiry", nullable = false)
  private LocalDate dateOfExpiry;

  @NotEmpty
  @Column(name = "place_of_birth", nullable = false)
  private String placeOfBirth;

  @NotEmpty
  @Column(name = "authority", nullable = false)
  private String authority;

  @NotEmpty(message = "nationality can`t be empty")
  @Column(name = "nationality", nullable = false)
  private String nationality;

  @NotEmpty(message = "registration can`t be empty")
  @Column(name = "registration", nullable = false)
  private String registration;

}
