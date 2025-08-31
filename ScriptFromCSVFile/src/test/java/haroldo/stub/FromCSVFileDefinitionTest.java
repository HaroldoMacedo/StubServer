package haroldo.stub;

import haroldo.stub.script.definition.Definition;
import haroldo.stub.script.in.ApiDefinition;
import haroldo.stub.script.in.ApiInException;

import java.util.Properties;

public class FromCSVFileDefinitionTest {

    public static void main(String[] args) {
        FromCSVFileDefinitionTest t = new FromCSVFileDefinitionTest();
        t.oneRowFileTest();

        t.manyRowFileTest();
    }

    String root = "ScriptFromCSVFile/src/test/resources/";

    public void oneRowFileTest() {
        try {
            FromCSVFileDefinition csvFileDefinition = new FromCSVFileDefinition();
            Properties properties = new Properties();
            properties.put("csv.file.in", root + "OneLine.csv");
            csvFileDefinition.setProperties(properties);

            ApiDefinition apiDefinition = csvFileDefinition.getNext();
            Definition definition = apiDefinition.getDefinitions().getFirst();
            System.out.printf("(%s, %s, %d, %s, '%s', %d)\n", apiDefinition.getName(), apiDefinition.getUri(), apiDefinition.getMaxThroughputTPS(),
                    definition.getMethod(), definition.getMessage(), definition.getLatencyMs());
        } catch (ApiInException e) {
            e.printStackTrace();
            assert (false);
        }
    }

    public void manyRowFileTest() {
        try {
            FromCSVFileDefinition csvFileDefinition = new FromCSVFileDefinition();
            Properties properties = new Properties();
            properties.put("csv.file.in", root + "ManyLines.csv");
            csvFileDefinition.setProperties(properties);

            while (csvFileDefinition.hasNext()) {
                ApiDefinition apiDefinition = csvFileDefinition.getNext();
                Definition definition = apiDefinition.getDefinitions().getFirst();
                System.out.printf("(%s, %s, %d, %s, '%s', %d)\n", apiDefinition.getName(), apiDefinition.getUri(), apiDefinition.getMaxThroughputTPS(),
                        definition.getMethod(), definition.getMessage(), definition.getLatencyMs());
            }
        } catch (ApiInException e) {
            e.printStackTrace();
            assert (false);
        }
    }

}
