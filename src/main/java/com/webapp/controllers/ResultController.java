package com.webapp.controllers;

import com.webapp.models.Answer;
import com.webapp.models.Result;
import com.webapp.models.TrainingSession;
import com.webapp.models.Word;
import com.webapp.payload.request.CreateResultRequest;
import com.webapp.payload.response.MessageResponse;
import com.webapp.repository.AnswerRepository;
import com.webapp.repository.ResultRepository;
import com.webapp.repository.TrainingSessionRepository;
import com.webapp.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static java.lang.StrictMath.round;

@RestController
@CrossOrigin(origins = "*")
@PreAuthorize("hasRole('USER')")
@RequestMapping("/session/result")
public class ResultController {

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    ResultRepository resultRepository;

    @Autowired
    WordRepository wordRepository;

    @Autowired
    TrainingSessionRepository trainingSessionRepository;

    @GetMapping("/get")
    public Result getResult(@RequestParam("id") Long id) {
        return resultRepository.findById(id).orElseThrow();
    }
    @GetMapping("/get-all")
    public List<Result> getAllResults(@RequestParam("id") Long id) {
        return resultRepository.findBySessionId(id);
    }

    @PostMapping("/create")
    public Long createResult(@RequestBody CreateResultRequest request) {
        Long sessionId = request.getSessionId();
        TrainingSession session = trainingSessionRepository.findById(request.getSessionId()).orElseThrow();
        ArrayList<Word> words = request.getWords();
        ArrayList<String> answers = request.getAnswers();
        double size = words.size();
        double rightAnswers = 0;
        if(size == answers.size()){
            ArrayList<Answer> ans = new ArrayList<>();
            for(int i=0; i<size; i++){
                Word word = words.get(i);
                boolean isCorrect = word.getTranslation().equals(answers.get(i));
                if(isCorrect) rightAnswers++;
                Answer answer = new Answer(word, answers.get(i), isCorrect);
                answerRepository.save(answer);
                ans.add(answer);
            }
            double rightAns = (rightAnswers / size)*100;

            Result result = new Result(session, sessionId, ans, rightAns, LocalDate.now(), LocalTime.now());
            resultRepository.save(result);
            return  result.getId();
        }
        return null;
    }
}
