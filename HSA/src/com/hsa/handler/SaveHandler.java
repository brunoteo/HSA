package com.hsa.handler;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.hsa.bean.Card;
import com.hsa.bean.Deck;
import com.hsa.bean.Formation;
import com.hsa.contract.CardEntry;
import com.hsa.contract.DeckEntry;
import com.hsa.contract.FormationEntry;
import com.hsa.database.HSADatabaseHelper;

public class SaveHandler{

	/**
	 * 
	 */
	
	private static SaveHandler saveInstance;


	private HSADatabaseHelper dbHelper;
	
	public static SaveHandler getInstance(HSADatabaseHelper dbHelper) {
		if(saveInstance == null)
			saveInstance = new SaveHandler(dbHelper);
		return saveInstance;
	}
	
	private SaveHandler(HSADatabaseHelper dbHelper) {
		this.dbHelper = dbHelper;
	}
	
	public void fillDB(){
		final Card card1 = new Card("goldshire footman", "Minion", 1, "Basic", "Taunt.", "Neutral", 1, 2, null, null, "goldshire_footman");
		final Card card2 = new Card("inner fire", "Spell", 1, "Common", "Change a minion's Attack to be equal to its Health.", "Priest", null, null, null, null, "inner_fire");
		final Card card3 = new Card("frost nova", "Spell", 3, "Basic", "Freeze all enemy Minions.", "Mage", null, null, null, null, "frost_nova");
		final Card card4 = new Card("ancestral spirit", "Spell", 2, "Rare", "Chose a minion. When that minion is destroyed, return it to the battlefield.", "Shaman", null, null, null, null, "ancestral_spirit");
		final Card card5 = new Card("blood imp", "Minion", 1, "Common", "Stealth. At the end of your turn, give another random friendly minion +1 Health.", "Warlock", 0, 1, null, "Demon", "blood_imp");
		final Card card6 = new Card("gladiator's longbow", "Weapon", 7, "Epic", "Your hero is Immune while attacking.", "Hunter", 5, null, 2, null, "gladiators_longbow");
		final Card card7 = new Card("perdition's blade", "Weapon", 3, "Rare", "Battlecry: Deal 1 damage. Combo: Deal 2 instead.", "Rogue", 2, null, 2, null, "perditions_blade");
		final Card card8 = new Card("ancient of war", "Minion", 7, "Epic", "Choose one - +5 Attack; or +5 Health and Taunt.", "Druid", 5, 5, null, null, "ancient_of_war");
		final Card card9 = new Card("tirion fordring", "Minion", 8, "Legendary", "Divine Shield. Taunt. Deathrattle: Equip a 5/3 Ashbringer.", "Paladin", 6, 6, null, null, "tirion_fordring");
		final Card card10 = new Card("grommash hellscream", "Minion", 8, "Legendary", "Charge. Enrage: +6 Attack", "Warrior", 4, 9, null, null, "grommash_hellscream");
		final Card card11 = new Card("arcane explosion", "Spell", 2, "Basic", "Deal 1 damage to all enemy minions.", "Mage", null, null, null, null, "arcane_explosion");
		final Card card12 = new Card("arcane intellect", "Spell", 3, "Basic", "Draw 2 cards.", "Mage", null, null, null, null, "arcane_intellect");
		final Card card13 = new Card("blizzard", "Spell", 6, "Rare", "Deal 2 damage to all enemy minions and Freeze them.", "Mage", null, null, null, null, "blizzard");
		final Card card14 = new Card("claw", "Spell", 1, "Basic", "Give your hero +2 Attack this turn and 2 Armor.", "Druid", null, null, null, null, "claw");
		final Card card15 = new Card("fen creeper", "Minion", 5, "Common", "Taunt.", "Neutral", 3, 6, null, null, "fen_creeper");
		final Card card16 = new Card("fireball", "Spell", 4, "Basic", "Deal 6 damage.", "Mage", null, null, null, null, "fireball");
		final Card card17 = new Card("frostbolt", "Spell", 2, "Basic", "Deal 3 damage to a character and Freeze it.", "Mage", null, null, null, null, "frostbolt");
		final Card card18 = new Card("healing touch", "Spell", 3, "Basic", "Restore 8 Health.", "Druid", null, null, null, null, "healing_touch");
		final Card card19 = new Card("holy nova", "Spell", 5, "Basic", "Deal 2 damage to all enemies. Restore 2 Health to all friendly characters.", "Priest", null, null, null, null, "holy_nova");
		final Card card20 = new Card("holy smite", "Spell", 1, "Basic", "Deal 2 damage.", "Priest", null, null, null, null, "holy_smite");
		final Card card21 = new Card("mark of the wild", "Spell", 2, "Basic", "Give a minion Taunt and +2/+2. (+2 Attack/+2 Health)", "Druid", null, null, null, null, "mark_of_the_wild");
		final Card card22 = new Card("mind control", "Spell", 10, "Basic", "Take control of an enemy minion", "Priest", null, null, null, null, "mind_control");
		final Card card23 = new Card("mind vision", "Spell", 1, "Basic", "Put a copy of a random card in your opponent's hand into your hand.", "Priest", null, null, null, null, "mind_vision");
		final Card card24 = new Card("mirror image", "Spell", 1, "Basic", "Summon two 0/2 minions with Taunt.", "Mage", null, null, null, null, "mirror_image");
		final Card card25 = new Card("moonfire", "Spell", 0, "Basic", "Deal 1 damage.", "Druid", null, null, null, null, "moonfire");
		final Card card26 = new Card("polymorph", "Spell", 4, "Basic", "Transform a minion into a 1/1 Sheep.", "Mage", null, null, null, null, "polymorph");
		final Card card27 = new Card("power word: shield", "Spell", 1, "Basic", "Give a minion +2 Health. Draw a card.", "Priest", null, null, null, null, "power_word_shield");
		final Card card28 = new Card("savage roar", "Spell", 3, "Basic", "Give your characters +2 Attack this turn.", "Druid", null, null, null, null, "savage_roar");
		final Card card29 = new Card("swipe", "Spell", 4, "Basic", "Deal 4 damage to an enemy and 1 damage to all other enemies.", "Druid", null, null, null, null, "swipe");
		final Card card30 = new Card("wild growth", "Spell", 2, "Basic", "Gain an empty Mana Crystal.", "Druid", null, null, null, null, "wild_growth");

		
		List<Card> cards = Arrays.asList(card1, card2, card3, card4, card5, card6, card7, card8, card9, card10,
				card11, card12, card13, card14, card15, card16, card17, card18, card19, card20,
				card21, card22, card23, card24, card25, card26, card27, card28, card29, card30);
		
		final Deck deck1 = new Deck("MyDruid", "Druid", "", "2014-05-16 11:30:00");
		final Deck deck2 = new Deck("MyHunter", "Hunter", "", "2014-05-16 11:33:26");
		
		List<Deck> decks = Arrays.asList(deck1, deck2);
		
		final Formation formation1 = new Formation("goldshire footman", "MyDruid", 20);
		final Formation formation2 = new Formation("ancient of war", "MyDruid", 2);
		final Formation formation3 = new Formation("gladiator's longbow", "MyHunter", 1);
		final Formation formation4 = new Formation("goldshire footman", "MyHunter", 2);
		final Formation formation5 = new Formation("swipe", "MyDruid", 2);
		final Formation formation6 = new Formation("moonfire", "MyDruid", 3);
		final Formation formation7 = new Formation("wild growth", "MyDruid", 3);
		
		List<Formation> formations = Arrays.asList(formation1, formation2, formation3, formation4, formation5, formation6, formation7);
		
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		
		for (int i = 0; i < cards.size(); i++){
			
			ContentValues values = new ContentValues();
			//values.put(CardEntry.COLUMN_NAME_ENTRY_ID, cards.get(i).get_id());
			values.put(CardEntry.COLUMN_NAME_NAME, cards.get(i).getName());
			values.put(CardEntry.COLUMN_NAME_TYPE, cards.get(i).getType());
			values.put(CardEntry.COLUMN_NAME_COST, cards.get(i).getCost());
			values.put(CardEntry.COLUMN_NAME_RARITY, cards.get(i).getRarity());
			values.put(CardEntry.COLUMN_NAME_EFFECT, cards.get(i).getEffect());
			values.put(CardEntry.COLUMN_NAME_CLASS, cards.get(i).getClassName());
			values.put(CardEntry.COLUMN_NAME_ATTACK, cards.get(i).getAttack());
			values.put(CardEntry.COLUMN_NAME_HEALTH, cards.get(i).getHealth());
			values.put(CardEntry.COLUMN_NAME_DURABILITY, cards.get(i).getDurability());
			values.put(CardEntry.COLUMN_NAME_RACE, cards.get(i).getRace());
			values.put(CardEntry.COLUMN_NAME_PATH, cards.get(i).getPath());
			
			db.insert(CardEntry.TABLE_NAME, null, values);
		}
		
		for (int i = 0; i < decks.size(); i++){
			ContentValues values = new ContentValues();
			values.put(DeckEntry.COLUMN_NAME_NAME, decks.get(i).getName());
			values.put(DeckEntry.COLUMN_NAME_CLASS, decks.get(i).getClassName());
			values.put(DeckEntry.COLUMN_NAME_NOTE, decks.get(i).getNote());
			values.put(DeckEntry.COLUMN_NAME_DATE, decks.get(i).getDate());
			
			db.insert(DeckEntry.TABLE_NAME, null, values);
		}
		
		for (int i = 0; i < formations.size(); i++){
			ContentValues values = new ContentValues();
			values.put(FormationEntry.COLUMN_NAME_CARD, formations.get(i).getCard());
			values.put(FormationEntry.COLUMN_NAME_DECK, formations.get(i).getDeck());
			values.put(FormationEntry.COLUMN_NAME_OCCURRENCE, formations.get(i).getOccurrence());
			
			db.insert(FormationEntry.TABLE_NAME, null, values);
		}
		db.close();
		
	}
	
