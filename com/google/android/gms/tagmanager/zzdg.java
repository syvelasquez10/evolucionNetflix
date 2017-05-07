// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import com.google.android.gms.analytics.HitBuilders$HitBuilder;
import java.util.LinkedHashMap;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.google.android.gms.analytics.HitBuilders$ScreenViewBuilder;
import java.util.LinkedList;
import com.google.android.gms.analytics.Tracker;
import java.util.HashMap;
import com.google.android.gms.internal.zzag$zza;
import java.util.regex.Matcher;
import java.util.Iterator;
import com.google.android.gms.analytics.ecommerce.Product;
import com.google.android.gms.analytics.ecommerce.Promotion;
import java.util.HashSet;
import android.content.Context;
import java.util.Arrays;
import com.google.android.gms.internal.zzae;
import com.google.android.gms.internal.zzad;
import java.util.Set;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.List;

public class zzdg extends zzdd
{
    private static final String ID;
    private static final String zzaSW;
    private static final String zzaSX;
    private static final String zzaSY;
    private static final String zzaSZ;
    private static final String zzaTa;
    private static final String zzaTb;
    private static final String zzaTc;
    private static final String zzaTd;
    private static final String zzaTe;
    private static final List<String> zzaTf;
    private static final Pattern zzaTg;
    private static final Pattern zzaTh;
    private static Map<String, String> zzaTi;
    private static Map<String, String> zzaTj;
    private final DataLayer zzaOT;
    private final Set<String> zzaTk;
    private final zzdc zzaTl;
    
    static {
        ID = zzad.zzcE.toString();
        zzaSW = zzae.zzdl.toString();
        zzaSX = zzae.zzdv.toString();
        zzaSY = zzae.zzeQ.toString();
        zzaSZ = zzae.zzeK.toString();
        zzaTa = zzae.zzeJ.toString();
        zzaTb = zzae.zzdu.toString();
        zzaTc = zzae.zzhn.toString();
        zzaTd = zzae.zzhq.toString();
        zzaTe = zzae.zzhs.toString();
        zzaTf = Arrays.asList("detail", "checkout", "checkout_option", "click", "add", "remove", "purchase", "refund");
        zzaTg = Pattern.compile("dimension(\\d+)");
        zzaTh = Pattern.compile("metric(\\d+)");
    }
    
    public zzdg(final Context context, final DataLayer dataLayer) {
        this(context, dataLayer, new zzdc(context));
    }
    
    zzdg(final Context context, final DataLayer zzaOT, final zzdc zzaTl) {
        super(zzdg.ID, new String[0]);
        this.zzaOT = zzaOT;
        this.zzaTl = zzaTl;
        (this.zzaTk = new HashSet<String>()).add("");
        this.zzaTk.add("0");
        this.zzaTk.add("false");
    }
    
    private Double zzO(final Object o) {
        if (o instanceof String) {
            try {
                return Double.valueOf((String)o);
            }
            catch (NumberFormatException ex) {
                throw new RuntimeException("Cannot convert the object to Double: " + ex.getMessage());
            }
        }
        if (o instanceof Integer) {
            return (double)o;
        }
        if (o instanceof Double) {
            return (Double)o;
        }
        throw new RuntimeException("Cannot convert the object to Double: " + o.toString());
    }
    
    private Integer zzP(final Object o) {
        if (o instanceof String) {
            try {
                return Integer.valueOf((String)o);
            }
            catch (NumberFormatException ex) {
                throw new RuntimeException("Cannot convert the object to Integer: " + ex.getMessage());
            }
        }
        if (o instanceof Double) {
            return (int)o;
        }
        if (o instanceof Integer) {
            return (Integer)o;
        }
        throw new RuntimeException("Cannot convert the object to Integer: " + o.toString());
    }
    
    private Promotion zzQ(final Map<String, String> map) {
        final Promotion promotion = new Promotion();
        final String s = map.get("id");
        if (s != null) {
            promotion.setId(String.valueOf(s));
        }
        final String s2 = map.get("name");
        if (s2 != null) {
            promotion.setName(String.valueOf(s2));
        }
        final String s3 = map.get("creative");
        if (s3 != null) {
            promotion.setCreative(String.valueOf(s3));
        }
        final String s4 = map.get("position");
        if (s4 != null) {
            promotion.setPosition(String.valueOf(s4));
        }
        return promotion;
    }
    
