package com.apiquestion.apiquestion.dao;

import com.apiquestion.apiquestion.documents.Question;
import reactor.core.publisher.Mono;

public interface QuestionCustomRepository<T> {

    Mono<Question> findByNumber(int numberQuestion);
}
