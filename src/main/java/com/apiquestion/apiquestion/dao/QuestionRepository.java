package com.apiquestion.apiquestion.dao;

import com.apiquestion.apiquestion.documents.Question;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends ReactiveMongoRepository<Question, String>, QuestionCustomRepository<Question>{

}
