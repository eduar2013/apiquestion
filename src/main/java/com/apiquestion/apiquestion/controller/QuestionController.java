package com.apiquestion.apiquestion.controller;

import com.apiquestion.apiquestion.documents.Question;
import com.apiquestion.apiquestion.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/question")
@CrossOrigin(origins = "http://localhost:4200")
public class QuestionController {

    @Autowired
    QuestionService  questionService;

    @GetMapping
    public Flux<Question> findAll(){
       return questionService.findAll();
    }

    @GetMapping("/{numberQuestion}")
    public Mono<Question> findByNumber(@PathVariable int numberQuestion){
        return questionService.findByNumber(numberQuestion);
    }

    @PutMapping
    public void loadQuestion(){
        questionService.loadAll();
    }
}
