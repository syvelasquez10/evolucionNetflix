// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

public class HitBuilders
{
    @Deprecated
    public static class AppViewBuilder extends HitBuilder<AppViewBuilder>
    {
        public AppViewBuilder() {
            u.cy().a(u.a.uS);
            this.set("&t", "appview");
        }
    }
    
    public static class EventBuilder extends HitBuilder<EventBuilder>
    {
        public EventBuilder() {
            u.cy().a(u.a.uG);
            this.set("&t", "event");
        }
        
        public EventBuilder(final String category, final String action) {
            this();
            this.setCategory(category);
            this.setAction(action);
        }
        
        public EventBuilder setAction(final String s) {
            this.set("&ea", s);
            return this;
        }
        
        public EventBuilder setCategory(final String s) {
            this.set("&ec", s);
            return this;
        }
        
        public EventBuilder setLabel(final String s) {
            this.set("&el", s);
            return this;
        }
        
        public EventBuilder setValue(final long n) {
            this.set("&ev", Long.toString(n));
            return this;
        }
    }
    
    public static class ExceptionBuilder extends HitBuilder<ExceptionBuilder>
    {
        public ExceptionBuilder() {
            u.cy().a(u.a.up);
            this.set("&t", "exception");
        }
        
        public ExceptionBuilder setDescription(final String s) {
            this.set("&exd", s);
            return this;
        }
        
        public ExceptionBuilder setFatal(final boolean b) {
            this.set("&exf", ak.u(b));
            return this;
        }
    }
    
    protected static class HitBuilder<T extends HitBuilder>
    {
        private Map<String, String> vl;
        
        protected HitBuilder() {
            this.vl = new HashMap<String, String>();
        }
        
        public Map<String, String> build() {
            return this.vl;
        }
        
        protected String get(final String s) {
            return this.vl.get(s);
        }
        
        public final T set(final String s, final String s2) {
            u.cy().a(u.a.tI);
            if (s != null) {
                this.vl.put(s, s2);
                return (T)this;
            }
            aa.z(" HitBuilder.set() called with a null paramName.");
            return (T)this;
        }
        
        public final T setAll(final Map<String, String> map) {
            u.cy().a(u.a.tJ);
            if (map == null) {
                return (T)this;
            }
            this.vl.putAll(new HashMap<String, String>(map));
            return (T)this;
        }
        
        public T setCampaignParamsFromUrl(String o) {
            u.cy().a(u.a.tL);
            o = ak.O(o);
            if (TextUtils.isEmpty((CharSequence)o)) {
                return (T)this;
            }
            final Map<String, String> n = ak.N(o);
            this.set("&cc", n.get("utm_content"));
            this.set("&cm", n.get("utm_medium"));
            this.set("&cn", n.get("utm_campaign"));
            this.set("&cs", n.get("utm_source"));
            this.set("&ck", n.get("utm_term"));
            this.set("&ci", n.get("utm_id"));
            this.set("&gclid", n.get("gclid"));
            this.set("&dclid", n.get("dclid"));
            this.set("&gmob_t", n.get("gmob_t"));
            return (T)this;
        }
        
        public T setCustomDimension(final int n, final String s) {
            this.set(o.q(n), s);
            return (T)this;
        }
        
        public T setCustomMetric(final int n, final float n2) {
            this.set(o.r(n), Float.toString(n2));
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
            this.set("&ni", ak.u(b));
            return (T)this;
        }
    }
    
    public static class ItemBuilder extends HitBuilder<ItemBuilder>
    {
        public ItemBuilder() {
            u.cy().a(u.a.uH);
            this.set("&t", "item");
        }
        
        public ItemBuilder setCategory(final String s) {
            this.set("&iv", s);
            return this;
        }
        
        public ItemBuilder setCurrencyCode(final String s) {
            this.set("&cu", s);
            return this;
        }
        
        public ItemBuilder setName(final String s) {
            this.set("&in", s);
            return this;
        }
        
        public ItemBuilder setPrice(final double n) {
            this.set("&ip", Double.toString(n));
            return this;
        }
        
        public ItemBuilder setQuantity(final long n) {
            this.set("&iq", Long.toString(n));
            return this;
        }
        
        public ItemBuilder setSku(final String s) {
            this.set("&ic", s);
            return this;
        }
        
        public ItemBuilder setTransactionId(final String s) {
            this.set("&ti", s);
            return this;
        }
    }
    
    public static class ScreenViewBuilder extends HitBuilder<ScreenViewBuilder>
    {
        public ScreenViewBuilder() {
            u.cy().a(u.a.uS);
            this.set("&t", "appview");
        }
    }
    
    public static class SocialBuilder extends HitBuilder<SocialBuilder>
    {
        public SocialBuilder() {
            u.cy().a(u.a.us);
            this.set("&t", "social");
        }
        
        public SocialBuilder setAction(final String s) {
            this.set("&sa", s);
            return this;
        }
        
        public SocialBuilder setNetwork(final String s) {
            this.set("&sn", s);
            return this;
        }
        
        public SocialBuilder setTarget(final String s) {
            this.set("&st", s);
            return this;
        }
    }
    
    public static class TimingBuilder extends HitBuilder<TimingBuilder>
    {
        public TimingBuilder() {
            u.cy().a(u.a.ur);
            this.set("&t", "timing");
        }
        
        public TimingBuilder(final String category, final String variable, final long value) {
            this();
            this.setVariable(variable);
            this.setValue(value);
            this.setCategory(category);
        }
        
        public TimingBuilder setCategory(final String s) {
            this.set("&utc", s);
            return this;
        }
        
        public TimingBuilder setLabel(final String s) {
            this.set("&utl", s);
            return this;
        }
        
        public TimingBuilder setValue(final long n) {
            this.set("&utt", Long.toString(n));
            return this;
        }
        
        public TimingBuilder setVariable(final String s) {
            this.set("&utv", s);
            return this;
        }
    }
    
    public static class TransactionBuilder extends HitBuilder<TransactionBuilder>
    {
        public TransactionBuilder() {
            u.cy().a(u.a.uo);
            this.set("&t", "transaction");
        }
        
        public TransactionBuilder setAffiliation(final String s) {
            this.set("&ta", s);
            return this;
        }
        
        public TransactionBuilder setCurrencyCode(final String s) {
            this.set("&cu", s);
            return this;
        }
        
        public TransactionBuilder setRevenue(final double n) {
            this.set("&tr", Double.toString(n));
            return this;
        }
        
        public TransactionBuilder setShipping(final double n) {
            this.set("&ts", Double.toString(n));
            return this;
        }
        
        public TransactionBuilder setTax(final double n) {
            this.set("&tt", Double.toString(n));
            return this;
        }
        
        public TransactionBuilder setTransactionId(final String s) {
            this.set("&ti", s);
            return this;
        }
    }
}
