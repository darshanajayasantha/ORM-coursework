package lk.ijse.D24.bo.cutom;

import lk.ijse.D24.bo.SuperBo;
import lk.ijse.D24.model.*;

import java.util.List;

public interface ReservationBO extends SuperBo {

    String genarateNextReservationId();

    void makeReservation(StudentDTO makeStudentDTO, ReservationDTO makeReservationDTO, RoomDTO roomById);

    List<ReservationDetailDTO> getAllReservationDetail();

    void updateReservationDetails(StudentDTO makeStudentDTO, ReservationDTO makeReservationDTO, RoomDTO roomById);

    ReservationDetailDTO searchOnReservasionId(String restId);

    void addMoneyToKeyMoney(String text, double text1);

    List<KeyMoneyDTO> getKeyMoneyDetails();
}
