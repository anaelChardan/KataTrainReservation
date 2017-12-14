package TrainReservation.Domain

case class Reservation(val trainId: TrainId, val bookingReference: BookingReference, val seats: List[Seat])
