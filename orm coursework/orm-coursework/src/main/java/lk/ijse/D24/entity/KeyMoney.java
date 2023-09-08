package lk.ijse.D24.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class KeyMoney {
    @Id
    private String  roomType;
    private double  keyMoneyAmount;

    public KeyMoney() {
    }

    public KeyMoney(String roomType, double keyMoneyAmount) {
        this.roomType = roomType;
        this.keyMoneyAmount = keyMoneyAmount;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }
}
