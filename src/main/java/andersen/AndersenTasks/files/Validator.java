package andersen.AndersenTasks.files;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;

public class Validator {
    static Map<String, Integer> violations;

    public Validator(){
        violations = new HashMap<>();
        violations.put("price", 0);
        violations.put("start date",0);
        violations.put("ticket type", 0);
    }

    public boolean validateBusTicket(BusTicket ticket){
        boolean validTicket = true;
        if (!validatePrice(ticket)){
            violations.put("price", violations.get("price") + 1);
            validTicket = false;
        }
        if(!validateStartDate(ticket)){
            violations.put("start date", violations.get("start date") + 1);
            validTicket = false;
        }
        if(!validateTicketType(ticket)){
            violations.put("ticket type", violations.get("ticket type") + 1);
            validTicket = false;
        }
        return validTicket;
    }


    private boolean validatePrice(BusTicket ticket){
        if(ticket.getPrice() != null) {
            if (Integer.parseInt(ticket.getPrice()) == 0) {
                System.out.println("Price can't be zero");
                return false;
            }
            if(Integer.parseInt(ticket.getPrice()) % 2 != 0){
                System.out.println("Price should always be even");
                return false;
            }
        }
        return true;
    }

    private boolean validateStartDate(BusTicket ticket){
        boolean retBool = true;
        List<String> list = List.of("DAY", "WEEK", "YEAR");
        if (ticket.getTicketType() != null
            && list.contains(ticket.getTicketType().toUpperCase())
            && ticket.getStartDate() == null) {
                System.out.println("Tickets with types DAY, WEEK and YEAR must have a start day");
                retBool =  false;
            }

        if (ticket.getStartDate() != null && ticket.getStartDate().length() > 0){
            LocalDate now = LocalDate.now();
            String[] arrSplit = ticket.getStartDate().split("-",3);
            LocalDate startDate = LocalDate.of(Integer.parseInt(arrSplit[0]),
                                                Month.of(Integer.parseInt(arrSplit[1])),
                                                Integer.parseInt(arrSplit[2]));
            if (now.isBefore(startDate)){
                System.out.println("Start day can't be in the future");
                retBool =  false;
            }
        }
        return retBool;
    }

    public boolean validateTicketType(BusTicket ticket) {
        List<String> list = List.of(new String[]{"DAY", "WEEK", "YEAR", "MONTH"});
        if (ticket.getTicketType() != null
        && !list.contains(ticket.getTicketType().toUpperCase())) {
                System.out.println("Invalid ticket type value, valid values are DAY, WEEK, YEAR, MONTH");
                return false;
        }
        return true;
    }


    public String getMostPopularViolation(){
        int maxViolation =  Collections.max(violations.values());
        String violationType ="-";
        if (maxViolation > 0){
            for (Map.Entry<String, Integer> entry : violations.entrySet()){
                if(entry.getValue() == maxViolation)
                    violationType = entry.getKey();
            }
        }
        return violationType;
    }

}
