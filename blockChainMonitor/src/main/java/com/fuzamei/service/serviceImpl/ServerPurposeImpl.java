package com.fuzamei.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fuzamei.entity.PojoServerPurpose;
import com.fuzamei.entity.ServerPurpose;
import com.fuzamei.mapperInterface.ServerPurposeMapper;
import com.fuzamei.service.ServerPurposeService;
@Service
public class ServerPurposeImpl implements ServerPurposeService{
@Autowired
ServerPurposeMapper serverPurposeMapper;
	@Override
	public boolean insertServerPurpose(ServerPurpose serverPurpose) {
		// TODO Auto-generated method stub
		return serverPurposeMapper.insertServerPurpose(serverPurpose);
	}

	@Override
	public boolean deleteById(int serverPurposeId) {
		// TODO Auto-generated method stub
		return serverPurposeMapper.deleteById(serverPurposeId);
	}

	@Override
	public boolean updateServerPurpose(PojoServerPurpose pojoServerPurpose) {
		// TODO Auto-generated method stub
		return serverPurposeMapper.updateServerPurpose(pojoServerPurpose);
	}

	@Override
	public List<ServerPurpose> selectById(int serverPurposeId) {
		// TODO Auto-generated method stub
		return serverPurposeMapper.selectById(serverPurposeId);
	}

	public List<ServerPurpose> selectAll() {
		// TODO Auto-generated method stub
		return serverPurposeMapper.selectAll();
	}



}
