// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import com.google.android.gms.analytics.t;
import java.util.LinkedHashMap;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.google.android.gms.analytics.HitBuilders;
import java.util.Iterator;
import java.util.LinkedList;
import com.google.android.gms.analytics.Tracker;
import java.util.HashMap;
import com.google.android.gms.internal.d;
import com.google.android.gms.analytics.ecommerce.Product;
import com.google.android.gms.analytics.ecommerce.Promotion;
import java.util.HashSet;
import android.content.Context;
import java.util.Arrays;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.a;
import java.util.Set;
import java.util.Map;
import java.util.List;

class dj extends dg
{
    private static final String ID;
    private static final String arS;
    private static final String arT;
    private static final String arU;
    private static final String arV;
    private static final String arW;
    private static final String arX;
    private static final String arY;
    private static final String arZ;
    private static final String asa;
    private static final List<String> asb;
    private static Map<String, String> asc;
    private static Map<String, String> asd;
    private final DataLayer anS;
    private final Set<String> ase;
    private final df asf;
    
    static {
        ID = a.aO.toString();
        arS = b.bj.toString();
        arT = b.bs.toString();
        arU = b.cE.toString();
        arV = b.cz.toString();
        arW = b.cy.toString();
        arX = b.br.toString();
        arY = b.eN.toString();
        arZ = b.eQ.toString();
        asa = b.eS.toString();
        asb = Arrays.asList("detail", "checkout", "checkout_option", "click", "add", "remove", "purchase", "refund");
    }
    
    public dj(final Context context, final DataLayer dataLayer) {
        this(context, dataLayer, new df(context));
    }
    
    dj(final Context context, final DataLayer anS, final df asf) {
        super(dj.ID, new String[0]);
        this.anS = anS;
        this.asf = asf;
        (this.ase = new HashSet<String>()).add("");
        this.ase.add("0");
        this.ase.add("false");
    }
    
    private Promotion M(final Map<String, String> map) {
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
    
    private Product N(final Map<String, Object> map) {
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
            product.setPosition(this.z(value7));
        }
        final Object value8 = map.get("price");
        if (value8 != null) {
            product.setPrice(this.y(value8));
        }
        final Object value9 = map.get("quantity");
        if (value9 != null) {
            product.setQuantity(this.z(value9));
        }
        return product;
    }
    
    private Map<String, String> O(final Map<String, d.a> map) {
        final d.a a = map.get(dj.arZ);
        if (a != null) {
            return this.c(a);
        }
        if (dj.asc == null) {
            final HashMap<String, String> asc = new HashMap<String, String>();
            asc.put("transactionId", "&ti");
            asc.put("transactionAffiliation", "&ta");
            asc.put("transactionTax", "&tt");
            asc.put("transactionShipping", "&ts");
            asc.put("transactionTotal", "&tr");
            asc.put("transactionCurrency", "&cu");
            dj.asc = asc;
        }
        return dj.asc;
    }
    
    private Map<String, String> P(final Map<String, d.a> map) {
        final d.a a = map.get(dj.asa);
        if (a != null) {
            return this.c(a);
        }
        if (dj.asd == null) {
            final HashMap<String, String> asd = new HashMap<String, String>();
            asd.put("name", "&in");
            asd.put("sku", "&ic");
            asd.put("category", "&iv");
            asd.put("price", "&ip");
            asd.put("quantity", "&iq");
            asd.put("currency", "&cu");
            dj.asd = asd;
        }
        return dj.asd;
    }
    
    private void a(final Tracker tracker, final Map<String, d.a> map) {
        final String cz = this.cZ("transactionId");
        if (cz == null) {
            bh.T("Cannot find transactionId in data layer.");
        }
        else {
            final LinkedList<Map<String, String>> list = new LinkedList<Map<String, String>>();
            Map<String, String> p2;
            try {
                p2 = this.p(map.get(dj.arX));
                p2.put("&t", "transaction");
                for (final Map.Entry<String, String> entry : this.O(map).entrySet()) {
                    this.b(p2, entry.getValue(), this.cZ(entry.getKey()));
                }
            }
            catch (IllegalArgumentException ex) {
                bh.b("Unable to send transaction", ex);
                return;
            }
            list.add(p2);
            final List<Map<String, String>> da = this.da("transactionProducts");
            if (da != null) {
                for (final Map<String, String> map2 : da) {
                    if (map2.get("name") == null) {
                        bh.T("Unable to send transaction item hit due to missing 'name' field.");
                        return;
                    }
                    final Map<String, String> p3 = this.p(map.get(dj.arX));
                    p3.put("&t", "item");
                    p3.put("&ti", cz);
                    for (final Map.Entry<String, String> entry2 : this.P(map).entrySet()) {
                        this.b(p3, entry2.getValue(), map2.get(entry2.getKey()));
                    }
                    list.add(p3);
                }
            }
            final Iterator<Object> iterator4 = list.iterator();
            while (iterator4.hasNext()) {
                tracker.send(iterator4.next());
            }
        }
    }
    
