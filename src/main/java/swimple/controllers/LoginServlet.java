package swimple.controllers;

import swimple.models.Training;
import swimple.services.TrainingService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "login", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    @Inject
    private TrainingService trainingService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Training> trainings = trainingService.getAll();

        req.setAttribute("blubs", trainings);

        req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("email");
        req.setAttribute("currentUser", userName);
        req.getRequestDispatcher("/WEB-INF/pages/success.jsp").forward(req, resp);
    }
}
