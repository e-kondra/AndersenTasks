package andersen.AndersenTasks.service;

import andersen.AndersenTasks.models.PetOwner;
import andersen.AndersenTasks.repository.PetOwnerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public List<PetOwner> getTicketsFromFile(File file) {
        List<PetOwner> owners = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(file.getPath()));
            for (String s: lines) {
                PetOwner petOwner = new ObjectMapper().readValue(s, PetOwner.class);
                repository.save(petOwner);
                owners.add(petOwner);
            }
        }catch (NoSuchFileException e){
            System.out.println(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return owners;
    }
}
