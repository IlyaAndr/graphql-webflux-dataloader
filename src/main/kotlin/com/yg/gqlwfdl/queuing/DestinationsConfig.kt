package com.yg.gqlwfdl.queuing

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties("destinations")
class DestinationsConfig {

    lateinit var queues: MutableMap<String, DestinationInfo>

    class DestinationInfo {
        lateinit var exchange: String
        lateinit var routingKey: String
    }
}