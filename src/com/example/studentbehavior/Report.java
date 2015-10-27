/**
 * 
 */
package com.example.studentbehavior;

import android.os.Parcel;
import android.os.Parcelable;

import com.parse.ParseObject;

/**
 * Incoprates different reports.
 * @author Ronald Martin
 * Object describing a behavior report
 */
public class Report implements Parcelable {
	
	public String objectID = "";

	// Subject Info
	public String studentName       = "";
	public String studentGrade      = "";
	public String reportCreatedDate = "";
	
	// Summaries
	public String behaviorSummary = "";
	public String academicSummary = "";
	public String strategySummary = "";
	
	// Behavior Report
	public PositiveBehavior PB = new PositiveBehavior();
	public NegativeBehavior NB = new NegativeBehavior();
	
	public boolean behavior_other = false; 
	public String behaviorSetting = "";
	
	// Academic report
	public boolean academic_other = false;
	public AcademicReport AR = new AcademicReport();

	// Strategy report
	public StrategyReport SR = new StrategyReport();
	public boolean strategy_other = false;

	// settings and comment
	public String academicSetting = "";
	public String reportDetailsAndComments = "";
	public String strategyComment = "";
	public String academicComment = "";
	public String behaviorComment = "";
	public String username = "";
	
	/**
	 * 
	 */
	public Report() {
		
	}
	
	 /*
     * Create a Report object from an object retrieved from Parse
     */
    public Report(ParseObject parseObject) {
        // retrieve report subject info.
        this.studentName       = parseObject.getString("studentName");
        this.studentGrade      = parseObject.getString("studentGrade");
        this.reportCreatedDate = parseObject.getString("date");
        this.objectID = parseObject.getObjectId();
        this.username = parseObject.getString("username");
        
        // retrieve report details
        retrieveBehaviorData(parseObject);
        retrieveAcademicData(parseObject);
        retrieveStrategyData(parseObject);
        
        // populate data
        this.reportDetailsAndComments = parseObject.getString("report_details");
        this.behaviorComment = parseObject.getString("behavior_comments");
        this.academicComment = parseObject.getString("academic_comments");
        this.strategyComment = parseObject.getString("strategy_comments");
        this.behaviorSummary = parseObject.getString("behavior_details");
        this.academicSummary = parseObject.getString("academic_details");
        this.strategySummary = parseObject.getString("strategy_details");
        
    }
    
    private void retrieveBehaviorData(ParseObject parseObject) {
        // retrieve behavior report data (positive).
    	PB.retrievePositiveData(parseObject);
        NB.retrieveNegativeData(parseObject);

        // retrieve behavior setting
        this.behaviorSetting = parseObject.getString("behavior_setting");
        this.behavior_other = parseObject.getBoolean("behavior_other");
    }
    
    private void retrieveAcademicData(ParseObject parseObject) {
        // retrieve academic data.
    	this.academicSetting = parseObject.getString("academic_setting");
    	this.academic_other = parseObject.getBoolean("academic_other");
     	AR.retrieveAcademicData(parseObject);
    }
    
    private void retrieveStrategyData(ParseObject parseObject) {
        // retrieve strategy data.
        SR.retrieveStrategyData(parseObject);
        this.strategy_other = parseObject.getBoolean("strategy_other");
    }
    
