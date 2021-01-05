package com.myclass.filter;

import com.myclass.common.RoleConstant;
import com.myclass.common.SessionConstant;
import com.myclass.common.UrlConstant;
import com.myclass.dto.UserLoginDto;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * Author: Nguyễn Chánh Trực
 * Created: 1/4/2021
 * Updated: 1/4/2021
 *
 * Filter request base on Role of user
 *             |                 User                 |             Job                      |             Task              |
 *             | index | add | update | delete | info | index | add | update | delete | info | index | add | update | delete |
 * - USER:     |       |     |        |        |   x  |       |     |        |        |      |   x   |     |   xx   |        |
 * - ADMIN:    |   x   |  x  |   x    |   x    |   x  |   x   |  x  |   x    |   x    |  x   |   x   |  x  |   x    |    x   |
 * - MANAGER:  |   x   |     |        |        |   x  |   x   |  x  |   x    |   x    |  x   |   x   |  x  |   x    |    x   |
 */
public class AuthFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Get contextPath
        String contextPath = request.getContextPath();
        // Bypass if go to login
        if (request.getServletPath().equals(UrlConstant.URL_LOGIN)) {
            filterChain.doFilter(request,response);
            return;
        }
        // Get session
        HttpSession session = request.getSession();
        // Get userLoginDto
        UserLoginDto userLoginDto = (UserLoginDto) session.getAttribute(SessionConstant.USER_LOGIN);
        // Redirect to login page if userLoginDto is null
        if (userLoginDto == null) {
            response.sendRedirect(contextPath + UrlConstant.URL_LOGIN);
            return;
        }

        // --------- Permission Check ---------
        // Get uri
        String uri = request.getServletPath();

        switch (userLoginDto.getRoleName()) {
            case RoleConstant.ROLE_MANAGER:
                System.out.printf("ROlE_MANAGER: %s\n", uri);
                Pattern managerPattern = Pattern.compile("^/(((user)\\/(add|edit|delete))|(role))");
                if (managerPattern.matcher(uri).find()) {
                    response.sendRedirect(contextPath + UrlConstant.URL_403_ERROR);
                    return;
                }
                break;
            case RoleConstant.ROLE_USER:
                System.out.printf("ROlE_USER: %s\n", uri);
                // Process exception for /user/info
                if (uri.startsWith("/user/info")) {
                    break;
                }
                Pattern userPattern = Pattern.compile("^/((user)|(role)|((task)\\/(add|delete))|(job))");
                if (userPattern.matcher(uri).find()) {
                    response.sendRedirect(contextPath + UrlConstant.URL_403_ERROR);
                    return;
                }
                break;
            case RoleConstant.ROLE_ADMIN:
                System.out.printf("ROlE_ADMIN: %s\n", uri);
            default:
                break;
        }
        filterChain.doFilter(request,response);

    }

}
