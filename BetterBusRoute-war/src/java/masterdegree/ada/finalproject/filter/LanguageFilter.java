/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdegree.ada.finalproject.filter;

import java.io.IOException;
import java.util.Locale;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author angel_banuelos
 */
public class LanguageFilter implements Filter {

    @Override
    public void init(FilterConfig config) throws ServletException {
        //
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        if (request.getParameter("language") == null) {
            String userLocale = request.getHeader("Accept-Language");
            Locale locale = request.getLocale();
            String requestURI = request.getRequestURI();
            if (requestURI.contains("/rs/")) {
                chain.doFilter(req, res);
                return;
            }
            System.out.println("requestURI" + requestURI);
            req.setAttribute("userLocale", userLocale);
            String[] path = requestURI.split("/");

            String[] userLocaleA = userLocale.split(",");
            String uLang = userLocaleA[1].split(";")[0];

            req.getRequestDispatcher(path[path.length - 1] + "?lan=" + uLang).forward(req, res);
        }

    }

    @Override
    public void destroy() {
        //
    }
}
