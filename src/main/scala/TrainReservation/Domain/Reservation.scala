package TrainReservation.Domain

case class Reservation(val trainId: String, val bookingId: String, val seats: List[Seat])
