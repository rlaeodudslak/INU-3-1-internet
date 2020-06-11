package homeworkpro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.net.ssl.HttpsURLConnection;

public class GmarketLogin {

	private String myCookies;

	public String login(String id, String password) {
		URL obj;

		String myResult = "";
		try {
			HashMap<String, String> pList = new HashMap<String, String>();
			List<String> tokens = Crawler.GetGmarketToken();
			String cookieToken = tokens.get(0);
			String htmlToken = tokens.get(1);
			pList.put("id", id);
			pList.put("password", password);
			pList.put("__RequestVerificationToken", htmlToken);
			pList.put("url", "https://myg.gmarket.co.kr");
			pList.put("command", "login");

			obj = new URL("https://signinssl.gmarket.co.kr/LogIn/LogInProc");
			HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
			String cookie = "";
			// cookie +=
			// "connect.sid=s%3A35IOsKeAn9EuAemR7zR_hPfSfNOkMMjZ.RHOKSDnLSoj2UX7gX6eSPyuHjr9nzJyA3eSiIwXCUMU;";

			cookie += "pguid=21591797224265001632010000;";
			cookie += "__RequestVerificationToken=" + cookieToken + ";";
			cookie += "ssguid=315917972242650016322830000;";
			cookie += "sguid=31591797224265001632283000;";
			cookie += "cguid=11591797224265001632000000;";
			cookie += "pcidx=E46E2E360405306169D8F0D6E46BF537839A8D58FEF63D8F8EB1610F8D11754D;";
			cookie += "shipnation=KR;";
			cookie += "charset=enUS;";
			cookie += "mgim=mgim;";
			con.setRequestProperty("Cookie", cookie);
			con.setRequestProperty("content-type", "application/x-www-form-urlencoded");
			StringBuffer buffer = new StringBuffer();

			// HashMap으로 전달받은 파라미터가 null이 아닌경우 버퍼에 넣어준다
			if (pList != null) {

				Set key = pList.keySet();

				for (Iterator iterator = key.iterator(); iterator.hasNext();) {
					String keyName = (String) iterator.next();
					String valueName = pList.get(keyName);
					buffer.append(keyName).append("=").append(valueName);
					buffer.append("&");
				}
			}
			buffer.deleteCharAt(buffer.length() - 1);

			con.setDoOutput(true);
			OutputStreamWriter outStream = new OutputStreamWriter(con.getOutputStream(), "UTF-8");
			PrintWriter writer = new PrintWriter(outStream);
			writer.write(buffer.toString());
			writer.flush();

			// --------------------------
			// Response Code
			// --------------------------
			// http.getResponseCode();

			String cookieTemp = con.getHeaderField("Set-Cookie");
			if (cookieTemp != null) {
				myCookies = cookieTemp;
			}

			System.out.println(con.getResponseCode());
			System.out.println("cookies");
			System.out.println(myCookies);

			// --------------------------
			// 서버에서 전송받기
			// --------------------------
			InputStreamReader tmp = new InputStreamReader(con.getInputStream(), "UTF-8");
			BufferedReader reader = new BufferedReader(tmp);
			StringBuilder builder = new StringBuilder();
			String str;
			while ((str = reader.readLine()) != null) {
				builder.append(str + "\n");
			}
			myResult = builder.toString();

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(myResult);
		return myResult;
	}

	public String myg() {
		URL obj;

		String myResult = "";
		try {

			obj = new URL("https://myg.gmarket.co.kr/");
			HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
			String cookie = "";
			con.setRequestProperty("Cookie", myCookies);
			con.setRequestProperty("content-type", "application/x-www-form-urlencoded");
			StringBuffer buffer = new StringBuffer();

			con.setDoOutput(true);
			OutputStreamWriter outStream = new OutputStreamWriter(con.getOutputStream(), "UTF-8");
			PrintWriter writer = new PrintWriter(outStream);
			writer.write(buffer.toString());
			writer.flush();

			// --------------------------
			// Response Code
			// --------------------------
			// http.getResponseCode();

			// --------------------------
			// 서버에서 전송받기
			// --------------------------
			InputStreamReader tmp = new InputStreamReader(con.getInputStream(), "UTF-8");
			BufferedReader reader = new BufferedReader(tmp);
			StringBuilder builder = new StringBuilder();
			String str;
			while ((str = reader.readLine()) != null) {
				builder.append(str + "\n");
			}
			myResult = builder.toString();

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return myResult;
	}
}
