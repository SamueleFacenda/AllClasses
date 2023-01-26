package all.quinta.udp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientTermostato {
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JList<String> list1;
    private JButton submitValuesButton;
    private JButton retriveValuesButton;
    private JPanel Pannello;
    private JScrollPane scroll;
    private final JTextField[] textFields = {textField1, textField2, textField3, textField4, textField5, textField6};

    private DefaultListModel<String> listModel;
    private ComandoExploded controller;

    public ClientTermostato(){
        listModel = new DefaultListModel<>();
        list1.setModel(listModel);
        controller = new ComandoExploded(60001 + (int) (Math.random() * 100), "localhost", 60000, this::printCommand);
        for(int i = 0; i < textFields.length; i+=2){
            textFields[i].setEditable(false);
            textFields[i].setText(Display.NOME_TEMPERATURA.get(i/2));
        }
        submitValuesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    int[] values = new int[3];
                    for(int i = 0; i < values.length; i++){
                        values[i] = Integer.parseInt(textFields[i*2+1].getText());
                    }
                    String out = controller.updateTemperature(values);
                    printCommand(out);
            }
        });
        retriveValuesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] values = controller.readValues();
                for(int i = 0; i < values.length; i++){
                    textFields[i*2+1].setText(String.valueOf(values[i]));
                }
            }
        });
    }

    private void printCommand(String in){
        System.out.println(in);
        listModel.addElement(in);
        list1.ensureIndexIsVisible(listModel.getSize()-1);
        // scroll down with the scroll jscrollpane
        scroll.getVerticalScrollBar().setValue(scroll.getVerticalScrollBar().getMaximum());
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Client Termostato");
        frame.setContentPane(new ClientTermostato().Pannello);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
