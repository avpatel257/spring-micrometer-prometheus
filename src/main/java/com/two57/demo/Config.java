package com.two57.demo;
//
//import io.micrometer.core.instrument.Meter;
//import io.micrometer.core.instrument.Tag;
//import io.micrometer.core.instrument.config.MeterFilter;
//import io.micrometer.core.instrument.config.MeterFilterReply;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Configuration
//public class Config {
//    @Bean
//    public MeterFilter meterFilter() {
//        return new MeterFilter() {
//            @Override
//            public MeterFilterReply accept(Meter.Id id) {
//                if(id.getName().startsWith("tomcat.")) {
//                    return MeterFilterReply.DENY;
//                }
//                if(id.getName().startsWith("jvm.")) {
//                    return MeterFilterReply.DENY;
//                }
//                if(id.getName().startsWith("process.")) {
//                    return MeterFilterReply.DENY;
//                }
//                if(id.getName().startsWith("system.")) {
//                    return MeterFilterReply.DENY;
//                }
//                return MeterFilterReply.NEUTRAL;
//            }
//        };
//    }
//}
