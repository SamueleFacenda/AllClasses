package all.quinta.udp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Termostato {
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JList<String> list1;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JPanel Pannello;
    private JButton startServerButton;
    private JScrollPane scroll;
    private final Display controller;
    private final JTextField[] textFields = {textField1, textField2, textField3, textField4, textField5, textField6};

    private DefaultListModel<String> listModel;

    public Termostato(){
        listModel = new DefaultListModel<>();
        list1.setModel(listModel);
        controller = new Display(60000, this::printCommand);
        for(int i = 0; i < textFields.length; i++){
            textFields[i].setEditable(false);
            if(i>2){
                textFields[i].setText(Display.NOME_TEMPERATURA.get(i-3));
            }else{
                textFields[i].setText(String.valueOf(controller.getTemperatura(i)));
            }
        }
        startServerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //disable the button
                startServerButton.setEnabled(false);
                new Thread(controller::serve).start();
            }
        });
    }

    private void printCommand(String in){
        System.out.println(in);
        listModel.addElement(in);
        list1.ensureIndexIsVisible(listModel.getSize()-1);
        //update the textfields
        for(int i = 0; i < Display.NOME_TEMPERATURA.size(); i++){
            textFields[i].setText(String.valueOf(controller.getTemperatura(i)));
        }
        // scroll down with the scroll jscrollpane
        scroll.getVerticalScrollBar().setValue(scroll.getVerticalScrollBar().getMaximum());
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Termostato");
        frame.setContentPane(new Termostato().Pannello);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
