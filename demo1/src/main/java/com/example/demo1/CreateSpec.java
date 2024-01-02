package com.example.demo1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.example.demo1.models.ProcurementArchive;
import org.apache.poi.xwpf.usermodel.*;

public class CreateSpec {
    public static void createSpec(ProcurementArchive archive) {
        // Путь к шаблону Word документа
        String templatePath = "src/main/resources/template.docx";
        // Путь для сохранения заполненного документа
        String outputPath = "src/main/resources/output.docx";


        // Создание объекта XWPFDocument из шаблона
        XWPFDocument doc = null;
        try {
            doc = new XWPFDocument(new FileInputStream(templatePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Создание карты значений для замены в шаблоне
        Map<String, String> valuesMap = new HashMap<>();
        valuesMap.put("post", archive.getOrder().getProcurementOrganization());
        valuesMap.put("ob", archive.getOrder().getApplication().getProductName());
        valuesMap.put("kol", archive.getOrder().getApplication().getAmount().toString());
        valuesMap.put("ce", archive.getOrder().getApplication().getPriceForOne().toString());
        valuesMap.put("cbn", archive.getOrder().getApplication().getFinalPrice().toString());
        valuesMap.put("n", archive.getOrder().getVat().toString());
        valuesMap.put("i", archive.getOrder().getPriceWithVat().toString());
        valuesMap.put("dat", AppData.getInstance().getFormatForServer().format(new Date()));
        valuesMap.put("ndc", String.format("%.2f",archive.getOrder().getPriceWithVat() - archive.getOrder().getApplication().getFinalPrice()));

        for (XWPFParagraph p : doc.getParagraphs()) {
            for (XWPFRun r : p.getRuns()) {
                String text = r.getText(0);
                if (text != null) {
                    for (Map.Entry<String, String> entry : valuesMap.entrySet()) {
                        if (text.contains(entry.getKey())) {
                            text = text.replace(entry.getKey(), entry.getValue());
                            r.setText(text, 0);
                        }
                    }
                }
            }
            for (XWPFTable table : p.getDocument().getTables()) {
                for (XWPFTableRow row : table.getRows()) {
                    for (XWPFTableCell cell : row.getTableCells()) {
                        for (XWPFParagraph cellParagraph : cell.getParagraphs()) {
                            for (XWPFRun run : cellParagraph.getRuns()) {
                                String text = run.getText(0);
                                if (text != null) {
                                    for (Map.Entry<String, String> entry : valuesMap.entrySet()) {
                                        if (text.contains(entry.getKey())) {
                                            text = text.replace(entry.getKey(), entry.getValue());
                                            run.setText(text, 0);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }


        // Сохранение заполненного документа
        try {
            doc.write(new FileOutputStream(outputPath));
            doc.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}