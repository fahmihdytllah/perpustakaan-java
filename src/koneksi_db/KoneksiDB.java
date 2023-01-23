/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package koneksi_db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author pc-rpl
 */
public class KoneksiDB {
    Connection cn;
    public static Connection BuatKoneksi() {
        String url = "jdbc:mysql://localhost/db_perpus";
        String user = "root";
        String password = "";
        try{
            Connection cn = DriverManager.getConnection(url, user, password);
            System.out.println("Berhasil konek boyy");
            return cn;
        }catch (SQLException e){
            System.err.println(e.getMessage());
            return null;
        }
    }
}
