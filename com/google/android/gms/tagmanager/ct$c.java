// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class ct$c
{
    private final Set<cr$e> aqP;
    private final Map<cr$e, List<cr$a>> aqZ;
    private final Map<cr$e, List<cr$a>> ara;
    private final Map<cr$e, List<String>> arb;
    private final Map<cr$e, List<String>> arc;
    private cr$a ard;
    
    public ct$c() {
        this.aqP = new HashSet<cr$e>();
        this.aqZ = new HashMap<cr$e, List<cr$a>>();
        this.arb = new HashMap<cr$e, List<String>>();
        this.ara = new HashMap<cr$e, List<cr$a>>();
        this.arc = new HashMap<cr$e, List<String>>();
    }
    
    public void a(final cr$e cr$e, final cr$a cr$a) {
        List<cr$a> list;
        if ((list = this.aqZ.get(cr$e)) == null) {
            list = new ArrayList<cr$a>();
            this.aqZ.put(cr$e, list);
        }
        list.add(cr$a);
    }
    
    public void a(final cr$e cr$e, final String s) {
        List<String> list;
        if ((list = this.arb.get(cr$e)) == null) {
            list = new ArrayList<String>();
            this.arb.put(cr$e, list);
        }
        list.add(s);
    }
    
    public void b(final cr$e cr$e) {
        this.aqP.add(cr$e);
    }
    
    public void b(final cr$e cr$e, final cr$a cr$a) {
        List<cr$a> list;
        if ((list = this.ara.get(cr$e)) == null) {
            list = new ArrayList<cr$a>();
            this.ara.put(cr$e, list);
        }
        list.add(cr$a);
    }
    
    public void b(final cr$e cr$e, final String s) {
        List<String> list;
        if ((list = this.arc.get(cr$e)) == null) {
            list = new ArrayList<String>();
            this.arc.put(cr$e, list);
        }
        list.add(s);
    }
    
    public void i(final cr$a ard) {
        this.ard = ard;
    }
    
    public Set<cr$e> po() {
        return this.aqP;
    }
    
    public Map<cr$e, List<cr$a>> pp() {
        return this.aqZ;
    }
    
    public Map<cr$e, List<String>> pq() {
        return this.arb;
    }
    
    public Map<cr$e, List<String>> pr() {
        return this.arc;
    }
    
    public Map<cr$e, List<cr$a>> ps() {
        return this.ara;
    }
    
    public cr$a pt() {
        return this.ard;
    }
}
