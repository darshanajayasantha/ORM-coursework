package lk.ijse.D24.model;

import java.sql.Date;
import java.time.LocalDate;

public class ReservationDetailDTO {
    private String resId;
    private String studentId;
    private String studentName;
    private Date resDate;
    private String roomType;
    private double keyMoney;
    private String gender;
    private LocalDate dob;
    private String address;
    private int contactNo;
    private String roomId;

    public ReservationDetailDTO() {
    }

    public ReservationDetailDTO(String resId, String studentId, String studentName, Date resDate, String roomType, double keyMoney, String gender, LocalDate dob, String address, int contactNo, String roomId) {
        this.resId = resId;
        this.studentId = studentId;
        this.studentName = studentName;
        this.resDate = resDate;
        this.roomType = roomType;
        this.keyMoney = keyMoney;
        this.gender = gender;
        this.dob = dob;
        this.address = address;
        this.contactNo = contactNo;
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

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getContactNo() {
        return contactNo;
    }

    public void setContactNo(int contactNo) {
        this.contactNo = contactNo;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

}
