package com.seabattle.controllers;

import com.seabattle.models.Game;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

public class GameServlet extends HttpServlet {
    private ConcurrentHashMap<String,Game> games = new ConcurrentHashMap<>();
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username, password, action;
        HttpSession session = request.getSession();
        action = (String)session.getAttribute("action");
        username = (String) session.getAttribute("username");
        password = (String) session.getAttribute("password");
        if(action.equals("creating")) {
            Game game = new Game(8);
            game.setUsername(username);
            game.setPassword(password);
            String key = games.size() + 1 + "";
            games.put(key, game);
            Cookie cookie = new Cookie("gameKey", key);
            response.addCookie(cookie);
            session.setAttribute("action", "nothing");
        }
        request.getRequestDispatcher("/WEB-INF/view/game.jsp").forward(request, response);
    }

    public  void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String action = (String)request.getParameter("reset");
        String key = "";
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("gameKey")) {
                key = cookie.getValue();
            }
        }
        JSONObject myJsonObj = new JSONObject();
        Game game;
        if(action!=null && action.equals("Reset")){
             game = new Game(8);
            games.put(key, game);
            request.getRequestDispatcher("/WEB-INF/view/game.jsp").forward(request, response);
        }else {
            String x, y;
            x = (String) request.getParameter("x");
            y = (String) request.getParameter("y");
            game = games.get(key);
            if (x != null && y != null) {
                game.shoot(Integer.parseInt(x), Integer.parseInt(y));
            }
        }
        myJsonObj.append("battlefield1", game.getBattlefield1());
        myJsonObj.append("battlefield2", game.getBattlefield2());
        myJsonObj.append("myScore", game.getMyScore());
        myJsonObj.append("enemyScore", game.getEnemyScore());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(myJsonObj.toString());
    }
}
