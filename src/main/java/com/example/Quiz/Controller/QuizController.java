package com.example.Quiz.Controller;


import com.example.Quiz.Model.QuestionWrapper;
import com.example.Quiz.Model.Responses;
import com.example.Quiz.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.soap.AddressingFeature;
import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {


    @Autowired
    QuizService quizService;
    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title){
        return quizService.createQuiz(category, numQ, title);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id){
        return quizService.getQuiaQuestions(id);
    }

    @GetMapping("/submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Responses> responses){
        return quizService.calculateResult(id, responses);
    }
}
