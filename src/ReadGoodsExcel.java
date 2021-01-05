import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ReadGoodsExcel {
    public Products[] readgoodsExcel(File file) {
        Products products[] = null;
        try {
            XSSFWorkbook xw = new XSSFWorkbook(new FileInputStream(file));
            XSSFSheet xs = xw.getSheetAt(0);
            products = new Products[xs.getLastRowNum()];
            for (int j = 1; j <= xs.getLastRowNum(); j++) {
                XSSFRow row = xs.getRow(j);
                Products product = new Products();//每循环一次就把电子表格的一行的数据给对象赋值
                for (int k = 0; k <= row.getLastCellNum(); k++) {
                    XSSFCell cell = row.getCell(k);
                    if (cell == null)
                        continue;
                    if (k == 0) {
                        product.setProductname(this.getValue(cell));//给productname属性赋值
                    } else if (k == 1) {
                        product.setPrice(this.getValue(cell));//给price属性赋值
                    } else if (k == 2) {
                        product.setUsername(this.getValue(cell));//给username属性赋值
                    } else if (k == 3) {
                        product.setPhone(this.getValue(cell));//给phone属性赋值
                    }
                }
                products[j-1] = product;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }

    private String getValue(XSSFCell cell) {
        String value;
        CellType type = cell.getCellTypeEnum();

        switch (type) {
            case STRING:
                value = cell.getStringCellValue();
                break;
            case BLANK:
                value = "";
                break;
            case BOOLEAN:
                value = cell.getBooleanCellValue() + "";
                break;
            case NUMERIC:
                value = cell.getNumericCellValue() + "";
                break;
            case FORMULA:
                value = cell.getCellFormula();
                break;
            case ERROR:
                value = "非法字符";
                break;
            default:
                value = "";
                break;
        }
        return value;
    }
}