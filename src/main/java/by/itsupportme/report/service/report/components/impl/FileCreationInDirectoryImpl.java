package by.itsupportme.report.service.report.components.impl;

import by.itsupportme.report.service.report.components.FileCreationInDirectory;
import lombok.RequiredArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;

@RequiredArgsConstructor
@Component
public class FileCreationInDirectoryImpl implements FileCreationInDirectory {
    @Value("${workbook.name}")
    private String workBookName;
    @Value("${document.format}")
    private String documentFormat;
    @Value("${document.path}")
    private String filePath;

    @Override
    public String createFileInDirectory(XSSFWorkbook workbook) throws IOException {
        String fileLocation = filePath + workBookName + System.currentTimeMillis() + "." + documentFormat;
        try (FileOutputStream outputStream = new FileOutputStream(fileLocation)) {
            workbook.write(outputStream);
        }
        return fileLocation;
    }
}
