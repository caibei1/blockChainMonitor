package com.fuzamei.util;

public class ServerUtils {

	public static final String LINE_SEPARATOR = System.getProperty("line.separator");
	 //处理系统磁盘状态

    /**
     * Filesystem            Size  Used Avail Use% Mounted on
     * /dev/sda3             442G  327G   93G  78% /
     * tmpfs                  32G     0   32G   0% /dev/shm
     * /dev/sda1             788M   60M  689M   8% /boot
     * /dev/md0              1.9T  483G  1.4T  26% /ezsonar
     *
     * @param commandResult 处理系统磁盘状态shell执行结果
     * @return 处理后的结果
     */
    public static String disposeFilesSystem(String commandResult) {
        String[] strings = commandResult.split(" ");

        // final String PATTERN_TEMPLATE = "([a-zA-Z0-9%_/]*)\\s";
        int size = 0;
        int used = 0;
        for (int i = 0; i < strings.length - 1; i++) {
            if (i == 0) continue;

            int temp = 0;
            for (String s : strings[i].split("\\b")) {
                if (temp == 0) {
                    temp++;
                    continue;
                }
                if (!s.trim().isEmpty()) {
                    if (temp == 1) {
                        size += disposeUnit(s);
                        temp++;
                    } else {
                        used += disposeUnit(s);
                        temp = 0;
                    }
                }
            }
        }
        return new StringBuilder().append("大小 ").append(size).append("G , 已使用").append(used).append("G ,空闲")
                .append(size - used).append("G").toString();
    }

    /**
     * 处理单位转换
     * K/KB/M/T 最终转换为G 处理
     *
     * @param s 带单位的数据字符串
     * @return 以G 为单位处理后的数值
     */
    public static double disposeUnit(String s) {

        try {
        	if("0".equals(s)){
        		return 0;
        	}
            s = s.toUpperCase();
            String lastIndex = s.substring(s.length() - 1);
            String num = s.substring(0, s.length() - 1);
            double parseInt = Double.parseDouble(num);
            if (lastIndex.equals("G")) {
                return parseInt;
            } else if (lastIndex.equals("T")) {
                return parseInt * 1024.0;
            } else if (lastIndex.equals("M")) {
                return parseInt / 1024.0;
            } else if (lastIndex.equals("K") || lastIndex.equals("KB")) {
                return parseInt / (1024.0 * 1024.0);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
        return 0;
    }
    
    public static String disposeFilesSystem1(String commandResult) {
        String[] strings = commandResult.split(" ");
        int length = strings.length;
        double total = 0;
        double used = 0;
        if(length>0){
        	for(int i = 8;i<length;i+=6){
        		total += disposeUnit(strings[i]);
        		used += disposeUnit(strings[i+1]);
        	}
        }
        return new StringBuilder().append("大小:").append(total).append("G , 已使用:").append(used).append("G ,空闲:")
                .append(total - used).append("G").toString();
    }
    
}
