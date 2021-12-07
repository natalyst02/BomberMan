package NguHuynhDe.exceptions;

public class LoadLevelException extends BombermanException {
	public LoadLevelException() {
	}
	
	public LoadLevelException(String tex) {
		super(tex);
		
	}
	
	public LoadLevelException(String tex, Throwable thr) {
		super(tex, thr);
		
	}
	
	public LoadLevelException(Throwable thr) {
		super(thr);
		
	}
	
}
