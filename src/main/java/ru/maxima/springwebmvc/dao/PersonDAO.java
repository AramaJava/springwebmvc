package ru.maxima.springwebmvc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.maxima.springwebmvc.entity.Person;

import java.util.List;
import java.util.Optional;

/**
 * @author AramaJava 26.07.2023
 */

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM Person",
                BeanPropertyRowMapper.newInstance(Person.class));

    }

    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?",
                        BeanPropertyRowMapper.newInstance(Person.class), id)
                .stream().findAny().orElse(null);

    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO person (, year_of_birth) VALUES (?, ?)",
                person.getFullname(),
                person.getYearOfBirth());
    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE person SET full_name=?, year_of_birth=? WHERE id=?",
                updatedPerson.getFullname(),
                updatedPerson.getYearOfBirth(),
                id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE from person WHERE id=?", id);
    }

}
