package kalkulator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class Controller {

    @FXML private TextField input;
    @FXML private Label hasil;
    @FXML private VBox historyBox;
    @FXML private ListView<String> historyList;

    private KalkulatorLogic kalkulator = new KalkulatorLogic();
    private RiwayatDAO riwayatDAO = new RiwayatDAO();

    private char op = '\0';
    private double angka1;
    private double angka2;

    // ANGKA
    @FXML
    private void angka(ActionEvent event) {
        Button btn = (Button) event.getSource();
        input.appendText(btn.getText());
    }

    // OPERATOR
    @FXML private void tambah()  { setOperator('+'); }
    @FXML private void kurang()  { setOperator('-'); }
    @FXML private void kali()    { setOperator('*'); }
    @FXML private void bagi()    { setOperator('/'); }
    @FXML private void pangkat() { setOperator('^'); }

    private void setOperator(char operator) {
        try {
            angka1 = Double.parseDouble(input.getText());
            kalkulator.setAngkaAwal(angka1);
            op = operator;
            input.clear();
        } catch (Exception e) {
            hasil.setText("Input salah");
        }
    }

    // HASIL
    @FXML
    private void samaDengan() {
        try {
            angka2 = Double.parseDouble(input.getText());

            if (op == '^') {
                kalkulator.pangkat(angka2);
            } else {
                kalkulator.hitung(angka2, op);
            }

            double hasilAkhir = kalkulator.getHasil();
            hasil.setText("Hasil: " + hasilAkhir);

            String teks = angka1 + " " + op + " " + angka2 + " = " + hasilAkhir;
            historyList.getItems().add(teks);

            riwayatDAO.simpanRiwayat(
                angka1,
                String.valueOf(op),
                angka2,
                hasilAkhir
            );

            input.clear();
            op = '\0';

        } catch (Exception e) {
            hasil.setText("Error");
        }
    }

    // SCIENTIFIC
    @FXML private void sin() { satuAngka("sin"); }
    @FXML private void cos() { satuAngka("cos"); }
    @FXML private void tan() { satuAngka("tan"); }

    private void satuAngka(String jenis) {
        try {
            angka1 = Double.parseDouble(input.getText());
            kalkulator.setAngkaAwal(angka1);

            switch (jenis) {
                case "sin": kalkulator.sin(); break;
                case "cos": kalkulator.cos(); break;
                case "tan": kalkulator.tan(); break;
            }

            double hasilAkhir = kalkulator.getHasil();
            hasil.setText("Hasil: " + hasilAkhir);

            String teks = jenis + "(" + angka1 + ") = " + hasilAkhir;
            historyList.getItems().add(teks);

            riwayatDAO.simpanRiwayat(
                angka1,
                jenis,
                0,
                hasilAkhir
            );

            input.clear();

        } catch (Exception e) {
            hasil.setText("Error");
        }
    }

    @FXML
    private void akar() {
        try {
            angka1 = Double.parseDouble(input.getText());
            kalkulator.setAngkaAwal(angka1);
            kalkulator.akar();

            double hasilAkhir = kalkulator.getHasil();
            hasil.setText("Hasil: " + hasilAkhir);

            String teks = "√" + angka1 + " = " + hasilAkhir;
            historyList.getItems().add(teks);

            riwayatDAO.simpanRiwayat(
                angka1,
                "akar",
                0,
                hasilAkhir
            );

            input.clear();

        } catch (Exception e) {
            hasil.setText("Error");
        }
    }

    // CLEAR
    @FXML
    private void clear() {
        input.clear();
        hasil.setText("Hasil: 0");
        op = '\0';
    }

    // HISTORY
    @FXML
    private void toggleHistory() {
        boolean show = !historyBox.isVisible();
        historyBox.setVisible(show);
        historyBox.setManaged(show);
    }

    @FXML
    private void historyClicked() {
        String selected = historyList.getSelectionModel().getSelectedItem();
        if (selected != null && selected.contains("=")) {
            String hasilStr = selected.split("=")[1].trim();
            input.setText(hasilStr);
        }
    }
}
