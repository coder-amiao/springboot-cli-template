package cn.soboys.springbootjpa.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 公众号 程序员三时
 * @version 1.0
 * @date 2023/7/20 14:30
 * @webSite https://github.com/coder-amiao
 */
@Service
@SuppressWarnings("unchecked")
public class ServiceImpl<R extends BaseRepository<T, ID>, T, ID> implements IService<T, ID> {

    @Autowired
    private R baseRepository;

    @Override
    public T save(T entity) {
        return baseRepository.save(entity);
    }
}
