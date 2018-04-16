package com.enterprise.factory;

import com.enterprise.data.factory.EnterpriseSqlSessionFactory;
import com.enterprise.data.type.DBEnum;
import com.enterprise.testdomain.UserTypeEnum;
import org.apache.ibatis.session.Configuration;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * @author tommy
 */
public class EnterpriseSqlSessionFactoryTest {

    @Test
    public void testRegister() throws Exception {
        EnterpriseSqlSessionFactory ssf = new EnterpriseSqlSessionFactory();

        ssf.addScanInterfacePackages("com.enterprise.testdomain");

        Configuration configuration = new Configuration();
        ssf.configBeforeXmlBuilderParse(configuration);

        assertTrue(configuration.getTypeHandlerRegistry().hasTypeHandler(UserTypeEnum.class));

        DBEnum dbEnum = Enum.valueOf(UserTypeEnum.class,"ADMIN");
        Assert.assertNotNull(dbEnum);
    }
}
