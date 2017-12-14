package TrainReservation.Application

import TrainReservation.Domain._

//TODO Why more in Application than Domain?
class TicketOffice(val trainService: TrainService, bookingReferenceProvider: BookingReferenceProvider) {
  //TODO: Should be void as it is a command handler
  def makeReservation(reservationRequest: ReservationRequest): Option[Reservation] = {
    val train: Train = trainService.trainData(TrainId(reservationRequest.trainId))

    if (!train.canAcceptThosePeople(reservationRequest.seatCount)) {
      return None
    }

    train
      .coaches
      .filter(_.canAcceptThosePeople(reservationRequest.seatCount))
      .headOption
      .map(coach => Reservation(train.trainId, bookingReferenceProvider.get(), coach.freeSeats.take(reservationRequest.seatCount)))
  }
}