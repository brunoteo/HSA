package com.hsa.test;

import java.util.ArrayList;
import java.util.List;

import com.hsa.MainActivity;
import com.hsa.bean.Deck;
import com.hsa.bean.Formation;
import com.hsa.database.HSADatabaseHelper;
import com.hsa.handler.DeckHandler;

import android.test.ActivityInstrumentationTestCase2;

public class SaveTest extends ActivityInstrumentationTestCase2<MainActivity> {

	private DeckHandler deckHandler;
	
	public SaveTest() {
		super(MainActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		deckHandler = DeckHandler.getInstance(HSADatabaseHelper.getInstance(getActivity()));
	}
	
	public void testValid1(){
		deckHandler.setDeck(new Deck("MyMage", "Mage", "", ""));
		List<Formation> tmpFormations = new ArrayList<Formation>();
		deckHandler.setTmpFormations(tmpFormations);
		List<Formation> copyFormations = new ArrayList<Formation>();
		deckHandler.setCopyFormations(copyFormations);
		
		boolean value = deckHandler.controlModifyRequest();
		
		assertTrue(!value);
	}
	
	public void testValid2(){
		deckHandler.setDeck(new Deck("MyMage", "Mage", "", ""));
		List<Formation> tmpFormations = new ArrayList<Formation>();
		tmpFormations.add(new Formation("blizzard", "MyMage", 1));
		deckHandler.setTmpFormations(tmpFormations);
		List<Formation> copyFormations = new ArrayList<Formation>();
		copyFormations.add(new Formation("blizzard", "MyMage", 1));
		deckHandler.setCopyFormations(copyFormations);
		
		boolean value = deckHandler.controlModifyRequest();
		
		assertTrue(!value);
	}
	
	public void testValid3(){
		deckHandler.setDeck(new Deck("MyMage", "Mage", "", ""));
		List<Formation> tmpFormations = new ArrayList<Formation>();
		tmpFormations.add(new Formation("blizzard", "MyMage", 2));
		tmpFormations.add(new Formation("frostbolt", "MyMage", 6));
		deckHandler.setTmpFormations(tmpFormations);
		List<Formation> copyFormations = new ArrayList<Formation>();
		copyFormations.add(new Formation("blizzard", "MyMage", 2));
		copyFormations.add(new Formation("frostbolt", "MyMage", 6));
		deckHandler.setCopyFormations(copyFormations);
		
		boolean value = deckHandler.controlModifyRequest();
		
		assertTrue(!value);
	}
	
	//FIXME insert new cards until 29 different for at least one class

}
