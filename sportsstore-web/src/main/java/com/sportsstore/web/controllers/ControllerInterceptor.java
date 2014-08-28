package com.sportsstore.web.controllers;


import com.sportsstore.data.contracts.ProductRepository;
import com.sportsstore.models.Cart;
import com.sportsstore.models.CategoryViewModel;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

import static com.sportsstore.web.Helper.toJson;

public class ControllerInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = Logger.getLogger(ControllerInterceptor.class);

    @Inject
    private ProductRepository productRepo;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        try {
            if (!isAjax(request)) {
                String selectedCategory = null;
                Map<String, String> target = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

                if (target != null && !target.isEmpty()) {
                    if (target.containsKey("category")) {
                        selectedCategory = target.get("category");
                    }
                }

                List<String> categories = productRepo.getAllCategories();
                modelAndView.addObject("categoryModel", new CategoryViewModel(selectedCategory, categories));

                Cart cart = (Cart) request.getSession().getAttribute("cart");
                modelAndView.addObject("cart", cart);

                //logger.info(toJson(cart));
            }

        } finally {
            super.postHandle(request, response, handler, modelAndView);
        }
    }

    private boolean isAjax(HttpServletRequest request) {
        return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
    }
}

