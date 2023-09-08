package lk.ijse.D24.bo.impl;

import lk.ijse.D24.bo.SuperBo;
import lk.ijse.D24.bo.cutom.RoomBO;
import lk.ijse.D24.dao.DAOFactory;
import lk.ijse.D24.dao.cutom.RoomDAO;
import lk.ijse.D24.entity.Room;
import lk.ijse.D24.model.RoomDTO;

import java.util.ArrayList;
import java.util.List;

public class RoomBoImpl implements RoomBO {
    RoomDAO roomDAO = (RoomDAO) DAOFactory.getDaoFactory().getDao(DAOFactory.DaoTypes.ROOM);

    @Override
    public String genarateNextRoomId() {
        String lastRoomID = roomDAO.getLastRoomId();

        if(!lastRoomID.isEmpty()) {
            String[] splitId = lastRoomID.split("RM-");
            int newRoomId = Integer.parseInt(splitId[1]);
            if (newRoomId < 9) {
                newRoomId++;
                return "RM-00" + newRoomId;
            } else if (newRoomId < 99) {
                newRoomId++;
                return "RM-0" + newRoomId;
            } else {
                newRoomId++;
                return "RM-" + newRoomId;
            }
        }
        return "RM-001";
    }

    @Override
    public void addNewRoom(RoomDTO newRoom) {
        roomDAO.save(new Room(newRoom.getRoomTypeId(),newRoom.getRoomType(),newRoom.getKeyMoney()));
    }

    @Override
    public void updateRoom(RoomDTO newRoom) {
        roomDAO.update(new Room(newRoom.getRoomTypeId(),newRoom.getRoomType(),newRoom.getKeyMoney()));
    }

    @Override
    public void deleteRoom(String roomId) {
        roomDAO.delete(roomId);
    }

    @Override
    public List<RoomDTO> getAllRoomData() {
       List<RoomDTO> roomDTOS = new ArrayList<>();
       List<Room> rooms = roomDAO.getAll();

        for (Room room : rooms) {
            roomDTOS.add(new RoomDTO(
               room.getRoomTypeId(),
               room.getRoomType(),
               room.getKeyMoney()
            ));
        }
        return roomDTOS;
    }

    @Override
    public int acRoomWithFoodCount() {
        return roomDAO.acRoomWithFoodCount();
    }

    @Override
    public int acRoomWithoutFoodCount() {
        return roomDAO.acRoomWithoutFoodCount();
    }

    @Override
    public int nonAcWithFoodRoomCount() {
        return roomDAO.nonAcWithFoodRoomCount();
    }

    @Override
    public int nonAcWithoutFoodRoomCount() {
        return roomDAO.nonAcWithoutFoodRoomCount();
    }

    @Override
    public List<Double> getKeyMoneyamount() {
        return roomDAO.getKeyMoneyamount();
    }

    @Override
    public void updateKeymoneyAmount(String text, String text1, String text2, String text3) {
        double key = Double.parseDouble(text);
        double key1 = Double.parseDouble(text1);
        double key2 = Double.parseDouble(text2);
        double key3 = Double.parseDouble(text3);
        roomDAO.updateKeyMoneyAmount(key,key1,key2,key3);
    }

    @Override
    public List<String> isAvailableNonAcRooms() {
        List<String> nonAcRooms = new ArrayList<>();
        List<Room> rooms =  roomDAO.isAvailableRooms();

        for(Room room : rooms) {
            if(room.getRoomType().equalsIgnoreCase("NON A/C")) {
                nonAcRooms.add(room.getRoomTypeId());
            }
        }
        return nonAcRooms;
    }

    @Override
    public List<String> isAvailableNonAcFoodRooms() {
        List<String> nonAcFoodRooms = new ArrayList<>();
        List<Room> rooms =  roomDAO.isAvailableRooms();

        for(Room room : rooms) {
            if(room.getRoomType().equalsIgnoreCase("NON A/C FOOD")) {
                nonAcFoodRooms.add(room.getRoomTypeId());
            }
        }
        return nonAcFoodRooms;
    }

    @Override
    public List<String> isAvailableAcRooms() {
        List<String> acRooms = new ArrayList<>();
        List<Room> rooms =  roomDAO.isAvailableRooms();

        for(Room room : rooms) {
            if(room.getRoomType().equalsIgnoreCase("A/C")) {
                acRooms.add(room.getRoomTypeId());
            }
        }
        return acRooms;
    }

    @Override
    public List<String> isAvailableAcFoodRooms() {
        List<String> acFoodRooms = new ArrayList<>();
        List<Room> rooms =  roomDAO.isAvailableRooms();

        for(Room room : rooms) {
            if(room.getRoomType().equalsIgnoreCase("A/C FOOD")) {
                acFoodRooms.add(room.getRoomTypeId());
            }
        }
        return acFoodRooms;
    }

    @Override
    public RoomDTO getRoomById(String roomId) {
         Room room = roomDAO.getRoomById(roomId);
         return new RoomDTO(
                 room.getRoomTypeId(),
                 room.getRoomType(),
                 room.getKeyMoney()
         );
    }
}
