package com.example.Quiz.Model;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    @ManyToMany
    private List<Question> questions;

}
