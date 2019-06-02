package entity;

import java.io.Serializable;

/** 
 * PreparationStep Entity.
 * 
 * @author JGroup
 *
 */
public class PreparationStep implements Serializable{
	
	private static final long serialVersionUID = -2826246357448488550L;
	
	private String detail;
	
	/**
	 * Constructor with detail.
	 * 
	 * @param detail The detail of the preparation step.
	 */
	public PreparationStep(String detail) {
		this.detail = detail;
	}
	
	/**
	 * Get the detail.
	 * 
	 * @return The detail of the preparation step.
	 */
	public String getDetail() {
		return detail;
	}
	
	/**
	 * Set the detail.
	 * 
	 * @param detail The detail of the preparation step.
	 */
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	/**
	 * Override the toString method of the class PreparationStep.
	 */
	@Override
	public String toString() {
		return "PreparationStep {detail: " + detail + "}";
	}
	
}

