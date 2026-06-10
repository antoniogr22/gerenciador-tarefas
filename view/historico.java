package view;

import conexao.conexaoBanco;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

public class historico extends JFrame {

    private int idUsuarioLogado;
    private JTable tabela;
    private DefaultTableModel modelo;

    public historico(int idUsuarioLogado) {

        this.idUsuarioLogado = idUsuarioLogado;

        setTitle("Histórico");
        setSize(1000, 1000);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(15, 23, 42));

        JPanel pnlEsquerda = new JPanel();
        pnlEsquerda.setLayout(new GridBagLayout());
        pnlEsquerda.setBorder(new EmptyBorder(30, 20, 30, 20));
        pnlEsquerda.setPreferredSize(new Dimension(400, 0));
        pnlEsquerda.setBackground(new Color(30, 41, 59));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 0, 15, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.weightx = 1.0;

        JLabel lblHistorico = new JLabel("Histórico");
        lblHistorico.setForeground(new Color(241, 245, 249));
        lblHistorico.setFont(new Font("Monospaced", Font.BOLD, 30));
        gbc.gridx = 0;
        gbc.gridy = 0;
        pnlEsquerda.add(lblHistorico, gbc);

        JLabel busca = new JLabel("Buscar tarefa (F7)");
        busca.setForeground(new Color(148, 163, 184));
        busca.setFont(new Font("Monospaced", Font.BOLD, 25));
        gbc.gridy = 1;
        pnlEsquerda.add(busca, gbc);

        JTextField campoBusca = new JTextField();
        campoBusca.setFont(new Font("Monospaced", Font.BOLD, 25));
        campoBusca.setPreferredSize(new Dimension(250, 45));
        campoBusca.setBackground(new Color(15, 23, 42));
        campoBusca.setForeground(Color.WHITE);
        campoBusca.setCaretColor(Color.WHITE);
        gbc.gridy = 2;
        pnlEsquerda.add(campoBusca, gbc);

        campoBusca.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                carregarHistorico(campoBusca.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                carregarHistorico(campoBusca.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                carregarHistorico(campoBusca.getText());
            }
        });

        JButton voltar = new JButton("<- Voltar (F3)");
        voltar.setBackground(Color.RED);
        voltar.setForeground(Color.WHITE);
        voltar.setFont(new Font("Monospaced", Font.BOLD, 25));
        voltar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        gbc.gridy = 3;
        pnlEsquerda.add(voltar, gbc);

        gbc.gridy = 4;
        gbc.weighty = 1.0;
        pnlEsquerda.add(Box.createVerticalGlue(), gbc);

        add(pnlEsquerda, BorderLayout.WEST);

        String[] colunas = {
            "Tarefa",
            "Prioridade",
            "Data de Entrega",
            "Status"
        };

        modelo = new DefaultTableModel(colunas, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabela = new JTable(modelo);
        tabela.setFont(new Font("Monospaced", Font.BOLD, 25));
        tabela.setRowHeight(50);
        tabela.getTableHeader().setFont(new Font("Monospaced", Font.BOLD, 25));
        tabela.getTableHeader().setReorderingAllowed(false);

        JScrollPane scrollPane = new JScrollPane(tabela);
        add(scrollPane, BorderLayout.CENTER);

        voltar.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0), "F3");

        voltar.getActionMap().put("F3", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new cadastro(idUsuarioLogado);
            }
        });

        voltar.addActionListener(
                e -> voltar.getActionMap().get("F3").actionPerformed(e)
        );

        campoBusca.setFocusAccelerator('7');

        carregarHistorico("");

        setVisible(true);
    }

    private void carregarHistorico(String busca) {

        modelo.setRowCount(0);

        String sql =
                "SELECT titulo, prioridade, data_entrega, status " +
                "FROM tarefas " +
                "WHERE usuario_id = ? " +
                "AND status = ? " +
                "AND titulo LIKE ?";

        try (
                Connection conn = conexaoBanco.obterConexao();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuarioLogado);
            stmt.setString(2, "Concluído");
            stmt.setString(3, "%" + busca + "%");

            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {

                    modelo.addRow(new Object[]{
                        rs.getString("titulo"),
                        rs.getString("prioridade"),
                        rs.getString("data_entrega"),
                        rs.getString("status")
                    });
                }
            }

        } catch (SQLException e) {

            System.err.println(
                    "Erro ao carregar histórico: "
                    + e.getMessage()
            );
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(
                () -> new historico(1)
        );
    }
}