package _00_Intro_To_ArrayLists;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class _02_GuestBook implements ActionListener {
    /*
     * Create a GUI with two buttons. One button reads "Add Name" and the other
     * button reads "View Names". When the add name button is clicked, display
     * an input dialog that asks the user to enter a name. Add that name to an
     * ArrayList. When the "View Names" button is clicked, display a message
     * dialog that displays all the names added to the list. Format the list as
     * follows:
     * Guest #1: Bob Banders
     * Guest #2: Sandy Summers
     * Guest #3: Greg Ganders
     * Guest #4: Donny Doners
     */

	
	ArrayList<String> names = new ArrayList<String>();
	
	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	JButton addName = new JButton("Add Name");
	JButton viewNames = new JButton("View Names");
	JButton deleteNames = new JButton("Delete Names");
	
	public void showWindow() {
		names.add("Bob Banders");
		names.add("Sandy Summers");
		names.add("Greg Ganders");
		names.add("Donny Doners");
		
		panel.add(addName);
		panel.add(viewNames);
		panel.add(deleteNames);
		addName.addActionListener(this);
		viewNames.addActionListener(this);
		deleteNames.addActionListener(this);
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
		frame.setTitle("Guest Book");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton buttonPressed = (JButton) e.getSource();
		
		if (buttonPressed.equals(addName)) {
			String input = JOptionPane.showInputDialog("What name do you want to add?");
			names.add(input);
		} else if (buttonPressed.equals(viewNames)) {
			String nameList = "";
			for (int i = 0; i < names.size(); i++) {
				nameList += "Guest #" + (i + 1) + ": " + names.get(i) + "\n";
			}
			JOptionPane.showMessageDialog(null, nameList);
		} else {
			if (JOptionPane.showConfirmDialog(null, "Are You Sure You Want to Delete ALL Names in the Guest Book?", null, JOptionPane.YES_NO_OPTION) == 0) {
				names.removeAll(names);
			}
		}
	}
	
}
