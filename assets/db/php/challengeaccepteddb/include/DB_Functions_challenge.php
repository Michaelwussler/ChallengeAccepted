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

     	//Funktion um ein neuen Profileintrag zu erstellen 	   

	public function anmelden($vorname, $nachname, $email, $telefonnummer) {
		$uuid =uniqid('', true);
 		$result = mysql_query("INSERT INTO profil(vorname, nachname, email, telefonnummer) VALUES('$vorname', '$nachname', '$email', '$telefonnummer' )");

		// hier wird noch eine überprüfung, ob der eintrag des profiles erfolgreich war vorgenommen
		if($result) {
			$response["Profil"] = 1;
			$response["success"] = 1;
			echo json_encode($response);	
		return $result;
		}
		else
		{
		return false;
		}
		/*
		$id = mysql_insert_id();
		$result = mysql_query("SELECT * FROM profil WHERE id = $id");
		return mysql_fetch_array($result);
		}
		else
		{
		return false;
		}
		*/
	
	}
}
 
?>