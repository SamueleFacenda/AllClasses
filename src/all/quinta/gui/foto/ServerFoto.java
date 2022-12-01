package all.quinta.gui.foto;

import javax.swing.*;

public class ServerFoto {
    private JPanel panel1;
    private JButton startButton;
    private JList listaImg;
    private JList listaCmd;

    //singleton
    private static ServerFoto instance = null;
    public static ServerFoto getInstance(){
        if(instance == null)
            instance = new ServerFoto();
        return instance;
    }

    public static void main(String[] args) {
        new ServerFoto().launch();
    }

    public void launch(){
        JFrame frame = new JFrame("ServerFoto");
        frame.setContentPane(new ServerFoto().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
