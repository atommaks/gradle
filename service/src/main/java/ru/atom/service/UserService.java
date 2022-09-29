package ru.atom.service;

import ru.atom.dao.UserDao;
import ru.atom.model.User;

import java.util.List;

public class UserService {
    private final UserDao userDao = new UserDao();

    public List<User> getAll() {
        return userDao.findAll();
    }
}
