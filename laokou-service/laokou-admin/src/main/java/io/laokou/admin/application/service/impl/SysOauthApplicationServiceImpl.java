package io.laokou.admin.application.service.impl;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.laokou.admin.application.service.SysOauthApplicationService;
import io.laokou.admin.domain.sys.entity.SysOauthDO;
import io.laokou.admin.domain.sys.repository.service.SysOauthService;
import io.laokou.admin.domain.sys.repository.service.SysUserService;
import io.laokou.admin.interfaces.dto.SysOauthDTO;
import io.laokou.admin.interfaces.qo.SysOauthQO;
import io.laokou.admin.interfaces.vo.SysOauthVO;
import io.laokou.common.constant.Constant;
import io.laokou.common.exception.CustomException;
import io.laokou.common.user.SecurityUser;
import io.laokou.common.user.UserDetail;
import io.laokou.common.utils.ConvertUtil;
import io.laokou.datasource.annotation.DataFilter;
import io.laokou.datasource.annotation.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import javax.servlet.http.HttpServletRequest;
/**
 * @author Kou Shenhai
 * @version 1.0
 * @date 2022/8/11 0011 上午 9:47
 */
@Service
@Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRES_NEW)
public class SysOauthApplicationServiceImpl implements SysOauthApplicationService {

    @Autowired
    private SysOauthService sysOauthService;

    @Autowired
    private SysUserService sysUserService;

    @Override
    @DataSource("master")
    @DataFilter(tableAlias = "boot_sys_oauth_client_details")
    public IPage<SysOauthVO> queryOauthPage(SysOauthQO qo) {
        IPage<SysOauthVO> page = new Page<>(qo.getPageNum(),qo.getPageSize());
        return sysOauthService.getOauthList(page,qo);
    }

    @Override
    @DataSource("master")
    public Boolean insertOauth(SysOauthDTO dto, HttpServletRequest request) {
        SysOauthDO sysOauthDO = ConvertUtil.sourceToTarget(dto, SysOauthDO.class);
        final int count = sysOauthService.count(Wrappers.lambdaQuery(SysOauthDO.class).eq(SysOauthDO::getDelFlag, Constant.NO).eq(SysOauthDO::getClientId, sysOauthDO.getClientId()));
        if (count > 0) {
            throw new CustomException("应用id已存在，请重新填写");
        }
        sysOauthDO.setCreator(SecurityUser.getUserId(request));
        final UserDetail userDetail = sysUserService.getUserDetail(sysOauthDO.getCreator());
        sysOauthDO.setDeptId(userDetail.getDeptId());
        return sysOauthService.save(sysOauthDO);
    }

    @Override
    @DataSource("master")
    public Boolean updateOauth(SysOauthDTO dto, HttpServletRequest request) {
        final SysOauthDO sysOauthDO = ConvertUtil.sourceToTarget(dto, SysOauthDO.class);
        final int count = sysOauthService.count(Wrappers.lambdaQuery(SysOauthDO.class).eq(SysOauthDO::getDelFlag, Constant.NO).eq(SysOauthDO::getClientId, sysOauthDO.getClientId()).ne(SysOauthDO::getId,dto.getId()));
        if (count > 0) {
            throw new CustomException("应用id已存在，请重新填写");
        }
        sysOauthDO.setEditor(SecurityUser.getUserId(request));
        return sysOauthService.updateById(sysOauthDO);
    }

    @Override
    @DataSource("master")
    public Boolean deleteOauth(Long id) {
        sysOauthService.deleteOauth(id);
        return true;
    }

    @Override
    @DataSource("master")
    public SysOauthVO getOauthById(Long id) {
        return sysOauthService.getOauthById(id);
    }
}