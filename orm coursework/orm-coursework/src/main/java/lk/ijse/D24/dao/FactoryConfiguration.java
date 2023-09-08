package lk.ijse.D24.dao;

import lk.ijse.D24.entity.KeyMoney;
import lk.ijse.D24.entity.Reservation;
import lk.ijse.D24.entity.Room;
import lk.ijse.D24.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private SessionFactory sessionFactory;

    private FactoryConfiguration() {
        Configuration configuration = new Configuration().configure().addAnnotatedClass(Student.class).addAnnotatedClass(Room.class).addAnnotatedClass(Reservation.class).addAnnotatedClass(Room.class).addAnnotatedClass(KeyMoney.class);
        sessionFactory = configuration.buildSessionFactory();
    }
    public static FactoryConfiguration getFactoryConfiguration(){
        return factoryConfiguration == null ? factoryConfiguration = new FactoryConfiguration() : factoryConfiguration;
    }
    public Session getSession(){
        return sessionFactory.openSession();
    }
}
