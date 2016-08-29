package org.tplatform.collector.service;

import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tianyi on 16/7/20.
 */
public class StationeryCrawler {

  private static final String userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_1) AppleWebKit/601.2.7 (KHTML, like Gecko) Version/9.0.1 Safari/601.2.7";

  private static final String url_list = "http://123.57.18.218/goodslist-25-0-{0}-0-0-0-null";
  private static final String url_detail = "http://123.57.18.218";
  private static final String mainSelector = "#div-goods > ul > li";

  private static final Map<String, String> detailSelector = new HashMap() {
    {
      put("title", ".goods-name ^ text");
      put("ad", "#goods-feature ^ text");
      put("price", "#goodsprice ^ val");
//      put("brand", "#divParam tr:eq(1) > .val ^ text");
//      put("type", "#divParam tr:eq(2) > .val ^ text");
//      put("model", "#divParam tr:eq(3) > .val ^ text");
//      put("standard", "#divParam tr:eq(5) > .val ^ text");
//      put("param", "#param ^ text");

      put("brand", ".dgfc .content:eq(1) tr:eq(2) .tdright ^ text");
      put("type", ".dgfc .content:eq(1) tr:eq(1) .tdright ^ text");
      put("model", ".dgfc .content:eq(1) tr:eq(3) .tdright ^ text");
      put("standard", ".dgfc .content:eq(1) tr:eq(5) .tdright ^ text");
      put("param", "#param ^ text");
    }
  };

  public static void main(String[] args) {
    List<Map<String, String>> result = praseMainList();
    System.out.println(result);

    // 创建工作薄
    Workbook workBook = new HSSFWorkbook();
    // 在工作薄中创建一工作表
    Sheet sheet = workBook.createSheet();
    Document document = null;
    // 在指定的索引处创建一行
    Row rowTitle = sheet.createRow(0);

    Cell cell = rowTitle.createCell(0);
    cell.setCellType(Cell.CELL_TYPE_STRING);
    cell.setCellValue(new HSSFRichTextString("名称"));
    Cell cell1 = rowTitle.createCell(1);
    cell1.setCellType(Cell.CELL_TYPE_STRING);
    cell1.setCellValue(new HSSFRichTextString("标题"));
    Cell cell2 = rowTitle.createCell(2);
    cell2.setCellType(Cell.CELL_TYPE_STRING);
    cell2.setCellValue(new HSSFRichTextString("广告语"));
    Cell cell3 = rowTitle.createCell(3);
    cell3.setCellType(Cell.CELL_TYPE_STRING);
    cell3.setCellValue(new HSSFRichTextString("价格"));
    Cell cell4 = rowTitle.createCell(4);
    cell4.setCellType(Cell.CELL_TYPE_STRING);
    cell4.setCellValue(new HSSFRichTextString("品牌"));
    Cell cell5 = rowTitle.createCell(5);
    cell5.setCellType(Cell.CELL_TYPE_STRING);
    cell5.setCellValue(new HSSFRichTextString("类型"));
    Cell cell6 = rowTitle.createCell(6);
    cell6.setCellType(Cell.CELL_TYPE_STRING);
    cell6.setCellValue(new HSSFRichTextString("型号"));
    Cell cell7 = rowTitle.createCell(7);
    cell7.setCellType(Cell.CELL_TYPE_STRING);
    cell7.setCellValue(new HSSFRichTextString("规格"));
    Cell cell8 = rowTitle.createCell(8);
    cell8.setCellType(Cell.CELL_TYPE_STRING);
    cell8.setCellValue(new HSSFRichTextString("参数"));
    Cell cell9 = rowTitle.createCell(9);
    cell9.setCellType(Cell.CELL_TYPE_STRING);
    cell9.setCellValue(new HSSFRichTextString("图片地址"));
    Cell cell10 = rowTitle.createCell(10);
    cell10.setCellType(Cell.CELL_TYPE_STRING);
    cell10.setCellValue(new HSSFRichTextString("来源"));

    for (int i = 0; i < result.size(); i++) {
      Map<String, String> map = result.get(i);
      Row _rowTitle = sheet.createRow(i+1);
      Cell _cell = _rowTitle.createCell(0);
      _cell.setCellType(Cell.CELL_TYPE_STRING);
      _cell.setCellValue(new HSSFRichTextString(map.get("name")));
      Cell _cell1 = _rowTitle.createCell(1);
      _cell1.setCellType(Cell.CELL_TYPE_STRING);
      _cell1.setCellValue(new HSSFRichTextString(map.get("title")));
      Cell _cell2 = _rowTitle.createCell(2);
      _cell2.setCellType(Cell.CELL_TYPE_STRING);
      _cell2.setCellValue(new HSSFRichTextString(map.get("ad")));
      Cell _cell3 = _rowTitle.createCell(3);
      _cell3.setCellType(Cell.CELL_TYPE_STRING);
      _cell3.setCellValue(new HSSFRichTextString(map.get("price")));
      Cell _cell4 = _rowTitle.createCell(4);
      _cell4.setCellType(Cell.CELL_TYPE_STRING);
      _cell4.setCellValue(new HSSFRichTextString(map.get("brand")));
      Cell _cell5 = _rowTitle.createCell(5);
      _cell5.setCellType(Cell.CELL_TYPE_STRING);
      _cell5.setCellValue(new HSSFRichTextString(map.get("type")));
      Cell _cell6 = _rowTitle.createCell(6);
      _cell6.setCellType(Cell.CELL_TYPE_STRING);
      _cell6.setCellValue(new HSSFRichTextString(map.get("model")));
      Cell _cell7 = _rowTitle.createCell(7);
      _cell7.setCellType(Cell.CELL_TYPE_STRING);
      _cell7.setCellValue(new HSSFRichTextString(map.get("standard")));
      Cell _cell8 = _rowTitle.createCell(8);
      _cell8.setCellType(Cell.CELL_TYPE_STRING);
      _cell8.setCellValue(new HSSFRichTextString(map.get("param")));
      Cell _cell9 = _rowTitle.createCell(9);
      _cell9.setCellType(Cell.CELL_TYPE_STRING);
      _cell9.setCellValue(new HSSFRichTextString(map.get("logo")));
      Cell _cell10 = _rowTitle.createCell(10);
      _cell10.setCellType(Cell.CELL_TYPE_STRING);
      _cell10.setCellValue(new HSSFRichTextString(url_detail + map.get("path")));
    }

    // 新建一输出流并把相应的excel文件存盘
    try {
      FileOutputStream fos = new FileOutputStream("/Users/tianyi/Desktop/office.xls");
      workBook.write(fos);
      fos.flush();
      //操作结束，关闭流
      fos.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 解析产品列表
   *
   * @return
   */
  public static List praseMainList() {
    List result = new ArrayList<>();
    try {
      Elements mainList;
      int pageNo = 1;
      do {
        System.out.println(MessageFormat.format(url_list, pageNo));
        Document document = Jsoup.connect(MessageFormat.format(url_list, pageNo)).timeout(300000).get();
        mainList = document.select(mainSelector);

        for (Element temp : mainList) {
          Element a = temp.child(0);
          Map detail = praseDetail(a.attr("href"));
          detail.put("path", a.attr("href"));
          detail.put("name", a.attr("title"));
          detail.put("logo", a.child(0).attr("src"));
          result.add(detail);
        }

        pageNo++;

      } while (mainList != null && !mainList.isEmpty() && pageNo < 120);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return result;
  }

  public static Map praseDetail(String path) {
    Map result = new HashMap();
    try {
      System.out.println(url_detail + path);
      Document document = Jsoup.connect(url_detail + path).timeout(300000).get();

      for (Map.Entry<String, String> entry : detailSelector.entrySet()) {
        String[] args = entry.getValue().split(" \\^ ");
        String val = null;
        switch (args[1]) {
          case "text":
            val = document.select(args[0]).text();
            break;
          case "val":
            val = document.select(args[0]).val();
            break;
          default:
            break;
        }
        result.put(entry.getKey(), val);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return result;
  }
}
