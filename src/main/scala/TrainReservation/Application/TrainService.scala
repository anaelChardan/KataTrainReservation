package TrainReservation.Application

import TrainReservation.Domain._

//PROVIDED IN PROJECT
trait TrainService {
  def trainData(trainId: TrainId): Option[Train]
}
