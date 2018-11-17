package chapter17;

import java.io.*;

public class Copy {

	public static void main(String[] args) throws IOException{
		if(args.length != 2) {
			System.err.println("Usage: java Copy sourceFile targetFile");
			System.exit(1);
		}
		
		File sourceFile = new File(args[0]);
		if(!sourceFile.exists()) {
			System.err.println("Source file " + args[0] + " does not exist");
			System.exit(2);
		}
		
		File targetFile = new File(args[1]);
		if(!targetFile.exists()) {
			System.err.println("Target file " + args[1] + " does not exist");
			System.exit(3);
		}
		
		try(
			BufferedInputStream input = new BufferedInputStream(new FileInputStream(sourceFile));
			BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(targetFile));
		){
			int r, numberOfBytesCopied = 0;
			while((r = input.read()) != -1) {
				output.write(r);
				numberOfBytesCopied++;
			}
			
			System.out.println(numberOfBytesCopied + " bytes copied");
		}
	}

}
