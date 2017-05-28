package com.backend.dao.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.backend.models.NodeVo;

public class NodeDaoImplTest {
	NodeDaoImpl nodeImpl;

	@Before
	public void setup() {
		nodeImpl = new NodeDaoImpl();
	}

	@After
	public void clear() {
		nodeImpl = null;
	}

	@Test
	public void testaddNode() throws IOException {
		File f = new File(this.getClass().getClassLoader().getResource("anshu/data.txt").getPath());
		FileReader fr = new FileReader(f);
		BufferedReader br = new BufferedReader(fr);
		String line = null;
		while ((line = br.readLine()) != null) {
			// System.out.println(line);
			String[] rawdata = line.split(",");
			NodeVo nvo = new NodeVo();
			nvo.setNodeId(Integer.parseInt(rawdata[0]));
			nvo.setParentId(Integer.parseInt(rawdata[1]));
			nvo.setLabel(rawdata[2]);
			nvo.setLeaf(Boolean.getBoolean(rawdata[3]));
			nodeImpl.addNode(nvo);
		}
		br.close();

	}

	@Test
	public void testRetrive() throws IOException {
System.out.println(nodeImpl.getNodeById(2));
	}
	
	@Test
	public void testdeleteAll() throws IOException {
 nodeImpl.deleteAllNode();
 nodeImpl.getAllNode();
	}
	

	@Test
	public void testAllRetrive() throws IOException {
nodeImpl.getAllNode().forEach(nodeVO->System.out.println(nodeVO));
	}

}
