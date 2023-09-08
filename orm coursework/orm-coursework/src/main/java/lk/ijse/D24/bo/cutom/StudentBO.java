package lk.ijse.D24.bo.cutom;

import lk.ijse.D24.bo.SuperBo;
import lk.ijse.D24.entity.Student;
import lk.ijse.D24.model.ReservationDTO;
import lk.ijse.D24.model.StudentDTO;

public interface StudentBO extends SuperBo {

    void saveStudent(StudentDTO studentDTO, ReservationDTO reservationDTO);

    void deleteStudent(String sId);

    Student searchStudentOnId(String sId);
}
