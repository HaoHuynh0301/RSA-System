import javax.swing.*;
import libs.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Screen extends JFrame {
    private JTextField txt_p;
    private JTextField txt_q;
    private JPanel pannel_main;
    private JPanel pannel_create_key;
    private JPasswordField txt_pass;
    private JButton btn_showpass;
    private JTextField txt_public_key;
    private JButton btn_Createkey;
    private JButton btn_Random;
    private int p = 0;
    private int q = 0;
    GenerateKeys g;

    public Screen() {

        //Event when we click to make 2 random numbers - p and q
        btn_Random.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                g = new GenerateKeys();
                g.get_p_q();
                txt_p.setText(g.getP()+""); txt_q.setText(g.getQ()+"");
            }
        });

        //Event when we click to generate 2 keys
        btn_Createkey.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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
