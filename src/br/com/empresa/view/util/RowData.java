package br.com.empresa.view.util;

import java.util.HashMap;
import java.util.Map;

public class RowData {

	private Map<Integer, Object> values = new HashMap<Integer, Object>();
	private Object element;

	public Object getValueForCol(int columnIndex) {
		if (values.containsKey(columnIndex)) {
			return values.get(columnIndex);
		}
		return "";
	}

	public void setValueForCol(Object aValue, int columnIndex) {
		values.put(columnIndex, aValue);
	}

	public Map<Integer, Object> getValues() {
		return values;
	}

	public void setValues(Map<Integer, Object> values) {
		this.values = values;
	}

	public Object getElement() {
		return element;
	}

	public void setElement(Object element) {
		this.element = element;
	}

}
