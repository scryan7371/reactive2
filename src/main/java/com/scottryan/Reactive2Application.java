package com.scottryan;

import com.scottryan.models.Person;
import com.scottryan.repositories.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.stream.Stream;

@SpringBootApplication
public class Reactive2Application {

    public static void main(String[] args) {
        SpringApplication.run(Reactive2Application.class, args);
    }

    // Build sample data
    @Component
    class SampleDataCLR implements CommandLineRunner {

        private PersonRepository personRepository;

        SampleDataCLR(PersonRepository personRepository) {
            this.personRepository = personRepository;
        }

        @Override
        public void run(String... args) throws Exception {
            Stream.of("Scott", "John", "Steve", "Max", "Stephanie").forEach(firstName -> {
                personRepository.save(new Person(firstName, "Ryan", new Random().nextInt(100)));
            });
            personRepository.find().forEach(System.out::println);
        }
    }
}
