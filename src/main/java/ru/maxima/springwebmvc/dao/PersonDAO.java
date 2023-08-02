package ru.maxima.springwebmvc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.maxima.springwebmvc.entity.Person;

import java.util.ArrayList;
import java.util.List;


/**
 * @author AramaJava 26.07.2023
 */

    /*
        Добавить на главную страницу приложения возможность поиска по имени.

        Отдельное поле, куда нужно будет написать искомое имя
         + кнопку для поиска и выдавать всех пользователей,
        у которых имя начинается на искомый стринг

        и возвращать пользователю новую страницу сo всеми людьми,
        где будет сверху надпись "Вы искали людей с именем ИСКОМОЕ_ИМЯ"
        и список всех людей, у которых имя начинается на искомый стринг.

        Метод контроллера с поиском по БД должен быть написан
        с вызовом ДАО класса, который будет использовать JDBCTemplate,
        необходимо погуглить, как искать в БД строки с колонками,
        начинающиеся на искомый стринг.

*/


@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        String sql = "select * from person";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Person.class));
    }

    public Person show(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM Person WHERE id=?",
                BeanPropertyRowMapper.newInstance(Person.class), id);
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO person (name, surname, age, email) VALUES (?, ?, ?, ?)",
                person.getName(),
                person.getSurname(),
                person.getAge(),
                person.getEmail());
    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE person SET name=?, surname=?, age=?, email=? WHERE id=?",
                updatedPerson.getName(),
                updatedPerson.getSurname(),
                updatedPerson.getAge(),
                updatedPerson.getEmail(),
                id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE from person WHERE id=?", id);
    }

    public  List<Person> findPersonsByName( String name) {
        String sql = "select * from person p where p.name like '%'?'%'";
        return new ArrayList<>(jdbcTemplate.query(sql,
                BeanPropertyRowMapper.newInstance(Person.class), name));
    }
}
