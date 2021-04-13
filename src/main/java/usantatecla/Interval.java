package usantatecla;

public class Interval {

	private Min min;
	private Max max;

	public Interval(Min min, Max max) {
		assert min.value <= max.value;
		this.min = min;
		this.max = max;
	}

	public boolean include(double value) {
			return this.min.isWithin(value) && this.max.isWithin(value);
	}

	public Interval getIntersection(Interval interval){
		Interval emptyIntersection = new Interval(new Min(0), new Max(0));
		if (this.isContained(interval)) {
			return this;
		} else if(interval.isContained(this)){
			return interval;
		}
		else if(isThisMaxGreaterOrEqualThanIntervalMin(interval) &&
				this.min.isWithin(interval.min) &&
				interval.max.isWithin(this.max)){
			return new Interval(interval.min, this.max);
		}
		else if(isIntervalMaxGreaterOrEqualThanThisMin(interval)){
			return new Interval(this.min, interval.max);
		}
		else
			return emptyIntersection;
	}

	private boolean isContained(Interval interval){
		return interval.min.isWithin(this.min) && interval.max.isWithin(this.max);
	}

	private boolean isThisMaxGreaterOrEqualThanIntervalMin(Interval interval){
		return Comparator.isMaxGreaterOrEqualThanMin(this.max, interval.min);
	}

	private boolean isIntervalMaxGreaterOrEqualThanThisMin(Interval interval){
		return Comparator.isMaxGreaterOrEqualThanMin(interval.max, this.min);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((max == null) ? 0 : max.hashCode());
		result = prime * result + ((min == null) ? 0 : min.hashCode());
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
		Interval other = (Interval) obj;
		if (max == null) {
			if (other.max != null)
				return false;
		} else if (!max.equals(other.max))
			return false;
		if (min == null) {
			if (other.min != null)
				return false;
		} else if (!min.equals(other.min))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.min.toString() + ", " + max.toString();
	}	

}