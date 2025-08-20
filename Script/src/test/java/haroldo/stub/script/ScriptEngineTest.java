package haroldo.stub.script;

import haroldo.stub.script.sample.SampleIn;
import haroldo.stub.script.sample.SampleOut;
import org.junit.jupiter.api.Test;

public class ScriptEngineTest {

    @Test
    public void scriptBasicTest() {
        ScriptEngine engine = new ScriptEngine(new SampleIn(), new SampleOut());

        engine.copyApisInToOut();
    }
}
