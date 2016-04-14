//Vincent Tran
//CS 1501
//Will perform Move to Front transform on ASCII characters
//Format: java Decode inputfilename.extension
//Tested files: .bin

import java.lang.*;
import java.util.*;
import java.io.*;

public class Decode
{
	public void decode(String ifilename) throws IOException
	{
		
		HuffmanDecompress hd = new HuffmanDecompress();
		hd.decompress(ifilename, 1);
		
		ArrayList<Integer> data = new ArrayList<Integer>();
		int[] dict = new int[256];
		for(int i = 0; i < 256; i++)
			dict[i] = i;
				
		//Will read each character from the input file and store into an ArrayList
		Scanner br = new Scanner(new File("temp.txt"));
		while(br.hasNext())
		{
			data.add(br.nextInt());
		}

		int index=0;
		String output = "";
		//Performs the Move-To-Front transform on the ArrayList
		for(int dataVal : data)
		{
			char b = (char)dict[dataVal];
			for(int check = dataVal; check != 0; check--)
			{
				dict[check] = dict[check-1];
			}
			dict[0] = b;
			output = output+ b;
		}
		
		new File("temp.txt").delete();
		Writer fileOut = new BufferedWriter(new FileWriter(ifilename.replace(".txt.m2f", ".txt")));
		fileOut.write(output);
		fileOut.close();
		
		//Prints out the decoded message onto the console
		//System.out.println(output);
	}
}