package lk.ijse.D24.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Room {
    @Id
    private String roomTypeId;
    private String roomType;
    private double keyMoney;

    @OneToMany(mappedBy = "room",fetch = FetchType.EAGER)
    private List<Reservation> reservations;

    @OneToOne
    private Student student;

    public Room() {
    }

    public Room(String roomTypeId, String roomType, double keyMoney) {
        this.roomTypeId = roomTypeId;
        this.roomType = roomType;
        this.keyMoney = keyMoney;
    }

    public Room(String roomTypeId, String roomType, double keyMoney, List<Reservation> reservations, Student student) {
        this.roomTypeId = roomTypeId;
        this.roomType = roomType;
        this.keyMoney = keyMoney;
        this.reservations = reservations;
        this.student = student;
    }

    public String getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(String roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public double getKeyMoney() {
        return keyMoney;
    }

    public void setKeyMoney(double keyMoney) {
        this.keyMoney = keyMoney;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
