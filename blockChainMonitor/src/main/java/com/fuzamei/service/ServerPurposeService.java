package com.fuzamei.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fuzamei.entity.PojoServerPurpose;
import com.fuzamei.entity.ServerPurpose;

public interface ServerPurposeService {
	   boolean insertServerPurpose(ServerPurpose serverPurpose);
	   boolean deleteById(@Param("serverPurposeId")int serverPurposeId);
	   boolean updateServerPurpose(PojoServerPurpose pojoServerPurpose);
	   List<ServerPurpose>  selectById(@Param("serverPurposeId")int serverPurposeId);
	   List<ServerPurpose>  selectAll();
}
