package com.petproject.todo.repository.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ConverterRegistration;
import org.hibernate.type.NumericBooleanConverter;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ConverterRegistration(converter = NumericBooleanConverter.class)
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30, unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "user")
    private List<TaskList> taskLists;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> authorities;

    @Column(nullable = false)
    @Convert(converter = NumericBooleanConverter.class)
    private boolean accountNonExpired;

    @Column(nullable = false)
    @Convert(converter = NumericBooleanConverter.class)
    private boolean accountNonLocked;

    @Column(nullable = false)
    @Convert(converter = NumericBooleanConverter.class)
    private boolean credentialsNonExpired;

    @Column(nullable = false)
    @Convert(converter = NumericBooleanConverter.class)
    private boolean enabled;
}

