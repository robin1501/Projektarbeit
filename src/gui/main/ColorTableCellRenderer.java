package gui.main;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

class ColorTableCellRenderer extends DefaultTableCellRenderer {

	/**
	 * 
	 */
	@Override
    public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
				row, column);
		
		if (column == 1 && Double.parseDouble(value.toString()) > 4.0) {
			setForeground(Color.RED);
		} else {
			setForeground(Color.BLACK);
		}

		return this;
	}
}