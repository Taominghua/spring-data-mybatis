package com.enterprise.data.factory;

import com.enterprise.data.interceptor.PageableAndSortInterceptor;
import com.enterprise.data.type.DBEnum;
import com.enterprise.data.type.DBEnumTypeHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.ResolverUtil;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.mybatis.spring.SqlSessionFactoryBean;

import java.lang.reflect.Modifier;
import java.util.*;

import static java.util.stream.Collectors.toList;

/**
 * @author tommy
 */
@Slf4j
public class EnterpriseSqlSessionFactory extends SqlSessionFactoryBean {

    private Map<Class, Class> interfaceTypeHandlerMap = new HashMap<Class, Class>() {{
        put(DBEnum.class, DBEnumTypeHandler.class);
    }};

    private List<String> scanPackages = new ArrayList<>();

    public void configBeforeXmlBuilderParse(Configuration config) {
        // add interceptor
        config.addInterceptor(new PageableAndSortInterceptor());

        // add type handler
        TypeHandlerRegistry typeHandlerRegistry = config.getTypeHandlerRegistry();

        typeHandlerRegistry.register("com.enterprise.data.type");

        scanPackages.forEach(pack -> {
            interfaceTypeHandlerMap.forEach((k, v) -> {
                ResolverUtil<Class<?>> resolverUtil = new ResolverUtil<>();
                resolverUtil.find(new ResolverUtil.IsA(k), pack);

                log.debug("Find {}, for {}, in {}.", k, v, pack);

                resolverUtil.getClasses().forEach(c -> {
                    log.debug("Register {}.", c);

                    if (!c.isAnonymousClass() && !c.isInterface() && !Modifier.isAbstract(c.getModifiers())) {
                        typeHandlerRegistry.register(c, v);
                    }
                });
            });
        });
    }

    public void addScanInterfacePackages(String packages) {
        if (packages != null && packages.trim().length() > 0) {
            scanPackages.addAll(Arrays.asList(packages.split(";")).stream().map(x -> x.trim()).filter(y -> y.length() > 0).collect(toList()));
        }
    }

    public void addInterfaceTypeHandler(Class<?> javaTypeClass, Class<?> typeHandlerClass) {
        interfaceTypeHandlerMap.put(javaTypeClass, typeHandlerClass);
    }
}
