// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs;

import com.google.gson.stream.JsonWriter;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

public final class AutoValue_EogAlert$GsonTypeAdapter extends TypeAdapter<EogAlert>
{
    private final TypeAdapter<Integer> abTestCellAdapter;
    private final TypeAdapter<Integer> abTestIdAdapter;
    private final TypeAdapter<String> bodyAdapter;
    private final TypeAdapter<String> continueBtnTextAdapter;
    private final TypeAdapter<String> currentPlanIdAdapter;
    private final TypeAdapter<String> currentPlanTierAdapter;
    private final TypeAdapter<String> disclaimerTextAdapter;
    private final TypeAdapter<String> footerLinkTextAdapter;
    private final TypeAdapter<String> footerSuffixAdapter;
    private final TypeAdapter<String> footerTextAdapter;
    private final TypeAdapter<Boolean> hdPlanIsCurrentPlanAdapter;
    private final TypeAdapter<String> hdPlanPlanIdAdapter;
    private final TypeAdapter<String> hdPlanPlanTierAdapter;
    private final TypeAdapter<String> hdPlanPriceAdapter;
    private final TypeAdapter<String> hdPlanTextAdapter;
    private final TypeAdapter<Boolean> isBlockingAdapter;
    private final TypeAdapter<String> localeAdapter;
    private final TypeAdapter<String> messageNameAdapter;
    private final TypeAdapter<Boolean> sdPlanIsCurrentPlanAdapter;
    private final TypeAdapter<String> sdPlanPlanIdAdapter;
    private final TypeAdapter<String> sdPlanPlanTierAdapter;
    private final TypeAdapter<String> sdPlanPriceAdapter;
    private final TypeAdapter<String> sdPlanTextAdapter;
    private final TypeAdapter<String> seeOtherPlansTextAdapter;
    private final TypeAdapter<String> selectPlanTextAdapter;
    private final TypeAdapter<String> skipBtnImpressionTypeAdapter;
    private final TypeAdapter<String> skipBtnTextAdapter;
    private final TypeAdapter<String> templateIdAdapter;
    private final TypeAdapter<String> titleAdapter;
    private final TypeAdapter<Boolean> uhdPlanIsCurrentPlanAdapter;
    private final TypeAdapter<String> uhdPlanPlanIdAdapter;
    private final TypeAdapter<String> uhdPlanPlanTierAdapter;
    private final TypeAdapter<String> uhdPlanPriceAdapter;
    private final TypeAdapter<String> uhdPlanTextAdapter;
    private final TypeAdapter<String> urlImage1Adapter;
    private final TypeAdapter<String> urlImage2Adapter;
    
    public AutoValue_EogAlert$GsonTypeAdapter(final Gson gson) {
        this.abTestCellAdapter = gson.getAdapter(Integer.class);
        this.abTestIdAdapter = gson.getAdapter(Integer.class);
        this.localeAdapter = gson.getAdapter(String.class);
        this.messageNameAdapter = gson.getAdapter(String.class);
        this.templateIdAdapter = gson.getAdapter(String.class);
        this.titleAdapter = gson.getAdapter(String.class);
        this.bodyAdapter = gson.getAdapter(String.class);
        this.seeOtherPlansTextAdapter = gson.getAdapter(String.class);
        this.continueBtnTextAdapter = gson.getAdapter(String.class);
        this.currentPlanIdAdapter = gson.getAdapter(String.class);
        this.currentPlanTierAdapter = gson.getAdapter(String.class);
        this.skipBtnTextAdapter = gson.getAdapter(String.class);
        this.skipBtnImpressionTypeAdapter = gson.getAdapter(String.class);
        this.footerTextAdapter = gson.getAdapter(String.class);
        this.footerLinkTextAdapter = gson.getAdapter(String.class);
        this.footerSuffixAdapter = gson.getAdapter(String.class);
        this.selectPlanTextAdapter = gson.getAdapter(String.class);
        this.sdPlanTextAdapter = gson.getAdapter(String.class);
        this.sdPlanPriceAdapter = gson.getAdapter(String.class);
        this.sdPlanPlanIdAdapter = gson.getAdapter(String.class);
        this.sdPlanPlanTierAdapter = gson.getAdapter(String.class);
        this.sdPlanIsCurrentPlanAdapter = gson.getAdapter(Boolean.class);
        this.hdPlanTextAdapter = gson.getAdapter(String.class);
        this.hdPlanPriceAdapter = gson.getAdapter(String.class);
        this.hdPlanPlanIdAdapter = gson.getAdapter(String.class);
        this.hdPlanPlanTierAdapter = gson.getAdapter(String.class);
        this.hdPlanIsCurrentPlanAdapter = gson.getAdapter(Boolean.class);
        this.uhdPlanTextAdapter = gson.getAdapter(String.class);
        this.uhdPlanPriceAdapter = gson.getAdapter(String.class);
        this.uhdPlanPlanIdAdapter = gson.getAdapter(String.class);
        this.uhdPlanPlanTierAdapter = gson.getAdapter(String.class);
        this.uhdPlanIsCurrentPlanAdapter = gson.getAdapter(Boolean.class);
        this.disclaimerTextAdapter = gson.getAdapter(String.class);
        this.isBlockingAdapter = gson.getAdapter(Boolean.class);
        this.urlImage1Adapter = gson.getAdapter(String.class);
        this.urlImage2Adapter = gson.getAdapter(String.class);
    }
    
