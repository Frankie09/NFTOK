package com.frank.trpam;

public class Shoplist {
    private String judul;
    private String deskripsi;
    private String foto;
    private String penjual;
    private String dari;
    private String namafile;
    private String pemilik;

    private double harga;

    public Shoplist(String judul, String deskripsi, String foto,String penjual,double harga,String dari, String namafile,String pemilik) {
        this.judul = judul;
        this.deskripsi = deskripsi;
        this.foto = foto;
        this.penjual = penjual;
        this.harga = harga;
        this.dari = dari;
        this.namafile = namafile;
        this.pemilik = pemilik;

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

    public String getDari() {
        return dari;
    }

    public void setDari(String dari) {
        this.dari = dari;
    }

    public String getNamafile() {
        return namafile;
    }

    public void setNamafile(String namafile) {
        this.namafile = namafile;
    }

    public String getPemilik() {
        return pemilik;
    }

    public void setPemilik(String pemilik) {
        this.namafile = pemilik;
    }
}
