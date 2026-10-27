package com.wildev.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(
        name = "APP_USERS",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "UK_APP_USERS_EMAIL",
                        columnNames = "EMAIL"
                )
        }
)
public class User {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "app_users_seq"
    )
    @SequenceGenerator(
            name = "app_users_seq",
            sequenceName = "APP_USERS_SEQ",
            allocationSize = 1
    )
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100)
    @Column(name = "FULL_NAME", nullable = false, length = 100)
    private String fullName;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El correo no tiene un formato válido")
    @Size(max = 150)
    @Column(name = "EMAIL", nullable = false, length = 150)
    private String email;

    @JsonIgnore
    @Column(name = "PASSWORD_HASH", nullable = false, length = 100)
    private String passwordHash;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "USER_ROLE", nullable = false, length = 20)
    private Role role = Role.USER;

    @Column(name = "IS_ENABLED", nullable = false)
    private boolean enabled = true;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}