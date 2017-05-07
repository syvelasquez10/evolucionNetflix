// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.query;

import com.google.android.gms.drive.query.internal.LogicalFilter;
import com.google.android.gms.drive.query.internal.Operator;
import com.google.android.gms.drive.query.internal.MatchAllFilter;
import java.util.ArrayList;
import java.util.List;

public class Query$Builder
{
    private String Qu;
    private SortOrder Qv;
    private List<String> Qw;
    private final List<Filter> Qx;
    
    public Query$Builder() {
        this.Qx = new ArrayList<Filter>();
    }
    
    public Query$Builder(final Query query) {
        (this.Qx = new ArrayList<Filter>()).add(query.getFilter());
        this.Qu = query.getPageToken();
        this.Qv = query.getSortOrder();
        this.Qw = query.iq();
    }
    
    public Query$Builder addFilter(final Filter filter) {
        if (!(filter instanceof MatchAllFilter)) {
            this.Qx.add(filter);
        }
        return this;
    }
    
    public Query build() {
        return new Query(new LogicalFilter(Operator.QW, this.Qx), this.Qu, this.Qv, this.Qw);
    }
    
    public Query$Builder setPageToken(final String qu) {
        this.Qu = qu;
        return this;
    }
    
    public Query$Builder setSortOrder(final SortOrder qv) {
        this.Qv = qv;
        return this;
    }
}
