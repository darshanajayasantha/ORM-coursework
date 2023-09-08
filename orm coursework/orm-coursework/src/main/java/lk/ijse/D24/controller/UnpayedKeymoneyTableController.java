package lk.ijse.D24.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.D24.bo.BoFactory;
import lk.ijse.D24.bo.cutom.PaymentBO;
import lk.ijse.D24.bo.cutom.ReservationBO;
import lk.ijse.D24.bo.cutom.RoomBO;
import lk.ijse.D24.model.KeyMoneyDTO;
import lk.ijse.D24.model.ReservationDTO;
import lk.ijse.D24.model.ReservationDetailDTO;
import lk.ijse.D24.tm.KeyMoneyTM;
import lk.ijse.D24.tm.ReservationDetailTM;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UnpayedKeymoneyTableController implements Initializable {

    @FXML
    public Label lblRoomId;

    @FXML
    public TableColumn clmReservationId;

    @FXML
    public Label lblReservationId;

    @FXML
    public AnchorPane paymentDetailPane;

    @FXML
    private TableColumn<?, ?> clmName;

    @FXML
    private TableColumn<?, ?> clmPayedKeyMoney;

    @FXML
    private TableColumn<?, ?> clmRemainKeyMoney;

    @FXML
    private TableColumn<?, ?> clmRoomId;

    @FXML
    private TableColumn<?, ?> clmStudentId;

    @FXML
    private TableColumn<?, ?> clmTotalKeyMoney;

    @FXML
    private ComboBox<String> cmbFilter;

    @FXML
    private TableView<KeyMoneyTM> tblPaymentDetail;

    @FXML
    private TextField txtAddBalanceToKeyMoney;

    @FXML
    private TextField txtSeach;

    PaymentBO paymentBO = (PaymentBO) BoFactory.getBoFactory().getBo(BoFactory.BoTypes.PAYMENT);
    ReservationBO reservationBO = (ReservationBO) BoFactory.getBoFactory().getBo(BoFactory.BoTypes.RESERVATION);
    RoomBO roomBO = (RoomBO) BoFactory.getBoFactory().getBo(BoFactory.BoTypes.ROOM);

    List<KeyMoneyDTO> allKeyMoneyDetails;

    double price ;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setSellValueFactory();
        loadAllStillPayStudents();
        rowValuesSetToFields();
        setCmbSelectFilterTitle();
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
        clmTotalKeyMoney.setCellValueFactory(new PropertyValueFactory<>("totalKeyMoney"));
        clmPayedKeyMoney.setCellValueFactory(new PropertyValueFactory<>("payedAmount"));
        clmRemainKeyMoney.setCellValueFactory(new PropertyValueFactory<>("remainAmount"));
        clmReservationId.setCellValueFactory(new PropertyValueFactory<>("resId"));
    }

    void loadAllStillPayStudents() {
        ObservableList<KeyMoneyTM> keyMoneyTMS = FXCollections.observableArrayList();
        List<KeyMoneyDTO> keyMoneyDTOS = paymentBO.calculateStillKeyMoneyPayedStudents();

        for (KeyMoneyDTO keyMoneyDTO : keyMoneyDTOS) {
            keyMoneyTMS.add(new KeyMoneyTM(
               keyMoneyDTO.getStudentId(),
               keyMoneyDTO.getName(),
               keyMoneyDTO.getRoomId(),
               keyMoneyDTO.getTotalKeyMoney(),
               keyMoneyDTO.getPayedAmount(),
               keyMoneyDTO.getRemainAmount(),
               keyMoneyDTO.getResId()
            ));
        }
        tblPaymentDetail.setItems(keyMoneyTMS);
    }

    void rowValuesSetToFields() {
        tblPaymentDetail.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                lblRoomId.setText(tblPaymentDetail.getSelectionModel().getSelectedItem().getRoomId());
                lblReservationId.setText(tblPaymentDetail.getSelectionModel().getSelectedItem().getResId());
                price = tblPaymentDetail.getSelectionModel().getSelectedItem().getPayedAmount();
            }
        });
    }

    public void typesOnSearchBar(KeyEvent keyEvent) {
        String searchBarValue = txtSeach.getText();
        if (searchBarValue.isEmpty()) {
            loadAllStillPayStudents();
        }
    }

    public void clickedOnSearchBar(MouseEvent mouseEvent) {
        allKeyMoneyDetails = reservationBO.getKeyMoneyDetails();
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
                        keyMoneyDTO.getTotalKeyMoney(),
                        keyMoneyDTO.getPayedAmount(),
                        keyMoneyDTO.getRemainAmount(),
                        keyMoneyDTO.getResId()
                ));
                tblPaymentDetail.setItems(searchedRow);
                count++;
            }
        }
        if (count == 0) {
            new Alert(Alert.AlertType.INFORMATION, "No Reservation Found This Id..!").show();
            tblPaymentDetail.getItems();
        }
        else {
            count = 0;
        }
    }

    public void btnFilterOnAction(ActionEvent event) throws IOException {
        String filterTitle = (String) cmbFilter.getValue();

        if (filterTitle.equals("by reservation Id")) {
            URL resource = getClass().getResource("/view/room_reservation.fxml");
            assert resource != null;
            Parent load = FXMLLoader.load(resource);
            paymentDetailPane.getChildren().clear();
            paymentDetailPane.getChildren().add(load);

        } else if (filterTitle.equals("by payed keyMoney")) {
            URL resource = getClass().getResource("/view/by_payed_keymoney_table.fxml");
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

    public void btnAddBalanceToKeyMoney(ActionEvent event) {
        double updatedKeyMoney = price+Double.parseDouble(txtAddBalanceToKeyMoney.getText());
        reservationBO.addMoneyToKeyMoney(lblReservationId.getText(),updatedKeyMoney);
        loadAllStillPayStudents();
    }
}
