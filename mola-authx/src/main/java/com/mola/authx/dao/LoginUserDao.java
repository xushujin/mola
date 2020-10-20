package com.mola.authx.dao;


import com.mola.authx.enyity.LoginUser;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * 登陆用户DAO
 *
 * @author hatim
 */
@Repository
public interface LoginUserDao extends ReactiveCrudRepository<LoginUser, Long> {

    Mono<LoginUser> findByUsername(String username);
}
