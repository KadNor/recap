package com.frequentis.training;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class NameVerifier {

    public List<Person> verifyNameContains(final String pattern, final List<Person> persons) {
        return persons.stream()
                      .filter(person -> person.getName().contains(pattern))
                      .collect(Collectors.toList());
    }

    public double avarageAge(final List<Person> persons) {
        return persons.stream()
                      .mapToInt(Person::getAge)
                      .average().orElse(0.0);
    }

    public Optional<Person> youngestPerson(final List<Person> persons) {
        return persons.stream().sorted().findFirst();
    }

    public boolean isMajor(final String name, final List<Person> persons) {
        return persons.stream()
                      .filter(p -> p.getName().equals(name))
                      .findFirst()
                      .filter(person1 -> person1.getAge() >= 18)
                      .isPresent();
    }
}
