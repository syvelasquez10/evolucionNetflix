// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics;

import java.util.Iterator;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashMap;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.google.android.gms.analytics.ecommerce.Product;
import java.util.List;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import java.util.Map;

public class HitBuilders$HitBuilder<T extends HitBuilders$HitBuilder>
{
    private Map<String, String> AI;
    ProductAction AJ;
    Map<String, List<Product>> AK;
    List<Promotion> AL;
    List<Product> AM;
    
    protected HitBuilders$HitBuilder() {
        this.AI = new HashMap<String, String>();
        this.AK = new HashMap<String, List<Product>>();
        this.AL = new ArrayList<Promotion>();
        this.AM = new ArrayList<Product>();
    }
    
    public T addImpression(final Product product, final String s) {
        if (product == null) {
            z.W("product should be non-null");
            return (T)this;
        }
        String s2;
        if ((s2 = s) == null) {
            s2 = "";
        }
        if (!this.AK.containsKey(s2)) {
            this.AK.put(s2, new ArrayList<Product>());
        }
        this.AK.get(s2).add(product);
        return (T)this;
    }
    
    public T addProduct(final Product product) {
        if (product == null) {
            z.W("product should be non-null");
            return (T)this;
        }
        this.AM.add(product);
        return (T)this;
    }
    
    public T addPromotion(final Promotion promotion) {
        if (promotion == null) {
            z.W("promotion should be non-null");
            return (T)this;
        }
        this.AL.add(promotion);
        return (T)this;
    }
    
    public Map<String, String> build() {
        final HashMap<String, String> hashMap = new HashMap<String, String>(this.AI);
        if (this.AJ != null) {
            hashMap.putAll((Map<?, ?>)this.AJ.build());
        }
        final Iterator<Promotion> iterator = this.AL.iterator();
        int n = 1;
        while (iterator.hasNext()) {
            hashMap.putAll((Map<?, ?>)iterator.next().aq(com.google.android.gms.analytics.n.A(n)));
            ++n;
        }
        final Iterator<Product> iterator2 = this.AM.iterator();
        int n2 = 1;
        while (iterator2.hasNext()) {
            hashMap.putAll((Map<?, ?>)iterator2.next().aq(com.google.android.gms.analytics.n.z(n2)));
            ++n2;
        }
        final Iterator<Map.Entry<String, List<Product>>> iterator3 = this.AK.entrySet().iterator();
        int n3 = 1;
        while (iterator3.hasNext()) {
            final Map.Entry<String, List<Product>> entry = iterator3.next();
            final List<Product> list = entry.getValue();
            final String c = com.google.android.gms.analytics.n.C(n3);
            final Iterator<Product> iterator4 = list.iterator();
            int n4 = 1;
            while (iterator4.hasNext()) {
                hashMap.putAll((Map<?, ?>)iterator4.next().aq(c + com.google.android.gms.analytics.n.B(n4)));
                ++n4;
            }
            if (!TextUtils.isEmpty((CharSequence)entry.getKey())) {
                hashMap.put(c + "nm", entry.getKey());
            }
            ++n3;
        }
        return hashMap;
    }
    
    protected String get(final String s) {
        return this.AI.get(s);
    }
    
    public final T set(final String s, final String s2) {
        t.eq().a(t$a.zf);
        if (s != null) {
            this.AI.put(s, s2);
            return (T)this;
        }
        z.W(" HitBuilder.set() called with a null paramName.");
        return (T)this;
    }
    
    public final T setAll(final Map<String, String> map) {
        t.eq().a(t$a.zg);
        if (map == null) {
            return (T)this;
        }
        this.AI.putAll(new HashMap<String, String>(map));
        return (T)this;
    }
    
    public T setCampaignParamsFromUrl(String ao) {
        t.eq().a(t$a.zi);
        ao = aj.ao(ao);
        if (TextUtils.isEmpty((CharSequence)ao)) {
            return (T)this;
        }
        final Map<String, String> an = aj.an(ao);
        this.set("&cc", an.get("utm_content"));
        this.set("&cm", an.get("utm_medium"));
        this.set("&cn", an.get("utm_campaign"));
        this.set("&cs", an.get("utm_source"));
        this.set("&ck", an.get("utm_term"));
        this.set("&ci", an.get("utm_id"));
        this.set("&gclid", an.get("gclid"));
        this.set("&dclid", an.get("dclid"));
        this.set("&gmob_t", an.get("gmob_t"));
        return (T)this;
    }
    
    public T setCustomDimension(final int n, final String s) {
        this.set(n.x(n), s);
        return (T)this;
    }
    
    public T setCustomMetric(final int n, final float n2) {
        this.set(n.y(n), Float.toString(n2));
        return (T)this;
    }
    
    protected T setHitType(final String s) {
        this.set("&t", s);
        return (T)this;
    }
    
    public T setNewSession() {
        this.set("&sc", "start");
        return (T)this;
    }
    
    public T setNonInteraction(final boolean b) {
        this.set("&ni", aj.C(b));
        return (T)this;
    }
    
    public T setProductAction(final ProductAction aj) {
        this.AJ = aj;
        return (T)this;
    }
    
    public T setPromotionAction(final String s) {
        this.AI.put("&promoa", s);
        return (T)this;
    }
}
