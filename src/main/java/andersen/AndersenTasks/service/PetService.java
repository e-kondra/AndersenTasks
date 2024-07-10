package andersen.AndersenTasks.service;

import andersen.AndersenTasks.models.Pet;
import andersen.AndersenTasks.repository.PetRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Component
public class PetService {

    private PetRepository repository;

    @Value("#{new Boolean(\"${custom-property.update-pet-Owner}\")}")
    private boolean updatePetOwner;

    public PetService(PetRepository petRepository){
        this.repository = petRepository;
    }

    @Transactional
    public void savePet(Pet pet)  {
        if(! updatePetOwner){
            throw new RuntimeException("Pet Owner couldn't be updated");
        }
        try {
            repository.save(pet);
        } catch (SQLException e){
            System.out.println(e);
        }
    }

    @Transactional
    public void updatePet(Pet pet)  {
        if(! updatePetOwner){
            throw new RuntimeException("Pet Owner couldn't be updated");
        }
        try {
            repository.update(pet);
        } catch (SQLException e){
            System.out.println(e);
        }
    }
}
