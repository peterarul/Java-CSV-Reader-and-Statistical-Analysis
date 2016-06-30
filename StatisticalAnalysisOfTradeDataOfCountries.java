
/**
 * Write a description of WhichCountriesExport here.
 * 
 * @author (Arul Peter) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
public class WhichCountriesExport {
    public void countryInfo(CSVParser parser, String exportItem1, String exportItem2){
    for (CSVRecord record : parser){
        String export = record.get("Exports");
        if((export.contains(exportItem1)) && export.contains(exportItem2) ){
            String values1 = record.get("Value (dollars)");
            String country1 = record.get("Country");
            System.out.println(country1+": "+export+": "+values1);
        }
    }
    }
    public void countryInfo1(CSVParser parser, String exportItem1){
        int count = 0;
        for (CSVRecord record : parser){
        
        String export = record.get("Exports");
        if(export.contains(exportItem1)){
            count++;
            String values1 = record.get("Value (dollars)");
            String country1 = record.get("Country");
            System.out.println(country1+": "+export+": "+values1);
        }
    }
        System.out.println(count);
    }
    public void countryInfo2(CSVParser parser, String countryName){
    for (CSVRecord record : parser){
        String country = record.get("Country");
        if(country.contains(countryName)){
            String values = record.get("Value (dollars)");
            String export = record.get("Exports");
            System.out.println(country+": "+export+": "+values);
        }
    }
    }
    public void bigExporters(CSVParser parser, String amount){
    for (CSVRecord record : parser){
        String values = record.get("Value (dollars)");
        if(values.length()>amount.length()){
           
            String country = record.get("Country");
            System.out.println(country+": "+values);
        }
    }
    }
    public void tester1(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        countryInfo(parser, "cotton","flowers");
        
    }
    public void tester2(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        bigExporters(parser, "$999,999,999,999");
        
    }
    public void tester3(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        countryInfo1(parser, "cocoa");
        
    }
    public void tester4(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        countryInfo2(parser, "Nauru");
        
    }
    
   

}
