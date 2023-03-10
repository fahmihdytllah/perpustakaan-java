/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package perpustakaan;

import javax.swing.JFrame;
import java.io.File;
import java.util.HashMap;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
        
/**
 *
 * @author Fahmi
 */
public class Beranda extends javax.swing.JFrame {

    

    /**
     * Creates new form Beranda
     */
    public Beranda() {
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        txNamaPetugas.setText(SesiLogin.getNamaPengguna());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem3 = new javax.swing.JMenuItem();
        jToolBar1 = new javax.swing.JToolBar();
        btBuku = new javax.swing.JButton();
        btAnggota = new javax.swing.JButton();
        btPeminjaman = new javax.swing.JButton();
        btPengembalian = new javax.swing.JButton();
        desktopBeranda = new javax.swing.JDesktopPane();
        jLabel1 = new javax.swing.JLabel();
        txNamaPetugas = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        mnSetting = new javax.swing.JMenu();
        iMnPengguna = new javax.swing.JMenuItem();
        iMnLogout = new javax.swing.JMenuItem();
        mnKelolaData = new javax.swing.JMenu();
        iMnBuku = new javax.swing.JMenuItem();
        iMnAnggota = new javax.swing.JMenuItem();
        iMnTransaksi = new javax.swing.JMenuItem();
        mnLogData = new javax.swing.JMenu();
        iMnPeminjaman = new javax.swing.JMenuItem();
        iMnPengembalian = new javax.swing.JMenuItem();
        mnLaporan = new javax.swing.JMenu();
        iMnLpBuku = new javax.swing.JMenuItem();
        iMnLpAnggota = new javax.swing.JMenuItem();
        iMnLpPeminjaman = new javax.swing.JMenuItem();
        iMnLpPengembalian = new javax.swing.JMenuItem();

        jMenuItem3.setText("jMenuItem3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Dashboard Perpustakaan");
        setAutoRequestFocus(false);

        jToolBar1.setRollover(true);

        btBuku.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/book.png"))); // NOI18N
        btBuku.setFocusable(false);
        btBuku.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btBuku.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btBuku);

        btAnggota.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/group.png"))); // NOI18N
        btAnggota.setFocusable(false);
        btAnggota.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btAnggota.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btAnggota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAnggotaActionPerformed(evt);
            }
        });
        jToolBar1.add(btAnggota);

        btPeminjaman.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/borrow.png"))); // NOI18N
        btPeminjaman.setFocusable(false);
        btPeminjaman.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btPeminjaman.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btPeminjaman.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPeminjamanActionPerformed(evt);
            }
        });
        jToolBar1.add(btPeminjaman);

        btPengembalian.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/return.png"))); // NOI18N
        btPengembalian.setFocusable(false);
        btPengembalian.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btPengembalian.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btPengembalian);

        javax.swing.GroupLayout desktopBerandaLayout = new javax.swing.GroupLayout(desktopBeranda);
        desktopBeranda.setLayout(desktopBerandaLayout);
        desktopBerandaLayout.setHorizontalGroup(
            desktopBerandaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 453, Short.MAX_VALUE)
        );
        desktopBerandaLayout.setVerticalGroup(
            desktopBerandaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 232, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("PETUGAS:");

        txNamaPetugas.setEditable(false);
        txNamaPetugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txNamaPetugasActionPerformed(evt);
            }
        });

        mnSetting.setText("Setting");

        iMnPengguna.setText("Pengguna");
        iMnPengguna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iMnPenggunaActionPerformed(evt);
            }
        });
        mnSetting.add(iMnPengguna);

        iMnLogout.setText("Logout");
        iMnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iMnLogoutActionPerformed(evt);
            }
        });
        mnSetting.add(iMnLogout);

        jMenuBar1.add(mnSetting);

        mnKelolaData.setText("Kelola Data");

        iMnBuku.setText("Buku");
        iMnBuku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iMnBukuActionPerformed(evt);
            }
        });
        mnKelolaData.add(iMnBuku);

        iMnAnggota.setText("Anggota");
        iMnAnggota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iMnAnggotaActionPerformed(evt);
            }
        });
        mnKelolaData.add(iMnAnggota);

        iMnTransaksi.setText("Transaksi");
        iMnTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iMnTransaksiActionPerformed(evt);
            }
        });
        mnKelolaData.add(iMnTransaksi);

        jMenuBar1.add(mnKelolaData);

        mnLogData.setText("Log Data");

        iMnPeminjaman.setText("Peminjaman");
        iMnPeminjaman.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iMnPeminjamanActionPerformed(evt);
            }
        });
        mnLogData.add(iMnPeminjaman);

        iMnPengembalian.setText("Pengembalian");
        mnLogData.add(iMnPengembalian);

        jMenuBar1.add(mnLogData);

        mnLaporan.setText("Laporan");

        iMnLpBuku.setText("Laporan Buku");
        iMnLpBuku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iMnLpBukuActionPerformed(evt);
            }
        });
        mnLaporan.add(iMnLpBuku);

        iMnLpAnggota.setText("Laporan Anggota");
        iMnLpAnggota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iMnLpAnggotaActionPerformed(evt);
            }
        });
        mnLaporan.add(iMnLpAnggota);

        iMnLpPeminjaman.setText("Laporan Peminjaman");
        mnLaporan.add(iMnLpPeminjaman);

        iMnLpPengembalian.setText("Laporan Pengembalian");
        mnLaporan.add(iMnLpPengembalian);

        jMenuBar1.add(mnLaporan);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopBeranda)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txNamaPetugas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txNamaPetugas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(desktopBeranda))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void iMnPenggunaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iMnPenggunaActionPerformed
        // TODO add your handling code here:
        FramePengguna fPengguna = new FramePengguna();
