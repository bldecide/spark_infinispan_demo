package com.runner.demo.infinispan

import org.infinispan.client.hotrod.{RemoteCache, RemoteCacheManager, Search}
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder

/**
 * Created by Runner on 2015. 12. 11..
 */
object RandomPut {
  def main(args: Array[String]): Unit = {
    // Create the remote cache manager
    val build = new ConfigurationBuilder().addServer().host("172.29.238.69").build()
    val remoteCacheManager = new RemoteCacheManager(build)

    // Obtain the remote cache
    val cache :RemoteCache[Integer, Integer] = remoteCacheManager.getCache("my-cache")

    var count = 0
    val r = scala.util.Random
    while (true) {
      val limit = r.nextInt(1000)
      for (i <- 1 to limit) {
        count += 1
        cache.put(count, count)
      }

      println(limit)
      Thread sleep 1000
    }
  }
}
