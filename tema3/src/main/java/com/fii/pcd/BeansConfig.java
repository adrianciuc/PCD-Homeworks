package com.fii.pcd;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.fii.pcd.service", "com.fii.pcd.security"})
public class BeansConfig {
}
