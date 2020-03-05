package com.ori.init;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ori.controller.CouponController;
import com.ori.utils.ExpiryCouponsRemoveTask;

@Component
public class Initializer {

	@Autowired
	private CouponController couponController;
	
	@PostConstruct
	public void init() {
		
		
		TimerTask removeExpiryTask = new ExpiryCouponsRemoveTask(couponController);
		Timer timer = new Timer();
		
		Calendar timeToRemoveExpiredCoupons = ExpiryCouponsRemoveTask.getTimeToRemoveExpCoupon();	
		long dayInMilliseconds = ExpiryCouponsRemoveTask.getDayInMillsec();
		
		timer.schedule(removeExpiryTask, timeToRemoveExpiredCoupons.getTime(), dayInMilliseconds);
		
	}
	
}
