package com.fuzamei.web;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fuzamei.constant.HintEnum;
import com.fuzamei.entity.Server;
import com.fuzamei.entity.ServerPurpose;
import com.fuzamei.service.ServerService;
import com.fuzamei.service.serviceImpl.ServerServiceImpl;
import com.fuzamei.util.PageDTO;
import com.fuzamei.util.ResultResp;
import com.fuzamei.util.ServerUtils;
import com.fuzamei.util.ValidationUtil;

@RestController
@RequestMapping(value = "/hardWare")
public class HardWareAction {

	@Autowired
	private ServerService serverService;

	@Autowired
	private DatagramSocket datagramSocket;

	public DatagramSocket getDatagramSocket() {
		return datagramSocket;
	}

	public void setDatagramSocket(DatagramSocket datagramSocket) {
		this.datagramSocket = datagramSocket;
	}

	byte[] buf = new byte[1024];
	DatagramPacket dp = new DatagramPacket(buf, 1024);

	/**
	 * 创建人 ： 吴少杰 方法用途 ： 获取服务器信息 2018年5月22日 上午9:48:26 传入参数 ： { "serverName":
	 * "serverName", "page":"2", "rows":"1" } 返回结果 ： 注意 ：
	 **/
	@RequestMapping(value = "findHardWareMsg", method = RequestMethod.POST)
	public ResultResp findHardWareMsg(@RequestBody Server server1) throws IOException {
		if (server1.getServerName() != null && server1.getServerName() != "") {
			ValidationUtil.checkNullAndAssignString(server1.getServerName());
		}
		ValidationUtil.checkMinAndAssignInt(server1.getPage(), 1);
		ValidationUtil.checkMinAndAssignInt(server1.getRows(), 1);
		Map<String, Server> map = new HashedMap();
		long startTime = System.currentTimeMillis();
		try {
			while (true) {
				PageDTO p = serverService.findAllServer(server1);
				// 接收数据，放入数据报
				try {
					datagramSocket.setSoTimeout(10000);
					datagramSocket.receive(dp);
				} catch (Exception e) {
					return ResultResp.getResult(HintEnum.QUERY_FAIL.getCode(), true, HintEnum.QUERY_FAIL.getHintMsg(),
							null);
				}
				// 从数据报中取出数据
				String info = new String(dp.getData(), 0, dp.getLength());
				String[] split = info.split("\n");
				String system = ServerUtils.disposeFilesSystem1(split[1]);
				List<Server> allServer = p.getRows();
				String[] split2 = split[4].split(",");
				String menMsg = "大小 :" + Integer.parseInt(split2[0]) + Integer.parseInt(split2[2]) + " 已使用:"
						+ Integer.parseInt(split2[1]) + Integer.parseInt(split2[3]);
				if (allServer.size() != 0) {
					for (Server s : allServer) {
						if (split[0].equals(s.getInIp()) || split[0].equals(s.getOutIp())) {
							s.setInfo("磁盘 " + system + "////cpu " + split[3] + "////内存 " + menMsg);
						}
						map.put(split[0], s);
					}
					System.out.println(info);
					if (map.size() == allServer.size()) {
						List<Server> s = new ArrayList<>();
						for (Map.Entry entry : map.entrySet()) {
							s.add((Server) entry.getValue());
						}
						p.setRows(s);
						return ResultResp.getResult(HintEnum.OPERATION_SUCCESS.getCode(), true,
								HintEnum.OPERATION_SUCCESS.getHintMsg(), p);
					}
					long endTime = System.currentTimeMillis();
					if (endTime - startTime > 3000) {
						List<Server> s = new ArrayList<>();
						for (Map.Entry entry : map.entrySet()) {
							s.add((Server) entry.getValue());
						}
						return ResultResp.getResult(HintEnum.OPERATION_SUCCESS.getCode(), true,
								HintEnum.OPERATION_SUCCESS.getHintMsg(), p);
					}
				} else {
					return ResultResp.getResult(HintEnum.OPERATION_SUCCESS.getCode(), true,
							HintEnum.OPERATION_SUCCESS.getHintMsg(), p);
				}
			}
		} catch (Exception e) {
			return ResultResp.getResult(HintEnum.QUERY_FAIL.getCode(), true, HintEnum.QUERY_FAIL.getHintMsg(), null);
		}

	}

	/**
	 * 创建人 ： 吴少杰 方法用途 ： 添加服务器 2018年5月22日 上午9:54:27 传入参数 ： {
	 * "serverName":"serverName", "company":"company", "outIp":"outIp",
	 * "inIp":"192.168.25.134", "description":"description",
	 * "serverPurposeId":"1"
	 * 
	 * } 返回结果 ： 注意 ：
	 **/
	@RequestMapping(value = "addServer", method = RequestMethod.POST)
	public ResultResp addServer(@RequestBody Server server) {

		List<ServerPurpose> serverPurposes = serverService.findAllServerPurpose();
		// 参数校验

		try {
			boolean bool = serverService.findServerName(server.getServerName());// true
																				// 代表用户名不存在
																				// false代表已存在
			if (!bool) {
				throw new RuntimeException("服务器名称已存在");
			}
			ValidationUtil.checkNullAndAssignString(server.getServerName());
			ValidationUtil.checkNullAndAssignString(server.getCompany());
			ValidationUtil.checkNullAndAssignString(server.getDescription());
			ValidationUtil.checkMinAndAssignInt(server.getServerPurposeId(), 1);
			ValidationUtil.checkMaxAndAssignInt(server.getServerPurposeId(), serverPurposes.size());
			if ((server.getInIp() == null || server.getInIp() == "")
					&& (server.getOutIp() == null || server.getOutIp() == "")) {
				throw new RuntimeException("必须有一个ip地址不为空");
			}
			if (server.getInIp() != null && server.getInIp() != "") {
				ValidationUtil.checkAndAssignIp(server.getInIp());
			}
			if (server.getOutIp() != null && server.getOutIp() != "") {
				ValidationUtil.checkAndAssignIp(server.getOutIp());
			}
		} catch (Exception e) {
			return ResultResp.getResult(HintEnum.VALI_FAIL.getCode(), true, HintEnum.VALI_FAIL.getHintMsg(), null);
		}

		try {
			serverService.addServer(server);
			return ResultResp.getResult(HintEnum.OPERATION_SUCCESS.getCode(), true,
					HintEnum.OPERATION_SUCCESS.getHintMsg(), null);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResultResp.getResult(HintEnum.OPERATION_FAIL.getCode(), true, HintEnum.OPERATION_FAIL.getHintMsg(),
					null);
		}
	}

