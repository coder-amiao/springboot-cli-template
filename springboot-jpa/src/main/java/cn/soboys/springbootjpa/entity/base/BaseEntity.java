package cn.soboys.springbootjpa.entity.base;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * @author 公众号 程序员三时
 * @version 1.0
 * @date 2023/7/20 16:50
 * @webSite https://github.com/coder-amiao
 */
@Data
@MappedSuperclass
@EntityListeners(value = AuditingEntityListener.class)
public abstract class BaseEntity {

    @CreatedDate
    private Date createTime;
    @LastModifiedDate
    private Date updateTime;
}
