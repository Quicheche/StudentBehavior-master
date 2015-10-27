package com.example.studentbehavior;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Activity to display the report.
 */
public class ReportActivity extends Activity implements ActionBar.TabListener{
	
	Report report;
	
	private static final int[] checkboxBehaviorIds =
		{R.id.checkbox_behavior_respectForSelfAndOthers, R.id.checkbox_behavior_followingDirections,
			R.id.checkbox_behavior_positiveConflictResolution, R.id.checkbox_behavior_peerMediation,
			R.id.checkbox_behavior_helpingPeerOrStaff, R.id.checkbox_behavior_leadership,
			R.id.checkbox_behavior_dealingWithAdversityPositively, R.id.checkbox_behavior_goingAboveAndBeyond,
			R.id.checkbox_behavior_refusalToFollowDirectionsOrParticipate, R.id.checkbox_behavior_disruptionOfClassOrActivity,
			R.id.checkbox_behavior_disrespectOfStaffOrScholars, R.id.checkbox_behavior_inappropriateLanguageOrGestures,
			R.id.checkbox_behavior_inappropriatePhysicalContactOrFighting, R.id.checkbox_behavior_teasingOrInstigatingConflict,
			R.id.checkbox_behavior_runningInCommonSpaces, R.id.checkbox_behavior_leavingSupervisionUnattended,
			R.id.checkbox_behavior_failingToFollowRules};
	
	private static final int[] checkboxAcademicIds =
		{R.id.checkbox_academic_respectsLearningForSelfAndOthers, R.id.checkbox_academic_followsDirections, 
	    	R.id.checkbox_academic_consistentlyPreparedAndOrganized, R.id.checkbox_academic_completesHomeworkAndAssignments, 
	    	R.id.checkbox_academic_staysOnTask, R.id.checkbox_academic_peerTutoring, R.id.checkbox_academic_disruptionOfClassLessonActivity, 
	    	R.id.checkbox_academic_refusalToFollowDirectionsAndParticipate, R.id.checkbox_academic_unPreparedAndDisorganized, 
	    	R.id.checkbox_academic_failureToCompleteHomeworkAssignment, R.id.checkbox_academic_questionableAcademicIntegrity,
	    	R.id.checkbox_academic_struggles};
	private static final int[] checkboxStrategyIds =
		{R.id.checkbox_strategy_plannedIgnoring, R.id.checkbox_strategy_behaviorLog, 
	    	R.id.checkbox_strategy_reteachReviewExpectations, R.id.checkbox_strategy_restorativeAction, 
	    	R.id.checkbox_strategy_apologyVerbalAndOrWritten, R.id.checkbox_strategy_scholarPairingTimeOut, 
	    	R.id.checkbox_strategy_timeOut, R.id.checkbox_strategy_ageAppropriateWritingActivity, 
	    	R.id.checkbox_strategy_behaviorProcessingForm, R.id.checkbox_strategy_teacherScholarConversationOutsideClassroom, 
	    	R.id.checkbox_strategy_conversationWithFamily, R.id.checkbox_strategy_conference, R.id.checkbox_strategy_lossOfPriveleges};

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a {@link FragmentPagerAdapter}
	 * derivative, which will keep every loaded fragment in memory. If this
	 * becomes too memory intensive, it may be best to switch to a
	 * {@link android.support.v13.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;
	FragmentPagerAdapter fd;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	private String username;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_report);
		
