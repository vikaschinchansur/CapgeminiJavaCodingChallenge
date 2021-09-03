package com.example.demo;

import java.util.List;

/*Number Entity class. Has the getter & setter methods for data and output class variables.*/
public class NumberModel {
	private List<Integer> data;
	private String output;

	public List<Integer> getData() {
		return data;
	}

	public void setData(List<Integer> data) {
		this.data = data;
	}

	

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	/*Overriding the toString() method for printing the class variables in a pretty format.*/
	@Override
	public String toString() {
		return "NumberModel [data=" + data + ", output=" + output + "]";
	}
}
