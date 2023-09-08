package lk.ijse.D24.dao.impl;

import lk.ijse.D24.dao.FactoryConfiguration;
import lk.ijse.D24.dao.cutom.ReservationDAO;
import lk.ijse.D24.entity.Reservation;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ReservationDAOImpl implements ReservationDAO {
    @Override
    public String getLastReseavationId() {
            Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("select resId from Reservation order by resId desc limit 1");
            String lastResId = (String) query.uniqueResult();
            transaction.commit();
            session.close();
            return lastResId;
    }

    @Override
    public void addMoneyToKeyMoney(String resId, double amount) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("UPDATE Reservation SET payedKeyMoney = :amount where resId = :id");
        query.setParameter("amount", amount);
        query.setParameter("id", resId);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public List<Reservation> getAll() {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("from Reservation");
        List<Reservation> reservationDetails = query.list();
        transaction.commit();
        session.close();
        return reservationDetails;
    }

    @Override
    public void save(Reservation reservation) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        session.persist(reservation);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void update(Reservation reservation) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(reservation);
        transaction.commit();
        session.close();
    }

    @Override
    public Reservation search(String resId) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("From Reservation where resId=?1");
        query.setParameter(1,resId);
        Reservation reservation = (Reservation) query.uniqueResult();
        transaction.commit();
        session.close();
        return reservation;
    }
}
