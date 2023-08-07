package ru.maxima.springwebmvc.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.maxima.springwebmvc.dao.PersonDAO;
import ru.maxima.springwebmvc.entity.Person;

import java.util.Optional;

/**
 * @author AramaJava 05.08.2023
 */
@Component
public class PersonValidator implements Validator {

    private final PersonDAO personDAO;

    @Autowired
    public PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        // посмотреть, есть ли в базе данных человек с такой же почтой и с id отличным от текущего
        Optional<Person> founded = personDAO.show(person.getEmail());
        if (founded.isPresent()) {
            Person person1 = founded.get();
            if (person1.getId() != person.getId()) {
                errors.rejectValue("email", "", "Эта почта уже используется!");
            }
        }
    }
}
