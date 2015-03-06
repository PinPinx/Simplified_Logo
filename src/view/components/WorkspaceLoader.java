package view.components;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class WorkspaceLoader {
	private WorkspaceFile myWorkspace;
	
	public WorkspaceLoader(){
		myWorkspace = null;
	}
	
	public WorkspaceLoader(WorkspaceFile wsp){
		myWorkspace = wsp;
	}
	
	public void saveWorkspace(WorkspaceFile wsp, String title) throws IOException{
		myWorkspace = wsp;
		JFrame parentFrame = new JFrame();
        File myFile = new File(title+".wsp");
        JFileChooser fileChooser = new JFileChooser(System.getProperties().getProperty("user.dir")+"/src/workspace");
        fileChooser.setDialogTitle("Specify a file to save");
        fileChooser.setSelectedFile(myFile);
        int userSelection = fileChooser.showSaveDialog(parentFrame);
        
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            FileOutputStream fout = new FileOutputStream(fileToSave);
    		ObjectOutputStream oos = new ObjectOutputStream(fout); 
    		oos.writeObject(this.myWorkspace);
    		oos.close();
        }
	}
	
	public void loadWorkspace() throws IOException, ClassNotFoundException{
		JFileChooser imageChooser = new JFileChooser(System.getProperties().getProperty("user.dir")+"/src/workspace");
		imageChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int retval = imageChooser.showOpenDialog(null);
        if (retval != JFileChooser.APPROVE_OPTION) {
            return;
        }
		FileInputStream fin = new FileInputStream(imageChooser.getSelectedFile());
        ObjectInputStream ois = new ObjectInputStream(fin);
        myWorkspace = (WorkspaceFile) ois.readObject();
        myWorkspace.setTitle(imageChooser.getSelectedFile().getName());
        return;
	}
	
	public WorkspaceFile getWorkspace(){
		return myWorkspace;
	}
	

}
