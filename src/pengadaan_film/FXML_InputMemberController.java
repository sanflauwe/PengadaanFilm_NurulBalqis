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
public class FXML_InputMemberController implements Initializable {

    private boolean editdata = false;

    @FXML
    private TextField txtid;
    @FXML
    private TextField txtnama;
    @FXML
    private TextField txtig;
    @FXML
    private TextField txtwa;
    @FXML
    private TextField txttotal;
    @FXML
    private Button btnsave;
    @FXML
    private Button btncancel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void execute(ModelMember d) {
        if (!d.getIdmember().isEmpty()) {
            editdata = true;
            txtid.setText(d.getIdmember());
            txtnama.setText(d.getNamamember());
            txtwa.setText(d.getWhatsapp());
            txtig.setText(d.getInstagram());
            txttotal.setText(String.valueOf(d.getTotal()));
            txtid.setEditable(false);
            txtnama.requestFocus();
        }
    }

    @FXML
    private void btnsaveklik(ActionEvent event) {
        ModelMember n = new ModelMember();
        n.setIdmember(txtid.getText());
        n.setNamamember(txtnama.getText());
        n.setWhatsapp(txtwa.getText());
        n.setInstagram(txtig.getText());
        n.setTotal(Double.parseDouble(txttotal.getText()));

        FXMLDocumentController.dtmember.setMemberModel(n);
        if (editdata) {
            if (FXMLDocumentController.dtmember.update()) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Data berhasil diubah", ButtonType.OK);
                a.showAndWait();
                txtid.setEditable(true);
                btncancelklik(event);
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Data gagal diubah", ButtonType.OK);
                a.showAndWait();
            }
        } else if (FXMLDocumentController.dtmember.validasi(n.getIdmember()) <= 0) {
            if (FXMLDocumentController.dtmember.insert()) {
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
            txtid.requestFocus();
        }
    }

    @FXML
    private void btncancelklik(ActionEvent event) {
        txtid.setText("");
        txtnama.setText("");
        txtwa.setText("");
        txtig.setText("");
        txttotal.setText("");
        txtid.requestFocus();
    }

}
