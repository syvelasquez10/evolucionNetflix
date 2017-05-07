// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Collections;
import java.util.Iterator;
import java.util.Collection;
import java.util.HashMap;
import java.util.ArrayList;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.google.android.gms.analytics.ecommerce.Product;
import java.util.List;
import java.util.Map;
import com.google.android.gms.analytics.ecommerce.ProductAction;

public final class zzon extends zzod<zzon>
{
    private ProductAction zzKC;
    private final Map<String, List<Product>> zzKD;
    private final List<Promotion> zzKE;
    private final List<Product> zzKF;
    
    public zzon() {
        this.zzKF = new ArrayList<Product>();
        this.zzKE = new ArrayList<Promotion>();
        this.zzKD = new HashMap<String, List<Product>>();
    }
    
    @Override
    public String toString() {
        final HashMap<String, List<Product>> hashMap = new HashMap<String, List<Product>>();
        if (!this.zzKF.isEmpty()) {
            hashMap.put("products", this.zzKF);
        }
        if (!this.zzKE.isEmpty()) {
            hashMap.put("promotions", (List<Product>)this.zzKE);
        }
        if (!this.zzKD.isEmpty()) {
            hashMap.put("impressions", (List<Product>)this.zzKD);
        }
        hashMap.put("productAction", (List<Product>)this.zzKC);
        return zzod.zzA(hashMap);
    }
    
    public void zza(final Product product, final String s) {
        if (product == null) {
            return;
        }
        String s2;
        if ((s2 = s) == null) {
            s2 = "";
        }
        if (!this.zzKD.containsKey(s2)) {
            this.zzKD.put(s2, new ArrayList<Product>());
        }
        this.zzKD.get(s2).add(product);
    }
    
    @Override
    public void zza(final zzon zzon) {
        zzon.zzKF.addAll(this.zzKF);
        zzon.zzKE.addAll(this.zzKE);
        for (final Map.Entry<String, List<Product>> entry : this.zzKD.entrySet()) {
            final String s = entry.getKey();
            final Iterator<Product> iterator2 = entry.getValue().iterator();
            while (iterator2.hasNext()) {
                zzon.zza(iterator2.next(), s);
            }
        }
        if (this.zzKC != null) {
            zzon.zzKC = this.zzKC;
        }
    }
    
    public ProductAction zzxM() {
        return this.zzKC;
    }
    
    public List<Product> zzxN() {
        return Collections.unmodifiableList((List<? extends Product>)this.zzKF);
    }
    
    public Map<String, List<Product>> zzxO() {
        return this.zzKD;
    }
    
    public List<Promotion> zzxP() {
        return Collections.unmodifiableList((List<? extends Promotion>)this.zzKE);
    }
}
