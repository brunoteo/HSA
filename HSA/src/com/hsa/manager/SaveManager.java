package com.hsa.manager;

import java.util.Arrays;
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

public class SaveManager {

	private HSADatabaseHelper dbHelper;

	private final Card card1 = new Card("goldshire footman", "Minion", 1, "Basic", "Taunt.", "Neutral", 1, 2, null, null, "goldshire_footman");
	private final Card card2 = new Card("inner fire", "Spell", 1, "Common", "Change a minion's Attack to be equal to its Health.", "Priest", null, null, null, null, "inner_fire");
	private final Card card3 = new Card("frost nova", "Spell", 3, "Basic", "Freeze all enemy Minions.", "Mage", null, null, null, null, "frost_nova");
	private final Card card4 = new Card("ancestral spirit", "Spell", 2, "Rare", "Chose a minion. When that minion is destroyed, return it to the battlefield.", "Shaman", null, null, null, null, "ancestral_spirit");
	private final Card card5 = new Card("blood imp", "Minion", 1, "Common", "Stealth. At the end of your turn, give another random friendly minion +1 Health.", "Warlock", 0, 1, null, "Demon", "blood_imp");
	private final Card card6 = new Card("gladiator's longbow", "Weapon", 7, "Epic", "Your hero is Immune while attacking.", "Hunter", 5, null, 2, null, "gladiators_longbow");
	private final Card card7 = new Card("perdition's blade", "Weapon", 3, "Rare", "Battlecry: Deal 1 damage. Combo: Deal 2 instead.", "Rogue", 2, null, 2, null, "perditions_blade");
	private final Card card8 = new Card("ancient of war", "Minion", 7, "Epic", "Choose one - +5 Attack; or +5 Health and Taunt.", "Druid", 5, 5, null, null, "ancient_of_war");
	private final Card card9 = new Card("tirion fordring", "Minion", 8, "Legendary", "Divine Shield. Taunt. Deathrattle: Equip a 5/3 Ashbringer.", "Paladin", 6, 6, null, null, "tirion_fordring");
	private final Card card10 = new Card("grommash hellscream", "Minion", 8, "Legendary", "Charge. Enrage: +6 Attack", "Warrior", 4, 9, null, null, "grommash_hellscream");
	
	private List<Card> cards = Arrays.asList(card1, card2, card3, card4, card5, card6, card7, card8, card9, card10);
	
	private final Deck deck1 = new Deck("MyDruid", "Druid", "", "2014-05-16 11:30:00");
	private final Deck deck2 = new Deck("MyHunter", "Hunter", "", "2014-05-16 11:33:26");
	
	private List<Deck> decks = Arrays.asList(deck1, deck2);
	
	private final Formation formation1 = new Formation("goldshire footman", "MyDruid", 1);
	private final Formation formation2 = new Formation("ancient of war", "MyDruid", 2);
	private final Formation formation3 = new Formation("gladiator's longbow", "MyHunter", 1);
	private final Formation formation4 = new Formation("goldshire footman", "MyHunter", 2);
	
	private List<Formation> formations = Arrays.asList(formation1, formation2, formation3, formation4);
	
	public SaveManager(HSADatabaseHelper dbHelper) {
		this.dbHelper = dbHelper;
	}
	
	public void fillDB(){
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
	
}
