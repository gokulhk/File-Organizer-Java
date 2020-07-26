import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.stage.Stage;
import javafx.stage.DirectoryChooser;

import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

import javafx.geometry.Insets;

import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;


public class GUIHandler extends Application{

	@FXML private TextField dirField;

	@FXML private Button chooseBtn;
	@FXML private Button advBtn;
	@FXML private Button orgBtn;

	@FXML private RadioButton gtrb1;
	@FXML private RadioButton gtrb2;
	@FXML private RadioButton gtrb3;

	@FXML private RadioButton sfrb1;
	@FXML private RadioButton sfrb2;
	@FXML private RadioButton sfrb3;
	

	private static Stage primaryStageCopy;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage){

		this.primaryStageCopy = primaryStage;

		try{
			Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("MainPage.fxml"));
			Scene scene = new Scene(root, 600, 500);


			// chooseBtn.setOnAction(new EventHandler<ActionEvent>(){
			// 	@Override
			// 	public void handle(ActionEvent event){
			// 		final DirectoryChooser directoryChooser =  new DirectoryChooser();
			// 		configuringDirectoryChooser(directoryChooser);

			// 		File root = directoryChooser.showDialog(primaryStage);
			// 		if(root != null){
			// 			System.out.println("Directory Chosen : " + root.getAbsolutePath() );
			// 		}else{
			// 			System.out.println("Invalid Directory path!");
			// 		}					
			// 	}
			// });


			//Main Window properties
			primaryStage.setScene(scene);
			primaryStage.setTitle("Pyler");
			primaryStage.show();
		}
		catch(Exception e){
			System.out.println("An Exception Occurred.");
			e.printStackTrace();
		}

	} 

	public void organiseClicked(ActionEvent event) throws Exception{

		//extract inputs
			int groupingCode, subFolderCode;

			String inputField = dirField.getText();
			groupingCode = gtrb1.isSelected() ? 0 : ( gtrb2.isSelected() ? 1 : 2 ) ;
			subFolderCode = sfrb1.isSelected() ? 0 : (sfrb2.isSelected() ? 1 : 2 ) ;

		//validate user inputs
			File pathValidate = new File(inputField);

			if( (! inputField.equals("")) && pathValidate.isDirectory() ){
				//path = pathValidate;
			}
			else if( false ){ //yet to finalise

			}
			else{
				//raise alarm
				System.out.println("Bad Directory Path Entered.");
				return;
			}

		//call respective functions

			//Testing Purpose
			System.out.println("Obtained Inputs are : ");
			System.out.println("rootPath : " +  pathValidate);
			System.out.println("groupingCode : " +  groupingCode);
			System.out.println("subFolderCode : " +  subFolderCode);
			System.out.println("Exited without Calling Backend.");
			if(true)
				return;


			//Finalised Inputs
			File rootPath = pathValidate; //path of the directory to be organized
			DriverClass dc = new DriverClass();

			try{
				dc.driverMethod(rootPath, groupingCode, subFolderCode);
			}
			catch(IOException ioe){
				System.out.println("IOException occurred");
				ioe.printStackTrace();
			}

		//Display Completed or Err Msg respectively
	}

	public String chooseDirectoryAction(ActionEvent event) throws Exception{
					final DirectoryChooser directoryChooser =  new DirectoryChooser();
					configuringDirectoryChooser(directoryChooser);

					File root = directoryChooser.showDialog(GUIHandler.primaryStageCopy);
					if(root != null){
						System.out.println("Directory Chosen : " + root.getAbsolutePath() );
					}else{
						System.out.println("Invalid Directory path!");
					}		


		return "make";
	}

	private void configuringDirectoryChooser(DirectoryChooser directoryChooser){
		//title for Directory Chooser
		directoryChooser.setTitle("Select Some Directory");

		//Set Intial Directory
		directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));
	}

}