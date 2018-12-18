package swing;

import java.util.ArrayList;

public class barChart {
	public String getBarChart(ArrayList<PieElement> list) {
		String htmlString = "<html>\r\n" + 
				"  <head>\r\n" + 
				"    <script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>\r\n" + 
				"    <script type=\"text/javascript\">\r\n" + 
				"      google.charts.load(\"current\", {packages:[\"imagebarchart\"]});\r\n" + 
				"      google.charts.setOnLoadCallback(drawChart);\r\n" + 
				"\r\n" + 
				"      function drawChart() {\r\n" + 
				"\r\n" + 
				"        var data = google.visualization.arrayToDataTable([\r\n" + 
				"          ['', ''],\r\n";
				for (int i = 0; i < list.size(); i++) {
					htmlString += "['" + list.get(i).getName() + "', " + list.get(i).getValue() + "],\r\n";
				}
				htmlString +="        ]);\r\n" + 
				"\r\n" + 
				"        var chart = new google.visualization.ImageBarChart(document.getElementById('chart_div'));\r\n" + 
				"\r\n" + 
				"        chart.draw(data, {width: 132, height: 134, min: 0});\r\n" + 
				"      }\r\n" + 
				"    </script>\r\n" + 
				"  </head>\r\n" + 
				"  <body>\r\n" + 
				"    <div id=\"chart_div\" style=\"width: 132px; height: 134px;\"></div>\r\n" + 
				"  </body>\r\n" + 
				"</html>";
		return htmlString;
	}
}
