package com.reptile.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: reptile
 * @ClassName: loginAspect
 * @Description: TODO(一句话描述该类的功能)
 * @Author: VTF
 * @create: 2020-05-09 18:08
 */
@Aspect
@Component
public class loginAspect {
    @Pointcut("execution(* com.reptile.controller.BookController.*)")
    public void checkAdmin(){

    }
    @Before("checkAdmin()")
    public void ADMIN(){
        //如果没有登录 这里会抛出异常

        System.out.println("拦截成功");

    }
}
