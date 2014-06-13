package com.hsa.test;

import android.app.Instrumentation.ActivityMonitor;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.CheckBox;

import com.hsa.MainActivity;
import com.hsa.activity.DeckActivity;
import com.hsa.activity.FilterActivity;
import com.hsa.database.HSADatabaseHelper;
import com.hsa.handler.SaveHandler;
import com.robotium.solo.Solo;


public class ViewDeckCardsTest extends ActivityInstrumentationTestCase2<MainActivity> {

	private Solo solo;
	private SaveHandler saveHandler;
	
	public ViewDeckCardsTest() {
		super(MainActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
		saveHandler = SaveHandler.getInstance(HSADatabaseHelper.getInstance(getActivity()));
		saveHandler.createDeck("MyDruid", "Druid");
		saveHandler.createDeck("MyMage", "Mage");
		saveHandler.createDeck("MyRogue", "Rogue");
		saveHandler.createDeck("MyPaladin", "Paladin");
		saveHandler.createDeck("MyHunter", "Hunter");
		saveHandler.createDeck("MyPriest", "Priest");
		saveHandler.createDeck("MyWarlock", "Warlock");
		saveHandler.createDeck("MyWarrior", "Warrior");
		saveHandler.createDeck("MyShaman", "Shaman");

	}
	
	protected void tearDown() throws Exception {
		solo.finishOpenedActivities();
		super.tearDown();	
	}
	
	public void testInit() {
		assertNotNull(solo);
	}
	
	public void testValid1() {
		//Arrange
		ActivityMonitor activityMonitor = getInstrumentation().addMonitor(DeckActivity.class.getName(), null, false);
		solo.scrollViewToSide(solo.getView("pager"), Solo.RIGHT);
		solo.clickOnText("MyDruid");
		DeckActivity da = (DeckActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 50000);
		
		//Act
		activityMonitor = getInstrumentation().addMonitor(FilterActivity.class.getName(), null, false);
		Intent intent = new Intent(da, FilterActivity.class);
		intent.putExtra("classDeck", da.getDeckDataAggregation().getClassName());
		intent.putStringArrayListExtra("classResult", da.getClassFilters());
		intent.putStringArrayListExtra("costResult", da.getCostFilters());
		intent.putStringArrayListExtra("rarityResult", da.getRarityFilters());
		intent.putStringArrayListExtra("typeResult", da.getTypeFilters());
		da.startActivityForResult(intent, 1);
		FilterActivity fa = (FilterActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 50000);
		
		//Assume
		CheckBox chkClass = (CheckBox) solo.getView(com.hsa.R.id.chkDruid);
		CheckBox chkNeutral = (CheckBox) solo.getView(com.hsa.R.id.chkNeutral);
		View chkMage = fa.findViewById(com.hsa.R.id.chkMage);
		View chkPaladin = fa.findViewById(com.hsa.R.id.chkPaladin);
		View chkRogue = fa.findViewById(com.hsa.R.id.chkRogue);
		View chkWarlock = fa.findViewById(com.hsa.R.id.chkWarlock);
		View chkWarrior = fa.findViewById(com.hsa.R.id.chkWarrior);
		View chkPriest = fa.findViewById(com.hsa.R.id.chkPriest);
		View chkHunter = fa.findViewById(com.hsa.R.id.chkHunter);
		View chkShaman = fa.findViewById(com.hsa.R.id.chkShaman);
		assertNull(chkMage);
		assertNull(chkPaladin);
		assertNull(chkRogue);
		assertNull(chkWarlock);
		assertNull(chkWarrior);
		assertNull(chkPriest);
		assertNull(chkHunter);
		assertNull(chkShaman);
		assertNotNull(chkClass);
		assertNotNull(chkNeutral);
		assertTrue(chkClass.isChecked());
		assertTrue(chkNeutral.isChecked());
	}
	
	public void testValid2() {
		//Arrange
		ActivityMonitor activityMonitor = getInstrumentation().addMonitor(DeckActivity.class.getName(), null, false);
		solo.scrollViewToSide(solo.getView("pager"), Solo.RIGHT);
		solo.clickOnText("MyMage");
		DeckActivity da = (DeckActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 50000);
		
		
		//Act
		activityMonitor = getInstrumentation().addMonitor(FilterActivity.class.getName(), null, false);
		Intent intent = new Intent(da, FilterActivity.class);
		intent.putExtra("classDeck", da.getDeckDataAggregation().getClassName());
		intent.putStringArrayListExtra("classResult", da.getClassFilters());
		intent.putStringArrayListExtra("costResult", da.getCostFilters());
		intent.putStringArrayListExtra("rarityResult", da.getRarityFilters());
		intent.putStringArrayListExtra("typeResult", da.getTypeFilters());
		da.startActivityForResult(intent, 1);
		FilterActivity fa = (FilterActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 50000);
		
		//Assume
		CheckBox chkClass = (CheckBox) solo.getView(com.hsa.R.id.chkMage);
		CheckBox chkNeutral = (CheckBox) solo.getView(com.hsa.R.id.chkNeutral);
		View chkDruid = fa.findViewById(com.hsa.R.id.chkDruid);
		View chkPaladin = fa.findViewById(com.hsa.R.id.chkPaladin);
		View chkRogue = fa.findViewById(com.hsa.R.id.chkRogue);
		View chkWarlock = fa.findViewById(com.hsa.R.id.chkWarlock);
		View chkWarrior = fa.findViewById(com.hsa.R.id.chkWarrior);
		View chkPriest = fa.findViewById(com.hsa.R.id.chkPriest);
		View chkHunter = fa.findViewById(com.hsa.R.id.chkHunter);
		View chkShaman = fa.findViewById(com.hsa.R.id.chkShaman);
		assertNull(chkDruid);
		assertNull(chkPaladin);
		assertNull(chkRogue);
		assertNull(chkWarlock);
		assertNull(chkWarrior);
		assertNull(chkPriest);
		assertNull(chkHunter);
		assertNull(chkShaman);
		assertNotNull(chkClass);
		assertNotNull(chkNeutral);
		assertTrue(chkClass.isChecked());
		assertTrue(chkNeutral.isChecked());
	}
	
	public void testValid3() {
		//Arrange
		ActivityMonitor activityMonitor = getInstrumentation().addMonitor(DeckActivity.class.getName(), null, false);
		solo.scrollViewToSide(solo.getView("pager"), Solo.RIGHT);
		solo.clickOnText("MyRogue");
		DeckActivity da = (DeckActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 50000);
		
		
		//Act
		activityMonitor = getInstrumentation().addMonitor(FilterActivity.class.getName(), null, false);
		Intent intent = new Intent(da, FilterActivity.class);
		intent.putExtra("classDeck", da.getDeckDataAggregation().getClassName());
		intent.putStringArrayListExtra("classResult", da.getClassFilters());
		intent.putStringArrayListExtra("costResult", da.getCostFilters());
		intent.putStringArrayListExtra("rarityResult", da.getRarityFilters());
		intent.putStringArrayListExtra("typeResult", da.getTypeFilters());
		da.startActivityForResult(intent, 1);
		FilterActivity fa = (FilterActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 50000);
		
		//Assume
		CheckBox chkClass = (CheckBox) solo.getView(com.hsa.R.id.chkRogue);
		CheckBox chkNeutral = (CheckBox) solo.getView(com.hsa.R.id.chkNeutral);
		View chkDruid = fa.findViewById(com.hsa.R.id.chkDruid);
		View chkPaladin = fa.findViewById(com.hsa.R.id.chkPaladin);
		View chkMage = fa.findViewById(com.hsa.R.id.chkMage);
		View chkWarlock = fa.findViewById(com.hsa.R.id.chkWarlock);
		View chkWarrior = fa.findViewById(com.hsa.R.id.chkWarrior);
		View chkPriest = fa.findViewById(com.hsa.R.id.chkPriest);
		View chkHunter = fa.findViewById(com.hsa.R.id.chkHunter);
		View chkShaman = fa.findViewById(com.hsa.R.id.chkShaman);
		assertNull(chkDruid);
		assertNull(chkPaladin);
		assertNull(chkMage);
		assertNull(chkWarlock);
		assertNull(chkWarrior);
		assertNull(chkPriest);
		assertNull(chkHunter);
		assertNull(chkShaman);
		assertNotNull(chkClass);
		assertNotNull(chkNeutral);
		assertTrue(chkClass.isChecked());
		assertTrue(chkNeutral.isChecked());
	}

	public void testValid4() {
		//Arrange
		ActivityMonitor activityMonitor = getInstrumentation().addMonitor(DeckActivity.class.getName(), null, false);
		solo.scrollViewToSide(solo.getView("pager"), Solo.RIGHT);
		solo.clickOnText("MyPaladin");
		DeckActivity da = (DeckActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 50000);
		
		
		//Act
		activityMonitor = getInstrumentation().addMonitor(FilterActivity.class.getName(), null, false);
		Intent intent = new Intent(da, FilterActivity.class);
		intent.putExtra("classDeck", da.getDeckDataAggregation().getClassName());
		intent.putStringArrayListExtra("classResult", da.getClassFilters());
		intent.putStringArrayListExtra("costResult", da.getCostFilters());
		intent.putStringArrayListExtra("rarityResult", da.getRarityFilters());
		intent.putStringArrayListExtra("typeResult", da.getTypeFilters());
		da.startActivityForResult(intent, 1);
		FilterActivity fa = (FilterActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 50000);
		
		//Assume
		CheckBox chkClass = (CheckBox) solo.getView(com.hsa.R.id.chkPaladin);
		CheckBox chkNeutral = (CheckBox) solo.getView(com.hsa.R.id.chkNeutral);
		View chkDruid = fa.findViewById(com.hsa.R.id.chkDruid);
		View chkRogue = fa.findViewById(com.hsa.R.id.chkRogue);
		View chkMage = fa.findViewById(com.hsa.R.id.chkMage);
		View chkWarlock = fa.findViewById(com.hsa.R.id.chkWarlock);
		View chkWarrior = fa.findViewById(com.hsa.R.id.chkWarrior);
		View chkPriest = fa.findViewById(com.hsa.R.id.chkPriest);
		View chkHunter = fa.findViewById(com.hsa.R.id.chkHunter);
		View chkShaman = fa.findViewById(com.hsa.R.id.chkShaman);
		assertNull(chkDruid);
		assertNull(chkRogue);
		assertNull(chkMage);
		assertNull(chkWarlock);
		assertNull(chkWarrior);
		assertNull(chkPriest);
		assertNull(chkHunter);
		assertNull(chkShaman);
		assertNotNull(chkClass);
		assertNotNull(chkNeutral);
		assertTrue(chkClass.isChecked());
		assertTrue(chkNeutral.isChecked());
	}

	
	
	public void testValid5() {
		//Arrange
		ActivityMonitor activityMonitor = getInstrumentation().addMonitor(DeckActivity.class.getName(), null, false);
		solo.scrollViewToSide(solo.getView("pager"), Solo.RIGHT);
		solo.clickOnText("MyHunter");
		DeckActivity da = (DeckActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 50000);
		
		
		//Act
		activityMonitor = getInstrumentation().addMonitor(FilterActivity.class.getName(), null, false);
		Intent intent = new Intent(da, FilterActivity.class);
		intent.putExtra("classDeck", da.getDeckDataAggregation().getClassName());
		intent.putStringArrayListExtra("classResult", da.getClassFilters());
		intent.putStringArrayListExtra("costResult", da.getCostFilters());
		intent.putStringArrayListExtra("rarityResult", da.getRarityFilters());
		intent.putStringArrayListExtra("typeResult", da.getTypeFilters());
		da.startActivityForResult(intent, 1);
		FilterActivity fa = (FilterActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 50000);
		
		//Assume
		CheckBox chkClass = (CheckBox) solo.getView(com.hsa.R.id.chkHunter);
		CheckBox chkNeutral = (CheckBox) solo.getView(com.hsa.R.id.chkNeutral);
		View chkDruid = fa.findViewById(com.hsa.R.id.chkDruid);
		View chkPaladin = fa.findViewById(com.hsa.R.id.chkPaladin);
		View chkMage = fa.findViewById(com.hsa.R.id.chkMage);
		View chkWarlock = fa.findViewById(com.hsa.R.id.chkWarlock);
		View chkWarrior = fa.findViewById(com.hsa.R.id.chkWarrior);
		View chkPriest = fa.findViewById(com.hsa.R.id.chkPriest);
		View chkRogue = fa.findViewById(com.hsa.R.id.chkRogue);
		View chkShaman = fa.findViewById(com.hsa.R.id.chkShaman);
		assertNull(chkDruid);
		assertNull(chkPaladin);
		assertNull(chkMage);
		assertNull(chkWarlock);
		assertNull(chkWarrior);
		assertNull(chkPriest);
		assertNull(chkRogue);
		assertNull(chkShaman);
		assertNotNull(chkClass);
		assertNotNull(chkNeutral);
		assertTrue(chkClass.isChecked());
		assertTrue(chkNeutral.isChecked());
	}
	
	public void testValid6() {
		//Arrange
		ActivityMonitor activityMonitor = getInstrumentation().addMonitor(DeckActivity.class.getName(), null, false);
		solo.scrollViewToSide(solo.getView("pager"), Solo.RIGHT);
		solo.clickOnText("MyPriest");
		DeckActivity da = (DeckActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 50000);
		
		
		//Act
		activityMonitor = getInstrumentation().addMonitor(FilterActivity.class.getName(), null, false);
		Intent intent = new Intent(da, FilterActivity.class);
		intent.putExtra("classDeck", da.getDeckDataAggregation().getClassName());
		intent.putStringArrayListExtra("classResult", da.getClassFilters());
		intent.putStringArrayListExtra("costResult", da.getCostFilters());
		intent.putStringArrayListExtra("rarityResult", da.getRarityFilters());
		intent.putStringArrayListExtra("typeResult", da.getTypeFilters());
		da.startActivityForResult(intent, 1);
		FilterActivity fa = (FilterActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 50000);
		
		//Assume
		CheckBox chkClass = (CheckBox) solo.getView(com.hsa.R.id.chkPriest);
		CheckBox chkNeutral = (CheckBox) solo.getView(com.hsa.R.id.chkNeutral);
		View chkDruid = fa.findViewById(com.hsa.R.id.chkDruid);
		View chkPaladin = fa.findViewById(com.hsa.R.id.chkPaladin);
		View chkMage = fa.findViewById(com.hsa.R.id.chkMage);
		View chkWarlock = fa.findViewById(com.hsa.R.id.chkWarlock);
		View chkWarrior = fa.findViewById(com.hsa.R.id.chkWarrior);
		View chkHunter = fa.findViewById(com.hsa.R.id.chkHunter);
		View chkRogue = fa.findViewById(com.hsa.R.id.chkRogue);
		View chkShaman = fa.findViewById(com.hsa.R.id.chkShaman);
		assertNull(chkDruid);
		assertNull(chkPaladin);
		assertNull(chkMage);
		assertNull(chkWarlock);
		assertNull(chkWarrior);
		assertNull(chkHunter);
		assertNull(chkRogue);
		assertNull(chkShaman);
		assertNotNull(chkClass);
		assertNotNull(chkNeutral);
		assertTrue(chkClass.isChecked());
		assertTrue(chkNeutral.isChecked());
	}
	
	public void testValid7() {
		//Arrange
		ActivityMonitor activityMonitor = getInstrumentation().addMonitor(DeckActivity.class.getName(), null, false);
		solo.scrollViewToSide(solo.getView("pager"), Solo.RIGHT);
		solo.clickOnText("MyWarlock");
		DeckActivity da = (DeckActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 50000);
		
		
		//Act
		activityMonitor = getInstrumentation().addMonitor(FilterActivity.class.getName(), null, false);
		Intent intent = new Intent(da, FilterActivity.class);
		intent.putExtra("classDeck", da.getDeckDataAggregation().getClassName());
		intent.putStringArrayListExtra("classResult", da.getClassFilters());
		intent.putStringArrayListExtra("costResult", da.getCostFilters());
		intent.putStringArrayListExtra("rarityResult", da.getRarityFilters());
		intent.putStringArrayListExtra("typeResult", da.getTypeFilters());
		da.startActivityForResult(intent, 1);
		FilterActivity fa = (FilterActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 50000);
		
		//Assume
		CheckBox chkClass = (CheckBox) solo.getView(com.hsa.R.id.chkWarlock);
		CheckBox chkNeutral = (CheckBox) solo.getView(com.hsa.R.id.chkNeutral);
		View chkDruid = fa.findViewById(com.hsa.R.id.chkDruid);
		View chkPaladin = fa.findViewById(com.hsa.R.id.chkPaladin);
		View chkMage = fa.findViewById(com.hsa.R.id.chkMage);
		View chkPriest = fa.findViewById(com.hsa.R.id.chkPriest);
		View chkWarrior = fa.findViewById(com.hsa.R.id.chkWarrior);
		View chkHunter = fa.findViewById(com.hsa.R.id.chkHunter);
		View chkRogue = fa.findViewById(com.hsa.R.id.chkRogue);
		View chkShaman = fa.findViewById(com.hsa.R.id.chkShaman);
		assertNull(chkDruid);
		assertNull(chkPaladin);
		assertNull(chkMage);
		assertNull(chkPriest);
		assertNull(chkWarrior);
		assertNull(chkHunter);
		assertNull(chkRogue);
		assertNull(chkShaman);
		assertNotNull(chkClass);
		assertNotNull(chkNeutral);
		assertTrue(chkClass.isChecked());
		assertTrue(chkNeutral.isChecked());
	}
	
	public void testValid8() {
		//Arrange
		ActivityMonitor activityMonitor = getInstrumentation().addMonitor(DeckActivity.class.getName(), null, false);
		solo.scrollViewToSide(solo.getView("pager"), Solo.RIGHT);
		solo.clickOnText("MyWarrior");
		DeckActivity da = (DeckActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 50000);
		
		
		//Act
		activityMonitor = getInstrumentation().addMonitor(FilterActivity.class.getName(), null, false);
		Intent intent = new Intent(da, FilterActivity.class);
		intent.putExtra("classDeck", da.getDeckDataAggregation().getClassName());
		intent.putStringArrayListExtra("classResult", da.getClassFilters());
		intent.putStringArrayListExtra("costResult", da.getCostFilters());
		intent.putStringArrayListExtra("rarityResult", da.getRarityFilters());
		intent.putStringArrayListExtra("typeResult", da.getTypeFilters());
		da.startActivityForResult(intent, 1);
		FilterActivity fa = (FilterActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 50000);
		
		//Assume
		CheckBox chkClass = (CheckBox) solo.getView(com.hsa.R.id.chkWarrior);
		CheckBox chkNeutral = (CheckBox) solo.getView(com.hsa.R.id.chkNeutral);
		View chkDruid = fa.findViewById(com.hsa.R.id.chkDruid);
		View chkPaladin = fa.findViewById(com.hsa.R.id.chkPaladin);
		View chkMage = fa.findViewById(com.hsa.R.id.chkMage);
		View chkPriest = fa.findViewById(com.hsa.R.id.chkPriest);
		View chkWarlock = fa.findViewById(com.hsa.R.id.chkWarlock);
		View chkHunter = fa.findViewById(com.hsa.R.id.chkHunter);
		View chkRogue = fa.findViewById(com.hsa.R.id.chkRogue);
		View chkShaman = fa.findViewById(com.hsa.R.id.chkShaman);
		assertNull(chkDruid);
		assertNull(chkPaladin);
		assertNull(chkMage);
		assertNull(chkPriest);
		assertNull(chkWarlock);
		assertNull(chkHunter);
		assertNull(chkRogue);
		assertNull(chkShaman);
		assertNotNull(chkClass);
		assertNotNull(chkNeutral);
		assertTrue(chkClass.isChecked());
		assertTrue(chkNeutral.isChecked());
	}
	
	public void testValid9() {
		//Arrange
		ActivityMonitor activityMonitor = getInstrumentation().addMonitor(DeckActivity.class.getName(), null, false);
		solo.scrollViewToSide(solo.getView("pager"), Solo.RIGHT);
		solo.clickOnText("MyShaman");
		DeckActivity da = (DeckActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 50000);
		
		
		//Act
		activityMonitor = getInstrumentation().addMonitor(FilterActivity.class.getName(), null, false);
		Intent intent = new Intent(da, FilterActivity.class);
		intent.putExtra("classDeck", da.getDeckDataAggregation().getClassName());
		intent.putStringArrayListExtra("classResult", da.getClassFilters());
		intent.putStringArrayListExtra("costResult", da.getCostFilters());
		intent.putStringArrayListExtra("rarityResult", da.getRarityFilters());
		intent.putStringArrayListExtra("typeResult", da.getTypeFilters());
		da.startActivityForResult(intent, 1);
		FilterActivity fa = (FilterActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 50000);
		
		//Assume
		CheckBox chkClass = (CheckBox) solo.getView(com.hsa.R.id.chkShaman);
		CheckBox chkNeutral = (CheckBox) solo.getView(com.hsa.R.id.chkNeutral);
		View chkDruid = fa.findViewById(com.hsa.R.id.chkDruid);
		View chkPaladin = fa.findViewById(com.hsa.R.id.chkPaladin);
		View chkMage = fa.findViewById(com.hsa.R.id.chkMage);
		View chkPriest = fa.findViewById(com.hsa.R.id.chkPriest);
		View chkWarrior = fa.findViewById(com.hsa.R.id.chkWarrior);
		View chkHunter = fa.findViewById(com.hsa.R.id.chkHunter);
		View chkRogue = fa.findViewById(com.hsa.R.id.chkRogue);
		View chkWarlock = fa.findViewById(com.hsa.R.id.chkWarlock);
		assertNull(chkDruid);
		assertNull(chkPaladin);
		assertNull(chkMage);
		assertNull(chkPriest);
		assertNull(chkWarrior);
		assertNull(chkHunter);
		assertNull(chkRogue);
		assertNull(chkWarlock);
		assertNotNull(chkClass);
		assertNotNull(chkNeutral);
		assertTrue(chkClass.isChecked());
		assertTrue(chkNeutral.isChecked());
	}
	
}
