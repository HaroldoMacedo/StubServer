package haroldo.stub.script;

import haroldo.stub.script.in.ScriptIn;
import haroldo.stub.script.out.ScriptOut;
import haroldo.stub.script.parameter.ParameterException;
import haroldo.stub.script.parameter.Parameters;

import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        try {
            mainImpl(args);
        } catch (ParameterException p) {
            System.err.println(p.getMessage());
            System.out.println(Parameters.getParameterSyntax());
            System.exit(1);
        } catch (ScriptException m) {
            System.err.println(m.getMessage());
            System.exit(2);
        } catch (Exception r) {
            r.printStackTrace();
            System.exit(100);
        }
    }

    public static void mainImpl(String[] args) throws Exception {
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        Parameters parameters = new Parameters(args);

        Properties propertiesIn = parameters.getPropertiesIn();
        ScriptIn scriptIn = getScriptIn(propertiesIn);

        Properties propertiesOut = parameters.getPropertiesOut();
        ScriptOut scriptOut = getScriptOut(propertiesOut);

        ScriptEngine scriptEngine = new ScriptEngine(scriptIn, scriptOut);
        scriptEngine.configureAndRunAPIs();
    }

    static ScriptIn getScriptIn(Properties properties) throws ScriptException {
        ScriptIn scriptIn;
        String className = getClassName(properties);
        try {
            scriptIn = ScriptFactory.createScriptIn(className);
            scriptIn.setProperties(properties);
        } catch (javax.script.ScriptException e) {
            throw new ScriptException("Error instantiating class name '" + className
                    + "' from the script input file property - "
                    + e.getMessage());
        }
        return scriptIn;
    }

    static ScriptOut getScriptOut(Properties properties) throws ScriptException {
        ScriptOut scriptOut;
        String className = getClassName(properties);
        try {
            scriptOut = ScriptFactory.createScriptOut(className);
            scriptOut.setProperties(properties);
        } catch (javax.script.ScriptException e) {
            throw new ScriptException("Error instantiating class name '" + className
                    + "' from the script ouput file property - "
                    + e.getMessage());
        }
        return scriptOut;
    }

    static String getClassName(Properties properties) throws ScriptException {
        String className = properties.getProperty("class.name");
        if (className == null)
            throw new ScriptException("No class has been defined. Define the class using property 'class.name' in the property file");

        return className;
    }
}
