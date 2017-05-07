// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.client.model;

import com.netflix.mediaclient.util.JsonUtils;
import org.json.JSONObject;
import com.netflix.mediaclient.util.StringUtils;
import com.google.gson.annotations.Since;
import com.google.gson.annotations.SerializedName;
import com.netflix.mediaclient.service.logging.JsonSerializer;

public class HttpResponse implements JsonSerializer
{
    public static final String API_SCRIPT_EXECUTION_TIME = "apiScriptExecutionTime";
    public static final String CONTENT_LENGTH = "contentLength";
    public static final String DNS_TIME = "dnsTime";
    public static final String ENDPOINT_REVISION = "apiScriptRevision";
    public static final String MIME_TYPE = "mimeType";
    public static final String PARSE_TIME = "parseTime";
    public static final String RESPONSE_TIME = "responseTime";
    public static final String SERVER_EXECUTION_TIME = "serverExecutionTime";
    public static final String SSL_TIME = "sslTime";
    public static final String TRANSFER_TIME = "transferTime";
    @SerializedName("apiScriptExecutionTime")
    @Since(2.0)
    private Integer apiScriptExecutionTime;
    @SerializedName("contentLength")
    @Since(1.0)
    private Integer contentLength;
    @SerializedName("dnsTime")
    @Since(1.0)
    private Integer dnsTime;
    @SerializedName("endpointRevision")
    @Since(2.0)
    private String endpointRevision;
    @SerializedName("mimeType")
    @Since(1.0)
    private String mimeType;
    @SerializedName("parseTime")
    @Since(1.0)
    private Integer parseTime;
    @SerializedName("responseTime")
    @Since(1.0)
    private Integer responseTime;
    @SerializedName("serverExecutionTime")
    @Since(1.0)
    private Integer serverExecutionTime;
    @SerializedName("sslTime")
    @Since(1.0)
    private Integer sslTime;
    @SerializedName("transferTime")
    @Since(1.0)
    private Integer transferTime;
    
    public static HttpResponse createInstance(final String s) {
        if (StringUtils.isEmpty(s)) {
            return null;
        }
        return createInstance(new JSONObject(s));
    }
    
    public static HttpResponse createInstance(JSONObject optJSONObject) {
        if (optJSONObject == null) {
            return null;
        }
        final HttpResponse httpResponse = new HttpResponse();
        httpResponse.contentLength = JsonUtils.getIntegerObject(optJSONObject, "contentLength", null);
        httpResponse.dnsTime = JsonUtils.getIntegerObject(optJSONObject, "dnsTime", null);
        httpResponse.sslTime = JsonUtils.getIntegerObject(optJSONObject, "sslTime", null);
        httpResponse.responseTime = JsonUtils.getIntegerObject(optJSONObject, "responseTime", null);
        httpResponse.transferTime = JsonUtils.getIntegerObject(optJSONObject, "transferTime", null);
        httpResponse.serverExecutionTime = JsonUtils.getIntegerObject(optJSONObject, "serverExecutionTime", null);
        httpResponse.parseTime = JsonUtils.getIntegerObject(optJSONObject, "parseTime", null);
        httpResponse.mimeType = JsonUtils.getString(optJSONObject, "mimeType", null);
        optJSONObject = optJSONObject.optJSONObject("custom");
        if (optJSONObject != null) {
            httpResponse.apiScriptExecutionTime = JsonUtils.getIntegerObject(optJSONObject, "apiScriptExecutionTime", null);
            httpResponse.endpointRevision = JsonUtils.getString(optJSONObject, "apiScriptRevision", null);
        }
        return httpResponse;
    }
    
    public Integer getApiScriptExecutionTime() {
        return this.apiScriptExecutionTime;
    }
    
    public Integer getContentLength() {
        return this.contentLength;
    }
    
    public Integer getDnsTime() {
        return this.dnsTime;
    }
    
    public String getEndpointRevision() {
        return this.endpointRevision;
    }
    
    public String getMimeType() {
        return this.mimeType;
    }
    
    public Integer getParseTime() {
        return this.parseTime;
    }
    
    public Integer getResponseTime() {
        return this.responseTime;
    }
    
    public Integer getServerExecutionTime() {
        return this.serverExecutionTime;
    }
    
    public Integer getSslTime() {
        return this.sslTime;
    }
    
    public Integer getTransferTime() {
        return this.transferTime;
    }
    
    public void setApiScriptExecutionTime(final Integer apiScriptExecutionTime) {
        this.apiScriptExecutionTime = apiScriptExecutionTime;
    }
    
    public void setContentLength(final Integer contentLength) {
        this.contentLength = contentLength;
    }
    
    public void setDnsTime(final Integer dnsTime) {
        this.dnsTime = dnsTime;
    }
    
    public void setEndpointRevision(final String endpointRevision) {
        this.endpointRevision = endpointRevision;
    }
    
    public void setMimeType(final String mimeType) {
        this.mimeType = mimeType;
    }
    
    public void setParseTime(final Integer parseTime) {
        this.parseTime = parseTime;
    }
    
    public void setResponseTime(final Integer responseTime) {
        this.responseTime = responseTime;
    }
    
    public void setServerExecutionTime(final Integer serverExecutionTime) {
        this.serverExecutionTime = serverExecutionTime;
    }
    
    public void setSslTime(final Integer sslTime) {
        this.sslTime = sslTime;
    }
    
    public void setTransferTime(final Integer transferTime) {
        this.transferTime = transferTime;
    }
    
    @Override
    public JSONObject toJSONObject() {
        final JSONObject jsonObject = new JSONObject();
        if (this.dnsTime != null) {
            jsonObject.put("dnsTime", (Object)this.dnsTime);
        }
        if (this.sslTime != null) {
            jsonObject.put("sslTime", (Object)this.sslTime);
        }
        if (this.responseTime != null) {
            jsonObject.put("responseTime", (Object)this.responseTime);
        }
        if (this.transferTime != null) {
            jsonObject.put("transferTime", (Object)this.transferTime);
        }
        if (this.serverExecutionTime != null) {
            jsonObject.put("serverExecutionTime", (Object)this.serverExecutionTime);
        }
        if (this.parseTime != null) {
            jsonObject.put("parseTime", (Object)this.parseTime);
        }
        if (this.contentLength != null) {
            jsonObject.put("contentLength", (Object)this.contentLength);
        }
        if (this.mimeType != null) {
            jsonObject.put("mimeType", (Object)this.mimeType);
        }
        if (this.apiScriptExecutionTime != null || this.endpointRevision != null) {
            final JSONObject jsonObject2 = new JSONObject();
            jsonObject.put("custom", (Object)jsonObject2);
            if (this.apiScriptExecutionTime != null) {
                jsonObject2.put("apiScriptExecutionTime", (Object)this.apiScriptExecutionTime);
            }
            if (this.endpointRevision != null) {
                jsonObject2.put("apiScriptRevision", (Object)this.endpointRevision);
            }
        }
        return jsonObject;
    }
    
    @Override
    public String toString() {
        return "HttpResponse [dnsTime=" + this.dnsTime + ", sslTime=" + this.sslTime + ", responseTime=" + this.responseTime + ", transferTime=" + this.transferTime + ", serverExecutionTime=" + this.serverExecutionTime + ", parseTime=" + this.parseTime + ", contentLength=" + this.contentLength + ", mimeType=" + this.mimeType + ", apiScriptExecutionTime=" + this.apiScriptExecutionTime + ", endpointRevision=" + this.endpointRevision + "]";
    }
}
