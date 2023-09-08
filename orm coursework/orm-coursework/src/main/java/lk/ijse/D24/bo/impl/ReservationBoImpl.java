package lk.ijse.D24.bo.impl;

import lk.ijse.D24.bo.cutom.ReservationBO;
import lk.ijse.D24.dao.DAOFactory;
import lk.ijse.D24.dao.cutom.ReservationDAO;
import lk.ijse.D24.dao.cutom.RoomDAO;
import lk.ijse.D24.dao.cutom.StudentDAO;
import lk.ijse.D24.entity.Reservation;
import lk.ijse.D24.entity.Room;
import lk.ijse.D24.entity.Student;
import lk.ijse.D24.model.*;

import java.util.ArrayList;
import java.util.List;

public class ReservationBoImpl implements ReservationBO {

    ReservationDAO reservationDAO = (ReservationDAO) DAOFactory.getDaoFactory().getDao(DAOFactory.DaoTypes.RESERVATION);
    StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDao(DAOFactory.DaoTypes.STUDENT);
    RoomDAO roomDAO = (RoomDAO) DAOFactory.getDaoFactory().getDao(DAOFactory.DaoTypes.ROOM);
    @Override
    public String genarateNextReservationId() {
         String lastResId = reservationDAO.getLastReseavationId();

        if(!lastResId.isEmpty()) {
            String[] splitId = lastResId.split("R00");
            int newResId = Integer.parseInt(splitId[1]);
            if (newResId < 9) {
                newResId++;
                return "R00" + newResId;
            } else if (newResId < 99) {
                newResId++;
                return "R0" + newResId;
            } else if (newResId < 999) {
                newResId++;
                return "R" + newResId;
            }
            else {
                newResId++;
                return "R" + newResId;
            }
        }
        return "R001";
    }

    @Override
    public void makeReservation(StudentDTO studentDTO, ReservationDTO reservationDTO, RoomDTO roomDTO) {
        Student student = new Student();
        student.setStudentId(studentDTO.getStudentId());
        student.setStudentName(studentDTO.getStudentName());
        student.setAddress(studentDTO.getAddress());
        student.setContactNo(studentDTO.getContactNo());
        student.setDob(studentDTO.getDob());
        student.setGender(studentDTO.getGender());

        Reservation reservation = new Reservation();
        reservation.setResId(reservationDTO.getResId());
        reservation.setResDate(reservationDTO.getResDate());
        reservation.setPayedKeyMoney(reservationDTO.getKeyMoney());

        Room room = new Room();
        room.setRoomTypeId(roomDTO.getRoomTypeId());
        room.setRoomType(roomDTO.getRoomType());
        room.setKeyMoney(roomDTO.getKeyMoney());

        reservation.setStudent(student);
        reservation.setRoom(room);

        student.getReservations().add(reservation);
        studentDAO.save(student);

//        room.getReservations().add(reservation);
        room.setStudent(student);

        roomDAO.update(room);
    }

    @Override
    public List<ReservationDetailDTO> getAllReservationDetail() {
        List<ReservationDetailDTO> reservationDetailDTOS = new ArrayList<>();

        List<Reservation> allReservationDetail = reservationDAO.getAll();
        for (Reservation details : allReservationDetail) {
            reservationDetailDTOS.add(new ReservationDetailDTO(
                    details.getResId(),
                    details.getStudent().getStudentId(),
                    details.getStudent().getStudentName(),
                    details.getResDate(),
                    details.getRoom().getRoomType(),
                    details.getRoom().getKeyMoney(),
                    details.getStudent().getGender(),
                    details.getStudent().getDob(),
                    details.getStudent().getAddress(),
                    details.getStudent().getContactNo(),
                    details.getRoom().getRoomTypeId()
            ));
        }
        return reservationDetailDTOS;
    }

    @Override
    public List<KeyMoneyDTO> getKeyMoneyDetails() {
        List<KeyMoneyDTO> keyMoneyDTOS = new ArrayList<>();
        List<Reservation> allReservationDetail = reservationDAO.getAll();

        for (Reservation details : allReservationDetail) {

            double remainKeyMoney = details.getRoom().getKeyMoney() - details.getPayedKeyMoney();

                    keyMoneyDTOS.add(new KeyMoneyDTO(
                    details.getStudent().getStudentId(),
                    details.getStudent().getStudentName(),
                    details.getRoom().getRoomTypeId(),
                    details.getRoom().getKeyMoney(),
                    details.getPayedKeyMoney(),
                    remainKeyMoney,
                    details.getResId()
            ));
        }
        return keyMoneyDTOS;
    }

    @Override
    public void updateReservationDetails(StudentDTO studentDTO, ReservationDTO reservationDTO, RoomDTO roomDTO) {
        Student student = new Student();
        student.setStudentId(studentDTO.getStudentId());
        student.setStudentName(studentDTO.getStudentName());
        student.setAddress(studentDTO.getAddress());
        student.setContactNo(studentDTO.getContactNo());
        student.setDob(studentDTO.getDob());
        student.setGender(studentDTO.getGender());

        Reservation reservation = new Reservation();
        reservation.setResId(reservationDTO.getResId());
        reservation.setResDate(reservationDTO.getResDate());
        reservation.setPayedKeyMoney(reservationDTO.getKeyMoney());

        Room room = new Room();
        room.setRoomTypeId(roomDTO.getRoomTypeId());
        room.setRoomType(roomDTO.getRoomType());
        room.setKeyMoney(roomDTO.getKeyMoney());

        reservation.setStudent(student);
        reservation.setRoom(room);

//        student.getReservations().add(reservation);
        reservationDAO.update(reservation);

//        room.getReservations().add(reservation);
        room.setStudent(student);

        roomDAO.update(room);
    }

    @Override
    public ReservationDetailDTO searchOnReservasionId(String restId) {
        Reservation reservation = reservationDAO.search(restId);

        return new ReservationDetailDTO(
                reservation.getResId(),
                reservation.getStudent().getStudentId(),
                reservation.getStudent().getStudentName(),
                reservation.getResDate(),
                reservation.getRoom().getRoomType(),
                reservation.getRoom().getKeyMoney(),
                reservation.getStudent().getGender(),
                reservation.getStudent().getDob(),
                reservation.getStudent().getAddress(),
                reservation.getStudent().getContactNo(),
                reservation.getRoom().getRoomTypeId()
        );
    }

    @Override
    public void addMoneyToKeyMoney(String roomId, double amount) {
        reservationDAO.addMoneyToKeyMoney(roomId, amount);
    }
}
