package com.alice.sjqy.view;

import com.alice.sjqy.log.LogCache;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ClassName: ViewController
 * @Description: 迁移页面
 * @Application:
 * @author: 86180
 * @date: 2022/10/27 13:18
 * @version: 1.0
 * @Copyright:
 */
@Controller
@RequestMapping("/")
public class ViewController {

    @Value("${sjqy.title}")
    private String titleInfo;

    @RequestMapping("/alice")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("titleInfo", titleInfo);
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping("/alice/errorInfo")
    public ModelAndView errorHtml(HttpServletRequest request) {
        String taskName = request.getParameter("taskName");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("titleInfo", taskName+"-ERROR");
        modelAndView.addObject("errorInfo", LogCache.getErrorStackHtmlCache(taskName));
        modelAndView.setViewName("error");
        return modelAndView;
    }
}
