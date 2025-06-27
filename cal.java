import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SimpleCalc extends JFrame implements ActionListener {
    JTextField display;
    String input = "", operator = "";
    double num1 = 0;

    public SimpleCalc() {
        setTitle("Simple Calc");
        setSize(300, 400);
        setLayout(new BorderLayout());

        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 24));
        add(display, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(4, 4, 5, 5));
        String[] buttons = {"7","8","9","+","4","5","6","-","1","2","3","*","0","C","=","/"};

        for (String text : buttons) {
            JButton btn = new JButton(text);
            btn.setFont(new Font("Arial", Font.BOLD, 20));
            btn.addActionListener(this);
            panel.add(btn);
        }

        add(panel, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if (cmd.matches("\\d")) {
            input += cmd;
            display.setText(input);
        } else if (cmd.matches("[+\\-*/]")) {
            operator = cmd;
            num1 = Double.parseDouble(input);
            input = "";
        } else if (cmd.equals("=")) {
            double num2 = Double.parseDouble(input);
            double result = switch (operator) {
                case "+" -> num1 + num2;
                case "-" -> num1 - num2;
                case "*" -> num1 * num2;
                case "/" -> num2 != 0 ? num1 / num2 : 0;
                default -> 0;
            };
            display.setText(String.valueOf(result));
            input = "";
        } else if (cmd.equals("C")) {
            input = "";
            operator = "";
            display.setText("");
        }
    }

    public static void main(String[] args) {
        new SimpleCalc();
    }
}
