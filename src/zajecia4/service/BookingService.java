package zajecia4.service;

import zajecia4.model.BookingStatus;
import zajecia4.model.SeatStatus;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Todo: zamienić na interfejs
 */
public class BookingService {
    private Map<Integer,Set<SeatStatus>> bookings;

    public BookingService() {
        bookings = new HashMap<>();

        Set<SeatStatus> train1 = new HashSet<>();
        train1.add(new SeatStatus(1, "55", BookingStatus.FREE));
        train1.add(new SeatStatus(1, "56", BookingStatus.FREE));
        train1.add(new SeatStatus(1, "66", BookingStatus.BOOKED));
        train1.add(new SeatStatus(1, "75", BookingStatus.BOOKED));
        train1.add(new SeatStatus(1, "76", BookingStatus.FREE));
        train1.add(new SeatStatus(1, "85", BookingStatus.FREE));
        train1.add(new SeatStatus(1, "72", BookingStatus.FREE));
        train1.add(new SeatStatus(1, "baggage1", BookingStatus.NOT_AVAILABLE));

        bookings.put(1, train1);

    }

    /**
     * Podaje status wszystkich miejsc w danym pociągu
     */
    public Set<SeatStatus> getBookingsForTrain(Integer trainId) {
        return bookings.get(trainId);
    }

    /**
     * Próbuje zarezerwować miejsce w danym pociągu.
     *
     * True jeśli operacja zakończyła się powodzeniem.
     */
    public boolean bookSeat(Integer trainId, String seatElementId) {
        Set<SeatStatus> currentTrain = bookings.get(trainId);
        if (currentTrain==null) return false;
        for(SeatStatus ss : currentTrain) {
            if (ss.getSeatElementId().equals(seatElementId)) {
                if (ss.getBookingStatus().equals(BookingStatus.FREE)) {
                    ss.setBookingStatus(BookingStatus.BOOKED);
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    /**
     * Kancelacja rezerwacji.
     *
     * True jeśli operacja się powiodła
     */
    public boolean unbookSeat(Integer trainId, String seatElementId) {
        Set<SeatStatus> currentTrain = bookings.get(trainId);
        if (currentTrain==null) return false;
        for(SeatStatus ss : currentTrain) {
            if (ss.getSeatElementId().equals(seatElementId)) {
                if (ss.getBookingStatus().equals(BookingStatus.BOOKED)) {
                    ss.setBookingStatus(BookingStatus.FREE);
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

}
