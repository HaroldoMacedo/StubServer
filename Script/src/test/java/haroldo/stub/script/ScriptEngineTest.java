package haroldo.stub.script;

import haroldo.stub.script.in.ApiInException;
import haroldo.stub.script.out.ApiOutException;
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
}
