package com.app.drools.api;

import com.app.drools.api.listener.TrackingAgendaEventListener;
import com.app.drools.api.model.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.KieServices;
import org.kie.api.io.Resource;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppDroolsApplicationTests {

    KieServices ks = KieServices.Factory.get();
    KieContainer kContainer = ks.newKieClasspathContainer();
    KieSession kieSession;


   /* @Before
    public void setup() {
//        Resource dt
//                = ResourceFactory
//                .newClassPathResource("rules/rules.xls",
//                        getClass());
    	kieSession = kContainer.newKieSession("ksession-rule");
    }

    @Test
    public void test_discount_for_diamond()
            throws Exception {
        Product product = new Product();
        product.setType("diamond");
        product.setQuality("a");
        product.setMade("uk");
        product.setPrice(100);
        product.setPurchasedDate(new Date(12-4-2011));
        
        kSession.insert(product);
        
        kSession.addEventListener(new TrackingAgendaEventListener());
        kSession.fireAllRules();
        
        assertEquals(product.getDiscount(),4);
    }*/
}
