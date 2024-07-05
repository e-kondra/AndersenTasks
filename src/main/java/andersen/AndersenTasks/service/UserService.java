package andersen.AndersenTasks.service;

import andersen.AndersenTasks.models.User;
import andersen.AndersenTasks.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class UserService {
    UserRepository repository;

    public UserService(UserRepository userRepository){
        this.repository = userRepository;
    }

    public void saveUser(User user){
        repository.save(user);
    }

    public User getUserById(int id){
        return repository.fetchById(id);
    }

    public void deleteUserById(int id){
        repository.delete(id);
    }
}
