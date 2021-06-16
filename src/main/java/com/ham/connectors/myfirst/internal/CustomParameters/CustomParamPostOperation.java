package com.ham.connectors.myfirst.internal.CustomParameters;

import java.util.Map;

import org.mule.runtime.api.meta.ExpressionSupport;
import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;

public class CustomParamPostOperation {
	@Parameter
	@Expression(ExpressionSupport.SUPPORTED)
	@Optional(defaultValue = "Enter the Name")
	private  String name;
	@Parameter
	@Expression(ExpressionSupport.SUPPORTED)
	@Optional(defaultValue = "Enter the job")
	private String job;
	@Parameter
	@Expression(ExpressionSupport.SUPPORTED)
	@Optional(defaultValue = "#[{}]")
	private  Map<String,String> additionalData;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public Map<String, String> getAdditionalData() {
		return additionalData;
	}
	public void setAdditionalData(Map<String, String> additionalData) {
		this.additionalData = additionalData;
	}
	

	

}
