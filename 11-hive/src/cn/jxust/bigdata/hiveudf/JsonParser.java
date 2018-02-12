package cn.jxust.bigdata.hiveudf;

import java.io.IOException;

import org.apache.hadoop.hive.ql.exec.UDF;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/*
 * ����json
 * {"movie":"1193","rate":"5","timeStamp":"978300760","uid":"1"}
 * 
 * �������ɵ�һ�и����±���ĸ��ֶΣ�
 * insert overwrite table t_rating
	select split(parsejson(line),'\t')[0]as movieid,split(parsejson(line),'\t')[1] as rate,split(parsejson(line),'\t')[2] as timestring,split(parsejson(line),'\t')[3] as uid from rat_json limit 10;
 
 *hive���Դ�������select get_json_object(line,'$.movie') as moive,get_json_object(line,'$.rate') as rate  from rat_json limit 10;
 */
public class JsonParser extends UDF{
	public String evaluate(String jsonLine){
		ObjectMapper objectMapper = new ObjectMapper();//ʹ��jkson���������
		MovieRateBean bean;
		try {
			bean = objectMapper.readValue(jsonLine, MovieRateBean.class);//��һ�����ݽ���ΪMovieRateBean����
			return bean.toString();
		} catch (Exception e) {
			return "";
		}
		
		
	}
}
