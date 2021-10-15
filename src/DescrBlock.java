package tmDataNew;

import java.io.*;

public class DescrBlock {
	byte type;
	byte countByteFieldConstantPart;
	byte countBitFieldConstantPart;
	byte countBitFieldVariablePart;
	byte countByteFieldVariablePart;
	DescrField[] massDF;
	int summDF;
	byte[] buff;
	DescrBlock(byte[] b) {
		buff=b;
		//System.out.println("buff=" + buff[2]);
		type=buff[0];
		countByteFieldConstantPart=buff[1];
		countBitFieldConstantPart=buff[2];
		countBitFieldVariablePart=buff[3];
		countByteFieldVariablePart=buff[4];
		summDF=buff[1]&0xff;
		summDF+= buff[2]&0xff;
		summDF+= buff[3]&0xff;
		summDF+= buff[4]&0xff;		
		massDF=new DescrField[summDF];
		//System.out.println(summDF);
		//System.out.println(massDF.length);
	}
	public void load() {
		int lenField, indBuff=5, tmpIndBuff, iMassDF;
		iMassDF=0;
		//indBuff=4;
		try {
			int summBuf = summDF;
		while(summBuf-->0) {
			lenField=buff[indBuff]&0xff;// + buff[indBuff+1]&0xff + 
				//	buff[indBuff+2]&0xff + buff[indBuff+3]&0xff;
			tmpIndBuff=indBuff+=4;	
			if(buff[indBuff+1]==0) {	// constant len
				DF_ConstantLen DF_CL = new DF_ConstantLen();
			//	DF_CL.typeField=buff[indBuff];
				//DF_CL.typeLen=buff[indBuff+1];
				massDF[iMassDF]=DF_CL;
				indBuff+=2;
				DF_CL.len=buff[indBuff]&0xff;
				DF_CL.len+= buff[indBuff+1]&0xff;
				//+ 
					//buff[indBuff+2]&0xff + buff[indBuff+3]&0xff + 
			//		buff[indBuff+4]&0xff + buff[indBuff+5]&0xff +
				//	buff[indBuff+6]&0xff + buff[indBuff+7]&0xff;
				indBuff+=8;
			}
			else {
				DF_VariableLen DF_VL = new DF_VariableLen();
				DF_VL.view=buff[indBuff+2];
				DF_VL.countVew=buff[indBuff+3];
				massDF[iMassDF]= DF_VL;
				indBuff+=4;
			}
			massDF[iMassDF].typeField=buff[tmpIndBuff];
			massDF[iMassDF].typeLen=buff[tmpIndBuff+1];
			iMassDF++;
				
		}
		Print();
		}
		catch(Exception e) {
			
		}
	}

	public void Print()
	{
		System.out.println("type - " + type);
		System.out.println("CountByteFCP - " + countByteFieldConstantPart);
		System.out.println("CountBitFCP - " + countBitFieldConstantPart);
		System.out.println("CountBitFVP - " + countBitFieldVariablePart);
		System.out.println("CountByteFVP - " + countByteFieldVariablePart);

		System.out.println("SUMMDF - " + summDF);

		for (int i = 0; i < summDF; i++)
		{
			massDF[i].Print();
		}
	}

}
