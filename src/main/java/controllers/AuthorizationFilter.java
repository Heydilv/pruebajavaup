/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "AuthFilter", urlPatterns = {"*.xhtml"})
public class AuthorizationFilter implements Filter {

    public AuthorizationFilter() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        try {

            HttpServletRequest reqt = (HttpServletRequest) request;
            HttpServletResponse resp = (HttpServletResponse) response;
            HttpSession ses = reqt.getSession(false);

            String reqURI = reqt.getRequestURI();
            System.out.println(reqURI);
            
            // Se el usuario ya inicio session, y quiere ir al login, se lo redirege al app.xhtml
            if ((reqURI.equals("/compartirinfo/") || reqURI.equals("/compartirinfo/faces/index.xhtml")) && (ses != null && ses.getAttribute("username") != null) ) {
                resp.sendRedirect(reqt.getContextPath() + "/faces/app.xhtml");
                System.out.println("send to app.xhtml" + ses.getAttribute("username"));
                
            } else if (reqURI.contains("/index.xhtml")
                    || (ses != null && ses.getAttribute("username") != null)
                    || reqURI.indexOf("/public/") >= 0
                    || reqURI.contains("javax.faces.resource")) {
                
                chain.doFilter(request, response);
            } else {
                // si no ha iniciado session se lo redirige a el login
                resp.sendRedirect(reqt.getContextPath() + "/faces/index.xhtml");
            }
        } catch (Exception e) {
            System.out.println("error");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void destroy() {

    }
}
