package com.dsfy.entity.relation;

import java.io.Serializable;

public class PK_User_Parking implements Serializable {

	private static final long serialVersionUID = -890616727598171830L;

	private int userId;

	private int parkingId;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getParkingId() {
		return parkingId;
	}

	public void setParkingId(int parkingId) {
		this.parkingId = parkingId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + parkingId;
		result = prime * result + userId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PK_User_Parking other = (PK_User_Parking) obj;
		if (parkingId != other.parkingId)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

}
