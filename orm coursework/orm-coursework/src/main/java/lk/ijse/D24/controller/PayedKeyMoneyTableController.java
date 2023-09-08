package lk.ijse.D24.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.D24.bo.BoFactory;
import lk.ijse.D24.bo.cutom.PaymentBO;
import lk.ijse.D24.bo.cutom.ReservationBO;
import lk.ijse.D24.model.KeyMoneyDTO;
import lk.ijse.D24.tm.KeyMoneyTM;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PayedKeyMoneyTableController implements Initializable {

    @FXML
    private TableColumn<?, ?> clmName;

    @FXML
    private TableColumn<?, ?> clmReservationId;

    @FXML
    private TableColumn<?, ?> clmRoomId;

    @FXML
    private TableColumn<?, ?> clmStudentId;

    @FXML
    private ComboBox<String> cmbFilter;

    @FXML
    private AnchorPane paymentDetailPane;

    @FXML
    private TableView<KeyMoneyTM> tblPaymentDetail;

    @FXML
    private TextField txtSeach;

    PaymentBO paymentBO = (PaymentBO) BoFactory.getBoFactory().getBo(BoFactory.BoTypes.PAYMENT);
    ReservationBO reservationBO = (ReservationBO) BoFactory.getBoFactory().getBo(BoFactory.BoTypes.RESERVATION);

    List<KeyMoneyDTO> allKeyMoneyDetails;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCmbSelectFilterTitle();
        setSellValueFactory();
        loadAllStillPayStudents();
    }

    void setCmbSelectFilterTitle() {
        cmbFilter.getItems().add("by reservation Id");
        cmbFilter.getItems().add("by student Id");
        cmbFilter.getItems().add("by payed keyMoney");
        cmbFilter.getItems().add("by unpayed keyMoney");
    }

    void setSellValueFactory() {
        clmStudentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        clmName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clmRoomId.setCellValueFactory(new PropertyValueFactory<>("roomId"));
        clmReservationId.setCellValueFactory(new PropertyValueFactory<>("resId"));
    }

    void loadAllStillPayStudents() {
        ObservableList<KeyMoneyTM> keyMoneyTMS = FXCollections.observableArrayList();
        List<KeyMoneyDTO> keyMoneyDTOS = paymentBO.calculateKeyMoneyPayedStudents();

        for (KeyMoneyDTO keyMoneyDTO : keyMoneyDTOS) {
            keyMoneyTMS.add(new KeyMoneyTM(
                    keyMoneyDTO.getStudentId(),
                    keyMoneyDTO.getName(),
                    keyMoneyDTO.getRoomId(),
                    keyMoneyDTO.getResId()
            ));
        }
        tblPaymentDetail.setItems(keyMoneyTMS);
    }

    public void typesOnSearchBar(javafx.scene.input.KeyEvent keyEvent) {
        String searchBarValue = txtSeach.getText();
        if (searchBarValue.isEmpty()) {
            loadAllStillPayStudents();
        }
    }

    int count = 0;
    public void btnSeacrByStudentIdOnAction(ActionEvent event) {
        ObservableList<KeyMoneyTM> searchedRow = FXCollections.observableArrayList();

        String valueOnSerchBar = txtSeach.getText();

        for (KeyMoneyDTO keyMoneyDTO : allKeyMoneyDetails) {

            if (valueOnSerchBar.equalsIgnoreCase(keyMoneyDTO.getStudentId())) {
                searchedRow.add(new KeyMoneyTM(
                        keyMoneyDTO.getStudentId(),
                        keyMoneyDTO.getName(),
                        keyMoneyDTO.getRoomId(),
                        keyMoneyDTO.getResId()
                ));
                tblPaymentDetail.setItems(searchedRow);
                count++;
            }
        }
    }

    public void clickedOnSearchBar(MouseEvent mouseEvent) {
        allKeyMoneyDetails = reservationBO.getKeyMoneyDetails();
    }

    public void btnFilterOnAction(ActionEvent event) throws IOException {
        String filterTitle = (String) cmbFilter.getValue();

        if (filterTitle.equals("by reservation Id")) {
            URL resource = getClass().getResource("/view/room_reservation.fxml");
            assert resource != null;
            Parent load = FXMLLoader.load(resource);
            paymentDetailPane.getChildren().clear();
            paymentDetailPane.getChildren().add(load);

        } else if (filterTitle.equals("by unpayed keyMoney")) {
            URL resource = getClass().getResource("/view/by_unpayed_keymoney_table.fxml");
            assert resource != null;
            Parent load = FXMLLoader.load(resource);
            paymentDetailPane.getChildren().clear();
            paymentDetailPane.getChildren().add(load);

        }else if (filterTitle.equals("by student Id")) {
            URL resource = getClass().getResource("/view/by_student_id_table.fxml");
            assert resource != null;
            Parent load = FXMLLoader.load(resource);
            paymentDetailPane.getChildren().clear();
            paymentDetailPane.getChildren().add(load);

        }
    }
}
