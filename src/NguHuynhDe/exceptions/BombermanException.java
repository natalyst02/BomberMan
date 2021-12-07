package NguHuynhDe.exceptions;

public class BombermanException extends Exception {
	public BombermanException() {
	}
	
	public BombermanException(String tex) {
		super(tex);
		
	}
	
	public BombermanException(String tex, Throwable thr) {
		super(tex, thr);
		
	}
	
	public BombermanException(Throwable thr) {
		super(thr);
		
	}

}
