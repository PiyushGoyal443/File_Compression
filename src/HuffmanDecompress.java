import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


// Decompresses an input file that was compressed with HuffmanCompress, to an output file.
public class HuffmanDecompress {
	
	public void decompress(String ifname, int compressionType) throws IOException {

		// decompress
		File inputFile = new File(ifname);
		String ofname;
		
		if (compressionType == 0) {
			ofname = ifname.replaceAll(".huf", "");
		}
		else {
			ofname = "temp.txt";
		}
		
		File outputFile = new File(ofname);
		
		outputFile.createNewFile();
		boolean bool = outputFile.exists();
		
		if (bool) {
			outputFile.delete();
			outputFile.createNewFile();
		}
		
		BitInputStream in = new BitInputStream(new BufferedInputStream(new FileInputStream(inputFile)));
		OutputStream out = new BufferedOutputStream(new FileOutputStream(outputFile));
		
		try {
			CanonicalCode canonCode = readCode(in);
			CodeTree code = canonCode.toCodeTree();
			decompress(code, in, out);
		} finally {
			out.close();
			in.close();
		}
	}
	
	
	static CanonicalCode readCode(BitInputStream in) throws IOException {
		int[] codeLengths = new int[257];
		for (int i = 0; i < codeLengths.length; i++) {
			// For this file format, we read 8 bits in big endian
			int val = 0;
			for (int j = 0; j < 8; j++) 
				val = val << 1 | in.readNoEof();
			codeLengths[i] = val;
		}
		return new CanonicalCode(codeLengths);
	}
	
	
	static void decompress(CodeTree code, BitInputStream in, OutputStream out) throws IOException {
		HuffmanDecoder dec = new HuffmanDecoder(in);
		dec.codeTree = code;
		while (true) {
			int symbol = dec.read();
			if (symbol == 256)  // EOF symbol
				break;
			out.write(symbol);
		}
	}
	
}
