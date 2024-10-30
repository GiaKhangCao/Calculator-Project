import javax.swing.*;
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