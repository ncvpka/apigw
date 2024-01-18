package vn.supperapp.apigw.process.business;

import com.viettel.mmserver.base.ProcessThreadMX;
import vn.supperapp.apigw.process.confgis.ThreadProcessConfigInfo;

public abstract class BaseProcessBusiness extends ProcessThreadMX {

    protected volatile boolean isRunning = false;
    protected long sleepTime = 5;

    protected ThreadProcessConfigInfo config;

    public BaseProcessBusiness(ThreadProcessConfigInfo config) {
        super(config.getThreadName());
        try {
            registerAgent(String.format("%s:name=%s", config.getThreadName(), config.getThreadName()));

            this.config = config;
            this.sleepTime = config.getIntervalTime();
        } catch (Exception ex) {
            logger.error("#BaseProcessBusiness - ERROR: ", ex);
        }
    }

    protected abstract void process();
}
