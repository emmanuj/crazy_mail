package cu.cs.cpsc215.crazy_mail;

import cu.cs.cpsc215.crazy_mail.data.DataStore;
import cu.cs.cpsc215.crazy_mail.ui.MainFrame;

/**
 *	Main Driver. 
 *	This is separated from the window and main frame to allow for easy debugging of other components.
 **/
public class MainDriver {
	public static void main(String[] args) {
		System.out.println("Starting program");
               // DataStore ds = DataStore.initDataStore();//This will happen here so that all contacts are loaded
		
                MainFrame.init();
	}

}
