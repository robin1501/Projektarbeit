package gui.main;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

class ColorTableCellRenderer extends DefaultTableCellRenderer{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected,
                hasFocus, row, column);
        
        Color color = Color.red;         
        setForeground( color );
        
        return this;
    }
}