package tool;

import java.io.*;
import java.util.ArrayList;
import java.util.List;







import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.regexp.recompile;
public class ExcelUtils {  
  
    /** 
     * 建立excelFile 
     *  
     * @param excelPath 
     * @return 
     */  
    public static boolean createExcelFile(String excelPath) {  
        HSSFWorkbook workbook = new HSSFWorkbook();  
        return outputWorkbook(workbook, excelPath);  
    }  
  
    /** 
     * 插入新的工作表 
     *  
     * @param excelPath 
     * @param sheetName 
     * @return 
     */  
    public static boolean insertSheet(String excelPath, String sheetName) {  
        try {  
            Workbook workbook = checkFile(excelPath);
            if (workbook!=null) {  
                Sheet sheet = workbook.createSheet(sheetName);  
                return outputWorkbook(workbook, excelPath);  
            } else {  
                System.out.println(excelPath + ":文件不存在.....");  
            }  
            return false;  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return false;  
    }  
  
    /** 
     * 复制工作表 
     *  
     * @param excelPath 
     * @param sheetName 
     * @param sheetNum 
     * @return 
     */  
    public static boolean copySheet(String excelPath, String sheetName,  
            int formSheetNum) {  
        try {  
            Workbook workbook = checkFile(excelPath);  
            if (workbook!=null) {   
                if (!ExcelUtils.checkSheet(workbook, sheetName)) {  
                    workbook.cloneSheet(formSheetNum);  
                    workbook.setSheetName(workbook.getNumberOfSheets() - 1,  
                            sheetName);  
                    return outputWorkbook(workbook, excelPath);  
                } else {  
                    System.out.println(excelPath + ":存在同名工作表" + sheetName  
                            + ".....");  
                }  
            } else {  
                System.out.println(excelPath + ":文件不存在.....");  
            }  
            return false;  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return false;  
    }  
  
    /** 
     * 插入或者更新单元格 
     *  
     * @param excelPath 
     * @param sheetName 
     * @param rowNum 
     * @param cellNum 
     * @param value 
     * @return 
     */  
    public static boolean insertOrUpdateCell(String excelPath,  
            String sheetName, int rowNum, int cellNum, String value) {  
    	Workbook wb = checkFile(excelPath);  
        try {  
            if (wb!=null) {  
            	
                if (ExcelUtils.checkSheet(wb, sheetName)) {  
                    Sheet sheet = wb.getSheet(sheetName);  
                    Row row = null;  
                    if (sheet.getLastRowNum() < rowNum) {  
                        row = sheet.createRow(rowNum);  
                    } else {  
                        row = sheet.getRow(rowNum);  
                    }  
                    Cell cell = row.getCell(cellNum);  
                    cell.setCellType(Cell.CELL_TYPE_STRING);  
                    cell.setCellValue(value);  
  
                    return outputWorkbook(wb, excelPath);  
                } else {  
                    System.out.println(excelPath + "的" + sheetName  
                            + ":工作表不存在.....");  
                }  
            } else {  
                System.out.println(excelPath + ":文件不存在.....");  
            }  
            return false;  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return false;  
    }  
  
    /** 
     * 插入或者更新单元格 
     *  
     * @param excelPath 
     * @param sheetIndex 
     * @param rowNum 
     * @param cellNum 
     * @param value 
     * @return 
     */  
    public static boolean insertOrUpdateCell(String excelPath, int sheetindex,  
            int rowNum, int cellNum, String value) {  
        try {   
        	Workbook wb =checkFile(excelPath);
            if (wb!=null) {  
                if (ExcelUtils.checkSheet(wb, sheetindex)) {  
                    Sheet sheet = wb.getSheetAt(sheetindex);  
                    Row row = null;  
                    if (sheet.getLastRowNum() < rowNum) {  
                        row = sheet.createRow(rowNum);  
                    } else {  
                        row = sheet.getRow(rowNum);  
                    }  
                    Cell cell = row.getCell(cellNum);  
                    cell.setCellType(Cell.CELL_TYPE_STRING);  
                    cell.setCellValue(value);  
  
                    return outputWorkbook(wb, excelPath);  
                } else {  
                    System.out.println(excelPath + "的" + sheetindex  
                            + ":工作表不存在.....");  
                }  
            } else {  
                System.out.println(excelPath + ":文件不存在.....");  
            }  
            return false;  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return false;  
    }  
  
    /** 
     *  
     * @param excelPath 
     * @param sheetIndex 
     * @param rowNum 
     * @param cellNum 
     * @param value 
     * @param style 
     * @return 
     */  
    public static boolean insertOrUpdateCell(String excelPath, int sheetIndex,  
            int rowNum, int cellNum, String value, HSSFCellStyle style) {  
        try {  
        	Workbook wb = checkFile(excelPath); 
            if (wb!=null) {   
                if (ExcelUtils.checkSheet(wb, sheetIndex)) {  
                    Sheet sheet = wb.getSheetAt(sheetIndex);  
                    Row row = null;  
                    if (sheet.getLastRowNum() < rowNum) {  
                        row = sheet.createRow(rowNum);  
                    } else {  
                        row = sheet.getRow(rowNum);  
                    }  
                    Cell cell = row.createCell(cellNum);  
                    // cell.setCellType(HSSFCell.CELL_TYPE_STRING);  
  
                    Font font = wb.createFont();  
                    font.setFontHeight((short) 18);  
  
                    CellStyle style1 = wb.createCellStyle();  
                    style1.setFont(font);  
                    // cell.setCellStyle(style1);  
                    cell.setCellValue(value);  
                      
                    System.out.println(value);  
                    return outputWorkbook(wb, excelPath);  
                } else {  
                    System.out.println("insertOrUpdateCell:" + excelPath + "的"  
                            + sheetIndex + ":工作表不存在.....");  
                }  
            } else {  
                System.out.println(excelPath + ":文件不存在.....");  
            }  
            return false;  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return false;  
    }  
  
    /** 
     * 插入指定行数据 
     *  
     * @param excelPath 
     * @param sheetName 
     * @param rowNum 
     * @param values 
     * @return 
     */  
    public static boolean insertOrUpadateRowDatas(String excelPath,  
            String sheetName, int rowNum, String... values) {  
        try {  
            for (int i = 0; i < values.length; i++) {  
                insertOrUpdateCell(excelPath, sheetName, rowNum, i, values[i]);  
            }  
            return false;  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return false;  
    }  
  
   
  
    /** 
     * 输出Excel 
     *  
     * @param wb 
     * @param excelPath 
     * @return 
     */  
    private static boolean outputWorkbook(Workbook wb, String excelPath) {  
        FileOutputStream fOut = null;  
        try {  
            fOut = new FileOutputStream(excelPath);  
            wb.write(fOut);  
            fOut.flush();  
            System.out.println(excelPath + ":文件生成...");  
            return true;  
        } catch (FileNotFoundException e) {  
            // TODO 自动生成 catch 块  
            e.printStackTrace();  
        } catch (IOException e) {  
            // TODO 自动生成 catch 块  
            e.printStackTrace();  
        } finally {  
            try {  
                if (fOut != null)  
                    fOut.close();  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
        return false;  
  
    }  
  
    /** 
     * 检查是否存在工作表 
     *  
     * @param excelPath 
     * @param sheetName 
     * @return 
     */  
    public static boolean checkSheet(Workbook wb, String sheetName) {  
        try {  
            for (int numSheets = 0; numSheets < wb.getNumberOfSheets(); numSheets++) {  
                Sheet sheet = wb.getSheetAt(numSheets);  
                if (sheetName.equals(sheet.getSheetName())) {  
                    return true;  
                }  
            }  
            return false;  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return false;  
    }  
  
    /** 
     * 检查是否存在表数量 
     *  
     * @param wb 
     * @param sheetIndex 
     * @return 
     */  
    public static boolean checkSheet(Workbook wb, int sheetIndex) {  
        try {  
            if (wb.getNumberOfSheets() > sheetIndex)  
                return true;  
            return false;  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return false;  
    }  
  
    /** 
     * 清除指定excel的工作表所有内容 
     *  
     * @param excelPath 
     * @param sheet 
     * @return 
     */  
    public static boolean cleanExcelFile1(String excelPath, String sheetName) {  
        try {  
            Workbook wb = checkFile(excelPath);  
            if (wb!=null) {  
                if (ExcelUtils.checkSheet(wb, sheetName)) {  
                    Sheet sheet = wb.getSheet(sheetName);  
                    for (int i = 0; i < sheet.getLastRowNum() + 1; i++) {  
                        if (sheet.getRow(i) != null)  
                            sheet.removeRow(sheet.getRow(i));  
                    }  
                    return outputWorkbook(wb, excelPath);  
                } else {  
                    System.out.println("cleanExcelFile:" + excelPath + "的"  
                            + sheetName + ":工作表不存在.....");  
                }  
  
            } else {  
                System.out.println(excelPath + ":文件不存在.....");  
            }  
            return false;  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return false;  
    }  
  
    /** 
     * 获取指定工作簿行数 
     *  
     * @param excelPath 
     * @param sheetName 
     * @return 
     */  
    public static int getExcelSheetRowNum(Workbook wb, String sheetName) {  
        try {  
            if (wb!=null) {  
                if (ExcelUtils.checkSheet(wb, sheetName)) {  
                    Sheet sheet = wb.getSheet(sheetName);  
                    return sheet.getLastRowNum() + 1;  
                } else {  
                    System.out.println( sheetName + ":工作表不存在.....");  
                }  
  
            } else {  
                System.out.println( ":文件不存在.....");  
            }  
            return 0;  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return 0;  
    }  
  
    /** 
     * 删除行 
     *  
     * @excelPath 
     * @param sheetName 
     * @param row 
     * @return 
     */  
    public static boolean deleteRow(Workbook wb,String excelPath, String sheetName, int row) {  
        if (getExcelSheetRowNum(wb, sheetName) > row) {  
            try {    
                    if (ExcelUtils.checkSheet(wb, sheetName)) {  
                        Sheet sheet = wb.getSheet(sheetName);  
                        if (sheet.getRow(row) != null)  
                            sheet.removeRow(sheet.getRow(row));  
                        return outputWorkbook(wb, excelPath);  
                    } else {  
                        System.out.println("deleteRow:" + excelPath + "的"  
                                + sheetName + ":工作表不存在.....");  
                } 
                return false;  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        } else {  
            System.out.println("不存在指定行");  
            return true;  
        }  
        return false;  
    }  
  
    /** 
     * 获取所有数据 
     *  
     * @excelPath 
     * @param sheetName 
     * @return 
     */  
    public static List<List> getAllData(String excelPath, String sheetName) {  
        try {  
            List<List> list = new ArrayList<List>();  
            Workbook wb  = checkFile(excelPath);
            if (wb!=null) {   
                if (ExcelUtils.checkSheet(wb, sheetName)) {  
                    Sheet sheet = wb.getSheet(sheetName);  
                    for (int i = 0; i < sheet.getLastRowNum() + 1; i++) {  
                        if (sheet.getRow(i) != null) {  
                            List<String> rowList = new ArrayList<String>();  
                            Row aRow = sheet.getRow(i);  
                            for (int cellNumOfRow = 0; cellNumOfRow < aRow  
                                    .getLastCellNum(); cellNumOfRow++) {  
                                if (null != aRow.getCell(cellNumOfRow)) {  
                                    Cell aCell = aRow.getCell(cellNumOfRow);// 获得列值  
                                    if (aCell.getCellType() == Cell.CELL_TYPE_STRING) {  
                                        rowList.add(aCell.getStringCellValue());  
                                    } else if (aCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {  
                                        rowList.add(String.valueOf(  
                                                aCell.getNumericCellValue())  
                                                .replace(".0", ""));  
                                    }  
                                } else {  
                                    rowList.add("");  
                                }  
                            }  
                            list.add(rowList);  
                        }  
                    }  
                    return list;  
                } else {  
                    System.out.println("getAllData:" + excelPath + "的"  
                            + sheetName + ":工作表不存在.....");  
                }  
            } else {  
                System.out.println(excelPath + ":文件不存在.....");  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return null;  
    }  
    
    /** 
     * 读取部分数据 
     *  
     * @param excelPath 
     * @param sheetName 
     * @param start 
     * @param end 
     * @return 
     */  
    public static List<List> getDatas(Workbook wb, String sheetName,  
            int start, int end) {  
        try {  
            List<List> list = new ArrayList<List>(); 
            if (wb!=null) {  
                if (ExcelUtils.checkSheet(wb, sheetName)) {  
                    Sheet sheet = wb.getSheet(sheetName);  
                    for (int i = start - 1; i <= end-1; i++) {  
                        if (sheet.getRow(i) != null) {  
                            List<String> rowList = new ArrayList<String>();  
                            Row aRow = sheet.getRow(i);  
                            for (int cellNumOfRow = 0; cellNumOfRow < aRow  
                                    .getLastCellNum(); cellNumOfRow++) {  
                                if (null != aRow.getCell(cellNumOfRow)) {  
                                    Cell aCell = aRow.getCell(cellNumOfRow);// 获得列值  
                                    if (aCell.getCellType() == Cell.CELL_TYPE_STRING) {  
                                        rowList.add(aCell.getStringCellValue());  
                                    } else if (aCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {  
                                        rowList.add(String.valueOf(  
                                                aCell.getNumericCellValue())  
                                                .replace(".0", ""));  
                                    }  
                                } else {  
                                    rowList.add("");  
                                }  
                            }  
                            list.add(rowList);  
                        }  
                    }  
                    return list;  
                } else {  
                    System.out.println(sheetName + ":工作表不存在.....");  
                }  
            } else {  
                System.out.println(":文件不存在.....");  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return null;  
    }  
    public static List<String> getDatas(Workbook wb, String sheetName,  
            int startrow,int cell, int sum) {  
    	try {
    		List<String> cList = new ArrayList<String>(); 
			if (wb != null) {
				for (int i = 0; i < sum; i++) { 
					if (getData(wb, sheetName, startrow+i, cell)!=null) {
						cList.add(getData(wb, sheetName, startrow+i, cell).trim());
					}
				}
			 return cList;
			 }
			return null;
		} catch (Exception e) {
		}
		return null;
    }
    /** 
     * 获取指定行列数据 
     *  
     * @param excelPath 
     * @param sheetName 
     * @param row 
     * @param cellNum 
     * @return 
     */  
    public static String getData(Workbook wb, String sheetName, int row,  
            int cellNum) {  
            try {  
                    if (ExcelUtils.checkSheet(wb, sheetName)) {  
                        Sheet sheet = wb.getSheet(sheetName);  
                        Cell aCell = sheet.getRow(row-1).getCell(cellNum-1);  
                        if (aCell != null)  
                        	aCell.setCellType(CellType.STRING);
                            return aCell.getStringCellValue();  
                    } else {  
                        System.out.println( sheetName + ":工作表不存在.....");  
                    }  
            } catch (Exception e) {  
//                e.printStackTrace();  
                return null;
            }
			return null;  
    }  
    public static int getCellRow(Workbook wb, String sheetName, int row,  
            int cellNum) {  
        if (getExcelSheetRowNum(wb, sheetName) > row) {  
            try {  
            	int sum = 0;
                    if (ExcelUtils.checkSheet(wb, sheetName)) {  
                    	while (true) {
                    		if (getData(wb, sheetName, row, cellNum)!=null) {
    							sum++;
    							row++;
    						}else {
								return sum;
							}
							
						}
                    	
                    } else {  
                        System.out.println(sheetName + ":工作表不存在.....");  
                    }  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
        return 0;  
    }  
  public static Workbook checkFile(String filePath){
      Workbook wb = null;
      if(filePath==null){
          return null;
      }
      String extString = filePath.substring(filePath.lastIndexOf("."));
      InputStream is = null;
      try {
          is = new FileInputStream(filePath);
          if(".xls".equals(extString)){
              return wb = new HSSFWorkbook(is);
          }else if(".xlsx".equals(extString)){
              return wb = new XSSFWorkbook(is);
          }else{
              return wb = null;
          }
          
      } catch (FileNotFoundException e) {
          e.printStackTrace();
      } catch (IOException e) {
          e.printStackTrace();
      }
      return wb;
  }
}  