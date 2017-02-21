package org.tplatform.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 编码处理工具类
 * 一、Unicode(\u597D)码到中文的相互转换
 * 二、URL编码(%E6%B0)与中文的相互转换
 * *
 */
public class EncodeUtil {

  private static final char[] hexDigit = {
      '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
  };

  public EncodeUtil() {
  }

  private static char toHex(int nibble) {
    return hexDigit[(nibble & 0xF)];
  }

  /**
   * 将字符串编码成 Unicode 。
   *
   * @param theString   待转换成Unicode编码的字符串。
   * @param escapeSpace 是否忽略空格。
   * @return 返回转换后Unicode编码的字符串。
   * 例如 \u597D\u4E45\u4E0D\u89C1
   */
  public static String toUnicode(String theString, boolean escapeSpace) {
    int len = theString.length();
    int bufLen = len * 2;
    if (bufLen < 0) {
      bufLen = Integer.MAX_VALUE;
    }
    StringBuffer outBuffer = new StringBuffer(bufLen);

    for (int x = 0; x < len; x++) {
      char aChar = theString.charAt(x);
      // Handle common case first, selecting largest block that
      // avoids the specials below
      if ((aChar > 61) && (aChar < 127)) {
        if (aChar == '\\') {
          outBuffer.append('\\');
          outBuffer.append('\\');
          continue;
        }
        outBuffer.append(aChar);
        continue;
      }
      switch (aChar) {
        case ' ':
          if (x == 0 || escapeSpace)
            outBuffer.append('\\');
          outBuffer.append(' ');
          break;
        case '\t':
          outBuffer.append('\\');
          outBuffer.append('t');
          break;
        case '\n':
          outBuffer.append('\\');
          outBuffer.append('n');
          break;
        case '\r':
          outBuffer.append('\\');
          outBuffer.append('r');
          break;
        case '\f':
          outBuffer.append('\\');
          outBuffer.append('f');
          break;
        case '=': // Fall through
        case ':': // Fall through
        case '#': // Fall through
        case '!':
          outBuffer.append('\\');
          outBuffer.append(aChar);
          break;
        default:
          if ((aChar < 0x0020) || (aChar > 0x007e)) {
            outBuffer.append('\\');
            outBuffer.append('u');
            outBuffer.append(toHex((aChar >> 12) & 0xF));
            outBuffer.append(toHex((aChar >> 8) & 0xF));
            outBuffer.append(toHex((aChar >> 4) & 0xF));
            outBuffer.append(toHex(aChar & 0xF));
          } else {
            outBuffer.append(aChar);
          }
      }
    }
    return outBuffer.toString();
  }

  /**
   * 将字符串中的中文进行编码，是对应js中encodeURI(urlStr)方法，也是对java中URLEncoder.encode的封装
   */
  public static String ecode(String str, String enc) throws UnsupportedEncodingException {
    String newStr = str;
    Pattern p = null;
    Matcher m = null;
    String value = null;

    p = Pattern.compile("([\u4e00-\u9fa5]+)");//抽取中文
    m = p.matcher(str);

    while (m.find()) {
      value = m.group(0);
      newStr = newStr.replace(value, URLEncoder.encode(value, enc));// 将中文编码
    }
    newStr = newStr.replaceAll(" ", "%20");
    newStr = newStr.replaceAll("？", "%EF%BC%9F");
    newStr = newStr.replaceAll(" ", "%EF%BC%9F");
    return newStr;
  }

  /**
   * 反编码，对应js中decodeURI(urlStr)方法，也是对java中URLDecoder.decode的封装
   */
  public static String decode(String str, String enc) throws UnsupportedEncodingException {
    return URLDecoder.decode(str, enc);
  }

  /**
   * 从 Unicode 码转换成编码前的特殊字符串。
   *
   * @param in       Unicode编码的字符数组。
   * @param off      转换的起始偏移量。
   * @param len      转换的字符长度。
   * @param convtBuf 转换的缓存字符数组。
   * @return 完成转换，返回编码前的特殊字符串。
   */
  public String fromUnicode(char[] in, int off, int len, char[] convtBuf) {
    if (convtBuf.length < len) {
      int newLen = len * 2;
      if (newLen < 0) {
        newLen = Integer.MAX_VALUE;
      }
      convtBuf = new char[newLen];
    }
    char aChar;
    char[] out = convtBuf;
    int outLen = 0;
    int end = off + len;

    while (off < end) {
      aChar = in[off++];
      if (aChar == '\\') {
        aChar = in[off++];
        if (aChar == 'u') {
          // Read the xxxx
          int value = 0;
          for (int i = 0; i < 4; i++) {
            aChar = in[off++];
            switch (aChar) {
              case '0':
              case '1':
              case '2':
              case '3':
              case '4':
              case '5':
              case '6':
              case '7':
              case '8':
              case '9':
                value = (value << 4) + aChar - '0';
                break;
              case 'a':
              case 'b':
              case 'c':
              case 'd':
              case 'e':
              case 'f':
                value = (value << 4) + 10 + aChar - 'a';
                break;
              case 'A':
              case 'B':
              case 'C':
              case 'D':
              case 'E':
              case 'F':
                value = (value << 4) + 10 + aChar - 'A';
                break;
              default:
                throw new IllegalArgumentException(
                    "Malformed \\uxxxx encoding.");
            }
          }
          out[outLen++] = (char) value;
        } else {
          if (aChar == 't') {
            aChar = '\t';
          } else if (aChar == 'r') {
            aChar = '\r';
          } else if (aChar == 'n') {
            aChar = '\n';
          } else if (aChar == 'f') {
            aChar = '\f';
          }
          out[outLen++] = aChar;
        }
      } else {
        out[outLen++] = (char) aChar;
      }
    }
    return new String(out, 0, outLen);
  }

//    public static void main(String[] args) throws IOException {
////        String str = ecode("http://localhost:8080/周杰伦 - 好久不见.mp3", "utf-8");
////        String dstr = URLDecoder.decode(str, "utf-8");
////        System.out.println(str);
////        System.out.println(dstr);
//        HttpUtil.downLoad("http://localhost:8080/周杰伦 - 好久不见.mp3", "d://11.mp3");
//        //java.net.ConnectException: localhost/127.0.0.1:8080 - Connection refused
//    }
}
