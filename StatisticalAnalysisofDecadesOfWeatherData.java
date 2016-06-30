
/**
 * Write a description of CSVMax here.
 * 
 * @author (Arul Peter) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
public class CSVMax {
    public CSVRecord hottestHourInFile(CSVParser parser){
        CSVRecord largestSoFar = null;
        
        for(CSVRecord currentRow : parser){
           largestSoFar = getLargestOfTwo(currentRow, largestSoFar);
        
    }
    return largestSoFar;
}
 public void testHottestInDay() {
        FileResource fr = new FileResource();
        CSVRecord largest= hottestHourInFile(fr.getCSVParser());
        System.out.println("hottest temp was "+largest.get("TemperatureF")+ "at" +largest.get("TimeEDT"));
    }
 public CSVRecord hottestInManyDays() {
        CSVRecord largestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord currentRow= hottestHourInFile(fr.getCSVParser());
            largestSoFar = getLargestOfTwo(currentRow, largestSoFar);
        }
        return largestSoFar;
    }
 public void testHottestInManyDays(){
        CSVRecord largest = hottestInManyDays();
        System.out.println("hottest temp was "+largest.get("TemperatureF")+ "at " +largest.get("TimeEDT")+ " on "+largest.get("DateUTC"));
    }
 public CSVRecord getLargestOfTwo(CSVRecord currentRow, CSVRecord largestSoFar) {
        if(largestSoFar == null)
            {
                largestSoFar = currentRow;
            }
            else 
            {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
                
                if(currentTemp > largestTemp)
                {
                    largestSoFar = currentRow;
                }
                
            }
            return largestSoFar;
    }
 public CSVRecord getSmallestOfTwo(CSVRecord currentRow, CSVRecord smallestSoFar) {
        if(smallestSoFar == null)
            {
                smallestSoFar = currentRow;
            }
            else 
            {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double smallestTemp = Double.parseDouble(smallestSoFar.get("TemperatureF"));
                if (currentTemp == -9999)
                {
                    currentTemp = smallestTemp;
                }
                else if(currentTemp < smallestTemp)
                {
                    smallestSoFar = currentRow;
                }
                
            }
            return smallestSoFar;
    }
 public CSVRecord coldestHourInFile(CSVParser parser){
         CSVRecord smallestSoFar = null;
        
        for(CSVRecord currentRow : parser){
           smallestSoFar = getSmallestOfTwo(currentRow, smallestSoFar);
        
    }
    return smallestSoFar;
    }
 public void testColdestHourInFile(){
        FileResource fr = new FileResource();
        CSVRecord smallest= coldestHourInFile(fr.getCSVParser());
        System.out.println("coldest temp was "+smallest.get("TemperatureF")+ " at " +smallest.get("TimeEDT"));
    }
 
    
    public CSVRecord coldestInManyDays() {
        CSVRecord smallestSoFar = null;

        DirectoryResource dr = new DirectoryResource();
        
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord currentRow= coldestHourInFile(fr.getCSVParser());
            smallestSoFar = getSmallestOfTwo(currentRow, smallestSoFar);
            
        }

        return smallestSoFar;
        
    }
 public void testColdestInManyDays(){
        CSVRecord smallest = coldestInManyDays();
        System.out.println(" coldest temp was "+smallest.get("TemperatureF")+ " at " +smallest.get("TimeEST")+ " on "+smallest.get("DateUTC"));
    }
 public  String[] fileWithColdestTemperature() {

         CSVRecord smallestSoFar = null;
         DirectoryResource dr = new DirectoryResource();
         String filename = null;
         String filepath = null;
 
         for (File f : dr.selectedFiles()) {
 
             FileResource fr = new FileResource(f);
             CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
             
             if (smallestSoFar == null) {
                 smallestSoFar = currentRow;
             } 
             else {
                 double smallestTemp = Double.parseDouble(smallestSoFar.get("TemperatureF"));
                 double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                if (currentTemp == -9999)
                {
                    currentTemp = smallestTemp;
                }
                if(currentTemp < smallestTemp)
                {
                    smallestSoFar = currentRow;
                    filename = f.getName();
                    filepath = f.getAbsolutePath();
                }
                
             }
 
         }
         return new String[] {filename, filepath};

         

}

public void testFileWithColdestTemperature(){
         String fileattributes[] = fileWithColdestTemperature();
         System.out.println("Coldest day was in file " + fileattributes[0]);
         FileResource fr = new FileResource(fileattributes[1]);
         CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
         System.out.println("Coldest temperature on that day was " + coldest.get("TemperatureF") + " at " + coldest.get("DateUTC"));
         System.out.println("All the temperatures on the coldest day were");
        for (CSVRecord coldestTemp:fr.getCSVParser()) {
            System.out.println(coldestTemp.get("DateUTC")+" "+coldestTemp.get("TimeEST")+" : "+coldestTemp.get("TemperatureF"));
            }
}




//public CSVRecord getSmallestOfTwoHumidity(CSVRecord currentRow, CSVRecord smallestSoFar) {
    
                
 //            }
        
     //return smallestSoFar;
    
 public CSVRecord lowestHumidityInFile(CSVParser parser){
         CSVRecord smallestSoFar = null;
        
        for(CSVRecord currentRow : parser){
           //smallestSoFar = getSmallestOfTwoHumidity(currentRow, smallestSoFar);
           
            if(smallestSoFar == null)
            {   
                if(currentRow.get("Humidity").equals("N/A"))
                {
                    continue;
                }   
            
            else {
                smallestSoFar = currentRow;
                }
                
            }
            if (!currentRow.get("Humidity").equals("N/A"))
            {
                double currentTemp = Double.parseDouble(currentRow.get("Humidity"));
                double smallestTemp = Double.parseDouble(smallestSoFar.get("Humidity"));
                
                if(currentTemp < smallestTemp)
                {
                    smallestSoFar = currentRow;
                }
                
            }
            
           
        
    }
    return smallestSoFar;
    }
  
 public void testLowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVRecord smallest= lowestHumidityInFile(fr.getCSVParser());
        System.out.println("lowest humidity was "+smallest.get("Humidity")+ " on " +smallest.get("DateUTC"));
    }
 
    
    public CSVRecord lowestHumidityInManyFiles() {
        CSVRecord smallestSoFar = null;

        DirectoryResource dr = new DirectoryResource();
        double currentTemp = 0;
        double smallestTemp = 0;
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord currentRow= lowestHumidityInFile(fr.getCSVParser());
            //smallestSoFar = getSmallestOfTwoHumidity(currentRow, smallestSoFar);
             if(smallestSoFar == null)
            {   
                if(currentRow.get("Humidity").equals("N/A"))
                {
                    continue;
                }   
            
            else {
                smallestSoFar = currentRow;
                }
                
            }
            if (!currentRow.get("Humidity").equals("N/A"))
            {
                currentTemp = Double.parseDouble(currentRow.get("Humidity"));
                smallestTemp = Double.parseDouble(smallestSoFar.get("Humidity"));
                
                if(currentTemp < smallestTemp)
                {
                    smallestSoFar = currentRow;
                }
                
            }
            
        }

        return smallestSoFar;
        
    }
 public void testLowestHumidityInManyFiles(){
        CSVRecord smallest = lowestHumidityInManyFiles();
        System.out.println(" lowest humidity in many days was "+smallest.get("Humidity")+ " on "+smallest.get("DateUTC"));
    }
    
    public double averageTemperatureInFile(CSVParser parser){
 	    
 	    double sum=0;
 	    double avarage =0;
 	    int count = 1;
 	   for (CSVRecord currentRow : parser) {
             // use method to compare two records
             double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
             sum += currentTemp;
             avarage =sum/count;
             count++;
         }
 	   
         return avarage;
 	}
 	
 	public void  testAverageTemperatureInFile(){
 	    
 	    
 	    FileResource fr = new FileResource();
         CSVParser parser = fr.getCSVParser();
 	    double avarage = averageTemperatureInFile(parser);
 	    System.out.println("Average temperature in file is " + avarage);
 	    
 	}
 	
 	public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value ){
         
         double sum=0;
 	    double avarage =0;
 	    int count = 1;
 	   for (CSVRecord currentRow : parser) {
             // use method to compare two records
             double currentHumidity = Double.parseDouble(currentRow.get("Humidity"));
             double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
             if(currentHumidity>=value){
                 sum += currentTemp;
                 avarage =sum/count;
                 count++;
             }
         }
 	   
         return avarage;
     }
     
     
     public void testAverageTemperatureWithHighHumidityInFile(){
         
         FileResource fr = new FileResource();
         CSVParser parser = fr.getCSVParser();
 	    double avarage = averageTemperatureWithHighHumidityInFile(parser,80);
 	    if(avarage==0)System.out.println("No temperatures with that humidity");
 	     
 	    else System.out.println("Average temperature when high Humidity is " + avarage);
         
     }
 
}

   
    
