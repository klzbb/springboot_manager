package com.konglingzhan.manager.service.impl;
import com.konglingzhan.manager.common.authentication.SecurityUser;
import com.konglingzhan.manager.dao.UserRoleMapper;
import com.konglingzhan.manager.dto.LoginUserInfo;
import com.konglingzhan.manager.dto.PageResult;
import com.konglingzhan.manager.dto.UserDto;
import com.konglingzhan.manager.entity.User;
import com.konglingzhan.manager.common.RequestHolder;
import com.konglingzhan.manager.dao.RoleMapper;
import com.konglingzhan.manager.dao.UserMapper;
import com.konglingzhan.manager.common.exception.ParamException;
import com.konglingzhan.manager.param.PageQuery;
import com.konglingzhan.manager.param.UserParam;
import com.konglingzhan.manager.service.UserService;
import com.konglingzhan.manager.util.BeanValidator;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
import com.konglingzhan.manager.util.StringUtil;
import com.konglingzhan.manager.util.UserUtil;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private UserRoleMapper userRoleMapper;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.findByKeyWord(username);
        if (user == null) {
            throw new UsernameNotFoundException("不存在该用户");
        }
        return new SecurityUser(user);
    }

    @Override
    @Transactional
    public void insertUser(UserParam param) {
        // 业务校验
        if(checkTelephoneExist(param.getTelephone(),param.getId())){
            throw new ParamException("电话已经被占用");
        }
        if(checkEmailExist(param.getMail(),param.getId())){
            throw new ParamException("邮箱已经被占用");
        }

        // 新用户保存
        String password = passwordEncoder.encode(param.getPassword());
        User user = User.builder().username(param.getUsername()).telephone(param.getTelephone()).mail(param.getMail()).password(password).deptId(param.getDeptId()).status(param.getStatus()).remark(param.getRemark()).build();
        user.setOperator(UserUtil.getLoginUser().getUsername());
        user.setOperateIp("127.0.0.1");
        user.setOperateTime(new Date());

        // 用户角色关联保存
        List<Integer> roleList = StringUtil.splitToListInt(param.getRolesStr());
        userRoleMapper.insertArr(user.getId(),roleList);
    }


    @Override
    public void updateById(UserParam param) {
        BeanValidator.check(param);
        if(checkTelephoneExist(param.getTelephone(),param.getId())){
            throw new ParamException("电话已经被占用");
        }

        if(checkEmailExist(param.getMail(),param.getId())){
            throw new ParamException("邮箱已经被占用");
        }

        User before = userMapper.selectByPrimaryKey(param.getId());
        Objects.requireNonNull(before,"待更新的用户不存在");
        User after = User.builder().id(param.getId()).username(param.getUsername()).telephone(param.getTelephone()).mail(param.getMail()).password(before.getPassword()).deptId(param.getDeptId()).status(param.getStatus()).remark(param.getRemark()).build();
        after.setOperator("admin");
        after.setOperateIp("127.0.0.1");
        after.setOperateTime(new Date());
        userMapper.updateById(after);
    }


    public boolean checkEmailExist(String mail, Integer userId){
        return userMapper.countByMail(mail,userId) > 0;
    }

    public boolean checkTelephoneExist(String telephone,Integer userId){
        return userMapper.countByTelephone(telephone,userId) > 0;
    }

    @Override
    public PageResult<UserDto> userAll(PageQuery pageQuery) {
        int num = userMapper.countUser();
        List list = userMapper.selectAllUser(pageQuery);
        HashMap result = new HashMap(){
            {
                put("list",list);
            }
        };
        return PageResult.<UserDto>builder().total(num).data(list).result(result).build();
    }

    @Override
    public User selectUserByUsername(String username) {
        return userMapper.selectUserByUsername(username);
    }

    @Override
    public User findByKeyWord (String keyWord){
        return userMapper.findByKeyWord(keyWord);
    }

    @Override
    public List<User> selectByDeptId(String deptId) {
        return userMapper.selectByDeptId(deptId);
    }

    @Override
    public PageResult<User> userList(int deptId, PageQuery pageQuery) {
        BeanValidator.check(pageQuery);
        int num = userMapper.countByDeptId(deptId);

        if(num > 0){
            List list = userMapper.userList(deptId,pageQuery);
            return PageResult.<User>builder().total(num).data(list).build();
        }

        return PageResult.<User>builder().build();

    }

    @Override
    @Transactional
    public void delUserById(int id) {
        userMapper.delUserById(id);
//        userRoleMapper
    }

    @Override
    public LoginUserInfo getUserInfo(int id) {
        return userMapper.getUserInfo(id);
    }
}