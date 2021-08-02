package com.minorproject.libraryapplication.security;

import com.minorproject.libraryapplication.Model.applicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class userCache{

    private static final String ROLE="USER::";

//    private static Logger logger= (Logger) LoggerFactory.getLogger(userCache.class);

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    public void setUserInCache(applicationUser user){
        redisTemplate.opsForValue().set(ROLE+user.getUsername(),user, Duration.ofMinutes(10));
    }

    public applicationUser getUserFromCache(String username){
        return (applicationUser) redisTemplate.opsForValue().get(ROLE+username);
    }
}
