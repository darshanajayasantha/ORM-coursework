package lk.ijse.D24.dao.cutom;

import lk.ijse.D24.dao.CrudDAO;
import lk.ijse.D24.entity.Room;

import java.util.List;

public interface RoomDAO extends CrudDAO<Room,String> {
    String getLastRoomId();

    int acRoomWithFoodCount();

    int acRoomWithoutFoodCount();

    int nonAcWithFoodRoomCount();

    int nonAcWithoutFoodRoomCount();

    List<Double> getKeyMoneyamount();

    void updateKeyMoneyAmount(double text, double text1, double text2, double text3);

    List<Room> isAvailableRooms();

    Room getRoomById(String roomId);
}
