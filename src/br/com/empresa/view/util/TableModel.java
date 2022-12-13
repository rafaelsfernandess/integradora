package br.com.empresa.view.util;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel {

	int colIndex = 0;
	private List<String> cols = new ArrayList<String>();
	private List<RowData> rows = new ArrayList<RowData>();

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return true;
	}

	@Override
	public String getColumnName(int column) {
		return cols.get(column).toString();
	}

	@Override
	public int getRowCount() {
		return rows.size();
	}

	public void addRow() {
		rows.add(new RowData());
		fireTableRowsInserted(0, rows.size());
	}

	public void addRow(RowData rowData) {
		rows.add(rowData);
		fireTableRowsInserted(0, rows.size());
	}

	public void removeRow(int selectedRow) {
		rows.remove(selectedRow);
		fireTableRowsDeleted(selectedRow, selectedRow);
	}

	public void removeColumn(int selectedColumn, JTable table) {
		cols.remove(table.convertColumnIndexToModel(selectedColumn));
		fireTableStructureChanged();
	}

	public void addColumn(String nameColumn) {
		cols.add(nameColumn);
		fireTableStructureChanged();
	}

	public void clearTable() {
		rows.clear();
		this.fireTableDataChanged();
	}

	@Override
	public int getColumnCount() {
		return cols.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		RowData rowData = rows.get(rowIndex);
		return rowData.getValueForCol(columnIndex);
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		RowData rowData = rows.get(rowIndex);
		rowData.setValueForCol(aValue, columnIndex);
		fireTableCellUpdated(rowIndex, columnIndex);
	}

	public List<String> getCols() {
		return cols;
	}

	public void setCols(List<String> cols) {
		this.cols = cols;
	}

	public List<RowData> getRows() {
		return rows;
	}

	public void setRows(List<RowData> rows) {
		this.rows = rows;
	}

}
