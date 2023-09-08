package lk.ijse.D24.bo.impl;

import lk.ijse.D24.bo.cutom.StudentBO;
import lk.ijse.D24.dao.DAOFactory;
import lk.ijse.D24.dao.cutom.StudentDAO;
import lk.ijse.D24.entity.Student;
import lk.ijse.D24.model.ReservationDTO;
import lk.ijse.D24.model.StudentDTO;

public class StudentBoImpl implements StudentBO {

    StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDao(DAOFactory.DaoTypes.STUDENT);

    @Override
    public void saveStudent(StudentDTO studentDTO, ReservationDTO reservationDTO) {
//        studentDAO.update(new Student(studentDTO.getStudentId(),studentDTO.getStudentName(),studentDTO.getAddress(),studentDTO.getContactNo(),studentDTO.getDob(),studentDTO.getGender(),new Reservation(reservationDTO.getResId(),reservationDTO.getResDate(),reservationDTO.getStudentId(),reservationDTO.getRoomType(),reservationDTO.getKeyMoney()));
    }

    @Override
    public void deleteStudent(String sId) {
        studentDAO.delete(sId);
    }

//    @Override
//    public void updateStudent(StudentDTO studentDTO, ReservationDTO reservationDTO) {
//        studentDAO.update(new Student(studentDTO.getStudentId(),studentDTO.getStudentName(),studentDTO.getAddress(),studentDTO.getContactNo(),studentDTO.getDob(),studentDTO.getGender()));
//    }

    @Override
    public Student searchStudentOnId(String sId) {
        return studentDAO.search(sId);
    }
}
