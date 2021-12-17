package NguHuynhDe.MapLv;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.StringTokenizer;


import NguHuynhDe.Board;
import NguHuynhDe.Game;
import NguHuynhDe.entities.mobileGameEnti.Player;
import NguHuynhDe.entities.RenderOverlap;
import NguHuynhDe.entities.mobileGameEnti.enemy.Bot1;
import NguHuynhDe.entities.mobileGameEnti.enemy.Bot2;
import NguHuynhDe.entities.mobileGameEnti.enemy.Bot3;
import NguHuynhDe.entities.mobileGameEnti.enemy.Bot4;
import NguHuynhDe.entities.mobileGameEnti.enemy.Bot5;
import NguHuynhDe.entities.tile.grassObj;
import NguHuynhDe.entities.tile.portalObj;
import NguHuynhDe.entities.tile.wallObj;
import NguHuynhDe.entities.tile.Demolished.brickObj;
import NguHuynhDe.entities.tile.powerup.PowerupBombs;
import NguHuynhDe.entities.tile.powerup.PowerupFlames;
import NguHuynhDe.entities.tile.powerup.PowerupSpeed;
import NguHuynhDe.entities.tile.powerup.PowerupUndead;
import NguHuynhDe.Except.LevelExcep;
import NguHuynhDe.display.ScreenInGame;
import NguHuynhDe.display.SpriteInGame;

public class SetupLevel extends ModeGame {
	
	public SetupLevel(String path, Board boardgame) throws LevelExcep {
		super(path, boardgame);
	}
	
	@Override
	public void loadLevel(String path) throws LevelExcep {
		try {
			URL absPath = SetupLevel.class.getResource("/" + path);
			
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(absPath.openStream()));

			String dataGame = in.readLine();
			StringTokenizer tokens = new StringTokenizer(dataGame);
			
			modeG = Integer.parseInt(tokens.nextToken());
			ScreenHeight = Integer.parseInt(tokens.nextToken());
			ScreenWidth = Integer.parseInt(tokens.nextToken());

			FileGameTiles = new String[ScreenHeight];
			
			for(int i = 0; i < ScreenHeight; ++i) {
				FileGameTiles[i] = in.readLine().substring(0, ScreenWidth);
 			}
			
			in.close();
		} catch (IOException e) {
			throw new LevelExcep("cant load level " + path, e);
		}
	}
	
	@Override
	public void creatNewEnti() {
		for (int y = 0; y < getHeight(); y++) {
			for (int x = 0; x < getWidth(); x++) {
				addQueueEnti( FileGameTiles[y].charAt(x), x, y );
			}
		}
	}
	
	public void addQueueEnti(char c, int x, int y) {
		int pos = x + y * getWidth();
		
		switch(c) {
			case '#': 
				GameBoard.addEntitie(pos, new wallObj(x, y, SpriteInGame.wall));
				break;
			case 'b': 
				RenderOverlap layer = new RenderOverlap(x, y,
						new grassObj(x ,y, SpriteInGame.grass),
						new brickObj(x ,y, SpriteInGame.brick));
				
				if(GameBoard.isPowerupUsed(x, y, modeG) == false) {
					layer.addBeforePrior(new PowerupBombs(x, y, modeG, SpriteInGame.powerupBombs));
				}
				
				GameBoard.addEntitie(pos, layer);
				break;
			case 'u':
				 layer = new RenderOverlap(x, y,
						new grassObj(x ,y, SpriteInGame.grass),
						new brickObj(x ,y, SpriteInGame.brick));

				if(GameBoard.isPowerupUsed(x, y, modeG) == false) {
					layer.addBeforePrior(new PowerupUndead(x, y, modeG, SpriteInGame.powerup_undead));
				}

				GameBoard.addEntitie(pos, layer);
				break;
			case 's':
				layer = new RenderOverlap(x, y,
						new grassObj(x ,y, SpriteInGame.grass),
						new brickObj(x ,y, SpriteInGame.brick));
				
				if(GameBoard.isPowerupUsed(x, y, modeG) == false) {
					layer.addBeforePrior(new PowerupSpeed(x, y, modeG, SpriteInGame.powerupEnemySpeed));
				}
				
				GameBoard.addEntitie(pos, layer);
				break;
			case 'f': 
				layer = new RenderOverlap(x, y,
						new grassObj(x ,y, SpriteInGame.grass),
						new brickObj(x ,y, SpriteInGame.brick));
				
				if(GameBoard.isPowerupUsed(x, y, modeG) == false) {
					layer.addBeforePrior(new PowerupFlames(x, y, modeG, SpriteInGame.powerupFlame));
				}
				
				GameBoard.addEntitie(pos, layer);
				break;
			case '*': 
				GameBoard.addEntitie(pos, new RenderOverlap(x, y,
						new grassObj(x ,y, SpriteInGame.grass),
						new brickObj(x ,y, SpriteInGame.brick)) );
				break;
			case 'x': 
				GameBoard.addEntitie(pos, new RenderOverlap(x, y,
						new grassObj(x ,y, SpriteInGame.grass),
						new portalObj(x ,y, GameBoard, SpriteInGame.portal),
						new brickObj(x ,y, SpriteInGame.brick)) );
				break;
			case ' ': 
				GameBoard.addEntitie(pos, new grassObj(x, y, SpriteInGame.grass) );
				break;
			case 'p': 
				GameBoard.addMob( new Player(LoadGameMap.changeTileToPixel(x), LoadGameMap.changeTileToPixel(y) + Game.TILES_SIZE, GameBoard) );
				ScreenInGame.setPointOffset(0, 0);
				
				GameBoard.addEntitie(pos, new grassObj(x, y, SpriteInGame.grass) );
				break;
			// quai
			case '1':
				GameBoard.addMob( new Bot1(LoadGameMap.changeTileToPixel(x), LoadGameMap.changeTileToPixel(y) + Game.TILES_SIZE, GameBoard));
				GameBoard.addEntitie(pos, new grassObj(x, y, SpriteInGame.grass) );
				break;
			case '2':
				GameBoard.addMob( new Bot5(LoadGameMap.changeTileToPixel(x), LoadGameMap.changeTileToPixel(y) + Game.TILES_SIZE, GameBoard));
				GameBoard.addEntitie(pos, new grassObj(x, y, SpriteInGame.grass) );
				break;
			case '3':
				GameBoard.addMob( new Bot2(LoadGameMap.changeTileToPixel(x), LoadGameMap.changeTileToPixel(y) + Game.TILES_SIZE, GameBoard));
				GameBoard.addEntitie(pos, new grassObj(x, y, SpriteInGame.grass) );
				break;
			case '4':
				GameBoard.addMob( new Bot4(LoadGameMap.changeTileToPixel(x), LoadGameMap.changeTileToPixel(y) + Game.TILES_SIZE, GameBoard));
				GameBoard.addEntitie(pos, new grassObj(x, y, SpriteInGame.grass) );
				break;
			case '5':
				GameBoard.addMob( new Bot3(LoadGameMap.changeTileToPixel(x), LoadGameMap.changeTileToPixel(y) + Game.TILES_SIZE, GameBoard));
				GameBoard.addEntitie(pos, new grassObj(x, y, SpriteInGame.grass) );
				break;
			default: 
				GameBoard.addEntitie(pos, new grassObj(x, y, SpriteInGame.grass) );
				break;
			}
	}
	
}
