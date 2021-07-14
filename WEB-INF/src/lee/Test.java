package lee;

import java.io.FileNotFoundException;
import java.io.IOException;

import lee.common.util.XlsAdapter;

public class Test {
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		XlsAdapter c = new XlsAdapter();
		try {
			long startTime = System.nanoTime();
			
			c.readExcel("e:\\java\\Project\\leehouse\\src\\Root\\dhx\\sample\\샘플.xls"
					,"e:\\java\\Project\\leehouse\\src\\Root\\dhx\\sample");
			
			long elapsedTime = System.nanoTime() - startTime;
			System.out.println("elapsed Time : " + elapsedTime/1000000.0 + " ms");
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
