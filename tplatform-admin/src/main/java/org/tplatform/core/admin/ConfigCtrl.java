package org.tplatform.core.admin;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tplatform.common.BaseCtrl;
import org.tplatform.common.RespBody;
import org.tplatform.domain.Config;
import org.tplatform.domain.ConfigService;

/**
 * Created by Tianyi on 2016/3/21.
 */
@Controller
@RequestMapping("/config")
public class ConfigCtrl extends BaseCtrl<Config> {

  @Autowired
  private ConfigService configService;

  @RequestMapping(value = "/updateSingle", method = RequestMethod.POST)
  @ResponseBody
  public RespBody updateSingle(@RequestParam String name, @RequestParam Long pk, @RequestParam String value) {
    if (pk == null) return RespBody.error("id不能为空");

    Config config = configService.findOne(pk);
    if ("val".equalsIgnoreCase(name)) {
      config.setVal(value);
    } else if ("remark".equalsIgnoreCase(name)) {
      config.setRemark(value);
    } else if ("name".equalsIgnoreCase(name)) {
      config.setName(value);
    } else if ("confKey".equalsIgnoreCase(name)) {
      config.setConfKey(value);
    }
    configService.save(config);
    return RespBody.ok();
  }

  @RequestMapping("/saveVal")
  @ResponseBody
  public RespBody saveVal(String confKey, String value) {
    configService.updateByKey(value, confKey);
    return RespBody.ok();
  }

  @RequestMapping("/saveFtpInfo")
  @ResponseBody
  public RespBody saveFtpInfo(String host, String port, String username, String password, String root) {
    configService.updateByKey(Base64.encodeBase64String((host + "|" + port + "|" + username + "|" + Base64.encodeBase64String(password.getBytes()) + "|" + root).getBytes()), "ftpInfo");
    return RespBody.ok();
  }

  /**
   * 根据KEY，查询配置信息
   *
   * @return
   */
  @RequestMapping("/find/ftpInfo")
  @ResponseBody
  public RespBody find() {
    String[] ftpInfo = new String(Base64.decodeBase64(configService.getByKey("ftpInfo"))).split("\\|");
    ftpInfo[3] = new String(Base64.decodeBase64(ftpInfo[3]));
    return RespBody.ok(ftpInfo);
  }

  /**
   * 根据KEY，查询配置信息
   *
   * @param confKey
   * @return
   */
  @RequestMapping("/find/{confKey}")
  @ResponseBody
  public RespBody find(@PathVariable String confKey) {
    return RespBody.ok(configService.getByKey(confKey));
  }

}
