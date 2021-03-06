package com.flipkart.benchmark;

import com.flipkart.Data;
import com.flipkart.Response;
import com.flipkart.task.PincodeData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by jitendra.k on 13/07/16.
 */
public class PincodeDataBM extends BenchMark  implements Runnable {

    protected static final String STAGE_URL = "http://ekart-flash-stage-0002.stage.nm.flipkart.com:21004/";
    protected static final String PROD_URL = "https://api.ekartflash.com/";

    private PincodeData pincodeData;

    public PincodeDataBM(long qps, PincodeData pincodeData) {
        this.qps = qps;
        this.pincodeData = pincodeData;
        totalTime = 0L;
        failures = 0;
    }

    @Override
    public void run() {
        CALL_COUNT = RUNTIME * qps;
        for (int i = 0; i < CALL_COUNT; ++i) {
            try {
                Thread.sleep(1000 / qps);
                //System.out.println("Submitting Task ...");
                pincodeData.setUrl(PROD_URL + "api/v1/promiseengine/locations/560034/connection/" + Data.getRandPin() + "/service");
                Future future = threadpool.submit(pincodeData);
                //System.out.println("Task is submitted");
                tasks.add(future);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        threadpool.shutdown();
        int count = 1;
        for(Future f : tasks) {
            try {
                //System.out.println("getting response " + count++);
                Response res = (Response) f.get();
                System.out.println(res);
                totalTime += res.getResponseTime();
                if(res.getResponseCode() != 200)
                    ++failures;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
