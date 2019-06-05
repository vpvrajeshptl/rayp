package com.Testcode.tutorials;
import java.util.ArrayList;
import org.apache.commons.collections.MapIterator;
import org.apache.commons.collections.map.LRUMap;


public class CodeInmemorycache<K, T> {
	
	private long timeTOLive;
	private LRUMap<K, T> CodecacheMap;
	
	public class CodecacheMapObject{
		
		public long lastAccessed = System.currentTimeMillis();
		public T value;
		
		protected CodecacheMapObject(T value) {
			this.value = value;
		}
	}
	
	public CodeInmemorycache(long CodeTimeToLive,final long CodeTimerInterval, int maxItems) {
	         this.timeTOLive = CodeTimeToLive * 1000;
	         
	         CodecacheMap = new LRUMap<K, T>(maxItems);
	         
	         if (timeTOLive > 0 && CodeTimerInterval > 0 ) {
	        	 
	        	 Thread t = new Thread(new Runnable() {
	        		 
	        		 public void run() {
	        			 while (true) {
	        				 try {
	        					 Thread.sleep(CodeTimerInterval * 1000);
	        				 } catch (InterruptedException ex) {
	        				 }
	        				  cleanup();
	        			 }
	        		 }
	        		 
	        	 });
	        	 
	        	 t.setDaemon(true);
	        	 t.start();
	         }
	        	 
	         }
	
	public void put (K key, T value) {
		synchronized (CodecacheMap) {
			CodecacheMap.put(key, new CodecacheMapObject(value));
			
		}
	}

	public T get (K key) {
		
		synchronized (CodecacheMap){
			CodecacheMapObject c = (CodecacheMapObject) CodecacheMap.get(key);
			
			if(c == null)
				return null;
			else {
				c.lastAccessed = System.currentTimeMillis();
				return c.value;
			}
		}
	}
	
	public void remove (K key) {
		
		synchronized (CodecacheMap){
			CodecacheMap.remove(key);
		}
	}
	
	public int size() {
		synchronized (CodecacheMap){
			return CodecacheMap.size();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void cleanup() {
		long now = System.currentTimeMillis();
		ArrayList<K> deleteKey = null;
		
		synchronized (CodecacheMap) {
			MapIterator<?, ?> itr = CodecacheMap.mapIterator();
			
			deleteKey = new ArrayList<K>((CodecacheMap.size() / 2) + 1);
			K key = null;
			CodecacheMapObject c = null;
			
			while (itr.hasNext()) {
				key = (K) itr.next();
				c = (CodecacheMapObject) itr.getValue();
				
				if (c !=null && (now >(timeTOLive + c.lastAccessed))){
					deleteKey.add(key);
					
				}
			}
			
		}
		
		for (K key : deleteKey) {
			synchronized (CodecacheMap) {
				CodecacheMap.remove(key);
			
		}
			Thread.yield();
	}
	
	
}
}
