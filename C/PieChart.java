package swing;

public class PieChart {
	public String getPieChart() {
		String htmlString = "<html>\r\n" + 
				"  <head>\r\n" + 
				"    <script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>\r\n" + 
				"    <script type=\"text/javascript\">\r\n" + 
				"      google.charts.load('current', {'packages':['corechart']});\r\n" + 
				"      google.charts.setOnLoadCallback(drawChart);\r\n" + 
				"\r\n" + 
				"      function drawChart() {\r\n" + 
				"\r\n" + 
				"        var data = google.visualization.arrayToDataTable([\r\n" + 
				"          ['Task', 'Hours per Day'],\r\n" + 
				"          ['Chicken',     11],\r\n" + 
				"          ['Pizza',      2],\r\n" + 
				"          ['Chinese',  2],\r\n" + 
				"        ]);\r\n" + 
				"\r\n" + 
				"        var options = {\r\n" + 
				"          title: '음식 주문 비율'\r\n," + 
				"		   chartArea:{left:0,top:10,width:\"100%\",height:\"100%\"}\r\n" +
				"        };\r\n" + 
				"\r\n" + 
				"        var chart = new google.visualization.PieChart(document.getElementById('piechart'));\r\n" + 
				"\r\n" + 
				"        chart.draw(data, options);\r\n" + 
				"      }\r\n" + 
				"    </script>\r\n" + 
				"  </head>\r\n" + 
				"  <body>\r\n" + 
				"    <div id=\"piechart\" style=\"width: 189px; height: 129px;\"></div>\r\n" + 
				"  </body>\r\n" + 
				"</html>";
		return htmlString;
	}
}
