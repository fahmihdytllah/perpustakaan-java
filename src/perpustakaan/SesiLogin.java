/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package perpustakaan;

/**
 *
 * @author Fahmi
 */
public class SesiLogin {
    private static String idPengguna;
    private static String namaPengguna;
    private static String username;
    private static String password;
    private static String level;
    
    public static String getIdPengguna(){
        return idPengguna;
    }
    public static void setIdPengguna(String  idPengguna){
        SesiLogin.idPengguna = idPengguna;
    }
    public static String getNamaPengguna(){
        return namaPengguna;
    }
    public static void setNamaPengguna(String namaPengguna){
        SesiLogin.namaPengguna = namaPengguna;
    }
    public static String getUsername(){
        return username;
    }
    public static void setUsername(String username){
        SesiLogin.username = username;
    }
    public static String getPassword(){
        return password;
    }
    public static void setPassword(String password){
        SesiLogin.password = password;
    }
    public static String getLevel(){
        return level;
    }
    public static void setLevel(String level){
        SesiLogin.level = level;
    }
}
