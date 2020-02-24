package com.bilgi.ie.thesis;

import java.util.List;

public class AvailableMachine {
    private Machine machine;
    private List<Job> jobs;

    public AvailableMachine() {}

    public AvailableMachine(Machine machine, List<Job> jobs) {
        this.machine = machine;
        this.jobs = jobs;
    }

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    public List<Job> getJobs() {
        return jobs;
    }
    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }
}
