package com.enterprise.factory;

import com.enterprise.data.factory.EnterpriseSqlSessionFactory;
import com.enterprise.testdomain.UserTypeEnum;
import org.apache.ibatis.session.Configuration;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * @author tommy
 */
public class EnterpriseSqlSessionFactoryTest {

    @Test
    public void testRegister() throws Exception {
        EnterpriseSqlSessionFactory ssf = new EnterpriseSqlSessionFactory();

        ssf.addScanInterfacePackages("com.edol.testdomain");

        Configuration configuration = new Configuration();
        ssf.configBeforeXmlBuilderParse(configuration);

        assertTrue(configuration.getTypeHandlerRegistry().hasTypeHandler(UserTypeEnum.class));
    }
}
