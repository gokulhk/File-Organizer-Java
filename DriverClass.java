import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.IOException;	

class DriverClass{

	public void driverMethod(File rootPath, int groupingCode, int subFolderCode) throws IOException{

		//Do subFolder action first always
		if(subFolderCode != 2){

			SubFolderAction sfa = new SubFolderAction();

			if( subFolderCode == 0 ){ //Merge files to root folder and organize

				//Pulls all files to root Folder 
				sfa.mergeSubFolder( rootPath, rootPath, true); //true to indicate DepthOne

				//call Grouping Handler to group files as selected
				FileGrouper fg = new FileGrouper();
				fg.groupingHandler(rootPath, groupingCode); 

			}
			else if( subFolderCode == 1 ){ //Organize files in their respective folders with selected grouping type

				sfa.organizeWithOutMerge( rootPath, rootPath, groupingCode );
			}
		}
		else{ //Grouping type

			FileGrouper fg = new FileGrouper();
			fg.groupingHandler(rootPath, groupingCode); 
		}


	}
	
}