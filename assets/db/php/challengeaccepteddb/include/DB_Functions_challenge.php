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

	public function anmelden($name, $email, $telefonnummer) {
		$uuid =uniqid('', true);
 		$result = mysql_query("INSERT INTO profil(name, email, telefonnummer) VALUES('$name', '$email', '$telefonnummer' )");

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
	}

	     	//Funktion um ein neuen Profileintrag zu überprüfen   

	public function isUser($telefonnummer) {
		$result = mysql_query("SELECT * FROM profil WHERE telefonnummer = $telefonnummer"); 

		if($result) {
		$anzahlReihen = mysql_num_rows($result);
		$result2 = mysql_fetch_array($result);

		$response["anzahlReihen"] = $anzahlReihen; 		
		$response["telefonnummer"] = $result2["telefonnummer"];
		//$response["id"] = $result2["id"];
		$response["success"] = 1; 	
		echo json_encode($response);
			
		return $result;
		
		}
		else
		{
		return false;
		}
	}
			//Funktion um ein Profileintrag zu löschen (anhand der email oder telefonnummer)

		public function removeUser($email_telefonnummer) {
		$result = mysql_query("DELETE FROM profil WHERE telefonnummer = $email_telefonnummer OR email = $email_telefonnummer"); 
		$anzahlReihen = mysql_affected_rows();

		if($result) {
		
		$response["anzahlReihen"] = $anzahlReihen; 		
		$response["success"] = 1; 	
		echo json_encode($response);
			
		return $result;
		
		}
		else
		{
		return false;
		}
	
	}

	public function areUsers($telefonnummer, $count, $array) {

		$result = mysql_query("SELECT * FROM profil WHERE telefonnummer = $telefonnummer"); 

		if($result) {
				$anzahlReihen = mysql_num_rows($result);
				if($anzahlReihen!=0){
				$result2 = mysql_fetch_array($result);
				$response["result" . (string)$count][(string)$count] = $result2["telefonnummer"]; 		
				$array["challenges"]["result" . (string)$count]=$result2["telefonnummer"];
			//	$response["telefonnummer"] = $result2["telefonnummer"];
				//echo json_encode($response);
				}
				else{
				$array["challenges"]["result" . (string)$count]="not";	
				}
		return $array;
			}
		else
		{
		return false;
		}
	
	}

	public function createTable($telefonnummer) {

	          $sql =  "CREATE TABLE a9635519_challen.$telefonnummer (
                  serverid INT NOT NULL AUTO_INCREMENT ,
                  id VARCHAR( 10 ) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
                  title VARCHAR( 40 ) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
                  description VARCHAR( 400 ) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
                  receiver VARCHAR( 20 ) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
                  sender VARCHAR( 20 ) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
                  channel VARCHAR( 20 ) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
                  status VARCHAR( 2 ) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
                  time VARCHAR( 15 ) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
                  rupdate VARCHAR( 5 ) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
                  supdate VARCHAR( 5 ) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
                  PRIMARY KEY ( serverid ) ,
                  UNIQUE (id)
                  ) ENGINE = MYISAM";

	$result = mysql_query($sql);

		if($result) {
			$response["success"] = "1";
				echo json_encode($response);
			
		return $result;
		}
		else
		{
		return false;
		}
	}

	public function dropTable($telefonnummer) {

	          $sql = "DROP TABLE IF EXISTS a9635519_challen.$telefonnummer";

	$result = mysql_query($sql);

		if($result) {
			$response["success"] = "1";
				echo json_encode($response);
			
		return $result;
		}
		else
		{
		return false;
		}
	}

	public function profilSync($telefonnummer) {

		$result = mysql_query("SELECT * FROM profil WHERE telefonnummer = $telefonnummer"); 
		$anzahlReihen = mysql_num_rows($result);

		if($result) {
				//
				$response["anzahlReihen"] = $anzahlReihen; 		
				$response["successe"] = "1";

		 		//for($count =1; $count <= $anzahlReihen ; $count++) { 
					
				//	$result2 = mysql_fetch_array($result);
					
		 		 //	$response["argprofil" . (string)$count]["telefonnummer"] = $result2["telefonnummer"];
				//}

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


