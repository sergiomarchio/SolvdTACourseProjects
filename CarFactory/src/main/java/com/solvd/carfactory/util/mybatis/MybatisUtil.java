package com.solvd.carfactory.util.mybatis;

import com.solvd.carfactory.dao.IBaseDAO;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Proxy;

public class MybatisUtil {
    private final static Logger LOGGER = Logger.getLogger(MybatisUtil.class);
    private static SqlSessionFactory sqlSessionFactory = null;

    private MybatisUtil(){}

    public static SqlSessionFactory getSSF(){
        if (sqlSessionFactory == null){
            try (InputStream is = Resources.getResourceAsStream("mybatis-config.xml")){
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
            } catch (IOException e) {
                LOGGER.error("Error loading mybatis config file!\n" + e);
            }
        }
        return sqlSessionFactory;
    }

    // Wraps iClass (interface) methods with openSession inside try() and session.commit();
    public static <T extends IBaseDAO<?>> T getIDao(Class<T> iClass){
        Object o = Proxy.newProxyInstance(iClass.getClassLoader(),
                new Class[]{iClass},
                new MybatisMapperHandler());

        return iClass.cast(o);
    }
}
