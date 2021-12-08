package NguHuynhDe.MapLv;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.StringTokenizer;


import NguHuynhDe.Board;
import NguHuynhDe.Game;
import NguHuynhDe.entities.mob.Player;
import NguHuynhDe.entities.RenderOverlap;
import NguHuynhDe.entities.mob.enemy.Balloom;
import NguHuynhDe.entities.mob.enemy.Doll;
import NguHuynhDe.entities.mob.enemy.Kondoria;
import NguHuynhDe.entities.mob.enemy.Minvo;
import NguHuynhDe.entities.mob.enemy.Oneal;
import NguHuynhDe.entities.tile.GrassTile;
import NguHuynhDe.entities.tile.PortalTile;
import NguHuynhDe.entities.tile.WallTile;
import NguHuynhDe.entities.tile.destroyable.BrickTile;
import NguHuynhDe.entities.tile.powerup.PowerupBombs;
import NguHuynhDe.entities.tile.powerup.PowerupFlames;
import NguHuynhDe.entities.tile.powerup.PowerupSpeed;
import NguHuynhDe.entities.tile.powerup.PowerupUndead;
import NguHuynhDe.exceptions.LoadLevelException;
import NguHuynhDe.display.ScreenInGame;
import NguHuynhDe.display.SpriteInGame;

public class SetupLevel extends ModeGame {
	
	public SetupLevel(String path, Board boardgame) throws LoadLevelException {
		super(path, boardgame);
	}
	
	@Override
	public void loadLevel(String path) throws LoadLevelException {
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
			throw new LoadLevelException("cant load level " + path, e);
		}
	}
	
	@Override
	public void createEntities() {
		for (int y = 0; y < getHeight(); y++) {
			for (int x = 0; x < getWidth(); x++) {
				addLevelEntity( FileGameTiles[y].charAt(x), x, y );
			}
		}
	}
	
	public void addLevelEntity(char c, int x, int y) {
		int pos = x + y * getWidth();
		
		switch(c) {
			case '#': 
				GameBoard.addEntitie(pos, new WallTile(x, y, SpriteInGame.wall));
				break;
			case 'b': 
				RenderOverlap layer = new RenderOverlap(x, y,
						new GrassTile(x ,y, SpriteInGame.grass),
						new BrickTile(x ,y, SpriteInGame.brick));
				
				if(GameBoard.isPowerupUsed(x, y, modeG) == false) {
					layer.addBeforeTop(new PowerupBombs(x, y, modeG, SpriteInGame.powerupBombs));
				}
				
				GameBoard.addEntitie(pos, layer);
				break;
			case 'u':
				 layer = new RenderOverlap(x, y,
						new GrassTile(x ,y, SpriteInGame.grass),
						new BrickTile(x ,y, SpriteInGame.brick));

				if(GameBoard.isPowerupUsed(x, y, modeG) == false) {
					layer.addBeforeTop(new PowerupUndead(x, y, modeG, SpriteInGame.powerup_undead));
				}

				GameBoard.addEntitie(pos, layer);
				break;
			case 's':
				layer = new RenderOverlap(x, y,
						new GrassTile(x ,y, SpriteInGame.grass),
						new BrickTile(x ,y, SpriteInGame.brick));
				
				if(GameBoard.isPowerupUsed(x, y, modeG) == false) {
					layer.addBeforeTop(new PowerupSpeed(x, y, modeG, SpriteInGame.powerupEnemySpeed));
				}
				
				GameBoard.addEntitie(pos, layer);
				break;
			case 'f': 
				layer = new RenderOverlap(x, y,
						new GrassTile(x ,y, SpriteInGame.grass),
						new BrickTile(x ,y, SpriteInGame.brick));
				
				if(GameBoard.isPowerupUsed(x, y, modeG) == false) {
					layer.addBeforeTop(new PowerupFlames(x, y, modeG, SpriteInGame.powerup_flames));
				}
				
				GameBoard.addEntitie(pos, layer);
				break;
			case '*': 
				GameBoard.addEntitie(pos, new RenderOverlap(x, y,
						new GrassTile(x ,y, SpriteInGame.grass),
						new BrickTile(x ,y, SpriteInGame.brick)) );
				break;
			case 'x': 
				GameBoard.addEntitie(pos, new RenderOverlap(x, y,
						new GrassTile(x ,y, SpriteInGame.grass),
						new PortalTile(x ,y, GameBoard, SpriteInGame.portal),
						new BrickTile(x ,y, SpriteInGame.brick)) );
				break;
			case ' ': 
				GameBoard.addEntitie(pos, new GrassTile(x, y, SpriteInGame.grass) );
				break;
			case 'p': 
				GameBoard.addMob( new Player(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, GameBoard) );
				ScreenInGame.setPointOffset(0, 0);
				
				GameBoard.addEntitie(pos, new GrassTile(x, y, SpriteInGame.grass) );
				break;
			// quai
			case '1':
				GameBoard.addMob( new Balloom(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, GameBoard));
				GameBoard.addEntitie(pos, new GrassTile(x, y, SpriteInGame.grass) );
				break;
			case '2':
				GameBoard.addMob( new Oneal(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, GameBoard));
				GameBoard.addEntitie(pos, new GrassTile(x, y, SpriteInGame.grass) );
				break;
			case '3':
				GameBoard.addMob( new Doll(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, GameBoard));
				GameBoard.addEntitie(pos, new GrassTile(x, y, SpriteInGame.grass) );
				break;
			case '4':
				GameBoard.addMob( new Minvo(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, GameBoard));
				GameBoard.addEntitie(pos, new GrassTile(x, y, SpriteInGame.grass) );
				break;
			case '5':
				GameBoard.addMob( new Kondoria(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, GameBoard));
				GameBoard.addEntitie(pos, new GrassTile(x, y, SpriteInGame.grass) );
				break;
			default: 
				GameBoard.addEntitie(pos, new GrassTile(x, y, SpriteInGame.grass) );
				break;
			}
	}
	
}
