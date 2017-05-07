// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.view;

import android.content.res.TypedArray;
import android.support.v7.appcompat.R$styleable;
import android.view.SubMenu;
import android.view.View;
import android.support.v7.internal.view.menu.o;
import android.support.v7.internal.view.menu.m;
import android.view.MenuItem$OnMenuItemClickListener;
import android.support.v4.view.MenuItemCompat;
import android.view.MenuItem;
import java.lang.reflect.Constructor;
import android.util.Log;
import android.support.v4.view.ActionProvider;
import android.content.res.XmlResourceParser;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;
import android.view.InflateException;
import android.util.Xml;
import android.support.v4.internal.view.SupportMenu;
import android.view.Menu;
import android.util.AttributeSet;
import org.xmlpull.v1.XmlPullParser;
import android.content.ContextWrapper;
import android.app.Activity;
import android.content.Context;
import android.view.MenuInflater;

public class SupportMenuInflater extends MenuInflater
{
    private static final Class<?>[] ACTION_PROVIDER_CONSTRUCTOR_SIGNATURE;
    private static final Class<?>[] ACTION_VIEW_CONSTRUCTOR_SIGNATURE;
    private final Object[] mActionProviderConstructorArguments;
    private final Object[] mActionViewConstructorArguments;
    private Context mContext;
    private Object mRealOwner;
    
    static {
        ACTION_VIEW_CONSTRUCTOR_SIGNATURE = new Class[] { Context.class };
        ACTION_PROVIDER_CONSTRUCTOR_SIGNATURE = SupportMenuInflater.ACTION_VIEW_CONSTRUCTOR_SIGNATURE;
    }
    
    public SupportMenuInflater(final Context mContext) {
        super(mContext);
        this.mContext = mContext;
        this.mActionViewConstructorArguments = new Object[] { mContext };
        this.mActionProviderConstructorArguments = this.mActionViewConstructorArguments;
    }
    
    private Object findRealOwner(final Object o) {
        if (!(o instanceof Activity) && o instanceof ContextWrapper) {
            return this.findRealOwner(((ContextWrapper)o).getBaseContext());
        }
        return o;
    }
    
    private Object getRealOwner() {
        if (this.mRealOwner == null) {
            this.mRealOwner = this.findRealOwner(this.mContext);
        }
        return this.mRealOwner;
    }
    
    private void parseMenu(final XmlPullParser xmlPullParser, final AttributeSet set, final Menu menu) {
        final SupportMenuInflater$MenuState supportMenuInflater$MenuState = new SupportMenuInflater$MenuState(this, menu);
        int i = xmlPullParser.getEventType();
        String name3;
        while (true) {
            while (i != 2) {
                final int next = xmlPullParser.next();
                if ((i = next) == 1) {
                    final int next2 = next;
                    Object o = null;
                    final boolean b = false;
                    int next3 = next2;
                    int j = 0;
                    int n = b ? 1 : 0;
                    while (j == 0) {
                        switch (next3) {
                            case 2: {
                                if (n != 0) {
                                    break;
                                }
                                final String name = xmlPullParser.getName();
                                if (name.equals("group")) {
                                    supportMenuInflater$MenuState.readGroup(set);
                                    break;
                                }
                                if (name.equals("item")) {
                                    supportMenuInflater$MenuState.readItem(set);
                                    break;
                                }
                                if (name.equals("menu")) {
                                    this.parseMenu(xmlPullParser, set, (Menu)supportMenuInflater$MenuState.addSubMenuItem());
                                    break;
                                }
                                o = name;
                                n = 1;
                                break;
                            }
                            case 3: {
                                final String name2 = xmlPullParser.getName();
                                if (n != 0 && name2.equals(o)) {
                                    o = null;
                                    n = 0;
                                    break;
                                }
                                if (name2.equals("group")) {
                                    supportMenuInflater$MenuState.resetGroup();
                                    break;
                                }
                                if (name2.equals("item")) {
                                    if (supportMenuInflater$MenuState.hasAddedItem()) {
                                        break;
                                    }
                                    if (supportMenuInflater$MenuState.itemActionProvider != null && supportMenuInflater$MenuState.itemActionProvider.hasSubMenu()) {
                                        supportMenuInflater$MenuState.addSubMenuItem();
                                        break;
                                    }
                                    supportMenuInflater$MenuState.addItem();
                                    break;
                                }
                                else {
                                    if (name2.equals("menu")) {
                                        j = 1;
                                        break;
                                    }
                                    break;
                                }
                                break;
                            }
                            case 1: {
                                throw new RuntimeException("Unexpected end of document");
                            }
                        }
                        next3 = xmlPullParser.next();
                    }
                    return;
                }
            }
            name3 = xmlPullParser.getName();
            if (name3.equals("menu")) {
                final int next2 = xmlPullParser.next();
                continue;
            }
            break;
        }
        throw new RuntimeException("Expecting menu, got " + name3);
    }
    
    public void inflate(final int n, final Menu menu) {
        if (!(menu instanceof SupportMenu)) {
            super.inflate(n, menu);
            return;
        }
        XmlResourceParser xmlResourceParser = null;
        XmlResourceParser xmlResourceParser2 = null;
        XmlResourceParser layout = null;
        try {
            final XmlResourceParser xmlResourceParser3 = xmlResourceParser2 = (xmlResourceParser = (layout = this.mContext.getResources().getLayout(n)));
            this.parseMenu((XmlPullParser)xmlResourceParser3, Xml.asAttributeSet((XmlPullParser)xmlResourceParser3), menu);
        }
        catch (XmlPullParserException ex) {
            xmlResourceParser = layout;
            throw new InflateException("Error inflating menu XML", (Throwable)ex);
        }
        catch (IOException ex2) {
            xmlResourceParser = xmlResourceParser2;
            throw new InflateException("Error inflating menu XML", (Throwable)ex2);
        }
        finally {
            if (xmlResourceParser != null) {
                xmlResourceParser.close();
            }
        }
    }
}
