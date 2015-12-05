package hospitalsystem;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JApplet;
import javax.swing.JLabel;
import javax.swing.JTable;

public class CustomRenderer extends JApplet {

		JLabel label;
		int targetRow, targetCol;

		public CustomRenderer()
		{
			label = new JLabel();
			label.setHorizontalAlignment(JLabel.CENTER);
			label.setOpaque(true);
			targetRow = -1;
			targetCol = -1;
		}

		public Component getTableCellRendererComponent(JTable table,
				Object value,
				boolean isSelected,
				boolean hasFocus,
				int row, int column)
		{
			if(isSelected)
			{
				label.setBackground(table.getSelectionBackground());
				label.setForeground(table.getSelectionForeground());
			}
			else
			{
				label.setBackground(table.getBackground());
				label.setForeground(table.getForeground());
			}
			if(row == targetRow && column == targetCol)
			{
				label.setBorder(BorderFactory.createLineBorder(Color.red));
				label.setFont(table.getFont().deriveFont(Font.BOLD));
			}
			else
			{
				label.setBorder(null);
				label.setFont(table.getFont());
			}
			label.setText((String)value);
			return label;
		}

		public void setTargetCell(int row, int col)
		{
			targetRow = row;
			targetCol = col;
		}
	}

