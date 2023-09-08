package lk.ijse.D24.bo.cutom;

import lk.ijse.D24.bo.SuperBo;
import lk.ijse.D24.model.RoomDTO;

import java.util.List;

public interface RoomBO extends SuperBo {
    String genarateNextRoomId();

    void addNewRoom(RoomDTO newRoom);

    void updateRoom(RoomDTO newRoom);

    void deleteRoom(String roomId);

    List<RoomDTO> getAllRoomData();

    int acRoomWithFoodCount();

    int acRoomWithoutFoodCount();

    int nonAcWithFoodRoomCount();

    int nonAcWithoutFoodRoomCount();

    List<Double> getKeyMoneyamount();

    void updateKeymoneyAmount(String text, String text1, String text2, String text3);

    List<String> isAvailableNonAcRooms();

    List<String> isAvailableNonAcFoodRooms();

    List<String> isAvailableAcRooms();

    List<String> isAvailableAcFoodRooms();

    RoomDTO getRoomById(String roomId);
}
