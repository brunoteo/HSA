package com.hsa.handler;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.hsa.aggregation.DeckDataAggregation;
import com.hsa.bean.Card;
import com.hsa.bean.Deck;
import com.hsa.bean.Formation;
import com.hsa.contract.CardEntry;
import com.hsa.contract.DeckEntry;
import com.hsa.contract.FormationEntry;
import com.hsa.database.HSADatabaseHelper;

public class SaveHandler{
	
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
		final Card card31 = new Card("ancestral healing", "Spell", 0, "Basic", "Restore a minion to full Health and give it Taunt.", "Shaman", null, null, null, null, "ancestral_healing");
		final Card card32 = new Card("backstab", "Spell", 0, "Basic", "Deal 2 damage to an undamaged minion.", "Rogue", null, null, null, null, "backstab");
		final Card card33 = new Card("bloodlust", "Spell", 5, "Basic", "Give your minions +3 Attack this turn.", "Shaman", null, null, null, null, "bloodlust");
		final Card card34 = new Card("cold blood", "Spell", 1, "Common", "Give a minion +2 Attack. Combo: +4 Attack instead.", "Rogue", null, null, null, null, "cold_blood");
		final Card card35 = new Card("corruption", "Spell", 1, "Basic", "Choose an enemy minion. At the start of your turn, destroy it.", "Warlock", null, null, null, null, "corruption");
		final Card card36 = new Card("drain life", "Spell", 3, "Basic", "Deal 2 damage. Restore 2 Health to your hero.", "Warlock", null, null, null, null, "drain_life");
		final Card card37 = new Card("dread infernal", "Minion", 6, "Basic", "Battlecry: Deal 1 damage to ALL other characters.", "Warlock", 6, 6, null, "Demon", "dread_infernal");
		final Card card38 = new Card("far sight", "Spell", 3, "Epic", "Draw a card. That card costs (3) less.", "Shaman", null, null, null, null, "far_sight");
		final Card card39 = new Card("fire elemental", "Minion", 6, "Basic", "Battlecry: Deal 3 damage.", "Shaman", 6, 5, null, null, "fire_elemental");
		final Card card40 = new Card("flamestrike", "Spell", 7, "Basic", "Deal 4 damage to all enemy minions.", "Mage", null, null, null, null, "flamestrike");
		final Card card41 = new Card("frost shock", "Spell", 1, "Basic", "Deal 1 damage to an enemy character and Freeze it.", "Shaman", null, null, null, null, "frost_shock");
		final Card card42 = new Card("hellfire", "Spell", 4, "Basic", "Deal 3 damage to all characters.", "Warlock", null, null, null, null, "hellfire");
		final Card card43 = new Card("ice lance", "Spell", 1, "Basic", "Freeze a character. If it was already Frozen, deal 4 damage instead.", "Mage", null, null, null, null, "ice_lance");
		final Card card44 = new Card("rockbiter weapon", "Spell", 1, "Basic", "Give a friendly character +3 Attack this turn.", "Shaman", null, null, null, null, "rockbiter_weapon");
		final Card card45 = new Card("shadow bolt", "Spell", 3, "Basic", "Deal 4 damage to a minion.", "Warlock", null, null, null, null, "shadow_bolt");
		final Card card46 = new Card("voidwalker", "Minion", 1, "Basic", "Taunt.", "Warlock", 1, 3, null, "Demon", "voidwalker");
		final Card card47 = new Card("water elemental", "Minion", 4, "Basic", "Freeze any character damaged by this minion.", "Mage", 3, 6, null, null, "water_elemental");
		final Card card48 = new Card("windfury", "Spell", 2, "Basic", "Give a minion Windfury.", "Shaman", null, null, null, null, "windfury");
		final Card card49 = new Card("arcanite reaper", "Weapon", 5, "Basic", null, "Warrior", 5, null, 2, null, "arcanite_reaper");
		final Card card50 = new Card("assassinate", "Spell", 5, "Basic", "Destroy an enemy minion.", "Rogue", null, null, null, null, "assassinate");
		final Card card51 = new Card("assassin's blade", "Weapon", 5, "Basic", null, "Rogue", 5, null, 4, null, "assassins_blade");
		final Card card52 = new Card("blessing of kings", "Spell", 4, "Basic", "Give a minion +4/+4. (+4 Attack/+4 Health)", "Paladin", null, null, null, null, "blessing_of_kings");
		final Card card53 = new Card("charge", "Spell", 3, "Basic", "Give a friendly minion +2 Attack and Charge.", "Warrior", null, null, null, null, "charge");
		final Card card54 = new Card("blessings of might", "Spell", 1, "Basic", "Give a minion +3 Attack.", "Paladin", null, null, null, null, "blessing_of_might");
		final Card card55 = new Card("consecration", "Spell", 4, "Basic", "Deal 2 damage to all enemies.", "Paladin", null, null, null, null, "consecration");
		final Card card56 = new Card("deadly poison", "Spell", 1, "Basic", "Give your weapon +2 Attack.", "Rogue", null, null, null, null, "deadly_poison");
		final Card card57 = new Card("execute", "Spell", 1, "Basic", "Destroy a damaged enemy minion.", "Warrior", null, null, null, null, "execute");
		final Card card58 = new Card("fiery war axe", "Weapon", 2, "Basic", null, "Warrior", 3, null, 2, null, "fiery_war_axe");
		final Card card59 = new Card("guardian of kings", "Minion", 7, "Basic", "Battlecry: Restore 6 Health to your hero.", "Paladin", 5, 6, null, null, "guardian_of_kings");
		final Card card60 = new Card("hammer of wrath", "Spell", 4, "Basic", "Deal 3 damage. Draw a card.", "Paladin", null, null, null, null, "hammer_of_wrath");
		final Card card61 = new Card("heroic strike", "Spell", 2, "Basic", "Give your hero +4 Attack this turn.", "Warrior", null, null, null, null, "heroic_strike");
		final Card card62 = new Card("holy light", "Spell", 2, "Basic", "Restore 6 Health.", "Paladin", null, null, null, null, "holy_light");
		final Card card63 = new Card("hunter's mark", "Spell", 0, "Basic", "Change a minion's Health to 1.", "Hunter", null, null, null, null, "hunters_mark");
		final Card card64 = new Card("light's justice", "Weapon", 1, "Basic", null, "Paladin", 1, null, 4, null, "lights_justice");
		final Card card65 = new Card("rampage", "Spell", 2, "Basic", "Give a damaged minion +3/+3.", "Warrior", null, null, null, null, "rampage");
		final Card card66 = new Card("sinister strike", "Spell", 1, "Basic", "Deal 3 damage to the enemy hero.", "Rogue", null, null, null, null, "sinister_strike");
		final Card card67 = new Card("sprint", "Spell", 7, "Basic", "Draw 4 cards.", "Rogue", null, null, null, null, "sprint");
		final Card card68 = new Card("truesilver champion", "Weapon", 4, "Basic", "Whenever your hero attacks, restore 2 Health to it.", "Paladin", 4, null, 2, null, "truesilver_champion");
		final Card card69 = new Card("archmage", "Minion", 6, "Basic", "Spell Dmage +1", "Neutral", 4, 7, null, null, "archmage");
		final Card card70 = new Card("cleave", "Spell", 2, "Basic", "Deal 2damage to two random enemy minions.", "Warrior", null, null, null, null, "cleave");
		final Card card71 = new Card("earthen ring farseer", "Minion", 3, "Common", "Battlecry: Restore 3 Health.", "Neutral", 3, 3, null, null, "earthen_ring_farseer");
		final Card card72 = new Card("frostwolf grunt", "Minion", 2, "Basic", "Taunt", "neutral", 2, 2, null, null, "frostwolf_grunt");
		final Card card73 = new Card("gromish inventor", "Minion", 4, "Basic", "Battlecry: Draw a card.", "Neutral", 2, 4, null, null, "gnomish_inventor");
		final Card card74 = new Card("ironforge rifleman", "Minion", 3, "Basic", "Battlecry: Deal 1 damage.", "Neutral", 2, 2, null, null, "ironforge_rifleman");
		final Card card75 = new Card("ironfur grizzly", "Minion", 3, "Basic", "Taunt", "Neutral", 3, 3, null, "Beast", "ironfur_grizzly");
		final Card card76 = new Card("kobold geomancer", "Minion", 2, "Basic", "Spell Damage +1", "Neutral", 2, 2, null, null, "kobold_geomancer");
		final Card card77 = new Card("lord_of_the_arena", "Minion", 6, "Basic", "Taunt", "Neutral", 6, 5, null, null, "lord_of_the_arena");
		final Card card78 = new Card("magma rager", "Minion", 3, "Basic", null, "Neutral", 5, 1, null, null, "magma_rager");
		final Card card79 = new Card("oasis snapjaw", "Minion", 4, "Basic", null, "Neutral", 2, 7, null, "Beast", "oasis_snapjaw");
		final Card card80 = new Card("raid leader", "Minion", 3, "Basic", "Your other minions have +1 Attack.", "Neutral", 2, 2, null, null, "raid_leader");
		final Card card81 = new Card("ravenholdt assassin", "Minion", 7, "Rare", "Stealth", "Neutral", 7, 5, null, null, "ravenholdt_assassin");
		final Card card82 = new Card("river crocolisk", "Minion", 2, "Basic", null, "Neutral", 2, 3, null, "Beast", "river_crocolisk");
		final Card card83 = new Card("silver hand knight", "Minion", 5, "Basic", "Battlecry: Summon a 2/2 Squire.", "Neutral", 4, 4, null, null, "silver_hand_knight");
		final Card card84 = new Card("silverback patriarch", "Minion", 3, "Basic", "Taunt", "Neutral", 1, 4, null, "Beast", "silverback_patriarch");
		final Card card85 = new Card("southsea deckhand", "Minion", 1, "Common", "Has Charge while you have a weapon equipped.", "Neutral", 2, 1, null, "Pirate", "southsea_deckhand");
		final Card card86 = new Card("stormpike commando", "Minion", 5, "Basic", "Battlecry: Deal 2 damage.", "Neutral", 4, 2, null, null, "stormpike_commando");
		final Card card87 = new Card("stormwind knight", "Minion", 4, "Basic", "Charge", "Neutral", 2, 5, null, null, "stormwind_knight");
		final Card card88 = new Card("wolfrider", "Minion", 3, "Basic", "Charge", "Neutral", 3, 1, null, null, "wolfrider");
		
		
		List<Card> cards = Arrays.asList(card1, card2, card3, card4, card5, card6, card7, card8, card9, card10,
				card11, card12, card13, card14, card15, card16, card17, card18, card19, card20,
				card21, card22, card23, card24, card25, card26, card27, card28, card29, card30,
				card31, card32, card33, card34, card35, card36, card37, card38, card39, card40,
				card41, card42, card43, card44, card45, card46, card47, card48, card49, card50,
				card51, card52, card53, card54, card55, card56, card57, card58, card59, card60,
				card61, card62, card63, card64, card65, card66, card67, card68, card69, card70,
				card71, card72, card73, card74, card75, card76, card77, card78, card79, card80,
				card81, card82, card83, card84, card85, card86, card87, card88);
	
		
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		
		for (int i = 0; i < cards.size(); i++){
			
			ContentValues values = new ContentValues();
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
		
		db.close();
		
	}
	
	public DeckDataAggregation createDeck(String name, String className){
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
		
		db.close();
		
		return ViewHandler.getInstance(dbHelper).createDeckDataAggregation(deck);
	}

	public void deleteDeck() {
		String deckName = DeckHandler.getInstance(dbHelper).getDeck().getName();
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		String[] args = new String[]{deckName};
		db.delete(DeckEntry.TABLE_NAME, DeckEntry.COLUMN_NAME_NAME + " = ?", args);
		db.delete(FormationEntry.TABLE_NAME, FormationEntry.COLUMN_NAME_DECK + " = ?", args);
	}
	
	public void updateDeck() {
		String deckName = DeckHandler.getInstance(dbHelper).getDeck().getName();
		List<Formation> tmpFormations = DeckHandler.getInstance(dbHelper).getTmpFormations();
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
