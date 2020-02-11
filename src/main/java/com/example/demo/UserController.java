package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.RoutesRefreshedEvent;
import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

@Controller
@RestController
public class UserController {
	@Autowired
    RouteLocator routeLocator;
	
	@Autowired
    ApplicationEventPublisher publisher;
	
	
	@RequestMapping("/hello")
    public String hello(String name) {
        return "hello!"+"----"+name;
    }
	
    @RequestMapping("/getAllUser")
    public ArrayList<String> getAllUser() {
        ArrayList<String> list = new ArrayList<>();
        list.add("Benjieming-B");
        list.add("Huangsi-B");
        list.add("Yangyi-B");
        return list;

    }
    
    @RequestMapping("/abc/fast")
    public String fallback(){
        return "请求服务繁忙，请稍后重试provider";
    }
    
    @RequestMapping("/refresh")
    public void refresh() {
        List<Route> list=routeLocator.getRoutes();
        for(Route route:list){
        	String path=route.getPath();
        	String prefix=route.getPrefix();
        }
        RoutesRefreshedEvent routesRefreshedEvent = new RoutesRefreshedEvent(routeLocator);
        publisher.publishEvent(routesRefreshedEvent);
    }
}