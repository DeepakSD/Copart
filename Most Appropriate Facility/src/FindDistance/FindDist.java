package FindDistance;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.text.PDFTextStripper;

import com.google.gson.Gson;

import DAO.ReadFromDataBase;
import DAO.ReadFromExcel;
import findState.Address;
import findState.Address.address_component;
import findState.ApiRequest;
import net.minidev.json.parser.ParseException;
/*
 * The class which finds the nearest copart facilities from the set of list. 
 * */
public class FindDist {
	
	/*
	 * We are finding the distance between two zip codes.
	 * We use ZIP Code API, to find the difference between two zipcodes.
	 * We return the zip code with minimum distance from the provided zipecode.
	 * @param postalCode, User zipcode
	 * @param zipcodeCoPart, zipcode of the CoPart Facility
	 * @return JSON, result of distance between two zip codes.  
	 * */
	private static String dist(int postalCode, int zipcodeCoPart) throws IOException {
		URL url;String jsonResult = "";
			url = new URL("https://www.zipcodeapi.com/rest/HmM8gXGVxY3JTeCBMPqdZGtxXWIZ0QLHcyQswxo3wrNpGq0sfrFe89M65u56lROx/distance.json/" + postalCode +"/" + zipcodeCoPart+ "/km");
			URLConnection connection = url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			
			while ((inputLine = in.readLine()) != null) {
			    jsonResult += inputLine;
			}
			in.close();
			
		return jsonResult;
		
	}
	
	/*
	 * Converts PDF to text and from text we extract all the zipcodes based on regular expression
	 * */
	public static List<String> getAllZipCodes() throws InvalidPasswordException, IOException{
		File file = new File("D:\\Copart.pdf");
	      PDDocument document = PDDocument.load(file);

	      PDFTextStripper pdfStripper = new PDFTextStripper();

	      String text = pdfStripper.getText(document);

	      document.close();

	      List<String> allMatches = new ArrayList<String>();
	      Matcher m = Pattern.compile("(\\d{5,5})")
	          .matcher(text);
	      while (m.find()) {
	        allMatches.add(m.group());
	      }
	      return allMatches;
	}
	/*
	 *  Gets the postal zipcode from the user and list of available zipcodes of copart and 
	 *  returns the nearest copart zipcode.
	 *  @param zipcode, postal zipcode
	 *  @parm allZipCodes, list of zipcodes for the copart which has the facility
	 * */
	private static int findNearest(int zipcode, List<String> allZipCodes) throws IOException{
		Gson gson = new Gson();
		double min = Integer.MAX_VALUE; int nearestZipCode = Integer.parseInt(allZipCodes.get(0));
		for(String availableZipcode: allZipCodes){
			String distString = dist(zipcode, Integer.parseInt(availableZipcode));
			Distance distObject = gson.fromJson(distString, Distance.class);
			if(min > Double.parseDouble(distObject.getDistance())){
				min = Double.parseDouble(distObject.getDistance());
				nearestZipCode = Integer.parseInt(availableZipcode);
			}
		}
		return nearestZipCode;
	}
	
	public static void main(String[] args) throws IOException, ParseException, NumberFormatException, SQLException{
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the customer ID");
		String customerId = in.nextLine();
		System.out.println("Enter the vehicle type");
		String vehicleType = in.nextLine();
		System.out.println("Enter the postal Code");
		String zipCode = in.nextLine();
		
		// reads excel data of the copart facility and provides us the hashmap of the zipcode with yard.
		ReadFromExcel readFromExcel = new ReadFromExcel();
		HashMap<Integer,Integer> zipCodeForYard = new HashMap<>();
		zipCodeForYard = readFromExcel.readFromExcel();
		
		
		//gets the yard list fot the specific custoemr and vechicle type
		ArrayList<Integer> yardList = ReadFromDataBase.getYardNumber(vehicleType, Integer.parseInt(customerId), Integer.parseInt(zipCode));
		
		
		// gets the state of the provided zipcode
		Gson gson = new Gson();
		Address result = gson.fromJson(ApiRequest.jsonCoord(URLEncoder.encode(zipCode, "UTF-8")), Address.class);
		address_component state  = result.results[0].address_components[2]; 
		
		
		// all the zipcodes will be added in this list
		List<String> allZipCodes = new ArrayList<String>();
		
		
		if(yardList.size() ==1 && yardList.get(0) == 1){ // case OUTofState = 1 and it needs to see in all the neighbouring state
			allZipCodes = getAllZipCodes();
		}else if(yardList.size() == 1 && yardList.get(0) == -1){ // case OUTofState = -1, all zipcodes of same state are added here.
			for(String zipLocal: getAllZipCodes()){
			Gson gsonLocal = new Gson();
			Address resultLocal = gsonLocal.fromJson(ApiRequest.jsonCoord(URLEncoder.encode(zipLocal, "UTF-8")), Address.class);
		
			address_component stateLocal  = resultLocal.results[0].address_components[2]; 
			if(stateLocal.equals(state)){
				allZipCodes.add(zipLocal);
			}
			}
			
		}else if(yardList.size() == 1){ // case OUTofState = 0, zipcode for yard given is added
			allZipCodes.add(zipCodeForYard.get(yardList.get(0)).toString());
			
		} else{   // case OUTofState = 1, all zipcodes for the provoded list of yard are added
			for(Integer yardLocal: yardList){
				allZipCodes.add(zipCodeForYard.get(yardLocal).toString());
			}
		}
		
		
		System.out.println(findNearest(Integer.parseInt(zipCode), allZipCodes));
		
	}

}
