package lk.ijse.D24.model;

import lk.ijse.D24.entity.Student;

import java.util.List;

public class RoomDTO {
    private String roomTypeId;
    private String roomType;
    private double keyMoney;

    public RoomDTO() {
    }

    public RoomDTO(String roomTypeId, String roomType, double keyMoney) {
        this.roomTypeId = roomTypeId;
        this.roomType = roomType;
        this.keyMoney = keyMoney;
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
}
