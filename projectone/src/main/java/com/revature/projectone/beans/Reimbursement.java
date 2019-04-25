package com.revature.projectone.beans;

public class Reimbursement {

	private int reimbursementId;
	private String reimbursementType;
	private String reimbursementDescription;
	private String reimbursementStatus;
	private double reimbursementAmount;
	private String reimbursementImage;
	private int employeeId;
	private Employee employee;
	private int reimbursementManagedBy;

	public Reimbursement(int reimbursementId, String reimbursementType, String reimbursementDescription,
			String reimbursementStatus, double reimbursementAmount, int reimbursementManagedBy) {
		super();
		this.reimbursementId = reimbursementId;
		this.reimbursementType = reimbursementType;
		this.reimbursementDescription = reimbursementDescription;
		this.reimbursementStatus = reimbursementStatus;
		this.reimbursementAmount = reimbursementAmount;
		this.reimbursementManagedBy = reimbursementManagedBy;
	}

	
	public Reimbursement(int reimbursementId, int employeeId, String reimbursementType, String reimbursementDescription, String reimbursementStatus, double reimbursementAmount, int reimbursementManagedBy) {
		super();
		this.reimbursementId = reimbursementId;
		this.employeeId=employeeId;
		this.reimbursementType = reimbursementType;
		this.reimbursementDescription = reimbursementDescription;
		this.reimbursementStatus = reimbursementStatus;
		this.reimbursementAmount = reimbursementAmount;
		this.reimbursementManagedBy = reimbursementManagedBy;
	}

	public Reimbursement(String reimbursementType, String reimbursementDescription, double reimbursementAmount) {
		super();
		this.reimbursementType = reimbursementType;
		this.reimbursementDescription = reimbursementDescription;
		this.reimbursementAmount = reimbursementAmount;
	}
	
	
	public Reimbursement(int reimbursementId, String reimbursementType, String reimbursementDescription,
			double reimbursementAmount) {
		super();
		this.reimbursementId = reimbursementId;
		this.reimbursementType = reimbursementType;
		this.reimbursementDescription = reimbursementDescription;
		this.reimbursementAmount = reimbursementAmount;
	}

	public int getReimbursementId() {
		return reimbursementId;
	}

	public void setReimbursementId(int reimbursementId) {
		this.reimbursementId = reimbursementId;
	}

	public String getReimbursementType() {
		return reimbursementType;
	}

	public void setReimbursementType(String reimbursementType) {
		this.reimbursementType = reimbursementType;
	}

	public String getReimbursementDescription() {
		return reimbursementDescription;
	}

	public void setReimbursementDescription(String reimbursementDescription) {
		this.reimbursementDescription = reimbursementDescription;
	}

	public String getReimbursementStatus() {
		return reimbursementStatus;
	}

	public void setReimbursementStatus(String reimbursementStatus) {
		this.reimbursementStatus = reimbursementStatus;
	}

	public double getReimbursementAmount() {
		return reimbursementAmount;
	}

	public void setReimbursementAmount(double reimbursementAmount) {
		this.reimbursementAmount = reimbursementAmount;
	}

	public String getReimbursementImage() {
		return reimbursementImage;
	}

	public void setReimbursementImage(String reimbursementImage) {
		this.reimbursementImage = reimbursementImage;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public int getReimbursementResolverId() {
		return reimbursementManagedBy;
	}

	public void setReimbursementResolverId(int reimbursementResolverId) {
		this.reimbursementManagedBy = reimbursementResolverId;
	}


	@Override
	public String toString() {
		return "Reimbursement [reimbursementId=" + reimbursementId + ", reimbursementType=" + reimbursementType
				+ ", reimbursementDescription=" + reimbursementDescription + ", reimbursementStatus="
				+ reimbursementStatus + ", reimbursementAmount=" + reimbursementAmount + ", employeeId=" + employeeId
				+ ", reimbursementManagedBy=" + reimbursementManagedBy + "]";
	}


	
	
	
}
