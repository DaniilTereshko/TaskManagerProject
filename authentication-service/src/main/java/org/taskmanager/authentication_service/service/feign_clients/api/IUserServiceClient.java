package org.taskmanager.authentication_service.service.feign_clients.api;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "user-service")
public interface IUserServiceClient {

}
