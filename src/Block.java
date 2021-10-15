package tmDataNew;
import java.io.*;
public class Block {
	DescrBlock dBlock;
	FileInputStream f_input;
	Block(DescrBlock dBlock, FileInputStream f_input) {
		this.dBlock=dBlock;
		this.f_input=f_input;
	}
	public void load() throws  java.lang.NullPointerException{
		DescrField df;
		int iMassDF=0;
		for(int i=0; i<dBlock.countByteFieldConstantPart; i++, iMassDF++) {
			if(dBlock.massDF[iMassDF].typeLen==0)
				df = new DF_ConstantLen();
			else
				df = new DF_VariableLen();
			df.load();
		}
		
	}
}
