import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

public class Calculator implements ActionListener {
    JFrame frame;
    JTextField textField;
    JButton[] numberButtons =  new JButton[10];
    JButton[] operatorButtons =  new JButton[8];
    JButton plus, minus, multi, divide;
    JButton equals, clear, decimal, percent, delete;
    JPanel panel;

    public Calculator () {
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 600);
        frame.setLayout(null);
        textField = new JTextField();
        textField.setBounds(50, 10, 300, 50);
        textField.setEditable(false);

        plus = new JButton("+");
        minus = new JButton("-");
        multi = new JButton("X");
        divide = new JButton("/");
        equals = new JButton("=");
        clear = new JButton("Clear");
        decimal = new JButton(".");
//        percent = new JButton("%");
        delete = new JButton("Delete");
        operatorButtons[0] = plus;
        operatorButtons[1] = minus;
        operatorButtons[2] = multi;
        operatorButtons[3] = divide;
        operatorButtons[4] = delete;
        operatorButtons[5] = clear;
        operatorButtons[6] = decimal;
        operatorButtons[7] = equals;

        for (int i = 0; i < operatorButtons.length; i++) {
            operatorButtons[i].addActionListener(this);
            operatorButtons[i].setFocusable(false);
        }

        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFocusable(false);
        }

        delete.setBounds(10, 110, 100, 50);
        clear.setBounds(110, 110, 100, 50);

        panel = new JPanel(new GridLayout(4, 4));
        panel.setBounds(10, 160, 400, 400);

        for (int i = 1; i < numberButtons.length; i++) {
            panel.add(numberButtons[i]);
            switch (i){
                case 3: panel.add(plus);
                    break;
                case 6: panel.add(minus);
                    break;
                case 9: panel.add(multi);
                    break;
            }
        }
        panel.add(decimal);
        panel.add(numberButtons[0]);
//        panel.add(percent);
        panel.add(equals);

        frame.add(panel);
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
        double num = 0;
//        char operator;
        Stack<Double> number = new Stack<Double>();
        Stack<Character> operator = new Stack<Character>();

        for (int i = 0; i < numberButtons.length; i++) {
            if (e.getSource() == numberButtons[i]) {
                textField.setText(textField.getText().concat(String.valueOf(i)));
            }
        }

        for (JButton operatorButton : operatorButtons) {
            if (operatorButton != equals && operatorButton != clear && operatorButton != delete && e.getSource() == operatorButton) {
                String operation = String.valueOf(operatorButton.getText());
                textField.setText(textField.getText().concat(operation));
            }
        }

        if (e.getSource() == equals) {
            String text = textField.getText();
            for (int i = 0; i < text.length(); i++) {
                char c = text.charAt(i);
                if (Character.isDigit(c)) {
                    while (i < text.length() && Character.isDigit(text.charAt(i))) {
                        num = num * 10 + text.charAt(i) - '0';
                        i++;
                    }
                    i--;
                    number.push(num);
                } else if (isOperator(c)) {
                    while (!operator.empty() && priority(operator.peek()) < priority(c)) {
                        double num1 = number.pop();
                        double num2 = number.pop();
                    }
                }
            }
        }
    }

    private int priority(char operator) {
        return switch (operator) {
            case '+', '-' -> 1;
            case '*', '/' -> 2;
            default -> -1;
        };
    }

    private boolean isOperator(char operator) {
        return operator == '+' || operator == '-' || operator == 'x' || operator == '/' || operator == '%';
    }
}