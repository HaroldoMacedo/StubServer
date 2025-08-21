package haroldo.stub;

import haroldo.stub.node.Node;
import haroldo.stub.script.CodedApisDefinition;
import haroldo.stub.script.NodeConfiguration;
import haroldo.stub.script.ScriptEngine;
import haroldo.stub.script.summary.ApiDefinitionSummary;
import haroldo.stub.script.summary.SummaryCopy;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        readCommandLineParameters(args);

        int port = Integer.parseInt(args[0]);
        Node.createListener(port);

        SummaryCopy summaryCopy = configureNodeFromCodeScriptDefinition(port);

        startNodeUntilKilled(port, summaryCopy);
    }

    private static void readCommandLineParameters(String[] args) {
        if (args.length == 0 || args[0] == null) {
            System.err.println("Command line missing parameters\n");
            System.out.println("Use: java -jar ScriptDirectNode.jar <ListenerPortNumber>");
            System.exit(1);
        }

        System.out.printf("Starting listener at port %s\n", args[0]);
    }

    private static SummaryCopy configureNodeFromCodeScriptDefinition(int port) {
        ScriptEngine engine = new ScriptEngine(new CodedApisDefinition(), new NodeConfiguration(port));
        SummaryCopy summaryCopy = engine.copyApisInToOut();

        return summaryCopy;
    }

    private static void startNodeUntilKilled(int port, SummaryCopy summaryCopy) {
        try {
            Node.startListener(port);

            for (ApiDefinitionSummary app : summaryCopy.getApiDefinitionSummaryList()) {
                Node.startApplication(app.getDeployId());
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
