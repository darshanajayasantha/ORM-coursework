package lk.ijse.D24.dao.impl;


import lk.ijse.D24.dao.DAOFactory;
import lk.ijse.D24.dao.FactoryConfiguration;
import lk.ijse.D24.dao.cutom.ReservationDAO;
import lk.ijse.D24.dao.cutom.StudentDAO;
import lk.ijse.D24.entity.Reservation;
import lk.ijse.D24.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    @Override
    public void save(Student student) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(student);
        transaction.commit();
        session.close();;
    }

    @Override
    public void delete(String id) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        session.remove(id);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(Student entity) {

    }

    @Override
    public Student search(String id) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        NativeQuery nativeQuery = session.createNativeQuery("SELECT * FROM student WHERE studentId = ?",id);
        nativeQuery.addEntity(Student.class);
        Student student = (Student) nativeQuery.uniqueResult();
        transaction.commit();
        session.close();
        return student;
    }

    @Override
    public List<Student> getAll() {
        return null;
    }
}
