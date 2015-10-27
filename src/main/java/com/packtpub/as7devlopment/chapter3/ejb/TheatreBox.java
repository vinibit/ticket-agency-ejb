package com.packtpub.as7devlopment.chapter3.ejb;

import java.util.ArrayList;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.jboss.logging.Logger;

/**
 * Session Bean implementation class TheatreBox
 */
@Singleton
@Startup
public class TheatreBox {

	private ArrayList<Seat> seatList;
	private static final Logger logger = Logger.getLogger(TheatreBox.class);

	public void setupTheatre() {
		seatList = new ArrayList<Seat>();
		int id = 0;
		for (int i=0;i<5;i++) {
		Seat seat = new Seat(++id, "Stalls",40);
		seatList.add(seat);
		}
		for (int i=0;i<5;i++) {
		Seat seat = new Seat(++id,"Circle",20);
		seatList.add(seat);
		}
		for (int i=0;i<5;i++) {
		Seat seat = new Seat(++id, "Balcony",10);
		seatList.add(seat);
		}
		logger.info("Seat Map constructed.");
	}

	@Lock(LockType.READ)
	public ArrayList<Seat> getSeatList() {
		return seatList;
	}

	@Lock(LockType.READ)
	public int getSeatPrice(int id) {
		return getSeatList().get(id).getPrice();
	}

	@Lock(LockType.WRITE)
	public void buyTicket(Seat seat) {
		seat.setBooked(true);
	}
}
