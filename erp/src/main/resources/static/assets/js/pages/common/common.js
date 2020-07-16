'use strict';

function formattedDate(date){
	var tokens = date.split(" ");
	var month = "";
	switch(tokens[0]){
	case "Jan":
		month = "01";
		break;
	case "Feb":
		month = "02";
		break;
	case "Mar":
		month = "02";
		break;
	case "Apr":
		month = "02";
		break;
	case "May":
		month = "02";
		break;
	case "Jun":
		month = "02";
		break;
	case "Jul":
		month = "02";
		break;
	case "Aug":
		month = "02";
		break;
	case "Sep":
		month = "02";
		break;
	case "Oct":
		month = "02";
		break;
	case "Nov":
		month = "02";
		break;
	case "Dec":
		month = "02";
		break;
	}
	var day = tokens[1];
	var year = tokens[2];
	date = month + "/" + day + "/" +year;
	return date;
}