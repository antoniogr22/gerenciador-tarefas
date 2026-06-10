package view;

import dao.usuariosDAO;
import model.usuarios;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
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
        gbc.fill = GridBagConstraints.NONE;
        componentes.add(titulo, gbc);

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
        componentes.add(usuarioC, gbc);

        JLabel senha = new JLabel("senha");
        senha.setForeground(new Color(148, 163, 184));
        senha.setFont(new Font("Monospaced", Font.BOLD, 40));
        gbc.gridx = 0;
        gbc.gridy = 3;
        componentes.add(senha, gbc);

        JPasswordField senhaC = new JPasswordField(33);
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

        JButton btnCadastrar = new JButton("cadastrar");
        gbc.gridy = 6;
        btnCadastrar.setFont(new Font("Monospaced", Font.BOLD, 20));
        btnCadastrar.setPreferredSize(new Dimension(btnCadastrar.getPreferredSize().width, 45));
        btnCadastrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCadastrar.setBackground(new Color(71, 85, 105)); 
        btnCadastrar.setForeground(Color.WHITE);
        btnCadastrar.setMnemonic(KeyEvent.VK_C);
        componentes.add(btnCadastrar, gbc);

        getRootPane().setDefaultButton(btnEntrar);

        // === AÇÃO DO BOTÃO ENTRAR (Adicionada e corrigida) ===
        btnEntrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String txtUsuario = usuarioC.getText().trim();
                String txtSenha = new String(senhaC.getPassword()).trim();

                if (txtUsuario.isEmpty() || txtSenha.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos!", "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try {
                    usuariosDAO uDAO = new usuariosDAO();
                    usuarios usuarioLogado = uDAO.fazerLogin(txtUsuario, txtSenha);

                    if (usuarioLogado != null) {
                        dispose(); // Fecha a tela de Login
                        new cadastro(usuarioLogado.getid_usuario()); // Abre a tela de cadastro passando o ID
                    } else {
                        JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos!", "Erro de Autenticação", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro técnico na conexão: " + ex.getMessage(), "Erro no Banco", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });

        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirJanelaCadastro();
            }
        });

        add(componentes);
        setVisible(true);
    }

    private void abrirJanelaCadastro() {
        JDialog janelaCadastro = new JDialog(this, "Novo Cadastro", true); 
        janelaCadastro.setSize(450, 400);
        janelaCadastro.setLocationRelativeTo(this);
        janelaCadastro.getContentPane().setBackground(new Color(15, 23, 42));
        janelaCadastro.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTitulo = new JLabel("CRIAR CONTA");
        lblTitulo.setForeground(new Color(241, 245, 249));
        lblTitulo.setFont(new Font("Monospaced", Font.BOLD, 30));
        gbc.gridx = 0; gbc.gridy = 0;
        janelaCadastro.add(lblTitulo, gbc);

        JLabel lblUsuario = new JLabel("usuário:");
        lblUsuario.setForeground(new Color(148, 163, 184));
        lblUsuario.setFont(new Font("Monospaced", Font.BOLD, 20));
        gbc.gridy = 1;
        janelaCadastro.add(lblUsuario, gbc);

        JTextField txtNovoUsuario = new JTextField(20);
        txtNovoUsuario.setFont(new Font("Monospaced", Font.PLAIN, 18));
        txtNovoUsuario.setBackground(new Color(30, 41, 59));
        txtNovoUsuario.setForeground(Color.WHITE);
        gbc.gridy = 2;
        janelaCadastro.add(txtNovoUsuario, gbc);

        JLabel lblSenha = new JLabel("senha:");
        lblSenha.setForeground(new Color(148, 163, 184));
        lblSenha.setFont(new Font("Monospaced", Font.BOLD, 20));
        gbc.gridy = 3;
        janelaCadastro.add(lblSenha, gbc);

        JPasswordField txtNovaSenha = new JPasswordField(20);
        txtNovaSenha.setFont(new Font("Monospaced", Font.PLAIN, 18));
        txtNovaSenha.setBackground(new Color(30, 41, 59));
        txtNovaSenha.setForeground(Color.WHITE);
        gbc.gridy = 4;
        janelaCadastro.add(txtNovaSenha, gbc);

        JButton btnFinalizar = new JButton("finalizar");
        btnFinalizar.setFont(new Font("Monospaced", Font.BOLD, 18));
        btnFinalizar.setPreferredSize(new Dimension(btnFinalizar.getPreferredSize().width, 40));
        btnFinalizar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        gbc.gridy = 5;
        janelaCadastro.add(btnFinalizar, gbc);

        janelaCadastro.getRootPane().setDefaultButton(btnFinalizar);

        btnFinalizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuarioStr = txtNovoUsuario.getText().trim();
                String senhaStr = new String(txtNovaSenha.getPassword()).trim();

                if (usuarioStr.isEmpty() || senhaStr.isEmpty()) {
                    JOptionPane.showMessageDialog(janelaCadastro, "Por favor, preencha todos os campos!", "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try {
                    usuarios novoUsuario = new usuarios(0, usuarioStr, senhaStr);
                    usuariosDAO dao = new usuariosDAO();
                    dao.cadastrarUsuario(novoUsuario);

                    JOptionPane.showMessageDialog(janelaCadastro, "Usuário cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    janelaCadastro.dispose(); 
                    
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(janelaCadastro, "Erro ao cadastrar: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        janelaCadastro.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Login());
    }
}