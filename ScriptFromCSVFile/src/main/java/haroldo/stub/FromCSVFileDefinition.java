package haroldo.stub;

import haroldo.stub.script.definition.DefaultDefinitionImpl;
import haroldo.stub.script.definition.Definition;
import haroldo.stub.script.in.ApiDefinition;
import haroldo.stub.script.in.ApiInException;
import haroldo.stub.script.in.ScriptIn;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class FromCSVFileDefinition implements ScriptIn {

    private Scanner scanner;
    private int count = 0;

    @Override
    public boolean hasNext() throws ApiInException {
        return scanner.hasNextLine();
    }

    @Override
    public ApiDefinition getNext() throws ApiInException {
        count++;
        if (!scanner.hasNextLine())
            throw new ApiInException(count, "All lines have been read from input file");

        return getApiDefinition(scanner.nextLine());
    }

    @Override
    public void setProperties(Properties properties) throws ApiInException {
        try {
            String inputFileName = properties.getProperty("csv.file.in");
            if (inputFileName == null)
                throw new ApiInException("No input file name has been defined ");

            File csvFileInput = new File(inputFileName);
            scanner = new Scanner(csvFileInput);
        } catch (FileNotFoundException e) {
            throw new ApiInException("File not found: " + e.getMessage());
        }
    }

    private ApiDefinition getApiDefinition(String lineCSV) throws ApiInException {
        String name;
        String uri;
        int maxThroughputTPS;
        List<Definition> definitionList = new ArrayList<>();

        String[] values = lineCSV.split("(?<!\\\\),");
        name = values[0].trim();
        uri = values[1].trim();
        maxThroughputTPS = Integer.parseInt(values[2]);

        int method = methodValueOf(values[3].trim());
        String message = values[4].trim().replaceAll("\\\\,", ",");
        int latencyMS = Integer.parseInt(values[5]);
        definitionList.add(new DefaultDefinitionImpl(method, message, latencyMS));

        return new ApiDefinition(name, uri, maxThroughputTPS, definitionList);
    }

    private int methodValueOf(String methodName) throws ApiInException {
        List<String> methods = List.of("GET", "POST", "PUT", "DELETE");
        int method = methods.indexOf(methodName);

        if (method < 0)
            throw new ApiInException(0, "Method " + methodName + " is not acceptable");

        return method;
    }
}
