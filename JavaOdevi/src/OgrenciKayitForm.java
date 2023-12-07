import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class OgrenciKayitForm extends JFrame {
    private JPanel JPanel;
    private JButton btn_Kaydet;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JComboBox<Ders> comboBox1;

    public OgrenciKayitForm() {
        setTitle("Öğrenci Kayıt Form");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 300);
        setLocationRelativeTo(null);
        setVisible(true);
        setContentPane(JPanel);

        List<Ders> dersListesi = dersVerileriniOku("ders.csv");
        comboBox1.setModel(new DefaultComboBoxModel<>(dersListesi.toArray(new Ders[0])));

        btn_Kaydet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String bilgi1 = textField1.getText();
                String bilgi2 = textField2.getText();
                String bilgi3 = textField3.getText();
                String bilgi4 = textField4.getText();
                Ders seciliDers = (Ders) comboBox1.getSelectedItem();

                kaydetOgrenciCSV(bilgi1, bilgi2, bilgi3, bilgi4, seciliDers);

                JOptionPane.showMessageDialog(null, "Öğrenci bilgileri başarıyla kaydedildi.", "Başarılı", JOptionPane.INFORMATION_MESSAGE);

                // Anasayfa'yı oluşturup görünür hale getir
                Anasayfa anasayfa = new Anasayfa();
                anasayfa.setVisible(true);

                // ÖğrencikayıtForm penceresini kapat
                dispose();
            }
        });
    }

    private List<Ders> dersVerileriniOku(String dosyaYolu) {
        List<Ders> dersListesi = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(dosyaYolu))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] dersBilgileri = line.split(",");
                if (dersBilgileri.length == 3) {
                    String dersKodu = dersBilgileri[0].trim();
                    String dersAd = dersBilgileri[1].trim();
                    String dersDonem = dersBilgileri[2].trim();

                    Ders ders = new Ders(dersKodu, dersAd, dersDonem);
                    dersListesi.add(ders);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Dosya okuma hatası: " + e.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
        }

        return dersListesi;
    }

    private void kaydetOgrenciCSV(String bilgi1, String bilgi2, String bilgi3, String bilgi4, Ders seciliDers) {
        String dosyaYolu = "ogrenci.csv";

        try (PrintWriter writer = new PrintWriter(new FileWriter(dosyaYolu, true))) {
            writer.println(String.format("%s,%s,%s,%s,%s", bilgi1, bilgi2, bilgi3, bilgi4, seciliDers));
            writer.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Dosya yazma hatası: " + ex.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new OgrenciKayitForm();
    }
}
