package com.mola.authx.dao;


import com.mola.authx.enyity.UserInfo;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * 登陆用户DAO
 *
 * @author hatim
 */
@Repository
public interface UserInfoDao extends ReactiveCrudRepository<UserInfo, Long> {

    Mono<UserInfo> findByLoginUserId(Long loginUserId);
}