    private Product zzR(final Map<String, Object> map) {
        final Product product = new Product();
        final Object value = map.get("id");
        if (value != null) {
            product.setId(String.valueOf(value));
        }
        final Object value2 = map.get("name");
        if (value2 != null) {
            product.setName(String.valueOf(value2));
        }
        final Object value3 = map.get("brand");
        if (value3 != null) {
            product.setBrand(String.valueOf(value3));
        }
        final Object value4 = map.get("category");
        if (value4 != null) {
            product.setCategory(String.valueOf(value4));
        }
        final Object value5 = map.get("variant");
        if (value5 != null) {
            product.setVariant(String.valueOf(value5));
        }
        final Object value6 = map.get("coupon");
        if (value6 != null) {
            product.setCouponCode(String.valueOf(value6));
        }
        final Object value7 = map.get("position");
        if (value7 != null) {
            product.setPosition(this.zzP(value7));
        }
        final Object value8 = map.get("price");
        if (value8 != null) {
            product.setPrice(this.zzO(value8));
        }
        final Object value9 = map.get("quantity");
        if (value9 != null) {
            product.setQuantity(this.zzP(value9));
        }
        for (final String s : map.keySet()) {
            final Matcher matcher = zzdg.zzaTg.matcher(s);
            if (matcher.matches()) {
                try {
                    product.setCustomDimension(Integer.parseInt(matcher.group(1)), String.valueOf(map.get(s)));
                }
                catch (NumberFormatException ex) {
                    zzbg.zzaE("illegal number in custom dimension value: " + s);
                }
            }
            else {
                final Matcher matcher2 = zzdg.zzaTh.matcher(s);
                if (!matcher2.matches()) {
                    continue;
                }
                try {
                    product.setCustomMetric(Integer.parseInt(matcher2.group(1)), this.zzP(map.get(s)));
                }
                catch (NumberFormatException ex2) {
                    zzbg.zzaE("illegal number in custom metric value: " + s);
                }
            }
        }
        return product;
    }
    
    private Map<String, String> zzS(final Map<String, zzag$zza> map) {
        final zzag$zza zzag$zza = map.get(zzdg.zzaTd);
        if (zzag$zza != null) {
            return this.zzc(zzag$zza);
        }
        if (zzdg.zzaTi == null) {
            final HashMap<String, String> zzaTi = new HashMap<String, String>();
            zzaTi.put("transactionId", "&ti");
            zzaTi.put("transactionAffiliation", "&ta");
            zzaTi.put("transactionTax", "&tt");
            zzaTi.put("transactionShipping", "&ts");
            zzaTi.put("transactionTotal", "&tr");
            zzaTi.put("transactionCurrency", "&cu");
            zzdg.zzaTi = zzaTi;
        }
        return zzdg.zzaTi;
    }
    
    private Map<String, String> zzT(final Map<String, zzag$zza> map) {
        final zzag$zza zzag$zza = map.get(zzdg.zzaTe);
        if (zzag$zza != null) {
            return this.zzc(zzag$zza);
        }
        if (zzdg.zzaTj == null) {
            final HashMap<String, String> zzaTj = new HashMap<String, String>();
            zzaTj.put("name", "&in");
            zzaTj.put("sku", "&ic");
            zzaTj.put("category", "&iv");
            zzaTj.put("price", "&ip");
            zzaTj.put("quantity", "&iq");
            zzaTj.put("currency", "&cu");
            zzdg.zzaTj = zzaTj;
        }
        return zzdg.zzaTj;
    }
    
    private void zza(final Tracker tracker, final Map<String, zzag$zza> map) {
        final String zzfd = this.zzfd("transactionId");
        if (zzfd == null) {
            zzbg.e("Cannot find transactionId in data layer.");
        }
        else {
            final LinkedList<Map<String, String>> list = new LinkedList<Map<String, String>>();
            Map<String, String> zzm;
            try {
                zzm = this.zzm(map.get(zzdg.zzaTb));
                zzm.put("&t", "transaction");
                for (final Map.Entry<String, String> entry : this.zzS(map).entrySet()) {
                    this.zzd(zzm, entry.getValue(), this.zzfd(entry.getKey()));
                }
            }
            catch (IllegalArgumentException ex) {
                zzbg.zzb("Unable to send transaction", ex);
                return;
            }
            list.add(zzm);
            final List<Map<String, String>> zzfe = this.zzfe("transactionProducts");
            if (zzfe != null) {
                for (final Map<String, String> map2 : zzfe) {
                    if (map2.get("name") == null) {
                        zzbg.e("Unable to send transaction item hit due to missing 'name' field.");
                        return;
                    }
                    final Map<String, String> zzm2 = this.zzm(map.get(zzdg.zzaTb));
                    zzm2.put("&t", "item");
                    zzm2.put("&ti", zzfd);
                    for (final Map.Entry<String, String> entry2 : this.zzT(map).entrySet()) {
                        this.zzd(zzm2, entry2.getValue(), map2.get(entry2.getKey()));
                    }
                    list.add(zzm2);
                }
            }
            final Iterator<Object> iterator4 = list.iterator();
            while (iterator4.hasNext()) {
                tracker.send(iterator4.next());
            }
        }
    }
    
