package andersen.AndersenTasks.ticket;

import andersen.AndersenTasks.abstraction.ProtoType;
import andersen.AndersenTasks.annotation.NullableWarning;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
public class Ticket extends ProtoType {

    private Long id;
    @NullableWarning
    private String concertHall;

    private int eventCode;
    private long time;
    private boolean isPromo;
    @NullableWarning
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
        return "Ticket. id: " + id + ", ProtoId: " + this.getProtoId() + ", concertHall: " + concertHall +
                ", eventCode: " + eventCode + ", time: " + Instant.ofEpochSecond(time) +
                ", isPromo: " + isPromo + ", sector: " + sector +
                ", maxWeight: " + maxWeight +
                ", price: " + ((price == null)? "null": price.setScale(2, BigDecimal.ROUND_HALF_UP)
                );
    }


    public void share(String phoneNumber){
        System.out.println("shared by phone " + phoneNumber);
    }

    public void share(String phoneNumber, String email){
        System.out.println("shared by phone " + phoneNumber + " and email " + email);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Ticket)) return false;
        Ticket oTicket = (Ticket) o;
        return (this.time == oTicket.time)
                && ((this.getProtoId() != null && oTicket.getProtoId() != null
                    && Long.compare(this.getProtoId(), oTicket.getProtoId()) == 0)
                            || this.getProtoId() == null && oTicket.getProtoId() == null)
                && (Float.compare(oTicket.maxWeight, this.maxWeight) == 0)
                && (this.isPromo == oTicket.isPromo)
                && (this.id != null && oTicket.id != null && this.id.equals(oTicket.id) || (this.id == null && oTicket.id == null))
                && (this.eventCode == oTicket.eventCode)
                && (this.sector == oTicket.sector)
                && (this.concertHall != null && oTicket.concertHall != null && this.concertHall.equals(oTicket.concertHall)
                            || this.concertHall == null && oTicket.concertHall == null)
                && (this.price != null && oTicket.price != null && this.price.compareTo(oTicket.price) == 0
                            || this.price == null && oTicket.price == null);
    }
    @Override
    public final int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (this.getProtoId() == null ? 0 : Long.hashCode(this.getProtoId()));
        result = prime * result + Long.hashCode(time);
        result = prime * result + Float.floatToIntBits(maxWeight);
        result = prime * result + (isPromo ? 1 : 0);
        result = prime * result + ((id == null)? 0 : id.hashCode());
        result = prime * result + eventCode;
        result = prime * result + ((sector == null)? 0 : sector.hashCode());
        result = prime * result + ((concertHall == null) ? 0 : concertHall.hashCode());
        result = prime * result + ((price == null) ? 0 : price.hashCode());
        return result;
    }
}
