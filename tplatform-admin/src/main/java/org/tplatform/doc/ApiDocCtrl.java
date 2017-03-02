package org.tplatform.doc;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tplatform.common.BaseCtrl;
import org.tplatform.common.RespBody;
import org.tplatform.plugin.doc.Doc;
import org.tplatform.util.StringUtil;

import javax.persistence.criteria.Predicate;

@Controller
@RequestMapping("/api/doc")
public class ApiDocCtrl extends BaseCtrl<Doc> {

  private static Sort LIST_SORT = new Sort("status").and(new Sort(Sort.Direction.DESC, "createTime"));

  @RequestMapping(method = RequestMethod.GET)
  @ResponseBody
  public RespBody list(Doc doc, @RequestParam(required = false, defaultValue = "1") Integer page, @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
    if (page > 0) page--;
    else page = Math.abs(page);
    if (pageSize == 0) pageSize = 10;
    else if (pageSize < 0) pageSize = Math.abs(pageSize);

    return RespBody.ok(baseService.findAll((root, query, cb) -> {
      Predicate predicate = null;
      if (StringUtil.isNotEmpty(doc.getQ())) {
        String q = "%" + doc.getQ() + "%";
        predicate = cb.or(cb.like(root.get("title"), q), cb.like(root.get("keyword"), q), cb.like(root.get("author"), q));
      }
      return predicate;
    }, new PageRequest(page, pageSize, new Sort(Sort.Direction.DESC, "createTime"))));
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  @ResponseBody
  public RespBody view(@PathVariable Long id) {
    return RespBody.ok(baseService.findOne(id));
  }

}
