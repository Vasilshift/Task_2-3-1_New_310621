package web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "web")
@EnableTransactionManagement
@PropertySource(value = "classpath:db.properties")

public class HibernamteCofig {

        private Environment environment;

        @Autowired
        public void setEnvironment(Environment environment) {
            this.environment = environment;
        }

        private Properties hibernateProperties() {
            Properties properties = new Properties();
            properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
            properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
            return properties;
        }

        @Bean
        public DataSource dataSource() {
            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName(environment.getRequiredProperty("db.Driver"));
            dataSource.setUrl(environment.getRequiredProperty("db.url"));
            dataSource.setUsername(environment.getRequiredProperty("db.username"));
            dataSource.setPassword(environment.getRequiredProperty("db.password"));
            return dataSource;
        }

        @Bean
        public LocalSessionFactoryBean sessionFactory() {
            LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
            sessionFactory.setDataSource(dataSource());
            sessionFactory.setPackagesToScan("web.model");
            sessionFactory.setHibernateProperties(hibernateProperties());
            return sessionFactory;
        }

        @Bean
        public HibernateTransactionManager transactionManager() {
            HibernateTransactionManager transactionManager = new HibernateTransactionManager();
            transactionManager.setSessionFactory(sessionFactory().getObject());
            return transactionManager;
        }

}
