package lk.ijse.D24.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.D24.bo.BoFactory;
import lk.ijse.D24.bo.cutom.RoomBO;
import lk.ijse.D24.dao.DAOFactory;
import lk.ijse.D24.model.RoomDTO;
import lk.ijse.D24.tm.RoomTM;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class RoomController implements Initializable {

    @FXML
    public AnchorPane roomPane;

    @FXML
    public TextField txtNonAcKeyMoney;

    @FXML
    public TextField txtNonAcKeyMoneyFood;

    @FXML
    public TextField txtAcKeyMoney;

    @FXML
    public TextField txtAcKeyMoneyFood;

    @FXML
    private TableColumn<?, ?> clmKeyMoney;

    @FXML
    private TableColumn<?, ?> clmRoomId;

    @FXML
    private TableColumn<?, ?> clmRoomType;

    @FXML
    private ComboBox<String> cmbRoomType;

    @FXML
    private Label lblAcFoodCount;

    @FXML
    private Label lblAcRoomCount;

    @FXML
    private Label lblNonAcFoodRoomCount;

    @FXML
    private Label lblNonAcRoomCount;

    @FXML
    private TableView<RoomTM> tblRoomDetails;

    @FXML
    private TextField txtKeyMoney;

    @FXML
    private TextField txtRoomId;

    RoomBO roomBO = (RoomBO) BoFactory.getBoFactory().getBo(BoFactory.BoTypes.ROOM);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        genarateNextRoomId();
        setValuesForKeyMoneyLabels();
        setRoomTypeToCmb();
        loadDataToTable();
        setCellValueFactory();
        loadRowDataToFields();
        setRoomCountForLabel();
        setKeyMoneyForFieldAfterChoosenRoomType();
    }

    RoomDTO createNewRoom() {
        String roomId = txtRoomId.getText();
        String roomType = cmbRoomType.getValue();
        double keyMoney = Double.parseDouble(txtKeyMoney.getText());
        RoomDTO roomDTO = new RoomDTO(roomId,roomType,keyMoney);
        return roomDTO;
    }

    void genarateNextRoomId() {
        txtRoomId.setText(roomBO.genarateNextRoomId());
    }

    void setRoomTypeToCmb() {
        cmbRoomType.getItems().add("A/C");
        cmbRoomType.getItems().add("A/C FOOD");
        cmbRoomType.getItems().add("NON A/C");
        cmbRoomType.getItems().add("NON A/C FOOD");
    }

    void setCellValueFactory() {
        clmRoomId.setCellValueFactory(new PropertyValueFactory<>("roomTypeId"));
        clmRoomType.setCellValueFactory(new PropertyValueFactory<>("roomType"));
        clmKeyMoney.setCellValueFactory(new PropertyValueFactory<>("keyMoney"));
    }

    void loadDataToTable() {
        ObservableList<RoomTM> roomTMS = FXCollections.observableArrayList();
        List<RoomDTO> allRoomData = roomBO.getAllRoomData();

        for (RoomDTO roomDTO : allRoomData) {
            roomTMS.add(new RoomTM(
                    roomDTO.getRoomTypeId(),
                    roomDTO.getRoomType(),
                    roomDTO.getKeyMoney()
            ));
        }
        tblRoomDetails.setItems(roomTMS);
    }

    void loadRowDataToFields() {
        tblRoomDetails.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                txtRoomId.setText(tblRoomDetails.getSelectionModel().getSelectedItem().getRoomTypeId());
                cmbRoomType.setValue(tblRoomDetails.getSelectionModel().getSelectedItem().getRoomType());
                txtKeyMoney.setText(String.valueOf(tblRoomDetails.getSelectionModel().getSelectedItem().getKeyMoney()));
            }
        });
    }

    void clearFields() {
        genarateNextRoomId();
        cmbRoomType.setValue("");
        txtKeyMoney.clear();
    }

    void setKeyMoneyForFieldAfterChoosenRoomType() {
        cmbRoomType.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
               if(newValue.equalsIgnoreCase("NON A/C")){
                   txtKeyMoney.setText(txtNonAcKeyMoney.getText());
               }
                else if(newValue.equalsIgnoreCase("NON A/C FOOD")){
                    txtKeyMoney.setText(txtNonAcKeyMoneyFood.getText());
                }
                else if(newValue.equalsIgnoreCase("A/C")){
                    txtKeyMoney.setText(txtAcKeyMoney.getText());
                }
                if(newValue.equalsIgnoreCase("A/C FOOD")){
                    txtKeyMoney.setText(txtAcKeyMoneyFood.getText());
                }
            }
        });
    }

    void setValuesForKeyMoneyLabels() {
        List<Double> keyMoneylist = roomBO.getKeyMoneyamount();

        txtNonAcKeyMoney.setText(String.valueOf(keyMoneylist.get(2)));
        txtNonAcKeyMoneyFood.setText(String.valueOf(keyMoneylist.get(3)));
        txtAcKeyMoney.setText(String.valueOf(keyMoneylist.get(0)));
        txtAcKeyMoneyFood.setText(String.valueOf(keyMoneylist.get(1)));
    }

    @FXML
    public void btnUpdateKeyMoneyOnAction(ActionEvent event) {
        roomBO.updateKeymoneyAmount( txtAcKeyMoney.getText(), txtAcKeyMoneyFood.getText(), txtNonAcKeyMoney.getText(),  txtNonAcKeyMoneyFood.getText());
    }

    @FXML
    void btnAddRoomOnAction(ActionEvent event) {
        roomBO.addNewRoom(createNewRoom());
        genarateNextRoomId();
        loadDataToTable();
        clearFields();
        setRoomCountForLabel();
    }

    @FXML
    void btnUpdateRoomOnAction(ActionEvent event) {
        roomBO.updateRoom(createNewRoom());
        genarateNextRoomId();
        loadDataToTable();
        clearFields();
        setRoomCountForLabel();
    }

    @FXML
    void btnDeleteRoomAction(ActionEvent event) {
        String roomId = txtRoomId.getText();
        roomBO.deleteRoom(roomId);
        genarateNextRoomId();
        loadDataToTable();
        clearFields();
        setRoomCountForLabel();
    }

    void setRoomCountForLabel() {
        lblAcFoodCount.setText(String.valueOf(roomBO.acRoomWithFoodCount()));
        lblAcRoomCount.setText(String.valueOf(roomBO.acRoomWithoutFoodCount()));
        lblNonAcFoodRoomCount.setText(String.valueOf(roomBO.nonAcWithFoodRoomCount()));
        lblNonAcRoomCount.setText(String.valueOf(roomBO.nonAcWithoutFoodRoomCount()));
    }
}
