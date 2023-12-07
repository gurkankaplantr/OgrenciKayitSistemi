import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Anasayfa extends JFrame {
    private JButton btn_DersKayit;
    private JButton btn_Ogrenci;
    private JPanel JPanel;

    public Anasayfa(){


        setTitle("ANASAYFA");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300,  300);
        setLocationRelativeTo(null);
        setVisible(true);
        setContentPane(JPanel);

        // DERS KAYIT BUTONUNA TIKLANINCA YAPILACAKLAR
        btn_DersKayit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DersKayitForm dersKayitForm = new DersKayitForm();
                dersKayitForm.setVisible(true); // DersKayitForm'u görünür hale getiriyoruz.
            }
        });

        // ÖĞRENCİ KAYIT BUTONUNA TIKLANINCA YAPILACAKLAR
        btn_Ogrenci.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OgrenciKayitForm ogrenciKayitForm = new OgrenciKayitForm();
                ogrenciKayitForm.setVisible(true); // OgrenciKayitForm'u görünür hale getiriyoruz.
            }
        });
    }

    public static void main(String[] args) {
        new Anasayfa();
    }
}
