// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.nccp;

import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.XmlDomUtils;

public abstract class BaseNccpResponse implements NccpResponse
{
    protected static final String RESPONSE = "nccp:response";
    protected static final String RESPONSE_HEADER = "nccp:responseheader";
    protected static final String RESPONSE_HEADER_PAYLOAD = "nccp:payload";
    protected static final String RESPONSE_PARAMETERS = "nccp:parameters";
    protected static final String RESPONSE_RESULT = "nccp:result";
    protected static final String RESPONSE_RESULT_STATUS_ERROR_ACTIONID = "nccp:actionid";
    protected static final String RESPONSE_RESULT_STATUS_ERROR_CODE = "nccp:code";
    protected static final String RESPONSE_RESULT_STATUS_ERROR_DESC = "nccp:description";
    protected static final String RESPONSE_RESULT_STATUS_SUCCESS = "nccp:success";
    protected static final String TAG = "nf_nccp";
    protected NccpError error;
    protected boolean success;
    
    public BaseNccpResponse(final String s) {
        final Document document = XmlDomUtils.createDocument(s);
        if (Log.isLoggable("nf_nccp", 3)) {
            Log.d("nf_nccp", "Doc: " + document);
        }
        final Element documentElement = document.getDocumentElement();
        this.parseHeader(documentElement);
        if (this.parseStatuses(documentElement)) {
            final NodeList elementsByTagName = documentElement.getElementsByTagName("nccp:result");
            this.verify(elementsByTagName, "No result in response!");
            this.parseResult(elementsByTagName);
            return;
        }
        Log.d("nf_nccp", "NCCP request failed: " + this.error);
    }
    
    private void parseHeader(final Element element) {
        final String firstFoundElementValueByTagName = XmlDomUtils.getFirstFoundElementValueByTagName(element, "nccp:payload");
        if (firstFoundElementValueByTagName == null) {
            throw new IllegalArgumentException("No payload found in response!");
        }
        Log.d("nf_nccp", "Payload found " + firstFoundElementValueByTagName);
    }
    
    private boolean parseStatuses(final Element element) {
        final String firstFoundElementValueByTagName = XmlDomUtils.getFirstFoundElementValueByTagName(element, "nccp:success");
        if (Log.isLoggable("nf_nccp", 3)) {
            Log.d("nf_nccp", "Status: " + firstFoundElementValueByTagName);
        }
        if (!(this.success = "true".equalsIgnoreCase(firstFoundElementValueByTagName))) {
            Log.d("nf_nccp", "Request failed, extract error data");
            this.error = new NccpError(XmlDomUtils.getFirstFoundElementValueByTagName(element, "nccp:code"), XmlDomUtils.getFirstFoundElementValueByTagName(element, "nccp:description"), XmlDomUtils.getFirstFoundElementValueByTagName(element, "nccp:actionid"));
        }
        return this.success;
    }
    
    private void verify(final NodeList list, final String s) {
        this.verify(list, s, true);
    }
    
    private boolean verify(final NodeList list, final String s, final boolean b) {
        boolean b2 = true;
        if (list == null || list.getLength() < 1) {
            if (b) {
                throw new IllegalArgumentException(s);
            }
            Log.w("nf_nccp", "Verify error: " + s);
            b2 = false;
        }
        return b2;
    }
    
    public NccpError getError() {
        return this.error;
    }
    
    public boolean isSuccess() {
        return this.success;
    }
    
    protected abstract void parseResult(final NodeList p0);
}
