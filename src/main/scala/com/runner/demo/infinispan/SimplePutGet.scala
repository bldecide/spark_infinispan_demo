package com.runner.demo.infinispan

import org.infinispan.client.hotrod.{RemoteCache, RemoteCacheManager, Search}
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder

/**
 * Created by Runner on 2015. 12. 11..
 */
object SimplePutGet {
  def main(args: Array[String]): Unit = {
    // Create the remote cache manager
    val build = new ConfigurationBuilder().addServer().host("192.168.215.239").build()
    val remoteCacheManager = new RemoteCacheManager(build)

    // Obtain the remote cache
    val cache :RemoteCache[Integer, Integer] = remoteCacheManager.getCache("my-cache")

    var count = 0
    val r = scala.util.Random
    while (true) {
      val limit = r.nextInt(10)
      for (i <- 1 to limit) {
        count += 1
        cache.put(count, count)
      }

      println(limit)
      Thread sleep 1000
    }
  }
}
