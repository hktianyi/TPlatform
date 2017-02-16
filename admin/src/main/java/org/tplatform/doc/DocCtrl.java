package org.tplatform.doc;

import com.jcraft.jsch.SftpException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.tplatform.common.BaseCtrl;
import org.tplatform.common.GlobalConstant;
import org.tplatform.common.RespBody;
import org.tplatform.util.PropertyUtil;
import org.tplatform.util.SFTPUtil;
import org.tplatform.util.StringUtil;

import javax.persistence.criteria.Predicate;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("/doc")
public class DocCtrl extends BaseCtrl<Doc> {

  private static Sort LIST_SORT = new Sort("status").and(new Sort(Sort.Direction.DESC, "createTime"));

  @RequestMapping({"/load"})
  @ResponseBody
  public RespBody load(Doc doc, Integer start, Integer length) {
    return RespBody.ok(baseService.findAll((root, query, cb) -> {
      Predicate predicate = null;
      if (StringUtil.isNotEmpty(doc.getQ())) {
        String q = "%" + doc.getQ() + "%";
        predicate = cb.or(cb.like(root.get("title"), q), cb.like(root.get("keyword"), q), cb.like(root.get("author"), q));
      }
      return predicate;
    }, new PageRequest(start / length, length, new Sort(Sort.Direction.DESC, "createTime"))));
  }

  /**
   * CKEDITOR 图片上传功能
   *
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
      String path = "/doc/" + UUID.randomUUID().toString() + "." + originalFilename[originalFilename.length - 1];
      SFTPUtil.upload(upload.getInputStream(), path);
      if (StringUtil.isEmpty(CKEditorFuncNum)) {
        return "{\"path\":\"" + path + "\"}";
      }
      result = String.format(result, CKEditorFuncNum, PropertyUtil.getProInfo("config", GlobalConstant.SYSTEM_APPLICATION_FILE_DOMAIN) + path, "");
    } catch (SftpException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return result;
  }
}
