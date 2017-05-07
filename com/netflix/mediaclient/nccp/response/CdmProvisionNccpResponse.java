// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.nccp.response;

import org.w3c.dom.Node;
import com.netflix.mediaclient.Log;
import android.util.Base64;
import com.netflix.mediaclient.util.XmlDomUtils;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import com.netflix.mediaclient.nccp.BaseNccpResponse;

public final class CdmProvisionNccpResponse extends BaseNccpResponse
{
    protected static final String KCE_KEY_ID = "nccp:kcekeyid";
    protected static final String KCH_KEY_ID = "nccp:kchkeyid";
    protected static final String KEYRESPONSE = "nccp:keyresponse";
    private byte[] kcekeyid;
    private byte[] kchkeyid;
    private byte[] keyResponse;
    
    public CdmProvisionNccpResponse(final String s) {
        super(s);
    }
    
    private void handleKceKeyId(final NodeList list) {
        for (int i = 0; i < list.getLength(); ++i) {
            final Node item = list.item(i);
            if (item instanceof Element) {
                this.kcekeyid = Base64.decode(XmlDomUtils.getElementText((Element)item), 0);
                if (Log.isLoggable()) {
                    Log.d("nf_nccp", "Kce key id found  after unbase: " + new String(this.kcekeyid));
                }
            }
        }
    }
    
    private void handleKchKeyId(final NodeList list) {
        for (int i = 0; i < list.getLength(); ++i) {
            final Node item = list.item(i);
            if (item instanceof Element) {
                this.kchkeyid = Base64.decode(XmlDomUtils.getElementText((Element)item), 0);
                if (Log.isLoggable()) {
                    Log.d("nf_nccp", "Kch key id found  after unbase: " + new String(this.kchkeyid));
                }
            }
        }
    }
    
    private void handleKeyResponse(final NodeList list) {
        for (int i = 0; i < list.getLength(); ++i) {
            final Node item = list.item(i);
            if (item instanceof Element) {
                this.keyResponse = Base64.decode(XmlDomUtils.getElementText((Element)item), 0);
            }
        }
    }
    
    private void handleResult(final Element element) {
        this.handleKeyResponse(element.getElementsByTagName("nccp:keyresponse"));
        this.handleKceKeyId(element.getElementsByTagName("nccp:kcekeyid"));
        this.handleKchKeyId(element.getElementsByTagName("nccp:kchkeyid"));
    }
    
    public byte[] getKcekeyId() {
        return this.kcekeyid;
    }
    
    public byte[] getKchkeyId() {
        return this.kchkeyid;
    }
    
    public byte[] getKeyResponse() {
        return this.keyResponse;
    }
    
    @Override
    public boolean getStatus() {
        return false;
    }
    
    @Override
    public String getTransaction() {
        return "cdmprovision";
    }
    
    @Override
    protected void parseResult(final NodeList list) {
        for (int i = 0; i < list.getLength(); ++i) {
            final Node item = list.item(i);
            if (item instanceof Element) {
                Log.d("nf_nccp", "Result found");
                this.handleResult((Element)item);
            }
        }
    }
}
