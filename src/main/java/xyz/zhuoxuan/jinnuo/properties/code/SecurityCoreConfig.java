package xyz.zhuoxuan.jinnuo.properties.code;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import xyz.zhuoxuan.jinnuo.properties.SecurityProperties;


/**
 * 安全码配置
 * @author hy
 *
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {
	
}
