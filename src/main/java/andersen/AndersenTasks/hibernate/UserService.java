package andersen.AndersenTasks.hibernate;

public class UserService {

    UserRepository repository = new UserRepository();

    public void saveUser(UserNew user){
        repository.save(user);
    }

    public UserNew getUserById(int id){
        return repository.fetchById(id);
    }

    public void deleteUserById(int id){
        repository.delete(id);
    }

}
