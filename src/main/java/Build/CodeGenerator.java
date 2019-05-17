package Build;


public class CodeGenerator {
    Controller mainController;
    DataBaseConfiguration dataBaseConfiguration;
    FileConfiguration fileConfiguration;

    CodeGenerator(DataBaseConfiguration dataBaseConfiguration, FileConfiguration fileConfiguration)
    {
        this.dataBaseConfiguration = dataBaseConfiguration;
        this.fileConfiguration = fileConfiguration;
    }

    CodeGenerator(FileConfiguration fileConfiguration)
    {
        this.fileConfiguration = fileConfiguration;
    }

    CodeGenerator(DataBaseConfiguration dataBaseConfiguration)
    {
        this.dataBaseConfiguration = dataBaseConfiguration;
    }

    void generateDML()
    {

    }



}
