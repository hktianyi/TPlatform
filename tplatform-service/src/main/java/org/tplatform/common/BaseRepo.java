package org.tplatform.common;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Created by tianyi on 2016/11/18.
 */
@NoRepositoryBean
public interface BaseRepo<E> extends JpaRepository<E, Long>, JpaSpecificationExecutor<E> {

//  List<E> findWithSql(String sql);
//  List<E> findWithSql(String sql, Object... param);
}
