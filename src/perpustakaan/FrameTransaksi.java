/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package perpustakaan;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Fahmi
 */
public class FrameTransaksi extends javax.swing.JInternalFrame {
    Connection cn;

    /**
     * Creates new form FrameTransaksi
     */
    public FrameTransaksi() {
        initComponents();
        cn = koneksi_db.KoneksiDB.BuatKoneksi();
        tampilAnggota();
        tampilBuku();
        generateId();
        tampilData("");
        enableButton(false);
    }
    //method reset isian textfield dan combobox
    private void bersihkanForm(){
        generateId();
        txId.setText("");
        txBuku.setSelectedIndex(0);
        txNmPm.setSelectedIndex(0);
    }
    
    //mengaktifkan,nonaktifkan button
    private void enableButton(boolean status){
        btKembali.setEnabled(status);
        btPerpanjang.setEnabled(status);
    }
    
    //mengaktifkan,nonaktifkan textfield dan combo box
    private void enableForm(boolean status){
        txId.setEnabled(status);
        txBuku.setEnabled(status);
        txNmPm.setEnabled(status);
        txTgl.setEnabled(status);
    }
    
    //menghitung denda setiap terlambat mengembalikan buku
    private long hitungDenda(String IdTransaksi){
        long denda = 0;
        Date hariIni = new Date();
        Date tglKembali = null;
        
        long selisihHari = 0;
        try {
            Statement st = cn.createStatement();
            String tanggal = "";
            //querry menyeleksi tanggal kembali di tb_transaksi
            ResultSet rs = st.executeQuery("SELECT tgl_kembali FROM " + "tb_transaksi WHERE id_transaksi = '" +IdTransaksi + "'");
            while (rs.next()){
                tanggal = rs.getString(1);
            }
            SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
            tglKembali = fm.parse(tanggal);
            
            long dif = hariIni.getTime() - tglKembali.getTime();
            
            selisihHari = dif / (24 * 60 * 60 * 1000);
            
        }catch (SQLException | ParseException e){
            //menampilkan pesan jika error
            System.err.println(e);
        }
        
        denda = selisihHari * 1000;
        return denda;
    }
    
    private void updateDataTransaksi(String idTransaksi,String denda){
        try{
            Statement st = cn.createStatement();
            //ubah data lama 
            String sqlUpdate = "UPDATE tb_transaksi SET status = '"+denda+"'" + "WHERE id_transaksi = '" + idTransaksi + "'";
            st.executeUpdate(sqlUpdate);
                    
        }catch (SQLException e){
            System.err.println(e);
        }
    }
    
    private void updateDenda(){
        try{
            Statement st = cn.createStatement();
            //querry dieksekusi 
            ResultSet rs = st.executeQuery("SELECT id_transaksi "+ "FROM tb_transaksi");
            //denda diupdate
            while (rs.next()){
                String idTransaksi = rs.getString(1);
                //hitung denda 
                int jmlDenda = (int) (hitungDenda(idTransaksi));
                //update data 
                if (jmlDenda > 1000){
                updateDataTransaksi(idTransaksi, String.valueOf(jmlDenda));
                }
            }
        }catch (SQLException e){
            System.err.println(e);
        }
    }
    
    //membuat id_logPinjam otomatis 
    private String generateLogPinjam(){
        String idLogPinjam = "";
        
        try {
            Statement st = cn.createStatement();
            //mecari jumlah data di tabel transaksi
            String sql = "SELECT COUNT(id_log) FROM log_pinjam";
            //eksekusi query 
            ResultSet rs = st.executeQuery(sql);
            //menampilkan id log denga format ip(nomor urut )
            while (rs.next()){
                idLogPinjam = "1p-" + rs.getString("COUNT(id_log)");
            }
        }catch (SQLException e){
            System.err.println(e);
        }
        return idLogPinjam;
    }
    
    //membuat id_logkembali otomatis
    private String generateLogKembali(){
        String idLogKembali = "";
        
        try {
            Statement st = cn.createStatement();
            //mecari jumlah data di tabel transaksi
            String sql = "SELECT COUNT(id_log) FROM log_kembali";
            //eksekusi query 
            ResultSet rs = st.executeQuery(sql);
            //menampilkan id log denga format ip(nomor urut )
            while (rs.next()){
                idLogKembali = "1k-" + rs.getString("COUNT(id_log)");
            }
        }catch (SQLException e){
            System.err.println(e);
        }
        return idLogKembali;
    }

