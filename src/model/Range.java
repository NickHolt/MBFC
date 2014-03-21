package model;

public class Range {
	public float min, max;
	
	public Range(float min, float max) {
		this.min = min;
		this.max = max;
	}
	
	public float normalize(float value) {
		return (value - min) / (max - min);
	}
	
	public float denormalize(float value) {
		return min + (max - min) * value;
	}
}
