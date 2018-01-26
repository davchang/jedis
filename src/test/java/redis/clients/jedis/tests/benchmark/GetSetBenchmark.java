package redis.clients.jedis.tests.benchmark;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Calendar;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.tests.HostAndPortUtil;

public class GetSetBenchmark {
  private static HostAndPort hnp = new HostAndPort("redis-13396.ec.mystrongtie.com", 13396); // 10.240.135.43 HostAndPortUtil.getRedisServers().get(0);
  private static final int TOTAL_OPERATIONS = 100000;

  public static void main(String[] args) throws UnknownHostException, IOException {
    Jedis jedis = new Jedis(hnp);
    System.out.println("--host=" + hnp.getHost() + " port=" + hnp.getPort());
    jedis.connect();
    //jedis.auth("foobared");
    jedis.flushAll();

    long begin = Calendar.getInstance().getTimeInMillis();

    System.out.println("==================== Round 1 ===================");
    for (int n = 0; n <= TOTAL_OPERATIONS; n++) {
      String key = "foo" + n;
      String value = "bar" + n;

      jedis.set(key, value);
      System.out.println("--key=" + key + " value=" + value);

      // String returnValue = jedis.get(key);
      // System.out.println("--key=" + key + " returnValue=" + returnValue);
      // if (!value.equals(returnValue)) {
      //   System.out.println("====================================================== FATAL ERROR =========================" + key + " " + value + " " + returnValue);
      //   break;
      // }
    }

    System.out.println("==================== Round 2 ===================");
    for (int n = 0; n <= TOTAL_OPERATIONS; n++) {
      String key = "foo" + n;
      String value = "bar" + n;

      String returnValue = jedis.get(key);
      System.out.println("--key=" + key + " returnValue=" + returnValue);
      if (!value.equals(returnValue)) {
        System.out.println("====================================================== FATAL ERROR =========================" + key + " " + value + " " + returnValue);
        break;
      }
    }

    long elapsed = Calendar.getInstance().getTimeInMillis() - begin;

    jedis.disconnect();

    System.out.println(((1000 * 2 * TOTAL_OPERATIONS) / elapsed) + " ops");
  }
}
