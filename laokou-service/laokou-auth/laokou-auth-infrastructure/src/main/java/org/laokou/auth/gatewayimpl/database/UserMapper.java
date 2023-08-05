package org.laokou.auth.gatewayimpl.database;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.laokou.auth.gatewayimpl.database.dataobject.UserDO;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

    UserDO getUserByUsername(@Param("username")String username,@Param("tenantId")Long tenantId,@Param("type")String type);

}
