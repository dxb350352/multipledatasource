package com.dxb.self.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dxb.self.aop.DataSource;
import com.dxb.self.aop.DataSourceContextHolder;
import com.dxb.self.aop.DataSourceEnum;
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
@DataSource(DataSourceEnum.DS1)
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {


    @Override
    public void save1(User user) {
        user.setName(DataSourceContextHolder.getDataSource());
        save(user);
    }

    @Override
    @DataSource(DataSourceEnum.DS2)
    public void save2(User user) {
        user.setName(DataSourceContextHolder.getDataSource());
        save(user);
    }
}