		username = getIntent().getExtras().getString("username");
		report = new Report();
		report.username = username;
		
		

		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		// Create the adapter that will return a fragment for each of the three
		// primary sections of the activity.
		mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());
		
		
		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
		
		for(int i = 0; i < 4; i++){
			mSectionsPagerAdapter.getItem(i).onCreateView(getLayoutInflater(), mViewPager, null);
		}

		// When swiping between different sections, select the corresponding
		// tab. We can also use ActionBar.Tab#select() to do this if we have
		// a reference to the Tab.
		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						actionBar.setSelectedNavigationItem(position);
					}
				});
		

		// For each of the sections in the app, add a tab to the action bar.
		for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
			// Create a tab with text corresponding to the page title defined by
			// the adapter. Also specify this Activity object, which implements
			// the TabListener interface, as the callback (listener) for when
			// this tab is selected.
			actionBar.addTab(actionBar.newTab()
					.setText(mSectionsPagerAdapter.getPageTitle(i))
					.setTabListener(this));
		}
		

	}
	
	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		mViewPager.setCurrentItem(tab.getPosition());	
		
	}
	
	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction fragmentTransaction) {
	}
	
	@Override
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.report, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	

	private void setBasicInfo(){
		
		BasicInfoFragment basicInfo = (BasicInfoFragment) mSectionsPagerAdapter.getItem(0);
		
		TextView tv = (TextView) basicInfo.rootview.findViewById(R.id.scholar_name);
		report.studentName = tv.getText().toString();
		Spinner gradeSpinner = (Spinner) basicInfo.rootview.findViewById( R.id.grade_spinner);
		report.studentGrade = gradeSpinner.getSelectedItem().toString();
		DatePicker date = (DatePicker) basicInfo.rootview.findViewById(R.id.SelectDate);
		setReportDate(date);
	}
	
	private void clearBasicInfo(){
		BasicInfoFragment basicInfo = (BasicInfoFragment) mSectionsPagerAdapter.getItem(0);
		TextView tv = (TextView) basicInfo.rootview.findViewById(R.id.scholar_name);
		tv.setText("");
		DatePicker datePicker = (DatePicker) basicInfo.rootview.findViewById(R.id.SelectDate);
		Calendar c = Calendar.getInstance();
		int mYear = c.get(Calendar.YEAR);
		int mMonth = c.get(Calendar.MONTH);
		int mDay = c.get(Calendar.DAY_OF_MONTH);
		datePicker.init(mYear, mMonth, mDay, null);
		Spinner gradeSpinner = (Spinner) basicInfo.rootview.findViewById( R.id.grade_spinner);
		gradeSpinner.setSelection(0);
	}
	
	private void setReportDate(DatePicker date) {
		int day = date.getDayOfMonth();
   		int month = date.getMonth();
   		int year =  date.getYear();
   		Calendar calendar = Calendar.getInstance();
   		calendar.set(year, month, day);
   		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy", getResources().getConfiguration().locale);
   		report.reportCreatedDate = sdf.format(calendar.getTime());
	}
	
	private void setBehaviorData(){
		
		BehaviorReportFragment behaviorReport = (BehaviorReportFragment) mSectionsPagerAdapter.getItem(1);
		
		Spinner settingSpinner = (Spinner) behaviorReport.rootview.findViewById( R.id.behavior_settings_spinner);
		EditText comment = (EditText) behaviorReport.rootview.findViewById(R.id.behavior_comment);
		report.behaviorSetting = settingSpinner.getSelectedItem().toString();
		report.behaviorSummary = createSummary(behaviorReport.rootview, checkboxBehaviorIds);
		report.behaviorComment = comment.getText().toString();
	}
	
	private void clearBehaviorData(){
		
		BehaviorReportFragment behaviorReport = (BehaviorReportFragment) mSectionsPagerAdapter.getItem(1);
		
		Spinner settingSpinner = (Spinner) behaviorReport.rootview.findViewById( R.id.behavior_settings_spinner);
		settingSpinner.setSelection(0);
		EditText comment = (EditText) behaviorReport.rootview.findViewById(R.id.behavior_comment);
		comment.setText("");
		clearCheckbox(behaviorReport.rootview, checkboxBehaviorIds);
		
	}
	
	private void setAcademicData(){
		
		AcademicReportFragment academicReport = (AcademicReportFragment) mSectionsPagerAdapter.getItem(2);
		
		Spinner settingSpinner = (Spinner) academicReport.rootview.findViewById( R.id.academic_settings_spinner);
		settingSpinner.setSelection(0);
		EditText comment = (EditText) academicReport.rootview.findViewById(R.id.academic_comment);
		report.academicSetting = settingSpinner.getSelectedItem().toString();
		report.academicSummary = createSummary(academicReport.rootview, checkboxAcademicIds);
		report.academicComment = comment.getText().toString();
	}
	
	private void clearAcademicData(){
		
		AcademicReportFragment academicReport = (AcademicReportFragment) mSectionsPagerAdapter.getItem(2);
		
		Spinner settingSpinner = (Spinner) academicReport.rootview.findViewById( R.id.academic_settings_spinner);
		settingSpinner.setSelection(0);
		EditText comment = (EditText) academicReport.rootview.findViewById(R.id.academic_comment);
		comment.setText("");
		clearCheckbox(academicReport.rootview, checkboxAcademicIds);
		
	}
	
	private void setStrategyData(){
		
		StrategyReportFragment strategyReport = (StrategyReportFragment) mSectionsPagerAdapter.getItem(3);
		
		EditText comment = (EditText) strategyReport.rootview.findViewById(R.id.strategy_comment);
		report.strategySummary = createSummary(strategyReport.rootview, checkboxStrategyIds);
		report.strategyComment = comment.getText().toString();
	}
	
	private void clearStrategyData(){
		
		StrategyReportFragment strategyReport = (StrategyReportFragment) mSectionsPagerAdapter.getItem(3);
		
		EditText comment = (EditText) strategyReport.rootview.findViewById(R.id.strategy_comment);
		comment.setText("");
		clearCheckbox(strategyReport.rootview, checkboxStrategyIds);
		
	}
	
	private String createSummary(View view, int[] ids){
		
		CheckBox cb;
		
		String summary = "";
		
		for(int i: ids){
			cb = (CheckBox) view.findViewById(i);
			if(cb.isChecked()){
				summary += cb.getText() + "\n";
			}
		}
		
		return summary;
	}
	
	private void clearCheckbox(View view, int[] ids){
		
		CheckBox cb;
		
		for(int i: ids){
			cb = (CheckBox) view.findViewById(i);
			if(cb.isChecked()){
				cb.toggle();
			}
		}
		
	}
	
	
	public void onCheckboxClicked(View view) {
		
	}
	
	public void onClearButtonClick(View view){
		
		clearBasicInfo();
		clearBehaviorData();
		clearAcademicData();
		clearStrategyData();
		
	}
	
	public void onSubmitButtonClick(View view){
		
		setBasicInfo();
		setBehaviorData();
		setAcademicData();
		setStrategyData();
		
		// Save in background and notify the user.
    	if (!report.studentName.equals("")) {
    		
    		report.getParseObject().saveInBackground();
	    	Toast submittedNotification = Toast.makeText(this, "Report submitted!", Toast.LENGTH_SHORT);
	    	submittedNotification.show();
	    
	    	finish();
    	} else {
    		showFixUserInputDialog();
    	}
	}
	
	private void showFixUserInputDialog() {
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		
		alert.setTitle("Incomplete form");
		alert.setMessage("Please enter the student's name before submitting.");

		alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
			  }
			});

		alert.show();
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {
		
		private Fragment academicReportFragment = new AcademicReportFragment();
		private Fragment basicInfoFragment = new BasicInfoFragment();
		private Fragment behaviorReportFragment = new BehaviorReportFragment();
		private Fragment strategyReportFragment = new StrategyReportFragment();
		

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			switch (position) {
			case 0:
				return basicInfoFragment;
			case 1:
				return behaviorReportFragment;
			case 2:
				return academicReportFragment;
			case 3:
				return strategyReportFragment;
			default:
				return null;
			}
			
		}

		@Override
		public int getCount() {
			// Show 4 total pages.
			return 4;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_section1).toUpperCase(l);
			case 1:
				return getString(R.string.title_section2).toUpperCase(l);
			case 2:
				return getString(R.string.title_section3).toUpperCase(l);
			case 3:
				return getString(R.string.title_section4).toUpperCase(l);
			}
			return null;
		}
		
		public Object instantiateItem(View collection, int position) {

	        LayoutInflater inflater = (LayoutInflater) collection.getContext()
	                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	        int resId = 0;
	        switch (position) {
	        case 0: 
	            resId = R.layout.basic_info_report;             
	            break;
	        case 1:
	            resId = R.layout.behavior_report;         
	            break;              
	        case 2:     
	            resId = R.layout.academic_report;
	            break;
	        case 3:
	        	resId = R.layout.strategy_report;
	            break;      
	        }

	        View view = inflater.inflate(resId, null);
	        ((ViewPager) collection).addView(view, 0);
	        return view;
	    }
	}
	
	
	public class StrategyReportFragment extends Fragment {
		
		View rootview;

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			// Inflate the layout for this fragment
			rootview = inflater.inflate(R.layout.strategy_report, container, false);
			return rootview;
		}
	}
	
	public class BasicInfoFragment extends Fragment {
		
		View rootview;
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			// Inflate the layout for this fragment
			rootview = inflater.inflate(R.layout.basic_info_report, container, false);
			return rootview;
		}
		
		
	}
	
	public class AcademicReportFragment extends Fragment {
		
		View rootview;

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			// Inflate the layout for this fragment
			rootview = inflater.inflate(R.layout.academic_report, container, false);
			return rootview;
		}
	}
	
	public class BehaviorReportFragment extends Fragment {
		
		View rootview;

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			// Inflate the layout for this fragment
			rootview = inflater.inflate(R.layout.behavior_report, container, false);
			return rootview;
		}
	}
	



	

}
