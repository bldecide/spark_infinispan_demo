package com.runner.demo.infinispan

import org.infinispan.client.hotrod.{RemoteCache, RemoteCacheManager}
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder

/**
 * Created by Runner on 2015. 12. 11..
 */
object SimplePutGet {
  def main(args : Array[String]): Unit = {
    // Create the remote cache manager
    val build = new ConfigurationBuilder().addServer().host("172.29.238.69").build()
    val remoteCacheManager = new RemoteCacheManager(build)

    // Obtain the remote cache
    val cache :RemoteCache[Integer, Integer] = remoteCacheManager.getCache("default")

    cache.put(-1, 10)
    cache.put(-2, 10)
    cache.put(-3, 10)
    cache.put(-4, 10)
    cache.put(-5, 10)
    cache.put(-6, 10)


    for (i <- -11 to 10) {
      println(cache.get(i))
    }

  }
}
