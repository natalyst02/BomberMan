package NguHuynhDe.entities.mobileGameEnti.enemy.RandomAIBot;

import java.util.Random;

public abstract class AI {
	
	protected Random random = new Random();
	
	public abstract int CalcDirectionOfBot();
}
