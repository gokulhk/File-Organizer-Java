package org.fileorganizerjava;

import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.logging.Logger;

class DriverClass {

  private void driverMethod(
      File rootPath, PrintWriter mergeTracker, int groupingCode, int subFolderCode)
      throws IOException {

    // Do subFolder action first always
    if (subFolderCode != 2) {

      SubFolderAction sfa = new SubFolderAction();

      if (subFolderCode == 0) { // Merge files to root folder and organize

        // Pulls all files to root Folder
        sfa.mergeSubFolder(rootPath, rootPath, mergeTracker, true); // true to indicate DepthOne

        // call Grouping Handler to group files as selected
        FileGrouper fg = new FileGrouper();
        fg.groupingHandler(rootPath, mergeTracker, groupingCode);

      } else if (subFolderCode
          == 1) { // Organize files in their respective folders with selected grouping type

        sfa.organizeWithOutMerge(rootPath, rootPath, mergeTracker, groupingCode);
      }
    } else { // Grouping type

      FileGrouper fg = new FileGrouper();
      fg.groupingHandler(rootPath, mergeTracker, groupingCode);
    }
  }

  public void showMenu() {
    try (Scanner scanner = new Scanner(System.in)) {

      System.out.println("File Organizer - 1.0\n");

      // 1. How to rearrange menu
      System.out.println("How do you want to group files by ? ");
      System.out.println(
          """
              1. File Category (Images(.png, .jpeg..), Documents(.docx, .ppt,...)..).
              2. File Type (.txt, .png).
              3. Both.
              4. Exit
          """);
      System.out.println("Enter your choice: ");
      int groupingTypeChoice = scanner.nextInt();

      // 2. Should we pull all files to root menu
      System.out.println("Where do you want to group files by ?");
      System.out.println(
          """
               1. Pull all files from subfolders to root and rearrange."
               2. Rearrange files in their current subfolders."
               3. Don't rearrange subfolders."
               4. Exit.
          """);
      System.out.println("Enter your choice: ");
      int subFolderActionCode = scanner.nextInt();

      // 3. Get root folder path.
      System.out.println("Enter the folder path to rearrange: ");
      String folderPath = scanner.next();

      printPlan(groupingTypeChoice, subFolderActionCode, folderPath);

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private void printPlan(int groupingTypeChoice, int subFolderActionCode, String folderPath) {
    System.out.println("Confirm your choice.");
    System.out.println("Grouping Code : " + groupingTypeChoice);
    System.out.println("Subfolder Code : " + subFolderActionCode);
    System.out.println("Folder Path : " + folderPath);
  }

  public void start(String[] args) { // Getting Input

    int groupingCode = Integer.parseInt(args[0]); // Grouping Code
    int subFolderCode =
        Integer.parseInt(args[1]); // default subfolder code = 2 no action option as default
    File rootPath = new File(args[2]); // path of the directory to be organized
    File mergeLogger =
        new File(args[3]); // path of the file to log all the file movements for revert

    PrintWriter mergeTracker = null;
    DriverClass dc = new DriverClass();

    try {
      FileWriter fw = new FileWriter(mergeLogger, true);
      BufferedWriter bw = new BufferedWriter(fw);
      mergeTracker = new PrintWriter(bw);

      dc.driverMethod(rootPath, mergeTracker, groupingCode, subFolderCode);

    } catch (IOException ioe) {
      System.out.println("IOException occurred");
    } finally {
      // Closing Open Resources
      if (mergeTracker != null) mergeTracker.close();
    }
  }
}
