package com.github.jangalinski;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.AfterBeanDiscovery;
import javax.enterprise.inject.spi.BeforeBeanDiscovery;
import javax.enterprise.inject.spi.Extension;
import javax.enterprise.inject.spi.ProcessAnnotatedType;

public class MyExtension implements Extension {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    void beforeBeanDiscovery(@Observes BeforeBeanDiscovery bbd) {
        logger.error("============================> beginning the scanning process");
    }


    <T> void processAnnotatedType(@Observes ProcessAnnotatedType<T> pat) {
        final String name = pat.getAnnotatedType().getJavaClass().getName();
        logger.error("============================> scanning type: {}", name);

    }

    void afterBeanDiscovery(@Observes AfterBeanDiscovery abd) {
        logger.error("============================> finished the scanning process");

    }

}
