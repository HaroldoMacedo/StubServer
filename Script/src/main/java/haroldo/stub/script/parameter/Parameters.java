package haroldo.stub.script.parameter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Parameters {
    private final String[] args;

    public Parameters(String[] args) throws ParameterException {
        if (args.length == 0)
            throw new ParameterException("No parameters have been passed!");
        this.args = args;
    }

    public Properties getPropertiesIn() throws ParameterException {
        String propertyFileName = findPropertyFileNameFromFlag("-i");
        File file = new File(propertyFileName);
        return readPropertiesFile(file);
    }

    public Properties getPropertiesOut() throws ParameterException {
        String propertyFileName = findPropertyFileNameFromFlag("-o");
        File file = new File(propertyFileName);
        return readPropertiesFile(file);
    }

    private String findPropertyFileNameFromFlag(String option) throws ParameterException {
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals(option)) {
                if (i + 1 >= args.length)
                    throw new ParameterException("Parameter '" + option + "' without property file");
                return args[i + 1];
            }
        }
        throw new ParameterException("Option '" + option + "' not found. Cannot continue!");
    }

    private Properties readPropertiesFile(File file) throws ParameterException {
        FileInputStream propertyFileIn = null;
        Properties properties;
        try {
            if (!file.exists())
                throw new ParameterException("File '" + file.getName() + "' not found.");
            propertyFileIn = new FileInputStream(file);
            properties = new Properties();
            properties.load(propertyFileIn);
        } catch (FileNotFoundException fnfe) {
            throw new ParameterException("File '" + file.getName() + "' not found.");
        } catch (IOException ioe) {
            throw new ParameterException("Cannot open '" + file.getName() + "' for reading.");
        } finally {
            try {
                if (propertyFileIn != null)
                    propertyFileIn.close();
            } catch (Exception e) {
            }
        }

        if (properties.isEmpty())
            throw new ParameterException("No properties found in file '" + file.getName() + "'");

        return properties;
    }

    public static String getParameterSyntax() {
        return """
                Use: Script -i <PropertyFileIn> -o <PropertyFileOut>
                \t<PropertyFileIn> - File with the configurations properties for the input script.
                \t\t'class.name' must be present to define the ScriptIn class.
                \t<PropertyFileOut> - File with the configurations properties for the input script.
                \t\t'class.name' must be present to define the ScriptOut class.""";
    }
}
