package com.fanthal.PasswordManagement.config;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.task.DelegatingSecurityContextAsyncTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {
	private final ThreadPoolTaskExecutor defaultSpringBootAsyncExecutor;

	public AsyncConfig(ThreadPoolTaskExecutor defaultSpringBootAsyncExecutor) {
		this.defaultSpringBootAsyncExecutor = defaultSpringBootAsyncExecutor;
	}

	@Override
	public Executor getAsyncExecutor() {
		return new DelegatingSecurityContextAsyncTaskExecutor(defaultSpringBootAsyncExecutor);
	}
}