package lezione15;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class OptionPaneIcons {

	public static void main(String[] args) {
		
		// v1: default icon
		JOptionPane.showMessageDialog(null, "Hello, World!");

//		// v2: with image
//		JOptionPane.showMessageDialog(
//				 null,
//				 "Hello, World!",
//				 "Message",
//				 JOptionPane.INFORMATION_MESSAGE,
//				 new ImageIcon("src/lezione15/ab.gif"));
//		
//		// v3: with custom graphics
//		JOptionPane.showMessageDialog(
//				 null,
//				 "Hello, World!",
//				 "Message",
//				 JOptionPane.INFORMATION_MESSAGE,
//				 new MarsIcon(100));
		
//		// v4: with custom graphics -- now with interfaces!
//		JOptionPane.showMessageDialog(
//				 null,
//				 "Hello, World!",
//				 "Message",
//				 JOptionPane.INFORMATION_MESSAGE,
//				 new DarkSideOfTheMoonIcon(100));
	}

}
