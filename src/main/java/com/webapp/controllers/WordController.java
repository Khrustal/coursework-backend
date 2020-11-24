package com.webapp.controllers;

import com.webapp.models.User;
import com.webapp.models.Word;
import com.webapp.payload.request.CreateWordRequest;
import com.webapp.payload.request.WordRequest;
import com.webapp.payload.response.MessageResponse;
import com.webapp.repository.UserRepository;
import com.webapp.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@PreAuthorize("hasRole('USER')")
public class WordController {

    @Autowired
    private WordRepository wordRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/get")
    public List<Word> getAllWord(@RequestBody WordRequest request) throws UsernameNotFoundException{
        User user = userRepository.findByUsername(request.getWord())
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + request.getWord()));
        return wordRepository.findByUser(user);
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateWord(@RequestBody CreateWordRequest updateRequest) {
        if(!wordRepository.existsByOriginal(updateRequest.getOriginal())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: word not found"));
        }
        Word word = wordRepository.findByOriginal(updateRequest.getOriginal()).orElseThrow();
        word.setTranslation(updateRequest.getTranslation());
        wordRepository.save(word);
        return ResponseEntity.ok(new MessageResponse("Word updated"));
    }

    @PostMapping("/create")
    public ResponseEntity<?> createWord(@RequestBody CreateWordRequest createRequest) {
        if(wordRepository.existsByOriginal(createRequest.getOriginal())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: word is already exists!"));
        }
        User user = userRepository.findById(createRequest.getUserId())
                .orElseThrow(); //probably need to add something
        Word word = new Word(createRequest.getOriginal(), createRequest.getTranslation(), user);
        wordRepository.save(word);

        return ResponseEntity.ok(new MessageResponse("Word created"));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteWord(@RequestBody WordRequest wordRequest) {
        if(!wordRepository.existsByOriginal(wordRequest.getWord())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: word not found"));
        }
        Word word = wordRepository.findByOriginal(wordRequest.getWord()).orElseThrow();
        wordRepository.delete(word);

        return ResponseEntity.ok(new MessageResponse("Word deleted"));
    }
}
