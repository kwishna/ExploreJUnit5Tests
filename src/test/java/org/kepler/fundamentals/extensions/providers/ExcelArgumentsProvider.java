package org.kepler.fundamentals.extensions.providers;

import org.junit.jupiter.params.provider.ArgumentsProvider;

public class ExcelArgumentsProvider implements ArgumentsProvider {
//    @Override
//    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
//        ExcelSource excelSource = context.getRequiredTestMethod().getAnnotation(ExcelSource.class);
//
//        try (Workbook workbook = WorkbookFactory.create(new FileInputStream(excelSource.filePath()))) {
//            Sheet sheet = workbook.getSheet(excelSource.sheetName());
//            return sheet.stream()
//                    .skip(1)
//                    .map(row -> Arguments.of(
//                            row.getCell(0).getStringCellValue(),  // Column 1 (String)
//                            (int) row.getCell(1).getNumericCellValue(),  // Column 2 (int)
//                            row.getCell(2).getNumericCellValue()  // Column 3 (double)
//                    ));
//        }
//    }
}