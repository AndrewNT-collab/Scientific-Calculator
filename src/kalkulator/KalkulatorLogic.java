package kalkulator;

import java.util.ArrayList;
import java.util.List;

// Exception
class BagiNolException extends Exception {
    public BagiNolException(String pesan) {
        super(pesan);
    }
}

class AkarNegatifException extends Exception {
    public AkarNegatifException(String pesan) {
        super(pesan);
    }
}

public class KalkulatorLogic {

    private double hasil;
    private List<String> history = new ArrayList<>();

    public void setAngkaAwal(double angka) {
        hasil = angka;
        history.add("Angka awal = " + angka);
    }

    public double getAngkaAwal() {
        return hasil;
    }

    public void hitung(double angka, char op) throws BagiNolException {
        double sebelum = hasil;

        switch (op) {
            case '+': hasil += angka; break;
            case '-': hasil -= angka; break;
            case '*': hasil *= angka; break;
            case '/':
                if (angka == 0) {
                    throw new BagiNolException("Tidak bisa bagi nol");
                }
                hasil /= angka;
                break;
        }

        history.add(sebelum + " " + op + " " + angka + " = " + hasil);
    }

    public void pangkat(double p) {
        hasil = Math.pow(hasil, p);
        history.add("Pangkat ^ " + p + " = " + hasil);
    }

    public void sin() {
        hasil = Math.sin(Math.toRadians(hasil));
        history.add("sin = " + hasil);
    }

    public void cos() {
        hasil = Math.cos(Math.toRadians(hasil));
        history.add("cos = " + hasil);
    }

    public void tan() {
        hasil = Math.tan(Math.toRadians(hasil));
        history.add("tan = " + hasil);
    }

    public void akar() throws AkarNegatifException {
        if (hasil < 0) {
            throw new AkarNegatifException("Akar bilangan negatif");
        }
        hasil = Math.sqrt(hasil);
        history.add("akar = " + hasil);
    }

    public double getHasil() {
        return hasil;
    }

    public List<String> getHistory() {
        return history;
    }
}
