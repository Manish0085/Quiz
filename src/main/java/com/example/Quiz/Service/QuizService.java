package com.example.Quiz.Service;


import com.example.Quiz.DAO.QuestionDAO;
import com.example.Quiz.DAO.QuizDAO;
import com.example.Quiz.Model.Question;
import com.example.Quiz.Model.QuestionWrapper;
import com.example.Quiz.Model.Quiz;
import com.example.Quiz.Model.Responses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDAO quizDAO;

    @Autowired
    QuestionDAO questionDAO;
    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {


        List<Question> questions = questionDAO.findRandomQuestionByCategory(category, numQ);
        Quiz quiz = new Quiz();

        quiz.setTitle(title);
        quiz.setQuestions(questions);

        quizDAO.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.OK);


    }

    public ResponseEntity<List<QuestionWrapper>> getQuiaQuestions(Integer id) {

       Optional<Quiz> quiz =  quizDAO.findById(id);
       List<Question> questionFromDB = quiz.get().getQuestions();
       List<QuestionWrapper> questionForUser = new ArrayList<>();

       for (Question q : questionFromDB){
           QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
           questionForUser.add(qw);
       }

       return new ResponseEntity<>(questionForUser, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Responses> responses) {
        Quiz quiz = quizDAO.findById(id).get();
        List<Question> questions = quiz.getQuestions();
        int right = 0;
        int i =0;
        for (Responses response: responses) {
            if(response.getResponse().equals(questions.get(i).getRightAnswer())){
                right++;
            }
            i++;
        }
        return new ResponseEntity<>(right, HttpStatus.OK);
    }
}
