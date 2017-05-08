// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import com.netflix.mediaclient.service.webclient.model.leafs.EogAlert;

public class DummyEogAlert extends EogAlert
{
    public DummyEogAlert() {
        this.abTestId = 7027;
        this.abTestCell = 5;
        this.isBlocking = false;
        this.locale = "en";
        this.messageName = "END_OF_GRANDFATHERING_Q1_2016";
        this.templateId = "end-of-grandfathering";
        this.title = "Your price is changing soon.";
        this.body = "As of May 30, your price will be $9.99/month. This means more of the shows and movies you love\u2014like <b>House of Cards</b> and <b>Unbreakable Kimmy Schmidt</b>.";
        this.seeOtherPlansText = "See Other Choices";
        this.continueBtnText = "Continue";
        this.currentPlanId = "3088";
        this.currentPlanTier = "GLOBAL_TIER1504";
        this.skipBtnText = "Skip for Now";
        this.skipBtnImpressionType = "ACKNOWLEDGED";
        this.footerText = "You may always cancel your membership by going to your ";
        this.footerLinkText = "Account";
        this.footerSuffix = ".";
        this.selectPlanText = "Select the plan that works for you:";
        this.sdPlanText = "Basic Plan";
        this.sdPlanPrice = "$7.99/month";
        this.sdPlanPlanId = "4001";
        this.sdPlanPlanTier = "GLOBAL_TIER1401";
        this.hdPlanText = "HD Plan";
        this.hdPlanPrice = "$9.99/month";
        this.hdPlanPlanId = "3088";
        this.hdPlanPlanTier = "GLOBAL_TIER1504";
        this.hdPlanIsCurrentPlan = true;
        this.uhdPlanText = "Ultra HD Plan";
        this.uhdPlanPrice = "$11.99/month";
        this.uhdPlanPlanId = "3108";
        this.uhdPlanPlanTier = "GLOBAL_TIER1300";
        this.disclaimerText = "HD and Ultra HD quality depend on your Internet service, device, and content availability.";
        this.urlImage1 = "http://cdn7.nflximg.net/ipl/71797/1ab2b1b6f02f252dc45152498dd3f9f0ea397568.jpg";
        this.urlImage2 = "http://cdn2.nflximg.net/ipl/41547/a184a1d488e02e014e682787e1791fe3e57d6217.jpg";
    }
}
