package lk.ijse.D24.dao.impl;

import lk.ijse.D24.dao.FactoryConfiguration;
import lk.ijse.D24.dao.QueryDAO;
import lk.ijse.D24.dao.SuperDAO;
import lk.ijse.D24.dao.cutom.RoomDAO;
import lk.ijse.D24.entity.Reservation;
import lk.ijse.D24.entity.Room;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class RoomDAOImpl implements RoomDAO {
    @Override
    public void save(Room entity) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        session.persist(entity);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(String id) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("delete from Room where roomTypeId = :roomId");
        query.setParameter("roomId",id);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void update(Room entity) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(entity);
        transaction.commit();
        session.close();
    }

    @Override
    public Room search(String id) {
        return null;
    }

    @Override
    public List<Room> getAll() {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("from Room");
        List<Room> roomList = query.list();
        transaction.commit();
        session.close();
        return roomList;
    }

    @Override
    public String getLastRoomId() {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("select roomTypeId from Room order by roomTypeId desc limit 1");
        String lastRoomId = (String) query.uniqueResult();
        transaction.commit();
        session.close();
        return lastRoomId;
    }

    @Override
    public int acRoomWithFoodCount() {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("select count(*) from Room where roomType = ?1",Long.class);
        query.setParameter(1,"A/C FOOD");
        Long roomCount = (Long) query.uniqueResult();
        transaction.commit();
        session.close();
        return Math.toIntExact(roomCount);
    }

    @Override
    public int acRoomWithoutFoodCount() {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("select count(*) from Room where roomType = ?1",Long.class);
        query.setParameter(1,"A/C");
        Long roomCount = (Long) query.uniqueResult();
        transaction.commit();
        session.close();
        return Math.toIntExact(roomCount);
    }

    @Override
    public int nonAcWithFoodRoomCount() {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("select count(*) from Room where roomType = ?1",Long.class);
        query.setParameter(1,"NON A/C FOOD");
        Long roomCount = (Long) query.uniqueResult();
        transaction.commit();
        session.close();
        return Math.toIntExact(roomCount);
    }

    @Override
    public int nonAcWithoutFoodRoomCount() {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("select count(*) from Room where roomType = ?1",Long.class);
        query.setParameter(1,"NON A/C");
        Long roomCount = (Long) query.uniqueResult();
        transaction.commit();
        session.close();
        return Math.toIntExact(roomCount);
    }

    @Override
    public List<Double> getKeyMoneyamount() {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("SELECT e.keyMoneyAmount FROM KeyMoney e",Double.class);
        List<Double> keyMoneyList =  query.list();
        transaction.commit();
        session.close();
        return keyMoneyList;
    }

    @Override
    public void updateKeyMoneyAmount(double k, double k1, double k2, double k3) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("UPDATE KeyMoney e SET e.keyMoneyAmount = :newValue WHERE e.roomType = :roomtype");

        query.setParameter("newValue", k); // Set the new value here
        query.setParameter("roomtype", "a/c"); // Set the identifier value here

        query.executeUpdate();

        query.setParameter("newValue", k1); // Set the new value here
        query.setParameter("roomtype", "a/c food"); // Set the identifier value here

        query.executeUpdate();

        query.setParameter("newValue", k2); // Set the new value here
        query.setParameter("roomtype", "non a/c"); // Set the identifier value here

        query.executeUpdate();

        query.setParameter("newValue", k3); // Set the new value here
        query.setParameter("roomtype", "non a/c food"); // Set the identifier value here

        query.executeUpdate();

        transaction.commit();
        session.close();
    }

    @Override
    public List<Room> isAvailableRooms() {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("SELECT entity FROM Room entity WHERE entity.student IS NULL");
        List<Room> rooms = query.list();
        transaction.commit();
        session.close();
        return rooms;
    }

    @Override
    public Room getRoomById(String roomId) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Room where roomTypeId = :a ",Room.class);
        query.setParameter("a",roomId);
        Room room = (Room) query.uniqueResult();
        transaction.commit();
        session.close();
        System.out.println(room.getRoomTypeId());
        System.out.println(room.getRoomType());
        System.out.println(room.getKeyMoney());
        return room;
    }
}
