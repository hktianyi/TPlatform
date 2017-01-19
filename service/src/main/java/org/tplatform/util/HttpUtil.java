package org.tplatform.util;

import org.tplatform.common.GlobalConstant;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Http工具类
 * Created by Tianyi on 2014/11/20.
 */
public class HttpUtil {

  private final static HttpClient httpClient = HttpClients.createDefault();

  //工具类禁止实例化
  private HttpUtil() {
  }


  /**
   * 以get方式发送请求
   *
   * @param url
   * @return
   */
  public static String get(String url) {
    return get(url, null);
  }
  /**
   * 以get方式发送请求
   *
   * @param url
   * @return
   */
  public static String get(String url, Header... headers) {
    return get(URI.create(url), headers);
  }
  /**
   * 以post方式发送请求
   *
   * @param url
   * @return
   */
  public static String post(String url) {
    return post(URI.create(url), null);
  }

  /**
   * 以get方式提交请求
   *
   * @param uri
   * @return
   */
  public static String get(URI uri, Header... headers) {
    Logger.i("【发起GET请求】：" + uri);
    HttpGet get = new HttpGet(uri);
    String result = null;
    if (headers != null && headers.length > 0) {
      get.setHeaders(headers);
    }
    try {
      HttpResponse response = httpClient.execute(get);
      HttpEntity entity = response.getEntity();
      result = analysisResp(entity.getContent());
      EntityUtils.consume(entity);
    } catch (IOException e) {
      Logger.e("get", e);
    }
    Logger.i("【GET请求结果】：" + result);
    return result;
  }

  /**
   * 以post方式发送请求
   *
   * @param uri
   * @param args
   * @return
   */
  public static String post(URI uri, Map<String, String> args, Header... headers) {
    Logger.i("【发起POST请求】：" + uri);
    String result = null;
    HttpPost post = new HttpPost(uri);
    List<NameValuePair> nvps = new ArrayList();
    if (args != null && args.size() > 0) {
      Set<Map.Entry<String, String>> entrys = args.entrySet();
      for (Map.Entry<String, String> enrty : entrys) {
        nvps.add(new BasicNameValuePair(enrty.getKey(), enrty.getValue()));
      }
    }
    if (headers != null && headers.length > 0) {
      post.setHeaders(headers);
    }

    try {
      post.setEntity(new UrlEncodedFormEntity(nvps));
      HttpResponse response = httpClient.execute(post);
      HttpEntity entity = response.getEntity();
      result = analysisResp(entity.getContent());
      EntityUtils.consume(entity);
    } catch (UnsupportedEncodingException e) {
      Logger.e("post", e);
    } catch (ClientProtocolException e) {
      Logger.e("post", e);
    } catch (IOException e) {
      Logger.e("post", e);
    }
    Logger.i("【POST请求结果】：" + result);
    return result;
  }

  /**
   * 以post方式发送请求
   *
   * @param uri
   * @return
   */
  public static String post(URI uri, Header... headers) {
    Logger.i("【发起POST请求】：" + uri);
    String result = null;
    HttpPost post = new HttpPost(uri);
    if (headers != null && headers.length > 0) {
      post.setHeaders(headers);
    }

    try {
      HttpResponse response = httpClient.execute(post);
      HttpEntity entity = response.getEntity();
//      int statusCode = response.getStatusLine().getStatusCode();
      result = analysisResp(entity.getContent());
      EntityUtils.consume(entity);
    } catch (UnsupportedEncodingException e) {
      Logger.e("post", e);
    } catch (ClientProtocolException e) {
      Logger.e("post", e);
    } catch (IOException e) {
      Logger.e("post", e);
    }
    Logger.i("【POST请求结果】：" + result);
    return result;
  }

  /**
   * 解析接口返回数据
   *
   * @param inputStream
   * @return
   * @throws IOException
   */
  private static String analysisResp(InputStream inputStream) throws IOException {
    StringBuilder resp = new StringBuilder();
    if (inputStream != null) {
      BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
      String lines;
      while ((lines = reader.readLine()) != null) {
        resp.append(new String(lines.getBytes(), "UTF-8"));
      }
    }
    return resp.toString();
  }

