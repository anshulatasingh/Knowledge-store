package com.backend.dao.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.backend.models.InfoVo;
import com.backend.models.NodeVo;
import com.util.TableManagerUtil;

public class InfoDaoImplTest {

	private InfoDaoImpl infoImpl;
	
	private NodeDaoImpl nodeImpl;

	@Before
	public void setup() {
		TableManagerUtil.initTable();
		infoImpl = new InfoDaoImpl();
		nodeImpl=new NodeDaoImpl();
	}

	@After
	public void clear() {
		infoImpl = null;
		nodeImpl=null;
	}

	@Test
	public void test() {
		int id=1;
		NodeVo nodeVo=nodeImpl.getNodeById(id);
		if(nodeVo==null){
			nodeVo=new NodeVo(id, 0, "Dump", true);
			nodeImpl.addNode(nodeVo);
		}
		InfoVo infoData = new InfoVo(1, nodeVo.getNodeId(), "1st April", "ROOT", "TESTDATA");
		infoImpl.addInfo(infoData);
		
	}

	@Test
	public void testRetrive() {
		infoImpl.getAllInfo().forEach(vo -> System.out.println(vo));
	}

}
