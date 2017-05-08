// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs;

import com.google.gson.TypeAdapter;
import com.google.gson.Gson;

public abstract class EogAlert
{
    static final String FIELD_EOG = "eogAlert";
    public boolean isDirty;
    
    public static TypeAdapter<EogAlert> typeAdapter(final Gson gson) {
        return new AutoValue_EogAlert$GsonTypeAdapter(gson);
    }
    
    public abstract int abTestCell();
    
    public abstract int abTestId();
    
    public abstract String body();
    
    public abstract String continueBtnText();
    
    public abstract String currentPlanId();
    
    public abstract String currentPlanTier();
    
    public abstract String disclaimerText();
    
    public abstract String footerLinkText();
    
    public abstract String footerSuffix();
    
    public abstract String footerText();
    
    public abstract boolean hdPlanIsCurrentPlan();
    
    public abstract String hdPlanPlanId();
    
    public abstract String hdPlanPlanTier();
    
    public abstract String hdPlanPrice();
    
    public abstract String hdPlanText();
    
    public abstract boolean isBlocking();
    
    public abstract String locale();
    
    public abstract String messageName();
    
    public abstract boolean sdPlanIsCurrentPlan();
    
    public abstract String sdPlanPlanId();
    
    public abstract String sdPlanPlanTier();
    
    public abstract String sdPlanPrice();
    
    public abstract String sdPlanText();
    
    public abstract String seeOtherPlansText();
    
    public abstract String selectPlanText();
    
    public abstract String skipBtnImpressionType();
    
    public abstract String skipBtnText();
    
    public abstract String templateId();
    
    public abstract String title();
    
    public abstract boolean uhdPlanIsCurrentPlan();
    
    public abstract String uhdPlanPlanId();
    
    public abstract String uhdPlanPlanTier();
    
    public abstract String uhdPlanPrice();
    
    public abstract String uhdPlanText();
    
    public abstract String urlImage1();
    
    public abstract String urlImage2();
}
