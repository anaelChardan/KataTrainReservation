package TrainReservation.Infrastructure.WebApi

import play.api.libs.json.{JsObject, JsString, JsValue, Json}
import TrainReservation.Application.TrainService
import TrainReservation.Domain._

class RestTrainService extends TrainService {
  override def trainData(trainId: TrainId): Train = {
    val jsonValue: JsValue = Json.parse(io.Source.fromURL(s"http://localhost:8081/data_for_train/${trainId}").mkString)
    val seats: JsObject = jsonValue("seats").asInstanceOf[JsObject]

    val referencedSeats: List[ReferencedSeat] = seats.fields.map({
      case (_, seat: JsObject) => {
        val seatMap = seat.fields.toMap
        val bookingReference = seatMap.get("booking_reference").get.asInstanceOf[JsString].value

        ReferencedSeat(
          Seat(
            seatMap.get("coach").get.asInstanceOf[JsString].value,
            seatMap.get("seat_number").get.asInstanceOf[JsString].value.toInt
          ),
          if (bookingReference.trim.isEmpty) None else Some(BookingReference(bookingReference.trim))
        )
      }
    }).toList

    val coachLetters: Seq[String] = seats.fields.map( {
      case (seatLetter: String, seat: JsObject) => {
        seat.fields.toMap.get("coach").get.asInstanceOf[JsString].value
      }
    }).distinct

    val coaches = Coaches(coachLetters.map(coachLetter => {
      Coach(coachLetter, ReferencedSeats(referencedSeats.filter(referencedSeat => referencedSeat.seat.coach == coachLetter)))
    }).toList)

    Train(trainId, coaches)
  }

  override def reserveASeat(trainId: TrainId, bookingReference: BookingReference, seats: Seats): Unit = ???

  override def reset(trainId: TrainId): Unit = io.Source.fromURL(s"http://localhost:8081/reset/${trainId}").mkString
}
