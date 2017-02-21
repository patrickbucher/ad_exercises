package ch.hslu.ad.sw01.memory;

public final class Allocation {
	private final int address;
	private final int size;

	public Allocation(int address, int size) {
		this.address = address;
		this.size = size;
	}

	@Override
	public String toString() {
		return String.format("Allocation[Address:%d; Size:%d]", address, size);
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if (!(other instanceof Allocation)) {
			return false;
		}
		Allocation otherAllocation = (Allocation) other;
		if (otherAllocation.address == this.address && otherAllocation.size == this.size) {
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + address;
		result = 31 * result + size;
		return result;
	}

}
