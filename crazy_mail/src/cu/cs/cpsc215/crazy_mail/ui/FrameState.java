package cu.cs.cpsc215.crazy_mail.ui;

import javax.swing.JPanel;

/**
 * Interface for the visual states in the program.
 * 
 * @author Kevin
 *
 */
public interface FrameState{
	public void onHide(); //Performed when the state is switched out
	public void onShow(); //Performed when the state is switched in
	public String getName(); //Identifier in the state map. Needs to be unique for every state
	public JPanel getPanel(); //Gets the main state panel
}
