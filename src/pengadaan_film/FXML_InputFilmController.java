/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package pengadaan_film;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class FXML_InputFilmController implements Initializable {

    private boolean editdata = false;

    @FXML
    private Button btnsave;
    @FXML
    private Button btncancel;
    @FXML
    private TextField txtkodefilm;
    @FXML
    private TextField txtnamafilm;
    @FXML
    private TextField txttahun;
    @FXML
    private TextField txtgenre;
    @FXML
    private TextField txtharga;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void execute(ModelFilm d) {
        if (!d.getKodefilm().isEmpty()) {
            editdata = true;
            txtkodefilm.setText(d.getKodefilm());
            txtnamafilm.setText(d.getNamafilm());
            txtgenre.setText(d.getGenre());
            txttahun.setText(d.getTahun());
            txtharga.setText(String.valueOf(d.getHarga()));
            txtkodefilm.setEditable(false);
            txtnamafilm.requestFocus();
        }
    }

    @FXML
    private void btnsaveklik(ActionEvent event) {
        ModelFilm n = new ModelFilm();
        n.setKodefilm(txtkodefilm.getText());
        n.setNamafilm(txtnamafilm.getText());
        n.setGenre(txtgenre.getText());
        n.setTahun(txttahun.getText());
        n.setHarga(Double.parseDouble(txtharga.getText()));

        FXMLDocumentController.dtfilm.setFilmModel(n);
        if (editdata) {
            if (FXMLDocumentController.dtfilm.update()) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Data berhasil diubah", ButtonType.OK);
                a.showAndWait();
                txtkodefilm.setEditable(true);
                btncancelklik(event);
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Data gagal diubah", ButtonType.OK);
                a.showAndWait();
            }
        } else if (FXMLDocumentController.dtfilm.validasi(n.getKodefilm()) <= 0) {
            if (FXMLDocumentController.dtfilm.insert()) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Data berhasil disimpan", ButtonType.OK);
                a.showAndWait();
                btncancelklik(event);
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Data gagal disimpan", ButtonType.OK);
                a.showAndWait();
            }
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Data sudah ada", ButtonType.OK);
            a.showAndWait();
            txtkodefilm.requestFocus();
        }
    }

    @FXML
    private void btncancelklik(ActionEvent event) {
        txtkodefilm.setText("");
        txtnamafilm.setText("");
        txtgenre.setText("");
        txttahun.setText("");
        txtharga.setText("");
        txtkodefilm.requestFocus();
    }

}
