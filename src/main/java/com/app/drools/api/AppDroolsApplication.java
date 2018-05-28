package com.app.drools.api;

import java.util.ArrayList;
import java.util.List;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.app.drools.api.listener.TrackingAgendaEventListener;
import com.app.drools.api.model.Product;

@SpringBootApplication
public class AppDroolsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppDroolsApplication.class, args);
		
		List<Integer> ruleFiredList = new ArrayList<>();

		/*FactHandle f1, f2, f3;

		try {
			// load up the knowledge base
			KieServices ks = KieServices.Factory.get();
			KieContainer kContainer = ks.getKieClasspathContainer();
			KieSession kSession = kContainer.newKieSession("ksession-rule");
			Product p1 = new Product();
			p1.setType("diamond");
			p1.setQuality("a");
			p1.setMade("us");

			kSession.insert(p1);
			
			kSession.addEventListener(new TrackingAgendaEventListener());
			kSession.fireAllRules();
			kSession.getAgendaEventListeners().size();
			ruleFiredList.add(p1.getRule());
			System.out.println("The discount for given input : " + p1 + " is " + p1.getDiscount());
			// System.out.println();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		try {
			// load up the knowledge base
			KieServices ks = KieServices.Factory.get();
			KieContainer kContainer = ks.getKieClasspathContainer();
			KieSession kSession = kContainer.newKieSession("ksession-rule");

			Product p2 = new Product();
			p2.setType("diamond");
			p2.setQuality("a");
			p2.setMade("uk");

			f2 = kSession.insert(p2);
			
			kSession.addEventListener(new TrackingAgendaEventListener());
			kSession.fireAllRules();
			ruleFiredList.add(p2.getRule());
			System.out.println("The discount for given input : " + p2 + " is " + p2.getDiscount());
			//for(Integer ruleId: ruleFiredList) {
				System.out.println(ruleFiredList.toString());
			//}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}*/
	}

	@Bean
	public KieContainer kieContainer() {
		return KieServices.Factory.get().getKieClasspathContainer();
	}
}
