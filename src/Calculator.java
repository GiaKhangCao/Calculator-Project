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
    JButton equals, clear, decimal, delete;
    JPanel panel;

    public Calculator () {
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 600);
        frame.setLayout(null);
        textField = new JTextField();
        textField.setBounds(50, 10, 300, 50);
        textField.setEditable(false);

        //Initialize buttons
        plus = new JButton("+");
        minus = new JButton("-");
        multi = new JButton("X");
        divide = new JButton("/");
        equals = new JButton("=");
        clear = new JButton("Clear");
        decimal = new JButton(".");
        delete = new JButton("Delete");
        operatorButtons[0] = plus;
        operatorButtons[1] = minus;
        operatorButtons[2] = multi;
        operatorButtons[3] = divide;
        operatorButtons[4] = delete;
        operatorButtons[5] = clear;
        operatorButtons[6] = decimal;
        operatorButtons[7] = equals;

        for (JButton operatorButton : operatorButtons) {
            operatorButton.addActionListener(this);
            operatorButton.setFocusable(false);
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
        panel.add(divide);

        frame.add(panel);
        frame.add(clear);
        frame.add(delete);
        frame.add(textField);
        frame.setVisible(true);
    }

    //Operation of Calculator
    @Override
    public void actionPerformed(ActionEvent e) {
//        char operator;
        double result;
        Stack<Double> number = new Stack<>();//Stack holds number
        Stack<Character> operator = new Stack<>();//Stack holds operator

        //Field the text when button hit
        for (int i = 0; i < numberButtons.length; i++) {
            if (e.getSource() == numberButtons[i]) {
                textField.setText(textField.getText().concat(String.valueOf(i)));
            }
        }

        //Read calculation on textField
        for (JButton operatorButton : operatorButtons) {
            if (operatorButton != equals && operatorButton != clear && operatorButton != delete && e.getSource() == operatorButton) {
                String operation = String.valueOf(operatorButton.getText());
                textField.setText(textField.getText().concat(operation));
            }
        }

        //When "clear" button hit
        if (e.getSource() == clear) {
            textField.setText("");
        }

        //When "delete" hit
        if (e.getSource() == delete) {
            String text = textField.getText();
            textField.setText(text.substring(0, text.length()-1));
        }

        //When "equals" button hit
        if (e.getSource() == equals) {
            String text = textField.getText();
            //Traverse content on textField
            for (int i = 0; i < text.length(); i++) {
                char c = text.charAt(i);
                //If there is a "." or a number
                if (Character.isDigit(c) || c == '.') {
                    double num = 0;
                    boolean decimalMode = false;
                    double digit;
                    int fractional = 10;

                    while (i < text.length() && (Character.isDigit(text.charAt(i)) || text.charAt(i) == '.')) {
                        //If there is "."
                        if (text.charAt(i) == '.') {
                            decimalMode = true;
                        } else {
                            digit = Double.parseDouble(String.valueOf(text.charAt(i)));

                            if (decimalMode) {
                                num = num + digit/fractional;
                                fractional /= 10;
                            } else {
                                num = 10*num + digit;
                            }
                        }
                        i++;
                    }
                    i--;
                    number.push(num);
                } else if (isOperator(c)) {
                    while (!operator.empty() && priority(c) <= priority(operator.peek())) {
                        double num2 = Double.parseDouble(String.valueOf(number.pop()));
                        double num1 = Double.parseDouble(String.valueOf(number.pop()));
                        char operation = operator.pop();
                        result = calculate(operation, num1, num2);
                        number.push(result);
                    }
                    operator.push(c);

                }
            }

            //Calculation
            while (!operator.empty()) {
                double num2 = Double.parseDouble(String.valueOf(number.pop()));
                double num1 = Double.parseDouble(String.valueOf(number.pop()));
                char operation = operator.peek();
                result = calculate(operation, num1, num2);
                number.push(result);
                operator.pop();
            }
            result = number.pop();
            textField.setText(String.valueOf(result));
        }
    }

    private int priority(char operator) {
        return switch (operator) {
            case '+', '-' -> 1;
            case 'X', '/' -> 2;
            default -> -1;
        };
    }

    private boolean isOperator(char operator) {
        return operator == '+' || operator == '-' || operator == 'X' || operator == '/' || operator == '%';
    }

    private Double calculate(char operator, double num1, double num2) {
//        double result;
        return switch (operator) {
            case '+' -> num1 + num2;
            case '-' -> num1 - num2;
            case 'X' -> num1 * num2;
            case '/' -> {
                if (num2 == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                yield num1 / num2;
            }
            default -> 0.0;
        };
    }

    public static void main(String[] args) {
        Calculator calc = new Calculator();
    }
}