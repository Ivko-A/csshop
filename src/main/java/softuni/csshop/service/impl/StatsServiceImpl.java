package softuni.csshop.service.impl;

import org.springframework.stereotype.Service;
import softuni.csshop.service.StatsService;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class StatsServiceImpl implements StatsService {
    private AtomicInteger requestCount = new AtomicInteger(0);
    private Instant startedOn = Instant.now();

    @Override
    public void incRequestCount() {
        requestCount.incrementAndGet();
    }

    @Override
    public int getRequestCount() {

        return requestCount.intValue();
    }

    @Override
    public Instant getStartedOn() {

        return startedOn;
    }
}
