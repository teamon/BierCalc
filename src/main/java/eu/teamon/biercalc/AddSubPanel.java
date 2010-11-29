package eu.teamon.biercalc;

import java.awt.Dimension;
import java.awt.LayoutManager;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;


public class AddSubPanel extends JPanel {
	private JTextField a_tf;
	private JTextField b_tf;
	private JTextField base_tf;
	private JLabel operator_lbl;
	private JLabel a_int_lbl;
	private JLabel b_int_lbl;
	private JLabel c_int_lbl;
	private JLabel c_lbl;
	
	public AddSubPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{29, 67, 186, 19, 103, 0};
		gridBagLayout.rowHeights = new int[]{28, 28, 28, 30, 29, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel base_lbl = new JLabel("Podstawa:");
		GridBagConstraints gbc_base_lbl = new GridBagConstraints();
		gbc_base_lbl.anchor = GridBagConstraints.WEST;
		gbc_base_lbl.insets = new Insets(0, 0, 5, 5);
		gbc_base_lbl.gridx = 1;
		gbc_base_lbl.gridy = 0;
		add(base_lbl, gbc_base_lbl);
		
		base_tf = new JTextField();
		base_tf.setText("2");
		base_tf.setFont(new Font("Menlo", Font.PLAIN, 13));
		base_tf.setColumns(10);
		GridBagConstraints gbc_base_tf = new GridBagConstraints();
		gbc_base_tf.anchor = GridBagConstraints.WEST;
		gbc_base_tf.insets = new Insets(0, 0, 5, 5);
		gbc_base_tf.gridx = 2;
		gbc_base_tf.gridy = 0;
		add(base_tf, gbc_base_tf);
		
		a_tf = new JTextField();
		a_tf.setHorizontalAlignment(SwingConstants.RIGHT);
		a_tf.setFont(new Font("Menlo", Font.PLAIN, 13));
		GridBagConstraints gbc_a_tf = new GridBagConstraints();
		gbc_a_tf.fill = GridBagConstraints.HORIZONTAL;
		gbc_a_tf.insets = new Insets(0, 0, 5, 5);
		gbc_a_tf.gridwidth = 2;
		gbc_a_tf.gridx = 1;
		gbc_a_tf.gridy = 1;
		add(a_tf, gbc_a_tf);
		a_tf.setColumns(10);
		
		JLabel label = new JLabel("=");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.fill = GridBagConstraints.HORIZONTAL;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 3;
		gbc_label.gridy = 1;
		add(label, gbc_label);
		
		a_int_lbl = new JLabel("");
		a_int_lbl.setHorizontalAlignment(SwingConstants.RIGHT);
		a_int_lbl.setFont(new Font("Menlo", Font.PLAIN, 13));
		GridBagConstraints gbc_a_int_lbl = new GridBagConstraints();
		gbc_a_int_lbl.fill = GridBagConstraints.BOTH;
		gbc_a_int_lbl.insets = new Insets(0, 0, 5, 0);
		gbc_a_int_lbl.gridx = 4;
		gbc_a_int_lbl.gridy = 1;
		add(a_int_lbl, gbc_a_int_lbl);
		
		operator_lbl = new JLabel("+");
		GridBagConstraints gbc_operator_lbl = new GridBagConstraints();
		gbc_operator_lbl.anchor = GridBagConstraints.EAST;
		gbc_operator_lbl.insets = new Insets(0, 0, 5, 5);
		gbc_operator_lbl.gridx = 0;
		gbc_operator_lbl.gridy = 2;
		add(operator_lbl, gbc_operator_lbl);
		
		b_tf = new JTextField();
		b_tf.setHorizontalAlignment(SwingConstants.RIGHT);
		b_tf.setFont(new Font("Menlo", Font.PLAIN, 13));
		b_tf.setColumns(10);
		GridBagConstraints gbc_b_tf = new GridBagConstraints();
		gbc_b_tf.fill = GridBagConstraints.HORIZONTAL;
		gbc_b_tf.insets = new Insets(0, 0, 5, 5);
		gbc_b_tf.gridwidth = 2;
		gbc_b_tf.gridx = 1;
		gbc_b_tf.gridy = 2;
		add(b_tf, gbc_b_tf);
		
		JLabel label_1 = new JLabel("=");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 3;
		gbc_label_1.gridy = 2;
		add(label_1, gbc_label_1);
		
		b_int_lbl = new JLabel("");
		b_int_lbl.setHorizontalAlignment(SwingConstants.RIGHT);
		b_int_lbl.setFont(new Font("Menlo", Font.PLAIN, 13));
		GridBagConstraints gbc_b_int_lbl = new GridBagConstraints();
		gbc_b_int_lbl.fill = GridBagConstraints.BOTH;
		gbc_b_int_lbl.insets = new Insets(0, 0, 5, 0);
		gbc_b_int_lbl.gridx = 4;
		gbc_b_int_lbl.gridy = 2;
		add(b_int_lbl, gbc_b_int_lbl);
		
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.anchor = GridBagConstraints.NORTH;
		gbc_separator.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator.insets = new Insets(0, 0, 5, 0);
		gbc_separator.gridwidth = 5;
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 3;
		add(separator, gbc_separator);
		
		c_lbl = new JLabel("");
		c_lbl.setFont(new Font("Menlo", Font.PLAIN, 13));
		c_lbl.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_c_lbl = new GridBagConstraints();
		gbc_c_lbl.fill = GridBagConstraints.BOTH;
		gbc_c_lbl.insets = new Insets(0, 0, 5, 5);
		gbc_c_lbl.gridwidth = 2;
		gbc_c_lbl.gridx = 1;
		gbc_c_lbl.gridy = 3;
		add(c_lbl, gbc_c_lbl);
		
		c_int_lbl = new JLabel("");
		c_int_lbl.setHorizontalAlignment(SwingConstants.RIGHT);
		c_int_lbl.setFont(new Font("Menlo", Font.PLAIN, 13));
		GridBagConstraints gbc_c_int_lbl = new GridBagConstraints();
		gbc_c_int_lbl.fill = GridBagConstraints.BOTH;
		gbc_c_int_lbl.insets = new Insets(0, 0, 5, 0);
		gbc_c_int_lbl.gridx = 4;
		gbc_c_int_lbl.gridy = 3;
		add(c_int_lbl, gbc_c_int_lbl);
		
		JButton sub_btn = new JButton("Odejmij");
		sub_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			    try {
                    int base = Integer.parseInt(base_tf.getText());
                    BierInt a = new BierInt(base, a_tf.getText());
                    BierInt b = new BierInt(base, b_tf.getText());
                    display('-', a, b, a.subtract(b));
                } catch (BierIntException e){
                    System.err.println("Error: " + e.getMessage());
                }
			}
		});
		
		JButton add_btn = new JButton(" Dodaj ");
		add_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			    try {
                    int base = Integer.parseInt(base_tf.getText());
                    BierInt a = new BierInt(base, a_tf.getText());
                    BierInt b = new BierInt(base, b_tf.getText());
                    display('+', a, b, a.add(b));
                } catch (BierIntException e){
                    System.err.println("Error: " + e.getMessage());
                }
			}
		});
		GridBagConstraints gbc_add_btn = new GridBagConstraints();
		gbc_add_btn.anchor = GridBagConstraints.NORTHWEST;
		gbc_add_btn.insets = new Insets(0, 0, 0, 5);
		gbc_add_btn.gridx = 2;
		gbc_add_btn.gridy = 4;
		add(add_btn, gbc_add_btn);
		GridBagConstraints gbc_sub_btn = new GridBagConstraints();
		gbc_sub_btn.anchor = GridBagConstraints.NORTHEAST;
		gbc_sub_btn.insets = new Insets(0, 0, 0, 5);
		gbc_sub_btn.gridx = 2;
		gbc_sub_btn.gridy = 4;
		add(sub_btn, gbc_sub_btn);
		
		//setPrefferedSize(new Dimension(460, 200));
	}
	
	public void display(char operator, BierInt a, BierInt b, BierInt c){
	    c_lbl.setText(c.toString() + " ");
	    a_int_lbl.setText(Integer.toString(a.toInt()));
	    b_int_lbl.setText(Integer.toString(b.toInt()));
	    c_int_lbl.setText(Integer.toString(c.toInt()));
	    operator_lbl.setText(Character.toString(operator));
	}
}
