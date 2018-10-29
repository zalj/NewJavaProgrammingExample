package chapter31;

import java.net.*;
/** 
 * ��Eclipseֱ�����к󣬻�����Ŀ��Ӧ��bin�ļ���������.class�ļ�������ֱ������ײ�Ŀ¼��java����
 * 		java IdentifyHostNameIP ... ... ... ...
 * ִ�лᱨ�����쳣:
 * 		����: �Ҳ������޷���������
 * 
 * ��cmd�����²���
 * 1. cd d:\Eclipse\workspace\javaProgrammingExample\bin
 * 2. java chapter31.IdentifyHostNameIP ... ... ... ...
 * ����java chapter31/IdentifyHostNameIP ... ... ... ...
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
