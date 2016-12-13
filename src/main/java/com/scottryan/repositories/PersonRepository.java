package com.scottryan.repositories;

import com.scottryan.models.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

/**
 * Repository to interact with People.
 */
public interface PersonRepository extends MongoRepository<Person, String> {

    CompletableFuture<Person> findById(String id);

    @Query("{}")
    Stream<Person> find();
}
