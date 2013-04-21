package cu.cs.cpsc215.crazy_mail.ui;

import javax.swing.JPanel;

/**
 * Interface for the visual states in the program.
 * 
 * @author Kevin
 *
 */
public interface FrameState{
	public void onHide();
	public void onShow();
	public String getName();
	public JPanel getPanel();
}
