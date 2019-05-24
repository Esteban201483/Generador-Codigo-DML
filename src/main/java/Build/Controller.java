package Build;

import javax.xml.bind.ValidationException;
import java.util.List;

/**
 * Principal class that controlls all the process of the project. Also, manages the IR list.
 */
public class Controller {

    DataBaseConfiguration dataBaseConfiguration;
    FileConfiguration fileConfiguration;
    ClassScanner classScanner;
    ClassValidator validator;
    OutputViewer outputViewer;
    List<Entity> IRList;


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

    /**
     *  Start with the generation of code.
     */
    void startCodeGeneration() {
        String projectPackage = "";
        List<Class> classList;
        outputViewer = new OutputViewer();

        if (dataBaseConfiguration != null) {
            projectPackage = dataBaseConfiguration.getProjectPackage();
        }

        //Scan all the clases with the entity annotation
        classScanner = new ClassScanner();
        classList = classScanner.scanClasses(projectPackage);

        //Checks errors in the classes. If there isn't any error, then generate IR
        validateClasses(classList);

        System.out.println("Processing IR");


        SQLScriptGeneratorImp mySQLGenerator = new SQLScriptGeneratorImp(IRList);

    }


    /**
     *
     * @param projectClasses
     */
    void  validateClasses(List<Class> projectClasses)
    {
        validator = new ClassValidator(projectClasses);

        try {
            IRList = validator.startValidation();
            outputViewer.printMessage("Ready");
        }
        catch(ValidationException exception)
        {
            outputViewer.printMessage(exception.getMessage());
            outputViewer.printMessage("Cancelling DML Generation...");
        }


    }
}
