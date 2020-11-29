package boothalgorithm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import boothalgorithm.Booth;

public class BoothForm {
    private JFrame frame;
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
    private JScrollPane scroll;

    public int formBit = 0;

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
                ArrayList<String> paramResult = booth.writeInResultArea();
                for (String elem : paramResult) {
                    resultArea.append(elem + "\n");
                }
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
        BoothForm boothForm = new BoothForm();
        boothForm.frame = new JFrame("Booth Algorithm GUI");

//        boothForm.scroll = new JScrollPane();
//        boothForm.scroll.setEnabled(true);
//        boothForm.scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
//        boothForm.mainPanel.add(boothForm.scroll, BorderLayout.CENTER);

        boothForm.frame.setContentPane(boothForm.mainPanel);

//        boothForm.scroll.setViewportView(boothForm.resultArea);

        boothForm.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        boothForm.frame.pack();
        boothForm.frame.setVisible(true);
    }

}
