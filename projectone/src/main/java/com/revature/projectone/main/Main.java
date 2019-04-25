package com.revature.projectone.main;
import com.revature.projectone.dao.ReimbursementDAOImpl;
import com.revature.projectone.dao.ReimbursementDAO;
import com.revature.projectone.beans.*;

public class Main {
	public static void main(String[] args) {
		ReimbursementDAOImpl theDao = new ReimbursementDAOImpl();
		
		//System.out.println(theDao.getApprovedEmployee(364));
		//System.out.println(theDao.getPendingEmployee(220));
		//System.out.println(theDao.getAllEmployee(346));
		System.out.println(theDao.getAllPendingMan(220));
	
}}
