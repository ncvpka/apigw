package vn.supperapp.apigw.process.tasks;

import vn.supperapp.apigw.process.confgis.ExecutorTaskConfigInfo;
import org.slf4j.Logger;

public abstract class BaseTask implements Runnable {
    protected Logger logger;
    protected ExecutorTaskConfigInfo config;

    public ExecutorTaskConfigInfo getConfig() {
        return config;
    }

    public void setConfig(ExecutorTaskConfigInfo config) {
        this.config = config;
    }
}
