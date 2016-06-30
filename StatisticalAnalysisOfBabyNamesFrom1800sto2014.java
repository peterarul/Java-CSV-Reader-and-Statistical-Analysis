
/**
 * Write a description of BabyNames here.
 * 
 * @author (Arul Peter) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
public class BabyNames {
    public void readOneFile(int year){
            String fname = "data/yob" + year + ".txt";
            FileResource fr = new FileResource(fname);
            CSVParser parser = fr.getCSVParser(false);
            for (CSVRecord rec : parser){
                String name = rec.get(0);
                String gender = rec.get(1);
            }
    }
    public void printNames(){
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)){
            System.out.println("Name: "+rec.get(0)+
            " Gender: "+ rec.get(1)+ 
            " Num of Births: "+rec.get(2)+"\n\n");
            if (rec.get(1).equals("F"))
            {   System.out.println("Female Births");
                System.out.println("Name: "+rec.get(0)+
                " Gender: "+ rec.get(1)+ 
                " Num of Births: "+rec.get(2)+"\n\n");
            } else 
            {
                System.out.println("Male Births");
                System.out.println("Name: "+rec.get(0)+
                " Gender: "+ rec.get(1)+ 
                " Num of Births: "+rec.get(2)+"\n\n");
            }
        }
    }
    
    public void totalBirths(FileResource fr){
        int totalBirths = 0;
        int totalMaleBirths = 0;
        int totalFemaleBirths = 0;
        int numUniqueBoyNames = 0;
        int numUniqueGirlNames = 0;
        for (CSVRecord rec : fr.getCSVParser(false)){
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if (rec.get(1).equals("F"))
            {   
              totalFemaleBirths += numBorn;
              numUniqueGirlNames++;
            } else 
            {
                totalMaleBirths += numBorn;
                numUniqueBoyNames++;
            }
        }
        System.out.println("Total Number of Births: "+totalBirths);
        System.out.println("Total Number of Male Births: "+totalMaleBirths);
        System.out.println("Total Number of Female Births: "+totalFemaleBirths);
        System.out.println("Total Number of Unique Male Names: "+numUniqueBoyNames);
        System.out.println("Total Number of Unique Female Names: "+numUniqueGirlNames);
        
    
    }
    public void testTotalBirths(){
        FileResource fr = new FileResource();
        totalBirths(fr);
    }
    
    public String getName(int year, int rank, String gender){
       //String fileName = "yob"+year+".csv";
       //FileResource fr = new FileResource(fileName);
       FileResource fr = new FileResource();
       int count = 0;
       String name = null;
       boolean isPresent = false;
       for (CSVRecord rec : fr.getCSVParser(false)){
           if (rec.get(1).equals(gender)){
               count++;
               if (rank == count){
                  name = rec.get(0);
                  isPresent = true;
                } 
            }
        }
        if (isPresent){
            return name;
        } else return "No Name";
    }
    
    public void testgetName() {
        String name = getName( 2015,450, "M");
        System.out.println(name);
    }
    
     public int getRank(int year, String name, String gender){
       //String fileName = "yob"+year+".csv";
       //FileResource fr = new FileResource(fileName);
       FileResource fr = new FileResource();
       //int count = 0;
       int rank = 0;
       int theRank = 0;
       //String findname = null;
       boolean isPresent = false;
       for (CSVRecord rec : fr.getCSVParser(false)){
           if (rec.get(1).equals(gender)){
               rank++;
               if(rec.get(0).equals(name)) {isPresent = true;
                   theRank = rank;
                }
            }
        }
        if (isPresent){
            return theRank;
        } else return -1;
    }
    
    public void testgetRank() {
        int rank = getRank( 2015,"Frank", "M");
        System.out.println(rank);
    }
    
    public void whatIsNameInYear(String name, int year, int newYear, String gender){
        //String fileName = "yob"+year+".csv";
        //FileResource oldFr = new FileResource(fileName);
        FileResource oldFr = new FileResource();
        //String newFileName = "yob"+year+".csv";
        //FileResource newFr = new FileResource(newFileName);
        FileResource newFr = new FileResource();
        
        int rank = 0;
        int rankInNew = 0;
        boolean isPresent = false;
         boolean isPresentinNew = false;
        for (CSVRecord rec : oldFr.getCSVParser(false)){
           if (rec.get(1).equals(gender)){
               rank++;
               if(rec.get(0).equals(name)) isPresent = true;
            }
        }
        if (isPresent){
            for (CSVRecord rec : newFr.getCSVParser(false)){
                if (rec.get(1).equals(gender)){
                    rankInNew++;
                    if (rec.get(1).equals("M")){
                        String sex = "he";
                        if(rankInNew == rank) {
                        isPresentinNew = true;
                        System.out.println(name + " born in " 
                        + year + " would be " + rec.get(0) + 
                        " if "+sex+" was born in "+ newYear);
                      }
                    } else {
                        String sex = "she";
                        if(rankInNew == rank) {
                        isPresentinNew = true;
                        System.out.println(name + " born in " 
                        + year + " would be " + rec.get(0) + 
                        " if "+sex+" was born in "+ newYear);
                      }
                    }
                    
               }
             }
             if(!isPresentinNew){
                System.out.println("No such rank in year "+newYear);
                }
                
        } 
        
        else {
            System.out.println("No match found in first record");
        }
    }
    public void testWhatisNameInYear() {
        whatIsNameInYear("Owen", 2012, 2014, "M");
    }
    
    public int yearOfHighestRank(String name, String gender){
        int rank = 0;
        String fileName = null; 
        int year = 0;
        boolean isPresent = false;
        DirectoryResource dr = new DirectoryResource();
        
        for(File f : dr.selectedFiles()){
            int currentRank = 0;

            
            FileResource fr = new FileResource(f);
             for (CSVRecord rec : fr.getCSVParser(false)){
                 if (rec.get(1).equals(gender)){
                     currentRank++;
                     if(rec.get(0).equals(name)) {
                         
                         if (rank == 0){
                             rank = currentRank;
                            } else if (currentRank > rank){
                                rank = currentRank;
                                fileName = f.getName();
                                year = Integer.parseInt(fileName.replaceAll("[\\D]", ""));
                                System.out.println(f);
                                isPresent = true;
                            }
                         
                        } 
                 } 
                 
                } 
               
                }
             
           if (isPresent)return year; 
           else return -1;
        
        }
    public void testYearOfHighestRank() {
        int rank = yearOfHighestRank("Mich", "M");
        System.out.println("Year with highest rank is "+rank);
    }
    
    public double getAverageRank(String name, String gender){
        int occurrence = 0;
        int sumOfCurrentRank = 0;
        boolean isPresent = false;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            int currentRank = 0;
            
            FileResource fr = new FileResource(f);
            for (CSVRecord rec : fr.getCSVParser(false)){
                if (rec.get(1).equals(gender)){
                    currentRank++;
                    
                    if(rec.get(0).equals(name)) {
                        occurrence++;
                        sumOfCurrentRank += currentRank;
                        isPresent = true;
                    } 
                    
                
                
                } 
                
            
            }
        
        
        }
            if(isPresent) return sumOfCurrentRank/occurrence;
            else return -1.0;
    }
    
    public void testGetAverageRank() {
        double average = getAverageRank("Robert", "M");
        System.out.println("Average rank is "+average);
    }
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender){
         //String fileName = "yob"+year+".csv";
        //FileResource fr = new FileResource(fileName);
        int totalBirthsHigher = 0;
        boolean isPresent = false;
        
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)){
                if (rec.get(1).equals(gender)){
                    totalBirthsHigher +=Integer.parseInt(rec.get(2));
                    
                    if(rec.get(0).equals(name)) {
                       
                       isPresent = true; 
                       
                    }
                    
                }
            }
            if (isPresent){
                return totalBirthsHigher;
            } else return -1;
            
            
    }
  
    public void testGetTotalBirthsRankedHigher() {
        int sum = getTotalBirthsRankedHigher(2012, "Drew", "M");
        System.out.println("The total briths higher is "+sum);
    }
    }
    
        
    
   
   
   
   


