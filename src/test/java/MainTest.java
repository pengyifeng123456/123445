import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;

public class MainTest {
    @Test
    public void test() {
        JSONArray array = new JSONArray();
        JSONObject object = new JSONObject();
        array.add(object);
        object.put("array",array);
        object.put("count",3);
        System.out.println(object.toJSONString()); //以JSON格式输 出JSONObject字符串
    }
}
