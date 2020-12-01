package boothalgorithm2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BoothFormV2 {
    public static int formBit = 0;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Booth Algorithm GUI - 1723940 황진성");
        JPanel mainPanel = new JPanel();
        ButtonGroup radioGroup = new ButtonGroup();
        JRadioButton bit4Btn = new JRadioButton("4bit");
        JRadioButton bit8Btn = new JRadioButton("8bit");
        JRadioButton bit16Btn = new JRadioButton("16bit");
        JRadioButton bit32Btn = new JRadioButton("32bit");
        JRadioButton bit64Btn = new JRadioButton("64bit");
        JTextArea resultArea = new JTextArea(30, 180);
        JTextField multiplicandInputField = new JTextField(30);
        JTextField multiplierInputField = new JTextField(30);
        JButton executeBtn = new JButton("실행");
        JButton exitBtn = new JButton("종료");
        JLabel noticeLabel = new JLabel("반드시 bit, 피승수, 승수를 입력 후 실행 버튼을 눌려주세요!");
        JLabel multiplicandLabel = new JLabel("피승수 입력: ");
        JLabel multiplierLabel = new JLabel("승수 입력");
        JScrollPane scroll = new JScrollPane(resultArea);

        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        resultArea.setFont(new Font("D2Coding ligature", Font.BOLD, 15));

        radioGroup.add(bit4Btn);
        radioGroup.add(bit8Btn);
        radioGroup.add(bit16Btn);
        radioGroup.add(bit32Btn);
        radioGroup.add(bit64Btn);

        // ------------------------------------------ Event Listener - START

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
                BoothV2 booth = new BoothV2(formBit, multiplicandInputField.getText(), multiplierInputField.getText());
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

        // ------------------------------------------ Event Listener - END

        mainPanel.add(noticeLabel);

        mainPanel.add(bit4Btn);
        mainPanel.add(bit8Btn);
        mainPanel.add(bit16Btn);
        mainPanel.add(bit32Btn);
        mainPanel.add(bit64Btn);

        mainPanel.add(multiplicandLabel);
        mainPanel.add(multiplicandInputField);

        mainPanel.add(multiplierLabel);
        mainPanel.add(multiplierInputField);

        mainPanel.add(scroll);
        mainPanel.add(executeBtn);
        mainPanel.add(exitBtn);

        frame.setContentPane(mainPanel);

        // ------------------------------------------ vital functions for GUI program - START
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1500, 650));
        frame.pack();
        frame.setVisible(true);
        // ------------------------------------------ vital functions for GUI program - END
    }

}
