package lk.ijse.D24.dao.cutom;

import lk.ijse.D24.dao.CrudDAO;
import lk.ijse.D24.entity.Reservation;
import lk.ijse.D24.entity.Student;

public interface ReservationDAO extends CrudDAO<Reservation,String> {

    String getLastReseavationId();

    void addMoneyToKeyMoney(String roomId, double amount);
}
