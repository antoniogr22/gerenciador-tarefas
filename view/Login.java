package view;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Login extends JFrame {
    public Login() {
        setTitle("tela de Login");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600,600);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        getContentPane().setBackground(new Color(15, 23, 42));

        JPanel componentes = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        componentes.setOpaque(false);
        componentes.setBorder(new EmptyBorder(30, 40, 30, 40));
        componentes.setLayout(new GridBagLayout());
        componentes.setBackground(new Color(30, 41, 59));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);

        JLabel titulo = new JLabel("GESTÃO TAREFAS");
        titulo.setForeground(new Color(241, 245, 249));
        titulo.setFont(new Font("Monospaced", Font.BOLD, 60));
        gbc.gridx = 0;
        gbc.gridy = 0;
        componentes.add(titulo,gbc);

        gbc.anchor = GridBagConstraints.WEST; 
    
        JLabel nome = new JLabel("usuário");
        nome.setForeground(new Color(148, 163, 184));
        nome.setFont(new Font("Monospaced", Font.BOLD, 40));
        gbc.gridx = 0;
        gbc.gridy = 1;
        componentes.add(nome, gbc);

        JTextField usuarioC = new JTextField(33);
        gbc.gridx = 0;
        gbc.gridy = 2;
        usuarioC.setFont(new Font("Monospaced", Font.PLAIN, 25));
        usuarioC.setBackground(new Color(15, 23, 42));
        usuarioC.setForeground(Color.WHITE);
        usuarioC.setCaretColor(Color.WHITE);
        componentes.add(usuarioC,gbc);

        JLabel senha = new JLabel("senha");
        senha.setForeground(new Color(148, 163, 184));
        senha.setFont(new Font("Monospaced", Font.BOLD, 40));
        gbc.gridx = 0;
        gbc.gridy = 3;
        componentes.add(senha, gbc);

        JTextField senhaC = new JTextField(33);
        gbc.gridx = 0;
        gbc.gridy = 4;
        senhaC.setFont(new Font("Monospaced", Font.BOLD, 25));
        senhaC.setBackground(new Color(15, 23, 42));
        senhaC.setForeground(Color.WHITE);
        senhaC.setCaretColor(Color.WHITE);
        componentes.add(senhaC, gbc);

        JButton btnEntrar = new JButton("entrar");
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        btnEntrar.setFont(new Font("Monospaced", Font.BOLD, 20));
        btnEntrar.setPreferredSize(new Dimension(btnEntrar.getPreferredSize().width, 45));
        btnEntrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        componentes.add(btnEntrar, gbc);

        add(componentes);
        setVisible(true);
    }

    public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> new Login());
    }
}