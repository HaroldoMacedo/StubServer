package haroldo.script;

import haroldo.stub.FromCSVFileDefinition;
import haroldo.stub.script.ScriptEngine;
import haroldo.stub.script.ToStandaAloneNode;
import haroldo.stub.script.in.ApiInException;
import haroldo.stub.script.out.ApiOutException;
import haroldo.stub.script.summary.SummaryScript;

import java.io.File;

public class FromCSVToNode {
    static String root = "ScriptTests/src/main/resources/";

    public static void main(String[] args) {
        try {
            int listenerPort = readCommandLineParameters(args);

            File in = new File(root + "OneLine.csv");
            ScriptEngine engine = new ScriptEngine(new FromCSVFileDefinition(in), new ToStandaAloneNode(listenerPort));
            SummaryScript summaryScript = engine.configureAndRunAPIs();

            System.out.println(summaryScript.toString());

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
