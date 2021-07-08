package com.kazak.carrent.model.entity;

import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @NotEmpty(message = "username can`t be empty")
  @Size(min = 3, max = 16, message = "username should be between 3 and 16 characters")
  @Pattern(regexp = "^[A-Za-z]{3,16}$", message = "username should contains latin characters only")
  @Column(name = "username", nullable = false, unique = true)
  private String username;

  @NotEmpty(message = "password can`t be empty")
  @Size(min = 3, max = 16, message = "password should be between 3 and 16 characters")
//  @Pattern(regexp = "^(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{3,16}$",
//      message = "password should contains at least one lowercase letter, one number and one special character (@$!%*?&)")
  @Column(name = "password", nullable = false)
  private String password;

  @NotEmpty(message = "email can`t be empty")
  @Pattern(regexp = "([A-z0-9_.-]+)@([A-z0-9_.-]+).([A-z]{2,8})")
  @Column(name = "email", nullable = false)
  private String email;

  @NotEmpty(message = "phone number can`t be empty")
  @Column(name = "phone_number", nullable = false, unique = true)
  private String phoneNumber;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name="passport_data_id")
  private PassportData passportData;

  @ManyToOne
  @JoinColumn(name = "user_role_id", nullable = false)
  private UserRole userRole;

  @Column(name = "status", nullable = false)
  private String status = "ACTIVE";

  @Column(name = "created_at", nullable = false)
  private LocalDate createdAt = LocalDate.now();

}
