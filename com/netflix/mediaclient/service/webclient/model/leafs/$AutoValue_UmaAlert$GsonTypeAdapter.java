// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs;

import com.google.gson.stream.JsonWriter;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

public final class $AutoValue_UmaAlert$GsonTypeAdapter extends TypeAdapter<UmaAlert>
{
    private final TypeAdapter<Integer> abTestCellAdapter;
    private final TypeAdapter<Integer> abTestIdAdapter;
    private final TypeAdapter<Boolean> blockingAdapter;
    private final TypeAdapter<String> bodyAdapter;
    private final TypeAdapter<UmaCta> cta1Adapter;
    private final TypeAdapter<UmaCta> cta2Adapter;
    private final TypeAdapter<String> localeAdapter;
    private final TypeAdapter<Long> messageIdAdapter;
    private final TypeAdapter<String> messageNameAdapter;
    private final TypeAdapter<Long> timestampAdapter;
    private final TypeAdapter<String> titleAdapter;
    private final TypeAdapter<String> viewTypeAdapter;
    
    public $AutoValue_UmaAlert$GsonTypeAdapter(final Gson gson) {
        this.abTestCellAdapter = gson.getAdapter(Integer.class);
        this.abTestIdAdapter = gson.getAdapter(Integer.class);
        this.localeAdapter = gson.getAdapter(String.class);
        this.messageNameAdapter = gson.getAdapter(String.class);
        this.messageIdAdapter = gson.getAdapter(Long.class);
        this.viewTypeAdapter = gson.getAdapter(String.class);
        this.blockingAdapter = gson.getAdapter(Boolean.class);
        this.titleAdapter = gson.getAdapter(String.class);
        this.bodyAdapter = gson.getAdapter(String.class);
        this.cta1Adapter = gson.getAdapter(UmaCta.class);
        this.cta2Adapter = gson.getAdapter(UmaCta.class);
        this.timestampAdapter = gson.getAdapter(Long.class);
    }
    
    @Override
    public UmaAlert read(final JsonReader jsonReader) {
        jsonReader.beginObject();
        int intValue = 0;
        int intValue2 = 0;
        String s = null;
        String s2 = null;
        long longValue = 0L;
        String s3 = null;
        boolean booleanValue = false;
        String s4 = null;
        String s5 = null;
        UmaCta umaCta = null;
        UmaCta umaCta2 = null;
        long longValue2 = 0L;
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
                    case "messageId": {
                        longValue = this.messageIdAdapter.read(jsonReader);
                        continue;
                    }
                    case "viewType": {
                        s3 = this.viewTypeAdapter.read(jsonReader);
                        continue;
                    }
                    case "blocking": {
                        booleanValue = this.blockingAdapter.read(jsonReader);
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
                    case "cta1": {
                        umaCta = this.cta1Adapter.read(jsonReader);
                        continue;
                    }
                    case "cta2": {
                        umaCta2 = this.cta2Adapter.read(jsonReader);
                        continue;
                    }
                    case "timestamp": {
                        longValue2 = this.timestampAdapter.read(jsonReader);
                        continue;
                    }
                }
            }
        }
        jsonReader.endObject();
        return new AutoValue_UmaAlert(intValue, intValue2, s, s2, longValue, s3, booleanValue, s4, s5, umaCta, umaCta2, longValue2);
    }
    
    @Override
    public void write(final JsonWriter jsonWriter, final UmaAlert umaAlert) {
        jsonWriter.beginObject();
        jsonWriter.name("abTestCell");
        this.abTestCellAdapter.write(jsonWriter, umaAlert.abTestCell());
        jsonWriter.name("abTestId");
        this.abTestIdAdapter.write(jsonWriter, umaAlert.abTestId());
        if (umaAlert.locale() != null) {
            jsonWriter.name("locale");
            this.localeAdapter.write(jsonWriter, umaAlert.locale());
        }
        if (umaAlert.messageName() != null) {
            jsonWriter.name("messageName");
            this.messageNameAdapter.write(jsonWriter, umaAlert.messageName());
        }
        jsonWriter.name("messageId");
        this.messageIdAdapter.write(jsonWriter, umaAlert.messageId());
        if (umaAlert.viewType() != null) {
            jsonWriter.name("viewType");
            this.viewTypeAdapter.write(jsonWriter, umaAlert.viewType());
        }
        jsonWriter.name("blocking");
        this.blockingAdapter.write(jsonWriter, umaAlert.blocking());
        if (umaAlert.title() != null) {
            jsonWriter.name("title");
            this.titleAdapter.write(jsonWriter, umaAlert.title());
        }
        if (umaAlert.body() != null) {
            jsonWriter.name("body");
            this.bodyAdapter.write(jsonWriter, umaAlert.body());
        }
        if (umaAlert.cta1() != null) {
            jsonWriter.name("cta1");
            this.cta1Adapter.write(jsonWriter, umaAlert.cta1());
        }
        if (umaAlert.cta2() != null) {
            jsonWriter.name("cta2");
            this.cta2Adapter.write(jsonWriter, umaAlert.cta2());
        }
        jsonWriter.name("timestamp");
        this.timestampAdapter.write(jsonWriter, umaAlert.timestamp());
        jsonWriter.endObject();
    }
}
