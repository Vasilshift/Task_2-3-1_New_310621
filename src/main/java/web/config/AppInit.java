package web.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInit extends AbstractAnnotationConfigDispatcherServletInitializer {

    // Метод, указывающий на класс конфигурации
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{
                HiberConfig.class
        };
    }

    // Добавление конфигурации, в которой инициализируем ViewResolver, для корректного отображения jsp.
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{
                WebConfig.class
        };
    }

    /* Данный метод указывает url, на котором будет базироваться приложение */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }


    // this code may be redundant, application works without it
//    @Override
//    public void onStartup(ServletContext aServletContext) throws ServletException {
//        super.onStartup(aServletContext);
//        registerHiddenFieldFilter(aServletContext);
//    }
//    private void registerHiddenFieldFilter(ServletContext aContext) {
//        aContext.addFilter("hiddenHttpMethodFilter",
//                new HiddenHttpMethodFilter()).addMappingForUrlPatterns(null, false, "/*");
//        //aContext.addFilter("characterEncodingFilter", characterEncodingFilter);
//    }

    // this code don't allow at application

//    @Override
//    public void onStartup(ServletContext servletContext) throws ServletException {
//        super.onStartup(servletContext);
//        registerHiddenFieldFilter(servletContext);
//    }
//
//    private void registerHiddenFieldFilter(ServletContext aContext) {
//        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
//        characterEncodingFilter.setEncoding("UTF-8");
//        characterEncodingFilter.setForceEncoding(true);
//
//        aContext.addFilter("hiddenHttpMethodFilter",
//                new HiddenHttpMethodFilter()).addMappingForUrlPatterns(null ,false, "/*");
//    }

}