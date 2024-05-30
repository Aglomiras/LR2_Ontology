import lombok.SneakyThrows;
import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.Rio;
import org.example.convertCimToJson.convertCimToJson;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class Tests {
    @SneakyThrows
    @Test
    public void tester(){
        File initialFile = new File("src/main/resources/lr1_1_scheme.xml");
        InputStream targetStream = new FileInputStream(initialFile);

        Model model = Rio.parse(targetStream,"http://iec.ch/TC57/2016/CIM-schema-cim#", RDFFormat.RDFXML);

        convertCimToJson convertCimToJson = new convertCimToJson();
        convertCimToJson.convertCim(model);
    }
}