    //menyimpan data di tabel log_pinjam
    private void simpanLogPinjam(String idBuku, String idAnggota, String tglPinjam){
        try {
            Statement st = cn.createStatement();
            //membuat query tambah data baru 
            String sql = "INSERT INTO log_pinjam VALUES ('"
                    + generateLogPinjam() + "','"
                    + idBuku + "','"
                    + idAnggota + "','"
                    + tglPinjam + "')";
            
            st.executeUpdate(sql);
        }catch (SQLException ex){
            System.err.println(ex);
        }
    }
    
    private void simpanLogKembali(String idBuku, String idAnggota, String tglKembali){
        try {
            Statement st = cn.createStatement();
            //membuat query tambah data baru 
            String sql = "INSERT INTO log_kembali VALUES ('"
                    + generateLogKembali() + "','"
                    + idBuku + "','"
                    + idAnggota + "','"
                    + tglKembali+ "')";
            
            st.executeUpdate(sql);
        }catch (SQLException ex){
            System.err.println(ex);
        }
    }
    
    private void tampilData(String q) {
        Statement st;
        ResultSet rs;
        int jd = 0;
        
        try{
            cn = koneksi_db.KoneksiDB.BuatKoneksi();
            st = cn.createStatement();
            
            String sql;
            if(q.equals("")){
                sql = "SELECT * FROM tb_transaksi WHERE status != 'kembali'";
            }else{
                sql = "SELECT * FROM tb_transaksi WHERE "
                        + "id_transaksi LIKE '%" + q + "%'"; 
            }
            rs = st.executeQuery(sql);
            
            DefaultTableModel model = new DefaultTableModel();
            
            model.addColumn("ID");
            model.addColumn("Judul Buku");
            model.addColumn("Peminjam");
            model.addColumn("Tanggal Pinjam");
            model.addColumn("Tanggal Kembali");
            model.addColumn("Denda");
            
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
            model.setRowCount(0);
            
            while (rs.next()){
                Object[] data = {
                    rs.getString("id_transaksi"),
                    getJudulBuku(rs.getString("id_buku")),
                    getNamaPeminjam(rs.getString("id_anggota")),
                    rs.getString("tgl_pinjam"),
                    rs.getString("tgl_kembali"),
                    rs.getString("status")
                };
                
                model.addRow(data);
                tbTransaksi.setModel(model);
                jd++;
            }
            txJm.setText(jd + "");
        } catch (SQLException e){
            System.err.println(e);
        }
    }
    
    private void tampilAnggota() {
        Statement st;
        ResultSet rs;
        
        try {
            st = cn.createStatement();
            String sql = "SELECT * FROM tb_anggota";
            rs = st.executeQuery(sql);
            if(txNmPm.getItemCount() == 0) {
                txNmPm.addItem("-- PILIH ANGGOTA --");
            }
            while (rs.next()){
                txNmPm.addItem(rs.getString("id_anggota") + " | " + rs.getString("nama"));
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    private void tampilBuku() {
        Statement st;
        ResultSet rs;
        
        try {
            st = cn.createStatement();
            String sql = "SELECT * FROM tb_buku";
            rs = st.executeQuery(sql);
            if(txBuku.getItemCount() == 0) {
                txBuku.addItem("-- PILIH Buku --");
            }
            while (rs.next()){
                txBuku.addItem(rs.getString("id_buku") + " | " + rs.getString("judul_buku"));
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    void generateId() {
        Statement st;
        ResultSet rs;

        try {
            st = cn.createStatement();
            //memebua query untuk mencari jumlah data di tabel transaksi 
            String sql = "SELECT COUNT(id_transaksi) FROM tb_transaksi";
            //mengeksekusi query 
            rs = st.executeQuery(sql);
            //menampilkan id transaksi dengan format "tr-[nomor urut]"
            while (rs.next()) {
                txId.setText("tr-" + rs.getString("COUNT(id_transaksi)"));
            }
        } catch (SQLException e) {
            //menampilkan pesan jika eror
            System.err.println(e);

        }

    }
    private String getJudulBuku(String id) {
        Statement st;
        ResultSet rs;
        String judul = "";
        try {
            st = cn.createStatement();
            String sql = "SELECT judul_buku FROM tb_buku WHERE id_buku = '" + id + "'";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                judul = rs.getString("judul_buku");
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return judul;
    }
    
    private String getNamaPeminjam(String id) {
        Statement st;
        ResultSet rs;
        String nama = "";
        try {
            st = cn.createStatement();
            String sql = "SELECT nama FROM tb_anggota WHERE id_anggota = '" + id + "'";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                nama = rs.getString("nama");
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return nama;
    }
    
    private void bersihkanFrom() {
        txId.setText("");
//        txJudul.setText("")
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txId = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txNmPm = new javax.swing.JComboBox<>();
        txBuku = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        txTgl = new com.toedter.calendar.JDateChooser();
        btKembali = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        btPerpanjang = new javax.swing.JButton();
        btSimpan = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbTransaksi = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        txJm = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txCari = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Data Transaksi");
        setToolTipText("");
        setName("Data Transaksi"); // NOI18N

        jLabel3.setText("ID Transaksi");

        jLabel4.setText("Nama Peminjam");

        txId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txIdActionPerformed(evt);
            }
        });

        jLabel5.setText("Buku");

        jLabel6.setText("Tanggal Pinjam");

        btKembali.setText("Kembalikan Buku");
        btKembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btKembaliActionPerformed(evt);
            }
        });

        jButton2.setText("Batal");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btPerpanjang.setText("Perpanjang Pinjam");
        btPerpanjang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPerpanjangActionPerformed(evt);
            }
        });

