package com.reptile.controller;

import com.reptile.service.SerchBookService;
import com.reptile.util.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SearchBookController {

    @Autowired
    SerchBookService serchBookService;

    @RequestMapping(value = "/serch",method = RequestMethod.POST)
    public ResponseData<String> SearchBookUrlAndTitle(@RequestParam(value = "url_str") String url_str){
        ResponseData<String> resp=new ResponseData<>();
        serchBookService.SerchBookUrlAndTitle(url_str);

        resp.ok();
        return resp;
    }

    @RequestMapping(value = "/getbook",method = RequestMethod.POST)
    public void getBookContent(String name){
        serchBookService.getBookContent(name);

    }

    @RequestMapping(value = "/claerall",method = RequestMethod.POST)
    public ResponseData<String> claerAll(@RequestParam(value = "pwd")Long pwd){
        ResponseData<String> resp=new ResponseData<>();
        String result = serchBookService.claerAll(pwd);
        resp.setData(result).ok();
        return  resp;
    }

}
