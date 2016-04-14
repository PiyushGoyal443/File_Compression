//Vincent Tran
//CS 1501
//Will perform Move to Front transform on ASCII characters
//Format: java Encode inputfilename.extension outputfilename.extension
//Tested files: .bin

import java.lang.*;
import java.util.*;
import java.io.*;

public class Encode
{
	public void encode(String ifilename) throws IOException
	{
		ArrayList<Character> data = new ArrayList<Character>();
		int[] dict = new int[128];
		for(int i = 0; i < 128; i++)
			dict[i] = i;
				
		String current;
		//Will read each character from the input file and store into an ArrayList
		InputStream input = new BufferedInputStream(new FileInputStream(ifilename));
		try {
			while (true) {
				int b = input.read();
				if (b == -1)
					break;
				data.add((char)b);
			}
		} finally {
			input.close();
		}
	
		int index=0;
		int charVal;
		String output = "";
		
		//Performs the Move-To-Front transform on the ArrayList
		for(char x : data)
		{
			charVal = (int) x;
			for(int i = 0; i < 128; i++)
			{
				if(dict[i] == charVal)
					index = i;
			}
			
			for(int check = index; check != 0; check--)
				dict[check] = dict[check-1];
				
			dict[0] = charVal;
			output = output + index + " ";
		}
		
		//Writes into a file with the user-defined name and extension
		File file = new File("temp.txt");
		Writer fileOut = new BufferedWriter(new FileWriter(file));
		fileOut.write(output);
		fileOut.close();
		HuffmanCompress hc = new HuffmanCompress();
		hc.compress("temp.txt",1);
		new File("temp.txt.m2f").renameTo(new File(ifilename + ".m2f"));
	}
}