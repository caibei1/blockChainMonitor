package com.fuzamei.mapperInterface;

import com.fuzamei.entity.PojoServerPurpose;
import com.fuzamei.entity.ServerPurpose;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface ServerPurposeMapper {
   boolean insertServerPurpose(ServerPurpose serverPurpose);
   boolean deleteById(@Param("serverPurposeId")int serverPurposeId);
   boolean updateServerPurpose(PojoServerPurpose pojoServerPurpose);
   List<ServerPurpose>  selectById(@Param("serverPurposeId")int serverPurposeId);
   List<ServerPurpose>  selectAll();
}
