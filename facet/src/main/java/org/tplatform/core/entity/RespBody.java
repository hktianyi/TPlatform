package org.tplatform.core.entity;

import org.tplatform.framework.util.DateUtil;
import org.tplatform.framework.util.JsonUtil;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.github.pagehelper.PageInfo;

/**
 * 统一的 HTTP 响应格式。
 * code 为 "ok" 表示业务调用成功，否则是失败的错误码，如果有多个则以逗号分隔。
 * data 是业务数据，如果失败了则是 null。
 * Created by Tianyi on 2014/11/20.
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@statusCode")
public class RespBody {
  public static final int OK_CODE = 200;
  public static final int ERROR_CODE = 500;
  private static final RespBody OK = new RespBody(OK_CODE, "");
  private static final RespBody ERROR = new RespBody(ERROR_CODE, "");
  private int statusCode;
  private Object data;
  private String errorInfo;
  private Long recordsTotal;
  private Long recordsFiltered;

  private RespBody(int statusCode, String errorInfo) {
    this.statusCode = statusCode;
    this.errorInfo = errorInfo;
  }
  private RespBody(int statusCode, Object data) {
    this.statusCode = statusCode;
    this.data = data;
  }
  private RespBody(int statusCode, Object data, Long records) {
    this.statusCode = statusCode;
    this.data = data;
    this.recordsTotal = records;
    this.recordsFiltered = records;
  }
  private RespBody(int statusCode, Object data, Long recordsTotal, Long recordsFiltered) {
    this.statusCode = statusCode;
    this.data = data;
    this.recordsTotal = recordsTotal;
    this.recordsFiltered = recordsFiltered;
  }

  public static RespBody ok() {
    return OK;
  }

  public static RespBody ok(Object data) {
    return new RespBody(OK_CODE, data);
  }

  public static RespBody ok(Object data, Long records) {
    return new RespBody(OK_CODE, data, records);
  }

  public static RespBody ok(Object data, Pagination pagination) {
    return new RespBody(OK_CODE, data, pagination.getRecordsTotal(), pagination.getRecordsFiltered());
  }
  public static RespBody ok(PageInfo pageInfo) {
    return new RespBody(OK_CODE, pageInfo.getList(), pageInfo.getTotal(), pageInfo.getTotal());
  }

  public static RespBody error() {
    return ERROR;
  }

  public static RespBody error(int statusCode) {
    return new RespBody(statusCode, "");
  }

  public static RespBody error(String msg) {
    return new RespBody(ERROR_CODE, msg);
  }

  public static RespBody error(int statusCode, Object msg) {
    return new RespBody(statusCode, msg);
  }
  
  public static RespBody error(int statusCode, String msg) {
	    return new RespBody(statusCode, msg);
	  }
  

  public int getStatusCode() {
    return statusCode;
  }

  public Object getData() {
    return data;
  }

  public String getErrorInfo() {
    return errorInfo;
  }

  public Long getRecordsTotal() {
    return recordsTotal;
  }

  public Long getRecordsFiltered() {
    return recordsFiltered;
  }

  @Override
  public String toString() {
    return "{" +
        "\"statusCode\":" + statusCode +
        ",\"data\":" + JsonUtil.obj2Json(data) +
        ",\"errorInfo\":\"" + errorInfo +
        "\",\"versionCode\":" + DateUtil.getTimeInMillis() +
        '}';
  }
}
