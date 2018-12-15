package pl.codeme.jeeb.e2.bank.backend;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.jboss.logging.Logger;

import pl.codeme.jeeb.e2.bank.backend.logger.Log;

public class Resources {

    @Produces
    @Log
    public Logger produceLogger(InjectionPoint ip) {
        System.out.println("IP: " + ip.getMember().getDeclaringClass().getName());
        return Logger.getLogger(ip.getMember().getDeclaringClass());
    }
}
