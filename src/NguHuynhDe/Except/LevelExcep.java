package NguHuynhDe.Except;

public class LevelExcep extends GameExcept {
	public LevelExcep() {
	}
	
	public LevelExcep(String tex) {
		super(tex);
		
	}
	
	public LevelExcep(String tex, Throwable thr) {
		super(tex, thr);
		
	}
	
	public LevelExcep(Throwable thr) {
		super(thr);
		
	}
	
}
