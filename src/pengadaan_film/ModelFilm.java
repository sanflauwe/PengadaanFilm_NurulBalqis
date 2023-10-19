/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pengadaan_film;

import java.sql.Date;

/**
 *
 * @author ASUS
 */
public class ModelFilm {

    private String kodefilm, namafilm, genre, tahun,  gambar;
    private Double harga;

    public String getKodefilm() {
        return kodefilm;
    }

    public void setKodefilm(String kodefilm) {
        this.kodefilm = kodefilm;
    }

    public String getNamafilm() {
        return namafilm;
    }

    public void setNamafilm(String namafilm) {
        this.namafilm = namafilm;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    
    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

}
