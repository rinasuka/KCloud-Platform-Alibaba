package io.laokou.datasource.aspect;
import io.laokou.common.constant.Constant;
import io.laokou.common.entity.BasePage;
import io.laokou.common.enums.SuperAdminEnum;
import io.laokou.common.exception.CustomException;
import io.laokou.common.exception.ErrorCode;
import io.laokou.common.user.UserDetail;
import io.laokou.common.utils.HttpContextUtil;
import io.laokou.common.utils.HttpResultUtil;
import io.laokou.datasource.annotation.DataFilter;
import io.laokou.security.feign.auth.AuthApiFeignClient;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;
@Component
@Aspect
public class DataFilterAspect {

    @Autowired
    private AuthApiFeignClient authApiFeignClient;

    @Pointcut("@annotation(io.laokou.datasource.annotation.DataFilter)")
    public void dataFilterPointCut() {

    }

    @Before("dataFilterPointCut()")
    public void dataFilterPoint(JoinPoint point) {
        Object params = point.getArgs()[0];
        if (params != null && params instanceof BasePage) {
            HttpServletRequest request = HttpContextUtil.getHttpServletRequest();
            String Authorization = getAuthorization(request);
            String language = HttpContextUtil.getLanguage();
            String method = request.getMethod();
            String uri = request.getRequestURI();
            HttpResultUtil<UserDetail> result = authApiFeignClient.resource(language, Authorization, uri, method);
            if (!result.success()) {
                throw new CustomException(result.getCode(),result.getMsg());
            }
            UserDetail userDetail = result.getData();
            //如果是超级管理员，不进行数据过滤
            if (userDetail.getSuperAdmin() == SuperAdminEnum.YES.ordinal()) {
                return;
            }
            try {
                //否则进行数据过滤
                BasePage page = (BasePage)params;
                String sqlFilter = getSqlFilter(userDetail, point);
                page.setSqlFilter(sqlFilter);
            }catch (Exception e){}
            return;
        }
        throw new CustomException("服务正在维护，请联系管理员");
    }

    /**
     * 获取数据过滤的SQL
     */
    private String getSqlFilter(UserDetail userDetail, JoinPoint point) throws Exception {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = point.getTarget().getClass().getDeclaredMethod(signature.getName(), signature.getParameterTypes());
        DataFilter dataFilter = method.getAnnotation(DataFilter.class);
        //获取表的别名
        String tableAlias = dataFilter.tableAlias();
        if(StringUtils.isNotBlank(tableAlias)){
            tableAlias +=  ".";
        }
        StringBuilder sqlFilter = new StringBuilder();
        //用户列表
        List<Long> userIds = userDetail.getUsers().stream().map(item -> item.getId()).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(userIds)) {
            throw new CustomException(ErrorCode.NOT_PERMISSIONS);
        }
        sqlFilter.append(" find_in_set(").append(tableAlias).append(dataFilter.userId()).append(",").append("'").append(StringUtils.join(userIds,",")).append("'").append(")");
        return sqlFilter.toString();
    }

    /**
     * 获取请求的token
     */
    private String getAuthorization(HttpServletRequest request){
        //从header中获取token
        String Authorization = request.getHeader(Constant.AUTHORIZATION_HEADER);
        //如果header中不存在Authorization，则从参数中获取Authorization
        if(StringUtils.isBlank(Authorization)){
            Authorization = request.getParameter(Constant.AUTHORIZATION_HEADER);
        }
        return Authorization;
    }

}
