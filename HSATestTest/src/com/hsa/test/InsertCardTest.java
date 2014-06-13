package com.hsa.test;

import java.util.ArrayList;
import java.util.List;

import com.hsa.MainActivity;
import com.hsa.aggregation.GraphicalAggregation;
import com.hsa.bean.Deck;
import com.hsa.bean.Formation;
import com.hsa.database.HSADatabaseHelper;
import com.hsa.handler.DeckHandler;
import com.hsa.handler.ViewHandler;

import android.test.ActivityInstrumentationTestCase2;

public class InsertCardTest extends ActivityInstrumentationTestCase2<MainActivity> {

	private DeckHandler deckHandler;
	
	public InsertCardTest() {
		super(MainActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		deckHandler = DeckHandler.getInstance(HSADatabaseHelper.getInstance(getActivity()));
		ViewHandler.getInstance(HSADatabaseHelper.getInstance(getActivity())).setContext(getActivity());
		
	}
	
	public void testError1() {
		//Arrange
		deckHandler.setDeck(new Deck("MyMage", "Mage", "", ""));
		List<Formation> tmpFormations = new ArrayList<Formation>();
		tmpFormations.add(new Formation("frostbolt", "MyMage", 30));
		deckHandler.setTmpFormations(tmpFormations);
		List<GraphicalAggregation> ga = new ArrayList<GraphicalAggregation>();
		ga.add(new GraphicalAggregation("frostbolt", 30, 2130837602));
		deckHandler.setTmpGraphicalsAggregations(ga);
		
		//Act
		deckHandler.modifyDeckRequest("frostbolt", true);
		
		//Assert
		assertTrue(deckHandler.getTmpGraphicalsAggregations().size() == 1);
		assertTrue(deckHandler.getTmpGraphicalsAggregations().get(0).getName().equals("frostbolt"));
		assertTrue(deckHandler.getTmpGraphicalsAggregations().get(0).getOccurrence() == 30);
		assertTrue(deckHandler.getTmpFormations().size() == 1);
		assertTrue(deckHandler.getTmpFormations().get(0).getCard().equals("frostbolt"));
		assertTrue(deckHandler.getTmpFormations().get(0).getOccurrence() == 30);
	}
	
	public void testSingle1() {
		//Arrange
		deckHandler.setDeck(new Deck("MyMage", "Mage", "", ""));
		List<Formation> tmpFormations = new ArrayList<Formation>();
		tmpFormations.add(new Formation("frostbolt", "MyMage", 1));
		deckHandler.setTmpFormations(tmpFormations);
		List<GraphicalAggregation> ga = new ArrayList<GraphicalAggregation>();
		ga.add(new GraphicalAggregation("frostbolt", 1, 2130837602));
		deckHandler.setTmpGraphicalsAggregations(ga);
		
		//Act
		deckHandler.modifyDeckRequest("arcane intellect", true);
		
		//Assert
		assertTrue(deckHandler.getTmpGraphicalsAggregations().size() == 2);
		assertTrue(deckHandler.getTmpGraphicalsAggregations().get(0).getName().equals("frostbolt"));
		assertTrue(deckHandler.getTmpGraphicalsAggregations().get(1).getName().equals("arcane intellect"));
		assertTrue(deckHandler.getTmpGraphicalsAggregations().get(0).getOccurrence() == 1);
		assertTrue(deckHandler.getTmpGraphicalsAggregations().get(1).getOccurrence() == 1);
		assertTrue(deckHandler.getTmpFormations().size() == 2);
		assertTrue(deckHandler.getTmpFormations().get(0).getCard().equals("frostbolt"));
		assertTrue(deckHandler.getTmpFormations().get(1).getCard().equals("arcane intellect"));
		assertTrue(deckHandler.getTmpFormations().get(0).getOccurrence() == 1);
		assertTrue(deckHandler.getTmpFormations().get(1).getOccurrence() == 1);
	}
	
	public void testSingle2(){
		//Arrange
		deckHandler.setDeck(new Deck("MyMage", "Mage", "", ""));
		List<Formation> tmpFormations = new ArrayList<Formation>();
		tmpFormations.add(new Formation("frostbolt", "MyMage", 10));
		tmpFormations.add(new Formation("blizzard", "MyMage", 19));
		deckHandler.setTmpFormations(tmpFormations);
		List<GraphicalAggregation> ga = new ArrayList<GraphicalAggregation>();
		ga.add(new GraphicalAggregation("frostbolt", 10, 2130837602));
		ga.add(new GraphicalAggregation("blizzard", 19, 2130837595));
		deckHandler.setTmpGraphicalsAggregations(ga);
		
		//Act
		deckHandler.modifyDeckRequest("arcane intellect", true);
		
		//Assert
		assertTrue(deckHandler.getTmpGraphicalsAggregations().size() == 3);
		assertTrue(deckHandler.getTmpGraphicalsAggregations().get(0).getName().equals("frostbolt"));
		assertTrue(deckHandler.getTmpGraphicalsAggregations().get(1).getName().equals("blizzard"));
		assertTrue(deckHandler.getTmpGraphicalsAggregations().get(2).getName().equals("arcane intellect"));
		assertTrue(deckHandler.getTmpGraphicalsAggregations().get(0).getOccurrence() == 10);
		assertTrue(deckHandler.getTmpGraphicalsAggregations().get(1).getOccurrence() == 19);
		assertTrue(deckHandler.getTmpGraphicalsAggregations().get(2).getOccurrence() == 1);
		assertTrue(deckHandler.getTmpFormations().size() == 3);
		assertTrue(deckHandler.getTmpFormations().get(0).getCard().equals("frostbolt"));
		assertTrue(deckHandler.getTmpFormations().get(1).getCard().equals("blizzard"));
		assertTrue(deckHandler.getTmpFormations().get(2).getCard().equals("arcane intellect"));
		assertTrue(deckHandler.getTmpFormations().get(0).getOccurrence() == 10);
		assertTrue(deckHandler.getTmpFormations().get(1).getOccurrence() == 19);
		assertTrue(deckHandler.getTmpFormations().get(2).getOccurrence() == 1);
	}
	
	public void testSingle3(){
		//Arrange
		deckHandler.setDeck(new Deck("MyMage", "Mage", "", ""));
		List<Formation> tmpFormations = new ArrayList<Formation>();
		tmpFormations.add(new Formation("frostbolt", "MyMage", 10));
		tmpFormations.add(new Formation("blizzard", "MyMage", 1));
		deckHandler.setTmpFormations(tmpFormations);
		List<GraphicalAggregation> ga = new ArrayList<GraphicalAggregation>();
		ga.add(new GraphicalAggregation("frostbolt", 10, 2130837602));
		ga.add(new GraphicalAggregation("blizzard", 1, 2130837595));
		deckHandler.setTmpGraphicalsAggregations(ga);
		
		//Act
		deckHandler.modifyDeckRequest("blizzard", true);
		
		//Assert
		assertTrue(deckHandler.getTmpGraphicalsAggregations().size() == 2);
		assertTrue(deckHandler.getTmpGraphicalsAggregations().get(0).getName().equals("frostbolt"));
		assertTrue(deckHandler.getTmpGraphicalsAggregations().get(1).getName().equals("blizzard"));
		assertTrue(deckHandler.getTmpGraphicalsAggregations().get(0).getOccurrence() == 10);
		assertTrue(deckHandler.getTmpGraphicalsAggregations().get(1).getOccurrence() == 2);
		assertTrue(deckHandler.getTmpFormations().size() == 2);
		assertTrue(deckHandler.getTmpFormations().get(0).getCard().equals("frostbolt"));
		assertTrue(deckHandler.getTmpFormations().get(1).getCard().equals("blizzard"));
		assertTrue(deckHandler.getTmpFormations().get(0).getOccurrence() == 10);
		assertTrue(deckHandler.getTmpFormations().get(1).getOccurrence() == 2);
	}
	
	public void testSingle4(){
		//Arrange
		deckHandler.setDeck(new Deck("MyMage", "Mage", "", ""));
		List<Formation> tmpFormations = new ArrayList<Formation>();
		tmpFormations.add(new Formation("frostbolt", "MyMage", 29));
		deckHandler.setTmpFormations(tmpFormations);
		List<GraphicalAggregation> ga = new ArrayList<GraphicalAggregation>();
		ga.add(new GraphicalAggregation("frostbolt", 29, 2130837602));
		deckHandler.setTmpGraphicalsAggregations(ga);
		
		//Act
		deckHandler.modifyDeckRequest("frostbolt", true);
		
		//Assert
		assertTrue(deckHandler.getTmpGraphicalsAggregations().size() == 1);
		assertTrue(deckHandler.getTmpGraphicalsAggregations().get(0).getName().equals("frostbolt"));
		assertTrue(deckHandler.getTmpGraphicalsAggregations().get(0).getOccurrence() == 30);
		assertTrue(deckHandler.getTmpFormations().size() == 1);
		assertTrue(deckHandler.getTmpFormations().get(0).getCard().equals("frostbolt"));
		assertTrue(deckHandler.getTmpFormations().get(0).getOccurrence() == 30);
	}
	
	public void testValid1(){
		//Arrange
		deckHandler.setDeck(new Deck("MyMage", "Mage", "", ""));
		List<Formation> tmpFormations = new ArrayList<Formation>();
		deckHandler.setTmpFormations(tmpFormations);
		List<GraphicalAggregation> ga = new ArrayList<GraphicalAggregation>();
		deckHandler.setTmpGraphicalsAggregations(ga);
		
		//Act
		deckHandler.modifyDeckRequest("frostbolt", true);
		
		//Assert
		assertTrue(deckHandler.getTmpGraphicalsAggregations().size() == 1);
		assertTrue(deckHandler.getTmpGraphicalsAggregations().get(0).getName().equals("frostbolt"));
		assertTrue(deckHandler.getTmpGraphicalsAggregations().get(0).getOccurrence() == 1);
		assertTrue(deckHandler.getTmpFormations().size() == 1);
		assertTrue(deckHandler.getTmpFormations().get(0).getCard().equals("frostbolt"));
		assertTrue(deckHandler.getTmpFormations().get(0).getOccurrence() == 1);
	}
	
	public void testValid2(){
		//Arrange
		deckHandler.setDeck(new Deck("MyMage", "Mage", "", ""));
		List<Formation> tmpFormations = new ArrayList<Formation>();
		tmpFormations.add(new Formation("frostbolt", "MyMage", 24));
		deckHandler.setTmpFormations(tmpFormations);
		List<GraphicalAggregation> ga = new ArrayList<GraphicalAggregation>();
		ga.add(new GraphicalAggregation("frostbolt", 24, 2130837602));
		deckHandler.setTmpGraphicalsAggregations(ga);
		
		//Act
		deckHandler.modifyDeckRequest("blizzard", true);
		
		//Assert
		assertTrue(deckHandler.getTmpGraphicalsAggregations().size() == 2);
		assertTrue(deckHandler.getTmpGraphicalsAggregations().get(0).getName().equals("frostbolt"));
		assertTrue(deckHandler.getTmpGraphicalsAggregations().get(1).getName().equals("blizzard"));
		assertTrue(deckHandler.getTmpGraphicalsAggregations().get(0).getOccurrence() == 24);
		assertTrue(deckHandler.getTmpGraphicalsAggregations().get(1).getOccurrence() == 1);
		assertTrue(deckHandler.getTmpFormations().size() == 2);
		assertTrue(deckHandler.getTmpFormations().get(0).getCard().equals("frostbolt"));
		assertTrue(deckHandler.getTmpFormations().get(1).getCard().equals("blizzard"));
		assertTrue(deckHandler.getTmpFormations().get(0).getOccurrence() == 24);
		assertTrue(deckHandler.getTmpFormations().get(1).getOccurrence() == 1);
		
	}
	
	public void testValid3(){
		//Arrange
		deckHandler.setDeck(new Deck("MyMage", "Mage", "", ""));
		List<Formation> tmpFormations = new ArrayList<Formation>();
		tmpFormations.add(new Formation("frostbolt", "MyMage", 10));
		tmpFormations.add(new Formation("blizzard", "MyMage", 1));
		deckHandler.setTmpFormations(tmpFormations);
		List<GraphicalAggregation> ga = new ArrayList<GraphicalAggregation>();
		ga.add(new GraphicalAggregation("frostbolt", 10, 2130837602));
		ga.add(new GraphicalAggregation("blizzard", 1, 2130837595));
		deckHandler.setTmpGraphicalsAggregations(ga);
		
		//Act
		deckHandler.modifyDeckRequest("frostbolt", true);
		
		//Assert
		assertTrue(deckHandler.getTmpGraphicalsAggregations().size() == 2);
		assertTrue(deckHandler.getTmpGraphicalsAggregations().get(0).getName().equals("frostbolt"));
		assertTrue(deckHandler.getTmpGraphicalsAggregations().get(1).getName().equals("blizzard"));
		assertTrue(deckHandler.getTmpGraphicalsAggregations().get(0).getOccurrence() == 11);
		assertTrue(deckHandler.getTmpGraphicalsAggregations().get(1).getOccurrence() == 1);
		assertTrue(deckHandler.getTmpFormations().size() == 2);
		assertTrue(deckHandler.getTmpFormations().get(0).getCard().equals("frostbolt"));
		assertTrue(deckHandler.getTmpFormations().get(1).getCard().equals("blizzard"));
		assertTrue(deckHandler.getTmpFormations().get(0).getOccurrence() == 11);
		assertTrue(deckHandler.getTmpFormations().get(1).getOccurrence() == 1);
	}

}
