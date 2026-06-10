package view;

import conexao.conexaoBanco;
import dao.categoriasDAO;
import dao.tagsDAO;
import dao.tarefas_tagsDAO;
import model.categorias;
import model.tags;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class cadastro extends JFrame {
    private int idUsuarioLogado;
    private JComboBox<String> comboCategoria;
    private JList<String> listaTags;
    private DefaultComboBoxModel<String> modelCategorias;
    private DefaultListModel<String> modelTags;
    private JTable tabela;
    private DefaultTableModel modeloTabela;
    
    private List<categorias> listaCategoriasBanco;
    private List<tags> listaTagsBanco;
    private List<Integer> listaIdsTarefasTabela = new ArrayList<>();

    public cadastro(int idUsuarioLogado) {
        this.idUsuarioLogado = idUsuarioLogado;

        JTextField campoTitulo = new JTextField(25);
        campoTitulo.setFont(new Font("Monospaced", Font.BOLD, 25));

        JTextField campoDesc = new JTextField(25);
        campoDesc.setFont(new Font("Monospaced", Font.BOLD, 25));

        JTextField dataCampo = new JTextField(25);
        dataCampo.setFont(new Font("Monospaced", Font.BOLD, 25));

        String[] prioridades = { "Baixa", "Média", "Alta" };
        JComboBox<String> comboPrioridade = new JComboBox<>(prioridades);
        comboPrioridade.setFont(new Font("Monospaced", Font.BOLD, 25));

        JButton limparCampos = new JButton("Limpar campos");
        limparCampos.setFont(new Font("Monospaced", Font.BOLD, 25));
        limparCampos.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JButton salvar = new JButton("Salvar");
        salvar.setBackground(Color.GREEN);
        salvar.setFont(new Font("Monospaced", Font.BOLD, 25));
        salvar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JButton btnVoltar = new JButton("<- Voltar (F3)");
        btnVoltar.setFont(new Font("Monospaced", Font.BOLD, 25));
        btnVoltar.setBackground(Color.RED);
        btnVoltar.setForeground(Color.WHITE);
        btnVoltar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JButton btnHist = new JButton("Histórico (F8)");
        btnHist.setFont(new Font("Monospaced", Font.BOLD, 25));
        btnHist.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JTextField campoPesquisa = new JTextField();
        campoPesquisa.setFont(new Font("Monospaced", Font.PLAIN, 18));

        JButton btnConcluir = new JButton("Concluir (F5)");
        btnConcluir.setFont(new Font("Monospaced", Font.BOLD, 22));
        btnConcluir.setBackground(Color.GREEN);
        btnConcluir.setForeground(Color.BLACK);
        btnConcluir.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JButton btnExcluir = new JButton("Excluir (F6)");
        btnExcluir.setFont(new Font("Monospaced", Font.BOLD, 22));
        btnExcluir.setBackground(Color.RED);
        btnExcluir.setForeground(Color.WHITE);
        btnExcluir.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        setTitle("tela de cadastro");
        setSize(1000, 1000);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(15, 23, 42));

        JTabbedPane abas = new JTabbedPane();
        abas.setFont(new Font("Monospaced", Font.BOLD, 25));
        
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

        gbc.gridx = 0;
        gbc.gridy = 2;
        painelCentral.add(campoTitulo, gbc);

        JLabel descricao = new JLabel("Descrição:");
        descricao.setFont(new Font("Monospaced", Font.BOLD, 25));
        gbc.gridx = 0;
        gbc.gridy = 3;
        painelCentral.add(descricao, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        painelCentral.add(campoDesc, gbc);

        JLabel labelCategoria = new JLabel("Categoria:");
        labelCategoria.setFont(new Font("Monospaced", Font.BOLD, 25));
        gbc.gridx = 0;
        gbc.gridy = 5;
        painelCentral.add(labelCategoria, gbc);

        modelCategorias = new DefaultComboBoxModel<>();
        
        comboCategoria = new JComboBox<>(modelCategorias);
        comboCategoria.setFont(new Font("Monospaced", Font.BOLD, 25));
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        painelCentral.add(comboCategoria, gbc);
        gbc.fill = GridBagConstraints.NONE;

        JLabel prioridade = new JLabel("Prioridade:");
        prioridade.setFont(new Font("Monospaced", Font.BOLD, 25));
        gbc.gridx = 1;
        gbc.gridy = 3;
        painelCentral.add(prioridade, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        painelCentral.add(comboPrioridade, gbc);

        JLabel dataEntrega = new JLabel("Data de entrega:");
        dataEntrega.setFont(new Font("Monospaced", Font.BOLD, 25));
        gbc.gridx = 1;
        gbc.gridy = 1;
        painelCentral.add(dataEntrega, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        painelCentral.add(dataCampo, gbc);

        JLabel labelTags = new JLabel("Tags (Segure Ctrl):");
        labelTags.setFont(new Font("Monospaced", Font.BOLD, 25));
        gbc.gridx = 1;
        gbc.gridy = 5;
        painelCentral.add(labelTags, gbc);

        modelTags = new DefaultListModel<>();
        listaTags = new JList<>(modelTags);
        listaTags.setFont(new Font("Monospaced", Font.BOLD, 20));
        JScrollPane scrollTags = new JScrollPane(listaTags);
        scrollTags.setPreferredSize(new Dimension(scrollTags.getPreferredSize().width, 100));
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.BOTH;
        painelCentral.add(scrollTags, gbc);
        gbc.fill = GridBagConstraints.NONE;

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
        gbc.gridx = 1;
        gbc.gridy = 7;

        painelBotoes.add(limparCampos);
        painelBotoes.add(salvar);
        painelCentral.add(painelBotoes, gbc);

        JLabel atalhos = new JLabel("F1 - Limpar  F2 - Salvar");
        atalhos.setFont(new Font("Monospaced", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 7;
        painelCentral.add(atalhos, gbc);

        gbc.gridx = 0;
        gbc.gridy = 0;
        painelCentral.add(btnVoltar, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        painelCentral.add(btnHist, gbc);

        abas.addTab("Nova tarefa", novaTarefa);

        JPanel status = new JPanel();
        status.setLayout(new BorderLayout());

        JPanel painelPai = new JPanel();
        painelPai.setLayout(new BorderLayout(20, 0));

        JPanel componentes = new JPanel();
        componentes.setPreferredSize(new Dimension(280, 0));
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

        gbcComp.gridy = 2;
        componentes.add(campoPesquisa, gbcComp);

        gbcComp.gridy = 3;
        componentes.add(btnConcluir, gbcComp);

        gbcComp.gridy = 4;
        componentes.add(btnExcluir, gbcComp);

        gbcComp.gridy = 5;
        gbcComp.weighty = 1.0;
        componentes.add(Box.createVerticalGlue(), gbcComp);

        painelPai.add(componentes, BorderLayout.WEST);

        String[] colunas = { "Tarefa", "Categoria", "Tags", "Prioridade", "Data de Entrega", "Status" };
        Object[][] dados = {};

        modeloTabela = new DefaultTableModel(dados, colunas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabela = new JTable(modeloTabela);
        tabela.setFont(new Font("Monospaced", Font.PLAIN, 18));
        tabela.setRowHeight(40);
        tabela.getTableHeader().setFont(new Font("Monospaced", Font.BOLD, 18));
        tabela.getTableHeader().setReorderingAllowed(false);

        JScrollPane scrollPane = new JScrollPane(tabela);

        painelPai.add(scrollPane, BorderLayout.CENTER);
        status.add(painelPai, BorderLayout.CENTER);
        abas.addTab("Status", status);

        atualizarDadosDoBanco();

        limparCampos.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0), "F1");
        limparCampos.getActionMap().put("F1", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                campoTitulo.setText("");
                campoDesc.setText("");
                dataCampo.setText("");
                comboPrioridade.setSelectedIndex(0);
                if(comboCategoria.getItemCount() > 0) comboCategoria.setSelectedIndex(0);
                listaTags.clearSelection();
            }
        });
        limparCampos.addActionListener(e -> limparCampos.getActionMap().get("F1").actionPerformed(e));

        salvar.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0), "F2");
        salvar.getActionMap().put("F2", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tit = campoTitulo.getText().trim();
                String desc = campoDesc.getText().trim();
                String data = dataCampo.getText().trim();
                String prio = (String) comboPrioridade.getSelectedItem();
                
                if(tit.isEmpty() || comboCategoria.getSelectedIndex() == -1) {
                    JOptionPane.showMessageDialog(null, "Preencha o título e a categoria!");
                    return;
                }
                
                int idCat = listaCategoriasBanco.get(comboCategoria.getSelectedIndex()).getid();
                
               String sqlIns = "INSERT INTO tarefas (titulo, descricao, prioridade, data_entrega, status, usuario_id, id_categoria) VALUES (?, ?, ?, ?, ?, ?, ?)";
                int idTarefaInserida = 0;
                
                try (Connection conn = conexaoBanco.obterConexao();
                     PreparedStatement stmt = conn.prepareStatement(sqlIns, PreparedStatement.RETURN_GENERATED_KEYS)) {
                    
                    stmt.setString(1, tit);
                    stmt.setString(2, desc);
                    stmt.setString(3, prio);
                    stmt.setString(4, data);
                    stmt.setString(5, "Pendente");
                    stmt.setInt(6, idUsuarioLogado);
                    stmt.setInt(7, idCat);
                    
                    stmt.executeUpdate();
                    
                    try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            idTarefaInserida = generatedKeys.getInt(1);
                        }
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao salvar tarefa: " + ex.getMessage());
                    return;
                }
                
                List<String> tagsSelecionadas = listaTags.getSelectedValuesList();
                tarefas_tagsDAO ttDAO = new tarefas_tagsDAO();
                for(String tagNome : tagsSelecionadas) {
                    for(tags t : listaTagsBanco) {
                        if(t.getnome().equals(tagNome)) {
                            ttDAO.vincularTagATarefa(idTarefaInserida, t.getid());
                        }
                    }
                }
                
                JOptionPane.showMessageDialog(null, "Tarefa salva com sucesso!");
                atualizarDadosDoBanco();
                limparCampos.doClick();
            }
        });
        salvar.addActionListener(e -> salvar.getActionMap().get("F2").actionPerformed(e));

        btnVoltar.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0), "F3");
        btnVoltar.getActionMap().put("F3", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Login();
            }
        });
        btnVoltar.addActionListener(e -> btnVoltar.getActionMap().get("F3").actionPerformed(e));

        btnConcluir.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0), "F5");
        btnConcluir.getActionMap().put("F5", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = tabela.getSelectedRow();
                if (row == -1) {
                    JOptionPane.showMessageDialog(null, "Selecione uma tarefa na tabela para concluir!");
                    return;
                }
                int idTarefa = listaIdsTarefasTabela.get(row);
                String sqlUpd = "UPDATE tarefas SET status = ? WHERE id_tarefa = ?";
                try (Connection conn = conexaoBanco.obterConexao();
                     PreparedStatement stmt = conn.prepareStatement(sqlUpd)) {
                    stmt.setString(1, "Concluído");
                    stmt.setInt(2, idTarefa);
                    stmt.executeUpdate();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao concluir tarefa: " + ex.getMessage());
                }
                atualizarDadosDoBanco();
            }
        });
        btnConcluir.addActionListener(e -> btnConcluir.getActionMap().get("F5").actionPerformed(e));

        btnExcluir.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F6, 0), "F6");
        btnExcluir.getActionMap().put("F6", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = tabela.getSelectedRow();
                if (row == -1) {
                    JOptionPane.showMessageDialog(null, "Selecione uma tarefa na tabela para excluir!");
                    return;
                }
                int idTarefa = listaIdsTarefasTabela.get(row);
                String sqlDel = "DELETE FROM tarefas WHERE id_tarefa = ?";
                try (Connection conn = conexaoBanco.obterConexao();
                     PreparedStatement stmt = conn.prepareStatement(sqlDel)) {
                    stmt.setInt(1, idTarefa);
                    stmt.executeUpdate();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao excluir tarefa: " + ex.getMessage());
                }
                atualizarDadosDoBanco();
            }
        });
        btnExcluir.addActionListener(e -> btnExcluir.getActionMap().get("F6").actionPerformed(e));

        campoPesquisa.setFocusAccelerator('7');

        btnHist.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F8, 0), "F8");
        btnHist.getActionMap().put("F8", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new historico(idUsuarioLogado);
            }
        });
        btnHist.addActionListener(e -> btnHist.getActionMap().get("F8").actionPerformed(e));

        add(abas);
        setVisible(true);
    }

    private void atualizarDadosDoBanco() {
        modelCategorias.removeAllElements();
        modelTags.clear();
        modeloTabela.setRowCount(0);
        listaIdsTarefasTabela.clear();
        
        categoriasDAO cDAO = new categoriasDAO();
        listaCategoriasBanco = cDAO.listarTodas();
        for (categorias c : listaCategoriasBanco) {
            modelCategorias.addElement(c.getnome());
        }
        
        tagsDAO tDAO = new tagsDAO();
        listaTagsBanco = tDAO.listarTodas();
        for (tags t : listaTagsBanco) {
            modelTags.addElement(t.getnome());
        }

       String sqlSel = "SELECT t.id_tarefa, t.titulo, t.prioridade, t.data_entrega, t.status, c.nome AS nome_categoria " +
                "FROM tarefas t " +
                "LEFT JOIN categorias c ON t.id_categoria = c.id " + // <--- AQUI ESTÁ A CORREÇÃO
                "WHERE t.usuario_id = ?";
                        
        try (Connection conn = conexaoBanco.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sqlSel)) {
            
            stmt.setInt(1, idUsuarioLogado);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    listaIdsTarefasTabela.add(rs.getInt("id_tarefa"));
                    
                    modeloTabela.addRow(new Object[]{
                        rs.getString("titulo"),
                        rs.getString("nome_categoria") != null ? rs.getString("nome_categoria") : "Sem categoria", 
                        "Tags",
                        rs.getString("prioridade"),
                        rs.getString("data_entrega"),
                        rs.getString("status")
                    });
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao carregar tabela de tarefas: " + e.getMessage());
        }
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(() -> new cadastro(1));
    }
}