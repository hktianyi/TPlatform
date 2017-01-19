package org.tplatform.core.admin;//package org.tplatform.core.admin;
//
//import org.apache.commons.codec.binary.Base64;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.tplatform.common.BaseCtrl;
//import org.tplatform.common.RespBody;
//import org.tplatform.domain.Config;
//import org.tplatform.domain.ConfService;
//
///**
// * Created by Tianyi on 2016/3/21.
// */
//@Controller
//@RequestMapping("/sysConf")
//public class SysConfCtrl extends BaseCtrl<Config> {
//
//  @Autowired
//  private ConfService sysConfService;
//
//  @RequestMapping("/saveVal")
//  @ResponseBody
//  public RespBody saveVal(String confKey, String value) {
//    sysConfService.saveVal(confKey, value);
//    return RespBody.ok();
//  }
//
//  @RequestMapping("/saveFtpInfo")
//  @ResponseBody
//  public RespBody saveFtpInfo(String host, String port, String username, String password, String root) {
//    sysConfService.saveVal("ftpInfo", Base64.encodeBase64String((host + "|" + port + "|" + username + "|" + Base64.encodeBase64String(password.getBytes()) + "|" + root).getBytes()));
//    return RespBody.ok();
//  }
//
//  /**
//   * 根据KEY，查询配置信息
//   *
//   * @return
//   */
//  @RequestMapping("/find/ftpInfo")
//  @ResponseBody
//  public RespBody find() {
//    String[] ftpInfo = new String(Base64.decodeBase64(sysConfService.findVal("ftpInfo"))).split("\\|");
//    ftpInfo[3] = new String(Base64.decodeBase64(ftpInfo[3]));
//    return RespBody.ok(ftpInfo);
//  }
//
//  /**
//   * 根据KEY，查询配置信息
//   *
//   * @param confKey
//   * @return
//   */
//  @RequestMapping("/find/{confKey}")
//  @ResponseBody
//  public RespBody find(@PathVariable String confKey) {
//    return RespBody.ok(sysConfService.findVal(confKey));
//  }
//
//}
