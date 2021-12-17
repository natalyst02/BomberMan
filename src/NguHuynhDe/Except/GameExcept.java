package NguHuynhDe.Except;

public class GameExcept extends Exception {
	public GameExcept() {
	}
	
	public GameExcept(String tex) {
		super(tex);
		
	}
	
	public GameExcept(String tex, Throwable thr) {
		super(tex, thr);
		
	}
	
	public GameExcept(Throwable thr) {
		super(thr);
		
	}

}
