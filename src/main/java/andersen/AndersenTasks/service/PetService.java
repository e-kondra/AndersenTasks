package andersen.AndersenTasks.service;

import andersen.AndersenTasks.models.Pet;
import andersen.AndersenTasks.repository.PetRepository;
import org.springframework.stereotype.Component;

@Component
public class PetService {

    private PetRepository repository;

    public PetService(PetRepository petRepository){
        this.repository = petRepository;
    }

    public void savePet(Pet pet){
        repository.save(pet);
    }
}
