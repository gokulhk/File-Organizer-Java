import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.IOException;	

class DriverClass{

	private void driverMethod(File rootPath, PrintWriter mergeTracker, int groupingCode, int subFolderCode) throws IOException{

		//Do subFolder action first always
		if(subFolderCode != 2){

			SubFolderAction sfa = new SubFolderAction();

			if( subFolderCode == 0 ){ //Merge files to root folder and organize

				//Pulls all files to root Folder 
				sfa.mergeSubFolder( rootPath, rootPath, mergeTracker, true); //true to indicate DepthOne

				//call Grouping Handler to group files as selected
				FileGrouper fg = new FileGrouper();
				fg.groupingHandler(rootPath, mergeTracker, groupingCode); 

			}
			else if( subFolderCode == 1 ){ //Organize files in their respective folders with selected grouping type

				sfa.organizeWithOutMerge( rootPath, rootPath, mergeTracker, groupingCode );
			}
		}
		else{ //Grouping type

			FileGrouper fg = new FileGrouper();
			fg.groupingHandler(rootPath, mergeTracker, groupingCode); 
		}


	}



	public static void main(String[] args) { //Getting Input
		
		int groupingCode = Integer.parseInt(args[0]); //Grouping Code
		int subFolderCode = Integer.parseInt(args[1]);// default subfolder code = 2 no action option as default
		File rootPath = new File(args[2]); //path of the directory to be organized
		File mergeLogger = new File(args[3]); // path of the file to log all the file movements for revert
		PrintWriter mergeTracker = null;
		DriverClass dc = new DriverClass();

		try{
			FileWriter fw = new FileWriter(mergeLogger, true);
			BufferedWriter bw = new BufferedWriter(fw);
			mergeTracker = new PrintWriter(bw);

			dc.driverMethod(rootPath, mergeTracker, groupingCode, subFolderCode);

		}
		catch(IOException ioe){
			System.out.println("IOException occurred");
			ioe.printStackTrace();
		}
		finally{
			//Closing Open Resources
			if(mergeTracker != null)
				mergeTracker.close();
		}

	}
}