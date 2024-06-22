package andersen.AndersenTasks.files;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class BusTicketValidator {

    public static void main(String[] args) throws IOException {
        int ticketsNumber = 0;
        int validTickets = 0;
        String filePath = "C:\\Users\\User\\IdeaProjects\\AndersonTasks\\AndersenTasks\\src\\main\\java\\andersen\\AndersenTasks\\files\\busTickets.yaml";

        Validator validator = new Validator();

        try{
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            for (String s: lines) {
                BusTicket ticket = new ObjectMapper().readValue(s, BusTicket.class);
                ticketsNumber ++;
                System.out.println(ticket);
                if(validator.validateBusTicket(ticket))
                    validTickets ++;
            }
        } catch (NoSuchFileException e){
            System.out.println("We couldn't find file for reading tickets, so you can write it line by line in console");
            System.out.println("What number of tickets would you like to validate?");
            String input = getInput();
            int lineNumber = Integer.parseInt(input);
            int x = 0;

            do {
                input = getInput();
                BusTicket ticket= new ObjectMapper().readValue(input, BusTicket.class);
                ticketsNumber ++;
                if(validator.validateBusTicket(ticket))
                    validTickets ++;
                x++;
            } while (x < lineNumber);
        }

        System.out.println("Total = " + ticketsNumber);
        System.out.println("Valid = " + validTickets);
        System.out.println("Most popular violation = " + validator.getMostPopularViolation());
    }

    private static String getInput() {
        return new Scanner(System.in).nextLine();
    }
}
