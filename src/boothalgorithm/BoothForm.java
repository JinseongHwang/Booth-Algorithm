package boothalgorithm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import boothalgorithm.Booth;

public class BoothForm {
    private JPanel mainPanel;
    private JRadioButton bit4Btn;
    private JRadioButton bit8Btn;
    private JRadioButton bit16Btn;
    private JRadioButton bit32Btn;
    private JRadioButton bit64Btn;
    private JTextArea resultArea;
    private JTextField multiplicandInputField;
    private JTextField multiplierInputField;
    private JButton executeBtn;
    private JButton exitBtn;
    private JLabel noticeLabel;

    public static BoothForm boothForm = new BoothForm();

    public int formBit = 0;

    public void writeInResultArea(String inputText) {
        resultArea.append(inputText);
    }

    public BoothForm() {

        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (bit4Btn.isSelected()) {
                    formBit = 4;
                } else if (bit8Btn.isSelected()) {
                    formBit = 8;
                } else if (bit16Btn.isSelected()) {
                    formBit = 16;
                } else if (bit32Btn.isSelected()) {
                    formBit = 32;
                } else if (bit64Btn.isSelected()) {
                    formBit = 64;
                }
            }
        };
        bit8Btn.addActionListener(listener);
        bit16Btn.addActionListener(listener);
        bit32Btn.addActionListener(listener);
        bit64Btn.addActionListener(listener);
        bit4Btn.addActionListener(listener);

        executeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Booth booth = new Booth(formBit, multiplicandInputField.getText(), multiplierInputField.getText());
            }
        });

        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Booth Algorithm GUI");
        frame.setContentPane(boothForm.mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
