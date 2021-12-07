package NguHuynhDe.entities;


public abstract class AnimatedEntitiy extends Entity {

	protected int AnimationOfEnti = 0;
	protected final int AnimationMax = 7500;
	
	protected void animate() {
		if(AnimationOfEnti < AnimationMax) AnimationOfEnti++; else AnimationOfEnti = 0; //reset hoat anh
	}

}
