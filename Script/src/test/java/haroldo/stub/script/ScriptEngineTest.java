package haroldo.stub.script;

import haroldo.stub.script.in.ApiInException;
import haroldo.stub.script.out.ApiOutException;
import haroldo.stub.script.parameter.ParameterException;
import haroldo.stub.script.sample.SampleIn;
import haroldo.stub.script.sample.SampleOut;
import org.junit.jupiter.api.Test;

public class ScriptEngineTest {
    private final String ROOT_DIR = "src/test/resources/";

    @Test
    public void basicMainTest() {
        Main.main(new String[] {"-i", ROOT_DIR + "test.samplein.properties", "-o", ROOT_DIR + "test.sampleout.properties"});
    }

    @Test
    public void basicScriptTest() {
        ScriptEngine engine = new ScriptEngine(new SampleIn(), new SampleOut());

        try {
            engine.configureAndRunAPIs();
        } catch (ApiInException | ApiOutException e) {
            assert (false);
            e.printStackTrace();
        }
    }

    @Test
    public void noParameterMainTest() {
        try {
            Main.mainImpl(new String[0]);
        } catch (ParameterException e) {
            assert(e.getMessage().contains("No parameters"));
            return;
        } catch (Exception e) {
            assert(false);
        }
        assert(false);
    }

    @Test
    public void noPropertyInFileTest() {
        try {
            String[] args = {"-i", ROOT_DIR + "test.samplein.properties.dont.exist", "-o", ROOT_DIR + "test.sampleout.properties"};
            Main.mainImpl(args);
        } catch (ParameterException e) {
            assert(e.getMessage().contains("File") && e.getMessage().contains("not found"));
            return;
        } catch (Exception e) {
            assert(false);
        }
        assert(false);
    }

    @Test
    public void noPropertyOutFileTest() {
        try {
            String[] args = {"-i", ROOT_DIR + "test.samplein.properties", "-o", ROOT_DIR + "test.sampleout.properties.dont.exist"};
            Main.mainImpl(args);
        } catch (ParameterException e) {
            assert(e.getMessage().contains("File") && e.getMessage().contains("not found"));
            return;
        } catch (Exception e) {
            assert(false);
        }
        assert(false);
    }

    @Test
    public void nonExistentInClassTest() {
        try {
            String[] args = {"-i", ROOT_DIR + "test.nonexistent.class.properties", "-o", ROOT_DIR + "test.sampleout.properties"};
            Main.mainImpl(args);
        } catch (ScriptException e) {
            assert(e.getMessage().contains("Error instantiating class name"));
            return;
        } catch (Exception e) {
            assert(false);
        }
        assert(true);
    }

    @Test
    public void nonExistentOutClassTest() {
        try {
            String[] args = {"-i", ROOT_DIR + "test.samplein.properties", "-o", ROOT_DIR + "test.nonexistent.class.properties"};
            Main.mainImpl(args);
        } catch (ScriptException e) {
            assert(e.getMessage().contains("Error instantiating class name"));
            return;
        } catch (Exception e) {
            assert(false);
        }
        assert(true);
    }

    @Test
    public void simplePropertyFileTest() {
        try {
            String[] args = {"-i", ROOT_DIR + "test.samplein.properties", "-o", ROOT_DIR + "test.sampleout.properties"};
            Main.mainImpl(args);
        } catch (Exception e) {
            assert(false);
        }
        assert(true);
    }

}
