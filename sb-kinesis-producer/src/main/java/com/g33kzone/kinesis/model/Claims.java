package com.g33kzone.kinesis.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "Claims")
public class Claims {

	@Id
	@Column(name = "claim_id")
	private Integer claimId;
	@Column(name = "customer_id")
	private String customerId;
	@Column(name = "claim_type")
	private String claimType;
	@Column(name = "claim_amount")
	private float claimAmount;
	@Column(name = "country")
	private String country;
	@Column(name = "ip_address")
	private String ipAddress;

	public Claims() {

	}

	public Integer getClaimId() {
		return claimId;
	}

	public void setClaimId(Integer claimId) {
		this.claimId = claimId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getClaimType() {
		return claimType;
	}

	public void setClaimType(String claimType) {
		this.claimType = claimType;
	}

	public float getClaimAmount() {
		return claimAmount;
	}

	public void setClaimAmount(float claimAmount) {
		this.claimAmount = claimAmount;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	@Override
	public String toString() {
		return claimId + "," + customerId + "," + claimType + "," + claimAmount + "," + country + "," + ipAddress + "\n";
	}
}
