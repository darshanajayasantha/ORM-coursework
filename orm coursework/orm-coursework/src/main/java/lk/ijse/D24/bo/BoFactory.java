package lk.ijse.D24.bo;

import lk.ijse.D24.bo.impl.PaymentBOImpl;
import lk.ijse.D24.bo.impl.ReservationBoImpl;
import lk.ijse.D24.bo.impl.RoomBoImpl;
import lk.ijse.D24.bo.impl.StudentBoImpl;

public class BoFactory {
    private static BoFactory boFactory;

    private BoFactory() {
    }

    public static BoFactory getBoFactory() {
        return boFactory == null ? boFactory = new BoFactory() : boFactory;
    }

    public enum BoTypes {
        STUDENT, ROOM,RESERVATION,PAYMENT
    }

    public SuperBo getBo(BoTypes boType) {
        switch (boType) {
            case STUDENT:
                return new StudentBoImpl();
            case ROOM:
                return new RoomBoImpl();
            case RESERVATION:
                return new ReservationBoImpl();
            case PAYMENT:
                return new PaymentBOImpl();
            default:
                return null;
        }
    }
}
