package TrainReservation.Domain

trait BookingReferenceProvider {
  def get(): BookingReference
}
