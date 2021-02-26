import javax.swing.*;
import libs.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
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
    private JButton btn_Sign;
    private JButton btn_Export;
    private JPanel pannel_encrypt_1;
    private JTextField txt_Filepath;
    private JTextField txt_encrypt_file;
    private JButton btn_Select;
    private JTextField txt_Filepath_2;
    private JButton btn_Select_2;
    private JTextField txt_decrypt_file;
    private JButton btn_Export_2;
    private JButton btn_Decrypt;
    private BigInteger public_key = new BigInteger("0");
    private BigInteger private_key = new BigInteger("0");
    private GenerateKeys g;
    private String filepath = new String("");
    private String encrypt_file;
    private String decrypt_file;
    private String filename = new String("");
    private String filepath_2 = new String("");
    private String filename_2 = new String("");

    private boolean flag = false;

    public Screen() {

        btn_showpass.setIcon(new ImageIcon(getClass().getResource("/media/icons8-show-password-50.png")));
        //Event when we click the button to make 2 random numbers - p and q
        btn_Random.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                g = new GenerateKeys(1024);
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
                if(flag == false) {
                    txt_pass.setText(private_key+"");
                    flag = true;
                } else {
                    txt_pass.setText("****");
                    flag = false;
                }
            }
        });

        //Event when we click the button to select file
        btn_Select_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txt_Filepath_2.setText("");
                JFileChooser file = new JFileChooser();
                file.setMultiSelectionEnabled(true);
                file.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                file.setFileHidingEnabled(false);
                if (file.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    java.io.File f = file.getSelectedFile();
                    filepath_2 = f.getPath();
                    txt_Filepath_2.setText(f.getPath());
                }
            }});

        btn_Select.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txt_Filepath.setText("");
                JFileChooser file = new JFileChooser();
                file.setMultiSelectionEnabled(true);
                file.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                file.setFileHidingEnabled(false);
                if (file.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    java.io.File f = file.getSelectedFile();
                    filepath = f.getPath();
                    txt_Filepath.setText(f.getPath());
                }
            }
        });

        //Event when we click the button to export the result

        btn_Export_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BufferedWriter writer = null;
                try {
                    writer = new BufferedWriter(new FileWriter("D:\\RSA_Project\\src\\result\\Decrypt.txt")); //Your folder's path that you wanna save
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                try {
                    writer.write(decrypt_file);
                    writer.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        btn_Export.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BufferedWriter writer = null;
                try {
                    writer = new BufferedWriter(new FileWriter("D:\\RSA_Project\\src\\result\\Encrypt.txt")); //Your folder's path that you wanna save
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                try {
                    writer.write(encrypt_file);
                    writer.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        //Event when we click the button the sign text
        btn_Sign.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if("".equals(filepath)) {
                    JOptionPane.showMessageDialog(null, "Chọn file trước khi mã hóa", "Thông báo", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    filename = Functions.readFileAsString(filepath);
                    encrypt_file = g.encrypt(filename);
                    System.out.println(encrypt_file);
                    txt_encrypt_file.setText(encrypt_file);
                }
            }
        });

        //Event when we click the button to decrypt text
        btn_Decrypt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if("".equals(filepath_2)) {
                    JOptionPane.showMessageDialog(null, "Chọn file trước khi xác thực", "Thông báo", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    filename_2 = Functions.readFileAsString(filepath_2);
                    decrypt_file = g.decrypt(filename_2);
                    System.out.println(decrypt_file);
                    txt_decrypt_file.setText(decrypt_file);
                }
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
