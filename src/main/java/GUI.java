import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JComboBox;


class GUI extends JFrame{
    JPanel panel=new JPanel();
    JLabel instruction=new JLabel("Please Paste a Reddit URL Below");
    JTextField textEntry=new JTextField("",45);

    JLabel saveLocation=new JLabel("Save location:");
    String choices[] = {
            "Downloads",
            "Desktop",
            "Documents",
            "Pictures",
            "Root",
    };

    JComboBox choicesButton = new JComboBox(choices);
    JButton beginButton=new JButton("Begin Image Scrape");
    JButton restartButton = new JButton("Clear");
    JButton exit = new JButton("Exit");

    public static void main(String[] args){
        new GUI();
    }

    public GUI(){
        super("Reddit Image Scraper");
        setSize(625,600);
        setResizable(true);
        panel.add(instruction);
        panel.add(textEntry);
        panel.add(saveLocation);
        panel.add(choicesButton);
        panel.add(beginButton);
        panel.add(restartButton);
        panel.add(exit);
        add(panel);
        setVisible(true);
    }
}
