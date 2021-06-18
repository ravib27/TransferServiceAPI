package com.natwest.transferservice.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "transaction")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long transactionId;
	
	@Column(name = "src_account_number")
	private String srcAccountNumber;
	
	@Column(name = "dest_account_number")
	private String destAccountNumber;
	
	@Column(name = "amount")
	private BigDecimal amount;
	
	@Column(name = "transaction_date")
	private Date transactionDate;
	
	public Long getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}
	public String getSrcAccountNumber() {
		return srcAccountNumber;
	}
	public void setSrcAccountNumber(String srcAccountNumber) {
		this.srcAccountNumber = srcAccountNumber;
	}
	public String getDestAccountNumber() {
		return destAccountNumber;
	}
	public void setDestAccountNumber(String destAccountNumber) {
		this.destAccountNumber = destAccountNumber;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Date getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	
}
