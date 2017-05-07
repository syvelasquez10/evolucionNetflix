// 
// Decompiled by Procyon v0.5.30
// 

package test.netflix.model;

import com.google.gson.JsonObject;
import junit.framework.Assert;
import com.google.gson.JsonParser;
import com.netflix.mediaclient.Log;
import java.util.List;
import com.netflix.falkor.PathMapCreator;
import java.util.Map;
import java.util.Collection;
import java.util.Arrays;
import com.netflix.falkor.PQL;
import java.util.ArrayList;

public class PathMapCreatorTest
{
    private static final String TAG = "PathMapCreatorTest";
    
    public void testCreate() {
        final ArrayList<PQL> list = new ArrayList<PQL>();
        list.add(PQL.create("tester", "first", "second", "something"));
        list.add(PQL.create("lolomo", new ArrayList(Arrays.asList(PQL.range(10), 13, 26)), "name"));
        list.add(PQL.create("videos", new ArrayList(Arrays.asList(88, 99)), "summary"));
        list.add(PQL.create("lolomo", new ArrayList(Arrays.asList(PQL.range(20))), new ArrayList(Arrays.asList(PQL.range(5, 15), 56, 57, 58)), "lolomo_name"));
        list.add(PQL.create("videos", PQL.range(4, 16), new ArrayList(Arrays.asList("summary", "detail"))));
        list.add(PQL.create("lolomo", 26, "summary"));
        final JsonObject create = PathMapCreator.create(list);
        Log.v("PathMapCreatorTest", create.toString());
        Assert.assertEquals((Object)new JsonParser().parse("{\"tester\":{\"first\":{\"second\":{\"something\":{\"_path\":true}}}},\"lolomo\":{\"_ranges\":[{\"_rangeFrom\":0,\"_rangeTo\":10,\"_rangeValue\":{\"name\":{\"_path\":true}}},{\"_rangeFrom\":0,\"_rangeTo\":20,\"_rangeValue\":{\"_ranges\":[{\"_rangeFrom\":5,\"_rangeTo\":15,\"_rangeValue\":{\"lolomo_name\":{\"_path\":true}}}],\"56\":{\"lolomo_name\":{\"_path\":true}},\"57\":{\"lolomo_name\":{\"_path\":true}},\"58\":{\"lolomo_name\":{\"_path\":true}}}}],\"13\":{\"name\":{\"_path\":true}},\"26\":{\"name\":{\"_path\":true},\"summary\":{\"_path\":true}}},\"videos\":{\"88\":{\"summary\":{\"_path\":true}},\"99\":{\"summary\":{\"_path\":true}},\"_ranges\":[{\"_rangeFrom\":4,\"_rangeTo\":16,\"_rangeValue\":{\"summary\":{\"_path\":true},\"detail\":{\"_path\":true}}}]}}"), (Object)create);
    }
}
