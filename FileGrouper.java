import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.HashSet;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Iterator;

import java.io.IOException;


public class FileGrouper{


	// => This is a driver function for all the functions in this class.
	public void groupingHandler(File currentPath, PrintWriter mergeTracker, int groupingCode) throws IOException{

			System.out.println("Grouping Code : "+ groupingCode );

			if(groupingCode == 0 ){ //Group only by File Category
				groupByFileCategory( currentPath, mergeTracker, false); //false indicates not to group by type
			}
			else if(groupingCode ==1 ){ //Group by File Category anf File Type
				groupByFileCategory( currentPath, mergeTracker, true); //true indicates to group by type as well
			}
			else if(groupingCode == 2){
				groupByFileType( currentPath, mergeTracker);	
			}
	}

	private void groupByFileType(File currentPath, PrintWriter mergeTracker) throws IOException {

		System.out.println("Group By File Type Method Invoked" );
		//list only files
		File[] allEntries =  currentPath.listFiles();
		String fileName, extension = "" ;
		Integer fileCount = allEntries.length;

		if(fileCount < 1){
			return;
		}

		for(File tempFile : allEntries){

			//only action on files not on directories
			if(tempFile.isDirectory())
				continue;

			//Get File name
			fileName = tempFile.getName();
			
			//Get File extension
			int pos;
			if(fileName.contains(".") && (pos = fileName.lastIndexOf("."))!=0 ){ //valid file name condition
				extension = fileName.substring(  pos+1 );
				System.out.println("File : " + fileName + " extension : "+ extension );

			}

			//if file type directory doesn't exist create one and move current file into it
			String s = extension + "_Files" ;
			String p = currentPath + (File.separatorChar + s) ;
			File f = new File(p);
			if(! f.isDirectory()){
				f.mkdir();
				System.out.println("Directory created " + f );
			}

			File target =  new File (f + (File.separatorChar + fileName) ) ;
			System.out.println("Terget : " + target);
			System.out.println("Moved File from " + tempFile );
			System.out.println("Moved File to " + target );
			System.out.println("------------------------------------------------------------");

			Files.move(tempFile.toPath(), target.toPath() );
		}


	}	

