package gui;

import java.awt.Desktop;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.SecureRandom;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Gui extends JFrame {

    private static final long serialVersionUID = 1L;
    private static final String UPPERCASE_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE_CHARS = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_CHARS = "!@#$%^&()_-+=<>,.?/\\|[]{}";
    private JLabel passwordLabel;
    private JTextField passwordField;
    private JButton checkButton;
    private JButton contactButton;
    private JButton docsButton;
    private JButton donateButton;

    public Gui() {

        URL iconURL = getClass().getResource("ironkey.png");
        ImageIcon icon = new ImageIcon(iconURL);
        setIconImage(icon.getImage());

        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("ironkey.png")));

        setTitle("IronKey - Desarrollado por Héctor Carpio - Proyecto Final IC");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 300);
        JPanel panel = new JPanel();
        //panel.setBackground(Color.WHITE);
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(20, 20, 10, 20);
        passwordLabel = new JLabel("Introduce tu contraseña:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 30));
        panel.add(passwordLabel, gbc);
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 20, 10, 20);
        passwordField = new JTextField();
        passwordField.setFont(new Font("Arial", Font.PLAIN, 30));
        panel.add(passwordField, gbc);
        gbc.gridy = 2;
        gbc.insets = new Insets(10, 20, 20, 20);
        checkButton = new JButton("Comprobar seguridad");
        checkButton.setFont(new Font("Arial", Font.BOLD, 30));
        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkPasswordSecurity(passwordField.getText());
            }
        });
        panel.add(checkButton, gbc);

        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.gridy = 3;
        gbc.insets = new Insets(10, 440, 20, 0);
        contactButton = new JButton("Contacto");
        contactButton.setFont(new Font("Arial", Font.BOLD, 15)); // Reduce el tamaño de la fuente
        contactButton.addActionListener(e -> {
            try {
                Desktop.getDesktop().browse(new URI("https://hectorcarpio.es/"));
            } catch (IOException | URISyntaxException ex) {
                ex.printStackTrace();
            }
        });

        panel.add(contactButton, gbc);

        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.gridy = 3;
        gbc.insets = new Insets(10, 0, 20, 470);
        docsButton = new JButton("Docs");
        docsButton.setFont(new Font("Arial", Font.BOLD, 15)); // Reduce el tamaño de la fuente
        docsButton.addActionListener(e -> {
            try {
                Desktop.getDesktop().browse(new URI("https://docs.google.com/document/d/1292r7hzQelR-jhZ4JtS5ehrx9sTQ60wZvOszYGaVMGU/edit?usp=sharing"));
            } catch (IOException | URISyntaxException ex) {
                ex.printStackTrace();
            }
        });

        panel.add(docsButton, gbc);

        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.gridy = 3;
        gbc.insets = new Insets(10, 0, 20, 0);
        donateButton = new JButton("Donaciones");
        donateButton.setFont(new Font("Arial", Font.BOLD, 15)); // Reduce el tamaño de la fuente
        donateButton.addActionListener(e -> {
            try {
                Desktop.getDesktop().browse(new URI("https://checkout.stripe.com/c/pay/cs_live_b145bJr2nUlTBqHM1NFh1pfFLkZ1J7V9QeYHsnm0P3CFNRwduBo717lzG4#fidkdWxOYHwnPyd1blppbHNgWjA0ST1pdlNMcn1TMWRsUVVia1VHSk9AakpWUDU2dXxmbmF1QWhDRk9pamFJMl1XSD1pYnBISFNxR2JfPUNOQjVvUkk0bFxPNmJPYGxhdXVzc2swcF9LQE1cNTVud19vYUpcQScpJ3VpbGtuQH11anZgYUxhJz8nYFNkYVRRMTx9PG5MYUFSMnZ2Jyknd2BjYHd3YHdKd2xibGsnPydtcXF1dj8qKm1gZnFqd2Zkd3VsaitgdioneCUl"));
            } catch (IOException | URISyntaxException ex) {
                ex.printStackTrace();
            }
        });

        panel.add(donateButton, gbc);
        add(panel);
        setVisible(true);
    }

    public void checkPasswordSecurity(String password) {
        boolean containsUppercase = false;
        boolean containsLowercase = false;
        boolean containsDigit = false;
        boolean containsSpecialChar = false;
        boolean isLongEnough = false;
        if (password.length() >= 8) {
            isLongEnough = true;
        }
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                containsUppercase = true;
            } else if (Character.isLowerCase(c)) {
                containsLowercase = true;
            } else if (Character.isDigit(c)) {
                containsDigit = true;
            } else if (SPECIAL_CHARS.indexOf(c) != -1) {
                containsSpecialChar = true;
            }
        }
        if (containsUppercase && containsLowercase && containsDigit && containsSpecialChar && isLongEnough) {
            JOptionPane.showMessageDialog(this, "¡Tu contraseña es segura!");
        } else {
            UIManager.put("OptionPane.yesButtonText", "Sí");
            UIManager.put("OptionPane.noButtonText", "No");
            UIManager.put("OptionPane.cancelButtonText", "Cancelar");
            int result = JOptionPane.showConfirmDialog(this, "Tu contraseña no es lo suficientemente segura. ¿Deseas generar una nueva?", "Generar nueva contraseña", JOptionPane.YES_NO_OPTION);
            UIManager.put("OptionPane.yesButtonText", "Sí");
            UIManager.put("OptionPane.noButtonText", "No");
            UIManager.put("OptionPane.cancelButtonText", "Cancelar");
            if (result == JOptionPane.YES_OPTION) {
                generateNewPassword(password);
            }
        }
    }

    public void generateNewPassword(String oldPassword) {
        SecureRandom random = new SecureRandom();
        StringBuilder newPasswordBuilder = new StringBuilder();
        while (newPasswordBuilder.length() < 8) {
            int charType = random.nextInt(4);
            if (charType == 0) {
                newPasswordBuilder.append(UPPERCASE_CHARS.charAt(random.nextInt(UPPERCASE_CHARS.length())));
            } else if (charType == 1) {
                newPasswordBuilder.append(LOWERCASE_CHARS.charAt(random.nextInt(LOWERCASE_CHARS.length())));
            } else if (charType == 2) {
                newPasswordBuilder.append(DIGITS.charAt(random.nextInt(DIGITS.length())));
            } else {
                newPasswordBuilder.append(SPECIAL_CHARS.charAt(random.nextInt(SPECIAL_CHARS.length())));
            }
        }
        String newPassword = newPasswordBuilder.toString();
        StringBuilder finalPasswordBuilder = new StringBuilder();
        for (int i = 0; i < oldPassword.length(); i++) {
            char c = oldPassword.charAt(i);
            if (newPassword.indexOf(c) == -1) {
                finalPasswordBuilder.append(c);
            }
        }
        finalPasswordBuilder.append(newPassword);
        String suggestedPassword = finalPasswordBuilder.toString();
        JTextField passwordTextField = new JTextField(suggestedPassword);

        passwordTextField.setEditable(false);

        JButton passwordMonsterButton = new JButton("Probar seguridad de la contraseña");
        passwordMonsterButton.addActionListener(e -> {
            try {
                Desktop.getDesktop().browse(new URI("https://www.passwordmonster.com/"));
            } catch (IOException | URISyntaxException ex) {
                ex.printStackTrace();
            }
        });

        JButton txtButton = new JButton("Generar txt con la contraseña");
        txtButton.addActionListener(e -> {

            try {
                String desktopPath = System.getProperty("user.home") + "/Desktop";
                File file = new File(desktopPath, "passugerida.txt");
                FileWriter fw = new FileWriter(file);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(suggestedPassword);
                bw.close();
                JOptionPane.showMessageDialog(this, "Archivo passugerida.txt generado en el escritorio con éxito.");
            } catch (IOException ex) {
                System.out.println("Error al crear o escribir en el archivo.");
                ex.printStackTrace();
            }
        });

        Object[] message = {
            "Te sugerimos utilizar la siguiente contraseña:",
            passwordTextField,
            "¿Estás satisfecho con esta contraseña?", passwordMonsterButton, txtButton
        };
        int result = JOptionPane.showConfirmDialog(this, message, "¿Contento ahora?", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(this, "Ha sido un placer ayudarte, no olvides cambiar periodicamente tus contraseñas.", "Gracias por usar IronKey", JOptionPane.DEFAULT_OPTION);
            dispose();
        } else if (result == JOptionPane.NO_OPTION) {
            generateNewPassword(oldPassword);
        }
    }

    public static void main(String[] args) {
        // Establecer la apariencia de la interfaz de usuario según el sistema operativo
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> new Gui());
    }
}

