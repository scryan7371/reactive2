package com.scottryan.handlers;

import com.scottryan.models.Person;
import com.scottryan.repositories.PersonRepository;
import org.springframework.http.codec.BodyInserters;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.ServerRequest;
import org.springframework.web.reactive.function.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Handler for Person.
 */
@Component
public class PersonHandler {

    private PersonRepository personRepository;

    PersonHandler(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public ServerResponse<Flux<Person>> all(ServerRequest request) {
        Flux<Person> flux = Flux.fromStream(personRepository.find());
        return ServerResponse.ok().body(BodyInserters.fromPublisher(flux, Person.class));
    }

    public ServerResponse<Mono<Person>> byId(ServerRequest request) {
        String id = request.pathVariable("id");
        Mono<Person> mono = Mono.fromFuture(personRepository.findById(id));
        return ServerResponse.ok().body(BodyInserters.fromPublisher(mono, Person.class));
    }
}
