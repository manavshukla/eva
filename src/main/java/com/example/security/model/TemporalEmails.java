package com.example.security.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "temporalEmails")
public class TemporalEmails {

    @Id
    @GeneratedValue
    private long id;
    private String email;
    //@GenericGenerator(name = "code", strategy = "com.example.security.resource.CustomFourDigitsGenerator")
    //@GeneratedValue(generator = "code")
    private int code;
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Instant dateTime;

}