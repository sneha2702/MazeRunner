/***
 * Group member names: Gopala Sai Uppalapati (Z1840615),Sneha Konatham (Z1838982), Anusha Chanduri(Z1840609)
 * Assignment-3
 * CSCI 502
 *****/
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
//MazeApp class containing main method
public class MazeApp extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    //declaring MazeApp variables
    //mazePanel object from MazePanel class
    MazePanel mazePanel = new MazePanel();
    //openMazeFile button
    private JButton openMazefile=new JButton("Open Maze File");
    //solveMaze button
    private JButton solveMaze=new JButton("Solve Maze");
    //clearSolution button
    private JButton clearSolution=new JButton("Clear Solution");

    private MazeApp(String title){
        super(title);
    }
    //crateAndShowGUI method which calla initcomponrnt method
    private void createAndShowGUI(){
        initComponents();
        openMazefile.addActionListener(this);
        solveMaze.addActionListener(this);
        clearSolution.addActionListener(this);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
    //initcomponent method to initialize jframe components
    private void initComponents(){
        JPanel mainPanel=new JPanel(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(600,600));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(Color.LIGHT_GRAY);
        mainPanel.add(mazePanel, BorderLayout.CENTER);
        JPanel flowLayout=new JPanel();
        flowLayout.setLayout(new FlowLayout());
        flowLayout.add(openMazefile);
        flowLayout.add(solveMaze);
        solveMaze.setEnabled(false);
        flowLayout.add(clearSolution);
        clearSolution.setEnabled(false);
        mainPanel.add(flowLayout,BorderLayout.SOUTH);
        add(mainPanel,BorderLayout.CENTER);
    }
    //actionPerformrd method called when button is clicked
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==openMazefile){
            JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
            j.setFileFilter(filter);
            // invoke the showsOpenDialog function to show the save dialog
            int r = j.showOpenDialog(null);

            // if the user selects a file
            if (r == JFileChooser.APPROVE_OPTION)
            {
                File inputFile=j.getSelectedFile();
                mazePanel.readMaze(inputFile);
                solveMaze.setEnabled(true);
            }
        }
        else if (ae.getSource() == solveMaze){
            mazePanel.solveMaze();
            solveMaze.setEnabled(false);
            clearSolution.setEnabled(true);
        }
        else if (ae.getSource() == clearSolution){
            mazePanel.clearMazePath();
            solveMaze.setEnabled(true);
            clearSolution.setEnabled(false);
        }

    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException
                | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        EventQueue.invokeLater(() -> {
            MazeApp mainFrame=new MazeApp("Maze");
            mainFrame.createAndShowGUI();
            //TipApp mainFrame = new TipApp("Tip Calculator");
            //mainFrame.createAndShowGUI();
        });

    }
}
