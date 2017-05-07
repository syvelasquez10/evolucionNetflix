// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics;

import java.util.Iterator;
import android.text.TextUtils;
import com.google.android.gms.analytics.internal.zzae;
import java.util.ArrayList;
import java.util.HashMap;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.google.android.gms.analytics.ecommerce.Product;
import java.util.List;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import java.util.Map;

public class HitBuilders$HitBuilder<T extends HitBuilders$HitBuilder>
{
    private Map<String, String> zzKB;
    ProductAction zzKC;
    Map<String, List<Product>> zzKD;
    List<Promotion> zzKE;
    List<Product> zzKF;
    
    protected HitBuilders$HitBuilder() {
        this.zzKB = new HashMap<String, String>();
        this.zzKD = new HashMap<String, List<Product>>();
        this.zzKE = new ArrayList<Promotion>();
        this.zzKF = new ArrayList<Product>();
    }
    
    public T addImpression(final Product product, final String s) {
        if (product == null) {
            zzae.zzaE("product should be non-null");
            return (T)this;
        }
        String s2;
        if ((s2 = s) == null) {
            s2 = "";
        }
        if (!this.zzKD.containsKey(s2)) {
            this.zzKD.put(s2, new ArrayList<Product>());
        }
        this.zzKD.get(s2).add(product);
        return (T)this;
    }
    
    public T addProduct(final Product product) {
        if (product == null) {
            zzae.zzaE("product should be non-null");
            return (T)this;
        }
        this.zzKF.add(product);
        return (T)this;
    }
    
    public T addPromotion(final Promotion promotion) {
        if (promotion == null) {
            zzae.zzaE("promotion should be non-null");
            return (T)this;
        }
        this.zzKE.add(promotion);
        return (T)this;
    }
    
    public Map<String, String> build() {
        final HashMap<String, String> hashMap = new HashMap<String, String>(this.zzKB);
        if (this.zzKC != null) {
            hashMap.putAll((Map<?, ?>)this.zzKC.build());
        }
        final Iterator<Promotion> iterator = this.zzKE.iterator();
        int n = 1;
        while (iterator.hasNext()) {
            hashMap.putAll((Map<?, ?>)iterator.next().zzaV(zzc.zzU(n)));
            ++n;
        }
        final Iterator<Product> iterator2 = this.zzKF.iterator();
        int n2 = 1;
        while (iterator2.hasNext()) {
            hashMap.putAll((Map<?, ?>)iterator2.next().zzaV(zzc.zzS(n2)));
            ++n2;
        }
        final Iterator<Map.Entry<String, List<Product>>> iterator3 = this.zzKD.entrySet().iterator();
        int n3 = 1;
        while (iterator3.hasNext()) {
            final Map.Entry<String, List<Product>> entry = iterator3.next();
            final List<Product> list = entry.getValue();
            final String zzX = zzc.zzX(n3);
            final Iterator<Product> iterator4 = list.iterator();
            int n4 = 1;
            while (iterator4.hasNext()) {
                hashMap.putAll((Map<?, ?>)iterator4.next().zzaV(zzX + zzc.zzW(n4)));
                ++n4;
            }
            if (!TextUtils.isEmpty((CharSequence)entry.getKey())) {
                hashMap.put(zzX + "nm", entry.getKey());
            }
            ++n3;
        }
        return hashMap;
    }
    
    public final T set(final String s, final String s2) {
        if (s != null) {
            this.zzKB.put(s, s2);
            return (T)this;
        }
        zzae.zzaE(" HitBuilder.set() called with a null paramName.");
        return (T)this;
    }
    
    public final T setAll(final Map<String, String> map) {
        if (map == null) {
            return (T)this;
        }
        this.zzKB.putAll(new HashMap<String, String>(map));
        return (T)this;
    }
    
    public T setProductAction(final ProductAction zzKC) {
        this.zzKC = zzKC;
        return (T)this;
    }
}
