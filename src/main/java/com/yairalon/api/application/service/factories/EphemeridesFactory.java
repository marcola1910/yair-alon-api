package com.yairalon.api.application.service.factories;

import com.yairalon.api.application.service.EphemerisService;
import com.yairalon.api.application.service.EphemerisServiceImpl;

public abstract class EphemeridesFactory {
    public static EphemerisService createEphemeridesService() {
        return new EphemerisServiceImpl();
    }
}
