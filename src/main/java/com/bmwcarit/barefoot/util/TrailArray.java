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
    public TrailArray() {
        super();
    }

    public void record(MatcherCandidate candidate) {
        MatcherTransition transition = candidate.transition();
        if (transition != null && transition.route() != null && transition.route().path().size() > 0) {
            if (size() > 0) {
                remove(size() -1);
            }
            addAll(transition.route().path().stream().map(Road::id).collect(Collectors.toList()));
        }
    }

    public JSONArray toJSONArray() {
        return new JSONArray(this);
    }
}
