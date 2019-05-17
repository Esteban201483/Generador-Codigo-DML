package Build;

public class Controller {

    DataBaseConfiguration dataBaseConfiguration;
    FileConfiguration fileConfiguration;
    ClassScanner classScanner;

    Controller(DataBaseConfiguration dataBaseConfiguration, FileConfiguration fileConfiguration)
    {
        this.dataBaseConfiguration = dataBaseConfiguration;
        this.fileConfiguration = fileConfiguration;
    }

    Controller(FileConfiguration fileConfiguration)
    {
        this.fileConfiguration = fileConfiguration;
    }

    Controller(DataBaseConfiguration dataBaseConfiguration)
    {
        this.dataBaseConfiguration = dataBaseConfiguration;
    }

    void setClassScanner(ClassScanner classScanner)
    {
        this.classScanner = classScanner;
    }

    void startCodeGeneration()
    {
        
    }
}
