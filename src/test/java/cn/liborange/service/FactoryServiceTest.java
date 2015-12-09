package cn.liborange.service;

import junit.framework.TestCase;

/**
 * Created by liborange on 15/10/7.
 */
public class FactoryServiceTest extends TestCase {

    public void testCreateService() throws Exception {
        FactoryService fac = new FactoryService();
        System.out.println(fac.createService("image").getClass());;
    }
}