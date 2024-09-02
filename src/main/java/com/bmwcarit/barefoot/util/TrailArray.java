package com.bmwcarit.barefoot.util;

import com.bmwcarit.barefoot.matcher.MatcherCandidate;
import com.bmwcarit.barefoot.matcher.MatcherTransition;
import com.bmwcarit.barefoot.roadmap.Road;
import com.bmwcarit.barefoot.roadmap.Route;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

public class TrailArray extends ArrayList<Long> {
    int gettingAroundCursor = 0;

    public TrailArray() {
        super();
    }

    public void record(MatcherCandidate candidate) {
        MatcherTransition transition = candidate.transition();
        if (transition != null && transition.route() != null && transition.route().path().size() > 0) {
            if (size() > 0) {
                remove(size() -1);
            }
            addAll(transition.route().path().stream().map(r -> r.base().id()).collect(Collectors.toList()));
        }
    }

    public JSONArray getAround(MatcherCandidate candidate) {
        MatcherTransition transition = candidate.transition();
        if (transition != null && transition.route() != null && transition.route().path().size() > 0) {
	    gettingAroundCursor += transition.route().path().size() -1;
       	    if (transition.route().path().size() == 2) {
            }
        }

	JSONArray json = new JSONArray();
        if (0 <= gettingAroundCursor -2) { json.put(get(gettingAroundCursor -2)); }
        if (0 <= gettingAroundCursor -1) { json.put(get(gettingAroundCursor -1)); }
        if (size() > gettingAroundCursor) { json.put(get(gettingAroundCursor)); }
        if (size() > gettingAroundCursor +1) { json.put(get(gettingAroundCursor +1)); }
        if (size() > gettingAroundCursor +2) { json.put(get(gettingAroundCursor +2)); }
        return json;
    }

    public JSONArray toJSON() {
        return new JSONArray(this);
    }
}
