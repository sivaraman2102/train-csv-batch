package com.siva.traincsvbatch.models;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "Train")
public class TrainInfo {
	
	public int train_No;
	
	
	public String train_Name;
	
	
	public int getTrain_No() {
		return train_No;
	}


	public void setTrain_No(int train_No) {
		this.train_No = train_No;
	}


	public String getTrain_Name() {
		return train_Name;
	}


	public void setTrain_Name(String train_Name) {
		this.train_Name = train_Name;
	}


	public String getSource_Station_Name() {
		return source_Station_Name;
	}


	public void setSource_Station_Name(String source_Station_Name) {
		this.source_Station_Name = source_Station_Name;
	}


	public String getDestination_Station_Name() {
		return destination_Station_Name;
	}


	public void setDestination_Station_Name(String destination_Station_Name) {
		this.destination_Station_Name = destination_Station_Name;
	}


	public String getDays() {
		return days;
	}


	public void setDays(String days) {
		this.days = days;
	}


	public String source_Station_Name;
	
	
	public String destination_Station_Name;
	
	
	public String days;


}
