import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;
public class GUI
{
    public static String stateChoice;
    public static String selection;
    public static String result;
    public GUI()
    {
        JFrame frame = new JFrame("Covid Data");
        frame.setLayout(new BorderLayout());
        frame.setSize(800,800);

        JPanel panel = new JPanel();
        panel.setSize(100,100);
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));


        String[] states = {"AL", "AK", "AS", "AZ", "AR", "CA", "CO", "CT", "DE", "DC", "FM", "FL", "GA", "GU", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MH", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "MP", "OH", "OK", "OR", "PW", "PA", "PR", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VI", "VA", "WA", "WV", "WI", "WY"};
        JComboBox stateList = new JComboBox(states);
        panel.add(stateList);

        String[] choices = {"Historic", "Current"};
        JComboBox choiceList = new JComboBox(choices);
        panel.add(choiceList);

        JTextArea textArea = new JTextArea(4,4);
        textArea.setEditable(false);
        textArea.setSize(100,100);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setSize(100,100);
        panel.add(scrollPane);

        frame.add(panel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        class dropDown implements ActionListener
        {
            public void actionPerformed(ActionEvent e)
            {
                stateChoice = (String) stateList.getSelectedItem();

            }
        }

        class dropDownChoice implements ActionListener
        {
            public void actionPerformed(ActionEvent e)
            {
                selection = (String) choiceList.getSelectedItem();
                methodToGetData temp = new methodToGetData();
                try {
                    result = temp.getData(stateChoice, selection);
                    System.out.print(stateChoice + "\n" + selection);
                    textArea.setText("");
                    textArea.append(result);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }

        ActionListener statePicker = new dropDown();
        ActionListener choicePicker = new dropDownChoice();
        stateList.addActionListener(statePicker);
        choiceList.addActionListener(choicePicker);
    }
}
