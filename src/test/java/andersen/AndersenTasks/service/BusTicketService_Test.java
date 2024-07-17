package andersen.AndersenTasks.service;

import andersen.AndersenTasks.busticket.BusTicketType;
import andersen.AndersenTasks.models.BusTicket;
import andersen.AndersenTasks.models.User;
import andersen.AndersenTasks.repository.BusTicketRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BusTicketService_Test {

    @Mock
    BusTicketRepository mockRepository;
    @InjectMocks
    BusTicketService_ service;

    User user;
    BusTicket busTicket;

    @BeforeEach
    public void init(){
        user = new User(1L, "UserName", Timestamp.valueOf(LocalDate.now().atStartOfDay()), null);
        busTicket = new BusTicket(1L,Timestamp.valueOf(LocalDate.now().atStartOfDay()), BusTicketType.DAY, 10, user );
    }


    @Test
    public void saveBusTicketPositive() {
    when(mockRepository.save(any(BusTicket.class))).thenReturn(busTicket);
    service.saveBusTicket(busTicket);
    verify(mockRepository,times(1)).save(busTicket);
    }

    @Test
    public void saveBusTicketNegative() {
        when(mockRepository.save(any(BusTicket.class))).thenReturn(null);
        assertThrows(RuntimeException.class, ()->{
            service.saveBusTicket(null);
        });
    }

    @Test
    public void getTicketByIdPositive() {
        when(mockRepository.findById(anyLong())).thenReturn(Optional.of(busTicket));
        Optional<BusTicket> returnedBusTicket = service.getTicketById(busTicket.getId());
        assertEquals(busTicket.getId(), returnedBusTicket.get().getId());
        assertEquals(busTicket.getTicketType(), returnedBusTicket.get().getTicketType());
        verify(mockRepository,times(1)).findById(anyLong());
    }

    @Test
    public void getTicketByIdNegative() {
        when(mockRepository.findById(anyLong())).thenReturn(null);
        Optional<BusTicket> returnedBusTicket = service.getTicketById(busTicket.getId());
        assertNull(returnedBusTicket);
        verify(mockRepository, times(1)).findById(anyLong());
    }

    @Test
    public void getTicketByIdCorner() {
        when(mockRepository.findById(-1L)).thenReturn(null);
        Optional<BusTicket> returnedBusTicket = service.getTicketById(-1L);
        assertNull(returnedBusTicket);
        verify(mockRepository, times(1)).findById(-1L);
    }

    @Test
    public void getTicketByUserIdPositive() {
        when(mockRepository.findAllByUserId(anyLong())).thenReturn(List.of(busTicket));
        List<BusTicket> returnedList = service.getTicketByUserId(user.getId());
        assertEquals(List.of(busTicket), returnedList);
        assertEquals(1,returnedList.size());
        verify(mockRepository, times(1)).findAllByUserId(anyLong());
    }

    @Test
    public void getTicketByUserIdNegative() {
        when(mockRepository.findAllByUserId(anyLong())).thenReturn(null);
        List<BusTicket> returnedList = service.getTicketByUserId(user.getId());
        assertNull(returnedList);
        verify(mockRepository, times(1)).findAllByUserId(anyLong());
    }

    @Test
    public void getTicketByUserIdCorner() {
        when(mockRepository.findAllByUserId(-1L)).thenReturn(null);
        List<BusTicket> returnedList = service.getTicketByUserId(-1L);
        assertNull(returnedList);
        verify(mockRepository, times(1)).findAllByUserId(-1L);
    }

    @Test
    public void updateTicketTypePositive() {
        when(mockRepository.findById(anyLong())).thenReturn(Optional.of(busTicket));
        busTicket.setTicketType(BusTicketType.MONTH);
        when(mockRepository.save(any(BusTicket.class))).thenReturn(busTicket);
        BusTicket updatedTicket = service.updateTicketType(busTicket, BusTicketType.MONTH);
        assertEquals(updatedTicket, busTicket);
        assertEquals(BusTicketType.MONTH,updatedTicket.getTicketType());
        verify(mockRepository,times(1)).findById(anyLong());
        verify(mockRepository, times(1)).save(any());
    }
    @Test
    public void updateTicketTypeNegative() {
        when(mockRepository.findById(anyLong())).thenReturn(Optional.of(busTicket));
        when(mockRepository.save(any(BusTicket.class))).thenReturn(null);
        BusTicket updatedTicket = service.updateTicketType(busTicket, BusTicketType.MONTH);
        assertNull(updatedTicket);
        verify(mockRepository,times(1)).findById(anyLong());
        verify(mockRepository, times(1)).save(any());
    }
    @Test
    public void updateTicketTypeCorner() {
        when(mockRepository.findById(anyLong())).thenReturn(Optional.ofNullable(any()));
        BusTicket updatedTicket = service.updateTicketType(busTicket, BusTicketType.MONTH);
        assertNull(updatedTicket);
        verify(mockRepository,times(1)).findById(anyLong());
        verify(mockRepository, times(0)).save(any());
    }
}