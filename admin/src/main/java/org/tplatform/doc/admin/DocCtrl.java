package org.tplatform.doc.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tplatform.common.BaseCtrl;
import org.tplatform.doc.entity.Doc;
import org.tplatform.doc.service.IDocService;

@Controller
@RequestMapping("/doc")
public class DocCtrl extends BaseCtrl<Doc> {

  @Autowired
  private IDocService docService;
}
