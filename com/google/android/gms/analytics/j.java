// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics;

import android.content.res.Resources$NotFoundException;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;
import android.text.TextUtils;
import android.content.res.XmlResourceParser;
import android.content.Context;

abstract class j<T extends i>
{
    Context mContext;
    a<T> xV;
    
    public j(final Context mContext, final a<T> xv) {
        this.mContext = mContext;
        this.xV = xv;
    }
    
    private T a(final XmlResourceParser xmlResourceParser) {
        while (true) {
            while (true) {
                String lowerCase = null;
                try {
                    xmlResourceParser.next();
                    for (int i = xmlResourceParser.getEventType(); i != 1; i = xmlResourceParser.next()) {
                        if (xmlResourceParser.getEventType() == 2) {
                            lowerCase = xmlResourceParser.getName().toLowerCase();
                            if (lowerCase.equals("screenname")) {
                                final String attributeValue = xmlResourceParser.getAttributeValue((String)null, "name");
                                final String trim = xmlResourceParser.nextText().trim();
                                if (!TextUtils.isEmpty((CharSequence)attributeValue) && !TextUtils.isEmpty((CharSequence)trim)) {
                                    this.xV.f(attributeValue, trim);
                                }
                            }
                            else {
                                if (!lowerCase.equals("string")) {
                                    goto Label_0205;
                                }
                                final String attributeValue2 = xmlResourceParser.getAttributeValue((String)null, "name");
                                final String trim2 = xmlResourceParser.nextText().trim();
                                if (!TextUtils.isEmpty((CharSequence)attributeValue2) && trim2 != null) {
                                    this.xV.g(attributeValue2, trim2);
                                }
                            }
                        }
                    }
                    goto Label_0195;
                }
                catch (XmlPullParserException ex) {
                    z.T("Error parsing tracker configuration file: " + ex);
                }
                catch (IOException ex2) {
                    z.T("Error parsing tracker configuration file: " + ex2);
                    goto Label_0195;
                }
                try {
                    final String s;
                    final String s2;
                    this.xV.d(s, Boolean.parseBoolean(s2));
                    continue;
                }
                catch (NumberFormatException ex3) {}
                if (!lowerCase.equals("integer")) {
                    continue;
                }
                final String attributeValue3 = xmlResourceParser.getAttributeValue((String)null, "name");
                final String trim3 = xmlResourceParser.nextText().trim();
                if (!TextUtils.isEmpty((CharSequence)attributeValue3) && !TextUtils.isEmpty((CharSequence)trim3)) {
                    try {
                        this.xV.c(attributeValue3, Integer.parseInt(trim3));
                        continue;
                    }
                    catch (NumberFormatException ex4) {
                        z.T("Error parsing int configuration value: " + trim3);
                        continue;
                    }
                    continue;
                }
                continue;
            }
        }
    }
    
    public T w(final int n) {
        try {
            return this.a(this.mContext.getResources().getXml(n));
        }
        catch (Resources$NotFoundException ex) {
            z.W("inflate() called with unknown resourceId: " + ex);
            return null;
        }
    }
    
    public interface a<U extends i>
    {
        void c(final String p0, final int p1);
        
        void d(final String p0, final boolean p1);
        
        U dX();
        
        void f(final String p0, final String p1);
        
        void g(final String p0, final String p1);
    }
}