    private void zzb(final Tracker tracker, Map<String, zzag$zza> map) {
        final HitBuilders$ScreenViewBuilder hitBuilders$ScreenViewBuilder = new HitBuilders$ScreenViewBuilder();
        final Map<String, String> zzm = this.zzm(map.get(zzdg.zzaTb));
        hitBuilders$ScreenViewBuilder.setAll(zzm);
        if (this.zzj(map, zzdg.zzaSZ)) {
            final Object value = this.zzaOT.get("ecommerce");
            if (value instanceof Map) {
                map = (Map<String, zzag$zza>)value;
            }
            else {
                map = null;
            }
        }
        else {
            final Object zzl = zzdf.zzl(map.get(zzdg.zzaTa));
            if (zzl instanceof Map) {
                map = (Map<String, zzag$zza>)zzl;
            }
            else {
                map = null;
            }
        }
        while (true) {
            String s;
            zzag$zza value2;
            Object o;
            int n;
            final String s2;
            List list;
            ProductAction zze;
            Label_0720:Label_0456_Outer:
            while (true) {
                if (map == null) {
                    break Label_0667;
                }
                if ((s = zzm.get("&cu")) == null) {
                    s = (String)map.get("currencyCode");
                }
                if (s != null) {
                    hitBuilders$ScreenViewBuilder.set("&cu", s);
                }
                value2 = map.get("impressions");
                if (value2 instanceof List) {
                    for (final Map<K, String> map2 : (List<Map>)value2) {
                        try {
                            hitBuilders$ScreenViewBuilder.addImpression(this.zzR((Map<String, Object>)map2), map2.get("list"));
                        }
                        catch (RuntimeException ex) {
                            zzbg.e("Failed to extract a product from DataLayer. " + ex.getMessage());
                        }
                    }
                }
                if (map.containsKey("promoClick")) {
                    o = ((Map)map.get("promoClick")).get("promotions");
                }
                else {
                    if (!map.containsKey("promoView")) {
                        break Label_0720;
                    }
                    o = ((Map)map.get("promoView")).get("promotions");
                }
                Label_0629: {
                    while (true) {
                        Label_0624: {
                            if (o == null) {
                                break Label_0624;
                            }
                            for (final Map<String, String> map3 : o) {
                                try {
                                    hitBuilders$ScreenViewBuilder.addPromotion(this.zzQ(map3));
                                }
                                catch (RuntimeException ex2) {
                                    zzbg.e("Failed to extract a promotion from DataLayer. " + ex2.getMessage());
                                }
                            }
                            if (!map.containsKey("promoClick")) {
                                hitBuilders$ScreenViewBuilder.set("&promoa", "view");
                                break Label_0624;
                            }
                            hitBuilders$ScreenViewBuilder.set("&promoa", "click");
                            n = 0;
                            if (n != 0) {
                                for (final String s2 : zzdg.zzaTf) {
                                    if (map.containsKey(s2)) {
                                        map = (Map<String, zzag$zza>)map.get(s2);
                                        list = (List)map.get("products");
                                        if (list != null) {
                                            for (final Map<String, Object> map4 : list) {
                                                try {
                                                    hitBuilders$ScreenViewBuilder.addProduct(this.zzR(map4));
                                                }
                                                catch (RuntimeException ex3) {
                                                    zzbg.e("Failed to extract a product from DataLayer. " + ex3.getMessage());
                                                }
                                            }
                                        }
                                        break Label_0629;
                                    }
                                }
                                break Label_0667;
                            }
                            break Label_0667;
                        }
                        n = 1;
                        continue;
                    }
                    try {
                        if (map.containsKey("actionField")) {
                            zze = this.zze(s2, (Map<String, Object>)map.get("actionField"));
                        }
                        else {
                            zze = new ProductAction(s2);
                        }
                        hitBuilders$ScreenViewBuilder.setProductAction(zze);
                        tracker.send(hitBuilders$ScreenViewBuilder.build());
                        return;
                    }
                    catch (RuntimeException ex4) {
                        zzbg.e("Failed to extract a product action from DataLayer. " + ex4.getMessage());
                        continue Label_0456_Outer;
                    }
                }
                break;
            }
            o = null;
            continue;
        }
    }
    
