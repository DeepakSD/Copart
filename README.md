# Copart-Coding-Challenge

Team Members: Ashwin Kumar Muruganandam, Deepak Shanmugam

Contributions: FrontEnd: Deepak Shanmugam, BackEnd: Ashwin Kumar Muruganandam

## Problem: Most Appropriate Facility

We converted PDF of Copart facility to excel sheet and from excel sheet. We ran through the excel sheet and mapped yard with zipcode in a hashmap. With yard being the key and zipcode being the value. We could also get the entire list of the zipcode from the excel sheet. But in our case we tried an another method of converting the pdf to text and using regular expression to get the list of zipcodes. Now we have all the list of zipcodes and map with yard and zipcodes. We retrieve the zipcode, vehicle type and customer id from the user. Based on the input we retrieve the out of state value and yard value. We could either use excel sheet to read or use database. In our case we have used database. We need to get the zipcode list based on the rules provided and once we have that we need to find the closest distance copart facility. 
			We have 4 cases
				(i)  case OUTofState = 1 and it needs to see in all the neighbouring state
				(ii) case OUTofState = -1, all zipcodes of same state are added here.
				(iii) case OUTofState = 0, zipcode for yard given is added
				(iv) case OUTofState = 1, all zipcodes for the provoded list of yard are added
				
	For case (i), we just add all the zipcodes we have already retrieved. case(ii) we iterate through all zipcodes, we use google geocoding API and retrieve the state. We compare the state of the user entered zipcode and for each zipcode and if it matches we add to the list. case(iii) we retrieve the zipcode from hashmap for the given yard and for case(iv) we iterate the list of yard, we retrieve the zipcode for each yard from hashmap and that to the list. 

	Now we use another API, zipcodeapi to retrieve the distance between two zipcodes, We find the minimum distance and store its zipcode. This zipcode will be nearest facility. 
	
		
	API used - 
		(i)google geocoding api for getting state
		(ii) zipcodeapi for getting distance betweeen 2 zipcodes.
	
	Technologies - 
		(i) - Converted PDF to text (Apache PDF BOX)
		(ii) - Read from Excel (APache POI)
		(iii) - Regular expression


## Problem: License Keys 

Got the string and K values from user. Change all the characters to uppercase and remove "-".
If the size of the string does have a remainder(s1), append those much number of characters initially.If the size of the string does not have a remainder, then store K in s1.
If s1 is less than length of the string, then append "-" and K number of characters from string and continue this process unitl s1 greater than or equals size of the string.

Technology : Java

## Problem: Convert String to Integer

Got the string from the user.
Initialize the "result" variable as 0.
Repeat the following step:
Find a number by taking the difference between ascii of that particular character and the ascii of character '0' and add with result multiplied by 10.

This will eventually return the resulting number in integer type.

Technology: Java

## Problem: Coding Exercises - Problem statements (JavaScript or ReactJS)

We created a HTML file with phone number and address fields
Linked that HTML file with a external javascript file for validation purposes.
For address fields, we used the google maps api and made the address components to fall in the corresponding locations.

Technology: HTML, Javascript


## Problem: JS Library/framework* based - Nested ListView

Created a .js file(Angular js)
Showcased both Single JSON response and Multiple JSON response with some sample data.
Reused the components and reused data from cache/store.

Technology: Angular JS

Problem: Extract Text from Image/PDF
We converted the the pdf to text. By using apache PDF box. We could use regular expression to read from the string. The code wont work for images.

Technology: Java
