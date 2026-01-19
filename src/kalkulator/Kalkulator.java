package kalkulator;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.InputMismatchException;

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

class LogNegatifException extends Exception {
    public LogNegatifException(String pesan) {
        super(pesan);
    }
}

// Kelas induk
class Kalkulator1 {
    protected double hasil;
    protected List<String> history = new ArrayList<>();

    public void setAngkaAwal(double angka) {
        hasil = angka;
        history.add("Angka awal = " + angka);
    }

    public void hitung(double angka, char op) throws BagiNolException {
        double riwayat = hasil;

        switch (op) {
            case '+':
                hasil += angka;
                break;
            case '-':
                hasil -= angka;
                break;
            case '*':
                hasil *= angka;
                break;
            case '/':
                if (angka == 0) {
                    throw new BagiNolException("Tidak bisa membagi dengan nol!");
                }
                hasil /= angka;
                break;
            default:
                System.out.println("Operator tidak dikenal.");
                return;
        }

        history.add(riwayat + " " + op + " " + angka + " = " + hasil);
    }

    public double getHasil() {
        return hasil;
    }

    public void tampilkanHistory() {
        System.out.println("=== Riwayat Perhitungan ===");
        for (String h : history) {
            System.out.println(h);
        }
    }
}

// Kelas anak 
class Kalkulator2 extends Kalkulator1 {

    public void pangkat(int p) {
        double riwayat = hasil;
        hasil = Math.pow(hasil, p);
        history.add(riwayat + " ^ " + p + " = " + hasil);
    }

    public void akar() throws AkarNegatifException {
        if (hasil < 0) {
            throw new AkarNegatifException("Tidak bisa akar bilangan negatif!");
        }
        double riwayat = hasil;
        hasil = Math.sqrt(hasil);
        history.add("√" + riwayat + " = " + hasil);
    }

    // Scientific
    public void sin() {
        double riwayat = hasil;
        hasil = Math.sin(Math.toRadians(hasil));
        history.add("sin(" + riwayat + ") = " + hasil);
    }

    public void cos() {
        double riwayat = hasil;
        hasil = Math.cos(Math.toRadians(hasil));
        history.add("cos(" + riwayat + ") = " + hasil);
    }

    public void tan() {
        double riwayat = hasil;
        hasil = Math.tan(Math.toRadians(hasil));
        history.add("tan(" + riwayat + ") = " + hasil);
    }

    public void log10() throws LogNegatifException {
        if (hasil <= 0) {
            throw new LogNegatifException("Log hanya untuk bilangan positif!");
        }
        double riwayat = hasil;
        hasil = Math.log10(hasil);
        history.add("log(" + riwayat + ") = " + hasil);
    }

    public void ln() throws LogNegatifException {
        if (hasil <= 0) {
            throw new LogNegatifException("Ln hanya untuk bilangan positif!");
        }
        double riwayat = hasil;
        hasil = Math.log(hasil);
        history.add("ln(" + riwayat + ") = " + hasil);
    }
}

// Main
public class Kalkulator {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Kalkulator2 kalkulator = new Kalkulator2();

        try {
            System.out.print("Masukkan angka pertama: ");
            kalkulator.setAngkaAwal(sc.nextDouble());
        } catch (InputMismatchException e) {
            System.out.println("Input harus berupa angka!");
            return;
        }

        while (true) {
            try {
                System.out.print("Masukkan angka berikutnya: ");
                double angka = sc.nextDouble();

                System.out.print("Pilih operasi (+ - * /): ");
                char op = sc.next().charAt(0);

                kalkulator.hitung(angka, op);
                System.out.println("Hasil sementara: " + kalkulator.getHasil());

                System.out.print(
                    "Fitur scientific (pangkat/akar/sin/cos/tan/log/ln/tidak): "
                );
                String fitur = sc.next();

                switch (fitur.toLowerCase()) {
                    case "pangkat":
                        System.out.print("Masukkan pangkat: ");
                        kalkulator.pangkat(sc.nextInt());
                        break;
                    case "akar":
                        kalkulator.akar();
                        break;
                    case "sin":
                        kalkulator.sin();
                        break;
                    case "cos":
                        kalkulator.cos();
                        break;
                    case "tan":
                        kalkulator.tan();
                        break;
                    case "log":
                        kalkulator.log10();
                        break;
                    case "ln":
                        kalkulator.ln();
                        break;
                }

                System.out.println("Hasil sekarang: " + kalkulator.getHasil());
            }
            catch (BagiNolException | AkarNegatifException | LogNegatifException e) {
                System.out.println("Error: " + e.getMessage());
            }
            catch (InputMismatchException e) {
                System.out.println("Input tidak valid!");
                sc.nextLine();
            }

            System.out.print("Lanjut? (ya/tidak): ");
            if (sc.next().equalsIgnoreCase("tidak")) {
                kalkulator.tampilkanHistory();
                System.out.println("Program selesai.");
                break;
            }
        }
    }
}
