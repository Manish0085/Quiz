package com.example.Quiz.Controller;

import com.example.Quiz.Model.Question;
import com.example.Quiz.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Question")
public class QuestionController {

    @Autowired
    QuestionService questionService;
    @GetMapping("/getQuestion")
    public ResponseEntity<List<Question>> getAllQuestions(){
            return questionService.getAllQuestions();
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable String category){

        return questionService.getQuestionByCategory(category);
    }


    @PostMapping("/add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }
}
