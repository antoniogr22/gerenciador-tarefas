package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class historico extends JFrame {
    public historico() {
        setTitle("Histórico");
        setSize(1000, 1000);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel pnlEsquerda = new JPanel();
        pnlEsquerda.setLayout(new GridBagLayout());
        pnlEsquerda.setBorder(new EmptyBorder(30, 20, 30, 20));
        pnlEsquerda.setPreferredSize(new Dimension(300, 0));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.weightx = 1.0;

        JLabel historico = new JLabel("Histórico");
        historico.setFont(new Font("Monospaced", Font.BOLD, 25));
        gbc.gridx = 0;
        gbc.gridy = 0;
        pnlEsquerda.add(historico, gbc);

        JLabel busca = new JLabel("Buscar tarefa (F7)");
        busca.setFont(new Font("Monospaced", Font.BOLD, 18));
        gbc.gridy = 1;
        pnlEsquerda.add(busca, gbc);

        JTextField campoBusca = new JTextField();
        campoBusca.setFont(new Font("Monospaced", Font.PLAIN, 18));
        gbc.gridy = 2;
        pnlEsquerda.add(campoBusca, gbc);

        JButton voltar = new JButton(" <- Voltar (F3)");
        voltar.setBackground(Color.RED);
        voltar.setForeground(Color.WHITE);
        voltar.setFont(new Font("Monospaced", Font.BOLD, 22));
        voltar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        gbc.gridy = 3;
        pnlEsquerda.add(voltar, gbc);

        gbc.gridy = 4;
        gbc.weighty = 1.0;
        pnlEsquerda.add(Box.createVerticalGlue(), gbc);

        add(pnlEsquerda, BorderLayout.WEST);

        String[] colunas = { "Tarefa", "Prioridade", "Data de Entrega", "Status" };
        Object[][] dados = {};

        javax.swing.table.DefaultTableModel modelo = new javax.swing.table.DefaultTableModel(dados, colunas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable tabela = new JTable(modelo);
        tabela.setFont(new Font("Monospaced", Font.PLAIN, 18));
        tabela.setRowHeight(40);
        tabela.getTableHeader().setFont(new Font("Monospaced", Font.BOLD, 18));
        tabela.getTableHeader().setReorderingAllowed(false);

        JScrollPane scrollPane = new JScrollPane(tabela);

        add(scrollPane, BorderLayout.CENTER);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new historico());
    }
}