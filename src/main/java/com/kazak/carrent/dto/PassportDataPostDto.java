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

  @NotEmpty(message = "first name can`t be empty")
  private String firstName;

  @NotEmpty(message = "last name can`t be empty")
  private String lastName;

  @NotEmpty(message = "middle name can`t be empty")
  private String middleName;

  @NotEmpty(message = "sex can`t be empty")
  private String sex;

  @Past(message = "date of birth can`t be in future")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate dateOfBirth;

  @NotEmpty(message = "passport number can`t be empty")
  private String passportNumber;

  @NotEmpty(message = "identification number can`t be empty")
  private String identificationNumber;

  @Past(message = "date of issue can`t be in future")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate dateOfIssue;

  @Future(message = "date of expiry can`t be in past")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate dateOfExpiry;

  @NotEmpty(message = "place of birth can`t be empty")
  private String placeOfBirth;

  @NotEmpty(message = "authority can`t be empty")
  private String authority;

  @NotEmpty(message = "nationality can`t be empty")
  private String nationality;

  @NotEmpty(message = "registration can`t be empty")
  private String registration;

}
