package me.kn.ecommerce.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class LayoutInterceptor implements HandlerInterceptor {

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView) throws Exception {
        if (modelAndView == null || !modelAndView.hasView()) return;

        String viewName = modelAndView.getViewName();

        if (viewName == null
                || viewName.startsWith("redirect:")
                || viewName.equals("_layout")
                || viewName.equals("error")) {
            return; // ⚠️ bỏ qua layout cho các view đặc biệt
        }

        modelAndView.addObject("content", viewName);
        modelAndView.setViewName("_layout");
    }

}
