package com.file_handling_test;

import java.io.File;
//welcome to employee payroll
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.IntStream;

import org.junit.Assert;
import org.junit.Test;

public class EmployeePayrollServiceTest
{
private static String HOME = System.getProperty("user.home");
private static String PLAY_WITH_NIO = "SandeepSinghGaur";

@Test
public void givenPathWhenCheckedThenConfirm() throws IOException {
   //Checks file exists
   Path homePath = Paths.get(HOME);
   Assert.assertTrue(Files.exists(homePath));
   //Deletes files checks file not exists
   Path playPath = Paths.get(HOME + "/" + PLAY_WITH_NIO);
   if(Files.exists(playPath)) FileUtils.deleteFiles(playPath.toFile());
   Assert.assertTrue(Files.notExists(playPath));
   //create Directory
   Files.createDirectories(playPath);
   Assert.assertTrue(Files.exists(playPath));
   //Creates file
   IntStream.range(1, 10).forEach(cntr -> {
      Path tempFile = Paths.get(playPath + "/sandeep"+cntr);
      Assert.assertTrue(Files.notExists(tempFile));
      try {Files.createFile(tempFile);}
      catch(IOException e) { }
      Assert.assertTrue(Files.exists(tempFile));
   });
   //lists directories and files with extension
   Files.list(playPath).filter(Files::isRegularFile).forEach(System.out::println);
   Files.newDirectoryStream(playPath).forEach(System.out::println);
   Files.newDirectoryStream(playPath, path -> path.toFile().isFile() &&
           path.toString().startsWith("sandeep")).forEach(System.out::println);;
}
}
 class FileUtils
{
   public static boolean deleteFiles(File contentsToDelete) {
      File[] allContents = contentsToDelete.listFiles();
      if(allContents != null) {
         for(File file : allContents)
            deleteFiles(file);
      }
      return contentsToDelete.delete();
   }
}