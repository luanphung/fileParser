import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;





public class parser {

	public static void main(String[] args) {
		String input ="";
		String directory ="";
		String temp;
		String output ="";
		doubleInput[]names;
		String fileName;
		boolean success = false;
		//temporary variables for testing.
		//input = "C:\\Users\\lphung\\Desktop\\input.txt";
		//directory = "C:\\Users\\lphung\\Desktop\\scripts";
		
		//get string input from the user to take the paths of the input and the scripts folder
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter the file path including the input file: ");
		temp = sc.nextLine(); 
		
		for(int i = 0; i  < temp.length(); i++){ //conversion from / to \\ since it cannot be read in java for both files
			char c = temp.charAt(i);
			if( c =='/'){
				input += "\\";
			}else{
				input += c;
			}
		}
		
		System.out.println("Please now enter the folder directory in which you want to find the inputs: ");
		temp = sc.nextLine();
		
		
		for(int i = 0; i  < temp.length(); i++){
			char c = temp.charAt(i);
			if( c =='/'){
				directory += "\\";
			}else{
				directory += c;
			}
		}
		
		System.out.println("Please now enter the name of the ouput file(the extension will automatically be .csv): ");
		fileName = sc.nextLine();

		
		System.out.println("Please now enter the folder directory in which you want to find the ouput: ");
		temp = sc.nextLine();
		
		for(int i = 0; i  < temp.length(); i++){
			char c = temp.charAt(i);
			if( c =='/'){
				output += "\\";
			}else{
				output += c;
			}
		}
		sc.close();
		
		try {
			List<String> lines = Files.readAllLines(Paths.get(input), StandardCharsets.UTF_8);
			//System.out.println(lines);
			String[] classCreator;
			names = new doubleInput[lines.size()];
			for (int i = 0; i < lines.size(); i++){
				classCreator = lines.get(i).split(",");
				names[i] = new doubleInput(classCreator[0],classCreator[1]);
				System.out.println(classCreator[0] +": "+classCreator[1]);
			}
		
		
			File folder = new File(directory); //create a new file list and list all the files withing an array
			File[] listOfFiles = folder.listFiles(); //get a list of all the files from the folder

		    
			for(int i = 0; i < listOfFiles.length; i++){
		    	
				try{
					for (int z =0; z < names.length; z++){
											
						BufferedReader reader = new BufferedReader(new FileReader(directory+"\\"+listOfFiles[i].getName())); //get the file 
						String lineForTable; 
						boolean contains = false;
						while((lineForTable = reader.readLine())!= null){
							if(lineForTable.contains(names[z].getTable())){
								contains = true;
							}
						}
						if(contains){
							String line; 
							int lineNum = 1; //the file start with line 1
							BufferedReader readerColumn = new BufferedReader(new FileReader(directory+"\\"+listOfFiles[i].getName())); //get the file 
							while ((line = readerColumn.readLine()) != null){
								lineNum++;
								if(line.contains(names[z].getName())){ //compare if the string we are searching for exists
									names[z].add(listOfFiles[i].getName(), lineNum);
								}
							}readerColumn.close();
						}reader.close();

					}		

					
				}catch (Exception e){
					System.err.format("Exception occurred trying to read '%s'.", "C:\\Users\\lphung\\Desktop\\input.txt");
					e.printStackTrace();
				}
			}
			
		//create a new file and write in it
			
			
			
			for (int x = 0; x < names[0].isFoundin.size();x++){
			System.out.println(names[0].getInfo(x));
			}
			
			
			File file = new File(output +"\\"+fileName+".csv");
			PrintWriter writer = new PrintWriter(file);
			writer.println("Tables, Searched Terms"+","+"File with the Search Term"+","+"Line Number");
			
			
			for(int i =0;i < names.length; i++){
				if(names[i].isFoundin.size() > 0){
					for(int x = 0; x < names[i].isFoundin.size();x++){
						String[] parts = names[i].getInfo(x).split(";");
						if(x ==0){
							writer.println(names[i].getTable()+ ","+ names[i].getName()+","+parts[0]+","+parts[1]);
							//System.out.println(parts[0]+","+parts[1]+","+parts[2]);
						}else{
							writer.println(","+","+parts[0]+","+parts[1]);
							//System.out.println(""+","+parts[1]+","+parts[2]);
						}
					}
				}else{
					writer.println(names[i].getTable()+","+names[i].getName());
				}
			}
			writer.close();
			success = true;
			
		}catch (AccessDeniedException a){
			System.out.println("One of your paths has a mistake in it. Please restart the program.");
			//a.printStackTrace();
		}catch (IOException e) {
			System.out.println("IOException caught:");
			//e.printStackTrace();
		}catch (Exception i){
			System.out.println("Program failed. Please verify your paths and retry.");
			//i.printStackTrace();
		}
		
				
		if(success){
			System.out.println("Output File has been created. Here is the directory:  \n "+temp +"\\"+fileName+".csv");
		}else{
			System.out.println("Search Failed.");
		}
	}
}

