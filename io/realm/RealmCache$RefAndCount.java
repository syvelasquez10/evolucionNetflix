// 
// Decompiled by Procyon v0.5.30
// 

package io.realm;

class RealmCache$RefAndCount
{
    private int globalCount;
    private final ThreadLocal<Integer> localCount;
    private final ThreadLocal<BaseRealm> localRealm;
    
    private RealmCache$RefAndCount() {
        this.localRealm = new ThreadLocal<BaseRealm>();
        this.localCount = new ThreadLocal<Integer>();
        this.globalCount = 0;
    }
}