//        desktopBeranda.removeAll();
        desktopBeranda.add(fPengguna).setVisible(true);
    }//GEN-LAST:event_iMnPenggunaActionPerformed

    private void iMnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iMnLogoutActionPerformed
        // TODO add your handling code here:
        Login login = new Login();
        login.pack();
        login.setLocationRelativeTo(null);
        login.setVisible(true);
        dispose();
    }//GEN-LAST:event_iMnLogoutActionPerformed

    private void iMnPeminjamanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iMnPeminjamanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_iMnPeminjamanActionPerformed

    private void iMnBukuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iMnBukuActionPerformed
        // TODO add your handling code here:
        FrameBuku fBuku = new FrameBuku();
//        desktopBeranda.removeAll();
        desktopBeranda.add(fBuku).setVisible(true);
    }//GEN-LAST:event_iMnBukuActionPerformed

    private void btAnggotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAnggotaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btAnggotaActionPerformed

    private void iMnAnggotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iMnAnggotaActionPerformed
        // TODO add your handling code here:
        FrameAnggota fAnggota = new FrameAnggota();
//        desktopBeranda.removeAll();
        desktopBeranda.add(fAnggota).setVisible(true);
    }//GEN-LAST:event_iMnAnggotaActionPerformed

    private void btPeminjamanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPeminjamanActionPerformed
        // TODO add your handling code here:
        FrameTransaksi fTr = new FrameTransaksi();
//        desktopBeranda.removeAll();
        desktopBeranda.add(fTr).setVisible(true);
    }//GEN-LAST:event_btPeminjamanActionPerformed

    private void iMnTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iMnTransaksiActionPerformed
        // TODO add your handling code here:
        FrameTransaksi fTr = new FrameTransaksi();
//        desktopBeranda.removeAll();
        desktopBeranda.add(fTr).setVisible(true);
    }//GEN-LAST:event_iMnTransaksiActionPerformed

    private void iMnLpBukuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iMnLpBukuActionPerformed
        // TODO add your handling code here:
        try{
            HashMap param = new HashMap();
            param.put("nm_petugas", SesiLogin.getNamaPengguna());
            File fileReport = new File("src/laporan/report_buku.jasper");
            JasperPrint jp = JasperFillManager.fillReport(fileReport.getPath(),
                    param, koneksi_db.KoneksiDB.BuatKoneksi());
           JasperViewer.viewReport(jp, false);
        }catch(JRException e){
            System.err.println(e);
        }
    }//GEN-LAST:event_iMnLpBukuActionPerformed

    private void iMnLpAnggotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iMnLpAnggotaActionPerformed
        // TODO add your handling code here:
        try{
            File fileReport = new File("src/laporan/report_anggota.jasper");
            JasperPrint jp = JasperFillManager.fillReport(fileReport.getPath(),
                    null, koneksi_db.KoneksiDB.BuatKoneksi());
           JasperViewer.viewReport(jp, false);
        }catch(JRException e){
            System.err.println(e);
        }
    }//GEN-LAST:event_iMnLpAnggotaActionPerformed

    private void txNamaPetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txNamaPetugasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txNamaPetugasActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Beranda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Beranda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Beranda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Beranda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Beranda().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAnggota;
    private javax.swing.JButton btBuku;
    private javax.swing.JButton btPeminjaman;
    private javax.swing.JButton btPengembalian;
    private javax.swing.JDesktopPane desktopBeranda;
    private javax.swing.JMenuItem iMnAnggota;
    private javax.swing.JMenuItem iMnBuku;
    private javax.swing.JMenuItem iMnLogout;
    private javax.swing.JMenuItem iMnLpAnggota;
    private javax.swing.JMenuItem iMnLpBuku;
    private javax.swing.JMenuItem iMnLpPeminjaman;
    private javax.swing.JMenuItem iMnLpPengembalian;
    private javax.swing.JMenuItem iMnPeminjaman;
    private javax.swing.JMenuItem iMnPengembalian;
    private javax.swing.JMenuItem iMnPengguna;
    private javax.swing.JMenuItem iMnTransaksi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JMenu mnKelolaData;
    private javax.swing.JMenu mnLaporan;
    private javax.swing.JMenu mnLogData;
    private javax.swing.JMenu mnSetting;
    private javax.swing.JTextField txNamaPetugas;
    // End of variables declaration//GEN-END:variables
}
