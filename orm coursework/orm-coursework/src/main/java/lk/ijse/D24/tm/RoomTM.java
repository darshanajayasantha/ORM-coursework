package lk.ijse.D24.tm;

public class RoomTM {
    private String roomTypeId;
    private String roomType;
    private double keyMoney;

    public RoomTM() {
    }

    public RoomTM(String roomTypeId, String roomType, double keyMoney) {
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
