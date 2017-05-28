package com.backend.dao.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.backend.models.InfoVo;
import com.util.TableManagerUtil;

public class InfoDaoImplTest {

	private InfoDaoImpl infoImpl;
	@Before
	public void setup() {
		TableManagerUtil.initTable();
		infoImpl = new InfoDaoImpl();
	}

	@After
	public void clear() {
		infoImpl = null;
	}
	
	
	@Test
	public void test() {
		InfoVo infoData=new InfoVo(1, 1, "1st April", "ROOT", "TESTDATA");
		
	infoImpl.addInfo(infoData)	;
	}
	
	@Test
	public void testRetrive() {
		infoImpl.getAllInfo().forEach(vo->System.out.println(vo));
	}
	
	
}
