package org.tplatform.doc;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.tplatform.common.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "T_DOC")
public class Doc extends BaseEntity {

  public Doc(Long id, String title, String summary, String author, String keyword, String imgUrl) {
    this.id = id;
    this.title = title;
    this.summary = summary;
    this.author = author;
    this.keyword = keyword;
    this.imgUrl = imgUrl;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(length = 10)
  protected Long id;

  @Column(length = 32)
  private String title;         //标题
  @Column(length = 200)
  private String summary;       //摘要
  @Column(columnDefinition = "longtext")
  private String context;       //内容
  @Column(length = 8)
  private String type;       		//文章类型
  @Column(length = 32)
  private String author;        //作者
  @Column(length = 64)
  private String keyword;       //关键词
  @Column(length = 200)
  private String originAddress; //来源
  @Column(length = 200)
  private String imgUrl;        //封面图片地址
  @Column(columnDefinition = "datetime", updatable = false)
  private Date createTime;        //发布时间

}
