package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.TestNG;
import org.testng.xml.XmlGroups;
import org.testng.xml.XmlPackage;
import org.testng.xml.XmlRun;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;


public class MoreGroupsExecutor {

	public void doExecute(String exeParams) {
		// TODO Auto-generated method stub
		System.out.println("Request params: " + exeParams);
		System.out.println("Begin to run test");
		JSONObject jsob = new JSONObject(exeParams);
		JSONArray groupArr = jsob.getJSONArray("Groups");
		JSONArray packArr = jsob.getJSONArray("Packes");
		String suiteName = "MoreGroupsSuite";
		
		Map<String, String> hashMap = new HashMap<String, String>();
		if(exeParams.contains("exModules")){
			String exModules = jsob.getString("ExModules");
			hashMap.put("exModules", exModules);
		}
		XmlSuite suite = new XmlSuite();
		suite.setVerbose(3);
		suite.setName(suiteName);
		
		suite.setParameters(hashMap);
		
		XmlTest xmlTest = new XmlTest(suite);
		xmlTest.setName("TestMoreGroups: cases in jar");
		
		XmlGroups xmlGroups = new XmlGroups();
		XmlRun xmlRun = new XmlRun();
		for(int i=0; i<groupArr.length(); i++){
			JSONObject obj = groupArr.getJSONObject(i);
			xmlRun.onInclude(obj.getString("GroupName"));
		}
		xmlGroups.setRun(xmlRun);
		xmlTest.setGroups(xmlGroups);
		
		List<XmlPackage> packages = new ArrayList<XmlPackage>();
		for(int j=0; j<packArr.length(); j++){
			JSONObject obj2 = packArr.getJSONObject(j);
			XmlPackage xmlPackage = new XmlPackage(obj2.getString("PackName"));
			packages.add(xmlPackage);
		}
		xmlTest.setPackages(packages);
		
		List<XmlSuite> suites = new ArrayList<XmlSuite>();
		suites.add(suite);
		//GroupsTransformer gtf = new GroupsTransformer();
		
		TestNG tng = new TestNG();
		tng.setXmlSuites(suites);
		//tng.setAnnotationTransformer(gtf);
		tng.run();
		System.out.println("Finish running test.");
	}
}
