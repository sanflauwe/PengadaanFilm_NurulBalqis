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
public class DBFilm {

    private ModelFilm dt = new ModelFilm();

    public ModelFilm getFilmModel() {
        return (dt);
    }

    public void setFilmModel(ModelFilm s) {
        dt = s;
    }

    public ObservableList<ModelFilm> Load() {
        try {
            ObservableList<ModelFilm> tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select kodefilm, namafilm, genre, tahun, harga, gambar from film");
            int i = 1;
            while (rs.next()) {
                ModelFilm d = new ModelFilm();
                d.setKodefilm(rs.getString("kodefilm"));
                d.setNamafilm(rs.getString("namafilm"));
                d.setGenre(rs.getString("genre"));
                d.setTahun(rs.getString("tahun"));
                d.setHarga(rs.getDouble("harga"));
                d.setGambar(rs.getString("gambar"));        
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
            ResultSet rs = con.statement.executeQuery("select count(*) as jml from film where kodefilm = '" + nomor + "'");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into film (kodefilm, namafilm, genre, tahun, harga, gambar) values (?,?,?,?,?,?)");
            con.preparedStatement.setString(1, getFilmModel().getKodefilm());
            con.preparedStatement.setString(2, getFilmModel().getNamafilm());
            con.preparedStatement.setString(3, getFilmModel().getGenre());
            con.preparedStatement.setString(4, getFilmModel().getTahun());
            con.preparedStatement.setDouble(5, getFilmModel().getHarga());
            con.preparedStatement.setString(6, getFilmModel().getGambar());
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from film where kodefilm  = ? ");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("update film set namafilm = ?, genre = ?, tahun = ?, harga = ?, gambar = ?   where  kodefilm = ? ");
            con.preparedStatement.setString(1, getFilmModel().getNamafilm());
            con.preparedStatement.setString(2, getFilmModel().getGenre());
            con.preparedStatement.setString(3, getFilmModel().getTahun());
            con.preparedStatement.setDouble(4, getFilmModel().getHarga());
            con.preparedStatement.setString(5, getFilmModel().getGambar());
            con.preparedStatement.setString(6, getFilmModel().getKodefilm());
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
