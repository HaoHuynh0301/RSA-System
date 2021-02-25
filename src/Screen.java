import javax.swing.*;
import libs.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.security.SecureRandom;

public class Screen extends JFrame {

    private JTextField txt_p;
    private JTextField txt_q;
    private JPanel pannel_main;
    private JPanel pannel_create_key;
    private JTextField txt_pass;
    private JButton btn_showpass;
    private JTextField txt_public_key;
    private JButton btn_Createkey;
    private JButton btn_Random;
    private BigInteger public_key = new BigInteger("0");
    private BigInteger private_key = new BigInteger("0");
    public GenerateKeys g;

    public Screen() {
        btn_showpass.setIcon(new ImageIcon(getClass().getResource("/media/icons8-show-password-50.png")));

        //Event when we click the button to make 2 random numbers - p and q
        btn_Random.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GenerateKeys g = new GenerateKeys(1024);
                private_key = g.getE();
                public_key = g.getD();
                txt_public_key.setText(""); txt_pass.setText("");
                txt_p.setText(g.getP()+"");
                txt_q.setText(g.getQ()+"");
            }
        });

        //Event when we click the button to generate 2 keys
        btn_Createkey.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    txt_public_key.setText(public_key+"");
                    txt_pass.setText("****");
                    }
                catch(Exception ex) {
                    System.out.println(ex);
                }
            }
        });

        //Event when we click the button to show the private key
        btn_showpass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txt_pass.setText(private_key+"");;
            }
        });
    }

    public static void main(String args[]) {
        Screen screen = new Screen();
        screen.setContentPane(new Screen().pannel_main);
        screen.pack();
        screen.setVisible(true);
    }

}
