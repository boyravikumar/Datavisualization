package com.gov.charts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class CSVParser {
	public HashMap<String, ArrayList<String>> parse(String inFile) {
		HashMap<String, ArrayList<String>> csvData = new HashMap<String, ArrayList<String>>();

		BufferedReader br = null;

		try {
			br = new BufferedReader(new FileReader(new File(inFile)));

			String header = br.readLine();

			String[] headings = header.split(",");
			int i;
			int headingsCount = headings.length;
			for (i = 0; i < headingsCount; i++) {
				headings[i] = trimDoubleQuotes(headings[i]);
				csvData.put(headings[i], new ArrayList<String>());
			}

			String dataRow = br.readLine();
			String datas[];
			String data = "";

			while (dataRow != null) {
				datas = dataRow.split(",");
				for (i = 0; i < headingsCount; i++) {
					if (i < datas.length) {
						data = trimDoubleQuotes(datas[i]);
						csvData.get(headings[i]).add(data);
					}
				}
				dataRow = br.readLine();

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return csvData;
	}

	private String trimDoubleQuotes(String in) {
		String out = null;
		if (in != null) {
			out = in;

			if (out.startsWith("\""))
				out = out.substring(1);

			if (out.endsWith("\""))
				out = out.substring(0, out.length() - 1);
		}

		return out;
	}

}
