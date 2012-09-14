<?php
 
class DB_Functions {
 
    private $db;
 
    //Verantwortlich alle GET und POST requests zu verarbeiten
    // Konstructor
    function __construct() {
        require_once 'DB_connect.php';
        // verbinden mit der database
        $this->db = new DB_connect();
        $this->db->connect();
    }
 
    // Destructor
    function __destruct() {
 
    }
 	
 	//Funktion um einen Kartenpunkt zu erstellen
 	
 	public function kartenpunktErstellen($latitude, $longitude, $magneticvertical, $magnetichorizontal, $anzahl) {
 		$uuid =uniqid('', true);
 		$result = mysql_query("INSERT INTO magnetkarte(latitude, longitude, magneticvertical, magnetichorizontal, anzahl) VALUES('$latitude', '$longitude', '$magneticvertical', '$magnetichorizontal', '$anzahl' )");
// 		$result = mysql_query("INSERT INTO magnetkarte(uniqueid, latitude, longitude, altitude, time, start, stop, strecke) VALUES('$uuid', '$latitude', '$longitude', '$altitude', '$time', '$start', '$stop', '$strecke' )");

		// hier kann noch eine überprüfung, ob der eintrag des waypoints erfolgreich war vorgenommen werden
		
		if($result) {
		$id = mysql_insert_id();
		$result = mysql_query("SELECT * FROM magnetkarte WHERE id = $id");
		return mysql_fetch_array($result);
		}
		else
		{
		return false;
		}
 	}
 	   

	public function magnetkarteLaden($latitude, $longitude, $umkreis, $response) {
		$result = mysql_query("SELECT * FROM magnetkarte WHERE latitude < $latitude+$umkreis AND 
		latitude > $latitude-$umkreis AND longitude < $longitude+$umkreis AND longitude > $longitude-$umkreis"); 

		if($result) {
		$anzahlReihen = mysql_num_rows($result);
		
		$response["anzahlReihen"] = $anzahlReihen; 		
		$response["success"] = 1;

 		for($count =1; $count <= $anzahlReihen ; $count++) { 
			
			$result2 = mysql_fetch_array($result);
			
 		 	$response["laden" . (string)$count]["strecke"] = $result2["strecke"];
 		 	$response["laden" . (string)$count]["latitude"] = $result2["latitude"];
 		 	$response["laden" . (string)$count]["longitude"] = $result2["longitude"];
 		 	$response["laden" . (string)$count]["magneticvertical"] = $result2["magneticvertical"];
 		 	$response["laden" . (string)$count]["magnetichorizontal"] = $result2["magnetichorizontal"];   
 		 	$response["laden" . (string)$count]["anzahl"] = $result2["anzahl"];   

		}
		
		echo json_encode($response);
			
		return $result;
		
		}
		else
		{
		return false;
		}
	
	}
   
}
 
?>