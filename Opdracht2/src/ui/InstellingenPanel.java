package ui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import db.PropertiesLoaderSaver;
import domain.tellers.TellerType;

import javax.swing.JSeparator;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

public class InstellingenPanel extends JPanel{
	private JTextField teller0WaardesTextField;
	private JTextField teller1WaardesTextField;
	private JTextField teller2WaardesTextField;
	private JTextField teller3WaardesTextField;
	private JTextField teller4WaardesTextField;
	JComboBox teller0TypeComboBox;
	JComboBox teller1TypeComboBox;
	JComboBox teller2TypeComboBox;
	JComboBox teller3TypeComboBox;
	JComboBox teller4TypeComboBox;
	
	private JTextField codeTextField;
	
	public InstellingenPanel() {
		setLayout(null);
		List<TellerType> types = PropertiesLoaderSaver.getTellerTypes();
		List<Character[]> waardes = PropertiesLoaderSaver.getTellerWaardes();
		String code = PropertiesLoaderSaver.getCode();
		
		JLabel teller0Label = new JLabel("Teller 1");
		teller0Label.setBounds(10, 11, 46, 14);
		add(teller0Label);
		
		JLabel teller0TypeLabel = new JLabel("TellerType");
		teller0TypeLabel.setBounds(20, 36, 75, 14);
		add(teller0TypeLabel);
		
		JLabel teller0WaardesLabel = new JLabel("TellerWaardes");
		teller0WaardesLabel.setBounds(20, 61, 75, 14);
		add(teller0WaardesLabel);
		
		teller0TypeComboBox = new JComboBox(TellerType.values());
		if(types.size()>=1){
			if(types.get(0)==TellerType.CYCLISCH){
				teller0TypeComboBox.setSelectedIndex(0);
			}else{
				teller0TypeComboBox.setSelectedIndex(1);
			}
		}else{
			teller0TypeComboBox.setEnabled(false);
			teller0WaardesTextField.setEnabled(false);
		}
		teller0TypeComboBox.setBounds(105, 33, 86, 20);
		add(teller0TypeComboBox);
		
		teller0WaardesTextField = new JTextField();
		teller0WaardesTextField.setBounds(105, 58, 86, 20);
		teller0WaardesTextField.setColumns(10);
		teller0WaardesTextField.setText(waardeToString(waardes.get(0)));
		add(teller0WaardesTextField);
		
		
		JLabel teller1Label = new JLabel("Teller 2");
		teller1Label.setBounds(227, 11, 46, 14);
		add(teller1Label);
		
		JLabel teller1TypeLabel = new JLabel("TellerType");
		teller1TypeLabel.setBounds(237, 36, 75, 14);
		add(teller1TypeLabel);
		
		JLabel teller1WaardesLabel = new JLabel("TellerWaardes");
		teller1WaardesLabel.setBounds(237, 61, 75, 14);
		add(teller1WaardesLabel);
		
		teller1TypeComboBox = new JComboBox(TellerType.values());
		if(types.size()>=2){
			if(types.get(0)==TellerType.CYCLISCH){
				teller1TypeComboBox.setSelectedIndex(0);
			}else{
				teller1TypeComboBox.setSelectedIndex(1);
			}
		}else{
			teller1TypeComboBox.setEnabled(false);
			teller1WaardesTextField.setEnabled(false);
		}
		teller1TypeComboBox.setBounds(322, 33, 86, 20);
		add(teller1TypeComboBox);
		
		teller1WaardesTextField = new JTextField();
		teller1WaardesTextField.setBounds(322, 58, 86, 20);
		teller1WaardesTextField.setColumns(10);
		teller1WaardesTextField.setText(waardeToString(waardes.get(1)));
		add(teller1WaardesTextField);
		
		
		JLabel teller2Label = new JLabel("Teller 3");
		teller2Label.setBounds(10, 86, 46, 14);
		add(teller2Label);
		
		JLabel teller2TypeLabel = new JLabel("TellerType");
		teller2TypeLabel.setBounds(20, 111, 75, 14);
		add(teller2TypeLabel);
		
		JLabel teller2WaardesLabel = new JLabel("TellerWaardes");
		teller2WaardesLabel.setBounds(20, 136, 75, 14);
		add(teller2WaardesLabel);
		
		teller2TypeComboBox = new JComboBox(TellerType.values());
		if(types.size()>=3){
			if(types.get(0)==TellerType.CYCLISCH){
				teller2TypeComboBox.setSelectedIndex(0);
			}else{
				teller2TypeComboBox.setSelectedIndex(1);
			}
		}else{
			teller2TypeComboBox.setEnabled(false);
			teller2WaardesTextField.setEnabled(false);
		}
		teller2TypeComboBox.setBounds(105, 108, 86, 20);
		add(teller2TypeComboBox);
		
		teller2WaardesTextField = new JTextField();
		teller2WaardesTextField.setBounds(105, 133, 86, 20);
		teller2WaardesTextField.setColumns(10);
		teller2WaardesTextField.setText(waardeToString(waardes.get(2)));
		add(teller2WaardesTextField);
		
		
		JLabel teller3Label = new JLabel("Teller 4");
		teller3Label.setBounds(227, 86, 46, 14);
		add(teller3Label);
		
		JLabel teller3TypeLabel = new JLabel("TellerType");
		teller3TypeLabel.setBounds(237, 111, 75, 14);
		add(teller3TypeLabel);
		
		JLabel teller3WaardesLabel = new JLabel("TellerWaardes");
		teller3WaardesLabel.setBounds(237, 136, 75, 14);
		add(teller3WaardesLabel);
		
		teller3TypeComboBox = new JComboBox(TellerType.values());
		if(types.size()>=4){
			if(types.get(0)==TellerType.CYCLISCH){
				teller3TypeComboBox.setSelectedIndex(0);
			}else{
				teller3TypeComboBox.setSelectedIndex(1);
			}
		}else{
			teller3TypeComboBox.setEnabled(false);
			teller3WaardesTextField.setEnabled(false);
		}
		teller3TypeComboBox.setBounds(322, 108, 86, 20);
		add(teller3TypeComboBox);
		
		teller3WaardesTextField = new JTextField();
		teller3WaardesTextField.setBounds(322, 133, 86, 20);
		teller3WaardesTextField.setColumns(10);
		teller3WaardesTextField.setText(waardeToString(waardes.get(3)));
		add(teller3WaardesTextField);
		
		
		JLabel teller4Label = new JLabel("Teller 5");
		teller4Label.setBounds(10, 167, 46, 14);
		add(teller4Label);
		
		JLabel teller4TypeLabel = new JLabel("TellerType");
		teller4TypeLabel.setBounds(20, 192, 75, 14);
		add(teller4TypeLabel);
		
		JLabel teller4WaardesLabel = new JLabel("TellerWaardes");
		teller4WaardesLabel.setBounds(20, 217, 75, 14);
		add(teller4WaardesLabel);
		
		teller4TypeComboBox = new JComboBox(TellerType.values());
		if(types.size()>=5){
			if(types.get(0)==TellerType.CYCLISCH){
				teller4TypeComboBox.setSelectedIndex(0);
			}else{
				teller4TypeComboBox.setSelectedIndex(1);
			}
		}else{
			teller4TypeComboBox.setEnabled(false);
			teller4WaardesTextField.setEnabled(false);
		}
		teller4TypeComboBox.setBounds(105, 189, 86, 20);
		add(teller4TypeComboBox);
		
		teller4WaardesTextField = new JTextField();
		teller4WaardesTextField.setBounds(105, 214, 86, 20);
		teller4WaardesTextField.setColumns(10);
		teller4WaardesTextField.setText(waardeToString(waardes.get(4)));
		add(teller4WaardesTextField);
		
		
		JLabel codeInstlingenLabel = new JLabel("Code Instellingen");
		codeInstlingenLabel.setBounds(227, 167, 97, 14);
		add(codeInstlingenLabel);
		
		JLabel codeLabel = new JLabel("Code");
		codeLabel.setBounds(237, 186, 75, 14);
		add(codeLabel);
		
		codeTextField = new JTextField();
		codeTextField.setColumns(10);
		codeTextField.setBounds(322, 183, 86, 20);
		codeTextField.setText(code);
		add(codeTextField);
		
		JButton cancelButton = new JButton("cancel");
		cancelButton.setBounds(319, 245, 89, 23);
		add(cancelButton);
		
		JButton okButton = new JButton("ok");
		okButton.setBounds(223, 245, 89, 23);
		add(okButton);
	}

