package vn.supperapp.apigw.db.dto;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "CRON_JOB", schema = "SUPPERAPP", catalog = "")
public class CronJob {
    public static final String TABLE_NAME = "CRON_JOB";
    public static final String SEQ_NAME = "CRON_JOB_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cron_job_generator")
    @SequenceGenerator(name = "cron_job_generator", allocationSize = 1, sequenceName = "cron_job_SEQ")
    @Column(name = "ID", nullable = false)
    private Long id;
    @Basic
    @Column(name = "CONTROLLER")
    private String controller;
    @Basic
    @Column(name = "ACTION")
    private String action;
    @Basic
    @Column(name = "LIMIT")
    private Long limit;
    @Basic
    @Column(name = "OFFSET")
    private Long offset;
    @Basic
    @Column(name = "RUNNING")
    private Long running;
    @Basic
    @Column(name = "SUCCESS")
    private Long success;
    @Basic
    @Column(name = "STARTED_AT")
    private Long startedAt;
    @Basic
    @Column(name = "ENDED_AT")
    private Long endedAt;
    @Basic
    @Column(name = "LAST_EXECUTION_TIME")
    private Double lastExecutionTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getController() {
        return controller;
    }

    public void setController(String controller) {
        this.controller = controller;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Long getLimit() {
        return limit;
    }

    public void setLimit(Long limit) {
        this.limit = limit;
    }

    public Long getOffset() {
        return offset;
    }

    public void setOffset(Long offset) {
        this.offset = offset;
    }

    public Long getRunning() {
        return running;
    }

    public void setRunning(Long running) {
        this.running = running;
    }

    public Long getSuccess() {
        return success;
    }

    public void setSuccess(Long success) {
        this.success = success;
    }

    public Long getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(Long startedAt) {
        this.startedAt = startedAt;
    }

    public Long getEndedAt() {
        return endedAt;
    }

    public void setEndedAt(Long endedAt) {
        this.endedAt = endedAt;
    }

    public Double getLastExecutionTime() {
        return lastExecutionTime;
    }

    public void setLastExecutionTime(Double lastExecutionTime) {
        this.lastExecutionTime = lastExecutionTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CronJob that = (CronJob) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (controller != null ? !controller.equals(that.controller) : that.controller != null) return false;
        if (action != null ? !action.equals(that.action) : that.action != null) return false;
        if (limit != null ? !limit.equals(that.limit) : that.limit != null) return false;
        if (offset != null ? !offset.equals(that.offset) : that.offset != null) return false;
        if (running != null ? !running.equals(that.running) : that.running != null) return false;
        if (success != null ? !success.equals(that.success) : that.success != null) return false;
        if (startedAt != null ? !startedAt.equals(that.startedAt) : that.startedAt != null) return false;
        if (endedAt != null ? !endedAt.equals(that.endedAt) : that.endedAt != null) return false;
        if (lastExecutionTime != null ? !lastExecutionTime.equals(that.lastExecutionTime) : that.lastExecutionTime != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (controller != null ? controller.hashCode() : 0);
        result = 31 * result + (action != null ? action.hashCode() : 0);
        result = 31 * result + (limit != null ? limit.hashCode() : 0);
        result = 31 * result + (offset != null ? offset.hashCode() : 0);
        result = 31 * result + (running != null ? running.hashCode() : 0);
        result = 31 * result + (success != null ? success.hashCode() : 0);
        result = 31 * result + (startedAt != null ? startedAt.hashCode() : 0);
        result = 31 * result + (endedAt != null ? endedAt.hashCode() : 0);
        result = 31 * result + (lastExecutionTime != null ? lastExecutionTime.hashCode() : 0);
        return result;
    }
}
