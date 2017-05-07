// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.client.model;

import com.netflix.mediaclient.util.JsonUtils;
import org.json.JSONException;
import org.json.JSONObject;
import com.netflix.mediaclient.util.StringUtils;
import com.google.gson.annotations.Since;
import com.google.gson.annotations.SerializedName;
import com.netflix.mediaclient.service.logging.JsonSerializer;

public class HttpResponse implements JsonSerializer
{
    public static final String CONTENT_LENGTH = "contentLength";
    public static final String DNS_TIME = "dnsTime";
    public static final String MIME_TYPE = "mimeType";
    public static final String PARSE_TIME = "parseTime";
    public static final String RESPONSE_TIME = "responseTime";
    public static final String SERVER_EXECUTION_TIME = "serverExecutionTime";
    public static final String SSL_TIME = "sslTime";
    public static final String TRANSFER_TIME = "transferTime";
    @SerializedName("contentLength")
    @Since(1.0)
    private Integer contentLength;
    @SerializedName("dnsTime")
    @Since(1.0)
    private Integer dnsTime;
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
    
    public static HttpResponse createInstance(final String s) throws JSONException {
        if (StringUtils.isEmpty(s)) {
            return null;
        }
        return createInstance(new JSONObject(s));
    }
    
    public static HttpResponse createInstance(final JSONObject jsonObject) throws JSONException {
        if (jsonObject == null) {
            return null;
        }
        final HttpResponse httpResponse = new HttpResponse();
        httpResponse.contentLength = JsonUtils.getIntegerObject(jsonObject, "contentLength", null);
        httpResponse.dnsTime = JsonUtils.getIntegerObject(jsonObject, "dnsTime", null);
        httpResponse.sslTime = JsonUtils.getIntegerObject(jsonObject, "sslTime", null);
        httpResponse.responseTime = JsonUtils.getIntegerObject(jsonObject, "responseTime", null);
        httpResponse.transferTime = JsonUtils.getIntegerObject(jsonObject, "transferTime", null);
        httpResponse.serverExecutionTime = JsonUtils.getIntegerObject(jsonObject, "serverExecutionTime", null);
        httpResponse.parseTime = JsonUtils.getIntegerObject(jsonObject, "parseTime", null);
        httpResponse.mimeType = JsonUtils.getString(jsonObject, "mimeType", null);
        return httpResponse;
    }
    
    public Integer getContentLength() {
        return this.contentLength;
    }
    
    public Integer getDnsTime() {
        return this.dnsTime;
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
    
    public void setContentLength(final Integer contentLength) {
        this.contentLength = contentLength;
    }
    
    public void setDnsTime(final Integer dnsTime) {
        this.dnsTime = dnsTime;
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
    public JSONObject toJSONObject() throws JSONException {
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
        return jsonObject;
    }
    
    @Override
    public String toString() {
        return "HttpResponse [dnsTime=" + this.dnsTime + ", sslTime=" + this.sslTime + ", responseTime=" + this.responseTime + ", transferTime=" + this.transferTime + ", serverExecutionTime=" + this.serverExecutionTime + ", parseTime=" + this.parseTime + ", contentLength=" + this.contentLength + ", mimeType=" + this.mimeType + "]";
    }
}
