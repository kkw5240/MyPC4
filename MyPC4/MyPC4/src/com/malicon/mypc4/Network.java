package com.malicon.mypc4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Network extends Thread {
	
	ArrayList<String> blackList = new ArrayList<String>();	// Product exception remove(for filter)
	AllParts allParts = null;	// All Product
	
	ArrayList<String> arg = null;	// Each Products's Address(sort)
	
	public Network() {
		arg = new ArrayList<String>();
		allParts = AllParts.getInstance();
		
		/* Add arg(address) */
		arg.add("&minPrice=10&maxPrice=99999999");
		arg.add("&minPrice=10&maxPrice=99999999");
		arg.add("&minPrice=10&maxPrice=99999999&attributeValues=35101&attributeValues=4400");
		arg.add("&minPrice=10&maxPrice=99999999");
		arg.add("&minPrice=10&maxPrice=99999999&attributeValues=1244&attributeValues=1245,1246");
		arg.add("&minPrice=10&maxPrice=105000");
		arg.add("&minPrice=10&maxPrice=99999999");
		arg.add("&minPrice=10&maxPrice=99999999&attributeValues=4772");
		arg.add("&minPrice=10000&maxPrice=99999999");
		
		/* Add blackList */
		blackList.add("가이드");
		blackList.add("브라켓");
		blackList.add("카드리더기");
		blackList.add("주변기기");
		blackList.add("스페이서");
		blackList.add("어댑터");
		blackList.add("메모리스틱");
		blackList.add("복사기");
		blackList.add("노트북");
		blackList.add("FDD");
		blackList.add("CTRL FRONT USB3.0");
		blackList.add("A-Cross Kit");
		blackList.add("액세서리");
		blackList.add("제온");
		blackList.add("BTX");
	}
	
	public Network(ArrayList<String> arg) {	// Constructor
		this();
		this.arg = arg;
	}
	
	@Override
	public void run() {	// Network thread
		
		//send("")
		String url = "http://m.danawa.com/new/product/products.xml?categoryCode1=861&categoryCode2=";	// Basic address
		String urlList[] = new String[]{
				url+"873&categoryCode3=0&categoryCode4=0&order=BEST&page=1&limit=20",			// CPU's Address
				url+"875&categoryCode3=0&categoryCode4=0&order=MinPrice&page=1&limit=20",		// MainBoard's Address
				url+"877&categoryCode3=0&categoryCode4=0&order=MinPrice&page=1&limit=20",		// HDD's Address
				url+"32617&categoryCode3=0&categoryCode4=0&order=MinPrice&page=1&limit=20",		// SSD's Address
				url+"874&categoryCode3=0&categoryCode4=0&order=MinPrice&page=1&limit=20",		// RAM's Address
				url+"876&categoryCode3=0&categoryCode4=0&order=MinPrice&page=1&limit=20",		// VGA's Address
				url+"880&categoryCode3=0&categoryCode4=0&order=BEST&page=1&limit=20",			// Power's Address
				url+"878&categoryCode3=0&categoryCode4=0&order=MinPrice&page=1&limit=20",		// ODD's Address
				url+"879&categoryCode3=0&categoryCode4=0&order=MinPrice&page=1&limit=20"		// Case's Address
		};
		for(int i=0;i<urlList.length;i++)
			if(!arg.get(i).equals("none"))
				getInfo(urlList[i]+arg.get(i),allParts.getParts(i),1);	// Add all products's information(code, name, attribute, price)
			

		Loading.changemain.sendEmptyMessage(0);	// Loading(ProgressBar)
	}
	/*
	void send(String str){
		Message msg = new Message();
		msg.obj = (Object)str;
		Loading.uptext.sendMessage(msg);
	}*/
	
	static String remote(String site, String charset) {		// Read XMLpage
		StringBuffer sBuffer = new StringBuffer();
		String str = "";
		try {
			String urlAddr = site;
			HttpURLConnection conn = (HttpURLConnection) (new URL(urlAddr)
					.openConnection());
			if (conn != null) {
				conn.setConnectTimeout(20000);
				conn.setUseCaches(false);
				if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
					BufferedReader br = new BufferedReader(
							new InputStreamReader(conn.getInputStream(), charset));
					while (true) {
						String line = br.readLine();
						if (line == null)
							break;
						sBuffer.append(line);
					}
					br.close();
					conn.disconnect();
				}
			}
			str = sBuffer.toString();
		} catch (Exception e) {}
		return str;
	}
	
	private void getInfo(String url,ArrayList<Part> arr,int page){		// Get product's information(substring XMLpage)
		String line = null;

		if(page == 1)
			arr.clear();
		
		line = remote(url, "UTF-8");
		
		int totalCount = Integer.parseInt(substring(line, "<totalCount>",12, "</totalCount>",0));
		
		line = line.substring(line.indexOf("<product>")+1);
		
		
		while(true) {
			Part tmp = new Part();	// Make one part(product)'s info
			tmp.code = substring(line,"<code>",6, "</code>",0);
			tmp.name = substring(line, "<makerName>",11, "</makerName>",0).trim();
			if(line.indexOf("<brandName>") < line.indexOf("</product>"))
				tmp.name += " " + substring(line, "<brandName>",11, "</brandName>",0);
			tmp.name += " " + substring(line, "<name>",6, "</name>",0).trim();

			tmp.attribute = substring(line, "<option>",8, "</option>",0).trim();
			
			int i=0;
			for(i=0;i<blackList.size();i++){
				if(tmp.name.indexOf(blackList.get(i)) != -1 ||
						tmp.attribute.indexOf(blackList.get(i)) != -1)
					break;
			}
			if(i == blackList.size()){
				tmp.price = substring(line, "<minPrice>",10, "</minPrice>",0);
				
				arr.add(tmp);
			}
			if(line.indexOf("<product>")!=-1)
				line = line.substring(line.indexOf("<product>")+1);
			else break;
		}
		
		if(totalCount >= 20 && arr.size() < 20){
			getInfo(url.substring(0, url.indexOf("page="))+"page="+(page+1)+url.substring(url.indexOf("page=")+5+(""+page).length()),arr,page+1);
		}

	}	
	
	static String substring(String base, String str1, int index1, String str2, int index2){
		if(base.indexOf(str1)!=-1 && base.indexOf(str2)!=-1 && base.indexOf(str1) < base.indexOf(str2) ){
			return base.substring(base.indexOf(str1)+index1, base.indexOf(str2)+index2);
		}
		return "";
	}

}
