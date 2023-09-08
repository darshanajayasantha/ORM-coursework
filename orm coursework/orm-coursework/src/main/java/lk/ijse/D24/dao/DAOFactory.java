package lk.ijse.D24.dao;

import lk.ijse.D24.dao.impl.QueryDAOImpl;
import lk.ijse.D24.dao.impl.ReservationDAOImpl;
import lk.ijse.D24.dao.impl.RoomDAOImpl;
import lk.ijse.D24.dao.impl.StudentDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){}

    public static DAOFactory getDaoFactory() {
        return daoFactory == null ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DaoTypes {
        STUDENT,ROOM,RESERVATION,QUERY
    }

    public SuperDAO getDao(DaoTypes daoType){
        switch (daoType){
            case STUDENT:
                return new StudentDAOImpl();
            case ROOM:
                return new RoomDAOImpl();
            case RESERVATION:
                return new ReservationDAOImpl();
            case QUERY:
                return new QueryDAOImpl();
            default:
                return null;
        }
    }
}
