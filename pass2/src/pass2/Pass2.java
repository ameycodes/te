/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pass2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class Pass2 {
	BufferedReader interReader = null;
	BufferedReader symbolReader = null;
	BufferedWriter writer = null;
	
	public Pass2() throws IOException {
		interReader = new BufferedReader(new FileReader("C:\\Users\\Amey Gondhalekar\\Documents\\NetBeansProjects\\pass2\\src\\pass2\\inter.txt"));
		symbolReader = new BufferedReader(new FileReader("C:\\Users\\Amey Gondhalekar\\Documents\\NetBeansProjects\\pass2\\src\\pass2\\symbol.txt"));
		writer = new BufferedWriter(new FileWriter("C:\\Users\\Amey Gondhalekar\\Documents\\NetBeansProjects\\pass2\\src\\pass2\\output.txt"));
	}
	public void writeLine(String code) throws IOException {
		//System.out.println("code");
		//System.out.println(code);
		//System.out.println();
		writer.write(code+"\n");
	}
	public STable parseSymbolTable()throws IOException {
		String line;
		STable st = new STable();
		while((line = symbolReader.readLine()) != null) {
			String parts[] = line.split("\\s+|\n");
			int index = Integer.parseInt(parts[0]);
			String symbol = "";
			int address = Integer.parseInt(parts[1]);
			st.addSymbol(index, symbol, address);
		}
		return st;
	}
	
	public void generateMachineCode() throws IOException {
		String line;
		String fline[] = interReader.readLine().split("\\s+|\n");
		System.out.println("parts");
		for (String string : fline) {
			System.out.println(string);
		}
		//int lc = Integer.parseInt(fline[3]);
		
		STable st = parseSymbolTable();
		
		while((line = interReader.readLine()) != null) {
			String code="";
			String parts[] = line.split("\\s+|\n");
			if (parts.length > 2) {
				String lc = parts[0];
				if (parts[1].equals("IS")) {
					code = lc + " " + parts[2] + " " + parts[3] + " " + st.getAddress(Integer.parseInt(parts[5]));
					writeLine(code);
				} else if (parts[1].equals("DL")) {
					code = lc + " " + "0" + " "+ "0" + " " + parts[4];
					writeLine(code);
				}
			}
		}
		st.printTable();
		interReader.close();
		symbolReader.close();
		writer.close();
	}
	
	public static void main(String[] args) throws IOException {
		Pass2 p = new Pass2();
		p.generateMachineCode();		
	}
}
class Row {	// represents row of symbol table
	String symbol;
	int address;
	public Row(String symbol,int address) {
		this.symbol = symbol;
		this.address = address;
	}
	public int getAddress() {
		return address;
	}
	public void setAddress(int address) {
		this.address = address;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
}
class STable {
	HashMap<Integer, Row> symbolTable = new HashMap<Integer, Row>();
	
	public void addSymbol(int index,String symbol,int address) {
		Row row = new Row(symbol, address);
		symbolTable.put(index, row);
	}
	
	public int getAddress(int index) {
		return symbolTable.get(index).getAddress();
	}
	public void printTable() {
		System.out.println("Symbol Table");
		for (int index : symbolTable.keySet()) {
			System.out.println(index+" "+symbolTable.get(index).getSymbol()+ " " + symbolTable.get(index).getAddress());
		}
	}
}