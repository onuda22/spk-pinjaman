package mini_project.spk.service;

import mini_project.spk.model.User;

import java.util.List;

public interface UserService {
    User create(User newUser);

    List<User> getAll();
}
