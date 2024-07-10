package andersen.AndersenTasks.service;

import andersen.AndersenTasks.models.Pet;
import andersen.AndersenTasks.models.PetOwner;
import andersen.AndersenTasks.repository.PetOwnerRepository;
import andersen.AndersenTasks.repository.PetRepository;

import java.sql.SQLException;

public class PetOwnerService {

    private PetOwnerRepository repository;

    public PetOwnerService(PetOwnerRepository petOwnerRepository){
        this.repository = petOwnerRepository;
    }

    public void savePetOwner(PetOwner petOwner)  {
        try {
            repository.save(petOwner);
        } catch (SQLException e){
            System.out.println(e);
        }
    }
}
