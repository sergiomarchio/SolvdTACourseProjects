package com.solvd.carfactory.util.mybatis;

import com.solvd.carfactory.dao.IBaseDAO;
import org.apache.ibatis.session.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MybatisMapperHandler implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Class<?> iDaoClass = proxy.getClass().getInterfaces()[0];

        if (!IBaseDAO.class.isAssignableFrom(iDaoClass)) {
            throw new IllegalArgumentException(iDaoClass + " does not implement IBaseDao!");
        }

        try (SqlSession ss = MybatisUtil.getSSF().openSession()) {
            Object oResult = method.invoke(ss.getMapper(iDaoClass), args);
            ss.commit();
            return oResult;
        }
    }
}