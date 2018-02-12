package cn.jxust.bigdata.hiveudf;

import java.io.IOException;

import org.apache.hadoop.hive.ql.exec.UDF;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/*
 * 解析json
 * {"movie":"1193","rate":"5","timeStamp":"978300760","uid":"1"}
 * 
 * 将解析成的一行赋给新表的四个字段：
 * insert overwrite table t_rating
	select split(parsejson(line),'\t')[0]as movieid,split(parsejson(line),'\t')[1] as rate,split(parsejson(line),'\t')[2] as timestring,split(parsejson(line),'\t')[3] as uid from rat_json limit 10;
 
 *hive中自带解析：select get_json_object(line,'$.movie') as moive,get_json_object(line,'$.rate') as rate  from rat_json limit 10;
 */
public class JsonParser extends UDF{
	public String evaluate(String jsonLine){
		ObjectMapper objectMapper = new ObjectMapper();//使用jkson工具类解析
		MovieRateBean bean;
		try {
			bean = objectMapper.readValue(jsonLine, MovieRateBean.class);//将一行数据解析为MovieRateBean类型
			return bean.toString();
		} catch (Exception e) {
			return "";
		}
		
		
	}
}
