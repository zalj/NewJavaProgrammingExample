package chapter31;

import java.net.*;
/** 
 * 在Eclipse直接运行后，会在项目对应的bin文件夹中生成.class文件，但是直接在最底层目录用java命令
 * 		java IdentifyHostNameIP ... ... ... ...
 * 执行会报如下异常:
 * 		错误: 找不到或无法加载主类
 * 
 * 在cmd按以下步骤
 * 1. cd d:\Eclipse\workspace\javaProgrammingExample\bin
 * 2. java chapter31.IdentifyHostNameIP ... ... ... ...
 * 或者java chapter31/IdentifyHostNameIP ... ... ... ...
 */
public class IdentifyHostNameIP {
	public static void main(String[] args) {
		System.out.println("----------------------------");
		for(int i = 0; i < args.length; i++) {
			try {
				InetAddress address = InetAddress.getByName(args[i]);
				System.out.print("Host name: " + address.getHostName() + "\n");
				System.out.println("IP address: " + address.getHostAddress());
				System.out.println("----------------------------");
			} catch (Exception e) {
				System.err.println("Unknown host or IP address " + args[i]);
			}
		}
	}
}
