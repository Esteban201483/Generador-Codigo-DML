package Build;


public class CodeGenerator {
    private Controller mainController;
    private DataBaseConfiguration dataBaseConfiguration;
    private FileConfiguration fileConfiguration;

    public CodeGenerator(DataBaseConfiguration dataBaseConfiguration, FileConfiguration fileConfiguration)
    {
        this.dataBaseConfiguration = dataBaseConfiguration;
        this.fileConfiguration = fileConfiguration;
    }

    public CodeGenerator(FileConfiguration fileConfiguration)
    {
        this.fileConfiguration = fileConfiguration;
    }

    public CodeGenerator(DataBaseConfiguration dataBaseConfiguration)
    {
        this.dataBaseConfiguration = dataBaseConfiguration;
    }

    public void generateDML()
    {
        //Sends the configuration variables to the controller
        mainController = new Controller(dataBaseConfiguration, fileConfiguration);

        mainController.startCodeGeneration();
    }



}
