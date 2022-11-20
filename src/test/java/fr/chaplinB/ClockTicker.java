package fr.chaplinB;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

public class ClockTicker extends Clock {
    private final Instant ACCOUNT_CREATION_DATE_ON_2022_12_10 = LocalDate.of(2022,12,10)
            .atStartOfDay(ZoneId.of("Europe/Paris"))
            .toInstant();
    private int count = 0;

    @Override
    public ZoneId getZone() {
        return ZoneId.of("Europe/Paris");
    }

    @Override
    public Clock withZone(ZoneId zone) {
        return Clock.fixed(
                ACCOUNT_CREATION_DATE_ON_2022_12_10,
                zone
        );
    }

    @Override
    public Instant instant() {
        return nextInstant();
    }

    private Instant nextInstant() {
        ++count;
        return ACCOUNT_CREATION_DATE_ON_2022_12_10.plus(count, ChronoUnit.DAYS);
    }
}
