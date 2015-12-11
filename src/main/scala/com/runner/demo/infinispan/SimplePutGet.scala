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
    val cache = remoteCacheManager.getCache("default")

    val list = cache.getBulk(1000).values()

    println(list.size())
  }
}
