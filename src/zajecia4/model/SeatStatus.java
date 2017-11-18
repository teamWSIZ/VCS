package zajecia4.model;


public class SeatStatus {
    Integer trainId;
    String seatElementId;   //tak jak w mapowaniu (x,y) --> string
    BookingStatus bookingStatus;


    public SeatStatus(Integer trainId, String seatElementId, BookingStatus bookingStatus) {
        this.trainId = trainId;
        this.seatElementId = seatElementId;
        this.bookingStatus = bookingStatus;
    }

    public Integer getTrainId() {
        return trainId;
    }

    public void setTrainId(Integer trainId) {
        this.trainId = trainId;
    }

    public String getSeatElementId() {
        return seatElementId;
    }

    public void setSeatElementId(String seatElementId) {
        this.seatElementId = seatElementId;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SeatStatus that = (SeatStatus) o;

        if (trainId != null ? !trainId.equals(that.trainId) : that.trainId != null) return false;
        if (seatElementId != null ? !seatElementId.equals(that.seatElementId) : that.seatElementId != null)
            return false;
        return bookingStatus == that.bookingStatus;
    }

    @Override
    public int hashCode() {
        int result = trainId != null ? trainId.hashCode() : 0;
        result = 31 * result + (seatElementId != null ? seatElementId.hashCode() : 0);
        result = 31 * result + (bookingStatus != null ? bookingStatus.hashCode() : 0);
        return result;
    }
}
