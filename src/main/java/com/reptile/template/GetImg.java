package com.reptile.template;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * @ProjectName: Reptile
 * @ClassName: GetImg
 * @Description: TODO(爬虫模板类)
 * @Author: VTF
 * @create: 2020-03-12 16:44
 */
public class GetImg {

    static String url = "https://pvp.qq.com/web201605/herolist.shtml";

    public static void getImges(String url){
        try {
            Document document = Jsoup.connect(url).userAgent("Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 6.1;Trident/5.0)").get();
            // System.out.println(document);

            // 查找显示图片的ul标签
            Elements selectUL = document.select("[class=herolist clearfix]");
            // 查找在UL标签中查找 li 标签
            Elements seectLI = selectUL.select("li");
            // for循环  遍历出所有的 li 标签,获取详情地址 以及头像图片
            // Element 代表HTML 中显示内容的标签
            for (Element e : seectLI) {
                // 找到英雄详情页的地址
                // 从LI标签中 找到 a 标签  并且获取 里面 href 属性值
                String heroURL = e.select("a").attr("href");

                // 获取英雄名称
                //  .text 是获取标签中的文本内容
                String heroName = e.select("a").text();

                //  System.out.println(heroName +" "+ heroURL);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        getImges(url);
    }
}
