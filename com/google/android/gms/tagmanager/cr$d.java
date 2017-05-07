// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.b;
import com.google.android.gms.internal.d$a;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;

public class cr$d
{
    private String Sq;
    private final List<cr$e> aqu;
    private final Map<String, List<cr$a>> aqv;
    private int aqw;
    
    private cr$d() {
        this.aqu = new ArrayList<cr$e>();
        this.aqv = new HashMap<String, List<cr$a>>();
        this.Sq = "";
        this.aqw = 0;
    }
    
    public cr$d a(final cr$a cr$a) {
        final String j = di.j(cr$a.oS().get(b.df.toString()));
        List<cr$a> list;
        if ((list = this.aqv.get(j)) == null) {
            list = new ArrayList<cr$a>();
            this.aqv.put(j, list);
        }
        list.add(cr$a);
        return this;
    }
    
    public cr$d a(final cr$e cr$e) {
        this.aqu.add(cr$e);
        return this;
    }
    
    public cr$d cJ(final String sq) {
        this.Sq = sq;
        return this;
    }
    
    public cr$d fl(final int aqw) {
        this.aqw = aqw;
        return this;
    }
    
    public cr$c oY() {
        return new cr$c(this.aqu, this.aqv, this.Sq, this.aqw, null);
    }
}
