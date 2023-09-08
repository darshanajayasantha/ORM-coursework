package lk.ijse.D24.model;

import java.sql.Date;

public class ReservationDTO {
    private String resId;
    private String studentId;
    private Date resDate;
    private String roomType;
    private double keyMoney;
    private String roomId;

    public ReservationDTO() {
    }

    public ReservationDTO(String resId, String studentId, Date resDate, String roomType, double keyMoney, String roomId) {
        this.resId = resId;
        this.studentId = studentId;
        this.resDate = resDate;
        this.roomType = roomType;
        this.keyMoney = keyMoney;
        this.roomId = roomId;
    }

    public String getResId() {
        return resId;
    }

    public void setResId(String resId) {
        this.resId = resId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Date getResDate() {
        return resDate;
    }

    public void setResDate(Date resDate) {
        this.resDate = resDate;
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

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

}
