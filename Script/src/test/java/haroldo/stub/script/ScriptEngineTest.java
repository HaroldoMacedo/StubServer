package haroldo.stub.script;

import haroldo.stub.script.in.ApiInException;
import haroldo.stub.script.out.ApiOutException;
import haroldo.stub.script.parameter.ParameterException;
import haroldo.stub.script.sample.SampleIn;
import haroldo.stub.script.sample.SampleOut;
import org.junit.jupiter.api.Test;

public class ScriptEngineTest {

    @Test
    public void scriptBasicTest() {
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
}
