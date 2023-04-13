package _06_Intro_To_Hash_Maps;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class _02_LogSearch implements ActionListener {
	/*
	 * Crate a HashMap of Integers for the keys and Strings for the values. Create a
	 * GUI with three buttons. Button 1: Add Entry When this button is clicked, use
	 * an input dialog to ask the user to enter an ID number. After an ID is
	 * entered, use another input dialog to ask the user to enter a name. Add this
	 * information as a new entry to your HashMap.
	 * 
	 * Button 2: Search by ID When this button is clicked, use an input dialog to
	 * ask the user to enter an ID number. If that ID exists, display that name to
	 * the user. Otherwise, tell the user that that entry does not exist.
	 * 
	 * Button 3: View List When this button is clicked, display the entire list in a
	 * message dialog in the following format: ID: 123 Name: Harry Howard ID: 245
	 * Name: Polly Powers ID: 433 Name: Oliver Ortega etc...
	 * 
	 * When this is complete, add a fourth button to your window. Button 4: Remove
	 * Entry When this button is clicked, prompt the user to enter an ID using an
	 * input dialog. If this ID exists in the HashMap, remove it. Otherwise, notify
	 * the user that the ID is not in the list.
	 */

	HashMap<Integer, String> logs = new HashMap<Integer, String>();
	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	JButton addEntry = new JButton("Add Entry");
	JButton searchByID = new JButton("Search By ID");
	JButton viewList = new JButton("View List");

	public void showWindow() {
		panel.add(addEntry);
		panel.add(searchByID);
		panel.add(viewList);
		frame.add(panel);
		frame.pack();
		frame.setTitle("Log Search");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addEntry.addActionListener(this);
		searchByID.addActionListener(this);
		viewList.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton buttonPressed = (JButton) e.getSource();

		if (buttonPressed.equals(addEntry)) {
			String idString = JOptionPane.showInputDialog("Enter the ID number");
			String name = JOptionPane.showInputDialog("Enter the name");
			int id = Integer.parseInt(idString);
			logs.put(id, name);
			JOptionPane.showMessageDialog(null, "Entry added!");
		} else if (buttonPressed.equals(searchByID)) {
			String idString = JOptionPane.showInputDialog("Enter the ID number");
			int id = Integer.parseInt(idString);
			if (logs.get(id) != null) {
				JOptionPane.showMessageDialog(null, "ID: " + id + "Name: " + logs.get(id));
			} else {
				JOptionPane.showMessageDialog(null, "ID not found");
			}
		} else if (buttonPressed.equals(viewList)) {
			String list = "";
			for (int i = 0; i < logs.size(); i++) {
				if (logs.get(i) != null) {
					list += "ID: " + i + " NAME: " + logs.get(i) + "\n";
				}
			}
			if (list.equals("")) {
				JOptionPane.showMessageDialog(null, "No IDs in log :(");
			} else {
				JOptionPane.showMessageDialog(null, list);
			}
		}

	}

}
