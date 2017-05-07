// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.data;

import com.google.android.gms.common.internal.n;
import java.util.HashMap;
import java.util.ArrayList;

public class DataHolder$a
{
    private final String[] JU;
    private final ArrayList<HashMap<String, Object>> Kd;
    private final String Ke;
    private final HashMap<Object, Integer> Kf;
    private boolean Kg;
    private String Kh;
    
    private DataHolder$a(final String[] array, final String ke) {
        this.JU = n.i(array);
        this.Kd = new ArrayList<HashMap<String, Object>>();
        this.Ke = ke;
        this.Kf = new HashMap<Object, Integer>();
        this.Kg = false;
        this.Kh = null;
    }
}
