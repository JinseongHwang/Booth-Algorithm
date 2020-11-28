package testswing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CelsiusConverter extends JFrame {
    private JPanel mainPanel;
    private JTextField celsiusTextField;
    private JLabel celsiusLabel;
    private JButton convertButton;
    private JLabel fahrenheitLabel;
    private JRadioButton r1;
    private JRadioButton r2;
    private JRadioButton r3;
    private JRadioButton r5;
    private JRadioButton r4;
    private JLabel rLabel;

    public CelsiusConverter(String title) {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();

        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (r1.isSelected()) rLabel.setText(r1.getText());
                else if (r2.isSelected()) rLabel.setText(r2.getText());
                else if (r3.isSelected()) rLabel.setText(r3.getText());
                else if (r4.isSelected()) rLabel.setText(r4.getText());
                else if (r5.isSelected()) rLabel.setText(r5.getText());
            }
        };
        r1.addActionListener(listener);
        r2.addActionListener(listener);
        r3.addActionListener(listener);
        r5.addActionListener(listener);
        r4.addActionListener(listener);

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // grab the text from the celsiusTextField
                // convert to a Double
                // update the fahrenheitLabel
                int tempFahr = (int)((Double.parseDouble(celsiusTextField.getText())) * 1.8 + 32);
                fahrenheitLabel.setText(tempFahr + " Fahrenheit");
            }
        });

    }

    public static void main(String[] args) {
        JFrame frame = new CelsiusConverter("My Celsius Convertor");
        frame.setVisible(true);
    }
}
