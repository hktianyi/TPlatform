package org.tplatform.doc.entity;

import lombok.Data;
import org.tplatform.core.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@Table(name = "t_doc")
public class Doc extends BaseEntity {

  private String title;         //标题
  private String summary;       //摘要
  @Column(columnDefinition = "longtext")
  private String context;       //内容
  private String type;       		//文章类型
  private String author;        //作者
  private String keyword;       //关键词
  private String originAddress; //来源
  private String imgUrl;        //封面图片地址

}
