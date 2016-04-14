# File_Compression

### Huffman code and move-to-front transform for text file compression in Java.
Opens file and then compresses / decompresses it.<br><br>
For huffman Code extension is .huf<br>
For huffman and move-to-front extension is .m2f
<br>
<br>

```
java yourCode -h Hamlet.txt     // Do Huffman code
java yourCode -i Hamlet.txt.huf // Reverse Huffman code

java yourCode -m Hamlet.txt:    // Do move-to-front and Huffman code
java yourCode -n Hamlet.txt.m2f // Reverse Huffman code and move-to-front
```

###Compressed File Format
>
The first 256 bytes are the decode[] table<br>
The rest is the compressed data

