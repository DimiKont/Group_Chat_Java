import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.io.IOException;

class CredentialsHandler extends JFrame implements ActionListener, KeyListener, MouseListener
{
    private JLabel uname, pass, wrong, signup;
    private JTextField username;
    private JPasswordField password;
    private JButton button;
    private JCheckBox showPassword;

    CredentialsHandler()
    {
        super("Authorization");
        setLayout(new FlowLayout());

        // Labels
        uname = new JLabel("Username ");
        pass = new JLabel("Password ");
        signup = new JLabel("<html><u>Sign up</u></html>");
        signup.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        signup.setForeground(Color.BLUE);
        signup.setFont(new Font("JetBrains Mono", Font.BOLD, 13));

        signup.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                try
                {
                    new RegistrationForm();
                }
                catch (IOException ex)
                {
                    ex.printStackTrace();
                }
            }
        });

        username = new JTextField(10); // text field for username
        password = new JPasswordField(10); // used JPasswordField to censor password

        showPassword = new JCheckBox("Show Password");
        showPassword.setFocusable(false);
        // Button to submit credentials
        button = new JButton("Login");

        wrong = new JLabel("");

        // Adding all the components to
        add(uname);
        add(username);
        add(pass);
        add(password);
        add(showPassword);
        add(button);
        add(signup);
        add(wrong);
        button.addActionListener(this);
        password.addKeyListener(this);
        showPassword.addActionListener(this);

        pack(); // same as the client
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(250, 250);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == button)
        {
            Client c1 = new Client(username.getText(), password.getText());

            if(c1.count == 0)
            {
                setVisible(false);
            }
            else
            {
                username.setText("");
                password.setText("");
                wrong.setText("Incorrect username or password");
            }

            c1.ListenForMessage();
        }

        if(showPassword.isSelected())
        {
            password.setEchoChar((char) 0);
        }
        else
        {
            password.setEchoChar('*');
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if(e.getKeyCode() == KeyEvent.VK_ENTER)
        {
            if(!username.getText().equals(""))
            {
                Client c1 = new Client(username.getText(), password.getText());
                // c1.sendMessage();
                if(c1.count == 0)
                    setVisible(false);
                else
                {
                    username.setText("");
                    password.setText("");
                    wrong.setText("Incorrect username or password");
                }
                c1.ListenForMessage();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}