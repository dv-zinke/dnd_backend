package com.dnd.jachwirus.write.endpoint;

import com.dnd.jachwirus.write.domain.User;
import com.dnd.jachwirus.write.exception.RestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class UserEndpoint {

    public Mono<User> getUser(Long userId) {
        return WebClient.create()
                .get()
                .uri("http://localhost:9020/find?userId=" + userId)
                .retrieve()
                .bodyToMono(User.class)
                .switchIfEmpty(Mono.error(new RestException(HttpStatus.NOT_FOUND, "User is not exist")));
    }
}

