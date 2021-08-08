package com.kazak.carrent.dto;

import com.kazak.carrent.annotation.UniqueEmail;
import com.kazak.carrent.annotation.UniquePhoneNumber;
import com.kazak.carrent.annotation.UniqueUsername;
import com.kazak.carrent.model.entity.UserRole;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPostDto {

  private Integer id;

  @NotEmpty
  @Size(min = 3, max = 16)
  @Pattern(regexp = "^[A-Za-z]{3,16}$")
  @UniqueUsername
  private String username;

  @NotEmpty
  @Size(min = 3, max = 255)
  private String password;

  @NotEmpty
  @UniqueEmail
  private String email;

  @NotEmpty
  @UniquePhoneNumber
  private String phoneNumber;

  private UserRole userRole;

  private String status;

}
