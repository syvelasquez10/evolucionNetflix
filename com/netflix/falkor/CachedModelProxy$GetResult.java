// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor;

import java.util.Iterator;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.falkor.Falkor;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Collection;
import java.util.Set;
import java.util.List;

public class CachedModelProxy$GetResult
{
    public final List<PQL> foundPqls;
    public final List<PQL> ignoredPqls;
    public final Set<PQL> missingPqls;
    public final Collection<PQL> pqls;
    
    public CachedModelProxy$GetResult(final Collection<PQL> pqls) {
        this.pqls = pqls;
        this.missingPqls = new HashSet<PQL>();
        this.foundPqls = new ArrayList<PQL>();
        this.ignoredPqls = new ArrayList<PQL>();
    }
    
    public boolean hasMissingPaths() {
        return this.missingPqls.size() > 0;
    }
    
    public void printPaths(final String s) {
        if (Falkor.ENABLE_VERBOSE_LOGGING) {
            final Iterator<PQL> iterator = this.foundPqls.iterator();
            while (iterator.hasNext()) {
                Log.v(s, "Found PQL: " + iterator.next());
            }
            final Iterator<PQL> iterator2 = this.missingPqls.iterator();
            while (iterator2.hasNext()) {
                Log.v(s, "Missing PQL: " + iterator2.next());
            }
            final Iterator<PQL> iterator3 = this.ignoredPqls.iterator();
            while (iterator3.hasNext()) {
                Log.v(s, "Ignored PQL: " + iterator3.next());
            }
        }
    }
}
