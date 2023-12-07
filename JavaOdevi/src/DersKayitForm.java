import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class DersKayitForm extends JFrame {

    private JPanel JPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton kaydetButton;

    public DersKayitForm() {
        // Form özellikleri
        setTitle("Ders Kayıt");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 300);
        setLocationRelativeTo(null);
        setVisible(true);
        setContentPane(JPanel);

        // Kaydet butonuna tıklandığında yapılacaklar
        kaydetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Text alanlarından bilgileri al
                String bilgi1 = textField1.getText();
                String bilgi2 = textField2.getText();
                String bilgi3 = textField3.getText();

                // Bilgileri ders.csv dosyasına kaydet
                kaydetDersCSV(bilgi1, bilgi2, bilgi3);

                // Kayıt işlemi tamamlandıktan sonra bir mesaj göster
                JOptionPane.showMessageDialog(null, "Ders bilgileri başarıyla kaydedildi.", "Başarılı", JOptionPane.INFORMATION_MESSAGE);

                // Anasayfa'yı oluşturup görünür hale getir
                Anasayfa anasayfa = new Anasayfa();
                anasayfa.setVisible(true);

                // DersKayitForm penceresini kapat
                dispose();
            }
        });
    }

    private void kaydetDersCSV(String bilgi1, String bilgi2, String bilgi3) {
        String dosyaYolu = "ders.csv";

        try (PrintWriter writer = new PrintWriter(new FileWriter(dosyaYolu, true))) {
            // Dosyaya ders bilgilerini eklesin.
            writer.println(bilgi1 + "," + bilgi2 + "," + bilgi3);
            writer.flush();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Dosya yazma hatası: " + ex.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new DersKayitForm();
    }
}
