package ch.hslu.ad.sw01.memory;

public class MemorySimple implements Memory {
	private final int size;

	public MemorySimple(int size) {
		this.size = size;
	}

	@Override
	public void free(Allocation block) {

	}

	@Override
	public Allocation malloc(int blocks) {
		return new Allocation(0, 16);
	}

	@Override
	public String toString() {
		return String.format("MemorySimple[Belegt: %d; Frei: %d]", 0, size);
	}
}
