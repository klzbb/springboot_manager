package com.konglingzhan.manager.service;

import com.konglingzhan.manager.bean.PageResult;
import com.konglingzhan.manager.bean.User;
import com.konglingzhan.manager.common.RequestHolder;
import com.konglingzhan.manager.dao.RoleMapper;
import com.konglingzhan.manager.dao.UserMapper;
import com.konglingzhan.manager.exception.ParamException;
import com.konglingzhan.manager.param.PageQuery;
import com.konglingzhan.manager.param.UserParam;
import com.konglingzhan.manager.util.BeanValidator;
import org.assertj.core.util.Preconditions;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService{

    @Resource
    private UserMapper userMapper;

    @Resource
    private RoleMapper roleMapper;

//    @Resource
//    private PasswordEncoder passwordEncoder;

//    @Override
//    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        User user = userMapper.selectUserByUsername(s);
//        if (user == null) {
//            throw new UsernameNotFoundException("不存在该用户");
//        }
////        user.setRoles();
//        return  user;
//    }

    @Override
    public int insertUser(UserParam param) {
        BeanValidator.check(param);
        if(checkTelephoneExist(param.getTelephone(),param.getId())){
            throw new ParamException("电话已经被占用");
        }

        if(checkEmailExist(param.getMail(),param.getId())){
            throw new ParamException("邮箱已经被占用");
        }

        String password = param.getPassword();
//        String password = passwordEncoder.encode(param.getPassword());

        User user = User.builder().username(param.getUsername()).telephone(param.getTelephone()).mail(param.getMail()).password(password).dept_id(param.getDeptId()).status(param.getStatus()).remark(param.getRemark()).build();
        user.setOperator(RequestHolder.getCurrentUser().getUsername());
        user.setOperate_ip("127.0.0.1");
        user.setOperate_time(new Date());

        return userMapper.insert(user);
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
        User after = User.builder().id(param.getId()).username(param.getUsername()).telephone(param.getTelephone()).mail(param.getMail()).password(before.getPassword()).dept_id(param.getDeptId()).status(param.getStatus()).remark(param.getRemark()).build();
        after.setOperator(RequestHolder.getCurrentUser().getUsername());
        after.setOperate_ip("127.0.0.1");
        after.setOperate_time(new Date());
        userMapper.updateById(after);
    }


    public boolean checkEmailExist(String mail, Integer userId){
        return userMapper.countByMail(mail,userId) > 0;
    }

    public boolean checkTelephoneExist(String telephone,Integer userId){
        return userMapper.countByTelephone(telephone,userId) > 0;
    }
    @Override
    public List<User> selectAllUser() {
        return userMapper.selectAllUser();
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
    public void delUserById(int id) {
        userMapper.delUserById(id);
    }

    @Override
    public User getUserInfo() {
        int id = RequestHolder.getCurrentUser().getId();
        return userMapper.getUserInfo(id);
    }
}
