package fr.chaplinB;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

/*
* this class simulates time passing for concern.
* Whenever clock is passed as parameter in LocalDate.now() in production code
* ClockTicker makes the application move in time by one day in test.
* Do not use this class in production code.
* */
public class ClockTicker extends Clock {
    public static final ZoneId PARIS_ZONE_ID = ZoneId.of("Europe/Paris");
    private final Instant ACCOUNT_CREATION_DATE_ON_2022_12_09 = LocalDate.of(2022,12,9)
            .atStartOfDay(PARIS_ZONE_ID)
            .toInstant();
    private int count = 0;

    @Override
    public ZoneId getZone() {
        return PARIS_ZONE_ID;
    }

    @Override
    public Clock withZone(ZoneId zone) {
        return Clock.fixed(
                ACCOUNT_CREATION_DATE_ON_2022_12_09,
                zone
        );
    }

    @Override
    public Instant instant() {
        return nextInstant();
    }

    private Instant nextInstant() {
        ++count;
        return ACCOUNT_CREATION_DATE_ON_2022_12_09.plus(count, ChronoUnit.DAYS);
    }
}
