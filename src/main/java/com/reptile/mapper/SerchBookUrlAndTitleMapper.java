package com.reptile.mapper;

import com.reptile.entity.HomePage;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository
public interface SerchBookUrlAndTitleMapper {
        int saveUrlAndTitle(List<HomePage> homePage);
        List<HomePage> serchAll();
        HomePage serchByName(String name);
        int clearIndex();
        int clearTransition();
        int clearDetails();
        int insertGetBook(HomePage homePage);
        HomePage serchByChapterName(String name);
}
