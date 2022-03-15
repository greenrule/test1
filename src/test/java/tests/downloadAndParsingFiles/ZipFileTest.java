package tests.downloadAndParsingFiles;
import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

//д/з.9

public class ZipFileTest {

   @Test
    void parseZipTest() throws Exception {
       ClassLoader classLoader = getClass().getClassLoader();
        ZipFile zipUrl = new ZipFile("src/test/resources/files/files1.zip");

        try (InputStream is = classLoader.getResourceAsStream("files/files1.zip");
             ZipInputStream zis = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {

                if (entry.getName().contains("1.pdf")) {
                    assertThat(entry.getName()).isEqualTo("1.pdf");
                    PDF pdf = new PDF(zipUrl.getInputStream(entry));
                    assertThat(pdf.text).contains("Приведенные в данном руководстве");
                }

                else if (entry.getName().contains("2.xlsx")) {
                    assertThat(entry.getName()).isEqualTo("2.xlsx");
                    assertThat(entry.getName()).isEqualTo("2.xlsx");
                    XLS xls = new XLS(zipUrl.getInputStream(entry));
                    assertThat(xls.excel
                            .getSheetAt(0)
                            .getRow(0)
                            .getCell(0)
                            .getStringCellValue()).contains("Андрей");
                }

                else if (entry.getName().contains("3.csv")) {
                    assertThat(entry.getName()).isEqualTo("3.csv");
                    try (
                         CSVReader reader = new CSVReader(new InputStreamReader(zipUrl.getInputStream(entry)))) {
                        List<String[]> content = reader.readAll();
                        assertThat(content.get(0)).contains(
                                "fc;;;");
                    }
                }
            }
        }
    }
}

