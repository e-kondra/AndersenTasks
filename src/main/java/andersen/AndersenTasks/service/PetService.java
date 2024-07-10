package andersen.AndersenTasks.service;

import andersen.AndersenTasks.models.Pet;
import andersen.AndersenTasks.repository.PetRepository;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
public class PetService {

    private PetRepository repository;

    public PetService(PetRepository petRepository){
        this.repository = petRepository;
    }

    public void savePet(Pet pet)  {
        try {
            repository.save(pet);
        } catch (SQLException e){
            System.out.println(e);
        }
    }

    public void updatePet(Pet pet)  {
        try {
            repository.update(pet);
        } catch (SQLException e){
            System.out.println(e);
        }
    }
}
