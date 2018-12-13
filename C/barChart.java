package swing;

public class barChart {
	public String getPieChart() {
		String htmlString = "<script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>\r\n" + 
				"  <script type=\"text/javascript\">\r\n" + 
				"    google.charts.load(\"current\", {packages:['corechart']});\r\n" + 
				"    google.charts.setOnLoadCallback(drawChart);\r\n" + 
				"    function drawChart() {\r\n" + 
				"      var data = google.visualization.arrayToDataTable([\r\n" + 
				"        [\"Element\", \"Density\", { role: \"style\" } ],\r\n" + 
				"        [\"9\", 9, \"#b87333\"],\r\n" + 
				"        [\"10\", 8, \"silver\"],\r\n" + 
				"        [\"11\", 20, \"gold\"],\r\n" + 
				"        [\"12\", 3, \"color: #e5e4e2\"]\r\n" + 
				"      ]);\r\n" + 
				"\r\n" + 
				"      var view = new google.visualization.DataView(data);\r\n" + 
				"      view.setColumns([0, 1,\r\n" + 
				"                       { calc: \"stringify\",\r\n" + 
				"                         sourceColumn: 1,\r\n" + 
				"                         type: \"string\",\r\n" + 
				"                         role: \"annotation\" },\r\n" + 
				"                       2]);\r\n" + 
				"\r\n" + 
				"      var options = {\r\n" + 
				"        title: \"개월당 주문량\",\r\n" + 
				"        width: 127,\r\n" + 
				"        height: 129,\r\n" + 
				"        bar: {groupWidth: \"95%\"},\r\n" + 
				"        legend: { position: \"none\" },\r\n" + 
				"		 chartArea:{left:0,top:10,width:\"100%\",height:\"100%\"}" +
				"      };\r\n" + 
				"      var chart = new google.visualization.ColumnChart(document.getElementById(\"columnchart_values\"));\r\n" + 
				"      chart.draw(view, options);\r\n" + 
				"  }\r\n" + 
				"  </script>\r\n" + 
				"<div id=\"columnchart_values\" style=\"width: 132px; height: 134px;\"></div>";
		return htmlString;
	}
}
