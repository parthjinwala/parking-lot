package app;

import java.io.File;
import java.util.Scanner;
import utils.InstructionHandler;

public class ParkingLotApplication {

	public static void main(String[] args) {
		InstructionHandler iHandler = new InstructionHandler();
		
		//shell command mode		
		if (args.length == 0) {
			Scanner scanner = new Scanner(System.in);
			while(true) {
				String[] instruction = scanner.nextLine().split(" ");
				
				if(instruction[0].compareTo("exit") == 0)
					break;
				
				iHandler.executeInstruction(instruction);
			}
			scanner.close();
		}
		else {
			//Read instructions from file
			String fileName = args[0];
			try {
				File instructionFile = new File(fileName);
				Scanner scanner = new Scanner(instructionFile);
				while(scanner.hasNextLine()) {
					String[] instruction = scanner.nextLine().split(" ");
					iHandler.executeInstruction(instruction);
				}
				scanner.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
			
	}

}
