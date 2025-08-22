package haroldo.stub.script.in;

import haroldo.stub.script.Definition;

import java.util.ArrayList;
import java.util.List;

public class ApiDefinition {
    String name;
    String uri;
    int maxThroughputTPS;
    List<Definition> definitions = new ArrayList<>();

    public ApiDefinition(String name, String uri, int maxThroughputTPS) {
        this.name = name;
        this.uri = uri;
        this.maxThroughputTPS = maxThroughputTPS;
    }
    public ApiDefinition(String name, String uri, int maxThroughputTPS, List<Definition> definitionList) {
        this(name, uri, maxThroughputTPS);
        this.definitions = definitionList;
    }

    public void addResponse(Definition definition) {
        definitions.add(definition);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public int getMaxThroughputTPS() {
        return maxThroughputTPS;
    }

    public void setMaxThroughputTPS(int maxThroughputTPS) {
        this.maxThroughputTPS = maxThroughputTPS;
    }

    public List<Definition> getDefinitions() {
        return definitions;
    }

    public void setDefinitions(List<Definition> definitions) {
        this.definitions = definitions;
    }
}

