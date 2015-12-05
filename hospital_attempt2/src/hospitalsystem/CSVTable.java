package hospitalsystem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import finalproject.Controller;
import finalproject.Patient;

/*
 * Implements actionlistener?
 * http://stackoverflow.com/questions/11353875/reading-data-from-csv-file-and-displaying-it-in-a-jtable - this contains a new class for MyModel, when you don't need?
 * http://technojeeves.com/index.php/9-freebies/67-csv-to-tablemodel - creates a table model
 * http://docs.oracle.com/javase/tutorial/uiswing/components/table.html#sorting - table sorter
 */

public class CSVTable extends JPanel {
	
	public final String datafile = "/Users/dhruv/Dropbox/uclcs/gc001_java/javacode/hospitalregistry/src/masterpatients.csv";
	public FileReader fin;
	public JScrollPane patientScrollPane;
	public JTable patientTable; 
	public Dimension patientPaneDimension;
	
	public JPanel filterPanel;
	public JPanel editPanel;
	public JTextField filterText;
	public TableRowSorter<DefaultTableModel> sorter;
	
	public JButton exportPatients;
	public JButton deletePatients;
	public JButton editPatients;
	public Controller controller;
	boolean pressingCTRL=false;//flag, if pressing CTRL it is true, otherwise it is false.
	
	private ArrayList<Patient> editPatientList;
	private ArrayList<Patient> patientList;
	private DefaultTableModel model;
	
	private Patient selectedpatient;
	int selectedRow;
	
	ArrayList<Integer> selectedCells;
	
	public JTextField tffirstName;
	public JTextField tfLastName;
	public JTextField tfDob;
	public JTextPane addressPane;
	public JTextField tfEmergencyPhone;//Should these be static?
	public JTextField tfMedicalCondition;
	public JComboBox billingBox;
	public JTextPane commentPane;
	
	
	
	/*
	 * Sets up the scrollpane
	 * http://stackoverflow.com/questions/11353875/reading-data-from-csv-file-and-displaying-it-in-a-jtable
	 * http://stackoverflow.com/questions/1420310/easy-way-to-fill-a-jtable-with-csv-data - another way to fill the default data model
	 * http://technojeeves.com/index.php/9-freebies/67-csv-to-tablemodel - this is another way to do it, and works.
	 */ 
	
	public CSVTable(DefaultTableModel m,ArrayList<Patient> patientlist){
		generateTable(m);
		generatesearchBox(m);
		generateEditButtons();
		
	}
	
	/*
	 * http://stackoverflow.com/questions/2141273/selecting-multiple-rows-of-jtable
	 * http://www.coderanch.com/t/642575/GUI/java/disable-sorting-clicking-JTable-headers
	 */
	public void generateTable(DefaultTableModel m){
		patientPaneDimension = new Dimension(200,300);
        setLayout(new BorderLayout(2,2));
        setBackground(Color.BLACK);            
        patientTable = new JTable();
        patientTable.setModel(m);
        m.fireTableDataChanged();//Not sure where this goes
        patientTable.setRowSelectionAllowed(true);
        patientTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        patientTable.getTableHeader().setEnabled(false);
        patientTable.setPreferredSize(patientPaneDimension);
        patientScrollPane = new JScrollPane(patientTable);
        patientScrollPane.setBackground(Color.WHITE);
        patientScrollPane.setOpaque(true);
        patientTable.setBackground(Color.WHITE);
        add(patientScrollPane, BorderLayout.CENTER);
        setVisible(true);
		try {
			fin = new FileReader(datafile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * http://www.javaprogrammingforums.com/java-swing-tutorials/3301-jtable-simple-solutions.html
	 * http://swingdepot.blogspot.co.uk/2010/08/text-search-in-jtable.html - very complex solution, here also http://www.coderanch.com/t/341354/GUI/java/Highlighting-JTable-cell-successful-search
	 * https://www.youtube.com/watch?v=Uq4v-bIDAIk - watch this, seems essential
	 * //http://stackoverflow.com/questions/10133366/how-to-clear-jtextfield-when-mouse-clicks-the-jtextfield
	 * //http://www.coderanch.com/t/498043/GUI/java/JTable-rowFilter-case-insensitive
	 * //https://docs.oracle.com/javase/7/docs/api/javax/swing/RowFilter.html
	 * https://www.youtube.com/watch?v=yJUQshXN_EY - this is how to add checkboxes
	 */
	public void generatesearchBox(DefaultTableModel m){	
		sorter = new TableRowSorter<DefaultTableModel>(m);
	    patientTable.setRowSorter(sorter);
	    filterText = new JTextField("Type to search for a patient");
	    filterText.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
            	filterText.setText("");
            }
        });
	    filterText.addKeyListener(new KeyListener() {
			@Override
			public void keyReleased(KeyEvent e) {
				String text = filterText.getText();
		        if (text.length() == 0) {
		          sorter.setRowFilter(null);
		        } else {
		          sorter.setRowFilter(RowFilter.regexFilter("(?i)"+text));
		        }		
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	    filterPanel = new JPanel();
	    add(filterPanel, BorderLayout.NORTH);
	    filterPanel.add(filterText);
	}
	
	public void generateEditButtons(){
		
		controller = new Controller();
		
		editPanel = new JPanel(new GridLayout(1,3));
//		model = (DefaultTableModel)patientTable.getModel();
		patientList = controller.readtoArrayList();//TODO get the arraylist from which a table is generated
		
		KeyListener tableKeyListener = new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_META){//check if user is pressing CTRL key
					pressingCTRL=true;
//					System.out.println(pressingCTRL);
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_META){//check if user released CTRL key
					pressingCTRL=false;
//					System.out.println(pressingCTRL);
				}
			}
		};

		//http://stackoverflow.com/questions/14416188/jtable-how-to-get-selected-cells
		
		MouseListener tableMouseListener = new MouseAdapter() {		
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				//				System.out.println(selectedCells.size()+"cells have been selected");
				if(pressingCTRL){//check if user is pressing CTRL key
					//					System.out.println(selectedRow);
					selectedRow = patientTable.getSelectedRow();
					//					System.out.println(selectedRow);
					selectedCells = new ArrayList<Integer>();
					if(selectedCells.contains(selectedRow)){
						//cell was already selected, deselect it
						selectedCells.remove(selectedRow);
//						System.out.println(selectedCells.size()+"cells have been selected");
						//						System.out.println(selectedCells.size());
					}else{
						//cell was not selected
						selectedCells.add(selectedRow);
						//						System.out.println(selectedCells.size());
						
					}
//					System.out.println(selectedCells.size()+"cells have been selected");
				}
				else{
					selectedRow = patientTable.getSelectedRow();
//					System.out.println(selectedCells.size()+"cells have been selected");
				}
				
			}
		};
		patientTable.addKeyListener(tableKeyListener);
		patientTable.addMouseListener(tableMouseListener);
		
