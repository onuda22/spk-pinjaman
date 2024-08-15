package mini_project.spk.service.implementation;

import lombok.RequiredArgsConstructor;
import mini_project.spk.model.User;
import mini_project.spk.repository.UserRepo;
import mini_project.spk.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;

    @Override
    public User create(User newUser) {
        return userRepo.saveUser(newUser.getUsername(), newUser.getPassword());
    }

    @Override
    public List<User> getAll() {
        return userRepo.findUserAll();
    }
}
