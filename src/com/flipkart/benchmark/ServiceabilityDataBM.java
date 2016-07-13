package com.flipkart.benchmark;

import com.flipkart.Data;
import com.flipkart.Response;
import com.flipkart.task.PincodeData;
import com.flipkart.task.ServiceabilityData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by jitendra.k on 13/07/16.
 */
public class ServiceabilityDataBM extends BenchMark  implements Runnable {

    protected static final String STAGE_URL = "http://ekart-flash-stage-0002.stage.nm.flipkart.com:21004/";
    protected static final String PROD_URL = "https://api.ekartflash.com/";

    private ServiceabilityData serviceabilityData;

    public ServiceabilityDataBM(long qps, ServiceabilityData serviceabilityData) {
        this.qps = qps;
        this.serviceabilityData = serviceabilityData;
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
                serviceabilityData.setUrl(STAGE_URL + "api/v1/serviceability/" + Data.getRandPin() + "/pickup");
                Future future = threadpool.submit(serviceabilityData);
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
                //System.out.println(res);
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