	public Deck createDeck(String name, String className){
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String format = s.format(new Date());
		Deck deck = new Deck(name, className, "", format);
		ContentValues values = new ContentValues();
		values.put(DeckEntry.COLUMN_NAME_NAME, deck.getName());
		values.put(DeckEntry.COLUMN_NAME_CLASS, deck.getClassName());
		values.put(DeckEntry.COLUMN_NAME_NOTE, deck.getNote());
		values.put(DeckEntry.COLUMN_NAME_DATE, deck.getDate());
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		
		db.insert(DeckEntry.TABLE_NAME, null, values);
		
		return deck;
	}

	public void deleteDeck(String deckName) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		String[] args = new String[]{deckName};
		db.delete(DeckEntry.TABLE_NAME, DeckEntry.COLUMN_NAME_NAME + " = ?", args);
		db.delete(FormationEntry.TABLE_NAME, FormationEntry.COLUMN_NAME_DECK + " = ?", args);
	}
	
	public void updateDeck(List<Formation> tmpFormations, String deckName) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		String[] args = new String[]{deckName};
		db.delete(FormationEntry.TABLE_NAME, FormationEntry.COLUMN_NAME_DECK + " = ?", args);
		
		for(Formation f : tmpFormations) {
			ContentValues values = new ContentValues();
			values.put(FormationEntry.COLUMN_NAME_CARD, f.getCard());
			values.put(FormationEntry.COLUMN_NAME_DECK, f.getDeck());
			values.put(FormationEntry.COLUMN_NAME_OCCURRENCE, f.getOccurrence());
			
			db.insert(FormationEntry.TABLE_NAME, null, values);
		}
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		
		ContentValues values = new ContentValues();
		values.put(DeckEntry.COLUMN_NAME_DATE, dateFormat.format(date));
		String[] deckValue = new String[]{deckName};
		
		db.update(DeckEntry.TABLE_NAME, values, DeckEntry.COLUMN_NAME_NAME + "=?", deckValue);
		
		
		
	}
	
}
