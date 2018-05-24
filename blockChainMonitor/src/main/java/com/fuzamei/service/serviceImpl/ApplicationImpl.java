package com.fuzamei.service.serviceImpl;

import com.fuzamei.entity.Application;
import com.fuzamei.mapperInterface.ApplicationMapper;
import com.fuzamei.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ApplicationImpl implements ApplicationService{
    @Autowired
    ApplicationMapper applicationMapper;

    @Override
    public boolean insertApplication(Application application) {
        return applicationMapper.insertApplication(application);
    }

    @Override
    public boolean deleteByApplicationId(int applicationId) {
        return applicationMapper.deleteByApplicationId(applicationId);
    }

    @Override
    public boolean updateAppMonitor(Application application) {
        return applicationMapper.updateAppMonitor(application);
    }

    @Override
    public boolean updateApplication(Application application) {
        return applicationMapper.updateApplication(application);
    }

    @Override
    public List<Application> selectByProjectId(int projectId) {
        return  applicationMapper.selectByProjectId(projectId);
    }

	@Override
	public List<Application>  selectByApplicationId(int applicationId) {
		// TODO Auto-generated method stub
		return applicationMapper.selectByApplicationId(applicationId);
	}

	@Override
	public boolean updateAppState(Application application) {
		// TODO Auto-generated method stub
		return applicationMapper.updateAppState(application);
	}

	@Override
	public List<Application> selectForMonitor(int projectId) {
		// TODO Auto-generated method stub
		return applicationMapper.selectForMonitor(projectId);
	}

	@Override
	public List<Application> selectAppStatus(int projectId) {
		// TODO Auto-generated method stub
		return applicationMapper.selectAppStatus(projectId);
	}


}