        btSimpan.setText("Pinjam Buku");
        btSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSimpanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(54, 54, 54)
                        .addComponent(txId, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btSimpan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(27, 27, 27)
                        .addComponent(txNmPm, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txBuku, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txTgl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btKembali)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btPerpanjang)))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btSimpan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txNmPm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txBuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(txTgl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btKembali)
                    .addComponent(jButton2)
                    .addComponent(btPerpanjang))
                .addContainerGap(114, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Data Transaksi"));
        jPanel2.setName("Data Transaksi"); // NOI18N

        tbTransaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbTransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbTransaksiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbTransaksi);

        jLabel7.setText("Jumlah Transaksi:");

        txJm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txJmActionPerformed(evt);
            }
        });

        jLabel8.setText("Cari Transaksi:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txJm, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txCari, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txJm, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(txCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(116, 116, 116))
        );

        jLabel1.setText("Data Transaksi");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("PEMINJAMAN BUKU");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txIdActionPerformed

    private void txJmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txJmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txJmActionPerformed

    private void btKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btKembaliActionPerformed
        // TODO add your handling code here:
        Date tglSekarang = new Date();
        //ambil data dari tabel transaksi 
        String idTransaksi = tbTransaksi.getValueAt(tbTransaksi.getSelectedRow(), 0).toString();
        //menyeleksi data dari combo box
        String anggota = txNmPm.getSelectedItem().toString();
        String buku = txBuku.getSelectedItem().toString();
        //hanya mengambil id nya saja dari combo box 
        String[] idAnggota = anggota.split(" ");
        String[] idBuku = buku.split(" ");
        //membuat format tanggal
        SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
        //mengambil tanggal dari jcalendar
        String tanggalKembali = String.valueOf(fm.format(tglSekarang));
        
        try{
           Statement st = cn.createStatement();
           //ubah data lama
           String sqlUpdate = "UPDATE tb_transaksi SET status = 'kembali'" + " WHERE id_transaksi = '" + idTransaksi + "'";
           
           st.executeUpdate(sqlUpdate);
           JOptionPane.showMessageDialog(this, "Buku berhasil dikembalikan");
           
           //memanggil method untuk menyimpan data di log_pinjam 
           simpanLogKembali(idBuku[0], idAnggota[0], tanggalKembali);
           
           bersihkanForm();
           tampilData("");
           txId.setEditable(true);
           
           if (btKembali.isEnabled()){
               enableButton(false);
           }
           
           enableForm(true);
        }catch (SQLException e){
            System.err.println(e);
        }
    }//GEN-LAST:event_btKembaliActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSimpanActionPerformed
        // TODO add your handling code here:
        Statement st;
        String status = "pinjam";
        
        String ag = txNmPm.getSelectedItem().toString();
        String bk = txBuku.getSelectedItem().toString();
        String id = txId.getText();
        
        String[] iag = ag.split(" ");
        String[] ibk = bk.split(" ");
        
        SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
        String tpj = String.valueOf(fm.format(txTgl.getDate()));
        
        Calendar clt = Calendar.getInstance();
        clt.setTime(txTgl.getDate());
        clt.add(Calendar.DAY_OF_MONTH, 5);
        String tgk = fm.format(clt.getTime());
        
        try {
            st = cn.createStatement();
            String sql = "INSERT INTO tb_transaksi VALUES ('"
                    + id + "', '"
                    + ibk[0] + "', '"
                    + iag[0] + "', '"
                    + tpj + "', '"
                    + tgk + "', '"
                    + status + "')";
            simpanLogPinjam(ibk[0], iag[0], tpj);
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan");
            bersihkanFrom();
            tampilData("");
        } catch (SQLException e) {
            System.err.println(e);
        }
    }//GEN-LAST:event_btSimpanActionPerformed

    private void tbTransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTransaksiMouseClicked
        // TODO add your handling code here:
        txId.setText("");
        enableForm(false);
        enableButton(true);
        
        //ambil data tabel 
        //variable tampung adata sementara
        String idTransaksi = tbTransaksi.getValueAt(tbTransaksi.getSelectedRow(),0).toString();
        String judulBuku = tbTransaksi.getValueAt(tbTransaksi.getSelectedRow(),1).toString();
        String peminjam = tbTransaksi.getValueAt(tbTransaksi.getSelectedRow(),2).toString();
        String tglPinjam = tbTransaksi.getValueAt(tbTransaksi.getSelectedRow(),3).toString();
        String tglKembali = tbTransaksi.getValueAt(tbTransaksi.getSelectedRow(),4).toString();
        String denda = tbTransaksi.getValueAt(tbTransaksi.getSelectedRow(),5).toString();
        
        //ambil data id buku dan id anggota
        String idAnggota = "",idBuku = "";
        try{
            Statement st = cn.createStatement();
            //eksekusi query
            ResultSet rs = st.executeQuery("SELECT * FROM tb_transaksi WHERE"
            + "idTransaksi = '" + idTransaksi + "'");
            
            while (rs.next()){
                idAnggota = rs.getString("id_anggota");
                idBuku = rs.getString("id_buku");
            }
        }catch (SQLException e) {
            System.err.println(e);
        }
        //menampilkan data ke field dan combobox
        txId.setText(idTransaksi);
        txNmPm.setSelectedItem(idAnggota + "|" + peminjam);
        txBuku.setSelectedItem(idBuku + "|" + judulBuku);
        
        //menampilkan tanggal pinjam
        try{
            Date datePinjam = new SimpleDateFormat("yyyy-mm-dd").parse(tglPinjam);
            this.txTgl.setDate(datePinjam);
        }catch(ParseException ex){
            System.err.println(ex);
        }
    }//GEN-LAST:event_tbTransaksiMouseClicked

    private void btPerpanjangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPerpanjangActionPerformed
        // TODO add your handling code here:
        SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
        //mengambil data dari tabel transaksi 
        String idTransaksi = tbTransaksi.getValueAt(tbTransaksi.getSelectedRow(), 0).toString();
        String tglKembali = tbTransaksi.getValueAt(tbTransaksi.getSelectedRow(), 4).toString();
        
        try {
            Statement st = cn.createStatement();
            Date dateKembali = fm.parse(tglKembali);
            
            Calendar calTambah = Calendar.getInstance();
            calTambah.setTime(dateKembali);
            calTambah.add(Calendar.DAY_OF_MONTH, 5);
            String tglPerpanjangan = fm.format(calTambah.getTime());
            //ubah data lama
            String sqlUpdate = "UPDATE tb_transaksi SET"
                    + " tgl_kembali = '" + tglPerpanjangan + "'"
                    + "WHERE id_transaksi = '" + idTransaksi + "'";
            
            st.executeUpdate(sqlUpdate);
            JOptionPane.showMessageDialog(this, "Data berhasil diperpanjang");
            tampilData("");
            bersihkanForm();
            txId.setEditable(true);
            
            if(btKembali.isEnabled()){
                enableButton(false);
            }
            enableForm(true);
            
        }catch (SQLException | ParseException e){
            //menampilkan pesan jika error
            System.err.println(e);         
        }
    }//GEN-LAST:event_btPerpanjangActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btKembali;
    private javax.swing.JButton btPerpanjang;
    private javax.swing.JButton btSimpan;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbTransaksi;
    private javax.swing.JComboBox<String> txBuku;
    private javax.swing.JTextField txCari;
    private javax.swing.JTextField txId;
    private javax.swing.JTextField txJm;
    private javax.swing.JComboBox<String> txNmPm;
    private com.toedter.calendar.JDateChooser txTgl;
    // End of variables declaration//GEN-END:variables
}
