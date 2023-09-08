package lk.ijse.D24.bo.cutom;

import lk.ijse.D24.bo.SuperBo;
import lk.ijse.D24.model.KeyMoneyDTO;

import java.util.List;

public interface PaymentBO extends SuperBo {
    List<KeyMoneyDTO> calculateKeyMoneyPayedStudents();
    List<KeyMoneyDTO> calculateStillKeyMoneyPayedStudents();
}
