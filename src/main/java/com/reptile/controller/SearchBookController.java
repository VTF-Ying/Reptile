package com.reptile.controller;

import com.reptile.entity.HomePage;
import com.reptile.service.SerchBookService;
import com.reptile.util.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class SearchBookController {

    @Autowired
    SerchBookService serchBookService;

    @RequestMapping(value = "/serch",method = RequestMethod.POST)
    public ResponseData<String> SearchBookUrlAndTitle(@RequestBody HomePage homePage){
        ResponseData<String> resp=new ResponseData<>();
        serchBookService.SerchBookUrlAndTitle(homePage);

        resp.ok();
        return resp;
    }

    @RequestMapping(value = "/getbook",method = RequestMethod.POST)
    public void getBookContent(@RequestBody HomePage homePage){
        serchBookService.getBookContent(homePage);

    }

    @RequestMapping(value = "/claerall",method = RequestMethod.POST)
    public ResponseData<String> claerAll(){
        ResponseData<String> resp=new ResponseData<>();
        String result = serchBookService.claerAll();
        resp.setData(result).ok();
        return  resp;
    }
}
