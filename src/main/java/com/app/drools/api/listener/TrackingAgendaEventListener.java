/**
 * 
 */
package com.app.drools.api.listener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.drools.core.event.DefaultAgendaEventListener;
import org.kie.api.definition.rule.Rule;
import org.kie.api.event.rule.AfterMatchFiredEvent;
import org.kie.api.runtime.rule.Match;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.app.drools.api.model.Product;

/**
 * @author Tabrez
 *
 */

//@Component
public class TrackingAgendaEventListener extends DefaultAgendaEventListener {

	private static Logger log = LoggerFactory.getLogger(TrackingAgendaEventListener.class);

	private List<Match> matchList = new ArrayList<Match>();
	
	private List<Integer> ruleId = new ArrayList<>();

	@Override
	public void afterMatchFired(AfterMatchFiredEvent event) {
		Rule rule = event.getMatch().getRule();
		
		String ruleName = rule.getName();
		String idName=rule.getId();

		Map<String, Object> ruleMetaDataMap = rule.getMetaData();

		matchList.add(event.getMatch());
		
		List<Object> objects = event.getMatch().getObjects();
		for(Object o: objects) {
			Product p = (Product)o;
			//System.out.println(p.toString());
			ruleId.add(p.getRule());
			//System.out.println("Rule:" +ruleId);
		}
		//System.out.println(matchList.toString());
		StringBuilder sb = new StringBuilder("Rule fired: " + ruleName);
		

		if (ruleMetaDataMap.size() > 0) {
			sb.append("\n  With [" + ruleMetaDataMap.size() + "] meta-data:");
			for (String key : ruleMetaDataMap.keySet()) {
				sb.append("\n    key=" + key + ", value=" + ruleMetaDataMap.get(key));
			}
		}
		
	//System.out.println(sb.toString());
		// sb = null;
		log.debug(sb.toString());
		
	//	System.out.println("MatchList: " +matchList);

	}
	
	public List<Integer> getRuleId(){
		return ruleId;
	}

	public boolean isRuleFired(String ruleName) {
		for (Match a : matchList) {
			if (a.getRule().getName().equals(ruleName)) {
				return true;
			}
		}
		return false;
	}

	public void reset() {
		matchList.clear();
	}

	public final List<Match> getMatchList() {
		return matchList;
	}

	public String matchsToString() {
		if (matchList.size() == 0) {
			return "No matchs occurred.";
		} else {
			StringBuilder sb = new StringBuilder("Matchs: ");
			for (Match match : matchList) {
				sb.append("\n  rule: ").append(match.getRule().getName());
			}
			return sb.toString();
		}
	}

}