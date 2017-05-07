// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.content.DialogInterface$OnClickListener;
import android.view.KeyEvent;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.support.v7.internal.widget.TintTypedArray;
import android.support.v7.appcompat.R$id;
import android.text.TextUtils;
import android.view.ViewGroup$LayoutParams;
import android.widget.LinearLayout$LayoutParams;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.support.v7.appcompat.R$attr;
import android.support.v7.appcompat.R$styleable;
import android.content.DialogInterface;
import android.view.Window;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ImageView;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.widget.Button;
import android.view.View$OnClickListener;
import android.widget.ListAdapter;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.view.View;
import android.database.Cursor;
import android.content.Context;
import android.widget.ListView;
import android.widget.CursorAdapter;

class AlertController$AlertParams$2 extends CursorAdapter
{
    private final int mIsCheckedIndex;
    private final int mLabelIndex;
    final /* synthetic */ AlertController$AlertParams this$0;
    final /* synthetic */ AlertController val$dialog;
    final /* synthetic */ ListView val$listView;
    
    AlertController$AlertParams$2(final AlertController$AlertParams this$0, final Context context, final Cursor cursor, final boolean b, final ListView val$listView, final AlertController val$dialog) {
        this.this$0 = this$0;
        this.val$listView = val$listView;
        this.val$dialog = val$dialog;
        super(context, cursor, b);
        final Cursor cursor2 = this.getCursor();
        this.mLabelIndex = cursor2.getColumnIndexOrThrow(this.this$0.mLabelColumn);
        this.mIsCheckedIndex = cursor2.getColumnIndexOrThrow(this.this$0.mIsCheckedColumn);
    }
    
    public void bindView(final View view, final Context context, final Cursor cursor) {
        ((CheckedTextView)view.findViewById(16908308)).setText((CharSequence)cursor.getString(this.mLabelIndex));
        this.val$listView.setItemChecked(cursor.getPosition(), cursor.getInt(this.mIsCheckedIndex) == 1);
    }
    
    public View newView(final Context context, final Cursor cursor, final ViewGroup viewGroup) {
        return this.this$0.mInflater.inflate(this.val$dialog.mMultiChoiceItemLayout, viewGroup, false);
    }
}
