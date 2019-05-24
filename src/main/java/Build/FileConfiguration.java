package Build;

/**
 * This class allows the user to specify the configuration of the output file.
 */
public class FileConfiguration extends Configuration{
    private String outputFile;

    public String getOutputFile() {
        return outputFile;
    }

    public void setOutputFile(String outputFile) {
        this.outputFile = outputFile;
    }
}
