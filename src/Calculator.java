import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator implements ActionListener {
    JFrame frame;
    JTextField textField;
    JButton[] numberButtons =  new JButton[10];
    JButton[] operatorButtons =  new JButton[9];
    JButton plus, minus, multi, divide;
    JButton equals, clear, decimal, percent, delete;
    JPanel panel;

    public Calculator () {
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setLayout(null);
        textField = new JTextField();
        textField.setBounds(50, 0, 300, 100);
        textField.setEditable(false);

        plus = new JButton("+");
        minus = new JButton("-");
        multi = new JButton("*");
        divide = new JButton("/");
        equals = new JButton("=");
        clear = new JButton("Clear");
        decimal = new JButton(".");
        percent = new JButton("%");
        delete = new JButton("Delete");
        operatorButtons[0] = plus;
        operatorButtons[1] = minus;
        operatorButtons[2] = multi;
        operatorButtons[3] = divide;
        operatorButtons[4] = equals;
        operatorButtons[5] = clear;
        operatorButtons[6] = decimal;
        operatorButtons[7] = percent;
        operatorButtons[8] = delete;

        for (int i = 0; i < operatorButtons.length; i++) {
            operatorButtons[i].addActionListener(this);
            operatorButtons[i].setFocusable(false);
        }

        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFocusable(false);
        }

        delete.setBounds(50, 300, 145, 50);
        clear.setBounds(205, 300, 145, 50);

        panel = new JPanel();
        panel.setBounds(50, 100, 300, 300);
        panel.setLayout(new GridLayout(4, 4, 10, 10));
        panel.setBackground(Color.BLACK);

        frame.add(clear);
        frame.add(delete);
        frame.add(textField);
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        Calculator calc = new Calculator();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}