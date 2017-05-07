// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.util.Collections;
import java.util.List;

public class cr$e
{
    private final List<cr$a> aqA;
    private final List<cr$a> aqB;
    private final List<cr$a> aqC;
    private final List<String> aqD;
    private final List<String> aqE;
    private final List<String> aqF;
    private final List<String> aqG;
    private final List<cr$a> aqx;
    private final List<cr$a> aqy;
    private final List<cr$a> aqz;
    
    private cr$e(final List<cr$a> list, final List<cr$a> list2, final List<cr$a> list3, final List<cr$a> list4, final List<cr$a> list5, final List<cr$a> list6, final List<String> list7, final List<String> list8, final List<String> list9, final List<String> list10) {
        this.aqx = Collections.unmodifiableList((List<? extends cr$a>)list);
        this.aqy = Collections.unmodifiableList((List<? extends cr$a>)list2);
        this.aqz = Collections.unmodifiableList((List<? extends cr$a>)list3);
        this.aqA = Collections.unmodifiableList((List<? extends cr$a>)list4);
        this.aqB = Collections.unmodifiableList((List<? extends cr$a>)list5);
        this.aqC = Collections.unmodifiableList((List<? extends cr$a>)list6);
        this.aqD = Collections.unmodifiableList((List<? extends String>)list7);
        this.aqE = Collections.unmodifiableList((List<? extends String>)list8);
        this.aqF = Collections.unmodifiableList((List<? extends String>)list9);
        this.aqG = Collections.unmodifiableList((List<? extends String>)list10);
    }
    
    public static cr$f oZ() {
        return new cr$f(null);
    }
    
    public List<cr$a> pa() {
        return this.aqx;
    }
    
    public List<cr$a> pb() {
        return this.aqy;
    }
    
    public List<cr$a> pc() {
        return this.aqz;
    }
    
    public List<cr$a> pd() {
        return this.aqA;
    }
    
    public List<cr$a> pe() {
        return this.aqB;
    }
    
    public List<String> pf() {
        return this.aqD;
    }
    
    public List<String> pg() {
        return this.aqE;
    }
    
    public List<String> ph() {
        return this.aqF;
    }
    
    public List<String> pi() {
        return this.aqG;
    }
    
    public List<cr$a> pj() {
        return this.aqC;
    }
    
    @Override
    public String toString() {
        return "Positive predicates: " + this.pa() + "  Negative predicates: " + this.pb() + "  Add tags: " + this.pc() + "  Remove tags: " + this.pd() + "  Add macros: " + this.pe() + "  Remove macros: " + this.pj();
    }
}
