package tmDataNew;
//import java.util.TreeSet;
import java.io.*;
//import java.util.Map;
import java.util.TreeMap;

public class ReadTMI {
	FileInputStream f_input;
	public TreeMap<Byte, DescrBlock> arrDB = new TreeMap<Byte, DescrBlock>();
	ReadTMI(String inputStream) throws FileNotFoundException{ // throws ParserConfigurationException, SAXException, IOException {
		f_input = new FileInputStream(inputStream);
	}
	public void load()  throws IOException {
		byte [] buff = new byte[90000];	
		int numByte=0, lenBlock;
		byte type;
		try {			
			f_input.read(buff,0,10);
			numByte+=10;
			
			while(true) {
				
				f_input.read(buff,0,1);
				type=buff[0];
				if(type==1) {

					f_input.read(buff,0,4);
					lenBlock=buff[0]&0xff + buff[1]&0xff + buff[2]&0xff + buff[3]&0xff;
					f_input.read(buff,0,lenBlock);
					numByte+=5;
					numByte+=lenBlock;
					System.out.println("numByte=" + numByte);
					DescrBlock dBlock=new DescrBlock(buff);
					dBlock.load();
					arrDB.put(dBlock.type, dBlock);
					
				}
				else {
					Block block=new Block(arrDB.get(type), f_input);
					block.load();

				}
				if(numByte>400)
					break;
			}
			f_input.close();					
		}
		catch(IOException ex) {
			System.out.println("Error!!!");
		}	
	}
}
