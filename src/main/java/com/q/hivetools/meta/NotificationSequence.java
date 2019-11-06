package com.q.hivetools.meta;


public class NotificationSequence {

    private Long nni_id;
    private Long next_event_id;

    public void NotificationSequence() {
    }

    public Long getNniId() {
        return nni_id;
    }

    public void setNniId(Long nniId_) {
        nni_id = nniId_;
    }

    public Long getNextEventId() {
        return next_event_id;
    }

    public void setNextEventId(Long nextEventId_) {
        next_event_id = nextEventId_;
    }

}
