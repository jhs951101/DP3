package hufs.cse.grimpan.strategy;

public interface Command {
	
	public void execute();
	public void undo();		
}
