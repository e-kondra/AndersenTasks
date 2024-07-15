package andersen.AndersenTasks.service;

import andersen.AndersenTasks.models.User;
import andersen.AndersenTasks.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserService {
    @Autowired
    UserRepository repository;


    public void saveUser(User user){

        repository.save(user);
    }

    public Optional<User> getUserById(Long id){

        return repository.findById(id);
    }

    public void deleteUserById(Long id){

        repository.deleteById(id);
    }
}
