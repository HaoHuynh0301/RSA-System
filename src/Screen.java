import javax.swing.*;

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

    public Screen() {
        
    }
    
    public static void main(String args[]) {
        Screen screen = new Screen();
        screen.setContentPane(new Screen().pannel_main);
        screen.pack();
        screen.setVisible(true);
    }

}
