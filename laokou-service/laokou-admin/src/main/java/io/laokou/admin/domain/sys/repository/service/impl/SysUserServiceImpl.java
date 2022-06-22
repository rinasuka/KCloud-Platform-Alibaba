package io.laokou.admin.domain.sys.repository.service.impl;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.laokou.admin.infrastructure.common.password.PasswordUtil;
import io.laokou.admin.interfaces.dto.UserDTO;
import io.laokou.admin.interfaces.qo.UserQO;
import io.laokou.admin.interfaces.vo.UserVO;
import io.laokou.common.user.UserDetail;
import io.laokou.admin.domain.sys.entity.SysUserDO;
import io.laokou.admin.domain.sys.repository.dao.SysUserDao;
import io.laokou.admin.domain.sys.repository.service.SysUserService;
import io.laokou.common.utils.RedisKeyUtil;
import io.laokou.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * @author Kou Shenhai
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUserDO> implements SysUserService {

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void updateUser(UserDTO dto) {
        String password = dto.getPassword();
        if (StringUtils.isNotBlank(password)) {
            dto.setPassword(PasswordUtil.encode(password));
        }
        this.baseMapper.updateUser(dto);
        //删除用户缓存数据
        redisUtil.delete(RedisKeyUtil.getUserInfoKey(dto.getId()));
    }

    @Override
    public UserDetail getUserDetail(Long id, String username) {
        return this.baseMapper.getUserDetail(id, username);
    }

    @Override
    public String getUsernameByOpenid(String zfbOpenid) {
        return this.baseMapper.getUsernameByOpenid(zfbOpenid);
    }

    @Override
    public IPage<UserVO> getUserPage(IPage<UserVO> page, UserQO qo) {
        return this.baseMapper.getUserPage(page, qo);
    }

    @Override
    public void deleteUser(Long id) {
        this.baseMapper.deleteUser(id);
    }

}
