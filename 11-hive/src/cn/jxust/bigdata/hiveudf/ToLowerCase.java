package cn.jxust.bigdata.hiveudf;

import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.hive.ql.exec.UDF;

/*
 * hive�Զ��庯��:�̳�UDF�࣬��дevaluate�������������Ǵ���һ��ֵ������һ��ֵ
 */
public class ToLowerCase extends UDF{
	public static Map<Integer,String> provinceMap=new HashMap<Integer,String>();
	static{
		provinceMap.put(136, "����");
		provinceMap.put(137, "�Ϻ�");
		provinceMap.put(138, "����");
		provinceMap.put(139, "�ӱ�");
	}
	
	//������public����Ȼ������
	public String evaluate(String field){//תСд 
		if(field==null) return null;
		return field.toLowerCase();
	}
	
	//���ݵ绰���뷵�ع�������Ϣ ����evaluate����
	public String evaluate(int phone){
		String phoneNum=String.valueOf(phone);
		return provinceMap.get(phoneNum.substring(0,3))==null?"huoxing":provinceMap.get(phoneNum.substring(1,3));
	}
	
}
