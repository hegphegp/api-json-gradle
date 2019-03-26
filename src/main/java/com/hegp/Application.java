package com.hegp;

import com.hegp.framework.web.DemoFunction;
import com.hegp.framework.web.StructureUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.hegp.framework.apijson.Log;

@Configuration
@SpringBootApplication
public class Application implements CommandLineRunner {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Log.DEBUG = false; //上线生产环境前改为 false，可不输出 APIJSONORM 的日志 以及 SQLException 的原始(敏感)信息
		StructureUtil.test();
	}

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", buildConfig());
		return new CorsFilter(source);
	}

	private CorsConfiguration buildConfig() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.addAllowedOrigin("*"); //允许的域名或IP地址
		corsConfiguration.addAllowedHeader("*"); //允许的请求头
		corsConfiguration.addAllowedMethod("*"); //允许的HTTP请求方法
		corsConfiguration.setAllowCredentials(true); //允许发送跨域凭据，前端Axios存取JSESSIONID必须要
		return corsConfiguration;
	}
}