    private void b(final Tracker tracker, Map<String, d.a> map) {
        final HitBuilders.ScreenViewBuilder screenViewBuilder = new HitBuilders.ScreenViewBuilder();
        final Map<String, String> p = this.p(map.get(dj.arX));
        screenViewBuilder.setAll(p);
        if (this.f(map, dj.arV)) {
            final Object value = this.anS.get("ecommerce");
            if (value instanceof Map) {
                map = (Map<String, d.a>)value;
            }
            else {
                map = null;
            }
        }
        else {
            final Object o = di.o(map.get(dj.arW));
            if (o instanceof Map) {
                map = (Map<String, d.a>)o;
            }
            else {
                map = null;
            }
        }
        while (true) {
            String s;
            d.a value2;
            Object o2;
            int n;
            final String s2;
            ProductAction c;
            Label_0711:Label_0456_Outer:
            while (true) {
                if (map == null) {
                    break Label_0658;
                }
                if ((s = p.get("&cu")) == null) {
                    s = (String)map.get("currencyCode");
                }
                if (s != null) {
                    screenViewBuilder.set("&cu", s);
                }
                value2 = map.get("impressions");
                if (value2 instanceof List) {
                    for (final Map<K, String> map2 : (List<Map>)value2) {
                        try {
                            screenViewBuilder.addImpression(this.N((Map<String, Object>)map2), map2.get("list"));
                        }
                        catch (RuntimeException ex) {
                            bh.T("Failed to extract a product from DataLayer. " + ex.getMessage());
                        }
                    }
                }
                if (map.containsKey("promoClick")) {
                    o2 = ((Map)map.get("promoClick")).get("promotions");
                }
                else {
                    if (!map.containsKey("promoView")) {
                        break Label_0711;
                    }
                    o2 = ((Map)map.get("promoView")).get("promotions");
                }
                Label_0620: {
                    while (true) {
                        Label_0615: {
                            if (o2 == null) {
                                break Label_0615;
                            }
                            for (final Map<String, String> map3 : o2) {
                                try {
                                    screenViewBuilder.addPromotion(this.M(map3));
                                }
                                catch (RuntimeException ex2) {
                                    bh.T("Failed to extract a promotion from DataLayer. " + ex2.getMessage());
                                }
                            }
                            if (!map.containsKey("promoClick")) {
                                screenViewBuilder.set("&promoa", "view");
                                break Label_0615;
                            }
                            screenViewBuilder.set("&promoa", "click");
                            n = 0;
                            if (n != 0) {
                                for (final String s2 : dj.asb) {
                                    if (map.containsKey(s2)) {
                                        map = (Map<String, d.a>)map.get(s2);
                                        for (final Map<String, Object> map4 : (List)map.get("products")) {
                                            try {
                                                screenViewBuilder.addProduct(this.N(map4));
                                            }
                                            catch (RuntimeException ex3) {
                                                bh.T("Failed to extract a product from DataLayer. " + ex3.getMessage());
                                            }
                                        }
                                        break Label_0620;
                                    }
                                }
                                break Label_0658;
                            }
                            break Label_0658;
                        }
                        n = 1;
                        continue;
                    }
                    try {
                        if (map.containsKey("actionField")) {
                            c = this.c(s2, (Map<String, Object>)map.get("actionField"));
                        }
                        else {
                            c = new ProductAction(s2);
                        }
                        screenViewBuilder.setProductAction(c);
                        tracker.send(screenViewBuilder.build());
                        return;
                    }
                    catch (RuntimeException ex4) {
                        bh.T("Failed to extract a product action from DataLayer. " + ex4.getMessage());
                        continue Label_0456_Outer;
                    }
                }
                break;
            }
            o2 = null;
            continue;
        }
    }
    
    private void b(final Map<String, String> map, final String s, final String s2) {
        if (s2 != null) {
            map.put(s, s2);
        }
    }
    
    private ProductAction c(final String s, final Map<String, Object> map) {
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
            productAction.setTransactionRevenue(this.y(value6));
        }
        final Object value7 = map.get("tax");
        if (value7 != null) {
            productAction.setTransactionTax(this.y(value7));
        }
        final Object value8 = map.get("shipping");
        if (value8 != null) {
            productAction.setTransactionShipping(this.y(value8));
        }
        final Object value9 = map.get("step");
        if (value9 != null) {
            productAction.setCheckoutStep(this.z(value9));
        }
        return productAction;
    }
    
    private Map<String, String> c(final d.a a) {
        final Object o = di.o(a);
        if (!(o instanceof Map)) {
            return null;
        }
        final Map<Object, V> map = (Map<Object, V>)o;
        final LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>();
        for (final Map.Entry<Object, V> entry : map.entrySet()) {
            linkedHashMap.put(entry.getKey().toString(), entry.getValue().toString());
        }
        return linkedHashMap;
    }
    
    private String cZ(final String s) {
        final Object value = this.anS.get(s);
        if (value == null) {
            return null;
        }
        return value.toString();
    }
    
    private List<Map<String, String>> da(final String s) {
        final Object value = this.anS.get(s);
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
    
    private boolean f(final Map<String, d.a> map, final String s) {
        final d.a a = map.get(s);
        return a != null && di.n(a);
    }
    
    private Map<String, String> p(final d.a a) {
        if (a == null) {
            return new HashMap<String, String>();
        }
        final Map<String, String> c = this.c(a);
        if (c == null) {
            return new HashMap<String, String>();
        }
        final String s = c.get("&aip");
        if (s != null && this.ase.contains(s.toLowerCase())) {
            c.remove("&aip");
        }
        return c;
    }
    
    private Double y(final Object o) {
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
    
    private Integer z(final Object o) {
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
    
    @Override
    public void E(final Map<String, d.a> map) {
        final Tracker cr = this.asf.cR("_GTM_DEFAULT_TRACKER_");
        if (this.f(map, dj.arU)) {
            this.b(cr, map);
            return;
        }
        if (this.f(map, dj.arT)) {
            cr.send(this.p(map.get(dj.arX)));
            return;
        }
        if (this.f(map, dj.arY)) {
            this.a(cr, map);
            return;
        }
        bh.W("Ignoring unknown tag.");
    }
}
