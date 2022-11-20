package fr.chaplinB;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class ClockTicker extends Clock {
    @Override
    public ZoneId getZone() {
        return ZoneId.of("Europe/Paris");
    }

    @Override
    public Clock withZone(ZoneId zone) {
        return Clock.fixed(
                LocalDate.of(2022,12,10)
                        .atStartOfDay(ZoneId.of("Europe/Paris"))
                        .toInstant(),
                zone
        );
    }

    @Override
    public Instant instant() {
        return null;
    }
}
