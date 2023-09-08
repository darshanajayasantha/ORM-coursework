package lk.ijse.D24.entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
public class Reservation {
    @Id
    private String resId;
    private Date resDate;
    private double payedKeyMoney;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Student student;

    @ManyToOne(fetch = FetchType.EAGER)
    private Room room;

    public Reservation() {
    }

    public Reservation(String resId, Date resDate, double payedKeyMoney, Student student, Room room) {
        this.resId = resId;
        this.resDate = resDate;
        this.payedKeyMoney = payedKeyMoney;
        this.student = student;
        this.room = room;
    }

    public String getResId() {
        return resId;
    }

    public void setResId(String resId) {
        this.resId = resId;
    }

    public Date getResDate() {
        return resDate;
    }

    public void setResDate(Date resDate) {
        this.resDate = resDate;
    }

    public double getPayedKeyMoney() {
        return payedKeyMoney;
    }

    public void setPayedKeyMoney(double payedKeyMoney) {
        this.payedKeyMoney = payedKeyMoney;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
