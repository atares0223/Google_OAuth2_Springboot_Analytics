package com.lff.reporting.util;

import java.util.List;

import com.google.common.collect.Lists;

import lombok.Data;

@Data
public class ConsoleTable {
	private List<ConsoleRow> rows;
	private ConsoleRow header;
	private List<ConsoleRow> allRows;
	private static String SPACE_STR = "                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           ";
	public ConsoleTable(List<ConsoleRow> rows, ConsoleRow header) {
		super();
		this.rows = rows;
		this.header = header;
	}

	public List<ConsoleRow> getRows() {
		return rows;
	}

	public void setRows(List<ConsoleRow> rows) {
		this.rows = rows;
	}

	public ConsoleRow getHeader() {
		return header;
	}

	public void setHeader(ConsoleRow header) {
		this.header = header;
	}

	public void console(){
		format();
		for(ConsoleRow row: allRows){
			for(String cell : row.cells)
				System.out.print(cell+"  ");
			System.out.println();
		}
	}
	private void format(){
		allRows = Lists.newArrayList(header);
		allRows.addAll(rows);
		for(int i =0 ;i<header.cells.size();i++){
			int maxLength = 0;
			for(ConsoleRow row: allRows){
				maxLength = Math.max(maxLength, row.getCells().get(i).length());
			}
			for(ConsoleRow row: allRows){
				int length = row.getCells().get(i).length();
				int sub = maxLength-length;
				if(sub>0){
					row.getCells().set(i, SPACE_STR.substring(0, sub/2)+row.getCells().get(i)+SPACE_STR.substring(0, sub%2==0?sub/2:sub/2+1));
				}
			}
		}
	}

	public static class ConsoleRow{
		private List<String> cells;

		public ConsoleRow(List<String> cells) {
			super();
			this.cells = cells;
		}

		public List<String> getCells() {
			return cells;
		}
		
		
	}
}
