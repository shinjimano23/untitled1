import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.regex.Pattern;
import javax.swing.*;

public class loginForm {

    public class LoginWindow extends JFrame {
        private JPanel mainPanel;
        private JTextField usernameField;
        private JButton loginButton;
        private JPasswordField passwordField;
        private JLabel usernameLabel;
        private JLabel passwordLabel;
        private JLabel messageLabel;
        private JLabel emailLabel;
        private JTextField emailField;
        private JCheckBox agreeCheckBox;

        private final Pattern emailPattern = Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");

        public LoginWindow() {
            setSize(750, 750);
            setContentPane(mainPanel);
            setVisible(true);
            loginButton.setEnabled(false);

            loginButton.addActionListener(e -> {
                String enteredPassword = new String(passwordField.getPassword());
                if (enteredPassword.equals("CorrectPassword3")) {
                    messageLabel.setForeground(Color.GREEN);
                    messageLabel.setText("Login Successful!");
                } else {
                    messageLabel.setForeground(Color.RED);
                    messageLabel.setText("Incorrect Password!");
                }
            });

            usernameField.addFocusListener(new FocusAdapter() {
                @Override
                public void focusLost(FocusEvent e) {
                    String username = usernameField.getText();
                    if (username.length() <= 5) {
                        messageLabel.setForeground(Color.RED);
                        messageLabel.setText("Username must be more than 5 characters.");
                        usernameField.requestFocus();
                    } else {
                        messageLabel.setText("");
                    }
                }
            });

            emailField.addFocusListener(new FocusAdapter() {
                @Override
                public void focusLost(FocusEvent e) {
                    if (emailPattern.matcher(emailField.getText()).matches()) {
                        messageLabel.setText("");
                    } else {
                        messageLabel.setForeground(Color.RED);
                        messageLabel.setText("Invalid email address!");
                        emailField.requestFocus();
                    }
                }
            });

            passwordField.addFocusListener(new FocusAdapter() {
                @Override
                public void focusLost(FocusEvent e) {
                    String password = new String(passwordField.getPassword());

                    if (password.length() <= 8) {
                        messageLabel.setForeground(Color.RED);
                        messageLabel.setText("Password must be longer than 8 characters.");
                        passwordField.requestFocus();
                    } else if (password.equals(password.toLowerCase())) {
                        messageLabel.setForeground(Color.RED);
                        messageLabel.setText("Password must contain at least one uppercase letter.");
                        passwordField.requestFocus();
                    } else if (!containsNumber(password)) {
                        messageLabel.setForeground(Color.RED);
                        messageLabel.setText("Password must contain at least one number.");
                        passwordField.requestFocus();
                    }
                }
            });

            agreeCheckBox.addItemListener(e -> loginButton.setEnabled(agreeCheckBox.isSelected()));
        }

        private boolean containsNumber(String password) {
            for (char character : password.toCharArray()) {
                if (Character.isDigit(character)) {
                    return true;
                }
            }
            return false;
        }
    }
}
