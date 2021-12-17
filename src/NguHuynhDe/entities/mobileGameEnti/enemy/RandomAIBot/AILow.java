package NguHuynhDe.entities.mobileGameEnti.enemy.RandomAIBot;

public class AILow extends AI {

	@Override
	public int CalcDirectionOfBot() {
		return random.nextInt(4);
	}

}
