package com.synerzip.app.model;

import org.springframework.data.annotation.Id;

public class Result {

	@Id
	private Object id;
	private Object value;

	public Object getId() {
		return id;
	}

	public void setId(Object id) {
		this.id = id;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Result [id=" + id + ", value=" + value + "]";
	}

	public Result() {
	}

}
