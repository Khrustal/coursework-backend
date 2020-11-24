package com.webapp.controllers;

import com.webapp.models.TrainingSession;
import com.webapp.models.User;
import com.webapp.models.Word;
import com.webapp.payload.request.AddToTrainingSessionRequest;
import com.webapp.payload.request.CreateSessionRequest;
import com.webapp.payload.request.WordRequest;
import com.webapp.payload.response.MessageResponse;
import com.webapp.repository.TrainingSessionRepository;
import com.webapp.repository.UserRepository;
import com.webapp.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*")
@PreAuthorize("hasRole('USER')")
@RequestMapping("/session")
public class TrainingSessionController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    WordRepository wordRepository;

    @Autowired
    TrainingSessionRepository trainingSessionRepository;

    @GetMapping("/get-all")
    public List<TrainingSession> getTrainingSessions(@RequestParam("username") String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username " + username));
        return trainingSessionRepository.findByUser(user);
    }

    @GetMapping("/get")
    public Set<Word> getSession(@RequestParam("id") Long id) {
        TrainingSession session = trainingSessionRepository.findById(id).orElseThrow();
        return session.getWords();
    }

    @PostMapping("/create")
    public ResponseEntity<?> createTrainingSession(@RequestBody CreateSessionRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with " + request.getUsername()));
        TrainingSession trainingSession = new TrainingSession(user, request.getName());
        trainingSessionRepository.save(trainingSession);

        return ResponseEntity.ok(new MessageResponse("Training session created"));
    }

    @PostMapping("/add")
    public ResponseEntity<?> addWord(@RequestBody AddToTrainingSessionRequest request) {
        TrainingSession trainingSession = trainingSessionRepository.findById(request.getSessionId())
                .orElseThrow();
        Word word = wordRepository.findByOriginal(request.getWord()).orElseThrow();
        Set<Word> words = trainingSession.getWords();
        words.add(word);
        trainingSession.setWords(words);
        trainingSessionRepository.save(trainingSession);

        return ResponseEntity.ok(new MessageResponse("Word added to session"));
    }
}
