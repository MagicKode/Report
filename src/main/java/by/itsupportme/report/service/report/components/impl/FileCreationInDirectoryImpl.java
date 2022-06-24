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
    public void createFileInDirectory(XSSFWorkbook workbook){
        String fileLocation = filePath + workBookName + System.currentTimeMillis() + "." + documentFormat;
        try {
            FileOutputStream outputStream = new FileOutputStream(fileLocation);
            workbook.write(outputStream);
        } catch (IOException e) { //TODO обработать исключения
            e.printStackTrace(); // what else can be done here?
            //надо будет статус entity переводить в error
        }
//        finally {
//            workbook.close();
//        }
//    close all (in Finely)
    }
}
