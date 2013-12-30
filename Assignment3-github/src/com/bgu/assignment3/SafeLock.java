package com.bgu.assignment3;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 
 * this class can handle missed signals , any signals sent saved in AtmoicBooelan 
 * thus even if the signal was missed(notified while not in wait) we can check the boolean 
 */
public class SafeLock {
public SafeLock() {
	this.wasNotified = new AtomicBoolean();
	this.lock = new Object();
}
	private AtomicBoolean wasNotified;
	private Object lock;

	public void doWait(){
	    synchronized(lock){
	      if(!wasNotified.get()){
	        try{
	        	lock.wait();
	         } catch(InterruptedException e) {}
	      }
	      //
	      wasNotified.set(false);
	    }
	}
	public void doNotify() {
		synchronized (lock) {
			wasNotified.set(true);
			lock.notifyAll();
		}
	}
}
