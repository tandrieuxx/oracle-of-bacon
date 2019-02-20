package com.serli.oracle.of.bacon.repository;

import redis.clients.jedis.Jedis;

import java.util.List;

public class RedisRepository {
    private final Jedis jedis;

    public RedisRepository() {
        this.jedis = new Jedis("localhost");
    }
    private final String searchesKey = "bacon_search";

    public List<String> getLastTenSearches() {
        return jedis.lrange(searchesKey, 0, -1);
    }

    public void addSearch(String search) {
        jedis.lpush(searchesKey, search);
        jedis.ltrim(searchesKey, 0, 9);
    }
}
