package com.flipkart.task;

import com.flipkart.HttpHelper;
import com.flipkart.Response;

import java.util.concurrent.Callable;

/**
 * Created by jitendra.k on 13/07/16.
 */
public abstract class ApiTask implements Callable {

    private String url;

    @Override
    public Response call() throws Exception {
        return HttpHelper.sendGet(url);
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
