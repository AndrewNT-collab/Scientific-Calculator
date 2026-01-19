package kalkulator;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class RiwayatDAO {

    public void simpanRiwayat(
            double angka1,
            String operator,
            double angka2,
            double hasil
    ) {
        String sql = """
            INSERT INTO riwayat_kalkulator (angka1, operator, angka2, hasil)
            VALUES (?, ?, ?, ?)
        """;

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setDouble(1, angka1);
            ps.setString(2, operator);
            ps.setDouble(3, angka2);
            ps.setDouble(4, hasil);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Gagal simpan riwayat: " + e.getMessage());
        }
    }
}
