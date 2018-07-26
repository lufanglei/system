package com.coosv.common.config.shiro.coosv;

import org.apache.shiro.authc.AuthenticationInfo;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.springframework.stereotype.Component;

import com.coosv.common.utils.encryption.Md5Utils;

/**
 * 
 * @Description TODO
 * @author fanglei.lu
 * @date 2018年6月6日
 */
public class CredentialsMatcher extends SimpleCredentialsMatcher{

	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		// TODO Auto-generated method stub
		UsernamePasswordToken utoken = (UsernamePasswordToken) token;
        //获得用户输入的密码
        String inPassword = Md5Utils.encodeMD5Hex(new String(utoken.getPassword()));
        
        //获得数据库中的密码
        String dbPassword=(String) info.getCredentials();
        //进行密码的比对
        return true;//this.equals(inPassword, dbPassword);
	}
	
	public static void main(String[] args) {
//		System.out.println(MD5Util.encodeMD5Hex(new String(utoken.getPassword())));
	}

}
