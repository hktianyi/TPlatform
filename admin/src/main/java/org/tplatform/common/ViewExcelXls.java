package org.tplatform.common;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.web.servlet.view.document.AbstractXlsView;
import org.tplatform.framework.log.Logger;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Tianyi on 2016/5/13.
 */
public class ViewExcelXls extends AbstractXlsView {
  private static PropertyDescriptor[] props;

  @Override
  protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
    response.setContentType("application/vnd.ms-excel");
    response.setHeader("Content-disposition", "attachment;filename=" + urlEncode(model.get("filename") + "." + model.get("suffix"), request));
    OutputStream ouputStream = response.getOutputStream();
    Object sheetName = model.get("sheetName");
    HSSFWorkbook hssfWorkbook = (HSSFWorkbook) workbook;
    if (sheetName != null && sheetName instanceof String[]) {
      for (String name : (String[]) sheetName) {
        generateWorkbook(hssfWorkbook, name, (String[]) model.get(name + ".titles"), model.get(name + ".fieldNames"), model.get(name + ".data"));
      }
    } else {
      generateWorkbook(hssfWorkbook, sheetName == null ? String.valueOf(model.get("filename")) : String.valueOf(sheetName), (String[]) model.get("titles"), model.get("fieldNames"), model.get("data"));
    }
    hssfWorkbook.write(ouputStream);
    ouputStream.flush();
    ouputStream.close();
  }

  /**
   * 设置下载文件中文件的名称
   *
   * @param str
   * @param request
   * @return
   */
  public String urlEncode(String str, HttpServletRequest request) {
    /**
     * 获取客户端浏览器和操作系统信息
     * 在IE浏览器中得到的是：User-Agent=Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; Maxthon; Alexa Toolbar)
     * 在Firefox中得到的是：User-Agent=Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.7.10) Gecko/20050717 Firefox/1.0.6
     */
    String agent = request.getHeader("USER-AGENT");
    try {
      if ((agent != null) && (-1 != agent.indexOf("MSIE"))) {
        String newStr = URLEncoder.encode(str, "UTF-8");
        newStr = newStr.replace("+", "%20");
        if (newStr.length() > 150) {
          newStr = new String(str.getBytes("GB2312"), "ISO8859-1");
          newStr = newStr.replace(" ", "%20");
        }
        return newStr;
      }
      if ((agent != null) && (-1 != agent.indexOf("Mozilla")))
        return MimeUtility.encodeText(str, "UTF-8", "B");

      return str;
    } catch (Exception ex) {
      return str;
    }
  }

  public void generateWorkbook(HSSFWorkbook workbook, String sheetName, String[] titles, Object fieldObj, Object data) {
    HSSFSheet sheet = workbook.createSheet(sheetName);
    if (data instanceof List) {
      generateWorkbook(workbook, sheet, titles, fieldObj, (List) data, 0);
    } else if (data instanceof Map) {
      int offset = 0;
      for (Map.Entry<String, List> entry : ((Map<String, List>) data).entrySet()) {
        HSSFRow row = sheet.createRow(offset);
        sheet.addMergedRegion(new CellRangeAddress(offset, offset, 0, (short) titles.length - 1));
        HSSFCell cell = row.createCell(0);
        cell.setCellValue(entry.getKey());

        HSSFCellStyle titleStyle = workbook.createCellStyle();
        titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        titleStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        titleStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        HSSFFont titleFont = workbook.createFont();
        titleFont.setBold(true);
        titleStyle.setFont(titleFont);
        cell.setCellStyle(titleStyle);

        generateWorkbook(workbook, sheet, titles, fieldObj, entry.getValue(), ++offset);
        offset = ++offset + entry.getValue().size();
      }
    }
  }

  /**
   * 生成HSSFWorkbook
   *
   * @param titles   表头
   * @param fieldObj 属性名。目前支持 一维、二维数组，一个单元格，可以由多个字段取值组合
   * @param data     数据
   * @return
   */
  public HSSFWorkbook generateWorkbook(HSSFWorkbook workbook, HSSFSheet sheet, String[] titles, Object fieldObj, List data, int rowNumOffset) {
    try {
      HSSFRow row = sheet.createRow(rowNumOffset);
      HSSFCellStyle titleStyle = workbook.createCellStyle();
      titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
      titleStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
      titleStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
      HSSFFont titleFont = workbook.createFont();
      titleFont.setBold(true);
      titleStyle.setFont(titleFont);
      HSSFCell cell = null;
      for (int i = 0; i < titles.length; i++) {
        cell = row.createCell(i);
        cell.setCellValue(titles[i]);
        cell.setCellStyle(titleStyle);
      }

      HSSFCellStyle dataStyle = workbook.createCellStyle();
//      dataStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
      int count = data != null ? data.size() : 0;
      if (fieldObj instanceof String[]) {
        String[] fieldNames = (String[]) fieldObj;
        for (int i = 0; i < count; i++) {
          Object obj = data.get(i);
          row = sheet.createRow((short) (i + rowNumOffset + 1));
          for (int k = 0; k < fieldNames.length; k++) {
            cell = row.createCell(k);
            cell.setCellValue(reflectObjectFieldValue(obj, fieldNames[k]));
            cell.setCellStyle(dataStyle);
          }
        }
      } else if (fieldObj instanceof String[][]) {
        String[][] fieldNames = (String[][]) fieldObj;
        for (int i = 0; i < count; i++) {
          Object obj = data.get(i);
          row = sheet.createRow((short) (i + rowNumOffset + 1));
          for (int k = 0; k < fieldNames.length; k++) {
            String value = "";
            if (fieldNames[k].length == 1) {
              value = reflectObjectFieldValue(obj, fieldNames[k][0]);
            } else {
              for (String name : fieldNames[k])
                value += reflectObjectFieldValue(obj, name);
            }
            cell = row.createCell(k);
            cell.setCellValue(value);
            cell.setCellStyle(dataStyle);
          }
        }
      }

      props = null;

    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
    return workbook;
  }

  private String reflectObjectFieldValue(Object obj, String fieldName) {
    Object value = null;
    try {
      if(obj instanceof Map) {
        Object v = ((Map) obj).get(fieldName);
        return v == null ? "" : String.valueOf(v);
      }

      if (props == null) {
        BeanInfo bi = Introspector.getBeanInfo(obj.getClass(), Object.class);
        props = bi.getPropertyDescriptors();
      }
      String[] str = null;
      if (fieldName.indexOf(".") == -1) {
        for (int i = 0, len = props.length; i < len; i++) {
          if (fieldName.equals(props[i].getName())) {
            value = props[i].getReadMethod().invoke(obj);
            break;
          }
        }
      } else {
        str = fieldName.split("\\.");
        if (str.length == 2) {
          for (int i = 0, len = props.length; i < len; i++) {
            if (str[0].equals(props[i].getName())) {
              value = props[i].getReadMethod().invoke(obj);
              if (value != null) {
                BeanInfo bi = Introspector.getBeanInfo(value.getClass(), Object.class);
                PropertyDescriptor[] childProps = bi.getPropertyDescriptors();
                for (int i1 = 0, len1 = childProps.length; i < len1; i1++) {
                  if (str[1].equals(childProps[i1].getName())) {
                    value = childProps[i1].getReadMethod().invoke(value);
                    break;
                  }
                }
              }
              break;
            }
          }
        } else if (str.length == 3) {
          for (int i = 0, len = props.length; i < len; i++) {
            if (str[0].equals(props[i].getName())) {
              value = props[i].getReadMethod().invoke(obj);
              if (value != null) {
                BeanInfo bi = Introspector.getBeanInfo(value.getClass(), Object.class);
                PropertyDescriptor[] childProps = bi.getPropertyDescriptors();
                for (int i1 = 0, len1 = childProps.length; i < len1; i1++) {
                  if (str[1].equals(childProps[i1].getName())) {
                    switch (childProps[i1].getPropertyType().getSimpleName()) {
                      case "Set":
                        Field field = value.getClass().getDeclaredField(str[1]);
                        value = childProps[i1].getReadMethod().invoke(value);
                        Set set = (Set) value;
                        if (set.size() > 0) {
                          Class<?> clazz = (Class<?>) (((ParameterizedTypeImpl) field.getGenericType()).getActualTypeArguments()[0]);
                          BeanInfo bi1 = Introspector.getBeanInfo(clazz, Object.class);
                          PropertyDescriptor[] childProps1 = bi1.getPropertyDescriptors();
                          for (int i2 = 0, len2 = childProps1.length; i2 < len2; i2++) {
                            if (str[2].equals(childProps1[i2].getName())) {
                              System.out.println(childProps1[i2].getName() + "=====" + childProps1[i2].getPropertyType().getSimpleName());
                              StringBuilder sbd = new StringBuilder();
                              value = null;
                              Iterator iter = set.iterator();
                              while (iter.hasNext()) {
                                sbd.append(',').append(childProps1[i2].getReadMethod().invoke(iter.next()));
                              }
                              if (sbd.length() > 1) value = sbd.substring(1);
                              break;
                            }
                          }
                        } else {
                          value = null;
                        }
                        break;
                      default:
                        value = childProps[i1].getReadMethod().invoke(value);
                        break;
                    }
                    break;
                  }
                }
              }
              break;
            }
          }

        }
      }
    } catch (Exception e) {
      Logger.e("reflectObjectFieldValue", e);
    }
    return value == null ? "" : value.toString();
  }
}
