package com.dxb.baomidou.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dxb.baomidou.entity.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dxb
 * @since 2019-07-26
 */
public interface IUserService extends IService<User> {

    void save1(User user);

    void save2(User user2);
}
