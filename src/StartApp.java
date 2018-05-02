import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;

/**
 * Created by gogo on 28.4.2018 г..
 */
public class StartApp {
    static  String inputPath = "./test.txt";
    static String outputPath = "./outputTest.txt";
    static StringBuilder stringBuilder = new StringBuilder();
    static StringBuilder currentTxtString = new StringBuilder();
    static int pointcoutner = 1;
    public static void main(String[] args) {

        stringBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<gpx\n" +
                "  version=\"1.0\"\n" +
                "  creator=\"GPSBabel - http://www.gpsbabel.org\"\n" +
                "  xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "  xmlns=\"http://www.topografix.com/GPX/1/0\"\n" +
                "  xsi:schemaLocation=\"http://www.topografix.com/GPX/1/0 http://www.topografix.com/GPX/1/0/gpx.xsd\">\"\n" +
                "<time>2017-07-30T15:11:53Z</time>\"\n");
        TXTToXML(inputPath);
        stringBuilder.append("\t</gpx>");
       System.out.println(stringBuilder);
    }
    public static void TXTToXML(String inputPath) {
        int cyclingLinesIndex = 1;
        File file = new File(inputPath);
        try {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    if(cyclingLinesIndex%3 == 1){
                        currentTxtString.append(line);
                        cyclingLinesIndex++;
                    }
                    else if(cyclingLinesIndex%3 == 2){
                        currentTxtString.append(line);

                        LineToXML(currentTxtString.toString());

                        cyclingLinesIndex++;
                    }
                    else{
                         cyclingLinesIndex++;
                         currentTxtString = new StringBuilder();
                    }
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void LineToXML(String line){

       String lon = line.substring(63,72);
       lon = setCoordinate(lon);
       StringBuilder Oldlat = new StringBuilder(line.substring(75,85));
       Oldlat.deleteCharAt(0);

     String lat = setCoordinate(Oldlat.toString());


     //TODO: Time to be in the right format

       String time = line.substring(0,19);
      time = time.replace("/","-");
      time = time.replace(",","T");
      time+="Z";

       String name = String.valueOf(pointcoutner)+ " ";
       name += line.substring(27,35);
       pointcoutner++;

        stringBuilder.append("<wpt lat=\""+lat+"\" lon=\""+lon+"\">"+"\n"
        + "<time>"+time+"</time>"+"\n"
        +  "<name>"+name+"</name>"+"\n"+
                "</wpt>");



       stringBuilder.append("\n");
    }

    public static String setCoordinate(String coordinate){

       // Convert from DDS to DD cordinates format:
        double d = Double.parseDouble(coordinate.substring(0,2));
         double m = Double.parseDouble(coordinate.substring(2,9));

        double dd = (Math.abs(d) + (m / 60.0));

        return  String.format( "%.5f",dd);

    }


}
