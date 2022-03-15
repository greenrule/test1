//  д.з.9. используем Java Jackson https://javascopes.com/jackson-json-java-parser-api-example-tutorial-1e441cdc/
package tests.downloadAndParsingFiles;

import domain.Student;
import domain.Teacher;
import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class JacksonTest {
    ClassLoader classLoader = getClass().getClassLoader();
    @Test
    void parseJsonTest() throws Exception {

        String path = "src/test/resources/files/student_date.json";
        File jsonData = new File(path);
        //create ObjectMapper instance
        ObjectMapper objectMapper = new ObjectMapper();
        //convert json string to object
        Student student = objectMapper.readValue(jsonData, Student.class);
        assertThat(student.name).isEqualTo("Marina");
        assertThat(student.address.street).isEqualTo("Mira");

    }
}
