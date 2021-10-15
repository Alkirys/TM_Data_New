package tmDataNew;

import java.io.IOException;

public class Main {

	public static void main(String[] args) 
			throws IOException, java.lang.NullPointerException {
		ReadTMI readTMI = new ReadTMI("0828793_1907.actpa-06.c43.tmivk");
		readTMI.load(); 

	}

}

