// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs;

abstract class $AutoValue_EogAlert extends EogAlert
{
    private final int abTestCell;
    private final int abTestId;
    private final String body;
    private final String continueBtnText;
    private final String currentPlanId;
    private final String currentPlanTier;
    private final String disclaimerText;
    private final String footerLinkText;
    private final String footerSuffix;
    private final String footerText;
    private final boolean hdPlanIsCurrentPlan;
    private final String hdPlanPlanId;
    private final String hdPlanPlanTier;
    private final String hdPlanPrice;
    private final String hdPlanText;
    private final boolean isBlocking;
    private final String locale;
    private final String messageName;
    private final boolean sdPlanIsCurrentPlan;
    private final String sdPlanPlanId;
    private final String sdPlanPlanTier;
    private final String sdPlanPrice;
    private final String sdPlanText;
    private final String seeOtherPlansText;
    private final String selectPlanText;
    private final String skipBtnImpressionType;
    private final String skipBtnText;
    private final String templateId;
    private final String title;
    private final boolean uhdPlanIsCurrentPlan;
    private final String uhdPlanPlanId;
    private final String uhdPlanPlanTier;
    private final String uhdPlanPrice;
    private final String uhdPlanText;
    private final String urlImage1;
    private final String urlImage2;
    
    $AutoValue_EogAlert(final int abTestCell, final int abTestId, final String locale, final String messageName, final String templateId, final String title, final String body, final String seeOtherPlansText, final String continueBtnText, final String currentPlanId, final String currentPlanTier, final String skipBtnText, final String skipBtnImpressionType, final String footerText, final String footerLinkText, final String footerSuffix, final String selectPlanText, final String sdPlanText, final String sdPlanPrice, final String sdPlanPlanId, final String sdPlanPlanTier, final boolean sdPlanIsCurrentPlan, final String hdPlanText, final String hdPlanPrice, final String hdPlanPlanId, final String hdPlanPlanTier, final boolean hdPlanIsCurrentPlan, final String uhdPlanText, final String uhdPlanPrice, final String uhdPlanPlanId, final String uhdPlanPlanTier, final boolean uhdPlanIsCurrentPlan, final String disclaimerText, final boolean isBlocking, final String urlImage1, final String urlImage2) {
        this.abTestCell = abTestCell;
        this.abTestId = abTestId;
        this.locale = locale;
        this.messageName = messageName;
        this.templateId = templateId;
        this.title = title;
        this.body = body;
        this.seeOtherPlansText = seeOtherPlansText;
        this.continueBtnText = continueBtnText;
        this.currentPlanId = currentPlanId;
        this.currentPlanTier = currentPlanTier;
        this.skipBtnText = skipBtnText;
        this.skipBtnImpressionType = skipBtnImpressionType;
        this.footerText = footerText;
        this.footerLinkText = footerLinkText;
        this.footerSuffix = footerSuffix;
        this.selectPlanText = selectPlanText;
        this.sdPlanText = sdPlanText;
        this.sdPlanPrice = sdPlanPrice;
        this.sdPlanPlanId = sdPlanPlanId;
        this.sdPlanPlanTier = sdPlanPlanTier;
        this.sdPlanIsCurrentPlan = sdPlanIsCurrentPlan;
        this.hdPlanText = hdPlanText;
        this.hdPlanPrice = hdPlanPrice;
        this.hdPlanPlanId = hdPlanPlanId;
        this.hdPlanPlanTier = hdPlanPlanTier;
        this.hdPlanIsCurrentPlan = hdPlanIsCurrentPlan;
        this.uhdPlanText = uhdPlanText;
        this.uhdPlanPrice = uhdPlanPrice;
        this.uhdPlanPlanId = uhdPlanPlanId;
        this.uhdPlanPlanTier = uhdPlanPlanTier;
        this.uhdPlanIsCurrentPlan = uhdPlanIsCurrentPlan;
        this.disclaimerText = disclaimerText;
        this.isBlocking = isBlocking;
        this.urlImage1 = urlImage1;
        this.urlImage2 = urlImage2;
    }
    
    @Override
    public int abTestCell() {
        return this.abTestCell;
    }
    
