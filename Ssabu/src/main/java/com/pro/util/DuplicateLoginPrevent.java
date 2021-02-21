package com.pro.util;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import org.springframework.stereotype.Component;

@Component("DuplicateLoginPrevent")
public class DuplicateLoginPrevent implements HttpSessionBindingListener {
	private static Hashtable<String,Object> userList=new Hashtable<>();
	
	public void setSession(HttpSession ses,String userid) {
		userList.put(ses.getId(), userid);
		System.out.println("getId==="+ses.getId());
		System.out.println(userList.toString());
	}
	
	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		System.out.println("들어왔어요");
		System.out.println(userList.toString());
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		userList.remove(event.getSession().getId());
		System.out.println("지워졌어요");
		System.out.println(userList.toString());

	}
	public boolean isUsing(String userID, HttpSession session){
        boolean isUsing = false;
        Enumeration e = userList.keys();
        String key = "";
        while(e.hasMoreElements()){
             key = (String)e.nextElement();
             if(!key.equals(session.getId()) && userID.equals(userList.get(key))){
                 isUsing = true;         
             }
        }
        System.out.println("is Using " + isUsing);
        return isUsing;
	}
	public boolean removeLoginInfo(HttpSession session, String userID){
        
        Iterator<Map.Entry<String, Object>> itr = userList.entrySet().iterator();
        String sessionId = session.getId();
        boolean result=false;
        while(itr.hasNext()){
            Map.Entry<String, Object> data = itr.next();
            System.out.println("data =====> " + data);
            if(data.getValue().equals(userID)){
                System.out.println(data);
                System.out.println("user list " + userList);
                result=true;
                return result;
            }
        } 
        return result;
    }
}
