package com.example.Quiz.Service;


import com.example.Quiz.DAO.QuestionDAO;
import com.example.Quiz.Model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDAO questionDAO;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionDAO.findAll(), HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionByCategory(String category) {
        try {
            return new ResponseEntity<>(questionDAO.findByCategory(category), HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }



    public ResponseEntity<String>  addQuestion(Question question) {

        try {
            questionDAO.save(question);
            return new ResponseEntity<>("Question added successfully", HttpStatus.CREATED);        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new String(" "), HttpStatus.BAD_REQUEST);


    }
}