	/**
	 * 创建人 ： 吴少杰 方法用途 ： 删除服务器 2018年5月22日 上午10:27:34 传入参数 ： 返回结果 ： 注意 ：
	 **/
	@RequestMapping(value = "deleteServer",method = RequestMethod.POST)
	public ResultResp deleteServer(@RequestParam(value="serverId") int serverId) {
		try {
			serverService.deleteServer(serverId);
			return ResultResp.getResult(HintEnum.OPERATION_SUCCESS.getCode(), true,
					HintEnum.OPERATION_SUCCESS.getHintMsg(), null);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResultResp.getResult(HintEnum.OPERATION_FAIL.getCode(), true, HintEnum.OPERATION_FAIL.getHintMsg(),
					null);
		}
	}

	// 查找所有服务器的用途
	@RequestMapping(value = "findAllServerPurpose")
	public ResultResp findAllServerPurpose() {
		try {
			List<ServerPurpose> serverPurposes = serverService.findAllServerPurpose();
			return ResultResp.getResult(HintEnum.QUERY_SUCCESS.getCode(), true, HintEnum.QUERY_SUCCESS.getHintMsg(),
					serverPurposes);
		} catch (Exception e) {
			return ResultResp.getResult(HintEnum.QUERY_FAIL.getCode(), true, HintEnum.QUERY_FAIL.getHintMsg(), null);
		}
	}

	// 查询服务器名字是否重复
	@RequestMapping(value = "findServerName")
	public ResultResp findServerName(@RequestParam(value = "serverName") String serverName) {
		try {
			boolean bool = serverService.findServerName(serverName);// true
																	// 代表用户名不存在
																	// false代表已存在
			return ResultResp.getResult(HintEnum.QUERY_SUCCESS.getCode(), true, HintEnum.QUERY_SUCCESS.getHintMsg(),
					bool);
		} catch (Exception e) {
			return ResultResp.getResult(HintEnum.QUERY_FAIL.getCode(), true, HintEnum.QUERY_FAIL.getHintMsg(), null);
		}
	}

	
	/**
	 * 创建人  ：  吴少杰
	 * 方法用途  ：   更新server
	 * 2018年5月24日 
	 * 下午2:34:34
	 * 传入参数  ：  {
	"serverId":"2",
	"serverName":"qq1e1e",
	"company":"company",
	"outIp":"11",
	"inIp":"",
	"description":"",
	"serverPurposeId":"2"
    }
	 * 返回结果  ：  
	 * 注意  ：  
	 **/
	@RequestMapping(value = "updateServer")
	public ResultResp findServerName(@RequestBody Server server) {

		// 参数校验
		List<ServerPurpose> serverPurposes = serverService.findAllServerPurpose();
		try {
			boolean bool = serverService.findServerName(server.getServerName());// true
																				// 代表用户名不存在
																				// false代表已存在
			if (!bool) {
				throw new RuntimeException("服务器名称已存在");
			}
			ValidationUtil.checkAndAssignInt(server.getServerId());
			if (server.getServerName() != null && server.getServerName() != "") {
				ValidationUtil.checkNullAndAssignString(server.getServerName());
			}
			if (server.getCompany() != null && server.getCompany() != "") {
				ValidationUtil.checkNullAndAssignString(server.getCompany());
			}
			if (server.getDescription() != null && server.getDescription() != "") {
				ValidationUtil.checkNullAndAssignString(server.getDescription());
			}
			if (server.getServerPurposeId() != null) {
				ValidationUtil.checkMinAndAssignInt(server.getServerPurposeId(), 1);
				ValidationUtil.checkMaxAndAssignInt(server.getServerPurposeId(), serverPurposes.size());
			}
			if (server.getOutIp() != null && server.getOutIp() != "") {
				ValidationUtil.checkAndAssignIp(server.getOutIp());
			}
			if (server.getInIp() != null && server.getInIp() != "") {
				ValidationUtil.checkAndAssignIp(server.getInIp());
			}
		} catch (Exception e) {
			return ResultResp.getResult(HintEnum.VALI_FAIL.getCode(), true, HintEnum.VALI_FAIL.getHintMsg(), null);
		}
		try {
			serverService.updateServer(server);
			return ResultResp.getResult(HintEnum.QUERY_SUCCESS.getCode(), true, HintEnum.OPERATION_SUCCESS.getHintMsg(),
					null);
		} catch (Exception e) {
			return ResultResp.getResult(HintEnum.QUERY_FAIL.getCode(), true, HintEnum.OPERATION_FAIL.getHintMsg(),
					null);
		}
	}

}
