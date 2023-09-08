package lk.ijse.D24.model;

import java.sql.Date;
import java.time.LocalDate;

public class StudentDTO {
    private String studentId;
    private String studentName;
    private String address;
    private int contactNo;
    private LocalDate dob;
    private String gender;

    public StudentDTO() {
    }

    public StudentDTO(String studentId, String studentName, String address, int contactNo, LocalDate dob, String gender) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.address = address;
        this.contactNo = contactNo;
        this.dob = dob;
        this.gender = gender;
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

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
