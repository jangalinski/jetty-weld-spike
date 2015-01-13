package com.github.jangalinski;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHandler;
import org.jboss.weld.environment.servlet.BeanManagerResourceBindingListener;
import org.jboss.weld.environment.servlet.Listener;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Main {

    public static void main(String... args) throws Exception {

        final Server server = new Server(2345);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);

        context.addEventListener(new BeanManagerResourceBindingListener());
        context.addEventListener(new Listener());


        context.addServlet(HelloServlet.class, "/*");


        server.start();
        server.join();
    }

    public static class Bar {
        public String name() {
            return "foo";
        }
    }


    public static class HelloServlet extends HttpServlet {

        @Inject
        private Bar bar;

        @Override
        protected void doGet(HttpServletRequest request,
                             HttpServletResponse response) throws ServletException,
            IOException {
            response.setContentType("text/html");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println("<h1>Hello from HelloServlet</h1>" + bar.name());
        }
    }
}