    @Override
    public EogAlert read(final JsonReader jsonReader) {
        jsonReader.beginObject();
        int intValue = 0;
        int intValue2 = 0;
        String s = null;
        String s2 = null;
        String s3 = null;
        String s4 = null;
        String s5 = null;
        String s6 = null;
        String s7 = null;
        String s8 = null;
        String s9 = null;
        String s10 = null;
        String s11 = null;
        String s12 = null;
        String s13 = null;
        String s14 = null;
        String s15 = null;
        String s16 = null;
        String s17 = null;
        String s18 = null;
        String s19 = null;
        boolean booleanValue = false;
        String s20 = null;
        String s21 = null;
        String s22 = null;
        String s23 = null;
        boolean booleanValue2 = false;
        String s24 = null;
        String s25 = null;
        String s26 = null;
        String s27 = null;
        boolean booleanValue3 = false;
        String s28 = null;
        boolean booleanValue4 = false;
        String s29 = null;
        String s30 = null;
        while (jsonReader.hasNext()) {
            final String nextName = jsonReader.nextName();
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.skipValue();
            }
            else {
                switch (nextName) {
                    default: {
                        jsonReader.skipValue();
                        continue;
                    }
                    case "abTestCell": {
                        intValue = this.abTestCellAdapter.read(jsonReader);
                        continue;
                    }
                    case "abTestId": {
                        intValue2 = this.abTestIdAdapter.read(jsonReader);
                        continue;
                    }
                    case "locale": {
                        s = this.localeAdapter.read(jsonReader);
                        continue;
                    }
                    case "messageName": {
                        s2 = this.messageNameAdapter.read(jsonReader);
                        continue;
                    }
                    case "templateId": {
                        s3 = this.templateIdAdapter.read(jsonReader);
                        continue;
                    }
                    case "title": {
                        s4 = this.titleAdapter.read(jsonReader);
                        continue;
                    }
                    case "body": {
                        s5 = this.bodyAdapter.read(jsonReader);
                        continue;
                    }
                    case "seeOtherPlansText": {
                        s6 = this.seeOtherPlansTextAdapter.read(jsonReader);
                        continue;
                    }
                    case "continueBtnText": {
                        s7 = this.continueBtnTextAdapter.read(jsonReader);
                        continue;
                    }
                    case "currentPlanId": {
                        s8 = this.currentPlanIdAdapter.read(jsonReader);
                        continue;
                    }
                    case "currentPlanTier": {
                        s9 = this.currentPlanTierAdapter.read(jsonReader);
                        continue;
                    }
                    case "skipBtnText": {
                        s10 = this.skipBtnTextAdapter.read(jsonReader);
                        continue;
                    }
                    case "skipBtnImpressionType": {
                        s11 = this.skipBtnImpressionTypeAdapter.read(jsonReader);
                        continue;
                    }
                    case "footerText": {
                        s12 = this.footerTextAdapter.read(jsonReader);
                        continue;
                    }
                    case "footerLinkText": {
                        s13 = this.footerLinkTextAdapter.read(jsonReader);
                        continue;
                    }
                    case "footerSuffix": {
                        s14 = this.footerSuffixAdapter.read(jsonReader);
                        continue;
                    }
                    case "selectPlanText": {
                        s15 = this.selectPlanTextAdapter.read(jsonReader);
                        continue;
                    }
                    case "sdPlanText": {
                        s16 = this.sdPlanTextAdapter.read(jsonReader);
                        continue;
                    }
                    case "sdPlanPrice": {
                        s17 = this.sdPlanPriceAdapter.read(jsonReader);
                        continue;
                    }
                    case "sdPlanPlanId": {
                        s18 = this.sdPlanPlanIdAdapter.read(jsonReader);
                        continue;
                    }
                    case "sdPlanPlanTier": {
                        s19 = this.sdPlanPlanTierAdapter.read(jsonReader);
                        continue;
                    }
                    case "sdPlanIsCurrentPlan": {
                        booleanValue = this.sdPlanIsCurrentPlanAdapter.read(jsonReader);
                        continue;
                    }
                    case "hdPlanText": {
                        s20 = this.hdPlanTextAdapter.read(jsonReader);
                        continue;
                    }
                    case "hdPlanPrice": {
                        s21 = this.hdPlanPriceAdapter.read(jsonReader);
                        continue;
                    }
                    case "hdPlanPlanId": {
                        s22 = this.hdPlanPlanIdAdapter.read(jsonReader);
                        continue;
                    }
                    case "hdPlanPlanTier": {
                        s23 = this.hdPlanPlanTierAdapter.read(jsonReader);
                        continue;
                    }
                    case "hdPlanIsCurrentPlan": {
                        booleanValue2 = this.hdPlanIsCurrentPlanAdapter.read(jsonReader);
                        continue;
                    }
                    case "uhdPlanText": {
                        s24 = this.uhdPlanTextAdapter.read(jsonReader);
                        continue;
                    }
                    case "uhdPlanPrice": {
                        s25 = this.uhdPlanPriceAdapter.read(jsonReader);
                        continue;
                    }
                    case "uhdPlanPlanId": {
                        s26 = this.uhdPlanPlanIdAdapter.read(jsonReader);
                        continue;
                    }
                    case "uhdPlanPlanTier": {
                        s27 = this.uhdPlanPlanTierAdapter.read(jsonReader);
                        continue;
                    }
                    case "uhdPlanIsCurrentPlan": {
                        booleanValue3 = this.uhdPlanIsCurrentPlanAdapter.read(jsonReader);
                        continue;
                    }
                    case "disclaimerText": {
                        s28 = this.disclaimerTextAdapter.read(jsonReader);
                        continue;
                    }
                    case "isBlocking": {
                        booleanValue4 = this.isBlockingAdapter.read(jsonReader);
                        continue;
                    }
                    case "urlImage1": {
                        s29 = this.urlImage1Adapter.read(jsonReader);
                        continue;
                    }
                    case "urlImage2": {
                        s30 = this.urlImage2Adapter.read(jsonReader);
                        continue;
                    }
                }
            }
        }
        jsonReader.endObject();
        return new AutoValue_EogAlert(intValue, intValue2, s, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11, s12, s13, s14, s15, s16, s17, s18, s19, booleanValue, s20, s21, s22, s23, booleanValue2, s24, s25, s26, s27, booleanValue3, s28, booleanValue4, s29, s30);
    }
    
    @Override
    public void write(final JsonWriter jsonWriter, final EogAlert eogAlert) {
        jsonWriter.beginObject();
        jsonWriter.name("abTestCell");
        this.abTestCellAdapter.write(jsonWriter, eogAlert.abTestCell());
        jsonWriter.name("abTestId");
        this.abTestIdAdapter.write(jsonWriter, eogAlert.abTestId());
        if (eogAlert.locale() != null) {
            jsonWriter.name("locale");
            this.localeAdapter.write(jsonWriter, eogAlert.locale());
        }
        if (eogAlert.messageName() != null) {
            jsonWriter.name("messageName");
            this.messageNameAdapter.write(jsonWriter, eogAlert.messageName());
        }
        if (eogAlert.templateId() != null) {
            jsonWriter.name("templateId");
            this.templateIdAdapter.write(jsonWriter, eogAlert.templateId());
        }
        if (eogAlert.title() != null) {
            jsonWriter.name("title");
            this.titleAdapter.write(jsonWriter, eogAlert.title());
        }
        if (eogAlert.body() != null) {
            jsonWriter.name("body");
            this.bodyAdapter.write(jsonWriter, eogAlert.body());
        }
        if (eogAlert.seeOtherPlansText() != null) {
            jsonWriter.name("seeOtherPlansText");
            this.seeOtherPlansTextAdapter.write(jsonWriter, eogAlert.seeOtherPlansText());
        }
        if (eogAlert.continueBtnText() != null) {
            jsonWriter.name("continueBtnText");
            this.continueBtnTextAdapter.write(jsonWriter, eogAlert.continueBtnText());
        }
        if (eogAlert.currentPlanId() != null) {
            jsonWriter.name("currentPlanId");
            this.currentPlanIdAdapter.write(jsonWriter, eogAlert.currentPlanId());
        }
        if (eogAlert.currentPlanTier() != null) {
            jsonWriter.name("currentPlanTier");
            this.currentPlanTierAdapter.write(jsonWriter, eogAlert.currentPlanTier());
        }
        if (eogAlert.skipBtnText() != null) {
            jsonWriter.name("skipBtnText");
            this.skipBtnTextAdapter.write(jsonWriter, eogAlert.skipBtnText());
        }
        if (eogAlert.skipBtnImpressionType() != null) {
            jsonWriter.name("skipBtnImpressionType");
            this.skipBtnImpressionTypeAdapter.write(jsonWriter, eogAlert.skipBtnImpressionType());
        }
        if (eogAlert.footerText() != null) {
            jsonWriter.name("footerText");
            this.footerTextAdapter.write(jsonWriter, eogAlert.footerText());
        }
        if (eogAlert.footerLinkText() != null) {
            jsonWriter.name("footerLinkText");
            this.footerLinkTextAdapter.write(jsonWriter, eogAlert.footerLinkText());
        }
        if (eogAlert.footerSuffix() != null) {
            jsonWriter.name("footerSuffix");
            this.footerSuffixAdapter.write(jsonWriter, eogAlert.footerSuffix());
        }
        if (eogAlert.selectPlanText() != null) {
            jsonWriter.name("selectPlanText");
            this.selectPlanTextAdapter.write(jsonWriter, eogAlert.selectPlanText());
        }
        if (eogAlert.sdPlanText() != null) {
            jsonWriter.name("sdPlanText");
            this.sdPlanTextAdapter.write(jsonWriter, eogAlert.sdPlanText());
        }
        if (eogAlert.sdPlanPrice() != null) {
            jsonWriter.name("sdPlanPrice");
            this.sdPlanPriceAdapter.write(jsonWriter, eogAlert.sdPlanPrice());
        }
        if (eogAlert.sdPlanPlanId() != null) {
            jsonWriter.name("sdPlanPlanId");
            this.sdPlanPlanIdAdapter.write(jsonWriter, eogAlert.sdPlanPlanId());
        }
        if (eogAlert.sdPlanPlanTier() != null) {
            jsonWriter.name("sdPlanPlanTier");
            this.sdPlanPlanTierAdapter.write(jsonWriter, eogAlert.sdPlanPlanTier());
        }
        jsonWriter.name("sdPlanIsCurrentPlan");
        this.sdPlanIsCurrentPlanAdapter.write(jsonWriter, eogAlert.sdPlanIsCurrentPlan());
        if (eogAlert.hdPlanText() != null) {
            jsonWriter.name("hdPlanText");
            this.hdPlanTextAdapter.write(jsonWriter, eogAlert.hdPlanText());
        }
        if (eogAlert.hdPlanPrice() != null) {
            jsonWriter.name("hdPlanPrice");
            this.hdPlanPriceAdapter.write(jsonWriter, eogAlert.hdPlanPrice());
        }
        if (eogAlert.hdPlanPlanId() != null) {
            jsonWriter.name("hdPlanPlanId");
            this.hdPlanPlanIdAdapter.write(jsonWriter, eogAlert.hdPlanPlanId());
        }
        if (eogAlert.hdPlanPlanTier() != null) {
            jsonWriter.name("hdPlanPlanTier");
            this.hdPlanPlanTierAdapter.write(jsonWriter, eogAlert.hdPlanPlanTier());
        }
        jsonWriter.name("hdPlanIsCurrentPlan");
        this.hdPlanIsCurrentPlanAdapter.write(jsonWriter, eogAlert.hdPlanIsCurrentPlan());
        if (eogAlert.uhdPlanText() != null) {
            jsonWriter.name("uhdPlanText");
            this.uhdPlanTextAdapter.write(jsonWriter, eogAlert.uhdPlanText());
        }
        if (eogAlert.uhdPlanPrice() != null) {
            jsonWriter.name("uhdPlanPrice");
            this.uhdPlanPriceAdapter.write(jsonWriter, eogAlert.uhdPlanPrice());
        }
        if (eogAlert.uhdPlanPlanId() != null) {
            jsonWriter.name("uhdPlanPlanId");
            this.uhdPlanPlanIdAdapter.write(jsonWriter, eogAlert.uhdPlanPlanId());
        }
        if (eogAlert.uhdPlanPlanTier() != null) {
            jsonWriter.name("uhdPlanPlanTier");
            this.uhdPlanPlanTierAdapter.write(jsonWriter, eogAlert.uhdPlanPlanTier());
        }
        jsonWriter.name("uhdPlanIsCurrentPlan");
        this.uhdPlanIsCurrentPlanAdapter.write(jsonWriter, eogAlert.uhdPlanIsCurrentPlan());
        if (eogAlert.disclaimerText() != null) {
            jsonWriter.name("disclaimerText");
            this.disclaimerTextAdapter.write(jsonWriter, eogAlert.disclaimerText());
        }
        jsonWriter.name("isBlocking");
        this.isBlockingAdapter.write(jsonWriter, eogAlert.isBlocking());
        if (eogAlert.urlImage1() != null) {
            jsonWriter.name("urlImage1");
            this.urlImage1Adapter.write(jsonWriter, eogAlert.urlImage1());
        }
        if (eogAlert.urlImage2() != null) {
            jsonWriter.name("urlImage2");
            this.urlImage2Adapter.write(jsonWriter, eogAlert.urlImage2());
        }
        jsonWriter.endObject();
    }
}
