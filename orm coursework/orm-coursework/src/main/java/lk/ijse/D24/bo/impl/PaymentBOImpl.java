package lk.ijse.D24.bo.impl;

import lk.ijse.D24.bo.cutom.PaymentBO;
import lk.ijse.D24.dao.DAOFactory;
import lk.ijse.D24.dao.QueryDAO;
import lk.ijse.D24.dao.cutom.ReservationDAO;
import lk.ijse.D24.dao.cutom.RoomDAO;
import lk.ijse.D24.entity.Reservation;
import lk.ijse.D24.model.KeyMoneyDTO;
import lk.ijse.D24.tm.KeyMoneyTM;

import java.util.ArrayList;
import java.util.List;

public class PaymentBOImpl implements PaymentBO {

    ReservationDAO reservationDAO = (ReservationDAO) DAOFactory.getDaoFactory().getDao(DAOFactory.DaoTypes.RESERVATION);


    @Override
    public List<KeyMoneyDTO> calculateKeyMoneyPayedStudents() {

        List<KeyMoneyDTO> payedStudents = new ArrayList<>();
        List<Reservation> reservationList = reservationDAO.getAll();

        for (Reservation reservation : reservationList) {
            double remainKeyMoney = reservation.getRoom().getKeyMoney() - reservation.getPayedKeyMoney();

            if (0 == remainKeyMoney || 0 > remainKeyMoney) {
                payedStudents.add(new KeyMoneyDTO(
                        reservation.getStudent().getStudentId(),
                        reservation.getStudent().getStudentName(),
                        reservation.getRoom().getRoomTypeId(),
                        reservation.getResId()
                ));
            }
        }
        return payedStudents;
    }

    @Override
    public List<KeyMoneyDTO> calculateStillKeyMoneyPayedStudents() {

        List<KeyMoneyDTO> stillPayStudents = new ArrayList<>();
        List<Reservation> reservationList = reservationDAO.getAll();

        for (Reservation reservation : reservationList) {
            double remainKeyMoney = reservation.getRoom().getKeyMoney() - reservation.getPayedKeyMoney();

            if (0 < remainKeyMoney) {
                stillPayStudents.add(new KeyMoneyDTO(
                        reservation.getStudent().getStudentId(),
                        reservation.getStudent().getStudentName(),
                        reservation.getRoom().getRoomTypeId(),
                        reservation.getRoom().getKeyMoney(),
                        reservation.getPayedKeyMoney(),
                        remainKeyMoney,
                        reservation.getResId()
                ));
            }
        }
        return stillPayStudents;
    }
}
