package com.apiquestion.apiquestion.dao;

import com.apiquestion.apiquestion.documents.Sequences;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SequencesRepository extends ReactiveMongoRepository<Sequences,String> {
}
