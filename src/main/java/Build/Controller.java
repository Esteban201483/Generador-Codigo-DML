package Build;

import java.util.List;

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

        if (dataBaseConfiguration != null) {
            projectPackage = dataBaseConfiguration.getProjectPackage();
        }

        //Scan all the clases with the entity annotation
        classScanner = new ClassScanner();
        classList = classScanner.scanClasses(projectPackage);

        //Checks errors in the classes. If there isn't any error, then generate IR
        validateClasses(classList);

    }


    void  validateClasses(List<Class> projectClasses)
    {
        validator = new ClassValidator(projectClasses);
        IRList =  validator.startValidation();

        System.out.println("Ready");
    }
}
