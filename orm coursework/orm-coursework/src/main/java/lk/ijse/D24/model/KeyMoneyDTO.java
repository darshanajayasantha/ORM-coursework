package lk.ijse.D24.model;

public class KeyMoneyDTO {
    private String studentId;
    private String name;
    private String roomId;
    private double totalKeyMoney;
    private double payedAmount;
    private double remainAmount;
    private String resId;

    public KeyMoneyDTO() {
    }

    public KeyMoneyDTO(String studentId, String name, String roomId, String resId) {
        this.studentId = studentId;
        this.name = name;
        this.roomId = roomId;
        this.resId = resId;
    }


    public KeyMoneyDTO(String studentId, String name, String roomId, double totalKeyMoney, double payedAmount, double remainAmount, String resId) {
        this.studentId = studentId;
        this.name = name;
        this.roomId = roomId;
        this.totalKeyMoney = totalKeyMoney;
        this.payedAmount = payedAmount;
        this.remainAmount = remainAmount;
        this.resId = resId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public double getTotalKeyMoney() {
        return totalKeyMoney;
    }

    public void setTotalKeyMoney(double totalKeyMoney) {
        this.totalKeyMoney = totalKeyMoney;
    }

    public double getPayedAmount() {
        return payedAmount;
    }

    public void setPayedAmount(double payedAmount) {
        this.payedAmount = payedAmount;
    }

    public double getRemainAmount() {
        return remainAmount;
    }

    public void setRemainAmount(double remainAmount) {
        this.remainAmount = remainAmount;
    }

    public String getResId() {
        return resId;
    }

    public void setResId(String resId) {
        this.resId = resId;
    }
}
