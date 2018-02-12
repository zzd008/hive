package cn.jxust.bigdata.hiveudf;

import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.hive.ql.exec.UDF;

/*
 * hive自定义函数:继承UDF类，重写evaluate方法：函数就是传入一个值，返回一个值
 */
public class ToLowerCase extends UDF{
	public static Map<Integer,String> provinceMap=new HashMap<Integer,String>();
	static{
		provinceMap.put(136, "北京");
		provinceMap.put(137, "上海");
		provinceMap.put(138, "大连");
		provinceMap.put(139, "河北");
	}
	
	//必须是public，不然调不到
	public String evaluate(String field){//转小写 
		if(field==null) return null;
		return field.toLowerCase();
	}
	
	//根据电话号码返回归属地信息 从在evaluate方法
	public String evaluate(int phone){
		String phoneNum=String.valueOf(phone);
		return provinceMap.get(phoneNum.substring(0,3))==null?"huoxing":provinceMap.get(phoneNum.substring(1,3));
	}
	
}