	public void setOkButtonListener(ActionListener aL){
		
	}
	
	public void setCancelButtonListener(ActionListener aL){
		
	}
	
	public List<TellerType> getTellerTypes(){
		List<TellerType> tellerTypes = new ArrayList<TellerType>();
		
		tellerTypes.add((TellerType)teller0TypeComboBox.getSelectedItem());
		tellerTypes.add((TellerType)teller1TypeComboBox.getSelectedItem());
		tellerTypes.add((TellerType)teller2TypeComboBox.getSelectedItem());
		tellerTypes.add((TellerType)teller3TypeComboBox.getSelectedItem());
		tellerTypes.add((TellerType)teller4TypeComboBox.getSelectedItem());
		
		return tellerTypes;
	}
	
	public List<Character[]> getTellerWaardes(){
		List<Character[]> waardes = new ArrayList<Character[]>();
		
		List<String> waardesStrList = new ArrayList<String>();
		waardesStrList.add(teller0WaardesTextField.getText());
		waardesStrList.add(teller1WaardesTextField.getText());
		waardesStrList.add(teller2WaardesTextField.getText());
		waardesStrList.add(teller3WaardesTextField.getText());
		waardesStrList.add(teller4WaardesTextField.getText());
		
		for(String str : waardesStrList){
			Character[] characters= new Character[str.length()];
			
			int i=0;
			
			for(Character c : str.toCharArray()){
				characters[i]=c;
				i++;
			}
		}
		
		
		
		return waardes;
	}

	public String getCode(){
		return codeTextField.getText();
	}
	
	private String waardeToString(Character[] waardes){
		String temp="";
		
		for(Character c : waardes){
			temp+=c.toString();
		}
		
		return temp;
	}
}
