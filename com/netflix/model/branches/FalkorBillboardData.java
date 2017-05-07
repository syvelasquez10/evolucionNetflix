// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.branches;

import com.netflix.mediaclient.Log;
import java.util.HashSet;
import java.util.Set;
import com.netflix.falkor.Func;
import com.netflix.model.leafs.originals.BillboardSummary;
import com.netflix.falkor.BranchMap;

public class FalkorBillboardData extends BranchMap<BillboardSummary>
{
    private static final String TAG = "FalkorBillboardData";
    private BillboardSummary billboardSummary;
    
    public FalkorBillboardData(final Func<BillboardSummary> func) {
        super(func);
    }
    
    @Override
    public Object get(final String s) {
        switch (s) {
            default: {
                return null;
            }
            case "billboardSummary": {
                return this.billboardSummary;
            }
        }
    }
    
    @Override
    public Set<String> getKeys() {
        final HashSet<String> set = new HashSet<String>();
        if (this.billboardSummary != null) {
            set.add("billboardSummary");
        }
        return set;
    }
    
    @Override
    public Object getOrCreate(final String s) {
        final Object value = this.get(s);
        if (value != null) {
            return value;
        }
        if (Log.isLoggable()) {
            Log.v("FalkorBillboardData", "Creating leaf for key: " + s);
        }
        switch (s) {
            default: {
                return null;
            }
            case "billboardSummary": {
                return this.billboardSummary = new BillboardSummary();
            }
        }
    }
    
    @Override
    public void remove(final String s) {
        this.set(s, null);
    }
    
    @Override
    public void set(final String s, final Object o) {
        switch (s) {
            default: {}
            case "billboardSummary": {
                this.billboardSummary = (BillboardSummary)o;
            }
        }
    }
}
