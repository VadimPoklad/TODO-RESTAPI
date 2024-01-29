package com.petproject.todo.repository.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ConverterRegistration;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.NumericBooleanConverter;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ConverterRegistration(converter = NumericBooleanConverter.class)
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Convert(converter = NumericBooleanConverter.class)
    private Boolean isComplete;

    @Column(nullable = false, length = 50)
    private String text;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime createDate;

    @Column(nullable = false)
    @UpdateTimestamp
    private LocalDateTime updateDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private TaskList taskList;

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", isComplete=" + isComplete +
                ", text='" + text + '\'' +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                '}';
    }
}
