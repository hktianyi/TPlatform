package org.tplatform.common;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * Created by Colin on 2016/11/18.
 */
@NoRepositoryBean
public interface BaseRepo<E> extends JpaRepository<E, Serializable>, JpaSpecificationExecutor<E> {
}
