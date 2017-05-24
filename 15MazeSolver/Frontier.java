public interface Frontier{
	public void add (Location L);
	public Location next();
	public boolean hasNext();
}