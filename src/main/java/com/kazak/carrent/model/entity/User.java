package com.kazak.carrent.model.entity;

import com.kazak.carrent.annotation.UniqueEmail;
import com.kazak.carrent.annotation.UniquePhoneNumber;
import com.kazak.carrent.annotation.UniqueUsername;
import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @NotEmpty
  @Size
  @Pattern(regexp = "^[A-Za-z]{3,16}$")
  @UniqueUsername
  @Column(name = "username", nullable = false, unique = true)
  private String username;

  @NotEmpty
  @Size(min = 3, max = 255)
  @Pattern(regexp = "^(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{3,16}$")
  @Column(name = "password", nullable = false)
  private String password;

  @NotEmpty
  @Pattern(regexp = "([A-z0-9_.-]+)@([A-z0-9_.-]+).([A-z]{2,8})")
  @UniqueEmail
  @Column(name = "email", nullable = false)
  private String email;

  @NotEmpty
  @UniquePhoneNumber
  @Column(name = "phone_number", nullable = false, unique = true)
  private String phoneNumber;

  @OneToOne
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
