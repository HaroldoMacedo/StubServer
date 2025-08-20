package haroldo.stub.script.in;

import haroldo.stub.script.Definition;

import java.util.List;

public interface ApiDefinition {

    String getName();
    int getMaxThroughputTPS();
    List<Definition> getDefinitions();


}