	private void groupByFileCategory(File currentPath, PrintWriter mergeTracker, boolean subFolderAction) throws IOException {

		File[] allEntries = currentPath.listFiles();

		Integer fileCount = allEntries.length;

		if( fileCount == 0 ) //Zero files to Organize 
			return;

		//Intialise common file category list =>TO DO: this list has to be extended further
		//ArrayList used cause order is imp here
		ArrayList<String> text_file_types = new ArrayList<String>( Arrays.asList( "Text_files", "doc", "docx", "log", "msg", "odt", "pages", "rft", "tex", "txt", "wpd", "wps" ) ) ; 
		ArrayList<String> data_file_types = new ArrayList<String>( Arrays.asList( "Data_files", "csv", "dat", "ged", "key", "keychain", "pps", "ppt", "pptx", "sdf", "tar", "tax2016", "tax2017", "vcf", "xml" ) ) ;
		ArrayList<String> audio_file_types = new ArrayList<String>( Arrays.asList( "Audio_files", "aif", "iif", "m3u", "m4a", "mid", "mp3", "mpa", "wav", "wma" ) ) ;
		ArrayList<String> video_file_types = new ArrayList<String>( Arrays.asList( "Video_files", "3gp", "asf", "avi", "flv", "m4v", "mov", "mp4", "mpg", "mkv", "rm", "srt", "swf", "vob", "wmv", "webm" ) ) ;
		ArrayList<String> image_3d_file_types = new ArrayList<String>( Arrays.asList( "Image_3d_files", "3dm", "3ds", "max", "obj" ) ) ;
		ArrayList<String> image_raster_file_types = new ArrayList<String>( Arrays.asList( "Raster_Images", "bmp", "dds", "gif", "jpg", "png", "psd", "pspimage", "tga", "thm", "tif", "tiff", "yuv" ) ) ; 
		ArrayList<String> image_vector_file_types = new ArrayList<String>( Arrays.asList( "Vector_Images", "ai", "eps", "ps", "svg" ) ) ;
		ArrayList<String> page_layout_file_types = new ArrayList<String>( Arrays.asList( "Page_Layouts", "innd", "pct", "pdf" ) ) ;
		ArrayList<String> spreadsheet_file_types = new ArrayList<String>( Arrays.asList( "Spread_sheets", "xlr", "xls", "xlsx" ) ) ;
		ArrayList<String> database_file_types = new ArrayList<String>( Arrays.asList( "DB_Files", "accdb", "db", "dbf", "mdb", "pdb", "sql" ) ) ;
		ArrayList<String> executable_file_types = new ArrayList<String>( Arrays.asList( "Executables", "apk", "app", "bat", "cgi", "com", "exe", "gadget", "jar", "wsf" ) ) ;
		ArrayList<String> game_file_types = new ArrayList<String>( Arrays.asList( "Game_Files", "b", "dem", "gam", "nes", "rom", "sav" ) ) ;
		ArrayList<String> cad_file_types = new ArrayList<String>( Arrays.asList( "CAD_Files", "dwg", "dxf" ) ) ;
		ArrayList<String> gis_file_types = new ArrayList<String>( Arrays.asList( "GIS_Files", "gpx", "kml", "kmz" ) ) ;
		ArrayList<String> web_file_types = new ArrayList<String>( Arrays.asList( "Web_Files", "asp", "aspx", "cer", "cfm", "csr", "css", "dcr", "htm", "html", "js", "jsp", "php", "rss", "xhtml" ) ) ;
		ArrayList<String> plugin_file_types = new ArrayList<String>( Arrays.asList( "Plugins", "crx", "plugin" ) ) ;
		ArrayList<String> font_file_types = new ArrayList<String>( Arrays.asList( "Font_Files", "fnt", "fon", "otf", "ttf" ) ) ;
		ArrayList<String> system_file_types = new ArrayList<String>( Arrays.asList( "System_Files", "cab", "cpl", "cur", "deskthemepack", "dll", "dmp", "drv", "icns", "ico", "lnk", "sys" ) ) ;
		ArrayList<String> settings_file_types = new ArrayList<String>( Arrays.asList( "Setting_Files", "cfg", "ini", "prf" ) ) ;
		ArrayList<String> encoded_file_types = new ArrayList<String>( Arrays.asList( "Encoded_Files", "hqx", "mim", "uue" ) ) ;
		ArrayList<String> compressed_file_types = new ArrayList<String>( Arrays.asList( "Compressed_Files", "7z", "cbr", "deb", "gz", "pkg", "rar", "rpm", "sitx", "tar.gz", "zip", "zipx" ) ) ;
		ArrayList<String> disk_image_file_types = new ArrayList<String>( Arrays.asList( "Disk_Image_Files", "bin", "cue", "dmg", "iso", "mdf", "toast", "vcd" ) ) ;
		ArrayList<String> developer_file_types = new ArrayList<String>( Arrays.asList( "Developer_Files", "c", "class", "cpp", "cs", "dtd", "fla", "h", "java", "lua", "m", "pl", "py", "sh", "sln", "swift", "vb", "vcxproj", "xcodeproj" ) ) ;
		ArrayList<String> backup_file_types = new ArrayList<String>( Arrays.asList( "BackUp_Files", "bak", "tmp" ) ) ;
		ArrayList<String> misc_file_types = new ArrayList<String>( Arrays.asList( "Misc_Files", "crdownload", "ics", "msi", "part", "torrent" ) ) ;

		//Holds all File category in single set
		HashSet<ArrayList<String>>  fileCategories =  new HashSet<ArrayList<String>>(  Arrays.asList(text_file_types ,data_file_types,audio_file_types,video_file_types,image_3d_file_types,
		image_raster_file_types ,image_vector_file_types,page_layout_file_types,spreadsheet_file_types,database_file_types ,
		executable_file_types,game_file_types ,cad_file_types ,gis_file_types,web_file_types,plugin_file_types,font_file_types,
		system_file_types,settings_file_types,encoded_file_types,compressed_file_types,disk_image_file_types,developer_file_types,
		backup_file_types,misc_file_types ) ) ;


		String fileName, extension = "";
		ArrayList<File> commonDir = new ArrayList<File>(); //stores all newly created common dir
		for(File tempFile : allEntries ){
			
			//no action on subdirecory
			if(tempFile.isDirectory())
				continue;

			//Get File name
			fileName = tempFile.getName();
			
			//Get File extension
			int pos;
			if(fileName.contains(".") && (pos = fileName.lastIndexOf("."))!=0 ){ //valid file name condition
				extension = fileName.substring(  pos+1 );
				extension = extension.toLowerCase();
			}


			//find common file category with extension info
			for(ArrayList<String> aList : fileCategories ){

				if( aList.contains(extension)){

					//Create file common category dir if not exists
					String tempcomDir =  currentPath + (File.separatorChar + aList.get(0)) ;
					File comDir = new File(tempcomDir);
					if(!comDir.isDirectory()){
						comDir.mkdir();
						commonDir.add(comDir);
					}


					//Move current File to repective common type dir
					String tempTarget = comDir + (File.separatorChar + fileName) ;
					File target = new File(tempTarget);
					Files.move( tempFile.toPath(),  target.toPath());

				}
			}
		}

		//if subFolder action set -> indicates every common type dir has to sub orgaized by file type

		if(subFolderAction){
			commonDir.add(currentPath);
			for(File tempDir : commonDir){
				System.out.println("---------------"+tempDir+"----------------------");
				groupingHandler( tempDir, mergeTracker, 2); //2 indicates grouping code : 2
			}
		}

	}
}