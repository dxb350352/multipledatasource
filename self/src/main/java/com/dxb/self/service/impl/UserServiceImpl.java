package com.dxb.self.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dxb.self.entity.User;
import com.dxb.self.mapper.UserMapper;
import com.dxb.self.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author dxb
 * @since 2019-07-26
 */
@Service
@DS("ds1")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {


    @Override
    public void save1(User user) {
        user.setName(DynamicDataSourceContextHolder.peek());
        save(user);
    }

    @Override
    @DS("ds2")
    public void save2(User user) {
        user.setName(DynamicDataSourceContextHolder.peek());
        save(user);
    }
}