    private Map<String, String> zzc(final zzag$zza zzag$zza) {
        final Object zzl = zzdf.zzl(zzag$zza);
        if (!(zzl instanceof Map)) {
            return null;
        }
        final Map<Object, V> map = (Map<Object, V>)zzl;
        final LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>();
        for (final Map.Entry<Object, V> entry : map.entrySet()) {
            linkedHashMap.put(entry.getKey().toString(), entry.getValue().toString());
        }
        return linkedHashMap;
    }
    
    private void zzd(final Map<String, String> map, final String s, final String s2) {
        if (s2 != null) {
            map.put(s, s2);
        }
    }
    
    private ProductAction zze(final String s, final Map<String, Object> map) {
        final ProductAction productAction = new ProductAction(s);
        final Object value = map.get("id");
        if (value != null) {
            productAction.setTransactionId(String.valueOf(value));
        }
        final Object value2 = map.get("affiliation");
        if (value2 != null) {
            productAction.setTransactionAffiliation(String.valueOf(value2));
        }
        final Object value3 = map.get("coupon");
        if (value3 != null) {
            productAction.setTransactionCouponCode(String.valueOf(value3));
        }
        final Object value4 = map.get("list");
        if (value4 != null) {
            productAction.setProductActionList(String.valueOf(value4));
        }
        final Object value5 = map.get("option");
        if (value5 != null) {
            productAction.setCheckoutOptions(String.valueOf(value5));
        }
        final Object value6 = map.get("revenue");
        if (value6 != null) {
            productAction.setTransactionRevenue(this.zzO(value6));
        }
        final Object value7 = map.get("tax");
        if (value7 != null) {
            productAction.setTransactionTax(this.zzO(value7));
        }
        final Object value8 = map.get("shipping");
        if (value8 != null) {
            productAction.setTransactionShipping(this.zzO(value8));
        }
        final Object value9 = map.get("step");
        if (value9 != null) {
            productAction.setCheckoutStep(this.zzP(value9));
        }
        return productAction;
    }
    
    private String zzfd(final String s) {
        final Object value = this.zzaOT.get(s);
        if (value == null) {
            return null;
        }
        return value.toString();
    }
    
    private List<Map<String, String>> zzfe(final String s) {
        final Object value = this.zzaOT.get(s);
        if (value == null) {
            return null;
        }
        if (!(value instanceof List)) {
            throw new IllegalArgumentException("transactionProducts should be of type List.");
        }
        final Iterator<Map<String, String>> iterator = ((List<Map<String, String>>)value).iterator();
        while (iterator.hasNext()) {
            if (!(iterator.next() instanceof Map)) {
                throw new IllegalArgumentException("Each element of transactionProducts should be of type Map.");
            }
        }
        return (List<Map<String, String>>)value;
    }
    
    private boolean zzj(final Map<String, zzag$zza> map, final String s) {
        final zzag$zza zzag$zza = map.get(s);
        return zzag$zza != null && zzdf.zzk(zzag$zza);
    }
    
    private Map<String, String> zzm(final zzag$zza zzag$zza) {
        if (zzag$zza == null) {
            return new HashMap<String, String>();
        }
        final Map<String, String> zzc = this.zzc(zzag$zza);
        if (zzc == null) {
            return new HashMap<String, String>();
        }
        final String s = zzc.get("&aip");
        if (s != null && this.zzaTk.contains(s.toLowerCase())) {
            zzc.remove("&aip");
        }
        return zzc;
    }
    
    @Override
    public void zzI(final Map<String, zzag$zza> map) {
        final Tracker zzeV = this.zzaTl.zzeV("_GTM_DEFAULT_TRACKER_");
        zzeV.enableAdvertisingIdCollection(this.zzj(map, "collect_adid"));
        if (this.zzj(map, zzdg.zzaSY)) {
            this.zzb(zzeV, map);
            return;
        }
        if (this.zzj(map, zzdg.zzaSX)) {
            zzeV.send(this.zzm(map.get(zzdg.zzaTb)));
            return;
        }
        if (this.zzj(map, zzdg.zzaTc)) {
            this.zza(zzeV, map);
            return;
        }
        zzbg.zzaE("Ignoring unknown tag.");
    }
}
