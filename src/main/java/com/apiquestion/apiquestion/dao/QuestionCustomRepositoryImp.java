package com.apiquestion.apiquestion.dao;

import com.apiquestion.apiquestion.documents.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import reactor.core.publisher.Mono;

public class QuestionCustomRepositoryImp implements QuestionCustomRepository{

    @Autowired
    private ReactiveMongoTemplate mongoTemplate;

    @Override
    public Mono<Question> findByNumber(int numberQuestion) {
        Query query = new Query(Criteria.where("number").is(numberQuestion));
        return mongoTemplate.findOne(query,Question.class);
    }
}
