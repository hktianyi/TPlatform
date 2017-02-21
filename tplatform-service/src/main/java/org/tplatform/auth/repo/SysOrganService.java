package org.tplatform.auth.repo;

import org.springframework.stereotype.Service;

/**
 * Created by Tianyi on 2015/1/9.
 */
@Service
public class SysOrganService {


//  public List findForTree(String pCode, StatusEnum status, String selectedIds, Class<?> clazz) {
//    SysOrgan sysOrgan = new SysOrgan();
//    sysOrgan.setPCode(pCode);
//    List<SysOrgan> sysResourceList = super.find(sysOrgan);
//    if(JsTree.class.equals(clazz)) {
//      // 解析选中的标签
//      Set<Long> selectedId = (StringUtil.isEmpty(selectedIds) || "0".equals(selectedIds)) ? null : Arrays.stream(selectedIds.split(",")).map(Long::valueOf).collect(Collectors.toSet());
//
//      // 循环sysResourceList, 生成List<JsTree>
//      List list = sysResourceList.stream().map(temp -> {
//        JsTree jsTree = new JsTree();
//        jsTree.setId(temp.getCode());
//        jsTree.setText(temp.getName());
//        jsTree.setState("loaded", temp.getLeaf() < 1);
//
//        if(selectedId!=null) {
//          // 判断当前level是否有选中的节点
//          for (Long id : selectedId) {
//            if(Objects.equals(temp.getId(), id)) {
//              selectedId.remove(id);
//              jsTree.setState("selected", "true");
//            }
//          }
//        }
//        return jsTree;
//      }).collect(Collectors.toList());
//
////          // 未移除,需要循环层级
////          // TODO 只处理了二级关联
////          if(moved[0]) {
////            SysOrgan child = super.find(id);
////            sysResourceList.parallelStream().filter(temp -> Objects.equals(child.getPid(), temp.getId())).forEach(temp -> {
////              sysOrgan.setPid(temp.getId());
////              temp.setChildren(super.find(sysOrgan));
////            });
////          }
//      return list;
//    }
//    return sysResourceList;
//  }
}
