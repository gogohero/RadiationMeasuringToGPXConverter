import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;

public class Window extends JFrame {
    public String input;
    public String output;

    JButton OKButton;
    JTextArea inputArea = new JTextArea(1,50);
    JTextArea outputArea = new JTextArea(1,50);

    public Window() {

        this.setSize(600, 300);
        this.setResizable(false);
        this.setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());       // set the layout manager
        JLabel label = new JLabel("input path:");
        add(label);
        add(inputArea);
        JLabel label2 = new JLabel("output path:");
        add(label2);

        add(outputArea);
        OKButton = new JButton("OK");
        add(OKButton);
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                input = inputArea.toString();
                output = outputArea.toString();


            }
        });


    }

}
