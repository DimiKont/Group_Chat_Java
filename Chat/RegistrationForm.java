//package ChatApp;

import java.awt.FlowLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.IOException;

import javax.swing.*;

public class RegistrationForm extends JFrame implements ActionListener
{
    private JTextField uname, email;
    private JPasswordField upass;
    private JButton register;
    private JComboBox<String> chooseSex;
    private JCheckBox showPassReg;

    public RegistrationForm() throws IOException
    {
        super("Registration Window");
        initializeGUI();
    }

    private void initializeGUI()
    {
        setLayout(new FlowLayout());

        JLabel usernameLabel = new JLabel("Username ");
        JLabel passwordLabel = new JLabel("Password ");
        JLabel emailLabel = new JLabel("Email          "); // 10 spaces to align

//        check = new JLabel("Already a user?");
//        check.setFont(new Font("JetBrains Mono", Font.BOLD, 13));

        showPassReg = new JCheckBox("Show Password");
        showPassReg.setFocusable(false);

        String [] names = {"Male", "Female", "Other"};
        chooseSex = new JComboBox<>(names);

        uname = new JTextField(12);
        email = new JTextField(12);
        upass = new JPasswordField(12);

        register = new JButton("Register");

        // Add the components to the main frame
        add(usernameLabel);
        add(uname);
        add(emailLabel);
        add(email);
        add(passwordLabel);
        add(upass);
        add(chooseSex);
        add(showPassReg);
        add(register);
//        add(check);

        register.addActionListener(this);
        showPassReg.addActionListener(this);
        chooseSex.addActionListener(this);

        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(250,250);
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == register)
        {
            String choice = (String) chooseSex.getSelectedItem();

//            if (!uname.getText().isEmpty() && !upass.getText().isEmpty() && email.getText().isEmpty())
            new Database(uname.getText(), upass.getText(), email.getText(), choice);
        }

        if(showPassReg.isSelected())
            upass.setEchoChar((char)0);
        else
            upass.setEchoChar('*');
    }
}
