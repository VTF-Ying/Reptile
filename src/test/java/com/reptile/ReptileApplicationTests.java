package com.reptile;

import com.reptile.util.ResponseCode;
import com.reptile.util.exception.ApplicationException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@SpringBootTest
class ReptileApplicationTests {

    @Test
    void contextLoads() {
        Document document = null;
        try {
            document = Jsoup.connect("https://www.23wxc.com").timeout(5000).userAgent("Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 6.1;Trident/5.0)").get();
        } catch (IOException e) {
            throw new ApplicationException(ResponseCode.UNKOWN_EXCEPTION);
        }
        Elements selcetPlate = document.select("[class=update]");
        Elements selcetLi = selcetPlate.select("li");
        List remove = Collections.singletonList(selcetLi.remove(selcetLi.last()));


        System.out.println(selcetLi);
        for (Element element : selcetLi) {
            System.out.println(element.cssSelector());
            // System.out.println((element.select("p")).select("[class=ul1]").text() + " " +(element.select("p")).select("[class=ul1]").select("a").attr("href")+" "+element.select("p:nth-child(3)").text());

            //System.out.println((element.select("p")).select("[class=ul1]").text());
//            String str = (element.select("p")).select("[class=ul1]").text();
//            StringUtils.varLast(str,"]");
//            System.out.println( str.substring(0,StringUtils.varLast(str,"]")+1));
//            String str1 = element.select("a").attr("title").toString();
//            System.out.println(str1.substring(0,StringUtils.varLast(element.select("[class=ul1]").select("a").attr("title"),"txt")));
//
//            System.out.println(element.select("[class=ul1]").select("a").attr("href"));
            //System.out.println(element.select("p:nth-child(3)"));
            // System.out.println(element.select("p"));
        }
    }

}
