package redis.clients.jedis.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Connection;
import redis.clients.jedis.Protocol.Command;
import redis.clients.jedis.commands.ProtocolCommand;
import redis.clients.jedis.exceptions.JedisConnectionException;

public class ConnectionTest {
  private Connection client;

  // @Before
  // public void setUp() throws Exception {
  //   client = new Connection();
  // }
  //
  // @After
  // public void tearDown() throws Exception {
  //   client.disconnect();
  // }
  //
  // @Test(expected = JedisConnectionException.class)
  // public void checkUnkownHost() {
  //   client.setHost("someunknownhost");
  //   client.connect();
  // }
  //
  // @Test(expected = JedisConnectionException.class)
  // public void checkWrongPort() {
  //   client.setHost("localhost");
  //   client.setPort(55665);
  //   client.connect();
  // }
  //
  // @Test
  // public void connectIfNotConnectedWhenSettingTimeoutInfinite() {
  //   client.setHost("localhost");
  //   client.setPort(6379);
  //   client.setTimeoutInfinite();
  // }

  @Test
  public void checkCloseable() {
    System.out.println("========= dc 1=========");
    client.setHost("10.240.135.43");
    client.setPort(13396);
    client.connect();
    System.out.println("========= dc 2=========");
    client.close();
    System.out.println("========= dc 3=========");
  }

  // @Test
  // public void getErrorAfterConnectionReset() throws Exception {
  //   class TestConnection extends Connection {
  //     public TestConnection() {
  //       super("localhost", 6379);
  //     }
  //
  //     @Override
  //     public void sendCommand(ProtocolCommand cmd, byte[]... args) {
  //       super.sendCommand(cmd, args);
  //     }
  //   }
  //
  //   TestConnection conn = new TestConnection();
  //
  //   try {
  //     conn.sendCommand(Command.HMSET, new byte[1024 * 1024 + 1][0]);
  //     fail("Should throw exception");
  //   } catch (JedisConnectionException jce) {
  //     assertEquals("ERR Protocol error: invalid multibulk length", jce.getMessage());
  //   }
  // }
}
