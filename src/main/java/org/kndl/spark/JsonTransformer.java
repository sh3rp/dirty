package org.kndl.spark;

import com.google.gson.Gson;
import spark.ResponseTransformer;

/**
 * Created by skendall on 12/11/2014.
 */
public class JsonTransformer implements ResponseTransformer {
    private final Gson gson = new Gson();
    @Override
    public String render(Object model) throws Exception {
        return gson.toJson(model);
    }
}
