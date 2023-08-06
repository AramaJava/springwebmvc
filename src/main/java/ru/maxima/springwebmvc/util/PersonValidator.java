package ru.maxima.springwebmvc.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.maxima.springwebmvc.dao.PersonDAO;
import ru.maxima.springwebmvc.entity.Person;

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
        // посмотреть, есть ли в базе данных человек с такой же почтой
        if (personDAO.show(person.getEmail()).isPresent()) {
            errors.rejectValue("email", "", "Эта почта уже используется!");
        }
    }
}