    @Override
    public int abTestId() {
        return this.abTestId;
    }
    
    @Override
    public String body() {
        return this.body;
    }
    
    @Override
    public String continueBtnText() {
        return this.continueBtnText;
    }
    
    @Override
    public String currentPlanId() {
        return this.currentPlanId;
    }
    
    @Override
    public String currentPlanTier() {
        return this.currentPlanTier;
    }
    
    @Override
    public String disclaimerText() {
        return this.disclaimerText;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o != this) {
            if (o instanceof EogAlert) {
                final EogAlert eogAlert = (EogAlert)o;
                if (this.abTestCell == eogAlert.abTestCell() && this.abTestId == eogAlert.abTestId()) {
                    if (this.locale == null) {
                        if (eogAlert.locale() != null) {
                            return false;
                        }
                    }
                    else if (!this.locale.equals(eogAlert.locale())) {
                        return false;
                    }
                    if (this.messageName == null) {
                        if (eogAlert.messageName() != null) {
                            return false;
                        }
                    }
                    else if (!this.messageName.equals(eogAlert.messageName())) {
                        return false;
                    }
                    if (this.templateId == null) {
                        if (eogAlert.templateId() != null) {
                            return false;
                        }
                    }
                    else if (!this.templateId.equals(eogAlert.templateId())) {
                        return false;
                    }
                    if (this.title == null) {
                        if (eogAlert.title() != null) {
                            return false;
                        }
                    }
                    else if (!this.title.equals(eogAlert.title())) {
                        return false;
                    }
                    if (this.body == null) {
                        if (eogAlert.body() != null) {
                            return false;
                        }
                    }
                    else if (!this.body.equals(eogAlert.body())) {
                        return false;
                    }
                    if (this.seeOtherPlansText == null) {
                        if (eogAlert.seeOtherPlansText() != null) {
                            return false;
                        }
                    }
                    else if (!this.seeOtherPlansText.equals(eogAlert.seeOtherPlansText())) {
                        return false;
                    }
                    if (this.continueBtnText == null) {
                        if (eogAlert.continueBtnText() != null) {
                            return false;
                        }
                    }
                    else if (!this.continueBtnText.equals(eogAlert.continueBtnText())) {
                        return false;
                    }
                    if (this.currentPlanId == null) {
                        if (eogAlert.currentPlanId() != null) {
                            return false;
                        }
                    }
                    else if (!this.currentPlanId.equals(eogAlert.currentPlanId())) {
                        return false;
                    }
                    if (this.currentPlanTier == null) {
                        if (eogAlert.currentPlanTier() != null) {
                            return false;
                        }
                    }
                    else if (!this.currentPlanTier.equals(eogAlert.currentPlanTier())) {
                        return false;
                    }
                    if (this.skipBtnText == null) {
                        if (eogAlert.skipBtnText() != null) {
                            return false;
                        }
                    }
                    else if (!this.skipBtnText.equals(eogAlert.skipBtnText())) {
                        return false;
                    }
                    if (this.skipBtnImpressionType == null) {
                        if (eogAlert.skipBtnImpressionType() != null) {
                            return false;
                        }
                    }
                    else if (!this.skipBtnImpressionType.equals(eogAlert.skipBtnImpressionType())) {
                        return false;
                    }
                    if (this.footerText == null) {
                        if (eogAlert.footerText() != null) {
                            return false;
                        }
                    }
                    else if (!this.footerText.equals(eogAlert.footerText())) {
                        return false;
                    }
                    if (this.footerLinkText == null) {
                        if (eogAlert.footerLinkText() != null) {
                            return false;
                        }
                    }
                    else if (!this.footerLinkText.equals(eogAlert.footerLinkText())) {
                        return false;
                    }
                    if (this.footerSuffix == null) {
                        if (eogAlert.footerSuffix() != null) {
                            return false;
                        }
                    }
                    else if (!this.footerSuffix.equals(eogAlert.footerSuffix())) {
                        return false;
                    }
                    if (this.selectPlanText == null) {
                        if (eogAlert.selectPlanText() != null) {
                            return false;
                        }
                    }
                    else if (!this.selectPlanText.equals(eogAlert.selectPlanText())) {
                        return false;
                    }
                    if (this.sdPlanText == null) {
                        if (eogAlert.sdPlanText() != null) {
                            return false;
                        }
                    }
                    else if (!this.sdPlanText.equals(eogAlert.sdPlanText())) {
                        return false;
                    }
                    if (this.sdPlanPrice == null) {
                        if (eogAlert.sdPlanPrice() != null) {
                            return false;
                        }
                    }
                    else if (!this.sdPlanPrice.equals(eogAlert.sdPlanPrice())) {
                        return false;
                    }
                    if (this.sdPlanPlanId == null) {
                        if (eogAlert.sdPlanPlanId() != null) {
                            return false;
                        }
                    }
                    else if (!this.sdPlanPlanId.equals(eogAlert.sdPlanPlanId())) {
                        return false;
                    }
                    if (this.sdPlanPlanTier == null) {
                        if (eogAlert.sdPlanPlanTier() != null) {
                            return false;
                        }
                    }
                    else if (!this.sdPlanPlanTier.equals(eogAlert.sdPlanPlanTier())) {
                        return false;
                    }
                    if (this.sdPlanIsCurrentPlan == eogAlert.sdPlanIsCurrentPlan()) {
                        if (this.hdPlanText == null) {
                            if (eogAlert.hdPlanText() != null) {
                                return false;
                            }
                        }
                        else if (!this.hdPlanText.equals(eogAlert.hdPlanText())) {
                            return false;
                        }
                        if (this.hdPlanPrice == null) {
                            if (eogAlert.hdPlanPrice() != null) {
                                return false;
                            }
                        }
                        else if (!this.hdPlanPrice.equals(eogAlert.hdPlanPrice())) {
                            return false;
                        }
                        if (this.hdPlanPlanId == null) {
                            if (eogAlert.hdPlanPlanId() != null) {
                                return false;
                            }
                        }
                        else if (!this.hdPlanPlanId.equals(eogAlert.hdPlanPlanId())) {
                            return false;
                        }
                        if (this.hdPlanPlanTier == null) {
                            if (eogAlert.hdPlanPlanTier() != null) {
                                return false;
                            }
                        }
                        else if (!this.hdPlanPlanTier.equals(eogAlert.hdPlanPlanTier())) {
                            return false;
                        }
                        if (this.hdPlanIsCurrentPlan == eogAlert.hdPlanIsCurrentPlan()) {
                            if (this.uhdPlanText == null) {
                                if (eogAlert.uhdPlanText() != null) {
                                    return false;
                                }
                            }
                            else if (!this.uhdPlanText.equals(eogAlert.uhdPlanText())) {
                                return false;
                            }
                            if (this.uhdPlanPrice == null) {
                                if (eogAlert.uhdPlanPrice() != null) {
                                    return false;
                                }
                            }
                            else if (!this.uhdPlanPrice.equals(eogAlert.uhdPlanPrice())) {
                                return false;
                            }
                            if (this.uhdPlanPlanId == null) {
                                if (eogAlert.uhdPlanPlanId() != null) {
                                    return false;
                                }
                            }
                            else if (!this.uhdPlanPlanId.equals(eogAlert.uhdPlanPlanId())) {
                                return false;
                            }
                            if (this.uhdPlanPlanTier == null) {
                                if (eogAlert.uhdPlanPlanTier() != null) {
                                    return false;
                                }
                            }
                            else if (!this.uhdPlanPlanTier.equals(eogAlert.uhdPlanPlanTier())) {
                                return false;
                            }
                            if (this.uhdPlanIsCurrentPlan == eogAlert.uhdPlanIsCurrentPlan()) {
                                if (this.disclaimerText == null) {
                                    if (eogAlert.disclaimerText() != null) {
                                        return false;
                                    }
                                }
                                else if (!this.disclaimerText.equals(eogAlert.disclaimerText())) {
                                    return false;
                                }
                                if (this.isBlocking == eogAlert.isBlocking()) {
                                    if (this.urlImage1 == null) {
                                        if (eogAlert.urlImage1() != null) {
                                            return false;
                                        }
                                    }
                                    else if (!this.urlImage1.equals(eogAlert.urlImage1())) {
                                        return false;
                                    }
                                    if (this.urlImage2 == null) {
                                        if (eogAlert.urlImage2() == null) {
                                            return true;
                                        }
                                    }
                                    else if (this.urlImage2.equals(eogAlert.urlImage2())) {
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
                return false;
            }
            return false;
        }
        return true;
    }
    
    @Override
    public String footerLinkText() {
        return this.footerLinkText;
    }
    
    @Override
    public String footerSuffix() {
        return this.footerSuffix;
    }
    
    @Override
    public String footerText() {
        return this.footerText;
    }
    
    @Override
    public int hashCode() {
        int n = 1231;
        int hashCode = 0;
        final int abTestCell = this.abTestCell;
        final int abTestId = this.abTestId;
        int hashCode2;
        if (this.locale == null) {
            hashCode2 = 0;
        }
        else {
            hashCode2 = this.locale.hashCode();
        }
        int hashCode3;
        if (this.messageName == null) {
            hashCode3 = 0;
        }
        else {
            hashCode3 = this.messageName.hashCode();
        }
        int hashCode4;
        if (this.templateId == null) {
            hashCode4 = 0;
        }
        else {
            hashCode4 = this.templateId.hashCode();
        }
        int hashCode5;
        if (this.title == null) {
            hashCode5 = 0;
        }
        else {
            hashCode5 = this.title.hashCode();
        }
        int hashCode6;
        if (this.body == null) {
            hashCode6 = 0;
        }
        else {
            hashCode6 = this.body.hashCode();
        }
        int hashCode7;
        if (this.seeOtherPlansText == null) {
            hashCode7 = 0;
        }
        else {
            hashCode7 = this.seeOtherPlansText.hashCode();
        }
        int hashCode8;
        if (this.continueBtnText == null) {
            hashCode8 = 0;
        }
        else {
            hashCode8 = this.continueBtnText.hashCode();
        }
        int hashCode9;
        if (this.currentPlanId == null) {
            hashCode9 = 0;
        }
        else {
            hashCode9 = this.currentPlanId.hashCode();
        }
        int hashCode10;
        if (this.currentPlanTier == null) {
            hashCode10 = 0;
        }
        else {
            hashCode10 = this.currentPlanTier.hashCode();
        }
        int hashCode11;
        if (this.skipBtnText == null) {
            hashCode11 = 0;
        }
        else {
            hashCode11 = this.skipBtnText.hashCode();
        }
        int hashCode12;
        if (this.skipBtnImpressionType == null) {
            hashCode12 = 0;
        }
        else {
            hashCode12 = this.skipBtnImpressionType.hashCode();
        }
        int hashCode13;
        if (this.footerText == null) {
            hashCode13 = 0;
        }
        else {
            hashCode13 = this.footerText.hashCode();
        }
        int hashCode14;
        if (this.footerLinkText == null) {
            hashCode14 = 0;
        }
        else {
            hashCode14 = this.footerLinkText.hashCode();
        }
        int hashCode15;
        if (this.footerSuffix == null) {
            hashCode15 = 0;
        }
        else {
            hashCode15 = this.footerSuffix.hashCode();
        }
        int hashCode16;
        if (this.selectPlanText == null) {
            hashCode16 = 0;
        }
        else {
            hashCode16 = this.selectPlanText.hashCode();
        }
        int hashCode17;
        if (this.sdPlanText == null) {
            hashCode17 = 0;
        }
        else {
            hashCode17 = this.sdPlanText.hashCode();
        }
        int hashCode18;
        if (this.sdPlanPrice == null) {
            hashCode18 = 0;
        }
        else {
            hashCode18 = this.sdPlanPrice.hashCode();
        }
        int hashCode19;
        if (this.sdPlanPlanId == null) {
            hashCode19 = 0;
        }
        else {
            hashCode19 = this.sdPlanPlanId.hashCode();
        }
        int hashCode20;
        if (this.sdPlanPlanTier == null) {
            hashCode20 = 0;
        }
        else {
            hashCode20 = this.sdPlanPlanTier.hashCode();
        }
        int n2;
        if (this.sdPlanIsCurrentPlan) {
            n2 = 1231;
        }
        else {
            n2 = 1237;
        }
        int hashCode21;
        if (this.hdPlanText == null) {
            hashCode21 = 0;
        }
        else {
            hashCode21 = this.hdPlanText.hashCode();
        }
        int hashCode22;
        if (this.hdPlanPrice == null) {
            hashCode22 = 0;
        }
        else {
            hashCode22 = this.hdPlanPrice.hashCode();
        }
        int hashCode23;
        if (this.hdPlanPlanId == null) {
            hashCode23 = 0;
        }
        else {
            hashCode23 = this.hdPlanPlanId.hashCode();
        }
        int hashCode24;
        if (this.hdPlanPlanTier == null) {
            hashCode24 = 0;
        }
        else {
            hashCode24 = this.hdPlanPlanTier.hashCode();
        }
        int n3;
        if (this.hdPlanIsCurrentPlan) {
            n3 = 1231;
        }
        else {
            n3 = 1237;
        }
        int hashCode25;
        if (this.uhdPlanText == null) {
            hashCode25 = 0;
        }
        else {
            hashCode25 = this.uhdPlanText.hashCode();
        }
        int hashCode26;
        if (this.uhdPlanPrice == null) {
            hashCode26 = 0;
        }
        else {
            hashCode26 = this.uhdPlanPrice.hashCode();
        }
        int hashCode27;
        if (this.uhdPlanPlanId == null) {
            hashCode27 = 0;
        }
        else {
            hashCode27 = this.uhdPlanPlanId.hashCode();
        }
        int hashCode28;
        if (this.uhdPlanPlanTier == null) {
            hashCode28 = 0;
        }
        else {
            hashCode28 = this.uhdPlanPlanTier.hashCode();
        }
        int n4;
        if (this.uhdPlanIsCurrentPlan) {
            n4 = 1231;
        }
        else {
            n4 = 1237;
        }
        int hashCode29;
        if (this.disclaimerText == null) {
            hashCode29 = 0;
        }
        else {
            hashCode29 = this.disclaimerText.hashCode();
        }
        if (!this.isBlocking) {
            n = 1237;
        }
        int hashCode30;
        if (this.urlImage1 == null) {
            hashCode30 = 0;
        }
        else {
            hashCode30 = this.urlImage1.hashCode();
        }
        if (this.urlImage2 != null) {
            hashCode = this.urlImage2.hashCode();
        }
        return (hashCode30 ^ ((hashCode29 ^ (n4 ^ (hashCode28 ^ (hashCode27 ^ (hashCode26 ^ (hashCode25 ^ (n3 ^ (hashCode24 ^ (hashCode23 ^ (hashCode22 ^ (hashCode21 ^ (n2 ^ (hashCode20 ^ (hashCode19 ^ (hashCode18 ^ (hashCode17 ^ (hashCode16 ^ (hashCode15 ^ (hashCode14 ^ (hashCode13 ^ (hashCode12 ^ (hashCode11 ^ (hashCode10 ^ (hashCode9 ^ (hashCode8 ^ (hashCode7 ^ (hashCode6 ^ (hashCode5 ^ (hashCode4 ^ (hashCode3 ^ (hashCode2 ^ ((abTestCell ^ 0xF4243) * 1000003 ^ abTestId) * 1000003) * 1000003) * 1000003) * 1000003) * 1000003) * 1000003) * 1000003) * 1000003) * 1000003) * 1000003) * 1000003) * 1000003) * 1000003) * 1000003) * 1000003) * 1000003) * 1000003) * 1000003) * 1000003) * 1000003) * 1000003) * 1000003) * 1000003) * 1000003) * 1000003) * 1000003) * 1000003) * 1000003) * 1000003) * 1000003) * 1000003) * 1000003 ^ n) * 1000003) * 1000003 ^ hashCode;
    }
    
    @Override
    public boolean hdPlanIsCurrentPlan() {
        return this.hdPlanIsCurrentPlan;
    }
    
    @Override
    public String hdPlanPlanId() {
        return this.hdPlanPlanId;
    }
    
    @Override
    public String hdPlanPlanTier() {
        return this.hdPlanPlanTier;
    }
    
    @Override
    public String hdPlanPrice() {
        return this.hdPlanPrice;
    }
    
    @Override
    public String hdPlanText() {
        return this.hdPlanText;
    }
    
    @Override
    public boolean isBlocking() {
        return this.isBlocking;
    }
    
    @Override
    public String locale() {
        return this.locale;
    }
    
    @Override
    public String messageName() {
        return this.messageName;
    }
    
    @Override
    public boolean sdPlanIsCurrentPlan() {
        return this.sdPlanIsCurrentPlan;
    }
    
    @Override
    public String sdPlanPlanId() {
        return this.sdPlanPlanId;
    }
    
    @Override
    public String sdPlanPlanTier() {
        return this.sdPlanPlanTier;
    }
    
    @Override
    public String sdPlanPrice() {
        return this.sdPlanPrice;
    }
    
    @Override
    public String sdPlanText() {
        return this.sdPlanText;
    }
    
    @Override
    public String seeOtherPlansText() {
        return this.seeOtherPlansText;
    }
    
    @Override
    public String selectPlanText() {
        return this.selectPlanText;
    }
    
    @Override
    public String skipBtnImpressionType() {
        return this.skipBtnImpressionType;
    }
    
    @Override
    public String skipBtnText() {
        return this.skipBtnText;
    }
    
    @Override
    public String templateId() {
        return this.templateId;
    }
    
    @Override
    public String title() {
        return this.title;
    }
    
    @Override
    public String toString() {
        return "EogAlert{abTestCell=" + this.abTestCell + ", abTestId=" + this.abTestId + ", locale=" + this.locale + ", messageName=" + this.messageName + ", templateId=" + this.templateId + ", title=" + this.title + ", body=" + this.body + ", seeOtherPlansText=" + this.seeOtherPlansText + ", continueBtnText=" + this.continueBtnText + ", currentPlanId=" + this.currentPlanId + ", currentPlanTier=" + this.currentPlanTier + ", skipBtnText=" + this.skipBtnText + ", skipBtnImpressionType=" + this.skipBtnImpressionType + ", footerText=" + this.footerText + ", footerLinkText=" + this.footerLinkText + ", footerSuffix=" + this.footerSuffix + ", selectPlanText=" + this.selectPlanText + ", sdPlanText=" + this.sdPlanText + ", sdPlanPrice=" + this.sdPlanPrice + ", sdPlanPlanId=" + this.sdPlanPlanId + ", sdPlanPlanTier=" + this.sdPlanPlanTier + ", sdPlanIsCurrentPlan=" + this.sdPlanIsCurrentPlan + ", hdPlanText=" + this.hdPlanText + ", hdPlanPrice=" + this.hdPlanPrice + ", hdPlanPlanId=" + this.hdPlanPlanId + ", hdPlanPlanTier=" + this.hdPlanPlanTier + ", hdPlanIsCurrentPlan=" + this.hdPlanIsCurrentPlan + ", uhdPlanText=" + this.uhdPlanText + ", uhdPlanPrice=" + this.uhdPlanPrice + ", uhdPlanPlanId=" + this.uhdPlanPlanId + ", uhdPlanPlanTier=" + this.uhdPlanPlanTier + ", uhdPlanIsCurrentPlan=" + this.uhdPlanIsCurrentPlan + ", disclaimerText=" + this.disclaimerText + ", isBlocking=" + this.isBlocking + ", urlImage1=" + this.urlImage1 + ", urlImage2=" + this.urlImage2 + "}";
    }
    
    @Override
    public boolean uhdPlanIsCurrentPlan() {
        return this.uhdPlanIsCurrentPlan;
    }
    
    @Override
    public String uhdPlanPlanId() {
        return this.uhdPlanPlanId;
    }
    
    @Override
    public String uhdPlanPlanTier() {
        return this.uhdPlanPlanTier;
    }
    
    @Override
    public String uhdPlanPrice() {
        return this.uhdPlanPrice;
    }
    
    @Override
    public String uhdPlanText() {
        return this.uhdPlanText;
    }
    
    @Override
    public String urlImage1() {
        return this.urlImage1;
    }
    
    @Override
    public String urlImage2() {
        return this.urlImage2;
    }
}
