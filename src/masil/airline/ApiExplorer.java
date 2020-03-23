package masil.airline;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.IOException;
	
@WebServlet("/board/*")
public class ApiExplorer extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
		System.out.println("doGet");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
		System.out.println("doPoset");
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * String depAirportId ="NAARKJJ"; String arrAirportId = "NAARKJJC";
		 * String depPlandTime = "20200223";
		 */
		System.out.println("doHandle");

		String depAirportId = "";
		String arrAirportId = "";
		String depPlandTime = "";
		

		System.out.println("1: "+depAirportId+" "+ arrAirportId + " " +depPlandTime);
		String nextPage = "";

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		String action = request.getPathInfo();

		System.out.println("action : " + action);

		try {
			/*
			 * List<Map<String,String>> airlineList; List<Map<String,String>>
			 * arilineDetail;
			 */

			if (action.equals("/airline.do")) {
				
				depAirportId = request.getParameter("depAirportId");
				arrAirportId = request.getParameter("arrAirportId");
				depPlandTime = request.getParameter("depPlandTime");
	
				System.out.println("2: "+depAirportId+" "+ arrAirportId + " " +depPlandTime);

				// StringBuilder urlBuilder = new
				// StringBuilder("http://openapi.tago.go.kr/openapi/service/DmstcFlightNvgInfoService/getFlightOpratInfoList?serviceKey=zCLZWIHNolxGTsYDcB7TXNgRT%2F%2F49r4ojZsQ%2BZeF9Y6y4M40m82CFg1nvbyK%2BvG3PCioT42YRMRJnQqZCSi9og%3D%3D&depAirportId=NAARKJJ&arrAirportId=NAARKPC&depPlandTime=20200223");
				// /*URL*/
				StringBuilder urlBuilder = new StringBuilder("http://openapi.tago.go.kr/openapi/service/DmstcFlightNvgInfoService/getFlightOpratInfoList");// URL
				urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=zCLZWIHNolxGTsYDcB7TXNgRT%2F%2F49r4ojZsQ%2BZeF9Y6y4M40m82CFg1nvbyK%2BvG3PCioT42YRMRJnQqZCSi9og%3D%3D"); // Service
																																	// Key
				urlBuilder.append("&" + URLEncoder.encode("depAirportId", "UTF-8") + "=" + URLEncoder.encode(depAirportId, "UTF-8")); //��߰���
				urlBuilder.append("&" + URLEncoder.encode("arrAirportId", "UTF-8") + "=" + URLEncoder.encode(arrAirportId, "UTF-8"));// ��������
				urlBuilder.append("&" + URLEncoder.encode("depPlandTime", "UTF-8") + "=" + URLEncoder.encode(depPlandTime, "UTF-8")); // �����
				
				urlBuilder.append("&" + URLEncoder.encode("_type", "UTF-8") + "="+ URLEncoder.encode("json", "UTF-8")); //json�������� �ޱ�
				URL url = new URL(urlBuilder.toString());
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Content-type", "application/json");
				System.out.println("Response code: " + conn.getResponseCode());
				BufferedReader rd;
				if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
					rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				} else {
					rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
				}
				StringBuilder sb = new StringBuilder();
				String line;
				while ((line = rd.readLine()) != null) {
					sb.append(line);
				}
				rd.close();
				conn.disconnect();
				System.out.println(sb.toString());
				request.setAttribute("airResult", sb.toString());
												
				nextPage = "/airline/airList.jsp";
			}

			RequestDispatcher dispatche = request.getRequestDispatcher(nextPage);
			dispatche.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}



