package com.reptile.util;

import com.reptile.mapper.BookMapper;
import com.reptile.util.exception.ApplicationException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
/**
 * @ProjectName: Reptile
 * @ClassName: BookUtil
 * @Description: TODO(书籍详情)
 * @Author: VTF
 * @create: 2020-03-16 10:19
 */
public class BookUtil {

    @Autowired
    private BookMapper bookMapper;

    /**
     * 对新增书籍的详情进行拼接
     * @param url
     * @param <T>
     * @return
     */

    public static <T> Elements bookData(String url){

        if (StringUtils.isRealEmpty(url)){
            throw new ApplicationException(ResponseCode.INPUT_VALUE_IS_NULL);
        }
        Document document = null;
        try {
            document = Jsoup.connect(url).timeout(5000).userAgent("Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 6.1;Trident/5.0)").get();
        } catch (IOException e) {
            throw new ApplicationException(ResponseCode.UNKOWN_EXCEPTION);
        }
        Elements selcetPlate = document.select("[class=update]");
        Elements selcetLi = selcetPlate.select("li");
        return selcetLi;
    }

    public static String bookIntroduction(String url){
        if (StringUtils.isRealEmpty(url)){
            throw new ApplicationException(ResponseCode.INPUT_VALUE_IS_NULL);
        }
        Document document1 = null;
        try {
            document1=Jsoup.connect(url).timeout(5000).userAgent("Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 6.1;Trident/5.0)").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements selcetDP = document1.select("dd > p");

        return (selcetDP.get(1).toString()).substring(3,selcetDP.get(1).toString().length()-4);
    }
}
