package andersen.AndersenTasks;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
public class Ticket extends ProtoType{

    private Long id;
    private String concertHall;
    private int eventCode;
    private long time;
    private boolean isPromo;
    private Sector sector;
    private float maxWeight;
    private BigDecimal price;

    public Ticket() {}

    public Ticket(Long id, String concertHall, int eventCode, boolean isPromo, Sector sector, float maxWeight, BigDecimal price) {
        checkAttributes(concertHall, eventCode);
        this.id = id;
        this.concertHall = concertHall;
        this.eventCode = eventCode;
        this.time = Instant.now().getEpochSecond();
        this.isPromo = isPromo;
        this.sector = sector;
        this.maxWeight = maxWeight;
        this.price = price;
    }

    public Ticket(String concertHall, int eventCode) {
        this.concertHall = concertHall;
        this.eventCode = eventCode;
        this.time = Instant.now().getEpochSecond();
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    private void checkAttributes(String concertHall, int eventCode){
        if(concertHall.length() > 10) throw new TicketValidationException("Concert Hall must contain no more than 10 letters");
        if(eventCode/100 > 9 || eventCode/100 < 1) throw new TicketValidationException("Event code must contain exactly 3 digits");
    }

    @Override
    public String toString() {
        return "Ticket. id: " + id + ", concertHall: " + concertHall +
                ", eventCode: " + eventCode + ", time: " + Instant.ofEpochSecond(time) +
                ", isPromo: " + isPromo + ", sector: " + sector +
                ", maxWeight: " + maxWeight +
                ", price: " + ((price == null)? "null": price.setScale(2, BigDecimal.ROUND_HALF_UP));
    }


    public void share(String phoneNumber){
        System.out.println("shared by phone " + phoneNumber);
    }

    public void share(String phoneNumber, String email){
        System.out.println("shared by phone " + phoneNumber + " and email " + email);
    }

}
