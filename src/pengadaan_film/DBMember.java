/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pengadaan_film;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ASUS
 */
public class DBMember {

    private ModelMember dt = new ModelMember();

    public ModelMember getMemberModel() {
        return (dt);
    }

    public void setMemberModel(ModelMember s) {
        dt = s;
    }

    public ObservableList<ModelMember> Load() {
        try {
            ObservableList<ModelMember> tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select idmember, namamember, whatsapp, instagram, total from member");
            int i = 1;
            while (rs.next()) {
                ModelMember d = new ModelMember();
                d.setIdmember(rs.getString("idmember"));
                d.setNamamember(rs.getString("namamember"));
                d.setWhatsapp(rs.getString("whatsapp"));
                d.setInstagram(rs.getString("instagram"));
                d.setTotal(rs.getDouble("total"));
                
                double total = rs.getDouble("total");
                String status;
                
                if(total>300000) status =  "Sultan";
                else if (total>10000) status = "Bos";
                else status = "Jelata";
                d.setStatus(status);
                
                tableData.add(d);
                i++;
            }
            con.tutupKoneksi();
            return tableData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int validasi(String nomor) {
        int val = 0;
        try {
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("select count(*) as jml from member where idmember = '" + nomor + "'");
            while (rs.next()) {
                val = rs.getInt("jml");
            }
            con.tutupKoneksi();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return val;
    }

    public boolean insert() {
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        try {
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into member (idmember, namamember, whatsapp, instagram, total) values (?,?,?,?,?)");
            con.preparedStatement.setString(1, getMemberModel().getIdmember());
            con.preparedStatement.setString(2, getMemberModel().getNamamember());
            con.preparedStatement.setString(3, getMemberModel().getWhatsapp());
            con.preparedStatement.setString(4, getMemberModel().getInstagram());
            con.preparedStatement.setDouble(5, getMemberModel().getTotal());
            con.preparedStatement.executeUpdate();
            berhasil = true;
        } catch (Exception e) {
            e.printStackTrace();
            berhasil = false;
        } finally {
            con.tutupKoneksi();
            return berhasil;
        }
    }

    public boolean delete(String nomor) {
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        try {
            con.bukaKoneksi();;
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from member where idmember  = ? ");
            con.preparedStatement.setString(1, nomor);
            con.preparedStatement.executeUpdate();
            berhasil = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.tutupKoneksi();
            return berhasil;
        }
    }

    public boolean update() {
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        try {
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement("update member set namamember = ?, whatsapp = ?, instagram = ?, total = ?   where  idmember = ? ");
            con.preparedStatement.setString(1, getMemberModel().getNamamember());
            con.preparedStatement.setString(2, getMemberModel().getWhatsapp());
            con.preparedStatement.setString(3, getMemberModel().getInstagram());
            con.preparedStatement.setDouble(4, getMemberModel().getTotal());
            con.preparedStatement.setString(5, getMemberModel().getIdmember());
            con.preparedStatement.executeUpdate();
            berhasil = true;
        } catch (Exception e) {
            e.printStackTrace();
            berhasil = false;
        } finally {
            con.tutupKoneksi();
            return berhasil;
        }
    }
}
