import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.IOException;



class SubFolderAction {

	/*
		=> Moves all files from all subfolder from current user entered path to top level 
			which is user entered path itself ad deleted all emptied subfolders.
		=> Then it groups files as user seleted file grouping type.
		=> Recursion works in Depth First manner.
	*/
	public void mergeSubFolder(File rootPath, File  currentDirectory, Boolean isDepthOne ) throws IOException {

		
		File[] allEntries = currentDirectory.listFiles();
		
		//If current directory empty then return as nothing to organise
		if(allEntries.length == 0) 
			return;

		for(File file : allEntries){
			if(file.isDirectory()){

				File vistingDir = file;
				mergeSubFolder( rootPath, vistingDir, false);
				vistingDir.delete();

			}
			else if(isDepthOne){ //to avoid moving files in top level again to top level
				continue;
			}
			else{
				String final_path = rootPath.toString() + File.separatorChar  + file.getName();
				File target = new File ( final_path ) ;

				
				Files.move( file.toPath(), target.toPath() ); //moves file from current folder to top user specfied directory

			}
		}

		// directories in the current Folder
		return;
	}



	/*
		Recurses till last leaf folder and then organizes each directory in a bottom up backtracking manner.
	*/
	public void organizeWithOutMerge(File rootPath, File  currentDirectory, int groupingCode) throws IOException{

			File[] allEntries =  currentDirectory.listFiles();

			for(File tempFile : allEntries){ //for every subdirectory in current path self recurses to organize them 
				if(tempFile.isDirectory()){
					organizeWithOutMerge(rootPath, tempFile, groupingCode);
				}
			}
			
			FileGrouper fg = new FileGrouper(); //groups each file accord to user given input
			fg.groupingHandler(currentDirectory, groupingCode);

	}

}