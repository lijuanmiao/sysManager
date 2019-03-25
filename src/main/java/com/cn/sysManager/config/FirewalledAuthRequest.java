package com.cn.sysManager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;

/**swagger 请求参数设置[springboot 2.x   HttpFirewall]
 * Created by lijm on 2019-03-21.
 */
@Configuration
public class FirewalledAuthRequest {

    @Bean
    public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
        return new DefaultHttpFirewall();
    }
}
