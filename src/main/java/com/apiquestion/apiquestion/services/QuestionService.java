package com.apiquestion.apiquestion.services;

import com.apiquestion.apiquestion.documents.Question;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface QuestionService {

    public Flux<Question> findAll();

    public Mono<Question> findByNumber(int numberQuestion);

    public void loadAll();
}
