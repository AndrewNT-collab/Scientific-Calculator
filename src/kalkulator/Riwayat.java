package kalkulator;

import java.sql.Timestamp;

public class Riwayat {

    private int id;
    private double angka1;
    private String operator;
    private double angka2;
    private double hasil;
    private Timestamp waktu;

    public Riwayat(int id, double angka1, String operator, double angka2, double hasil, Timestamp waktu) {
        this.id = id;
        this.angka1 = angka1;
        this.operator = operator;
        this.angka2 = angka2;
        this.hasil = hasil;
        this.waktu = waktu;
    }

    public int getId() {
        return id;
    }

    public double getAngka1() {
        return angka1;
    }

    public String getOperator() {
        return operator;
    }

    public double getAngka2() {
        return angka2;
    }

    public double getHasil() {
        return hasil;
    }

    public Timestamp getWaktu() {
        return waktu;
    }
}
