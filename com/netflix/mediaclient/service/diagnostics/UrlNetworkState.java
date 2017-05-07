// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.diagnostics;

import org.json.JSONObject;

public class UrlNetworkState
{
    private static final String errorCodeString = "errorcode";
    private static final String errorGroupString = "errorgroup";
    private static final String successString = "success";
    private static final String urlString = "URL";
    private static final String urlTypeString = "urlType";
    private int errorCode;
    private int errorGroup;
    private int result;
    private DiagnosisAgent$UrlStatus status;
    private String url;
    
    public UrlNetworkState(final String url) {
        this.status = DiagnosisAgent$UrlStatus.NOT_TESTED;
        this.errorCode = 0;
        this.errorGroup = 0;
        this.result = 0;
        this.url = url;
    }
    
    public UrlNetworkState(final String url, final DiagnosisAgent$UrlStatus status) {
        this.status = DiagnosisAgent$UrlStatus.NOT_TESTED;
        this.errorCode = 0;
        this.errorGroup = 0;
        this.result = 0;
        this.url = url;
        this.status = status;
    }
    
    public boolean containsNetflix() {
        return this.url != null && this.url.contains("netflix");
    }
    
    public int getErrorCode() {
        return this.errorCode;
    }
    
    public int getErrorGroup() {
        return this.errorGroup;
    }
    
    public int getResult() {
        return this.result;
    }
    
    public DiagnosisAgent$UrlStatus getStatus() {
        return this.status;
    }
    
    public String getUrl() {
        return this.url;
    }
    
    public void setErrorCode(final int errorCode) {
        this.errorCode = errorCode;
    }
    
    public void setErrorGroup(final int errorGroup) {
        this.errorGroup = errorGroup;
    }
    
    public void setResult(final int result) {
        this.result = result;
    }
    
    public void setStatus(final DiagnosisAgent$UrlStatus status) {
        this.status = status;
    }
    
    public JSONObject toJson() {
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("URL", (Object)this.url);
        jsonObject.put("errorgroup", this.errorGroup);
        jsonObject.put("errorcode", this.errorCode);
        String s;
        if (this.result == 0) {
            s = "true";
        }
        else {
            s = "false";
        }
        jsonObject.put("success", (Object)s);
        String s2;
        if (this.containsNetflix()) {
            s2 = "NETFLIX";
        }
        else {
            s2 = "INTERNET";
        }
        jsonObject.put("urlType", (Object)s2);
        return jsonObject;
    }
}
