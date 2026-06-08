package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class cadastro extends JFrame {
    public cadastro() {
        setTitle("tela de cadastro");
        setSize(1000, 1000);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(15, 23, 42));

        JTabbedPane abas = new JTabbedPane();
        abas.setFont(new Font("Monospaced", Font.BOLD, 25));
        // aba de cadastro de tarefa
        JPanel novaTarefa = new JPanel();
        novaTarefa.setBackground(new Color(15, 23, 42));
        novaTarefa.setLayout(new GridBagLayout());

        JPanel painelCentral = new JPanel() {
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
        painelCentral.setOpaque(false);
        painelCentral.setBorder(new EmptyBorder(30, 40, 30, 40));
        painelCentral.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        novaTarefa.add(painelCentral);

        JLabel tituloTarefa = new JLabel("Titulo Tarefa: ");
        tituloTarefa.setFont(new Font("Monospaced", Font.BOLD, 25));
        gbc.gridx = 0;
        gbc.gridy = 1;
        painelCentral.add(tituloTarefa, gbc);

        JTextField campoTitulo = new JTextField(25);
        campoTitulo.setFont(new Font("Monospaced", Font.BOLD, 25));
        gbc.gridx = 0;
        gbc.gridy = 2;
        painelCentral.add(campoTitulo, gbc);

        JLabel descricao = new JLabel("Descrição:");
        descricao.setFont(new Font("Monospaced", Font.BOLD, 25));
        gbc.gridx = 0;
        gbc.gridy = 3;
        painelCentral.add(descricao, gbc);
        abas.addTab("Nova tarefa", novaTarefa);

        JTextField campoDesc = new JTextField(25);
        campoDesc.setFont(new Font("Monospaced", Font.BOLD, 25));
        gbc.gridx = 0;
        gbc.gridy = 4;
        painelCentral.add(campoDesc, gbc);

        JLabel prioridade = new JLabel("Prioridade:");
        prioridade.setFont(new Font("Monospaced", Font.BOLD, 25));
        gbc.gridx = 1;
        gbc.gridy = 3;
        painelCentral.add(prioridade, gbc);

        String[] prioridades = { "Baixa", "Média", "Alta" };
        JComboBox<String> comboPrioridade = new JComboBox<>(prioridades);
        comboPrioridade.setFont(new Font("Monospaced", Font.BOLD, 25));
        gbc.gridx = 1;
        gbc.gridy = 4;
        painelCentral.add(comboPrioridade, gbc);

        JLabel dataEntrega = new JLabel("Data de entrega:");
        dataEntrega.setFont(new Font("Monospaced", Font.BOLD, 25));
        gbc.gridx = 1;
        gbc.gridy = 1;
        painelCentral.add(dataEntrega, gbc);
        add(abas);

        JTextField dataCampo = new JTextField(25);
        dataCampo.setFont(new Font("Monospaced", Font.BOLD, 25));
        gbc.gridx = 1;
        gbc.gridy = 2;
        painelCentral.add(dataCampo, gbc);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
        gbc.gridx = 1;
        gbc.gridy = 5;

        JButton limparCampos = new JButton("Limpar campos");
        limparCampos.setFont(new Font("Monospaced", Font.BOLD, 25));
        gbc.gridx = 1;
        gbc.gridy = 4;
        limparCampos.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JButton salvar = new JButton("Salvar");
        salvar.setBackground(Color.GREEN);
        salvar.setFont(new Font("Monospaced", Font.BOLD, 25));
        gbc.gridx = 1;
        gbc.gridy = 6;
        salvar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        painelBotoes.add(limparCampos);
        painelBotoes.add(salvar);
        painelCentral.add(painelBotoes, gbc);

        JLabel atalhos = new JLabel("F1 - Limpar Campos  F2 - Salvar");
        atalhos.setFont(new Font("Monospaced", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 6;
        painelCentral.add(atalhos, gbc);

        JButton btnVoltar = new JButton("<- Voltar (F3)");
        btnVoltar.setFont(new Font("Monospaced", Font.BOLD, 25));
        btnVoltar.setBackground(Color.RED);
        btnVoltar.setForeground(Color.WHITE);
        btnVoltar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        gbc.gridx = 0;
        gbc.gridy = 0;
        painelCentral.add(btnVoltar, gbc);

        JButton btnHist = new JButton("Histórico (F8)");
        btnHist.setFont(new Font("Monospaced", Font.BOLD, 25));
        btnHist.setCursor(new Cursor(Cursor.HAND_CURSOR));
        gbc.gridx = 1;
        gbc.gridy = 0;
        painelCentral.add(btnHist, gbc);
        // ABA DE CADASTRO ACABA AQUI ACIMA

        // ABA DE STATUS COMEÇA AQUI
        JPanel status = new JPanel();
        status.setLayout(new BorderLayout());

        JPanel painelPai = new JPanel();
        painelPai.setLayout(new BorderLayout(20, 0));

        JPanel componentes = new JPanel();
        componentes.setPreferredSize(new Dimension(250, 0));
        componentes.setLayout(new GridBagLayout());
        componentes.setBorder(new EmptyBorder(30, 20, 30, 20));
        GridBagConstraints gbcComp = new GridBagConstraints();
        gbcComp.insets = new Insets(10, 0, 10, 0);
        gbcComp.fill = GridBagConstraints.HORIZONTAL;
        gbcComp.anchor = GridBagConstraints.NORTH;
        gbcComp.weightx = 1.0;

        JLabel statusTarefas = new JLabel("Status");
        statusTarefas.setFont(new Font("Monospaced", Font.BOLD, 25));
        gbcComp.gridx = 0;
        gbcComp.gridy = 0;
        componentes.add(statusTarefas, gbcComp);

        JLabel lblNomeTarefa = new JLabel("Buscar tarefa:(F7)");
        lblNomeTarefa.setFont(new Font("Monospaced", Font.BOLD, 18));
        gbcComp.gridy = 1;
        componentes.add(lblNomeTarefa, gbcComp);

        JTextField campoPesquisa = new JTextField();
        campoPesquisa.setFont(new Font("Monospaced", Font.PLAIN, 18));
        gbcComp.gridy = 2;
        componentes.add(campoPesquisa, gbcComp);

        JButton btnConcluir = new JButton("Concluir (F5)");
        btnConcluir.setFont(new Font("Monospaced", Font.BOLD, 22));
        btnConcluir.setBackground(Color.GREEN);
        btnConcluir.setForeground(Color.BLACK);
        btnConcluir.setCursor(new Cursor(Cursor.HAND_CURSOR));
        gbcComp.gridy = 3;
        componentes.add(btnConcluir, gbcComp);

        JButton btnExcluir = new JButton("Excluir (F6)");
        btnExcluir.setFont(new Font("Monospaced", Font.BOLD, 22));
        btnExcluir.setBackground(Color.RED);
        btnExcluir.setForeground(Color.WHITE);
        btnExcluir.setCursor(new Cursor(Cursor.HAND_CURSOR));
        gbcComp.gridy = 4;
        componentes.add(btnExcluir, gbcComp);

        gbcComp.gridy = 5;
        gbcComp.weighty = 1.0;
        componentes.add(Box.createVerticalGlue(), gbcComp);

        painelPai.add(componentes, BorderLayout.WEST);

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

        painelPai.add(scrollPane, BorderLayout.CENTER);
        status.add(painelPai, BorderLayout.CENTER);
        abas.addTab("Status", status);
        setVisible(true);
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(() -> new cadastro());

    }

}
