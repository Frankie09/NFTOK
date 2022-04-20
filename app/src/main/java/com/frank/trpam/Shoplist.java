package com.frank.trpam;

public class Shoplist {
    private String judul;
    private String deskripsi;
    private String foto;
    private String penjual;
    private double harga;

    public Shoplist(String judul, String deskripsi, String foto,String penjual,double harga) {
        this.judul = judul;
        this.deskripsi = deskripsi;
        this.foto = foto;
        this.penjual = penjual;
        this.harga = harga;
    }
    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPenjual() {
        return penjual;
    }

    public void setPenjual(String penjual) {
        this.penjual = penjual;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
