package TrainReservation.Application

import TrainReservation.Domain._

//TODO Why more in Application than Domain?
class ReservationRequestHandler(val trainService: TrainService, bookingReferenceProvider: BookingReferenceProvider) {
  //TODO: Should it be void as it is a command handler ?
  def handle(reservationRequest: ReservationRequest): Option[Reservation] = {
    val trainOption: Option[Train] = trainService.trainData(TrainId(reservationRequest.trainId))

    if (trainOption.isEmpty) {
      return None
    }

    val train: Train = trainOption.get

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