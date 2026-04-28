import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;

import javax.swing.*;

public class tarefas extends JFrame{

    public tarefas(){
        setTitle("tarefas");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout()); 
        setLocationRelativeTo(null);
        
        JPanel painelpai = new JPanel();
        painelpai.setBackground(Color.DARK_GRAY);

        JPanel painelCentral = new JPanel();
        painelCentral.setPreferredSize(getPreferredSize());
        painelCentral.setBackground(Color.LIGHT_GRAY);


        add(painelpai);
        painelpai.add(painelCentral, BorderLayout.CENTER);


        setVisible(true);
    }
    public static void main(String[] args) {
            new tarefas();
    }
    
}