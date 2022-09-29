package ru.atom.dao;

import ru.atom.model.User;

import java.util.List;

public class UserDao {
    public List<User> findAll() {
        return List.of(
                new User(21, "Ivan"),
                new User(23, "Jora"),
                new User(22, "Ramos"));
    }
}
