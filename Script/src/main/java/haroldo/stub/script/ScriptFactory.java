package haroldo.stub.script;

import haroldo.stub.script.in.ScriptIn;
import haroldo.stub.script.out.ScriptOut;

import javax.script.ScriptException;

public class ScriptFactory {

    public static ScriptIn createScriptIn(String className) throws ScriptException {
        ScriptIn scriptIn;
        try {
            scriptIn = (ScriptIn) getClassByName(className);
        } catch (ClassCastException c) {
            throw new ScriptException("Class '" + className + "' does not implement interface 'ScrpitIn'");
        }
        return scriptIn;
    }

    public static ScriptOut createScriptOut(String className) throws ScriptException {
        ScriptOut scriptOut;
        try {
            scriptOut = (ScriptOut) getClassByName(className);
        } catch (ClassCastException c) {
            throw new ScriptException("Class '" + className + "' does not implement interface 'ScrpitOut'");
        }
        return scriptOut;
    }

    public static Object getClassByName(String className) throws ScriptException {
        Object script;
        try {
            script = Class.forName(className).newInstance();
        } catch (ClassNotFoundException c) {
            throw new ScriptException("Class '" + className + "' was not found - " + c.getMessage());
        } catch (Exception e) {
            throw new ScriptException(e);
        }
        return script;
    }
}
