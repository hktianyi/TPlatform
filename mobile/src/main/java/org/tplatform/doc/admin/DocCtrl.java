package org.tplatform.doc.admin;

import com.jcraft.jsch.SftpException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.tplatform.common.BaseCtrl;
import org.tplatform.constant.GlobalConstant;
import org.tplatform.doc.entity.Doc;
import org.tplatform.util.SFTPUtil;

import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("/doc")
public class DocCtrl extends BaseCtrl<Doc> {

  /**
   * CKEDITOR 图片上传功能
   * @param upload
   * @param CKEditorFuncNum
   * @return
   */
  @RequestMapping("/upload")
  @ResponseBody
  public String upload(MultipartFile upload, String CKEditorFuncNum) {
    String result = "<script type=\"text/javascript\">window.parent.CKEDITOR.tools.callFunction('%s','%s','%s');</script>";
    try {
      String[] originalFilename = upload.getOriginalFilename().split("\\.");
      String path = "/doc/" + UUID.randomUUID().toString() + "." + originalFilename[originalFilename.length-1];
      SFTPUtil.upload(upload.getInputStream(), path);
      result = String.format(result, CKEditorFuncNum, GlobalConstant.FILE_DOMAIN + path, "");
    } catch (SftpException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return result;
  }
}
