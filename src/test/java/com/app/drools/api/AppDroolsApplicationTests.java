package com.app.drools.api;

import com.app.drools.api.listener.TrackingAgendaEventListener;
import com.app.drools.api.model.Product;
import com.app.drools.api.utils.DateFormatter;

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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppDroolsApplicationTests {

	/*@PostConstruct
	void started() {
		TimeZone.setDefault(TimeZone.getTimeZone("Etc/UTC"));
	}*/
	List<Integer> ruleFiredList=new ArrayList<>();
	

    KieServices ks = KieServices.Factory.get();
    KieContainer kContainer = ks.newKieClasspathContainer();
    KieSession kieSession;


    @Before
    public void setup() {
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
        product.setPurchasedDate(DateFormatter.formatDate("12-4-2011"));
        
        kieSession.insert(product);
        
        kieSession.addEventListener(new TrackingAgendaEventListener());
        kieSession.fireAllRules();
        
        kieSession.getAgendaEventListeners().size();
        //ruleFiredList.add(product.getRule());
        System.out.println("the given input with fired rule"+product);
        System.out.println("Rule List:: "+product.getRuleList());
        assertEquals(product.getDiscount(),4);
    }
    @Test
    public void test_discount_for_diamond_withoutDate()
            throws Exception {
        Product product = new Product();
        product.setType("diamond");
        product.setQuality("a");
        product.setMade("uk");
        product.setPrice(100);
        product.setPurchasedDate(DateFormatter.formatDate("12-4-2012"));
        
        kieSession.insert(product);
        
        kieSession.addEventListener(new TrackingAgendaEventListener());
        kieSession.fireAllRules();
        
        kieSession.getAgendaEventListeners().size();
        //ruleFiredList.add(product.getRule());
        System.out.println("the given input with fired rule"+product);
        System.out.println("Rule List:: "+product.getRuleList());
        assertEquals(product.getDiscount(),3);
    }
}
