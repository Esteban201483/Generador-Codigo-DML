package Build;

import javafx.scene.chart.PieChart;

import java.io.*;
import java.util.List;

/**
 * Controls the generation of the SQL Script. When SQL Script is generated, this class will execute it in the database and/or put in into a file specified by the user.
 */
public class DataManager {

    DataBaseConfiguration dataBaseConfiguration;
    List<Entity> entityList;
    FileConfiguration fileConfiguration;


    public DataManager(DataBaseConfiguration dbc, FileConfiguration fc, List<Entity> eL)
    {
        dataBaseConfiguration = dbc;
        fileConfiguration = fc;
        entityList = eL;
    }

    public void generateScript()
    {
        SQLScriptGeneratorImp mySQLGenerator = new SQLScriptGeneratorImp(entityList, dataBaseConfiguration);
        String scriptCode = mySQLGenerator.generateCode();

        System.out.println(scriptCode);

        if(fileConfiguration!= null)
        {
            System.out.println("Printing file");

            try {
                FileWriter writer = new FileWriter(fileConfiguration.getOutputFile());


                for(String line : scriptCode.split("\n")) {
                    writer.write(line + '\r');
                    System.out.println("...");
                }
                writer.close();

            }
            catch(IOException e)
            {
                System.out.println(e.getMessage());
            }

            System.out.println("Writted code in " + fileConfiguration.getOutputFile() + "file");

        }
    }

}
