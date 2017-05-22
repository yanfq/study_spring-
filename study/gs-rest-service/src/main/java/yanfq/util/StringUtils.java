package yanfq.util;

import org.assertj.core.util.Compatibility;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by yanfq on 17-5-22.
 */
public class StringUtils {

    /**
     * 分割list，比如将一个size为10000的list分割为10个size为1000的list
     * @param dataList
     * @param pointsDataLimit
     * @return
     */
    public static ConcurrentHashMap<String,List<Integer>> splitList(List<Integer> dataList, int pointsDataLimit) {
        //分批处理
        ConcurrentHashMap<String,List<Integer>> map = new ConcurrentHashMap<String,List<Integer>>();
        if (null != dataList && dataList.size() > 0) {
            Integer size = dataList.size();
            //判断是否有必要分批
            if (pointsDataLimit < size) {
                int part = size / pointsDataLimit;//分批数
                System.out.println("共有 ： " + size + "条，！" + " 分为 ：" + part + "批");
                for (int i = 0; i < part; i++) {
                    List<Integer> listPage = dataList.subList(i*pointsDataLimit, (i+1)*pointsDataLimit);
                    map.put(i+"",listPage);
                }
                int last = size % pointsDataLimit;
                if(last > 0){
                    List<Integer> x = dataList.subList(part*pointsDataLimit,size);
                    map.put(part+"",x);
                }
            }
        }
        return map;
    }

    //生成随机数字和字母,
    public static String getStringRandom(int length) {
        //String val = "";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        //参数length，表示生成几位随机数
        for (int i = 0; i < length; i++) {
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            //输出字母还是数字
            if ("char".equalsIgnoreCase(charOrNum)) {
                //输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                sb.append((char) (random.nextInt(26) + temp));
                //val += (char)(random.nextInt(26) + temp);
            } else if ("num".equalsIgnoreCase(charOrNum)) {
                //val += String.valueOf(random.nextInt(10));
                sb.append(String.valueOf(random.nextInt(10)));
            }
        }
        return sb.toString();
    }

    //固定长度字符串自动在前面添加0
    public static String autoAddZero(int length, int sourceData) {
        String number = String.format("%0" + length + "d", sourceData);
        return number;
    }

    //获取随机手机号码
    public static String getTel() {
        String[] telFirst="134,135,136,137,138,139,150,151,152,157,158,159,130,131,132,155,156,133,153".split(",");
        int index=getNum(0,telFirst.length-1);
        String first=telFirst[index];
        String second=String.valueOf(getNum(1,888)+10000).substring(1);
        String thrid=String.valueOf(getNum(1,9100)+10000).substring(1);
        return first+second+thrid;
    }
    public static int getNum(int start,int end) {
        return (int)(Math.random()*(end-start+1)+start);
    }

}
