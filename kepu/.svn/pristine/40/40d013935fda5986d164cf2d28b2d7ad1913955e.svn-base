package com.kepu.pojo.news;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class ZheJiang {
	
	public static final String[] area={"杭州","宁波","温州","嘉兴","湖州","绍兴","金华","衢州","舟山","台州","丽水"};
	
	public final static String[] hangzhou={"上城区","下城区","江干区","拱墅区","西湖区","滨江区","萧山区","余杭区",
		"富阳区","临安区","桐庐县","淳安县","建德市"};
	
	public final static String[] ningbo={"海曙区","江北区","北仑区","镇海区","鄞州区","奉化区","象山县","宁海县",
		"余姚市","慈溪市"};
	
	public final static String[] wenzhou={"鹿城区","龙湾区","瓯海区","洞头区","永嘉县","平阳县","苍南县","文成县",
		"泰顺县","瑞安市","乐清市"};
	
	public final static String[] jiaxing={"南湖区","秀洲区","嘉善县","海盐县","海宁市","平湖市","桐乡市"};
	
	public final static String[] huzhou={"吴兴区","南浔区","德清县","长兴县","安吉县"};
	
	public final static String[] shaoxing={"越城区","柯桥区","新昌县","诸暨市","上虞区","嵊州市"};
	
	public final static String[] jinhua={"婺城区","金东区","武义县","浦江县","磐安县","兰溪市","义乌市","东阳市","永康市"};
	
	public final static String[] quzhou={"柯城区","衢江区","常山县","开化县","龙游县","江山市"};
	
	public final static String[] zhoushan={"定海区","普陀区","岱山县","嵊泗县"};
	
	public final static String[] taizhou={"椒江区","黄岩区","路桥区","玉环市","三门县","天台县","仙居县","温岭市","临海市"};
	
	public final static String[] lishui={"莲都区","青田县","缙云县","遂昌县","松阳县","云和县","庆元县","景宁县","龙泉市"};
	
	public final static String path="http://kppic.appwzd.cn:8088/2017/07/04/53d3a4f6a0e34886bbf7d800bcc02263.jpg";
	
	public  static Object getZheJiang(){
		Map<Integer,String[]> m=new HashMap<Integer, String[]>();
		m.put(1, hangzhou);
		m.put(2, ningbo);
		m.put(3, wenzhou);
		m.put(4, jiaxing);
		m.put(5, huzhou);
		m.put(6, shaoxing);
		m.put(7, jinhua);
		m.put(8, quzhou);
		m.put(9, zhoushan);
		m.put(10, taizhou);
		m.put(11, lishui);
		List<Map<String,Object>> r=new LinkedList<Map<String,Object>>();
		for (int i = 0; i < 11; i++) {
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("id",i+1+"");
			map.put("name", area[i]);
			List<Map<String,Object>> l4=new LinkedList<Map<String,Object>>();
			String[] city=m.get(i+1);
			for (int k = 0; k < city.length; k++){
				Map<String,Object> map3=new HashMap<String, Object>();
				map3.put("id", (i+1)*100+k+1);
				map3.put("name", city[k]);
				map3.put("pic", path);
				l4.add(map3);
			}
			map.put("child", l4);
			r.add(map);
		}
		return r;
	}
}
