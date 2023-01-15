import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.Buffer;

public class TextEditor implements ActionListener {
    JFrame frame;
    JMenuBar menuBar;
    JMenu file, edit;
    JMenuItem newFile, saveFile, openFile;
    JMenuItem cut, copy, paste, selectAll, close;
    JTextArea textArea;
    TextEditor(){
        // Initialize frame
       frame = new JFrame();
       // Initialize text area;
        textArea = new JTextArea();
       // Initialize menu bar
        menuBar = new JMenuBar();
        // Initialize menus
        file = new JMenu("File");
        edit = new JMenu("Edit");
        // Add menu to menu bar
        menuBar.add(file);
        menuBar.add(edit);
        // Initialize menu items
        //  File menu items
        newFile = new JMenuItem("New File");
        saveFile = new JMenuItem("Save File");
        openFile = new JMenuItem("Open File");
        // Edit menu items
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close Window");
        // Adding menu items to file menu
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);
        // Adding menu items to edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);
        // add ActionListner to file menu items
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);
        // add ActionListner to edit menu items
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);
        // set menu bar to our frame
        frame.setJMenuBar(menuBar);
        //Add text area to our frame
        frame.add(textArea);
        // Create content Pane
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));
        //Add text Area to panel
        panel.add(textArea, BorderLayout.CENTER);
        //Add panel to the frame
         frame.add(panel);
       // create scroll pane
       JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
       // Add Scroll pane to the panel
        panel.add(scrollPane);
       // set dimensions of frame
       frame.setBounds(100,100,800,500);
       // set frame to be visible
       frame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent){
        // If cut menu item is clicked
        if(actionEvent.getSource()==cut){
           textArea.cut();
        }
        // If copy menu item is clicked
        if(actionEvent.getSource()==copy){
           textArea.copy();
        }
        // If paste menu item is clicked
        if(actionEvent.getSource()==paste){
            textArea.paste();
        }
        // If selectAll menu item is clicked
        if(actionEvent.getSource()==selectAll){
            textArea.selectAll();
        }
        // If close menu item is clicked
        if(actionEvent.getSource()==close){
            System.exit(0);
        }
        // If new file menu item is clicked
        if(actionEvent.getSource()==newFile){
            TextEditor newWindow = new TextEditor();
        }
        // If open file menu item is clicked
        if(actionEvent.getSource()==openFile){
            // Initialize a file chooser
            JFileChooser fileChooser = new JFileChooser("c");
            // Getting chosen option in file chooser
            int chooseOption  = fileChooser.showOpenDialog(null);
            //If file choosen option is the approve option
            if(chooseOption==JFileChooser.APPROVE_OPTION){
                //Getting selected file from file chooser
                File file = fileChooser.getSelectedFile();
                // Getting path of the selected file
                String filePath = file.getPath();
               try{
                   FileReader fileReader = new FileReader(filePath);
                   BufferedReader bufferedReader = new BufferedReader(fileReader);
                   String intermediate = "", output = "";
                   while((intermediate = bufferedReader.readLine())!=null){
                       output+=intermediate+"\n";
                   }
                   bufferedReader.close();
                   textArea.setText(output);
                }
                catch (Exception exception){
                    exception.printStackTrace();
                }
            }
        }
        // If save file menu item is clicked
        if(actionEvent.getSource()==saveFile){
            //Intialize file chooser
            JFileChooser fileChooser = new JFileChooser("C");
            // Get choosen option from file chooser
            int chooseOption = fileChooser.showSaveDialog(null);
            //If choosen option is the approve option
            if(chooseOption==JFileChooser.APPROVE_OPTION){
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                try{
                    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                }
                catch (Exception exception){
                    exception.printStackTrace();
                }
            }
        }
    }
    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor();
    }
}