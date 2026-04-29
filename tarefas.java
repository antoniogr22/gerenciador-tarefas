import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class tarefas extends JFrame {

    public tarefas() {
        setTitle("Gerenciador de Tarefas");
        setSize(700, 600); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
    
        JPanel painelpai = new JPanel(new GridBagLayout()); 
        painelpai.setBackground(Color.DARK_GRAY);

    
        JPanel painelCentral = new JPanel(new GridBagLayout());
        painelCentral.setPreferredSize(new Dimension(500, 400));
        painelCentral.setBackground(Color.LIGHT_GRAY);
        painelCentral.setBorder(new EmptyBorder(20, 20, 20, 20));

        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); 
        gbc.fill = GridBagConstraints.HORIZONTAL;

        
        gbc.gridx = 0; gbc.gridy = 0;
        painelCentral.add(new JLabel("Título da Tarefa:"), gbc);
        
        gbc.gridx = 1; 
        JTextField txtTitulo = new JTextField(20);
        painelCentral.add(txtTitulo, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        painelCentral.add(new JLabel("Data de Entrega (dd/mm/aaaa):"), gbc);
        
        gbc.gridx = 1;
        JTextField txtData = new JTextField(10);
        painelCentral.add(txtData, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        painelCentral.add(new JLabel("Prioridade:"), gbc);
        
        gbc.gridx = 1;
        String[] prioridades = {"Baixa", "Média", "Alta"};
        JComboBox<String> cbPrioridade = new JComboBox<>(prioridades);
        painelCentral.add(cbPrioridade, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        painelCentral.add(new JLabel("Descrição:"), gbc);
        
        gbc.gridx = 1;
        JTextArea txtDescricao = new JTextArea(4, 20);
        txtDescricao.setLineWrap(true);
        JScrollPane scrollDesc = new JScrollPane(txtDescricao); 
        painelCentral.add(scrollDesc, gbc);

        
        gbc.gridx = 0; gbc.gridy = 4;
        gbc.gridwidth = 2; 
        JButton btnSalvar = new JButton("Salvar Tarefa");
        btnSalvar.setBackground(new Color(46, 204, 113)); 
        btnSalvar.setForeground(Color.BLACK);
        painelCentral.add(btnSalvar, gbc);

        // Adicionar ao JFrame
        add(painelpai);
        painelpai.add(painelCentral);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new tarefas());
    }
}
