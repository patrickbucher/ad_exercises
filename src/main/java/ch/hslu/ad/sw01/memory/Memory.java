package ch.hslu.ad.sw01.memory;

public interface Memory {
	public void free(Allocation block);

	public Allocation malloc(int blocks);
}
