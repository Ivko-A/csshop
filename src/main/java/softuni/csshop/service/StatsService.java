package softuni.csshop.service;

import java.time.Instant;

public interface StatsService {
    void incRequestCount();
    int getRequestCount();
    Instant getStartedOn();
}
