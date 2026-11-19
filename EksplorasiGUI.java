import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class EksplorasiGUI extends JFrame {

    // Atomic Components
    private JTextField txtNamaTugas;
    private JComboBox<String> cmbPrioritas;
    private JTextArea areaDaftarTugas;
    private JButton btnTambah, btnHapus, btnKeluar;
    private int nomorTugas = 1;

    public EksplorasiGUI() {
        // 1. TOP-LEVEL CONTAINER CLASS (JFrame)
        setTitle("Eksplorasi Java GUI - Daftar Tugas");
        setSize(500, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Posisi window di tengah layar

        // 2. INTERMEDIATE CONTAINER CLASS (JPanel utama dengan BorderLayout)
        JPanel panelUtama = new JPanel(new BorderLayout(10, 10));
        panelUtama.setBorder(new EmptyBorder(15, 15, 15, 15));

        // --- BAGIAN ATAS (Header) ---
        JPanel panelHeader = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel lblJudul = new JLabel("Aplikasi Pencatat Tugas Kuliah");
        lblJudul.setFont(new Font("SansSerif", Font.BOLD, 16));
        panelHeader.add(lblJudul);

        // --- BAGIAN TENGAH (Form Input + Area Output) ---
        JPanel panelTengah = new JPanel(new BorderLayout(10, 10));

        // 4. LAYOUT CLASS 1: GridLayout (2 baris, 2 kolom untuk form)
        JPanel panelForm = new JPanel(new GridLayout(2, 2, 8, 8));
        
        // 3. ATOMIC COMPONENT CLASSES (JLabel, JTextField, JComboBox)
        JLabel lblTugas = new JLabel("Nama Tugas / Mata Kuliah:");
        txtNamaTugas = new JTextField();

        JLabel lblPrioritas = new JLabel("Tingkat Prioritas:");
        String[] opsiPrioritas = {"Tinggi", "Sedang", "Rendah"};
        cmbPrioritas = new JComboBox<>(opsiPrioritas);

        panelForm.add(lblTugas);
        panelForm.add(txtNamaTugas);
        panelForm.add(lblPrioritas);
        panelForm.add(cmbPrioritas);

        // 3. ATOMIC COMPONENT (JTextArea) di dalam INTERMEDIATE CONTAINER (JScrollPane)
        areaDaftarTugas = new JTextArea();
        areaDaftarTugas.setEditable(false);
        areaDaftarTugas.setFont(new Font("Monospaced", Font.PLAIN, 13));
        areaDaftarTugas.setMargin(new Insets(8, 8, 8, 8));
        JScrollPane scrollPane = new JScrollPane(areaDaftarTugas);

        panelTengah.add(panelForm, BorderLayout.NORTH);
        panelTengah.add(scrollPane, BorderLayout.CENTER);

        // --- BAGIAN BAWAH (Tombol Aksi) ---
        // 4. LAYOUT CLASS 2: FlowLayout (menyusun tombol secara horizontal)
        JPanel panelTombol = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        btnTambah = new JButton("Tambah Tugas");
        btnHapus = new JButton("Bersihkan");
        btnKeluar = new JButton("Keluar");

        panelTombol.add(btnTambah);
        panelTombol.add(btnHapus);
        panelTombol.add(btnKeluar);

        // Menggabungkan semua panel ke panelUtama menggunakan BorderLayout (Layout Class 3)
        panelUtama.add(panelHeader, BorderLayout.NORTH);
        panelUtama.add(panelTengah, BorderLayout.CENTER);
        panelUtama.add(panelTombol, BorderLayout.SOUTH);

        add(panelUtama);

        // 5. EVENT LISTENER (Interface Override & Adapter Class Override)
        inisialisasiEvent();
    }

    private void inisialisasiEvent() {
        // Implementasi Event Listener Interface: ActionListener
        btnTambah.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tambahTugas();
            }
        });

        btnHapus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                areaDaftarTugas.setText("");
                nomorTugas = 1;
            }
        });

        btnKeluar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        // Implementasi Event Adapter Class: KeyAdapter (tekan Enter untuk tambah tugas)
        txtNamaTugas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    tambahTugas();
                }
            }
        });
    }

    private void tambahTugas() {
        String namaTugas = txtNamaTugas.getText().trim();
        String prioritas = (String) cmbPrioritas.getSelectedItem();

        if (namaTugas.isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "Nama tugas tidak boleh kosong!",
                "Peringatan",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        areaDaftarTugas.append(nomorTugas + ". [" + prioritas + "] " + namaTugas + "\n");
        nomorTugas++;
        txtNamaTugas.setText("");
        txtNamaTugas.requestFocus();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new EksplorasiGUI().setVisible(true);
            }
        });
    }
}