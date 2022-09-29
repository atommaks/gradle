package ru.atom.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserServiceTest {
    private final UserService userService = new UserService();

    @Test
    public void getAll() {
        final var res = userService.getAll();
        Assertions.assertEquals(3, res.size());
    }
}
