// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import org.json.JSONObject;
import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;
import com.google.android.gms.R;
import android.app.AlertDialog$Builder;
import android.provider.CalendarContract$Events;
import android.content.Intent;
import android.text.TextUtils;
import java.util.Map;
import android.content.Context;

@ez
public class dc
{
    private final Context mContext;
    private final gv md;
    private final Map<String, String> qM;
    private String qN;
    private long qO;
    private long qP;
    private String qQ;
    private String qR;
    
    public dc(final gv md, final Map<String, String> qm) {
        this.md = md;
        this.qM = qm;
        this.mContext = md.dA();
        this.bG();
    }
    
    private String A(final String s) {
        if (TextUtils.isEmpty((CharSequence)this.qM.get(s))) {
            return "";
        }
        return this.qM.get(s);
    }
    
    private void bG() {
        this.qN = this.A("description");
        this.qQ = this.A("summary");
        this.qO = gj.O(this.qM.get("start"));
        this.qP = gj.O(this.qM.get("end"));
        this.qR = this.A("location");
    }
    
    Intent bH() {
        final Intent setData = new Intent("android.intent.action.EDIT").setData(CalendarContract$Events.CONTENT_URI);
        setData.putExtra("title", this.qQ);
        setData.putExtra("eventLocation", this.qR);
        setData.putExtra("description", this.qN);
        setData.putExtra("beginTime", this.qO);
        setData.putExtra("endTime", this.qP);
        setData.setFlags(268435456);
        return setData;
    }
    
    public void execute() {
        if (!new bl(this.mContext).bo()) {
            gs.W("This feature is not available on this version of the device.");
            return;
        }
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder(this.mContext);
        alertDialog$Builder.setTitle((CharSequence)gb.c(R.string.create_calendar_title, "Create calendar event"));
        alertDialog$Builder.setMessage((CharSequence)gb.c(R.string.create_calendar_message, "Allow Ad to create a calendar event?"));
        alertDialog$Builder.setPositiveButton((CharSequence)gb.c(R.string.accept, "Accept"), (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
                dc.this.mContext.startActivity(dc.this.bH());
            }
        });
        alertDialog$Builder.setNegativeButton((CharSequence)gb.c(R.string.decline, "Decline"), (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
                dc.this.md.b("onCalendarEventCanceled", new JSONObject());
            }
        });
        alertDialog$Builder.create().show();
    }
}
