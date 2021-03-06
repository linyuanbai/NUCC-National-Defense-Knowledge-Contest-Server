package org.fh.config;

import org.fh.plugins.bulletchat.BulletChatServer;
import org.fh.plugins.websocketInstantMsg.ChatServer;
import org.fh.plugins.websocketOnline.OnlineChatServer;
import org.fh.util.Const;
import org.fh.util.IniFileUtil;
import org.fh.util.PathUtil;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 说明：web服务器启动后立即执行
 * 作者：FH Admin Q313596790
 * 官网：www.fhadmin.org
 */
@Component
@Order(value = 1) // 1 代表启动顺序
public class StartWebsocketServer implements ApplicationRunner{
	
	@Override
    public void run(ApplicationArguments var1) throws Exception{
		startWebsocketOnline();		//启动在线管理服务
		startWebsocketInstantMsg();	//启动即时聊天服务
		startWebsocketBulletChat();	//启动视频弹幕服务
		System.out.println("-------------------系统启动成功-------------------");
    }
	
	/**
	 * 启动在线管理服务
	 */
	public void startWebsocketOnline(){
		OnlineChatServer s;
		try {
			String infFilePath = PathUtil.getClasspath()+Const.SYSSET;											//配置文件路径
			String onlinePort = IniFileUtil.readCfgValue(infFilePath, "SysSet1", "onlinePort", "8869");			//在线管理端口
			s = new OnlineChatServer(Integer.parseInt(onlinePort));
			s.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 启动即时聊天服务
	 */
	public void startWebsocketInstantMsg(){
		ChatServer s;
		try {
			String infFilePath = PathUtil.getClasspath()+Const.SYSSET;											//配置文件路径
			String imPort = IniFileUtil.readCfgValue(infFilePath, "SysSet1", "imPort", "8879");					//即时通讯端口
			s = new ChatServer(Integer.parseInt(imPort));
			s.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 启动视频弹幕服务
	 */
	public void startWebsocketBulletChat(){
		BulletChatServer s;
		try {
			String infFilePath = PathUtil.getClasspath()+Const.SYSSET;											//配置文件路径
			String bcPort = IniFileUtil.readCfgValue(infFilePath, "SysSet1", "bcPort", "8859");					//即时通讯端口
			s = new BulletChatServer(Integer.parseInt(bcPort));
			s.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
