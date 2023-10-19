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
public class FXML_DisplayFilmController implements Initializable {

    @FXML
    private TableView<ModelFilm> tbvfilm;
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
        ObservableList<ModelFilm> data = FXMLDocumentController.dtfilm.Load();
        if (data != null) {
            tbvfilm.getColumns().clear();
            tbvfilm.getItems().clear();
            TableColumn col = new TableColumn("kodefilm");
            col.setCellValueFactory(new PropertyValueFactory<ModelFilm, String>("kodefilm"));
            tbvfilm.getColumns().addAll(col);
            col = new TableColumn("namafilm");
            col.setCellValueFactory(new PropertyValueFactory<ModelFilm, String>("namafilm"));
            tbvfilm.getColumns().addAll(col);
            col = new TableColumn("genre");
            col.setCellValueFactory(new PropertyValueFactory<ModelFilm, String>("genre"));
            tbvfilm.getColumns().addAll(col);
            col = new TableColumn("tahun");
            col.setCellValueFactory(new PropertyValueFactory<ModelFilm, String>("tahun"));
            tbvfilm.getColumns().addAll(col);
            col = new TableColumn("harga");
            col.setCellValueFactory(new PropertyValueFactory<ModelFilm, String>("harga"));
            tbvfilm.getColumns().addAll(col);
            col = new TableColumn("gambar");
            col.setCellValueFactory(new PropertyValueFactory<ModelFilm, String>("gambar"));
            tbvfilm.getColumns().addAll(col);
            tbvfilm.setItems(data);
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Data kosong", ButtonType.OK);
            a.showAndWait();
            tbvfilm.getScene().getWindow().hide();;
        }
    }

    @FXML
    private void btnawalklik(ActionEvent event) {
        tbvfilm.getSelectionModel().selectFirst();
        tbvfilm.requestFocus();
    }

    @FXML
    private void btnprevklik(ActionEvent event) {
        tbvfilm.getSelectionModel().selectAboveCell();
        tbvfilm.requestFocus();
    }

    @FXML
    private void btnnextklik(ActionEvent event) {
        tbvfilm.getSelectionModel().selectBelowCell();
        tbvfilm.requestFocus();
    }

    @FXML
    private void btnakhirklik(ActionEvent event) {
        tbvfilm.getSelectionModel().selectLast();
        tbvfilm.requestFocus();
    }

    @FXML
    private void btnubahklik(ActionEvent event) {
        ModelFilm s = new ModelFilm();
        s = tbvfilm.getSelectionModel().getSelectedItem();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_InputFilm.fxml"));
            Parent root = (Parent) loader.load();
            FXML_InputFilmController isidt = (FXML_InputFilmController) loader.getController();
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
        ModelFilm s = new ModelFilm();
        s = tbvfilm.getSelectionModel().getSelectedItem();
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Mau dihapus?", ButtonType.YES, ButtonType.NO);
        a.showAndWait();
        if (a.getResult() == ButtonType.YES) {
            if (FXMLDocumentController.dtfilm.delete(s.getKodefilm())) {
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_InputFilm.fxml"));
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
