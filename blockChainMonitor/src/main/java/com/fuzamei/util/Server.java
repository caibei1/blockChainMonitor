package com.fuzamei.util;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Server {

	public static void main(String[] args) throws Exception {

		
		
		DatagramSocket ds = null;
		DatagramPacket dp = null;

		// �������Ͷ���
		ds = new DatagramSocket();
		// �����ݴ��-->������ݱ�
		Map<String,String> map = new HashMap<>();
		while (true) {
			try {
				String shpath = "/root/sh/a.sh";//�ű�����ip  ���̿ռ�
				Process ps = Runtime.getRuntime().exec(shpath);
				ps.waitFor();

				BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream()));
				StringBuffer sb = new StringBuffer();
				String line;
				while ((line = br.readLine()) != null) {
					sb.append(line).append("\n");
				}
				
				float cpuInfo = getCpuInfo();
				sb.append(cpuInfo+"\n");
				int[] memInfo = getMemInfo();
				for(int i = 0;i<memInfo.length;i++){
					sb.append(memInfo[i]+",");
				}
				String info = sb.toString();
				dp = new DatagramPacket(info.getBytes(), info.length(),new InetSocketAddress("192.168.25.5", 3333));
				// �������ݱ�
				ds.send(dp);
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	
	
	
	//������  ���ڸ�Ŀ¼
	public static int[] getMemInfo() throws IOException, InterruptedException   
	{   
	File file = new File("/proc/meminfo");   
	BufferedReader br = new BufferedReader(new InputStreamReader(   
	new FileInputStream(file)));   
	int[] result = new int[4];   
	String str = null;   
	StringTokenizer token = null;   
	while((str = br.readLine()) != null)   
	{   
	token = new StringTokenizer(str);   
	if(!token.hasMoreTokens())   
	continue;   
	  
	str = token.nextToken();   
	if(!token.hasMoreTokens())   
	continue;   
	  
	if(str.equalsIgnoreCase("MemTotal:"))   
	result[0] = Integer.parseInt(token.nextToken());   
	else if(str.equalsIgnoreCase("MemFree:"))   
	result[1] = Integer.parseInt(token.nextToken());   
	else if(str.equalsIgnoreCase("SwapTotal:"))   
	result[2] = Integer.parseInt(token.nextToken());   
	else if(str.equalsIgnoreCase("SwapFree:"))   
	result[3] = Integer.parseInt(token.nextToken());   
	}   
	  
	return result;   
	}   
	  
	/**  
	* get memory by used info  
	*  
	* @return float efficiency  
	* @throws IOException  
	* @throws InterruptedException  
	*/   
	public static float getCpuInfo() throws IOException, InterruptedException   
	{   
	File file = new File("/proc/stat");   
	BufferedReader br = new BufferedReader(new InputStreamReader(   
	new FileInputStream(file)));   
	StringTokenizer token = new StringTokenizer(br.readLine());   
	token.nextToken();   
	int user1 = Integer.parseInt(token.nextToken());   
	int nice1 = Integer.parseInt(token.nextToken());   
	int sys1 = Integer.parseInt(token.nextToken());   
	int idle1 = Integer.parseInt(token.nextToken());   
	  
	Thread.sleep(1000);   
	  
	br = new BufferedReader(   
	new InputStreamReader(new FileInputStream(file)));   
	token = new StringTokenizer(br.readLine());   
	token.nextToken();   
	int user2 = Integer.parseInt(token.nextToken());   
	int nice2 = Integer.parseInt(token.nextToken());   
	int sys2 = Integer.parseInt(token.nextToken());   
	int idle2 = Integer.parseInt(token.nextToken());   
	  
	return (float)((user2 + sys2 + nice2) - (user1 + sys1 + nice1)) / (float)((user2 + nice2 + sys2 + idle2) - (user1 + nice1 + sys1 + idle1));   
	}   
}
