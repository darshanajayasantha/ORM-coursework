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
import lk.ijse.D24.bo.cutom.ReservationBO;
import lk.ijse.D24.bo.cutom.RoomBO;
import lk.ijse.D24.bo.cutom.StudentBO;
import lk.ijse.D24.model.ReservationDTO;
import lk.ijse.D24.model.ReservationDetailDTO;
import lk.ijse.D24.model.RoomDTO;
import lk.ijse.D24.model.StudentDTO;
import lk.ijse.D24.tm.ReservationDetailTM;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class SearchByStudentIdController implements Initializable {

    @FXML
    public ComboBox<String> cmbRoomId;

    @FXML
    public TableColumn<?,?> clmRoomId;

    @FXML
    private TableColumn<?, ?> clmAddress;

    @FXML
    private TableColumn<?, ?> clmContact;

    @FXML
    private TableColumn<?, ?> clmDob;

    @FXML
    private TableColumn<?, ?> clmGender;

    @FXML
    private TableColumn<?, ?> clmMoney;

    @FXML
    private TableColumn<?, ?> clmName;

    @FXML
    private TableColumn<?, ?> clmRestDate;

    @FXML
    private TableColumn<?, ?> clmRestId;

    @FXML
    private TableColumn<?, ?> clmRoomType;

    @FXML
    private TableColumn<?, ?> clmStudentId;

    @FXML
    private ComboBox<String> cmbFilter;

    @FXML
    private ComboBox<String> cmbGender;

    @FXML
    private ComboBox<String> cmbRoomType;

    @FXML
    private AnchorPane reservationPane;

    @FXML
    private TableView<ReservationDetailTM> tblReservationDetail;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContact;

    @FXML
    private DatePicker txtDob;

    @FXML
    private TextField txtKeyMoneyAmount;

    @FXML
    private TextField txtReservationId;

    @FXML
    private TextField txtSeach;

    @FXML
    private TextField txtStudentId;

    @FXML
    private TextField txtStudentName;

    StudentBO studentBO = (StudentBO) BoFactory.getBoFactory().getBo(BoFactory.BoTypes.STUDENT);
    ReservationBO reservationBO = (ReservationBO) BoFactory.getBoFactory().getBo(BoFactory.BoTypes.RESERVATION);
    RoomBO roomBO = (RoomBO) BoFactory.getBoFactory().getBo(BoFactory.BoTypes.ROOM);

    List<ReservationDetailDTO> allReservationDetails;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtReservationId.setText(reservationBO.genarateNextReservationId());
        setGenderForComboBox();
        setRoomTypeForComboBox();
        setCmbSelectFilterTitle();
        setCellValueFactory();
        loadDataToTable();
        loadRowDataToFields();
        setAvailableRoomsForComboBox();
    }

    StudentDTO makeStudentDTO() {
        String studentId = txtStudentId.getText();
        String studentName = txtStudentName.getText();
        String address = txtAddress.getText();
        int contact = Integer.parseInt(txtContact.getText());
        LocalDate dob = txtDob.getValue();
        String gender = cmbGender.getValue();

        StudentDTO studentDTO = new StudentDTO(studentId, studentName, address, contact, dob, gender);
        return studentDTO;
    }

    ReservationDTO makeReservationDTO() {
        String reservationId = txtReservationId.getText();
        java.util.Date date = new java.util.Date();
        java.sql.Date registerDate = new java.sql.Date(date.getTime());
        String studentId = txtStudentId.getText();
        String roomType = cmbRoomType.getValue();
        double keymoney = Double.parseDouble(txtKeyMoneyAmount.getText());
        String roomId =  cmbRoomId.getValue();

        ReservationDTO reservationDTO = new ReservationDTO(reservationId, studentId, registerDate, roomType, keymoney, roomId);
        return reservationDTO;
    }

    RoomDTO getRoomById() {
        RoomDTO roomDTO = roomBO.getRoomById(cmbRoomId.getValue());
        return roomDTO;
    }

    void clearFields() {
        txtReservationId.setText(reservationBO.genarateNextReservationId());
        txtStudentId.clear();
        txtStudentName.clear();
        txtAddress.clear();
        txtContact.clear();
        txtDob.getEditor().clear();
        txtKeyMoneyAmount.clear();
        cmbRoomType.setValue("");
        cmbGender.setValue("");
        loadDataToTable();
    }

    void setGenderForComboBox() {
        cmbGender.getItems().add("Men");
        cmbGender.getItems().add("Women");
    }

    void setRoomTypeForComboBox() {
        cmbRoomType.getItems().add("Non A/C");
        cmbRoomType.getItems().add("NON A/C FOOD");
        cmbRoomType.getItems().add("A/C");
        cmbRoomType.getItems().add("A/C FOOD");
    }

    void setCmbSelectFilterTitle() {
        cmbFilter.getItems().add("by reservation Id");
        cmbFilter.getItems().add("by student Id");
        cmbFilter.getItems().add("by payed keyMoney");
        cmbFilter.getItems().add("by unpayed keyMoney");
    }

    void setAvailableRoomsForComboBox() {
        cmbRoomType.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                if(newValue.equalsIgnoreCase("NON A/C")){
                    ObservableList<String> rooms = FXCollections.observableArrayList();
                    List<String>  nonAcRooms = roomBO.isAvailableNonAcRooms();
                    for (String room : nonAcRooms) {
                        rooms.add(room);
                    }
                    cmbRoomId.setItems(rooms);
                }
                else if(newValue.equalsIgnoreCase("NON A/C FOOD")){
                    ObservableList<String> rooms = FXCollections.observableArrayList();
                    List<String>  nonAcFoodRooms = roomBO.isAvailableNonAcFoodRooms();
                    for (String room : nonAcFoodRooms) {
                        rooms.add(room);
                    }
                    cmbRoomId.setItems(rooms);
                }
                else if(newValue.equalsIgnoreCase("A/C")){
                    ObservableList<String> rooms = FXCollections.observableArrayList();
                    List<String>  acRooms = roomBO.isAvailableAcRooms();
                    for (String room : acRooms) {
                        rooms.add(room);
                    }
                    cmbRoomId.setItems(rooms);
                }
                if(newValue.equalsIgnoreCase("A/C FOOD")){
                    ObservableList<String> rooms = FXCollections.observableArrayList();
                    List<String>  acFoodRooms = roomBO.isAvailableAcFoodRooms();
                    for (String room : acFoodRooms) {
                        rooms.add(room);
                    }
                    cmbRoomId.setItems(rooms);
                }
            }
        });
    }

    void setCellValueFactory() {
        clmStudentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        clmAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        clmContact.setCellValueFactory(new PropertyValueFactory<>("contactNo"));
        clmDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        clmGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        clmMoney.setCellValueFactory(new PropertyValueFactory<>("keyMoney"));
        clmName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        clmRestId.setCellValueFactory(new PropertyValueFactory<>("resId"));
        clmRoomType.setCellValueFactory(new PropertyValueFactory<>("roomType"));
        clmRestDate.setCellValueFactory(new PropertyValueFactory<>("resDate"));
        clmRoomId.setCellValueFactory(new PropertyValueFactory<>("roomId"));
    }


    void loadDataToTable() {
        ObservableList<ReservationDetailTM> tableData = FXCollections.observableArrayList();

        List<ReservationDetailDTO> allDetails = reservationBO.getAllReservationDetail();

        for (ReservationDetailDTO reservationDetailDTO : allDetails) {
            tableData.add(new ReservationDetailTM(
                    reservationDetailDTO.getResId(),
                    reservationDetailDTO.getStudentId(),
                    reservationDetailDTO.getStudentName(),
                    reservationDetailDTO.getResDate(),
                    reservationDetailDTO.getRoomType(),
                    reservationDetailDTO.getKeyMoney(),
                    reservationDetailDTO.getGender(),
                    reservationDetailDTO.getDob(),
                    reservationDetailDTO.getAddress(),
                    reservationDetailDTO.getContactNo(),
                    reservationDetailDTO.getRoomId()
            ));
        }
        tblReservationDetail.setItems(tableData);
    }

    void loadRowDataToFields() {
        tblReservationDetail.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                txtStudentId.setText(tblReservationDetail.getSelectionModel().getSelectedItem().getStudentId());
                txtReservationId.setText(tblReservationDetail.getSelectionModel().getSelectedItem().getResId());
                txtStudentName.setText(tblReservationDetail.getSelectionModel().getSelectedItem().getStudentName());
                txtContact.setText(String.valueOf(tblReservationDetail.getSelectionModel().getSelectedItem().getContactNo()));
                txtAddress.setText(tblReservationDetail.getSelectionModel().getSelectedItem().getAddress());
                txtKeyMoneyAmount.setText(String.valueOf(tblReservationDetail.getSelectionModel().getSelectedItem().getKeyMoney()));
                cmbRoomType.setValue(tblReservationDetail.getSelectionModel().getSelectedItem().getRoomType());
                cmbGender.setValue(tblReservationDetail.getSelectionModel().getSelectedItem().getGender());
                txtDob.setValue(tblReservationDetail.getSelectionModel().getSelectedItem().getDob());
            }
        });
    }

    @FXML
    public void btnMakeRoomReservationOnAction(ActionEvent event) {
        reservationBO.makeReservation(makeStudentDTO(), makeReservationDTO(), getRoomById());
    }

    @FXML
    public void btnUpdateReservationDetailOnAction(ActionEvent event) {
        reservationBO.updateReservationDetails(makeStudentDTO(), makeReservationDTO(), getRoomById());
        loadDataToTable();
        clearFields();
    }

    @FXML
    public void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    public void btnFilterOnAction(ActionEvent event) throws IOException {
        String filterTitle = (String) cmbFilter.getValue();

        if (filterTitle.equals("by reservation Id")) {
            URL resource = getClass().getResource("/view/room_reservation.fxml");
            assert resource != null;
            Parent load = FXMLLoader.load(resource);
            reservationPane.getChildren().clear();
            reservationPane.getChildren().add(load);

        } else if (filterTitle.equals("by payed keyMoney")) {
            URL resource = getClass().getResource("/view/by_payed_keymoney_table.fxml");
            assert resource != null;
            Parent load = FXMLLoader.load(resource);
            reservationPane.getChildren().clear();
            reservationPane.getChildren().add(load);

        } else if (filterTitle.equals("by unpayed keyMoney")) {
            URL resource = getClass().getResource("/view/by_unpayed_keymoney_table.fxml");
            assert resource != null;
            Parent load = FXMLLoader.load(resource);
            reservationPane.getChildren().clear();
            reservationPane.getChildren().add(load);

        }
    }

    public void clickedOnSearchBar(MouseEvent mouseEvent) {
        allReservationDetails = reservationBO.getAllReservationDetail();
    }

    public void typesOnSearchBar(KeyEvent keyEvent) {
        String searchBarValue = txtSeach.getText();
        if (searchBarValue.isEmpty()) {
            loadDataToTable();
        }
    }

    int count = 0;
    public void btnSeacrByStudentIdOnAction(ActionEvent event) {
        ObservableList<ReservationDetailTM> searchedRow = FXCollections.observableArrayList();

        String valueOnSerchBar = txtSeach.getText();

        for (ReservationDetailDTO reservationDetailDTO : allReservationDetails) {

            if (valueOnSerchBar.equalsIgnoreCase(reservationDetailDTO.getStudentId())) {
                searchedRow.add(new ReservationDetailTM(
                        reservationDetailDTO.getResId(),
                        reservationDetailDTO.getStudentId(),
                        reservationDetailDTO.getStudentName(),
                        reservationDetailDTO.getResDate(),
                        reservationDetailDTO.getRoomType(),
                        reservationDetailDTO.getKeyMoney(),
                        reservationDetailDTO.getGender(),
                        reservationDetailDTO.getDob(),
                        reservationDetailDTO.getAddress(),
                        reservationDetailDTO.getContactNo(),
                        reservationDetailDTO.getRoomId()
                ));
                tblReservationDetail.setItems(searchedRow);
                count++;
            }
        }
        if (count == 0) {
            new Alert(Alert.AlertType.INFORMATION, "No Reservation Found This Id..!").show();
            tblReservationDetail.getItems();
        }
        else {
            count = 0;
        }
    }
}

