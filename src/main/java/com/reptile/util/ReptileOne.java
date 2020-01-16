package com.reptile.util;

import java.util.HashMap;
import java.util.Map;

public class ReptileOne {
    /**
     * 将URL的资源以HTML格式保存在指定文件
     * @param args
     */
    public static void main(String[] args) {

//
//        String url_str = "https://www.23wxc.com/5/5113/";
//        String filepath = "d:/index.html";
//        URL url = null;
//        try {
//            url = new URL(url_str);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//
//
//        CharSetNum charSetNum = new CharSetNum();
//
//        //获取网页的编码格式
//        String charset = charSetNum.getEncodingByMeta(url_str);
//
//            InputStream htm_in = charSetNum.gethim(url);
//
//            //将网页通过指定字符编码格式转换出来
//            String htm_str = charSetNum.InputStream2String(htm_in,charset);
//
//            //解析网页
//            Document doc1= Jsoup.parse(htm_str);
//
////            //将网页保存在指定文件夹
////            charSetNum.saveHtml(filepath,htm_str);
//
//            //获取 首页更新的小说的URL和标题
//            String label="L";
//            List list = charSetNum.getUrlAndTitle(doc1,label);
//        System.out.println(list);
//
////        String htm_book = charSetNum.InputStream2String(htm_in,charset);
//
//
//            System.out.println(doc1.getElementsByClass("L"));
//
//  }
            String host = "http://iploc.market.alicloudapi.com";
            String path = "/v3/ip";
            String method = "GET";
            String appcode = "你自己的AppCode";
            Map<String, String> headers = new HashMap<String, String>();
            //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
            headers.put("Authorization", "APPCODE " + appcode);
            Map<String, String> querys = new HashMap<String, String>();
            querys.put("ip", "114.247.50.2");


            try {
                /**
                 * 重要提示如下:
                 * HttpUtils请从
                 * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
                 * 下载
                 *
                 * 相应的依赖请参照
                 * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
                 */
//                HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
//                System.out.println(response.toString());
                //获取response的body
                //System.out.println(EntityUtils.toString(response.getEntity()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
}
