package com.kazak.carrent.dto;

import java.time.LocalDate;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
public class PassportDataPostDto {

  private Integer id;

  @NotEmpty
  private String firstName;

  @NotEmpty
  private String lastName;

  @NotEmpty
  private String middleName;

  @NotEmpty
  private String sex;

  @Past
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate dateOfBirth;

  @NotEmpty
  private String passportNumber;

  @NotEmpty
  private String identificationNumber;

  @Past
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate dateOfIssue;

  @Future
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate dateOfExpiry;

  @NotEmpty
  private String placeOfBirth;

  @NotEmpty
  private String authority;

  @NotEmpty
  private String nationality;

  @NotEmpty
  private String registration;

}