  /**
   * 以post方式发送请求
   *
   * @param uri
   * @param input
   * @return
   */
  public static String postStream(URI uri, String input, Header... headers) {
    Logger.i("【发起POST请求】：" + uri);
    HttpPost post = new HttpPost(uri);
    String result = null;
    if (headers != null && headers.length > 0) {
      post.setHeaders(headers);
    }
    try {
      post.setEntity(new StringEntity(input, "UTF-8"));
      HttpResponse response = httpClient.execute(post);
      HttpEntity entity = response.getEntity();
      result = analysisResp(entity.getContent());
      EntityUtils.consume(entity);
    } catch (UnsupportedEncodingException e) {
      Logger.e("post", e);
    } catch (ClientProtocolException e) {
      Logger.e("post", e);
    } catch (IOException e) {
      Logger.e("post", e);
    }
    Logger.i("【POST请求结果】：" + result);
    return result;
  }

  /**
   * 以post方式发送请求
   *
   * @param uri
   * @param inputStream
   * @return
   */
  public static String postStream(URI uri, InputStream inputStream) {
    Logger.i("【发起POST请求】：" + uri);
    HttpPost post = new HttpPost(uri);
    String result = null;
    try {
      post.setEntity(new InputStreamEntity(inputStream));
      HttpResponse response = httpClient.execute(post);
      HttpEntity entity = response.getEntity();
      result = analysisResp(entity.getContent());
      EntityUtils.consume(entity);
    } catch (UnsupportedEncodingException e) {
      Logger.e("post", e);
    } catch (ClientProtocolException e) {
      Logger.e("post", e);
    } catch (IOException e) {
      Logger.e("post", e);
    }
    Logger.i("【POST请求结果】：" + result);
    return result;
  }

  /**
   * 从request中获取请求参数
   *
   * @param request
   * @return
   */
  public static String getStringFromRequest(javax.servlet.http.HttpServletRequest request) {
    return getStringFromRequest(request, false);
  }

  /**
   * 从request中获取请求参数
   *
   * @param request
   * @param isStream
   * @return
   */
  public static String getStringFromRequest(javax.servlet.http.HttpServletRequest request, boolean isStream) {
    StringBuilder sbd = new StringBuilder();
    if (isStream) {
      try {
        BufferedReader in = null;
        try {
          in = new BufferedReader(new InputStreamReader(request.getInputStream()));
          String line;
          while ((line = in.readLine()) != null) {
            sbd.append(line);
          }
        } catch (Exception e) {
          Logger.e("getDataFromRequest", e);
        } finally {
          if (in != null) {
            try {
              in.close();
            } catch (IOException e) {
              Logger.e("getDataFromRequest", e);
            }
          }
        }
      } catch (Exception e) {
        Logger.e("getDataFromRequest", e);
      }
    } else {
      //将reqeust中的所有请求参数拼装成URL风格参数
      for (Iterator<Map.Entry<String, String[]>> iter = request.getParameterMap().entrySet().iterator(); iter.hasNext(); ) {
        Map.Entry<String, String[]> e = iter.next();
        String[] values = e.getValue();
        String valueStr = "";
        for (int i = 0; i < values.length; i++)
          valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
        sbd.append("&").append(e.getKey()).append("=").append(valueStr);
      }
    }
    return sbd.length() > 0 ? sbd.substring(1) : null;
  }

  /**
   * 从request中获取请求参数
   *
   * @param request
   * @return
   */
  public static Map<String, String> getMapFromRequest(javax.servlet.http.HttpServletRequest request) {
    return getMapFromRequest(request, false);
  }

  /**
   * 从request中获取请求参数
   *
   * @param request
   * @param isStream
   * @return
   */
  public static Map<String, String> getMapFromRequest(javax.servlet.http.HttpServletRequest request, boolean isStream) {
    Map<String, String> map = new HashMap<>();
    if (isStream) {
      try {
        BufferedReader in = null;
        try {
          in = new BufferedReader(new InputStreamReader(request.getInputStream()));
          String line = null;
          String result = "";
          while ((line = in.readLine()) != null) {
            result += line;
          }
          map.put(GlobalConstant.STREAM_DATA_NAME, result);
        } catch (Exception e) {
          Logger.e("getDataFromRequest", e);
        } finally {
          if (in != null) {
            try {
              in.close();
            } catch (IOException e) {
              Logger.e("getDataFromRequest", e);
            }
          }
        }
      } catch (Exception e) {
        Logger.e("getDataFromRequest", e);
      }
    } else {
      //将reqeust中的所有请求参数放入map中
      for (Iterator<Map.Entry<String, String[]>> iter = request.getParameterMap().entrySet().iterator(); iter.hasNext(); ) {
        Map.Entry<String, String[]> e = iter.next();
        String[] values = e.getValue();
        String valueStr = "";
        for (int i = 0; i < values.length; i++)
          valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
        map.put(e.getKey(), valueStr);
      }
    }
    return map;
  }
}