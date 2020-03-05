package com.ori.utils;

import java.util.Calendar;
import java.util.TimerTask;

import com.ori.controller.CouponController;

public class ExpiryCouponsRemoveTask extends TimerTask{ 

	private CouponController couponController;
	
	public ExpiryCouponsRemoveTask(CouponController couponController) {
		
		this.couponController = couponController;
	}

	@Override
	public void run() {
		try {
			couponController.removeExpiredCoupon();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Calendar getTimeToRemoveExpCoupon() {
		
		Calendar midnight = Calendar.getInstance();
		
		midnight.set(Calendar.HOUR_OF_DAY, 00);
		midnight.set(Calendar.MINUTE, 00);
		midnight.set(Calendar.SECOND, 00);
		
		return midnight;
	}
	
	public static long getDayInMillsec() {
		long dayInMilliseconds = 86400000;
		
		return dayInMilliseconds;
	}

}
