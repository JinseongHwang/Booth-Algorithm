package testswing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScrollPaneTest {
    private JPanel mainPanel;
    private JTextArea txt;
    private JButton bt;

    public ScrollPaneTest() {
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Jonas Test");
        JPanel mainPanel = new JPanel();

        JTextArea txt = new JTextArea(10, 20);
        JScrollPane scroll = new JScrollPane(txt);
        JButton bt = new JButton("Add!!!!");

        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        bt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txt.append("Append text!!\n");
            }
        });

        mainPanel.add(scroll);
        frame.add(BorderLayout.EAST, bt);
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setVisible(true);
    }

}
