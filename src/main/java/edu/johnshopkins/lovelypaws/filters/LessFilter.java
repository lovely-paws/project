package edu.johnshopkins.lovelypaws.filters;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.lesscss.LessCompiler;
import org.lesscss.LessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class LessFilter implements Filter {
    private ReentrantLock lock = new ReentrantLock();
    private Map<String, String> cssMap = new HashMap<>();
    private static final Logger log = LoggerFactory.getLogger(LessFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String url = ((HttpServletRequest)request).getRequestURL().toString();
        if(!cssMap.containsKey(url)) {
            try {
                lock.lock();
                try {
                    if (!cssMap.containsKey(url)) {
                        String cssFile = StringUtils.substringAfter(url, "/css/");
                        String cssFileContents = FileUtils.readFileToString(
                                new File(request.getServletContext().getRealPath("css/" + cssFile)).getAbsoluteFile());
                        cssMap.put(url, new LessCompiler().compile(cssFileContents));
                    }
                } catch (LessException lessException) {
                    log.error("Failed to compile LESS stylesheet.", lessException);
                }
            } finally {
                lock.unlock();
            }
        }

        response.getOutputStream().print(cssMap.get(url));
    }

    @Override
    public void destroy() {

    }
}
