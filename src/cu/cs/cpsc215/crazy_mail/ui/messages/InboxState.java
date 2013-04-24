/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.cs.cpsc215.crazy_mail.ui.messages;

import cu.cs.cpsc215.crazy_mail.ui.FrameState;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Emmanuel
 */
public class InboxState implements FrameState {
    private JPanel main_panel;
    private DefaultListModel msglistModel = new DefaultListModel();
    private static InboxState inst;
    private InboxState(){
        main_panel = new JPanel();
        main_panel.setLayout(new BorderLayout());
        
        main_panel.add(messageListView(),BorderLayout.WEST);
        main_panel.add(createMessageViewer(), BorderLayout.CENTER);
        
    }
    
    public static InboxState get()
    {
		if(inst == null)
		{
			inst = new InboxState();
		}
		return inst;
    }
    
    public JPanel messageListView(){
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(300,700));
        
        JList messagelist = new JList(msglistModel);
        messagelist.setFixedCellHeight(60);
        
        msglistModel.addElement("Mail 1");
        msglistModel.addElement("Mail 2");
        msglistModel.addElement("Mail 3");
        msglistModel.addElement("Mail 5");
        msglistModel.addElement("Mail 6");
        msglistModel.addElement("Mail 7");
        panel.add(new JScrollPane(messagelist));
        
        
        return panel;
        
    }
    
    public JPanel createMessageViewer(){
        JPanel panel = new JPanel();
        panel.setBackground(Color.white);
        return panel;
    }
    

    @Override
    public void onHide() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onShow() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getName() {
        return "Inbox";//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JPanel getPanel() {
        return main_panel;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
