package com.flipkart.task;

import com.flipkart.HttpHelper;
import com.flipkart.Response;

/**
 * Created by jitendra.k on 13/07/16.
 */
public class PincodeData extends ApiTask {

    private String url;

    public PincodeData() {
    }

    @Override
    public Response call() throws Exception {
        return HttpHelper.sendGet(url);
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