		exportPatients = new JButton("Export Patients");
		exportPatients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		editPanel.add(exportPatients);
		
		deletePatients = new JButton("Delete Patients");
		deletePatients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedRow = patientTable.getSelectedRow();
				for(int i=0;i<patientList.size();i++){
					System.out.println(patientList.get(i).toString());
				}
				System.out.println(selectedRow);
				
				selectedpatient = patientList.get(patientTable.convertRowIndexToModel(selectedRow));
//				System.out.println(selectedpatient.toString());
				
				patientList.remove(selectedpatient);
				for(int i=0;i<patientList.size();i++){
					System.out.println(patientList.get(i).toString());
				}
//				model = controller.createModel(patientList);
//				generateTable(model);
				model.fireTableDataChanged();
//				patientTable.repaint();
//				
				try {
					controller.writeFile(patientList);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				 
			}
		});
		editPanel.add(deletePatients);
		
		//http://stackoverflow.com/questions/28873023/get-content-of-selected-rows-from-jtable-as-arraylist-of-objects
		
		editPatients = new JButton("Edit Patient");
		editPatients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedRow = patientTable.getSelectedRow();
				//int patientID = (int) model.getValueAt(selectedRow, 3);//JTable. convertRowIndexToModel
				selectedpatient = patientList.get(patientTable.convertRowIndexToModel(selectedRow));
				System.out.println(selectedpatient.toString());
				transferEditFields(selectedpatient);
				//System.out.println(patientID);
			}
		});
		editPanel.add(editPatients);
		
		add(editPanel, BorderLayout.SOUTH);

	}
	
	public void transferEditFields(Patient patient){
		tffirstName.setText(patient.getpFirstName());
		tfLastName.setText(patient.getpLastName());
		tfDob.setText(patient.getpDob());
		addressPane.setText(patient.getpAddress());
		tfEmergencyPhone.setText(patient.getpPhoneNumber());
		billingBox.setSelectedItem(patient.getpBillingCycle());
		commentPane.setText(patient.getpComments());
	}
	
	public Patient getUserAt(int row) {
        return patientList.get(row);
    }
	
//	public String[] getRowData(int rowIndex)
//    {
//        //test the index
//        if (rowIndex  &lgt  getRowCount() || rowIndex  <  0)
//        {
//            return null;
//        }
//        ArrayList < String &lgt  data = new ArrayList < String &lgt ();
//        for (int c = 0; c  <  getColumnCount(); c++)
//        {
//            data.add((String) getValueforCell(rowIndex, c));
//        }
//        String[] retVal = new String[data.size()];
//        for (int i = 0; i  <  retVal.length; i++)
//        {
//            retVal[i] = data.get(i);
//        }
//        return retVal;
//    }

}
		
	


