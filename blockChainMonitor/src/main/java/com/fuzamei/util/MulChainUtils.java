package com.fuzamei.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MulChainUtils {

	/**
	 * 创建人  ：  吴少杰
	 * 方法用途  ：  判断多条链是否正常
	 * 2018年5月22日 
	 * 下午4:13:19
	 * 传入参数  ：  
	 * 返回结果  ：  key  关联表id   value  0正常  1不正常
	 * 注意  ：  
	 **/
	
	
	/**
	 * Description:修改
	 * 增加判断: 读入高度小于历史高度是的判断（当前读入高度a[0]小于a[1]，高度小于历史高度，有误 直接报错）
	 * 链返回状态  正常1异常0
	 * 添加了项目状态判断  没有异常链  状态为1    异常链个数小于半数大于0 状态为2  大于半数   状态为3
	 * Author : hbj
	 * Date : 2018年5月23日 上午11:29:53
	 */
	public static Map<Integer,Integer> isNormal(int[]arr,int diff){
		int length = arr.length/5;//几条连
		Map<Integer,Integer> map = new HashMap<Integer, Integer>();
		//不正常的链
		List<Integer> notNormal =new ArrayList<>();
		//判断单条链是否正常
		for(int i = 0;i<length;i++){
//			System.out.println("length " + length);
//			System.out.println("a5 " + arr[i*5] + "a6 " + arr[i*5+1]);
			if(arr[0+i*5]<arr[1+i*5]) {
				map.put(i, 0);
				notNormal.add(i);
//				break;
			}else {
				if(arr[i]==arr[i+1]&&arr[i+1]==arr[i+2]&&arr[i+2]==arr[i+3]&&arr[i+3]==arr[i+4]){
					map.put(i, 0);
					notNormal.add(i);
				}
			}
			
		}
		
		//正常的链比较高度
		//先获取正常链的高度
		List<Integer> normal =new ArrayList<>();
		for(int i = 0;i<length;i++){
			if(!notNormal.contains(i)){
				normal.add(i);
			}
		}
		
		if(normal.size()==1){
			map.put(normal.get(0), 1);
		}
		
		if(normal.size()==2){
			if(arr[normal.get(0)*5]-arr[normal.get(1)*5]>diff||arr[normal.get(1)*5]-arr[normal.get(0)*5]>diff){
				map.put(normal.get(0), 0);
				map.put(normal.get(1), 0);
				notNormal.add(normal.get(0));
				notNormal.add(normal.get(1));
			}else{
				map.put(normal.get(0), 1);
				map.put(normal.get(1), 1);
			}
		}
		
		if(normal.size()>2){
			for(int i = 0;i<normal.size();i++){
				int count = 0;
				for(int a : normal){
					if(arr[normal.get(i)*5]-arr[a*5]>diff||arr[a*5]-arr[normal.get(i)*5]>diff){
						count++;
					}
				}
				if(count>=normal.size()/2+1){
					map.put(normal.get(i), 0);
					notNormal.add(normal.get(i));
					//normal.remove(i);
				}else
					map.put(normal.get(i), 1);
			}
		}
		
		if(notNormal.size()>=length/2+1){
			map.put(length+1, 3);
		}else if(notNormal.size()>0 && notNormal.size()<length/2+1){
			map.put(length+1, 2);
		}else{
			map.put(length+1, 1);
		}
		
		return map;
		
	}
}
