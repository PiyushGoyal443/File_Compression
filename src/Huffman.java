import java.io.IOException;


public class Huffman {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//Show what command line arguments to use
		if (args.length == 0) {
			System.err.println("Usage: java HuffmanDecompress InputFile OutputFile");
			System.exit(1);
			return;
		}
		
		if (args[0] == "-h") {
			System.out.print("\nCompresing .....");
			HuffmanCompress hc = new HuffmanCompress();
			hc.compress(args[1], 0);
			System.out.print("\nCompresing Done");
		}
		else if (args[0] == "-i") {
			System.out.print("\nDecompresing .....");
			HuffmanDecompress hd = new HuffmanDecompress();
			hd.decompress(args[1], 0);
			System.out.print("\nDecompresing Done");
		}
		else if (args[0] == "-m") {
			System.out.print("\nCompresing .....");
			Encode ed = new Encode();
			ed.encode(args[1]);
			System.out.print("\nCompresing Done");
		}
		else if (args[0] == "-n") {
			System.out.print("\nDecompresing .....");
			Decode dd = new Decode();
			dd.decode(args[1]);
			System.out.print("\nDecompresing Done");
		}
	}

}
