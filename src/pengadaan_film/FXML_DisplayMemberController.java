/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package pengadaan_film;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class FXML_DisplayMemberController implements Initializable {

    @FXML
    private TableView<ModelMember> tbvmember;
    @FXML
    private Button btnawal;
    @FXML
    private Button btnprev;
    @FXML
    private Button btnnext;
    @FXML
    private Button btnakhir;
    @FXML
    private Button btnubah;
    @FXML
    private Button btnhapus;
    @FXML
    private Button btntambah;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showdata();
    }

    public void showdata() {
        ObservableList<ModelMember> data = FXMLDocumentController.dtmember.Load();
        if (data != null) {
            tbvmember.getColumns().clear();
            tbvmember.getItems().clear();
            TableColumn col = new TableColumn("idmember");
            col.setCellValueFactory(new PropertyValueFactory<ModelMember, String>("idmember"));
            tbvmember.getColumns().addAll(col);
            col = new TableColumn("namamember");
            col.setCellValueFactory(new PropertyValueFactory<ModelMember, String>("namamember"));
            tbvmember.getColumns().addAll(col);
            col = new TableColumn("whatsapp");
            col.setCellValueFactory(new PropertyValueFactory<ModelMember, String>("whatsapp"));
            tbvmember.getColumns().addAll(col);
            col = new TableColumn("instagram");
            col.setCellValueFactory(new PropertyValueFactory<ModelMember, String>("instagram"));
            tbvmember.getColumns().addAll(col);
            col = new TableColumn("total");
            col.setCellValueFactory(new PropertyValueFactory<ModelMember, String>("total"));
            tbvmember.getColumns().addAll(col);
            col = new TableColumn("status");
            col.setCellValueFactory(new PropertyValueFactory<ModelMember, String>("status"));
            tbvmember.getColumns().addAll(col);
            tbvmember.setItems(data);
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Data kosong", ButtonType.OK);
            a.showAndWait();
            tbvmember.getScene().getWindow().hide();;
        }
    }

    @FXML
    private void btnawalklik(ActionEvent event) {
        tbvmember.getSelectionModel().selectFirst();
        tbvmember.requestFocus();
    }

    @FXML
    private void btnprevklik(ActionEvent event) {
        tbvmember.getSelectionModel().selectAboveCell();
        tbvmember.requestFocus();
    }

    @FXML
    private void btnnextklik(ActionEvent event) {
        tbvmember.getSelectionModel().selectBelowCell();
        tbvmember.requestFocus();
    }

    @FXML
    private void btnakhirklik(ActionEvent event) {
        tbvmember.getSelectionModel().selectLast();
        tbvmember.requestFocus();
    }

    @FXML
    private void btnubahklik(ActionEvent event) {
        ModelMember s = new ModelMember();
        s = tbvmember.getSelectionModel().getSelectedItem();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_InputMember.fxml"));
            Parent root = (Parent) loader.load();
            FXML_InputMemberController isidt = (FXML_InputMemberController) loader.getController();
            isidt.execute(s);
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
        showdata();
        btnawalklik(event);
    }

    @FXML
    private void btnhapusklik(ActionEvent event) {
        ModelMember s = new ModelMember();
        s = tbvmember.getSelectionModel().getSelectedItem();
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Mau dihapus?", ButtonType.YES, ButtonType.NO);
        a.showAndWait();
        if (a.getResult() == ButtonType.YES) {
            if (FXMLDocumentController.dtmember.delete(s.getIdmember())) {
                Alert b = new Alert(Alert.AlertType.INFORMATION, "Data berhasil dihapus", ButtonType.OK);
                b.showAndWait();
            } else {
                Alert b = new Alert(Alert.AlertType.ERROR, "Data gagal dihapus", ButtonType.OK);
                b.showAndWait();
            }
            showdata();
            btnawalklik(event);
        }
    }

    @FXML
    private void btntambahklik(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_InputMember.fxml"));
            Parent root = (Parent) loader.load();
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
        showdata();
        btnawalklik(event);

    }
}
