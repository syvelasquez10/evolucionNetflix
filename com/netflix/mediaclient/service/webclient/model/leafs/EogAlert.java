// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs;

import com.google.gson.annotations.SerializedName;

public class EogAlert
{
    @SerializedName("abTestCell")
    public int abTestCell;
    @SerializedName("abTestId")
    public int abTestId;
    @SerializedName("body")
    public String body;
    @SerializedName("continueBtnText")
    public String continueBtnText;
    @SerializedName("currentPlanId")
    public String currentPlanId;
    @SerializedName("currentPlanTier")
    public String currentPlanTier;
    @SerializedName("disclaimerText")
    public String disclaimerText;
    @SerializedName("footerLinkText")
    public String footerLinkText;
    @SerializedName("footerSuffix")
    public String footerSuffix;
    @SerializedName("footerText")
    public String footerText;
    @SerializedName("hdPlanIsCurrentPlan")
    public boolean hdPlanIsCurrentPlan;
    @SerializedName("hdPlanPlanId")
    public String hdPlanPlanId;
    @SerializedName("hdPlanPlanTier")
    public String hdPlanPlanTier;
    @SerializedName("hdPlanPrice")
    public String hdPlanPrice;
    @SerializedName("hdPlanText")
    public String hdPlanText;
    @SerializedName("isBlocking")
    public boolean isBlocking;
    public boolean isDirty;
    @SerializedName("locale")
    public String locale;
    @SerializedName("messageName")
    public String messageName;
    @SerializedName("sdPlanIsCurrentPlan")
    public boolean sdPlanIsCurrentPlan;
    @SerializedName("sdPlanPlanId")
    public String sdPlanPlanId;
    @SerializedName("sdPlanPlanTier")
    public String sdPlanPlanTier;
    @SerializedName("sdPlanPrice")
    public String sdPlanPrice;
    @SerializedName("sdPlanText")
    public String sdPlanText;
    @SerializedName("seeOtherPlansText")
    public String seeOtherPlansText;
    @SerializedName("selectPlanText")
    public String selectPlanText;
    @SerializedName("skipBtnImpressionType")
    public String skipBtnImpressionType;
    @SerializedName("skipBtnText")
    public String skipBtnText;
    @SerializedName("templateId")
    public String templateId;
    @SerializedName("title")
    public String title;
    @SerializedName("uhdPlanIsCurrentPlan")
    public boolean uhdPlanIsCurrentPlan;
    @SerializedName("uhdPlanPlanId")
    public String uhdPlanPlanId;
    @SerializedName("uhdPlanPlanTier")
    public String uhdPlanPlanTier;
    @SerializedName("uhdPlanPrice")
    public String uhdPlanPrice;
    @SerializedName("uhdPlanText")
    public String uhdPlanText;
    @SerializedName("urlImage1")
    public String urlImage1;
    @SerializedName("urlImage2")
    public String urlImage2;
    
    @Override
    public String toString() {
        return "EogAlert{isDirty=" + this.isDirty + ", abTestCell=" + this.abTestCell + ", abTestId=" + this.abTestId + ", locale='" + this.locale + '\'' + ", messageName='" + this.messageName + '\'' + ", templateId='" + this.templateId + '\'' + ", title='" + this.title + '\'' + ", body='" + this.body + '\'' + ", seeOtherPlansText='" + this.seeOtherPlansText + '\'' + ", continueBtnText='" + this.continueBtnText + '\'' + ", currentPlanId='" + this.currentPlanId + '\'' + ", currentPlanTier='" + this.currentPlanTier + '\'' + ", skipBtnText='" + this.skipBtnText + '\'' + ", skipBtnImpressionType='" + this.skipBtnImpressionType + '\'' + ", footerText='" + this.footerText + '\'' + ", footerLinkText='" + this.footerLinkText + '\'' + ", footerSuffix='" + this.footerSuffix + '\'' + ", selectPlanText='" + this.selectPlanText + '\'' + ", sdPlanText='" + this.sdPlanText + '\'' + ", sdPlanPrice='" + this.sdPlanPrice + '\'' + ", sdPlanPlanId='" + this.sdPlanPlanId + '\'' + ", sdPlanPlanTier='" + this.sdPlanPlanTier + '\'' + ", sdPlanIsCurrentPlan=" + this.sdPlanIsCurrentPlan + ", hdPlanText='" + this.hdPlanText + '\'' + ", hdPlanPrice='" + this.hdPlanPrice + '\'' + ", hdPlanPlanId='" + this.hdPlanPlanId + '\'' + ", hdPlanPlanTier='" + this.hdPlanPlanTier + '\'' + ", hdPlanIsCurrentPlan=" + this.hdPlanIsCurrentPlan + ", uhdPlanText='" + this.uhdPlanText + '\'' + ", uhdPlanPrice='" + this.uhdPlanPrice + '\'' + ", uhdPlanPlanId='" + this.uhdPlanPlanId + '\'' + ", uhdPlanPlanTier='" + this.uhdPlanPlanTier + '\'' + ", uhdPlanIsCurrentPlan=" + this.uhdPlanIsCurrentPlan + ", disclaimerText='" + this.disclaimerText + '\'' + ", isBlocking=" + this.isBlocking + ", urlImage1='" + this.urlImage1 + '\'' + ", urlImage2='" + this.urlImage2 + '\'' + '}';
    }
}