    /*
     * Return this Report as a Parse Object to push to the database.
     */
    public ParseObject getParseObject() {
    	
        ParseObject reportParse = new ParseObject("Report");
        // Add report subject info.
        reportParse.put("studentName", studentName);
        reportParse.put("studentGrade", studentGrade);
        reportParse.put("date", reportCreatedDate);
        
        // Add behavior report data.
        for(String key: PB.PosBehaviorMap.keySet()){
        	reportParse.put(key, PB.PosBehaviorMap.get(key));
        }
        
        for(String key: NB.NegBehaviorMap.keySet()){
        	reportParse.put(key, NB.NegBehaviorMap.get(key));
        }

        // Add other behavior report data.
        reportParse.put("behavior_other", behavior_other);
        reportParse.put("behavior_setting", behaviorSetting);
        
        // Add academic data.
        for(String key: AR.AcademicMap.keySet()){
        	reportParse.put(key, AR.AcademicMap.get(key));
        }

        reportParse.put("academic_other", academic_other);
        reportParse.put("academic_setting", academicSetting);
        
        // Add strategy data.
        for(String key: SR.StrategyMap.keySet()){
        	reportParse.put(key, SR.StrategyMap.get(key));
        }
        
        // 
        reportParse.put("strategy_other", strategy_other);
        reportParse.put("report_details", reportDetailsAndComments);      
        reportParse.put("behavior_comments", this.behaviorComment);
        reportParse.put("academic_comments",this.academicComment);
        reportParse.put("strategy_comments", this.strategyComment);
        reportParse.put("behavior_details", this.behaviorSummary);
        reportParse.put("academic_details", this.academicSummary);
        reportParse.put("strategy_details", this.strategySummary);
        reportParse.put("object_id", this.objectID);
        reportParse.put("username", username);
        
        return reportParse;
    }
    
    @Override
    public String toString() {
        return this.studentName + " " + this.reportCreatedDate;
    }
    
	@Override
	public int describeContents() {
		return 0;
	}

	/**
	 * Write information to parcel
	 */
	@Override
	public void writeToParcel(Parcel out, int flags) {
		out.writeString(this.studentName);
		out.writeString(this.studentGrade);
		out.writeString(this.reportCreatedDate);
		out.writeString(this.behaviorSetting);
		out.writeString(this.behaviorSummary);
		out.writeString(this.behaviorComment);
		out.writeString(this.academicSetting);
		out.writeString(this.academicSummary);
		out.writeString(this.academicComment);
		out.writeString(this.strategySummary);
		out.writeString(this.strategyComment);
		out.writeString(this.reportDetailsAndComments);
		out.writeString(this.objectID);
	}
	
	private Report(Parcel in) {
		this.studentName              = in.readString();
		this.studentGrade             = in.readString();
		this.reportCreatedDate        = in.readString();
		this.behaviorSetting		  = in.readString();
		this.behaviorSummary          = in.readString();
		this.behaviorComment		  = in.readString();
		this.academicSetting		  = in.readString();
		this.academicSummary          = in.readString();
		this.academicComment		  = in.readString();
		this.strategySummary          = in.readString();
		this.strategyComment		  = in.readString();
		this.reportDetailsAndComments = in.readString();
		this.objectID			 	  = in.readString();
	}

	/**
	 * 
	 */
	public static final Parcelable.Creator<Report> CREATOR
			= new Parcelable.Creator<Report>() {
		@Override
		public Report createFromParcel(Parcel in) {
			return new Report(in);
		}

		@Override
		public Report[] newArray(int size) {
			return new Report[size];
		}
	};
	
	/**
	 * Create email report content.
	 */
	public String createEmailReportString() {
		String report = String.format(
				"Student Behavior Report \n\nName: %s\nGrade: %s\nDate: %s ",
				studentName, studentGrade, reportCreatedDate);
				 
		if (!behaviorSummary.equals("")) {
			report = report + "\n\nBehavior Summary: \n" + behaviorSummary + repo(behaviorSetting, behaviorComment);
		}
		if (!academicSummary.equals("")) {
			report = report + "\n\nAcademic Summary: \n" + academicSummary + repo(academicSetting, academicComment);
		}
		if (!strategySummary.equals("")) {
			report = report + "\n\nStrategy Summary: \n" + strategySummary + repo("", strategyComment);
		}
		return report;
	}

	private String repo(String...content) {
		String result = "";
		for (int i = 0; i < 2; i++) {
			switch (content[i]) {
			case "":
				break;
			default:
				if (i == 0)
					result += "\nSetting: " + content[0];
				else
					result += "\n\nComments: " + content[1];
			}
		}
		return result;
	}

}
