package com.hsa.test;

import java.util.ArrayList;
import java.util.List;

import com.hsa.MainActivity;
import com.hsa.aggregation.GraphicalAggregation;
import com.hsa.bean.Deck;
import com.hsa.bean.Formation;
import com.hsa.database.HSADatabaseHelper;
import com.hsa.handler.DeckHandler;

import android.test.ActivityInstrumentationTestCase2;

public class InsertCardTest extends ActivityInstrumentationTestCase2<MainActivity> {

	private DeckHandler deckHandler;
	
	public InsertCardTest() {
		super(MainActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		deckHandler = DeckHandler.getInstance(HSADatabaseHelper.getInstance(getActivity()));
		
	}
	
	public void testSingolo1() {
		deckHandler.setDeck(new Deck("MyMage", "Mage", "", ""));
		List<Formation> tmpFormations = new ArrayList<Formation>();
		tmpFormations.add(new Formation("frostbolt", "MyMage", 1));
		deckHandler.setTmpFormations(tmpFormations);
		List<GraphicalAggregation> ga = new ArrayList<GraphicalAggregation>();
		ga.add(new GraphicalAggregation("frostbolt", 1, 2130837602));
		deckHandler.setTmpGraphicalsAggregations(ga);
		
		deckHandler.modifyDeckRequest("arcane intellect", true);
		
		assertTrue(deckHandler.getTmpGraphicalsAggregations().size() == 2);
		assertTrue(deckHandler.getTmpGraphicalsAggregations().get(0).getName().equals("frostbolt"));
		assertTrue(deckHandler.getTmpGraphicalsAggregations().get(1).getName().equals("arcane intellect"));
	}
	
	public void testSingolo2(){
		deckHandler.setDeck(new Deck("MyMage", "Mage", "", ""));
		List<Formation> tmpFormations = new ArrayList<Formation>();
		tmpFormations.add(new Formation("frostbolt", "MyMage", 10));
		tmpFormations.add(new Formation("blizzard", "MyMage", 19));
		deckHandler.setTmpFormations(tmpFormations);
		List<GraphicalAggregation> ga = new ArrayList<GraphicalAggregation>();
		ga.add(new GraphicalAggregation("frostbolt", 1, 2130837602));
		ga.add(new GraphicalAggregation("blizzard", 1, 2130837595));
		deckHandler.setTmpGraphicalsAggregations(ga);
		
		deckHandler.modifyDeckRequest("arcane intellect", true);
		
		assertTrue(deckHandler.getTmpGraphicalsAggregations().size() == 3);
		assertTrue(deckHandler.getTmpGraphicalsAggregations().get(0).getName().equals("frostbolt"));
		assertTrue(deckHandler.getTmpGraphicalsAggregations().get(1).getName().equals("blizzard"));
		assertTrue(deckHandler.getTmpGraphicalsAggregations().get(2).getName().equals("arcane intellect"));
	}
	
	public void testSingolo3(){
		deckHandler.setDeck(new Deck("MyMage", "Mage", "", ""));
		List<Formation> tmpFormations = new ArrayList<Formation>();
		tmpFormations.add(new Formation("frostbolt", "MyMage", 10));
		tmpFormations.add(new Formation("blizzard", "MyMage", 1));
		deckHandler.setTmpFormations(tmpFormations);
		List<GraphicalAggregation> ga = new ArrayList<GraphicalAggregation>();
		ga.add(new GraphicalAggregation("frostbolt", 1, 2130837602));
		ga.add(new GraphicalAggregation("blizzard", 1, 2130837595));
		deckHandler.setTmpGraphicalsAggregations(ga);
		
		deckHandler.modifyDeckRequest("blizzard", true);
		
		assertTrue(deckHandler.getTmpGraphicalsAggregations().size() == 2);
		assertTrue(deckHandler.getTmpGraphicalsAggregations().get(0).getName().equals("frostbolt"));
		assertTrue(deckHandler.getTmpGraphicalsAggregations().get(1).getName().equals("blizzard"));
		assertTrue(deckHandler.getTmpGraphicalsAggregations().get(1).getOccurence() == 2);
	}

}
