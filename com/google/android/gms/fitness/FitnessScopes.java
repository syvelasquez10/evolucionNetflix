// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness;

import java.util.Iterator;
import java.util.HashSet;
import java.util.List;
import com.google.android.gms.common.api.Scope;

public final class FitnessScopes
{
    public static final Scope SCOPE_ACTIVITY_READ;
    public static final Scope SCOPE_ACTIVITY_READ_WRITE;
    public static final Scope SCOPE_BODY_READ;
    public static final Scope SCOPE_BODY_READ_WRITE;
    public static final Scope SCOPE_LOCATION_READ;
    public static final Scope SCOPE_LOCATION_READ_WRITE;
    
    static {
        SCOPE_ACTIVITY_READ = new Scope("https://www.googleapis.com/auth/fitness.activity.read");
        SCOPE_ACTIVITY_READ_WRITE = new Scope("https://www.googleapis.com/auth/fitness.activity.write");
        SCOPE_LOCATION_READ = new Scope("https://www.googleapis.com/auth/fitness.location.read");
        SCOPE_LOCATION_READ_WRITE = new Scope("https://www.googleapis.com/auth/fitness.location.write");
        SCOPE_BODY_READ = new Scope("https://www.googleapis.com/auth/fitness.body.read");
        SCOPE_BODY_READ_WRITE = new Scope("https://www.googleapis.com/auth/fitness.body.write");
    }
    
    public static String bp(final String s) {
        String s2;
        if (s.equals("https://www.googleapis.com/auth/fitness.activity.read")) {
            s2 = "https://www.googleapis.com/auth/fitness.activity.write";
        }
        else {
            if (s.equals("https://www.googleapis.com/auth/fitness.location.read")) {
                return "https://www.googleapis.com/auth/fitness.location.write";
            }
            s2 = s;
            if (s.equals("https://www.googleapis.com/auth/fitness.body.read")) {
                return "https://www.googleapis.com/auth/fitness.body.write";
            }
        }
        return s2;
    }
    
    public static String[] d(final List<String> list) {
        final HashSet<String> set = new HashSet<String>(list.size());
        for (final String s : list) {
            final String bp = bp(s);
            if (bp.equals(s) || !list.contains(bp)) {
                set.add(s);
            }
        }
        return set.toArray(new String[set.size()]);
    }
}
