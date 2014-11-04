package pac.testcase.jedis;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPipeline;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.jedis.Transaction;

public class DemoCRUD {
	Jedis jedis = new Jedis("localhost",6379);
	Jedis _jedis = new Jedis("localhost",6666);
	
	String doSet(String k,String v){
		return this.jedis.set(k, v);
	}
	
	List<Object> doTranSet(String k,String v){
		Transaction tx = jedis.multi();
		tx.set(k, v);
		return tx.exec();
	}
	
	List<Object> doPipelineSet(String k,String v){
		Pipeline p = this.jedis.pipelined();
		p.set(k, v);
		return p.syncAndReturnAll();
	}
	
	List<Object> doPipelineTransSet(String k,String v){
		Pipeline p = this.jedis.pipelined();
		p.multi();
		for (int i = 0; i < 100; i++) {
			p.set(k+"key"+i,v+"val"+i);
		}
		Response<List<Object>> res = p.exec();
		p.syncAndReturnAll();
//		jedis.disconnect();
//		res.set(null);
		return res.get();
	}
	
	void doShardSet(String k,String v){
		List<JedisShardInfo> JedisShardInfoList = Arrays.asList(new JedisShardInfo("localhost"));
		ShardedJedis shardedJedis = new ShardedJedis(JedisShardInfoList);
		shardedJedis.set(k,v);
		shardedJedis.disconnect();
	}
	
	void doShardPipelinedSet(String k,String v){
		List<JedisShardInfo> JedisShardInfoList = Arrays.asList(new JedisShardInfo("localhost"));
		ShardedJedis shardedJedis = new ShardedJedis(JedisShardInfoList);
		
		ShardedJedisPipeline pipeline = shardedJedis.pipelined();
		
		pipeline.set(k, v);
		pipeline.syncAndReturnAll();
		shardedJedis.disconnect();
	}
	
	void doShardSimplePoolSet(String k,String v){
		List<JedisShardInfo> JedisShardInfoList = Arrays.asList(new JedisShardInfo("localhost"));
//		ShardedJedis shardedJedis = new ShardedJedis(JedisShardInfoList);
		
		ShardedJedisPool pool = new ShardedJedisPool(new JedisPoolConfig(), JedisShardInfoList);
	}
	
	@Test
	public void testCase(){		
		for (Object o : doPipelineTransSet("Jin","King.")) {
			System.out.println(o);
		}
	}
	
	public static void main(String[] args) {
		Jedis jedis = new 
				Jedis("localhost",6379);

        System.out.println(jedis.get("host"));
		for (int i = 0; i < 100; i++) {
			jedis.del("Jinkey"+i);
		}
	}
}
