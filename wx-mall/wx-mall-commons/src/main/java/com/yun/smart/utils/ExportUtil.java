package com.yun.smart.utils;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yun.smart.enums.ReturnCode;
import com.yun.smart.exception.AppException;
import com.yun.smart.exception.BussinessException;

public class ExportUtil {
	
	private final static Logger logger = LoggerFactory.getLogger(ExportUtil.class);
	
	public static final String EXPORT_PATH = "E:/export/workPlan/";
	
	/**
	 * 导出文件
	 * @param result
	 * @param fileName
	 * @param response
	 * @throws IOException 
	 */
	public static void outputFile(FileExportParams params, HttpServletResponse response){
		logger.info("【导出excel入参】：{}",params);
		
		try {
			InputStream in = new ByteArrayInputStream(exportExcel(params));
			/*
			 *  设置response的Header
			 */
			response.reset();
			response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(params.getFullName(), "UTF-8"));
			response.addHeader("Content-Length", String.valueOf(in.available()));
			response.setContentType("application/x-msdownload;");
//			response.setContentType("application/octet-stream");
			IOUtils.copy(in, response.getOutputStream());
			
		} catch (Exception e) {
			logger.info("【导出excel出错】",e);
			throw new AppException(ReturnCode.EXPORT_EXCEL_ERROR);
		}
		
	}
	
	/**
	 * 输出文件到服务器
	 * @param params
	 * @param response
	 */
	public static String outputFile(FileExportParams params,Long userId){
		
		try {
			Long start = System.currentTimeMillis();
			InputStream in = new ByteArrayInputStream(exportExcel(params));
			Long end1 = System.currentTimeMillis();
			logger.info("导出excel完成耗时:{}ms",end1-start);
			
			File f = new File(ExportUtil.EXPORT_PATH);
			if (!f.exists()) {
				f.mkdirs();
			}
			
			f = new File(ExportUtil.EXPORT_PATH.concat(userId+"/").concat(params.getFileName().concat(params.getFileType())));
			f.createNewFile();
			
			FileWriter w = new FileWriter(f);
			IOUtils.copy(in, w,Charset.forName("UTF-8"));
			
			return f.getCanonicalPath();
		} catch (Exception e) {
			logger.error("导出文件失败。",e);
			throw new BussinessException("导出文件失败。");
		}
		
	}
	
	
	/**
	 * 导出到excel
	 * @param params
	 * @return
	 * @throws IOException
	 */
	public static byte[] exportExcel(FileExportParams params) throws IOException {
		if (".xlsx".equalsIgnoreCase(params.getFileType())) {
			logger.info("导出.xlsx文件");
			return createExcel(params,new SXSSFWorkbook(new XSSFWorkbook(), 100));
		}
		
		if (".xls".equalsIgnoreCase(params.getFileType())) {
			logger.info("导出.xls文件");
			return createExcel(params,new HSSFWorkbook());
		}
		
		return new byte[0];
	}
	
	
	/**
	* 创建Excel文件
	* @return 临时文件保存地址
	* @throws IOException
	*/
	public static byte[] createExcel(FileExportParams params,Workbook workBook) throws IOException {
		
		createSheet(params,workBook);
		
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		workBook.write(os);
		
		return os.toByteArray();  
	}
	

	/**
	 * 创建一个工作表，设置格式
	 * @param params
	 * @param workBook
	 */
	private static void createSheet(FileExportParams params, Workbook workBook) {
		// 创建一个工作薄对象
		Sheet sheet = workBook.createSheet();
		
		/*
		 * 创建样式对象
		 */
		CellStyle titleCellStyle = workBook.createCellStyle(); 
		// 指定单元格居中对齐，边框为细  
		titleCellStyle.setAlignment(HorizontalAlignment.CENTER);  
		titleCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);  
		titleCellStyle.setBorderTop(BorderStyle.THIN);  
		titleCellStyle.setBorderBottom(BorderStyle.THIN);  
		titleCellStyle.setBorderRight(BorderStyle.THIN);  
		titleCellStyle.setBorderLeft(BorderStyle.THIN);  
		// 设置填充色  
//		titleCellStyle.setFillForegroundColor(HSSFColor.SKY_BLUE.index);  
		titleCellStyle.setFillPattern(FillPatternType.NO_FILL);  
		// 指定当单元格内容显示不下时自动换行  
		titleCellStyle.setWrapText(true);  
		// 设置单元格字体  
		Font titleFont = workBook.createFont();  
		titleFont.setFontHeightInPoints((short) 12);  
		titleFont.setBold(false);
		titleCellStyle.setFont(titleFont);  
		
		/*
		 *  创建单元格样式  
		 */
		CellStyle cellStyle = workBook.createCellStyle();
		// 指定单元格居中对齐，边框为细  
		cellStyle.setAlignment(HorizontalAlignment.CENTER);  
		cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);  
		cellStyle.setBorderTop(BorderStyle.THIN);  
		cellStyle.setBorderBottom(BorderStyle.THIN);  
		cellStyle.setBorderRight(BorderStyle.THIN);  
		cellStyle.setBorderLeft(BorderStyle.THIN);  
		// 设置单元格字体  
		Font font = workBook.createFont();  
		font.setFontHeightInPoints((short) 11);  
		font.setBold(false);
		cellStyle.setFont(font);
		
		setExportData(sheet,titleCellStyle,cellStyle,params);
		
	}
	
	/**
	 * 设置导出数据
	 * @param result
	 */
	private static void setExportData(Sheet sheet,CellStyle titleCellStyle,CellStyle cellStyle,FileExportParams params) {
		/*
		 * 设置表头
		 */
		Row headerRow = sheet.createRow(0);
		Cell headerCell = null;
		String[] headers = params.getHeaders();
		for (int c = 0; c < headers.length; c++) {  
			headerCell  = headerRow.createCell(c);  
			headerCell.setCellStyle(titleCellStyle);  
			headerCell.setCellValue(headers[c]);  
			sheet.setColumnWidth(c, (30 * 160));  
		}
		
		/*
		 * 设置正文
		 */
		List<Map<String,Object>> datas = params.getDatas();
		String[] keys = params.getKeys();
		
		int i = 1;//正文从第二行开始
		for (Map<String,Object> map : datas) {
			Row row = sheet.createRow(i++);
			int j = 0;//开始列
			for (String key : keys) {
				if (ArrayUtils.contains(keys, key)) {
					Object value = map.get(key) == null ? "" :  map.get(key);
					Cell cell = row.createCell(j++);
					cell.setCellStyle(cellStyle);  
					cell.setCellValue(value.toString());
				}
			}
		}
		
	}

}
