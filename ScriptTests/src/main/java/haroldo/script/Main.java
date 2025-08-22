package haroldo.script;

import haroldo.stub.script.FromCodedApisDefinition;
import haroldo.stub.script.ToStandaAloneNode;
import haroldo.stub.script.ScriptEngine;
import haroldo.stub.script.in.ApiInException;
import haroldo.stub.script.out.ApiOutException;
import haroldo.stub.script.summary.SummaryScript;

public class Main {
    public static void main(String[] args) {
        try {
            int listenerPort = readCommandLineParameters(args);

            ScriptEngine engine = new ScriptEngine(new FromCodedApisDefinition(), new ToStandaAloneNode(listenerPort));
            SummaryScript summaryScript = engine.configureAndRunAPIs();
        } catch (ApiInException e) {
            System.err.println("Error while reading the APIs configurations - " + e.getMessage());
            e.printStackTrace();
        } catch (ApiOutException e) {
            System.err.println("Error while configuring or running the APIs - " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static int readCommandLineParameters(String[] args) {
        if (args.length == 0 || args[0] == null) {
            System.err.println("Command line missing parameters\n");
            System.out.println("Use: java -jar ScriptDirectNode.jar <ListenerPortNumber>");
            System.exit(1);
        }

        System.out.printf("Starting listener at port %s\n", args[0]);
        return Integer.parseInt(args[0]);
    }
}
